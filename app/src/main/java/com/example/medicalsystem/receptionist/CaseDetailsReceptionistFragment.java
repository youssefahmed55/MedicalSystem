package com.example.medicalsystem.receptionist;


import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.medicalsystem.R;
import com.example.medicalsystem.databinding.FragmentCaseDetailsReceptionistBinding;
import com.example.medicalsystem.pojo.MessageResponseModel;
import com.example.medicalsystem.pojo.calls.callsreceptionist.CallsData;

import com.example.medicalsystem.singleliveevent.EventHandler;
import com.example.medicalsystem.singleliveevent.EventObserver;
import com.example.medicalsystem.viewmodel.MedicalSystemViewModel;


import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CaseDetailsReceptionistFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CaseDetailsReceptionistFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CaseDetailsReceptionistFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CaseDetailsReceptionistFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CaseDetailsReceptionistFragment newInstance(String param1, String param2) {
        CaseDetailsReceptionistFragment fragment = new CaseDetailsReceptionistFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private CallsData callsData;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            callsData = (CallsData) getArguments().getSerializable("callData");
        }
    }
    private FragmentCaseDetailsReceptionistBinding binding;
    private View view;
    private MedicalSystemViewModel medicalSystemViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_case_details_receptionist, container, false);
        binding.setLifecycleOwner(this);
        view = binding.getRoot();
        inti();                                   //Initialize variables
        onCLickOnBackIcon();                      //Set On Click On Back Icon

        if(callsData != null) {
        SetOnClicktoLogoutButtonAndSetImageEnd(); //Set On Click On Logout Button
        }


        return view;
    }

    private void inti() {
        //Initialize medicalSystemViewModel
        medicalSystemViewModel = new ViewModelProvider(requireActivity()).get(MedicalSystemViewModel.class);
        //Set ViewModel for Data Binding
        binding.setViewModel(medicalSystemViewModel);
        //Get Data of Case By Id
        medicalSystemViewModel.getDatafromShowCallsResponse(callsData.getId());
    }

    private void SetOnClicktoLogoutButtonAndSetImageEnd(){

        if(!callsData.getStatus().equals("logout")){
            Drawable img = getActivity().getResources().getDrawable(R.drawable.ic_fi_rr_sign_out);
            binding.logoutCasedetailsReceptionist.setCompoundDrawablesWithIntrinsicBounds(null, null, img, null);
            binding.logoutCasedetailsReceptionist.setPadding(340,0,360,0);
            setOnClickOnButton();             //Set On Click On Logout Button
        }


    }

 private void setOnClickOnButton() {
       binding.logoutCasedetailsReceptionist.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               observeLogoutCallResponse(callsData.getId());  //Observe Response Of Logout Call By Id
           }
       });
    }

    private void observeLogoutCallResponse(int id) {
        medicalSystemViewModel.getDatafromLogOutCallResponse(id);
        medicalSystemViewModel.getSingleLiveEventLogoutCall().observe(getViewLifecycleOwner(),new EventObserver<MessageResponseModel>(new EventHandler<MessageResponseModel>() {
            @Override
            public void onEventUnHandled(MessageResponseModel object) {
                    if(object.getStatus() == 1){
                        Toasty.success(getActivity(), getString(R.string.The_Patient_Logout_Successfully), Toast.LENGTH_SHORT, true).show();
                        Navigation.findNavController(view).popBackStack();
                    }else {
                        Toasty.error(getActivity(), object.getMessage(), Toast.LENGTH_SHORT, true).show();
                    }
            }
        }));
    }

    private void onCLickOnBackIcon(){
        binding.backCasedetailsReceptionist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
            }
        });

    }
}