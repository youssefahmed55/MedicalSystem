package com.example.medicalsystem.nurse;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;


import androidx.databinding.DataBindingUtil;
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

import com.bumptech.glide.Glide;
import com.example.medicalsystem.R;
import com.example.medicalsystem.adapters.MyRecycleAdapterMedical3;
import com.example.medicalsystem.databinding.FragmentCaseDetailsNurseBinding;
import com.example.medicalsystem.pojo.MedicalModel;
import com.example.medicalsystem.pojo.cases.showcase.ShowCaseResponseData;
import com.example.medicalsystem.pojo.measurement.MeasurementRequestModel;
import com.example.medicalsystem.pojo.showprofile.ShowProfileData;
import com.example.medicalsystem.viewmodel.MedicalSystemViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CaseDetailsNurseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CaseDetailsNurseFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CaseDetailsNurseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CaseDetailsNurseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CaseDetailsNurseFragment newInstance(String param1, String param2) {
        CaseDetailsNurseFragment fragment = new CaseDetailsNurseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private ShowCaseResponseData caseResponseData;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            caseResponseData =  (ShowCaseResponseData) getArguments().getSerializable("showCaseById");
        }
    }
    private FragmentCaseDetailsNurseBinding binding;
    private View view;
    private MedicalSystemViewModel medicalSystemViewModel;
    private MyRecycleAdapterMedical3 myRecycleAdapterMedical3;
    private Configuration config;
    private ArrayList<MedicalModel> medicalModelArrayList;
    private NavController navController;
    private final String PROFILE_IMAGE = "https://png.pngtree.com/png-vector/20191027/ourlarge/pngtree-avatar-vector-icon-white-background-png-image_1884971.jpg";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_case_details_nurse, container, false);
        view = binding.getRoot();
        binding.setLifecycleOwner(this);
        inti();                 //Initialize variables
        checkIfRTL();           //Check If RTL
        onClickOnBack();        //Set On Click On Back Icon
        setProfileImage();      //Set Profile Image

        if(caseResponseData != null) {            //If caseResponseData Not Equal Null
            medicalSystemViewModel.getDatafromShowCaseResponse(caseResponseData.getId()); //Get Data Of Case By Id
            onClickOnCase();                      //Set On Click On Case
            onClickOnMedicalMeasurement();        //Set On Click On Medical Measurement
            onClickOnMedicalMeasurementImage();   //Set On Click On Medical Measurement Image
            onClickOnCallDoctorButton();          //Set On Click On Call Doctor Button
            observeMedicalMeasurement();          //Observe Medical Measurement
            if(caseResponseData.getMeasurementNote() != null){ //If Nurse Added Measurement Before
            checkMeasurementsValues(caseResponseData.getBloodPressure(),caseResponseData.getSugarAnalysis(),caseResponseData.getTempreture(),caseResponseData.getFluidBalance(),caseResponseData.getRespiratoryRate(),caseResponseData.getHeartRate());
            }
        }
        return view;
    }

    private void onClickOnCallDoctorButton() {
        binding.calldoctorbuttonCasedetailsNurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                observeNumberFromShowProfile(); //Observe Number Of Doctor
            }
        });
    }

    private void observeNumberFromShowProfile() {
        medicalSystemViewModel.getDataFromShowProfileResponse(caseResponseData.getDocId());
        medicalSystemViewModel.getMutableLiveEventShowProfile().observe(getViewLifecycleOwner(), new Observer<ShowProfileData>() {
            @Override
            public void onChanged(ShowProfileData showProfileData) {
                Intent intentDial = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + showProfileData.getMobile()));
                getActivity().startActivity(intentDial);   //Start Activity To Call Doctor
            }
        });
    }

    private void setProfileImage() {
        Glide.with(this).load(PROFILE_IMAGE).into(binding.profileimageCasedetailsNurse); //Load Image From Link
    }

    private void checkMeasurementsValues(String bloodPressure , String sugarAnalysis , String tempreture , String fluidBalance , String respiratoryRate ,String heartRate) {



              if(bloodPressure != null && !bloodPressure.equals("")){ //If bloodPressure Not Equal Null
                  medicalModelArrayList.add(new MedicalModel("blood pressure",bloodPressure));
              }
              if(sugarAnalysis != null && !sugarAnalysis.trim().equals("")){ //If sugarAnalysis Not Equal Null
                      medicalModelArrayList.add(new MedicalModel("sugar analysis",sugarAnalysis));
              }
              if(tempreture != null && !tempreture.equals("")){              //If tempreture Not Equal Null
                      medicalModelArrayList.add(new MedicalModel("tempreture",tempreture));
              }
              if(fluidBalance != null && !fluidBalance.equals("")){         //If fluidBalance Not Equal Null
                      medicalModelArrayList.add(new MedicalModel("fluid balance",fluidBalance));
              }
              if(respiratoryRate != null && !respiratoryRate.trim().equals("")){ //If respiratoryRate Not Equal Null
                      medicalModelArrayList.add(new MedicalModel("respiratory rate",respiratoryRate));
              }
              if(heartRate != null && !heartRate.equals("")){                     //If heartRate Not Equal Null
                      medicalModelArrayList.add(new MedicalModel("heart rate",heartRate));
              }

              if(medicalModelArrayList.size()>0){    //If Size Of Array > 0
                  setAdapter();                      //Set Adapter
              }






    }

    private void setAdapter() {
        myRecycleAdapterMedical3.setArrayList(medicalModelArrayList);          //Set Array to Adapter
        binding.recycleCasedetailsNurse.setAdapter(myRecycleAdapterMedical3);  //Set Adapter to Recycler
    }

    private void inti() {
        //Initialize navController
        navController = NavHostFragment.findNavController(this);
        //Initialize myRecycleAdapterMedical3
        myRecycleAdapterMedical3 = new MyRecycleAdapterMedical3();
        //Initialize medicalModelArrayList
        medicalModelArrayList = new ArrayList<>();
        //Initialize medicalSystemViewModel
        medicalSystemViewModel = new ViewModelProvider(requireActivity()).get(MedicalSystemViewModel.class);
        //Initialize config
        config = getContext().getResources().getConfiguration();
        //Set ViewModel for Data Binding
        binding.setViewModel(medicalSystemViewModel);
    }

    private void observeMedicalMeasurement() {
        MutableLiveData<MeasurementRequestModel> liveData = navController.getCurrentBackStackEntry()
                .getSavedStateHandle()
                .getLiveData("key");

        liveData.observe(getViewLifecycleOwner(), new Observer<MeasurementRequestModel>() {
            @Override
            public void onChanged(MeasurementRequestModel measurementRequestModel) {
                checkMeasurementsValues(measurementRequestModel.getBloodPressure()
                                       ,measurementRequestModel.getSugarAnalysis()
                                       ,measurementRequestModel.getTempreture()
                                       ,measurementRequestModel.getFluidBalance()
                                       ,measurementRequestModel.getRespiratoryRate()
                                       ,measurementRequestModel.getHeartRate());
                binding.nursenoteCasedetailsNurse.setText(measurementRequestModel.getNote());
            }
        });
    }

    private void checkIfRTL() {


        if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
            binding.linear3medicalmeasurementCasedetailsNurse.setBackgroundResource(R.drawable.layer2);
        }
    }

    private void onClickOnBack() {
        binding.backCasedetailsNurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
            }
        });
    }

    private void onClickOnMedicalMeasurementImage() {
        binding.medicalmeasurementimageCasedetailsNurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(caseResponseData.getMeasurementNote().trim().equals("") && medicalModelArrayList.size() == 0) {
                    if(!caseResponseData.getMedicalRecordNote().equals("")) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("showCase", caseResponseData);
                        Navigation.findNavController(view).navigate(R.id.action_caseDetailsNurseFragment_to_addMeasurementNurseFragment, bundle);
                    }else {
                        Toast.makeText(getActivity(), getString(R.string.The_Doctor_Has_Not_Yet_Written_Requests), Toast.LENGTH_SHORT).show();
                    }
                }else
                    Toast.makeText(getActivity(), getString(R.string.You_Addded_Medical_Measurement_Before), Toast.LENGTH_SHORT).show();
            }
        });

    }


    private void onClickOnCase() {
        binding.caseCasedetailsNurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.linear3medicalmeasurementCasedetailsNurse.setVisibility(View.INVISIBLE);
                binding.linearcaseCasedetailsNurse.setVisibility(View.VISIBLE);

                binding.caseCasedetailsNurse.setBackgroundResource(R.drawable.shape4);
                binding.caseCasedetailsNurse.setTextColor(Color.WHITE);

                binding.medicalmeasurementCasedetailsNurse.setBackgroundResource(R.drawable.shape10);
                binding.medicalmeasurementCasedetailsNurse.setTextColor(Color.BLACK);
            }
        });
    }
    private void onClickOnMedicalMeasurement() {
        binding.medicalmeasurementCasedetailsNurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.linearcaseCasedetailsNurse.setVisibility(View.INVISIBLE);
                binding.linear3medicalmeasurementCasedetailsNurse.setVisibility(View.VISIBLE);

                binding.medicalmeasurementCasedetailsNurse.setBackgroundResource(R.drawable.shape4);
                binding.medicalmeasurementCasedetailsNurse.setTextColor(Color.WHITE);

                binding.caseCasedetailsNurse.setBackgroundResource(R.drawable.shape10);
                binding.caseCasedetailsNurse.setTextColor(Color.BLACK);

            }
        });
    }

}