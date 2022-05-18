package com.example.medicalsystem.manager;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.medicalsystem.R;
import com.example.medicalsystem.adapters.MyRecycleAdapterMedical3;
import com.example.medicalsystem.databinding.FragmentCaseDetailsManagerBinding;
import com.example.medicalsystem.pojo.MedicalModel;
import com.example.medicalsystem.pojo.cases.showcase.ShowCaseResponseData;
import com.example.medicalsystem.viewmodel.MedicalSystemViewModel;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CaseDetailsManagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CaseDetailsManagerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CaseDetailsManagerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CaseDetailsManagerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CaseDetailsManagerFragment newInstance(String param1, String param2) {
        CaseDetailsManagerFragment fragment = new CaseDetailsManagerFragment();
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
    private FragmentCaseDetailsManagerBinding binding;
    private View view;
    private Configuration config;
    private MedicalSystemViewModel medicalSystemViewModel;
    private ArrayList<MedicalModel> medicalModelArrayList;
    private MyRecycleAdapterMedical3 myRecycleAdapterMedical3;
    private String url;
    private final String PROFILE_IMAGE = "https://png.pngtree.com/png-vector/20191027/ourlarge/pngtree-avatar-vector-icon-white-background-png-image_1884971.jpg";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_case_details_manager, container, false);
        view = binding.getRoot();
        binding.setLifecycleOwner(this);

        checkIfRTL();                      //Check If RTL
        inti();                            //Initialize variables
        onClickOnBackIcon();               //Set On Click On Back Icon
        setProfileImage();                 //Set Profile Image

        if(caseResponseData != null) {
            medicalSystemViewModel.getDatafromShowCaseResponse(caseResponseData.getId()); //Get Case Details By Id
            onClickOnMedicalRecord();                          //Set On Click On Medical Record
            onClickOnCase();                                   //Set On Click On Case
            onClickOnMedicalMeasurement();                     //Set On Click On Medical Measurement
            onClickOnDownloadImage();                          //Set On Click On Download Image
            if(caseResponseData.getMeasurementNote() != null){
                //Show Measurement Note And Medical Measurements
                checkMeasurementsValues(caseResponseData.getBloodPressure(),caseResponseData.getSugarAnalysis(),caseResponseData.getTempreture(),caseResponseData.getFluidBalance(),caseResponseData.getRespiratoryRate(),caseResponseData.getHeartRate());
            }
            url = caseResponseData.getImage();
            if (!url.equals("http://api.instant-ss.com/public")) {
                setMedicalRecordImage(url);
            }
        }


        return view;
    }
    private void setProfileImage() {
        Glide.with(this).load(PROFILE_IMAGE).into(binding.profileimageCasedetailsManager); //Load Image From Link
        Glide.with(this).load(PROFILE_IMAGE).into(binding.profileimage2CasedetailsManager);//Load Image From Link
    }

    private void onClickOnDownloadImage() {
        binding.downloadimageCasedetailsManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadMedicalRecordImage();     //Download Medical Record Image

            }
        });
    }

    private void DownloadMedicalRecordImage() {
        try {
            binding.imagemedicalrecordCasedetailsManager.setDrawingCacheEnabled(true);
            Bitmap bmap =  binding.imagemedicalrecordCasedetailsManager.getDrawingCache();
            MediaStore.Images.Media.insertImage(getActivity().getContentResolver(),bmap,url + System.currentTimeMillis(),"ImageDesc");
            Toasty.success(getActivity(), getString(R.string.Downloaded_Successfully), Toast.LENGTH_SHORT, true).show();
        }catch (Exception e){
            Toasty.error(getActivity(), getString(R.string.Download_Error) + e.getMessage(), Toast.LENGTH_SHORT, true).show();
        }

    }
    private void setMedicalRecordImage(String image) {
        Glide.with(this).load(image).into(binding.imagemedicalrecordCasedetailsManager); //Load Medical Record Image
    }
    private void checkMeasurementsValues(String bloodPressure , String sugarAnalysis , String tempreture , String fluidBalance , String respiratoryRate ,String heartRate) {



        if(bloodPressure != null && !bloodPressure.equals("")){ //If bloodPressure Not Equal Null
            medicalModelArrayList.add(new MedicalModel("blood pressure",bloodPressure));
        }
        if(sugarAnalysis != null && !sugarAnalysis.trim().equals("")){ //If sugarAnalysis Not Equal Null
            medicalModelArrayList.add(new MedicalModel("sugar analysis",sugarAnalysis));
        }
        if(tempreture != null && !tempreture.equals("")){    //If tempreture Not Equal Null
            medicalModelArrayList.add(new MedicalModel("tempreture",tempreture));
        }
        if(fluidBalance != null && !fluidBalance.equals("")){  //If fluidBalance Not Equal Null
            medicalModelArrayList.add(new MedicalModel("fluid balance",fluidBalance));
        }
        if(respiratoryRate != null && !respiratoryRate.trim().equals("")){ //If respiratoryRate Not Equal Null
            medicalModelArrayList.add(new MedicalModel("respiratory rate",respiratoryRate));
        }
        if(heartRate != null && !heartRate.equals("")){ //If heartRate Not Equal Null
            medicalModelArrayList.add(new MedicalModel("heart rate",heartRate));
        }

        if(medicalModelArrayList.size()>0){
            setAdapter();           //Set Adapter
        }

    }
    private void setAdapter() {
        myRecycleAdapterMedical3.setArrayList(medicalModelArrayList);          //Set Array to Adapter
        binding.recycleCasedetailsManager.setAdapter(myRecycleAdapterMedical3);//Set Adapter to Recycler
    }

    private void inti() {
        //Initialize medicalSystemViewModel
        medicalSystemViewModel = new ViewModelProvider(requireActivity()).get(MedicalSystemViewModel.class);
        //Set ViewModel for Data Binding
        binding.setViewModel(medicalSystemViewModel);
        //Initialize myRecycleAdapterMedical3
        myRecycleAdapterMedical3 = new MyRecycleAdapterMedical3();
        //Initialize medicalModelArrayList
        medicalModelArrayList = new ArrayList<>();
    }

    private void onClickOnMedicalMeasurement() {
        binding.medicalmeasurementCasedetailsManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.linear2medicalrecordCasedetailsManager.setVisibility(View.INVISIBLE);
                binding.linearcaseCasedetailsManager.setVisibility(View.INVISIBLE);
                binding.linear3medicalmeasurementCasedetailsManager.setVisibility(View.VISIBLE);

                binding.medicalmeasurementCasedetailsManager.setBackgroundResource(R.drawable.shape4);
                binding.medicalmeasurementCasedetailsManager.setTextColor(Color.WHITE);

                binding.caseCasedetailsManager.setBackgroundResource(R.drawable.shape10);
                binding.caseCasedetailsManager.setTextColor(Color.BLACK);

                binding.medicalrecordCasedetailsManager.setBackgroundResource(R.drawable.shape10);
                binding.medicalrecordCasedetailsManager.setTextColor(Color.BLACK);
            }
        });
    }

    private void onClickOnCase() {
        binding.caseCasedetailsManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.linear2medicalrecordCasedetailsManager.setVisibility(View.INVISIBLE);
                binding.linear3medicalmeasurementCasedetailsManager.setVisibility(View.INVISIBLE);
                binding.linearcaseCasedetailsManager.setVisibility(View.VISIBLE);

                binding.caseCasedetailsManager.setBackgroundResource(R.drawable.shape4);
                binding.caseCasedetailsManager.setTextColor(Color.WHITE);

                binding.medicalmeasurementCasedetailsManager.setBackgroundResource(R.drawable.shape10);
                binding.medicalmeasurementCasedetailsManager.setTextColor(Color.BLACK);

                binding.medicalrecordCasedetailsManager.setBackgroundResource(R.drawable.shape10);
                binding.medicalrecordCasedetailsManager.setTextColor(Color.BLACK);
            }
        });
    }

    private void onClickOnMedicalRecord() {

        binding.medicalrecordCasedetailsManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.linearcaseCasedetailsManager.setVisibility(View.INVISIBLE);
                binding.linear2medicalrecordCasedetailsManager.setVisibility(View.VISIBLE);
                binding.linear3medicalmeasurementCasedetailsManager.setVisibility(View.INVISIBLE);

                binding.caseCasedetailsManager.setBackgroundResource(R.drawable.shape10);
                binding.caseCasedetailsManager.setTextColor(Color.BLACK);

                binding.medicalmeasurementCasedetailsManager.setBackgroundResource(R.drawable.shape10);
                binding.medicalmeasurementCasedetailsManager.setTextColor(Color.BLACK);

                binding.medicalrecordCasedetailsManager.setBackgroundResource(R.drawable.shape4);
                binding.medicalrecordCasedetailsManager.setTextColor(Color.WHITE);
            }
        });
    }

    private void onClickOnBackIcon() {
        binding.backCasedetailsManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
            }
        });
    }

    private void checkIfRTL() {
        config = getContext().getResources().getConfiguration();

        if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
            binding.linear2medicalrecordCasedetailsManager.setBackgroundResource(R.drawable.layer2);
            binding.linear3medicalmeasurementCasedetailsManager.setBackgroundResource(R.drawable.layer2);
        }
    }
}