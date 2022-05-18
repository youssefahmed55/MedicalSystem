package com.example.medicalsystem.login;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.medicalsystem.R;
import com.example.medicalsystem.pojo.login.LoginData;
import com.example.medicalsystem.viewmodel.MedicalSystemViewModel;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SplashFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SplashFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SplashFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SplashFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SplashFragment newInstance(String param1, String param2) {
        SplashFragment fragment = new SplashFragment();
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
    private View view;
    private MedicalSystemViewModel medicalSystemViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_splash, container, false);
        inti();                                         //initialize variables
        new Handler().postDelayed(new Runnable() {      //Handler To Splash
            @Override
            public void run() {
                checkiflogin();
            }
        },2000);


        return  view ;
    }

    private void inti() {
        //Initialize medicalSystemViewModel
        medicalSystemViewModel = new ViewModelProvider(requireActivity()).get(MedicalSystemViewModel.class);
    }

    private void checkiflogin() {
        medicalSystemViewModel.getLoginData();
        medicalSystemViewModel.getLoginDataMutableLiveData().observe(getViewLifecycleOwner(), new Observer<LoginData>() {
            @Override
            public void onChanged(LoginData loginData) {
                if(loginData != null){                  //if login Data from SharedPreferences not Equal null
                    navigateToCorrectGraph(loginData);  //Go to Correct Fragment
                }else {
                    Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_loginFragment); //Go to Login Fragment
                }
            }
        });
    }
    private void navigateToCorrectGraph(LoginData data) {
        //doctor - hr -  receptionist - Analysis -manger - Nurse
        if(data.getType().equalsIgnoreCase("doctor"))
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_doctorFragment);
        else if(data.getType().equalsIgnoreCase("HR"))
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_HRFragment);
        else if(data.getType().equalsIgnoreCase("receptionist"))
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_specialist_receptionistFragment);
        else if(data.getType().equalsIgnoreCase("analysis"))
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_specialistAnalysisEmployeeFragment);
        else if(data.getType().equalsIgnoreCase("manger"))
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_managerFragment);
        else if(data.getType().equalsIgnoreCase("nurse"))
            Navigation.findNavController(view).navigate(R.id.action_splashFragment_to_nurseFragment);
        else
            Toasty.error(getActivity(), "Error Type", Toast.LENGTH_SHORT, true).show();

    }
}