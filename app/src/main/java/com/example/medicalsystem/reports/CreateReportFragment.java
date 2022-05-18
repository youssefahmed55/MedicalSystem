package com.example.medicalsystem.reports;

import static android.app.Activity.RESULT_OK;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.medicalsystem.R;
import com.example.medicalsystem.databinding.FragmentCreateReportBinding;
import com.example.medicalsystem.pojo.MessageResponseModel;
import com.example.medicalsystem.singleliveevent.EventHandler;
import com.example.medicalsystem.singleliveevent.EventObserver;
import com.example.medicalsystem.viewmodel.MedicalSystemViewModel;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateReportFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateReportFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreateReportFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateReportFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateReportFragment newInstance(String param1, String param2) {
        CreateReportFragment fragment = new CreateReportFragment();
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
    private FragmentCreateReportBinding binding;
    private View view;
    private MedicalSystemViewModel medicalSystemViewModel;
    private final int IMAGEKEY = 1;
    private File imageFile;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateReportBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        inti();                         //Initialize variables
        onClickOnBackIcon();            //Set On Click On Back Icon
        onClickOnCreateReportButton();  //Set On Click On Create Report Button
        onClickOnUploadImageButton();   //Set On Click On Upload Image Button


        return view;
    }

    private void onClickOnUploadImageButton() {
        binding.uploadImageCreatereport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();  //Select Image From Gallary
            }
        });
    }

    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGEKEY);
    }

    private void inti() {
        //Initialize medicalSystemViewModel
        medicalSystemViewModel = new ViewModelProvider(requireActivity()).get(MedicalSystemViewModel.class);
    }

    private void onClickOnCreateReportButton() {
        binding.createReportCreatereport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkRequirement();  //Check Requirement Data That Needed To Create Report
            }
        });
    }

    private void checkRequirement() {
        if(binding.reportnameCreatereport.getText().toString().trim().equals("")){ //If Report Name Not Equal null
            Toasty.error(getActivity(), getString(R.string.Report_Name_Is_Required), Toast.LENGTH_SHORT, true).show();
            return;
        }
        if(binding.descriptionCreatereport.getText().toString().trim().equals("")){ //If Description Not Equal null
            Toasty.error(getActivity(), getString(R.string.Report_Description_Is_Required), Toast.LENGTH_SHORT, true).show();
            return;
        }
        if(imageFile == null){          //If Image File Not Equal null
            Toasty.error(getActivity(), getString(R.string.Report_Image_Is_Required), Toast.LENGTH_SHORT, true).show();
            return;
        }

        RequestBody requestFile =
                RequestBody.create(MediaType.parse("multipart/form-data"), imageFile);
        // MultipartBody.Part is used to send also the actual file name
        MultipartBody.Part body = MultipartBody.Part.createFormData("image", imageFile.getName(), requestFile);
        // requestBody is used to send another data like String
        RequestBody reportName = RequestBody.create(MediaType.parse("multipart/form-data"), binding.reportnameCreatereport.getText().toString().trim());
        RequestBody description = RequestBody.create(MediaType.parse("multipart/form-data"), binding.descriptionCreatereport.getText().toString().trim());

        observeCreateReportResponse(reportName,description,body); //Observe Response Of Create Report

    }

    private void observeCreateReportResponse(RequestBody reportName, RequestBody description, MultipartBody.Part body) {
        medicalSystemViewModel.getDataFromCreateReportResponse(reportName,description,body);
        medicalSystemViewModel.getSingleLiveEventCreateReport().observe(getViewLifecycleOwner(),new EventObserver<MessageResponseModel>(new EventHandler<MessageResponseModel>() {
            @Override
            public void onEventUnHandled(MessageResponseModel object) {
                if (object.getStatus() == 1){
                    Toasty.success(getActivity(), getString(R.string.report_Created_Successfully), Toast.LENGTH_SHORT, true).show();
                    Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
                }else {
                    Toasty.error(getActivity(), object.getMessage(), Toast.LENGTH_SHORT, true).show();
                }
            }
        }));

    }


    private void onClickOnBackIcon() {
        binding.backCreatereport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== IMAGEKEY && resultCode== RESULT_OK && data!=null)
        {
            Uri path = data.getData();
            binding.imageCreatereport.setImageURI(path);                          //Show Image
            imageFile = new File(createCopyAndReturnRealPath(getContext(),path)); //Save Image in Image File

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