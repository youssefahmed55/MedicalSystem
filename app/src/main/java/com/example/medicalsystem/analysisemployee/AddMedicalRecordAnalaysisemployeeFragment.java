package com.example.medicalsystem.analysisemployee;

import static android.app.Activity.RESULT_OK;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.medicalsystem.R;

import com.example.medicalsystem.adapters.MyRecycleAdapterMedical2;
import com.example.medicalsystem.databinding.FragmentAddMedicalRecordAnalaysisemployeeBinding;
import com.example.medicalsystem.pojo.MedicalModel;
import com.example.medicalsystem.pojo.MessageResponseModel;
import com.example.medicalsystem.pojo.cases.showcase.ShowCaseResponseData;

import com.example.medicalsystem.singleliveevent.EventHandler;
import com.example.medicalsystem.singleliveevent.EventObserver;
import com.example.medicalsystem.viewmodel.MedicalSystemViewModel;


import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddMedicalRecordAnalaysisemployeeFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddMedicalRecordAnalaysisemployeeFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddMedicalRecordAnalaysisemployeeFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddMedicalRecordAnalaysisemployeeFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddMedicalRecordAnalaysisemployeeFragment newInstance(String param1, String param2) {
        AddMedicalRecordAnalaysisemployeeFragment fragment = new AddMedicalRecordAnalaysisemployeeFragment();
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
    private FragmentAddMedicalRecordAnalaysisemployeeBinding binding;
    private View view;
    private ArrayList<MedicalModel> arrayList;
    private MyRecycleAdapterMedical2 myRecycleAdapterMedical2;
    private Configuration config;
    private MedicalSystemViewModel medicalSystemViewModel;
    private int IMAGEKEY = 1;
    private RequestBody callId , note , status;
    private MultipartBody.Part body;
    private final String PROFILE_IMAGE = "https://png.pngtree.com/png-vector/20191027/ourlarge/pngtree-avatar-vector-icon-white-background-png-image_1884971.jpg";

    private static final String TAG = "AddMedicalRecordAnalays";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_medical_record_analaysisemployee, container, false);
        view = binding.getRoot();
        binding.setLifecycleOwner(this);
        checkIfRTL();                       //Check If RTL
        inti();                             //Initialize variables
        setProfileImage();                  //Set Profile Image
        onClickOnBackIcon();                //Set On Click On Back Icon

        if(caseResponseData != null){       //If caseResponseData Not Equal Null
            medicalSystemViewModel.getDatafromShowCaseResponse(caseResponseData.getId()); //Observe Response Of Case Details By Id
            getMedicalRequirements();       //Get Medical Requirements
            setMedicalAdapter();            //Set Medical Adapter
            setOnCLickOnAddRecordButton();  //Set On Click On Add Record Button
            setOnClickOnUploadImage();      //Set On Click On Upload Image Button
        }

        return view;
    }
    private void setProfileImage() {
        Glide.with(this).load(PROFILE_IMAGE).into(binding.profileimageAddmedicalrecordAnalysisemployee); //Load Image From Link
    }
    private void setOnClickOnUploadImage() {
        binding.uploadImageAddmedicalrecordAnalysisemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage(); //Select Image From Gallery
            }
        });
    }

    private void setOnCLickOnAddRecordButton() {
        binding.AddrecordbuttonAddmedicalrecordAnalysisemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!binding.addnoteAddmeasurementAnalysis.getText().toString().trim().equals("")){

                    if(!binding.addnoteAddmeasurementAnalysis.getText().toString().trim().contains("%")){
                        if(body != null){
                            // add another part within the multipart request
                            callId = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(caseResponseData.getId()));
                            note = RequestBody.create(MediaType.parse("multipart/form-data"), binding.addnoteAddmeasurementAnalysis.getText().toString().trim());
                            status = RequestBody.create(MediaType.parse("multipart/form-data"), "padding");
                            //Observe Response Of Send Medical Record Response
                            observeSendMedicalRecordResponse(callId,body,note,status);
                        }else {
                            Toast.makeText(getActivity(), getString(R.string.Please_Upload_Medical_Image_First), Toast.LENGTH_SHORT).show();
                        }
                    }else {
                        Toast.makeText(getActivity(), getString(R.string.Please_Remove), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getActivity(), getString(R.string.Note_Is_Required), Toast.LENGTH_SHORT).show();
                }

                }

        });
    }

    private void observeSendMedicalRecordResponse(RequestBody callId, MultipartBody.Part body, RequestBody note, RequestBody status) {
        medicalSystemViewModel.getDataFromSendMedicalRecordResponse(callId,body,note,status);
        medicalSystemViewModel.getSingleLiveEventSendMedicalRecord().observe(getViewLifecycleOwner(),new EventObserver<MessageResponseModel>(new EventHandler<MessageResponseModel>() {
            @Override
            public void onEventUnHandled(MessageResponseModel object) {
                if(object.getStatus() == 1){
                    observeMedicalImage();
                }else{
                    Toasty.error(getActivity(), object.getMessage(), Toast.LENGTH_SHORT, true).show();
                }
            }
        }));

    }

    private void observeMedicalImage() {
        medicalSystemViewModel.getMedicalRecoredImage(caseResponseData.getId());
        medicalSystemViewModel.getSingleLiveEventMedicalRecordImage().observe(getViewLifecycleOwner(),new EventObserver<String>(new EventHandler<String>() {
            @Override
            public void onEventUnHandled(String object) {
                Log.d(TAG, "onEventUnHandled: " + object);
                Toasty.success(getActivity(), getString(R.string.Success), Toast.LENGTH_SHORT, true).show();
                Navigation.findNavController(view).getPreviousBackStackEntry().getSavedStateHandle().set("key",binding.addnoteAddmeasurementAnalysis.getText().toString().trim()+"%"+object);//Save Data To Previous Fragment
                Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
            }
        }));
    }


    private void setMedicalAdapter() {
        myRecycleAdapterMedical2.setArrayList(arrayList);                                    //Set Array to Adapter
        binding.recycleAddmedicalrecordAnalysisemployee.setAdapter(myRecycleAdapterMedical2);//Set Adapter to Recycler
    }

    private void getMedicalRequirements() {
        String[] s = caseResponseData.getMedicalRecordNote().split("%");

        if(s.length == 3){
            String[] ss = s[1].split("-");
            for(int j = 0 ; j < ss.length ; j++){
                arrayList.add(new MedicalModel(ss[j],""));
            }

            binding.detailsnoteAddmedicalrecordAnalysisemployee.setText(s[2]);

        }

    }

    private void onClickOnBackIcon() {
        binding.backAddmedicalrecordAnalysisemployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
            }
        });
    }

    private void checkIfRTL() {
        config = getContext().getResources().getConfiguration();
        if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
            binding.linear1AddmedicalrecordAnalysisemployee.setBackgroundResource(R.drawable.layer2);
        }
    }

    private void inti() {
        //Initialize medicalSystemViewModel
        medicalSystemViewModel = new ViewModelProvider(requireActivity()).get(MedicalSystemViewModel.class);
        //Set ViewModel for Data Binding
        binding.setViewModel(medicalSystemViewModel);
        //Initialize arrayList
        arrayList = new ArrayList<>();
        //Initialize myRecycleAdapterMedical2
        myRecycleAdapterMedical2 = new MyRecycleAdapterMedical2();
    }
    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGEKEY);
    }



    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== IMAGEKEY && resultCode== RESULT_OK && data!=null)
        {
            Uri path = data.getData();

            binding.imageAddmedicalrecordAnalysisemployee.setImageURI(path);          //Show Image
            File imageFile = new File(createCopyAndReturnRealPath(getContext(),path));//Save Image In Image File

            RequestBody requestFile =
                    RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);


           // MultipartBody.Part is used to send also the actual file name
            body = MultipartBody.Part.createFormData("image", imageFile.getName(), requestFile);


        }
    }
    public static String createCopyAndReturnRealPath(
            @NonNull Context context, @NonNull Uri uri) {
        final ContentResolver contentResolver = context.getContentResolver();
        if (contentResolver == null)
            return null;

        // Create file path inside app's data dir
        String filePath = context.getApplicationInfo().dataDir + File.separator + "temp_file";
        File file = new File(filePath);
        try {
            InputStream inputStream = contentResolver.openInputStream(uri);
            if (inputStream == null)
                return null;
            OutputStream outputStream = new FileOutputStream(file);
            byte[] buf = new byte[1024];
            int len;
            while ((len = inputStream.read(buf)) > 0)
                outputStream.write(buf, 0, len);
            outputStream.close();
            inputStream.close();
        } catch (IOException ignore) {
            return null;
        }
        return file.getAbsolutePath();
    }

}