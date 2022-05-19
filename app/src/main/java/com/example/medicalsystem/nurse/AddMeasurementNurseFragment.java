package com.example.medicalsystem.nurse;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProvider;

import androidx.navigation.Navigation;



import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.medicalsystem.R;
import com.example.medicalsystem.adapters.MyRecycleAdapterAddMedicalValue;
import com.example.medicalsystem.adapters.MyRecycleAdapterMedical2;
import com.example.medicalsystem.databinding.FragmentAddMeasurementNurseBinding;
import com.example.medicalsystem.pojo.MedicalModel;
import com.example.medicalsystem.pojo.MessageResponseModel;
import com.example.medicalsystem.pojo.cases.showcase.ShowCaseResponseData;
import com.example.medicalsystem.pojo.measurement.MeasurementRequestModel;
import com.example.medicalsystem.singleliveevent.EventHandler;
import com.example.medicalsystem.singleliveevent.EventObserver;
import com.example.medicalsystem.viewmodel.MedicalSystemViewModel;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddMeasurementNurseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddMeasurementNurseFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddMeasurementNurseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddMeasurementNurseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddMeasurementNurseFragment newInstance(String param1, String param2) {
        AddMeasurementNurseFragment fragment = new AddMeasurementNurseFragment();
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
            caseResponseData =  (ShowCaseResponseData) getArguments().getSerializable("showCase");
        }
    }
    private FragmentAddMeasurementNurseBinding binding;
    private View view;
    private ArrayList<MedicalModel> arrayList;
    private MyRecycleAdapterMedical2 myRecycleAdapterMedical2;
    private MyRecycleAdapterAddMedicalValue myRecycleAdapterAddMedicalValue;
    private Configuration config;
    private MedicalSystemViewModel medicalSystemViewModel;
    private String blood_pressure,sugar_analysis,tempreture,fluid_balance,respiratory_rate,heart_rate;
    private final String PROFILE_IMAGE = "https://png.pngtree.com/png-vector/20191027/ourlarge/pngtree-avatar-vector-icon-white-background-png-image_1884971.jpg";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_measurement_nurse, container, false);
        view = binding.getRoot();
        binding.setLifecycleOwner(this);
        inti();                            //Initialize variables
        checkIfRTL();                      //Check If RTL
        onClickOnBackIcon();               //Set On Click On Back Icon
        setProfileImage();                 //Set Profile Image


        if(caseResponseData != null){
            medicalSystemViewModel.getDatafromShowCaseResponse(caseResponseData.getId()); //Get Data Of Case By Id
            getMedicalRequirements();              //Get Medical Requirements
            setMedicalAdapter();                   //Set Medical Adapter
            setAddMedicalValueAdapter();           //Set Add Medical Value Adapter
            setOnTextChangeInValues();             //Set On Text Change
            setOnCLickOnAddMeasurementButton();    //Set On Click On Add Measurement Button
        }



        return view;
    }

    private void setProfileImage() {
        Glide.with(this).load(PROFILE_IMAGE).into(binding.profileimageAddmeasurementNurse); //Load Image From Link
    }


    private void setOnCLickOnAddMeasurementButton() {
        binding.AddmeasurementbuttonAddmeasurementNurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!binding.addnoteAddmeasurementNurse.getText().toString().equals("")){
                    boolean isAllDone = true;

                    for (MedicalModel m : arrayList){
                         if(m.getValue().trim().equals("")){
                             isAllDone = false;
                             break;
                         }
                    }

                    if(isAllDone){
                        for (MedicalModel m : arrayList){
                            savevalues(m);
                        }
                        //Observe Send Measurement Response
                        observeSendMeasurementResponse(new MeasurementRequestModel(caseResponseData.getId()
                                                                                   ,blood_pressure
                                                                                   ,sugar_analysis
                                                                                   ,binding.addnoteAddmeasurementNurse.getText().toString().trim()
                                                                                   ,"Attendance"
                                                                                   ,fluid_balance
                                                                                   ,respiratory_rate
                                                                                   ,heart_rate
                                                                                   ,tempreture));
                    }else
                        Toast.makeText(getActivity(), getString(R.string.Please_Write_All_Measurements_Values), Toast.LENGTH_SHORT).show();

                }else {
                    Toast.makeText(getActivity(), getString(R.string.Note_Is_Required), Toast.LENGTH_SHORT).show();
                }




            }

        });
    }

    private void observeSendMeasurementResponse(MeasurementRequestModel measurementRequestModel) {
        medicalSystemViewModel.getDataFromSendMeasurementResponse(measurementRequestModel);
        medicalSystemViewModel.getSingleLiveEventSendMeasurement().observe(getViewLifecycleOwner(),new EventObserver<MessageResponseModel>(new EventHandler<MessageResponseModel>() {
            @Override
            public void onEventUnHandled(MessageResponseModel object) {
                if(object.getStatus() == 1){
                    Toasty.success(getActivity(), getString(R.string.Success), Toast.LENGTH_SHORT, true).show();
                    Navigation.findNavController(view).getPreviousBackStackEntry().getSavedStateHandle().set("key",measurementRequestModel); //Save Data To Previous Fragment
                    Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
                }else{
                    Toasty.error(getActivity(), object.getMessage(), Toast.LENGTH_SHORT, true).show();
                }
            }
        }));
    }


    private void savevalues(MedicalModel m) {

        switch (m.getMedicalName()){
            case "blood pressure" : blood_pressure = m.getValue(); break;
            case "sugar analysis" : sugar_analysis = m.getValue(); break;
            case "tempreture" : tempreture = m.getValue();         break;
            case "fluid balance" : fluid_balance = m.getValue();   break;
            case "respiratory rate" : respiratory_rate = m.getValue(); break;
            case "heart rate" : heart_rate = m.getValue();         break;

        }
    }

    private void setOnTextChangeInValues() {
        myRecycleAdapterAddMedicalValue.setOntextchangeListeners(new MyRecycleAdapterAddMedicalValue.OntextchangeListenerrr() {
            @Override
            public void onchange(int pos, String s) {
                arrayList.get(pos).setValue(s);
            }
        });
    }

    private void setAddMedicalValueAdapter() {
        myRecycleAdapterAddMedicalValue.setArrayList(arrayList);                        //Set Array to Adapter
        binding.recycle2AddmeasurementNurse.setAdapter(myRecycleAdapterAddMedicalValue);//Set Adapter to Recycler
    }

    private void setMedicalAdapter() {
        myRecycleAdapterMedical2.setArrayList(arrayList);                       //Set Array to Adapter
        binding.recycleAddmeasurementNurse.setAdapter(myRecycleAdapterMedical2);//Set Adapter to Recycler
    }

    private void getMedicalRequirements() {
        String[] s = caseResponseData.getMedicalRecordNote().split("%");

        if(s.length == 3){
            String[] ss = s[1].split("-");
            for(int j = 0 ; j < ss.length ; j++){
                arrayList.add(new MedicalModel(ss[j],""));
            }

            binding.detailsnoteAddmeasurementNurse.setText(s[2]);

        }

    }

    private void onClickOnBackIcon() {
        binding.backAddmeasurementNurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
            }
        });
    }

    private void checkIfRTL() {
        if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
            binding.linear1AddmeasurementNurse.setBackgroundResource(R.drawable.layer2);
        }
    }

    private void inti() {
        //Initialize medicalSystemViewModel
        medicalSystemViewModel = new ViewModelProvider(requireActivity()).get(MedicalSystemViewModel.class);
        //Initialize config
        config = getContext().getResources().getConfiguration();
        //Set ViewModel for Data Binding
        binding.setViewModel(medicalSystemViewModel);
        //Initialize arrayList
        arrayList = new ArrayList<>();
        //Initialize myRecycleAdapterMedical2
        myRecycleAdapterMedical2 = new MyRecycleAdapterMedical2();
        //Initialize myRecycleAdapterAddMedicalValue
        myRecycleAdapterAddMedicalValue= new MyRecycleAdapterAddMedicalValue();
    }
}