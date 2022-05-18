package com.example.medicalsystem.nurse;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.medicalsystem.R;
import com.example.medicalsystem.adapters.MyRecycleAdapterCases;
import com.example.medicalsystem.databinding.FragmentCasesNurseBinding;
import com.example.medicalsystem.pojo.cases.CasesResponseData;
import com.example.medicalsystem.pojo.cases.CasesResponseModel;
import com.example.medicalsystem.pojo.cases.showcase.ShowCaseResponseData;
import com.example.medicalsystem.singleliveevent.EventHandler;
import com.example.medicalsystem.singleliveevent.EventObserver;
import com.example.medicalsystem.viewmodel.MedicalSystemViewModel;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CasesNurseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CasesNurseFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CasesNurseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CasesNurseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CasesNurseFragment newInstance(String param1, String param2) {
        CasesNurseFragment fragment = new CasesNurseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
    private FragmentCasesNurseBinding binding;
    private View view;
    private MyRecycleAdapterCases myRecycleAdapterCases;
    private MedicalSystemViewModel medicalSystemViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCasesNurseBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        inti();                      //Initialize variables
        observeCases();              //Observe Cases
        onClickOnBackIcon();         //Set On Click On Back Icon
        onClickOnItemOfRecycler();   //Set On Click On Any Item From Recycler

        return view;
    }
    private void onClickOnBackIcon() {
        binding.backCasessnurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack();  //Back to previous Fragment
            }
        });
    }

    private void observeCaseDetailsResponse(int caseId) {
        medicalSystemViewModel.getDatafromShowCaseResponse(caseId);
        medicalSystemViewModel.getMutableLiveDataShowCase().observe(getViewLifecycleOwner(), new Observer<ShowCaseResponseData>() {
            @Override
            public void onChanged(ShowCaseResponseData showCaseResponseData) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("showCaseById", showCaseResponseData);
                Navigation.findNavController(view).navigate(R.id.action_casesNurseFragment_to_caseDetailsNurseFragment,bundle); //Send Case Data To Case Details Nurse Fragment
            }
        });
    }

    private void onClickOnItemOfRecycler() {
        myRecycleAdapterCases.setOnClickListeners(new MyRecycleAdapterCases.OnmyClickListenerrr() {
            @Override
            public void onclick2(int id) {
                observeCaseDetailsResponse(id); //Observe Response Of Case Data By Id
            }
        });

    }


    private void observeCases() {
        medicalSystemViewModel.getDatafromAllCasesResponse();
        medicalSystemViewModel.getSingleLiveEventAllCases().observe(getViewLifecycleOwner(),new EventObserver<CasesResponseModel>(new EventHandler<CasesResponseModel>() {
            @Override
            public void onEventUnHandled(CasesResponseModel object) {
                if(object.getStatus() == 1){
                    ArrayList<CasesResponseData> arrayList = new ArrayList<>();
                    arrayList.addAll(object.getData());
                    myRecycleAdapterCases.setArrayList(arrayList);                //Set Array To Adapter
                    binding.recycleCasessnurse.setAdapter(myRecycleAdapterCases); //Set Adapter To Recycler
                }else {
                    Toasty.error(getActivity(), object.getMessage(), Toast.LENGTH_SHORT, true).show();
                }
            }
        }));
    }

    private void inti() {
        //Initialize medicalSystemViewModel
        medicalSystemViewModel = new ViewModelProvider(requireActivity()).get(MedicalSystemViewModel.class);
        //Initialize myRecycleAdapterCases
        myRecycleAdapterCases = new MyRecycleAdapterCases();
    }
}