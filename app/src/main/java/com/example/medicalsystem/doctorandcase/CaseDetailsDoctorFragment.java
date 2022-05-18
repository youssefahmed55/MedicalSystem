package com.example.medicalsystem.doctorandcase;

import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.medicalsystem.customdialog.CustomDialogClass2;
import com.example.medicalsystem.R;
import com.example.medicalsystem.adapters.MyRecycleAdapterMedical3;
import com.example.medicalsystem.databinding.FragmentCaseDetailsDoctorBinding;
import com.example.medicalsystem.pojo.HospitalEmployeeModle;
import com.example.medicalsystem.pojo.MedicalModel;
import com.example.medicalsystem.pojo.MessageResponseModel;
import com.example.medicalsystem.pojo.cases.showcase.ShowCaseResponseData;
import com.example.medicalsystem.pojo.request.AddRequestModel;
import com.example.medicalsystem.singleliveevent.EventHandler;
import com.example.medicalsystem.singleliveevent.EventObserver;
import com.example.medicalsystem.viewmodel.MedicalSystemViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CaseDetailsDoctorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CaseDetailsDoctorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CaseDetailsDoctorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CaseDetailsDoctorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CaseDetailsDoctorFragment newInstance(String param1, String param2) {
        CaseDetailsDoctorFragment fragment = new CaseDetailsDoctorFragment();
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
    private FragmentCaseDetailsDoctorBinding binding;
    private View view;
    private View sheetview;
    private BottomSheetDialog bottomSheetDialog;
    private boolean isonmedicalrecord,isonmedicalmeasurement;
    private ImageView imageView , imageView2;
    private TextView textView , textView2;
    private LinearLayout linearLayout ,linearLayout2;
    private Button requestButton , sendButton;
    private EditText editTextNote , editTextAnlaysis;
    private Configuration config;
    private MedicalSystemViewModel medicalSystemViewModel;
    private HospitalEmployeeModle hospitalEmployeeModlee;
    private NavController navController;
    private String medicalRecordRequests , medicalMeasurementRequests;
    private boolean requestIsAddded ;
    private static final String TAG = "CaseDetailsDoctorFragme";
    private MyRecycleAdapterMedical3 myRecycleAdapterMedical3;
    private ArrayList<MedicalModel> medicalModelArrayList;
    private String url;
    private final String PROFILE_IMAGE = "https://png.pngtree.com/png-vector/20191027/ourlarge/pngtree-avatar-vector-icon-white-background-png-image_1884971.jpg";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_case_details_doctor, container, false);
        view = binding.getRoot();
        binding.setLifecycleOwner(this);
        checkIfRTL();                               //Check If RTL
        inti();                                     //Initialize variables
        onClickOnBackIcon();                        //Set On Click On Back Icon
        setProfileImage();                          //Set Profile Image
        if(caseResponseData != null) {              //If caseResponseData Not Equal Null
            medicalSystemViewModel.getDatafromShowCaseResponse(caseResponseData.getId());  //Get Data Of Case By Id
            observerSelectedNurse();                //Observe Selected Nurse
            observeSelectedAnalysis();              //Observe Selected Analysis
            observeMedicalRecordRequests();         //Observe Medical Record Requests
            observeMedicalMeasurementRequests();    //Observe Medical Measurement Requests
            onClickOnMedicalRecord();               //Set On Click On Medical Record
            onClickOnCase();                        //Set On Click On Case
            onClickOnMedicalMeasurement();          //Set On Click On Medical Measurement
            onClickOnAddNurseButton();              //Set On Click On Add Nurse Button
            onClickOnEndCaseButton();               //Set On Click On End Case Button
            onClickOnRequestButton();               //Set On Click On Requests Button
            onClickOnDownloadImage();               //Set On Click On Download Image

            if(caseResponseData.getMeasurementNote() != null){ //If Measurement Note Not Equal Null
                //Show Measurement Note And Medical Measurements
                checkMeasurementsValues(caseResponseData.getBloodPressure(),caseResponseData.getSugarAnalysis(),caseResponseData.getTempreture(),caseResponseData.getFluidBalance(),caseResponseData.getRespiratoryRate(),caseResponseData.getHeartRate());
            }
            url = caseResponseData.getImage();
            if (!url.equals("http://api.instant-ss.com/public")) {
                setMedicalRecordImage(url);    //Show Medical Record Image
            }

        }

        return view;
    }
    private void setMedicalRecordImage(String image) {
        Glide.with(this).load(image).into(binding.imagemedicalrecordCasedetailsDoctor);    //Load Medical Record Image
    }
    private void setProfileImage() {
        Glide.with(this).load(PROFILE_IMAGE).into(binding.profileimageCasedetailsDoctor);  //Load Image From Link
        Glide.with(this).load(PROFILE_IMAGE).into(binding.profileimage2CasedetailsDoctor); //Load Image From Link
    }
    private void checkMeasurementsValues(String bloodPressure , String sugarAnalysis , String tempreture , String fluidBalance , String respiratoryRate ,String heartRate) {



        if(bloodPressure != null && !bloodPressure.equals("")){ //If Blood Pressure Not Equal Null
            medicalModelArrayList.add(new MedicalModel("blood pressure",bloodPressure));
        }
        if(sugarAnalysis != null && !sugarAnalysis.trim().equals("")){ //If Sugar Analysis Not Equal Null
            medicalModelArrayList.add(new MedicalModel("sugar analysis",sugarAnalysis));
        }
        if(tempreture != null && !tempreture.equals("")){ //If tempreture Not Equal Null
            medicalModelArrayList.add(new MedicalModel("tempreture",tempreture));
        }
        if(fluidBalance != null && !fluidBalance.equals("")){ //If fluidBalance Not Equal Null
            medicalModelArrayList.add(new MedicalModel("fluid balance",fluidBalance));
        }
        if(respiratoryRate != null && !respiratoryRate.trim().equals("")){ //If Respiratory Rate Not Equal Null
            medicalModelArrayList.add(new MedicalModel("respiratory rate",respiratoryRate));
        }
        if(heartRate != null && !heartRate.equals("")){ //If HeartRate Not Equal Null
            medicalModelArrayList.add(new MedicalModel("heart rate",heartRate));
        }

        if(medicalModelArrayList.size()>0){
            setAdapter();             //Set Adapter
        }

    }
    private void setAdapter() {
        myRecycleAdapterMedical3.setArrayList(medicalModelArrayList);          //Set Array to Adapter
        binding.recycleCasedetailsDoctor.setAdapter(myRecycleAdapterMedical3); //Set Adapter to Recycler
    }

    private void onClickOnDownloadImage() {
        binding.downloadimageCasedetailsDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DownloadMedicalRecordImage();  //Download Medical Record Image

            }
        });
    }
    private void DownloadMedicalRecordImage() {
        try {
            binding.imagemedicalrecordCasedetailsDoctor.setDrawingCacheEnabled(true);
            Bitmap bmap =  binding.imagemedicalrecordCasedetailsDoctor.getDrawingCache();
            MediaStore.Images.Media.insertImage(getActivity().getContentResolver(),bmap,url + System.currentTimeMillis(),"ImageDesc");
            Toasty.success(getActivity(), getString(R.string.Downloaded_Successfully), Toast.LENGTH_SHORT, true).show();
        }catch (Exception e){
            Toasty.error(getActivity(), getString(R.string.Download_Error) + e.getMessage(), Toast.LENGTH_SHORT, true).show();
        }

    }


    private void observeMedicalMeasurementRequests() {
        MutableLiveData<String> liveData = navController.getCurrentBackStackEntry()
                .getSavedStateHandle()
                .getLiveData("keyMeasurementRequests");

        liveData.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                medicalMeasurementRequests = s ;
                showBottomSheet();    //Show Bottom Sheet
            }
        });
    }

    private void observeMedicalRecordRequests() {
        MutableLiveData<String> liveData = navController.getCurrentBackStackEntry()
                .getSavedStateHandle()
                .getLiveData("keyRecoredRequests");

        liveData.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                medicalRecordRequests = s ;
                showBottomSheet();           //Show Bottom Sheet
            }
        });
    }

    private void observeSelectedAnalysis() {
        MutableLiveData<HospitalEmployeeModle> liveData = navController.getCurrentBackStackEntry()
                .getSavedStateHandle()
                .getLiveData("keyAnalysis");

        liveData.observe(getViewLifecycleOwner(), new Observer<HospitalEmployeeModle>() {
            @Override
            public void onChanged(HospitalEmployeeModle hospitalEmployeeModle) {
                hospitalEmployeeModlee = hospitalEmployeeModle;
                editTextAnlaysis.setText(hospitalEmployeeModlee.getUserItem().getFirstName());
                showBottomSheet();            //Show Bottom Sheet
            }
        });
    }

    private void observerSelectedNurse() {
        MutableLiveData<String> liveData = navController.getCurrentBackStackEntry()
                .getSavedStateHandle()
                .getLiveData("key");

        liveData.observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.nursenameCasedetailsDoctor.setText(s);
                binding.name2CasedetailsDoctor.setText(s);
                caseResponseData.setNurseId(s);
            }
        });
    }

    private void onClickOnRequestButton() {
        binding.requestbuttonCasedetailsDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    if(!requestIsAddded && caseResponseData.getMedicalRecordNote().equals("")) {
                        showBottomSheet();    //Show Bottom Sheet

                    }else {
                        Toast.makeText(getActivity(),getString(R.string.You_Added_Request_Before), Toast.LENGTH_SHORT).show();
                    }
            }
        });
    }

    private void showBottomSheet() {
        isonmedicalmeasurement =false;
        isonmedicalrecord = false;

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isonmedicalmeasurement){
                    imageView2.setImageResource(R.drawable.ic_fi_rr_dashboard);
                    textView2.setTextColor(Color.parseColor("#B5B5B5"));
                    linearLayout2.setBackgroundResource(R.drawable.shape16);
                    isonmedicalmeasurement = false;
                }


                imageView.setImageResource(R.drawable.ic_fi_rr_treatment2);
                textView.setTextColor(Color.parseColor("#22C7B8"));
                linearLayout.setBackgroundResource(R.drawable.shape17);
                isonmedicalrecord = true;
            }
        });

        linearLayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isonmedicalrecord){
                    imageView.setImageResource(R.drawable.ic_fi_rr_treatment);
                    textView.setTextColor(Color.parseColor("#B5B5B5"));
                    linearLayout.setBackgroundResource(R.drawable.shape16);
                    isonmedicalrecord = false;
                }



                imageView2.setImageResource(R.drawable.ic_fi_rr_dashboard2);
                textView2.setTextColor(Color.parseColor("#22C7B8"));
                linearLayout2.setBackgroundResource(R.drawable.shape17);
                isonmedicalmeasurement = true;

            }
        });


        requestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(isonmedicalrecord) {
                    if(medicalRecordRequests != null){
                        Bundle bundle = new Bundle();
                        bundle.putString("recordRequests", medicalRecordRequests);
                        Navigation.findNavController(view).navigate(R.id.action_caseDetailsDoctorFragment_to_medicalrecordrequestFragment,bundle);
                    }else {
                        Navigation.findNavController(view).navigate(R.id.action_caseDetailsDoctorFragment_to_medicalrecordrequestFragment);
                    }
                    bottomSheetDialog.dismiss();   //Hide Bottom Sheet Dialog
                }
                if(isonmedicalmeasurement) {
                    if(medicalMeasurementRequests != null){
                        Bundle bundle = new Bundle();
                        bundle.putString("MeasurementRequests", medicalMeasurementRequests);
                        Navigation.findNavController(view).navigate(R.id.action_caseDetailsDoctorFragment_to_medicalmeasurementrequestFragment,bundle);
                    }else {
                        Navigation.findNavController(view).navigate(R.id.action_caseDetailsDoctorFragment_to_medicalmeasurementrequestFragment);
                    }
                    bottomSheetDialog.dismiss(); //Hide Bottom Sheet Dialog

                }
            }
        });

        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                checkTheRequirements();


            }
        });

        editTextAnlaysis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bottomSheetDialog.dismiss();        //Hide Bottom Sheet Dialog
                Navigation.findNavController(view).navigate(R.id.action_caseDetailsDoctorFragment_to_selectAnalysisEmployeeFragment);
            }
        });

        bottomSheetDialog.setContentView(sheetview); //Set Content View To Bottom Sheet Dialog
        bottomSheetDialog.show();                    //Show Bottom Sheet Dialog
    }

    private void checkTheRequirements() {
          if(medicalRecordRequests == null || medicalRecordRequests.isEmpty()){ //If medicalRecordRequests Is Equal Null
              Toast.makeText(getActivity(), getString(R.string.Medical_Record_Required), Toast.LENGTH_SHORT).show();
              return;
          }
        if(medicalMeasurementRequests == null || medicalMeasurementRequests.isEmpty()){ //If medicalMeasurementRequests Is Equal Null
            Toast.makeText(getActivity(), getString(R.string.Medical_Measurement_Required), Toast.LENGTH_SHORT).show();
            return;
        }
        if(editTextAnlaysis.getText().toString().trim().equals("")){ //If editTextAnlaysis Is Equal Null
            Toast.makeText(getActivity(), getString(R.string.Select_Analysis_Employee_is_Required), Toast.LENGTH_SHORT).show();
            return;
        }
        if(editTextNote.getText().toString().trim().equals("")){ //If editTextNote Is Equal Null
            Toast.makeText(getActivity(), getString(R.string.Doctor_Note_is_Required), Toast.LENGTH_SHORT).show();
            return;
        }

        if(editTextNote.getText().toString().trim().contains("%")){ //If editTextNote Contains %
            Toast.makeText(getActivity(), getString(R.string.Please_Remove), Toast.LENGTH_SHORT).show();
            return;
        }

        //Observe Response Of Make Request
        observeMakeRequestResponse(new AddRequestModel(caseResponseData.getId()
                                   ,hospitalEmployeeModlee.getUserItem().getId()
                                   , medicalRecordRequests + "%" + medicalMeasurementRequests + "%" + editTextNote.getText().toString().trim()
                                   , new String[]{""} ));
    }

    private void observeMakeRequestResponse(AddRequestModel addRequestModel) {
        medicalSystemViewModel.getDataFromMakeRequestResponse(addRequestModel);
        medicalSystemViewModel.getSingleLiveEventMakeRequest().observe(getViewLifecycleOwner(),new EventObserver<MessageResponseModel>(new EventHandler<MessageResponseModel>() {
            @Override
            public void onEventUnHandled(MessageResponseModel object) {
                Log.d(TAG, "onEventUnHandled:  iam here" );
                if(object.getStatus() == 1){
                    bottomSheetDialog.dismiss(); //Hide Bottom Sheet Dialog
                    requestIsAddded = true;
                    binding.nameCasedetailsDoctor.setText(editTextAnlaysis.getText());
                    Toasty.success(getActivity(),getString(R.string.The_request_has_been_created) , Toast.LENGTH_SHORT, true).show();
                }else {
                    Toasty.error(getActivity(), object.getMessage(), Toast.LENGTH_SHORT, true).show();

                }
            }
        }));
    }

    private void onClickOnEndCaseButton() {
        binding.endcaseCasedetailsDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialogClass2 cdd = new CustomDialogClass2(getActivity());
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                cdd.show(); //Show Dialog
                cdd.findViewById(R.id.btn2_yes).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        observeLogoutResponse();
                        cdd.dismiss();  //Hide Dialog
                    }
                });
                cdd.findViewById(R.id.btn2_no).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cdd.dismiss();    //Hide Dialog
                    }
                });


            }
        });
    }

    private void observeLogoutResponse() {
        medicalSystemViewModel.getDatafromLogOutCallResponse(caseResponseData.getId());
        medicalSystemViewModel.getSingleLiveEventLogoutCall().observe(getViewLifecycleOwner(),new EventObserver<MessageResponseModel>(new EventHandler<MessageResponseModel>() {
            @Override
            public void onEventUnHandled(MessageResponseModel object) {
                if(object.getStatus() == 1){
                    Toasty.success(getActivity(), getString(R.string.Case_Ended_Successfully), Toast.LENGTH_SHORT, true).show();
                    Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
                }else {
                    Toasty.error(getActivity(), object.getMessage(), Toast.LENGTH_SHORT, true).show();
                }

            }
        }));
    }

    private void onClickOnAddNurseButton() {
        binding.addnursebuttonCasedetailsDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(caseResponseData.getNurseId().equals("")) {
                    Bundle bundle = new Bundle();
                    bundle.putInt("caseId", caseResponseData.getId());
                    Navigation.findNavController(view).navigate(R.id.action_caseDetailsDoctorFragment_to_selectNurseFragment, bundle);
                }else {
                    Toast.makeText(getActivity(), getString(R.string.You_Added_Nurse_Before), Toast.LENGTH_SHORT).show();
                }

                }
        });
    }

    private void onClickOnBackIcon() {
        binding.backCasedetailsDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
            }
        });
    }

    private void inti(){
         //Initialize navController
         navController = NavHostFragment.findNavController(this);
         //Initialize myRecycleAdapterMedical3
         myRecycleAdapterMedical3 = new MyRecycleAdapterMedical3();
         //Initialize medicalModelArrayList
         medicalModelArrayList = new ArrayList<>();
         //Initialize medicalSystemViewModel
         medicalSystemViewModel = new ViewModelProvider(requireActivity()).get(MedicalSystemViewModel.class);
         //Set ViewModel for Data Binding
         binding.setViewModel(medicalSystemViewModel);
         //Initialize bottomSheetDialog
         bottomSheetDialog = new BottomSheetDialog(getContext(),R.style.AppBottomSheetDialogTheme);
         //Initialize sheetview
         sheetview = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.fragment_request,
                 (ViewGroup) view.findViewById(R.id.bottomsheet));
         //Initialize imageView
         imageView = sheetview.findViewById(R.id.imageMedicalrecord_requestfragment);
         //Initialize imageView2
         imageView2= sheetview.findViewById(R.id.imageMedicalmeasurement_requestfragment);
         //Initialize textView
         textView = sheetview.findViewById(R.id.Medicalrecord_requestfragment);
         //Initialize textView2
         textView2 = sheetview.findViewById(R.id.Medicalmeasurement_requestfragment);
         //Initialize linearLayout
         linearLayout = sheetview.findViewById(R.id.linearMedicalrecord_requestfragment);
         //Initialize linearLayout2
         linearLayout2 = sheetview.findViewById(R.id.linearMedicalmeasurement_requestfragment);
         //Initialize requestButton
         requestButton = sheetview.findViewById(R.id.requestbutton_requestfragment);
         //Initialize sendButton
         sendButton = sheetview.findViewById(R.id.sendbutton_requestfragment);
         //Initialize editTextNote
         editTextNote = sheetview.findViewById(R.id.addnote_requestfragment);
         //Initialize editTextAnlaysis
         editTextAnlaysis = sheetview.findViewById(R.id.SelectAnalysis_requestfragment);

     }

    private void onClickOnMedicalMeasurement() {
        binding.medicalmeasurementCasedetailsDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.linear2medicalrecordCasedetailsDoctor.setVisibility(View.INVISIBLE);
                binding.linearcaseCasedetailsDoctor.setVisibility(View.INVISIBLE);
                binding.linear3medicalmeasurementCasedetailsDoctor.setVisibility(View.VISIBLE);

                binding.medicalmeasurementCasedetailsDoctor.setBackgroundResource(R.drawable.shape4);
                binding.medicalmeasurementCasedetailsDoctor.setTextColor(Color.WHITE);

                binding.caseCasedetailsDoctor.setBackgroundResource(R.drawable.shape10);
                binding.caseCasedetailsDoctor.setTextColor(Color.BLACK);

                binding.medicalrecordCasedetailsDoctor.setBackgroundResource(R.drawable.shape10);
                binding.medicalrecordCasedetailsDoctor.setTextColor(Color.BLACK);
            }
        });
    }

    private void onClickOnCase() {
        binding.caseCasedetailsDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.linear2medicalrecordCasedetailsDoctor.setVisibility(View.INVISIBLE);
                binding.linear3medicalmeasurementCasedetailsDoctor.setVisibility(View.INVISIBLE);
                binding.linearcaseCasedetailsDoctor.setVisibility(View.VISIBLE);

                binding.caseCasedetailsDoctor.setBackgroundResource(R.drawable.shape4);
                binding.caseCasedetailsDoctor.setTextColor(Color.WHITE);

                binding.medicalmeasurementCasedetailsDoctor.setBackgroundResource(R.drawable.shape10);
                binding.medicalmeasurementCasedetailsDoctor.setTextColor(Color.BLACK);

                binding.medicalrecordCasedetailsDoctor.setBackgroundResource(R.drawable.shape10);
                binding.medicalrecordCasedetailsDoctor.setTextColor(Color.BLACK);
            }
        });
    }

    private void onClickOnMedicalRecord() {

        binding.medicalrecordCasedetailsDoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                binding.linearcaseCasedetailsDoctor.setVisibility(View.INVISIBLE);
                binding.linear2medicalrecordCasedetailsDoctor.setVisibility(View.VISIBLE);
                binding.linear3medicalmeasurementCasedetailsDoctor.setVisibility(View.INVISIBLE);

                binding.caseCasedetailsDoctor.setBackgroundResource(R.drawable.shape10);
                binding.caseCasedetailsDoctor.setTextColor(Color.BLACK);

                binding.medicalmeasurementCasedetailsDoctor.setBackgroundResource(R.drawable.shape10);
                binding.medicalmeasurementCasedetailsDoctor.setTextColor(Color.BLACK);

                binding.medicalrecordCasedetailsDoctor.setBackgroundResource(R.drawable.shape4);
                binding.medicalrecordCasedetailsDoctor.setTextColor(Color.WHITE);
            }
        });
    }
    private void checkIfRTL() {
        config = getContext().getResources().getConfiguration();

        if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
            binding.linear2medicalrecordCasedetailsDoctor.setBackgroundResource(R.drawable.layer2);
            binding.linear3medicalmeasurementCasedetailsDoctor.setBackgroundResource(R.drawable.layer2);
        }
    }
}