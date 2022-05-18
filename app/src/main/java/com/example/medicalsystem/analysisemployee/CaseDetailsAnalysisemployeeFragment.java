package com.example.medicalsystem.analysisemployee;


import android.content.res.Configuration;
import android.graphics.Bitmap;

import android.graphics.Color;

import android.os.Bundle;


import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;


import android.provider.MediaStore;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.medicalsystem.R;
import com.example.medicalsystem.databinding.FragmentCaseDetailsAnalysisemployeeBinding;
import com.example.medicalsystem.pojo.cases.showcase.ShowCaseResponseData;
import com.example.medicalsystem.viewmodel.MedicalSystemViewModel;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CaseDetailsAnalysisemployeeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CaseDetailsAnalysisemployeeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CaseDetailsAnalysisemployeeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CaseDetailsAnalysisemployeeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CaseDetailsAnalysisemployeeFragment newInstance(String param1, String param2) {
        CaseDetailsAnalysisemployeeFragment fragment = new CaseDetailsAnalysisemployeeFragment();
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
            caseResponseData = (ShowCaseResponseData) getArguments().getSerializable("showCaseById");
        }
    }

    private FragmentCaseDetailsAnalysisemployeeBinding binding;
    private View view;
    private MedicalSystemViewModel medicalSystemViewModel;
    private Configuration config;
    private NavController navController;
    private final String PROFILE_IMAGE = "https://png.pngtree.com/png-vector/20191027/ourlarge/pngtree-avatar-vector-icon-white-background-png-image_1884971.jpg";
    private static final String TAG = "CaseDetailsAnalysisempl";
    private String url;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_case_details_analysisemployee, container, false);
        view = binding.getRoot();
        binding.setLifecycleOwner(this);
        checkIfRTL();                            //Check If RTL
        inti();                                  //Initialize variables
        if (caseResponseData != null) {          //If caseResponseData Not Equal Null
            medicalSystemViewModel.getDatafromShowCaseResponse(caseResponseData.getId());
            onClickOnCase();                     //Set On Click On Case
            onClickOnMedicalMeasurement();       //Set On Click On Medical Measurement
            onClickOnMedicalMesasurementImage(); //Set On Click On Medical Measurement Image
            onClickOnBack();                     //Set On Click On Back Icon
            observeMedicalRecordImageandNote();  //Observe Medical Record Image And Note
            setProfileImage();                   //Set Profile Image
            onClickOnDownloadImage();            //Set On Click On Download Image
            url = caseResponseData.getImage();
            if (!url.equals("http://api.instant-ss.com/public")) {
                setMedicalRecordImage(caseResponseData.getImage()); //Show Medical Record Image
            }
        }


        return view;
    }

    private void onClickOnDownloadImage() {
        binding.downloadimageCasedetailsAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadMedicalRecordImage(); //Download Medical Record Image

            }
        });
    }

    private void setMedicalRecordImage(String image) {
        Glide.with(this).load(image).into(binding.imagemedicalrecordCasedetailsAnalysis);   //Load Medical Record Image
    }

    private void setProfileImage() {
        Glide.with(this).load(PROFILE_IMAGE).into(binding.profileimageCasedetailsAnalysis); //Load Image From Link
    }


    private void inti() {
        //Initialize navController
        navController = NavHostFragment.findNavController(this);
        //Initialize medicalSystemViewModel
        medicalSystemViewModel = new ViewModelProvider(requireActivity()).get(MedicalSystemViewModel.class);
        //Set ViewModel for Data Binding
        binding.setViewModel(medicalSystemViewModel);
    }

    private void observeMedicalRecordImageandNote() {
        MutableLiveData<String> liveData = navController.getCurrentBackStackEntry()
                .getSavedStateHandle()
                .getLiveData("key");

        liveData.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                String ss[] = s.split("%");
                binding.detailsnoteCasedetailsAnalysis.setText(ss[0]);
                setMedicalRecordImage(ss[1]);
                url = ss[1];
                binding.linearimageCasedetailsAnalysis.setVisibility(View.VISIBLE);
            }
        });
    }

    private void checkIfRTL() {
        config = getContext().getResources().getConfiguration();

        if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
            binding.linear2medicalrecordCasedetailsAnalysis.setBackgroundResource(R.drawable.layer2);
        }
    }

    private void onClickOnBack() {
        binding.backCasedetailsAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
            }
        });
    }

    private void onClickOnMedicalMesasurementImage() {
        binding.medicalrecordimageCasedetailsAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (caseResponseData.getMedicalRecordNote().contains("%") && binding.detailsnoteCasedetailsAnalysis.getText().toString().trim().equals("")) {
                    if (!caseResponseData.getMeasurementNote().trim().equals("")) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("showCase", caseResponseData);
                        Navigation.findNavController(view).navigate(R.id.action_caseDetailsAnalysisemployeeFragment_to_addMedicalRecordAnalaysisemployeeFragment, bundle);
                    } else {
                        Toast.makeText(getActivity(), getString(R.string.Nurse_Must_Send_Medical_Measurment_First), Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(getActivity(), getString(R.string.You_Added_Medical_record_Before), Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private void onClickOnCase() {
        binding.caseCasedetailsAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.linear2medicalrecordCasedetailsAnalysis.setVisibility(View.INVISIBLE);
                binding.linearcaseCasedetailsAnalysis.setVisibility(View.VISIBLE);

                binding.caseCasedetailsAnalysis.setBackgroundResource(R.drawable.shape4);
                binding.caseCasedetailsAnalysis.setTextColor(Color.WHITE);

                binding.medicalrecordCasedetailsAnalysis.setBackgroundResource(R.drawable.shape10);
                binding.medicalrecordCasedetailsAnalysis.setTextColor(Color.BLACK);
            }
        });
    }

    private void onClickOnMedicalMeasurement() {
        binding.medicalrecordCasedetailsAnalysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.linearcaseCasedetailsAnalysis.setVisibility(View.INVISIBLE);
                binding.linear2medicalrecordCasedetailsAnalysis.setVisibility(View.VISIBLE);

                binding.medicalrecordCasedetailsAnalysis.setBackgroundResource(R.drawable.shape4);
                binding.medicalrecordCasedetailsAnalysis.setTextColor(Color.WHITE);

                binding.caseCasedetailsAnalysis.setBackgroundResource(R.drawable.shape10);
                binding.caseCasedetailsAnalysis.setTextColor(Color.BLACK);

            }
        });
    }



    private void DownloadMedicalRecordImage() {
        try {
            binding.imagemedicalrecordCasedetailsAnalysis.setDrawingCacheEnabled(true);
            Bitmap bmap =  binding.imagemedicalrecordCasedetailsAnalysis.getDrawingCache();
            MediaStore.Images.Media.insertImage(getActivity().getContentResolver(),bmap,url + System.currentTimeMillis(),"ImageDesc");
            Toasty.success(getActivity(), getString(R.string.Downloaded_Successfully), Toast.LENGTH_SHORT, true).show();
        }catch (Exception e){
            Toasty.error(getActivity(), getString(R.string.Download_Error) + e.getMessage(), Toast.LENGTH_SHORT, true).show();
        }

    }



}