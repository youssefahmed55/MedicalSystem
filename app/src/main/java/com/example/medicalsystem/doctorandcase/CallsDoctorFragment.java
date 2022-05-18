package com.example.medicalsystem.doctorandcase;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.medicalsystem.R;
import com.example.medicalsystem.adapters.MyRecycleAdapterDoctorCalls;
import com.example.medicalsystem.databinding.FragmentCallsDoctorBinding;
import com.example.medicalsystem.pojo.MessageResponseModel;
import com.example.medicalsystem.pojo.calls.callsreceptionist.CallsData;
import com.example.medicalsystem.pojo.calls.callsreceptionist.CallsResponse;
import com.example.medicalsystem.singleliveevent.EventHandler;
import com.example.medicalsystem.singleliveevent.EventObserver;
import com.example.medicalsystem.viewmodel.MedicalSystemViewModel;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CallsDoctorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CallsDoctorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CallsDoctorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CallsDoctorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CallsDoctorFragment newInstance(String param1, String param2) {
        CallsDoctorFragment fragment = new CallsDoctorFragment();
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
    private FragmentCallsDoctorBinding binding;
    private View view;
    private MedicalSystemViewModel medicalSystemViewModel;
    private MyRecycleAdapterDoctorCalls myRecycleAdapterDoctorCalls;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCallsDoctorBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        inti();                                     //Initialize variables
        onClickOnBackIcon();                        //Set On Click On Back Icon
        observeCalls();                             //Observe Calls
        onClickOnAcceptOrBusyButtonOfRecycler();    //Set On Click On Accept Or Busy Button
        observeAcceptorRejectResponse();            //Observe Response Of Accept Or Busy To Call


        return view;
    }

    private void onClickOnAcceptOrBusyButtonOfRecycler() {
        myRecycleAdapterDoctorCalls.setOnmyClickListenerrr(new MyRecycleAdapterDoctorCalls.OnmyClickListenerrr() {
            @Override
            public void onclick2(int id, View v) {
                switch (v.getId()){
                    case R.id.accept_list_callsdoctor:
                        medicalSystemViewModel.getDataFromAcceptOrRejectCallResponse(id,"accept");
                     break;
                    case R.id.busy_list_callsdoctor:
                        medicalSystemViewModel.getDataFromAcceptOrRejectCallResponse(id,"reject");
                        break;
                }

            }
        });
    }

    private void observeAcceptorRejectResponse() {
       medicalSystemViewModel.getSingleLiveEventAcceptorRejectCall().observe(getViewLifecycleOwner(), new EventObserver<MessageResponseModel>(new EventHandler<MessageResponseModel>() {
           @Override
           public void onEventUnHandled(MessageResponseModel object) {
               if(object.getStatus() == 1){
                   Toasty.success(getActivity(), getString(R.string.Done), Toast.LENGTH_SHORT, true).show();
                   medicalSystemViewModel.getDatafromAllCallsResponse();
               }else {
                   Toasty.error(getActivity(), object.getMessage(), Toast.LENGTH_SHORT, true).show();
               }
           }
       }));
    }



    private void observeCalls() {
        medicalSystemViewModel.getDatafromAllCallsResponse();
        medicalSystemViewModel.getSingleLiveEventCalls().observe(getViewLifecycleOwner(),new EventObserver<CallsResponse>(new EventHandler<CallsResponse>() {
            @Override
            public void onEventUnHandled(CallsResponse object) {
                ArrayList<CallsData> arrayList = new ArrayList();
                arrayList.addAll(object.getData());
                myRecycleAdapterDoctorCalls.setArrayList(arrayList);                 //Set Array to Adapter
                binding.recyclecallsdoctor.setAdapter(myRecycleAdapterDoctorCalls);  //Set Adapter to Recycler
                if(arrayList.size() ==0){
                    Toasty.info(getActivity(), getActivity().getString(R.string.Empty_Calls), Toast.LENGTH_SHORT,true).show();
                }
            }
        }));
    }

    private void onClickOnBackIcon() {
        binding.backCallsdoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
            }
        });
    }

    private void inti() {
        //Initialize myRecycleAdapterDoctorCalls
        myRecycleAdapterDoctorCalls = new MyRecycleAdapterDoctorCalls();
        //Initialize medicalSystemViewModel
        medicalSystemViewModel = new ViewModelProvider(requireActivity()).get(MedicalSystemViewModel.class);
    }
}