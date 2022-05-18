package com.example.medicalsystem.receptionist.calls;


import android.content.res.Configuration;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.medicalsystem.R;
import com.example.medicalsystem.databinding.FragmentCreateCallBinding;
import com.example.medicalsystem.pojo.HospitalEmployeeModle;
import com.example.medicalsystem.pojo.calls.createcall.CreateCallRequestModel;
import com.example.medicalsystem.pojo.MessageResponseModel;
import com.example.medicalsystem.singleliveevent.EventHandler;
import com.example.medicalsystem.singleliveevent.EventObserver;
import com.example.medicalsystem.viewmodel.MedicalSystemViewModel;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateCallFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateCallFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreateCallFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateCallFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateCallFragment newInstance(String param1, String param2) {
        CreateCallFragment fragment = new CreateCallFragment();
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
    private FragmentCreateCallBinding binding;
    private View view;
    private MedicalSystemViewModel medicalSystemViewModel;
    private HospitalEmployeeModle hospitalEmployeeModlee;
    private Configuration config;
    private static final String TAG = "CreateCallFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateCallBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        inti();                    //Initialize variables
        checkIfRTL();              //Check If RTL
        observerSelectedDoctor();  //Observe Selected Doctor
        onClickOnBackIcon();       //Set On Click On Back Icon
        onClickOnSelectDoctor();   //Set On Click On Select Doctor
        OnClickOnSendCallButton(); //Set On Click On Send Call Button



        return view;
    }

    private void checkIfRTL() {
        if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
            binding.arrowCreateCall.setImageResource(R.drawable.ic_baseline_arrow_right_24);
        }
    }

    private void inti() {
        //Initialize medicalSystemViewModel
        medicalSystemViewModel = new ViewModelProvider(requireActivity()).get(MedicalSystemViewModel.class);
        //Initialize config
        config = getContext().getResources().getConfiguration();
    }

    private void OnClickOnSendCallButton() {
        binding.sendcallbuttonCreatecall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkFields(); //Check Fields That Needed To Create Call

            }
        });
    }

    private void checkFields() {
        if(binding.PatientNameCreateCall.getText().toString().trim().equals("")){ //If Patient Name Not Equal Null
            binding.PatientNameCreateCall.setError(getString(R.string.Patient_Name_is_Required));
            return;
        }
        if(binding.ageCreateCall.getText().toString().trim().equals("")){         //If age Not Equal Null
            binding.ageCreateCall.setError(getString(R.string.Age_is_Required));
            return;
        }

        if(binding.PhoneNumberCreateCall.getText().toString().trim().equals("")){ //If Phone Number Not Equal Null
            binding.PhoneNumberCreateCall.setError(getString(R.string.Phone_Number_is_Required));
            return;
        }
        if(hospitalEmployeeModlee == null){           //If Doctor Not Selected yet
            Toasty.error(getActivity(), getString(R.string.Select_Doctor_is_Required), Toast.LENGTH_SHORT, true).show();
            return;
        }
        if(binding.CaseDescriptionCreateCall.getText().toString().trim().equals("")){  //If Case Description Not Equal Null
            binding.CaseDescriptionCreateCall.setError(getString(R.string.Case_Description_is_Required));
            return;
        }

        //Observe Response of Create Call
        observeCreateCallResponse(new CreateCallRequestModel(binding.PatientNameCreateCall.getText().toString().trim()
                                      ,String.valueOf(hospitalEmployeeModlee.getUserItem().getId())
                                      ,binding.ageCreateCall.getText().toString().trim()
                                      ,binding.PhoneNumberCreateCall.getText().toString().trim()
                                      ,binding.CaseDescriptionCreateCall.getText().toString().trim()));

    }

    private void observeCreateCallResponse(CreateCallRequestModel createCallRequestModel ) {
             medicalSystemViewModel.getDatafromCreateCallsResponse(createCallRequestModel);
             medicalSystemViewModel.getSingleLiveEventCreateCall().observe(getViewLifecycleOwner(), new EventObserver<MessageResponseModel>(new EventHandler<MessageResponseModel>() {
                 @Override
                 public void onEventUnHandled(MessageResponseModel object) {
                              if(object.getStatus() == 1){
                                  Toasty.success(getActivity(), getActivity().getString(R.string.Call_Created_Successfully), Toast.LENGTH_SHORT, true).show();
                                  Navigation.findNavController(view).popBackStack();  //Back to previous Fragment
                              }else {
                                  Toasty.error(getActivity(), object.getMessage(), Toast.LENGTH_SHORT, true).show();
                              }
                 }
             }));
    }

    private void onClickOnSelectDoctor() {
        binding.SelectDoctorCreateCall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_createCallFragment_to_selectDoctorFragment);
            }
        });
    }

    private void onClickOnBackIcon() {
        binding.backCreatecalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack();  //Back to previous Fragment
            }
        });
    }

    private void observerSelectedDoctor() {
        NavController navController = NavHostFragment.findNavController(this);
        MutableLiveData<HospitalEmployeeModle> liveData = navController.getCurrentBackStackEntry()
                .getSavedStateHandle()
                .getLiveData("key");

        liveData.observe(getViewLifecycleOwner(), new Observer<HospitalEmployeeModle>() {
            @Override
            public void onChanged(HospitalEmployeeModle hospitalEmployeeModle) {
                hospitalEmployeeModlee = hospitalEmployeeModle;      //Save Doctor That Selected in hospitalEmployeeModlee
                binding.SelectDoctorCreateCall.setText(hospitalEmployeeModlee.getUserItem().getFirstName()); //Show Name Of Him In EditText
            }
        });
    }
}