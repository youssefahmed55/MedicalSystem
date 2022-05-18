package com.example.medicalsystem.manager;

import static android.app.Activity.RESULT_OK;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;

import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.medicalsystem.R;
import com.example.medicalsystem.adapters.MyRecycleAdapterToDoTask;
import com.example.medicalsystem.databinding.FragmentCreateTaskManagerBinding;
import com.example.medicalsystem.pojo.HospitalEmployeeModle;
import com.example.medicalsystem.pojo.MessageResponseModel;
import com.example.medicalsystem.singleliveevent.EventHandler;
import com.example.medicalsystem.singleliveevent.EventObserver;
import com.example.medicalsystem.viewmodel.MedicalSystemViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import es.dmoral.toasty.Toasty;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CreateTaskManagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CreateTaskManagerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CreateTaskManagerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CreateTaskManagerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CreateTaskManagerFragment newInstance(String param1, String param2) {
        CreateTaskManagerFragment fragment = new CreateTaskManagerFragment();
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
        todos = new ArrayList();
    }
    private FragmentCreateTaskManagerBinding binding;
    private View view;
    private View sheetview;
    private BottomSheetDialog bottomSheetDialog;
    private MyRecycleAdapterToDoTask myRecycleAdapterToDoTask;

    private EditText editText;
    private Button button;
    private HospitalEmployeeModle hospitalEmployeeModlee;
    private final int IMAGEKEY = 1;
    private RequestBody userId , taskName , description  ;
    private List<String> todos;
    private MultipartBody.Part body;
    private NavController navController;
    private Uri path;
    private MedicalSystemViewModel medicalSystemViewModel;
    private static final String TAG = "CreateTaskManagerFragme";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCreateTaskManagerBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        inti();                             //Initialize variables
        onClickOnBackIcon();                //Set On Click On Back Icon
        onClickOnSelectEmployee();          //Set On Click On Select Employee
        onClickOnAddIcon();                 //Set On Click On Add Icon
        setAdapter();                       //Set Adapter
        onCLickonItemOfRecycleForDelete();  //Set On Click On Close Icon On Any Item Of Recycler
        onClickOnUploadImage();             //Set On Click On Upload Image
        onClickOnSendButton();              //Set On Click On Send Button
        observeSelectEmployee();            //Observe Selected Employee

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if(path!=null)
            binding.uploadimageCreateTaskManager.setImageURI(path);

    }

    private void observeSelectEmployee() {
        MutableLiveData<HospitalEmployeeModle> liveData = navController.getCurrentBackStackEntry()
                .getSavedStateHandle()
                .getLiveData("key");

        liveData.observe(getViewLifecycleOwner(), new Observer<HospitalEmployeeModle>() {
            @Override
            public void onChanged(HospitalEmployeeModle hospitalEmployeeModle) {
                hospitalEmployeeModlee = hospitalEmployeeModle;
                binding.SelectEmployeeCreateTaskManager.setText(hospitalEmployeeModlee.getUserItem().getFirstName());
            }
        });
    }

    private void onClickOnSendButton() {
        binding.sendbuttonCreateTaskManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CheckDataToSend(); // Check Data Needed To Send Request for Creating Task
            }
        });
    }

    private void CheckDataToSend() {

        if(binding.tasknameCreateTaskManager.getText().toString().trim().equals("")){ //If Task Name Not Equal Null
            binding.tasknameCreateTaskManager.setError(getString(R.string.Task_Name_is_Required));
            return;
        }
        if(binding.SelectEmployeeCreateTaskManager.getText().toString().trim().equals("")){ //If Selected Employee Not Equal Null
            Toasty.error(getActivity(), getString(R.string.Select_Employee_is_Required), Toast.LENGTH_SHORT, true).show();
            return;
        }
        if(binding.descriptionCreateTaskManager.getText().toString().trim().equals("")){  //If Description Not Equal Null
            binding.descriptionCreateTaskManager.setError(getString(R.string.Task_Description_Is_Required));
            return;
        }

        if(todos.size() == 0){  //If todos Not Equal Null
            Toasty.error(getActivity(), getString(R.string.To_Do_Tasks_is_Required), Toast.LENGTH_SHORT, true).show();
            return;
        }
        if(path == null){      //If path of Photo Not Equal Null
            Toasty.error(getActivity(), getString(R.string.Task_Image_Is_Required), Toast.LENGTH_SHORT, true).show();
            return;
        }


        userId = RequestBody.create(MediaType.parse("multipart/form-data"), String.valueOf(hospitalEmployeeModlee.getUserItem().getId()));
        taskName = RequestBody.create(MediaType.parse("multipart/form-data"), binding.tasknameCreateTaskManager.getText().toString().trim());
        description = RequestBody.create(MediaType.parse("multipart/form-data"), binding.descriptionCreateTaskManager.getText().toString().trim());

        //Observe Response Of Send or Create Task
        observeSendTaskResponse(userId,taskName,body,description,todos);
    }

    private void observeSendTaskResponse(RequestBody userId, RequestBody taskName, MultipartBody.Part bodyy, RequestBody description, List<String> todos) {
        medicalSystemViewModel.getDataFromCreateTaskResponse(userId,taskName,bodyy,description,todos);
        medicalSystemViewModel.getSingleLiveEventCreateTask().observe(getViewLifecycleOwner(),new EventObserver<MessageResponseModel>(new EventHandler<MessageResponseModel>() {
            @Override
            public void onEventUnHandled(MessageResponseModel object) {
                if(object.getStatus() == 1){
                    Toasty.success(getActivity(), getString(R.string.Success), Toast.LENGTH_SHORT, true).show();
                    Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
                }else{
                    Log.d(TAG, "onEventUnHandled: " + object.getMessage());
                    Toasty.error(getActivity(), object.getMessage(), Toast.LENGTH_SHORT, true).show();
                }
            }
        }));
    }

    private void onClickOnUploadImage() {
        binding.uploadImagebuttonCreateTaskManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectImage();   //Select Image From Gallery
            }
        });
    }
    private void selectImage() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, IMAGEKEY);
    }

    private void onCLickonItemOfRecycleForDelete() {
        myRecycleAdapterToDoTask.setOnClickListeners(new MyRecycleAdapterToDoTask.OnmyClickListenerrr() {
            @Override
            public void onclick2(int pos) {
                todos.remove(pos);
                myRecycleAdapterToDoTask.notifyDataSetChanged();
            }
        });
    }

    private void setAdapter() {
        myRecycleAdapterToDoTask.setArrayList(todos);                         //Set Array to Adapter
        binding.recycleCreateTaskManager.setAdapter(myRecycleAdapterToDoTask);//Set Adapter to Recycler
    }

    private void onClickOnAddIcon() {
        binding.addimageCreateTaskManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if(!editText.getText().toString().trim().equals("")){
                            todos.add(editText.getText().toString().trim());
                            myRecycleAdapterToDoTask.notifyDataSetChanged();
                            bottomSheetDialog.dismiss(); //Hide Bottom Sheet Dialog
                        }
                    }
                });


                bottomSheetDialog.setContentView(sheetview); //Set Content View To Bottom Sheet Dialog
                bottomSheetDialog.show();                    //Show Bottom Sheet Dialog
            }
        });
    }

    private void onClickOnSelectEmployee() {
        binding.SelectEmployeeCreateTaskManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_createTaskManagerFragment_to_selectEmployeeManagerFragment);
            }
        });
    }

    private void onClickOnBackIcon() {
        binding.backCreateTaskManager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
            }
        });
    }

    private void inti() {
        //Initialize medicalSystemViewModel
        medicalSystemViewModel = new ViewModelProvider(requireActivity()).get(MedicalSystemViewModel.class);
        //Initialize myRecycleAdapterToDoTask
        myRecycleAdapterToDoTask = new MyRecycleAdapterToDoTask();
        //Initialize navController
        navController = NavHostFragment.findNavController(this);
        //Initialize bottomSheetDialog
        bottomSheetDialog = new BottomSheetDialog(getContext(),R.style.AppBottomSheetDialogTheme);
        //Initialize sheetview
        sheetview = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.addtaskmanager,
                (ViewGroup) view.findViewById(R.id.bottomsheet2));
        //Initialize editText
        editText = sheetview.findViewById(R.id.edittext_addtaskmanager);
        //Initialize button
        button = sheetview.findViewById(R.id.addbutton_addtaskmanager);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode== IMAGEKEY && resultCode== RESULT_OK && data!=null)
        {
            path = data.getData();

            binding.uploadimageCreateTaskManager.setImageURI(path);                    // Show Image
            File imageFile = new File(createCopyAndReturnRealPath(getContext(),path)); // Save Image In Image File


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