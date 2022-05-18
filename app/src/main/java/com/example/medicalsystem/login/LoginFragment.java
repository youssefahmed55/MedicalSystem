package com.example.medicalsystem.login;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;


import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.medicalsystem.R;
import com.example.medicalsystem.databinding.FragmentLoginBinding;
import com.example.medicalsystem.pojo.login.LoginData;
import com.example.medicalsystem.singleliveevent.EventHandler;
import com.example.medicalsystem.singleliveevent.EventObserver;
import com.example.medicalsystem.pojo.login.LoginRequestModel;
import com.example.medicalsystem.pojo.login.LoginResponseModel;
import com.example.medicalsystem.viewmodel.MedicalSystemViewModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link LoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LoginFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public LoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LoginFragment newInstance(String param1, String param2) {
        LoginFragment fragment = new LoginFragment();
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
    private FragmentLoginBinding binding;
    private View view;
    private MedicalSystemViewModel medicalSystemViewModel;
    private static final String TAG = "LoginFragment";
    private String token = "";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentLoginBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        inti();                  //Initialize variables
        onpressback();           //Set On Press On Back
        onclickonloginbutton();  //Set On Click On Login Button
        onclickoneyeimage();     //Set On Click On Eye Image


        return view;
    }

    private void onpressback(){

        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                getActivity().finish(); //Finish Activity
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
    }

    private String getDeviceToken() {

        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            Log.w(TAG, "Fetching FCM registration token failed", task.getException());
                            return;
                        }
                        // Get new FCM registration token
                        token = task.getResult();
                        Log.d(TAG, token);
                        //Toast.makeText(getActivity(), token, Toast.LENGTH_SHORT).show();
                    }
                });
        return token ;
    }

    private void onclickoneyeimage() {
      binding.eyeimageLogin.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              if(binding.passwordLogin.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                  //Show Password
                  binding.passwordLogin.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
              }else{
                  //Hide Password
                  binding.passwordLogin.setTransformationMethod(PasswordTransformationMethod.getInstance());

              }
          }
      });

    }

    private void inti() {
        //Initialize medicalSystemViewModel
        medicalSystemViewModel = new ViewModelProvider(requireActivity()).get(MedicalSystemViewModel.class);
    }
    private void onclickonloginbutton() {
        if(isNetworkAvailable()) {
            binding.loginbuttonLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(!isEmailValid(binding.emailLogin.getText().toString().trim())){ //If Email Address Is Not Valid
                        binding.emailLogin.setError(getString(R.string.The_email_address_is_badly_formatted));
                        return;
                    }
                    if (binding.emailLogin.getText().toString().trim().equals("")) {  //If Email Address Is Not Equal Null
                        binding.emailLogin.setError(getString(R.string.Email_is_Required));
                        return;
                    }
                    if (binding.passwordLogin.getText().toString().trim().equals("")) { //If Password Is Not Equal Null
                        binding.passwordLogin.setError(getString(R.string.Password_is_Required));
                        return;
                    }
                    //Observe Response Of Login
                    getResponseDataLogin(binding.emailLogin.getText().toString().trim(), binding.passwordLogin.getText().toString().trim(), getDeviceToken());

                }
            });
        }else {
            Toast.makeText(getActivity(), R.string.Internet_is_Not_Connected, Toast.LENGTH_SHORT).show();
        }
    }


    private void getResponseDataLogin(String email , String password , String deviceToken){
              medicalSystemViewModel.getDatafromLoginResponse(new LoginRequestModel(email,password,deviceToken));
              medicalSystemViewModel.getSingleLiveDataLogin().observe(getViewLifecycleOwner(), new EventObserver<>(new EventHandler<LoginResponseModel>() {
                  @Override
                  public void onEventUnHandled(LoginResponseModel object) {

                          if (object.getStatus() == 1){
                              Toasty.success(getActivity(), getActivity().getString(R.string.Login_Success), Toast.LENGTH_SHORT, true).show();
                              saveThatUserLogin(object.getData());
                              navigateToCorrectGraph(object.getData());
                          }else {
                              if (object.getMessage().equals("Unauthorized"))
                              Toasty.info(getActivity(), getString(R.string.Email_Or_Password_Is_Incorrect), Toast.LENGTH_SHORT, true).show();
                              else
                              Toasty.info(getActivity(), object.getMessage(), Toast.LENGTH_SHORT, true).show();
                          }
                  }
              }));


    }

    private void navigateToCorrectGraph(LoginData data) {
        Log.d(TAG, "navigateToCorrectGraph: " + data.getAccessToken());
        //doctor - hr -  receptionist - Analysis -manger - Nurse
        if(data.getType().equalsIgnoreCase("doctor"))
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_doctorFragment);
        else if(data.getType().equalsIgnoreCase("HR"))
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_HRFragment);
        else if(data.getType().equalsIgnoreCase("receptionist"))
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_specialist_receptionistFragment);
        else if(data.getType().equalsIgnoreCase("analysis"))
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_specialistAnalysisEmployeeFragment);
        else if(data.getType().equalsIgnoreCase("manger"))
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_managerFragment);
        else if(data.getType().equalsIgnoreCase("nurse"))
            Navigation.findNavController(view).navigate(R.id.action_loginFragment_to_nurseFragment);
        else
            Toasty.error(getActivity(), "Error Type", Toast.LENGTH_SHORT, true).show();

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager
                = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }
    public static boolean isEmailValid(String email) {
        String expression = "^[\\w\\.-]+@([\\w\\-]+\\.)+[A-Z]{2,4}$";
        Pattern pattern = Pattern.compile(expression, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
    private void saveThatUserLogin(LoginData loginData1) {
        medicalSystemViewModel.saveLoginData(loginData1);  //Save Login Data In SharedPreferences
    }
}