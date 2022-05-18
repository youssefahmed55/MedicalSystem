package com.example.medicalsystem.attendanceandleaving;


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
import com.example.medicalsystem.databinding.FragmentAttendanceandLeavingBinding;
import com.example.medicalsystem.pojo.MessageResponseModel;

import com.example.medicalsystem.singleliveevent.EventHandler;
import com.example.medicalsystem.singleliveevent.EventObserver;
import com.example.medicalsystem.viewmodel.MedicalSystemViewModel;

import es.dmoral.toasty.Toasty;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AttendanceandLeavingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AttendanceandLeavingFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AttendanceandLeavingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AttendanceandLeavingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AttendanceandLeavingFragment newInstance(String param1, String param2) {
        AttendanceandLeavingFragment fragment = new AttendanceandLeavingFragment();
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
    private FragmentAttendanceandLeavingBinding binding;
    private View view;
    private MedicalSystemViewModel medicalSystemViewModel;
    private String status;
    private final String PROFILE_IMAGE = "https://png.pngtree.com/png-vector/20191027/ourlarge/pngtree-avatar-vector-icon-white-background-png-image_1884971.jpg";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_attendanceand_leaving, container, false);
        view = binding.getRoot();
        binding.setLifecycleOwner(this);
        inti();                       //Initialize variables
        setProfileImage();            //Set Profile Image
        onClickOnCardImageProfile();  //Set On CLick On Profile Image
        observeResultofAttendance();  //Observe Result Of Attendane
        onClickOnAttendanceButton();  //Set On CLick On Attendance Button
        onClickOnLeavingButton();     //Set On CLick On Leaving Button

        return view;
    }

    private void observeResultofAttendance() {
        medicalSystemViewModel.getSingleLiveEventAttendance().observe( getViewLifecycleOwner(),new EventObserver<MessageResponseModel>(new EventHandler<MessageResponseModel>() {
            @Override
            public void onEventUnHandled(MessageResponseModel object) {
                if(object.getStatus() == 1){
                    Toasty.success(getActivity(), status + " " + getString(R.string.Success), Toast.LENGTH_SHORT, true).show();
                    Bundle bundle = new Bundle();
                    bundle.putString("status",status);
                    Navigation.findNavController(view).navigate(R.id.action_attendanceandLeavingFragment_to_touchIDSensorFragment,bundle);
                }else{
                    Toasty.error(getActivity(), object.getMessage(), Toast.LENGTH_SHORT, true).show();
                }

            }
        }));
    }

    private void inti() {
        //Initialize medicalSystemViewModel
        medicalSystemViewModel = new ViewModelProvider(requireActivity()).get(MedicalSystemViewModel.class);
        //Set ViewModel for Data Binding
        binding.setViewModel(medicalSystemViewModel);
        //Get Data Of Login From SharedPrefrences
        medicalSystemViewModel.getLoginData();
    }

    private void onClickOnLeavingButton() {
        binding.leavingbuttonAttendanceandleaving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = "leaving";
                medicalSystemViewModel.getDataFromAttendanceResponse(status);
            }
        });
    }

    private void onClickOnAttendanceButton() {
        binding.attendancebuttonAttendanceandleaving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                status = "attendance";
                medicalSystemViewModel.getDataFromAttendanceResponse(status);
            }
        });
    }

    private void onClickOnCardImageProfile() {
        binding.cardimageAttendanceandleaving.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_attendanceandLeavingFragment_to_myProfileFragment);
            }
        });
    }


    private void setProfileImage() {
        Glide.with(this).load(PROFILE_IMAGE).into(binding.imageAttendanceandleaving); //Load Image From Link
    }



}