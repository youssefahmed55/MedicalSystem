package com.example.medicalsystem.hr;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.medicalsystem.customdialog.CustomDialogClass;
import com.example.medicalsystem.R;
import com.example.medicalsystem.databinding.FragmentHRBinding;
import com.example.medicalsystem.viewmodel.MedicalSystemViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link HRFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class HRFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public HRFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment HRFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static HRFragment newInstance(String param1, String param2) {
        HRFragment fragment = new HRFragment();
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

    private FragmentHRBinding binding;
    private View view;
    private MedicalSystemViewModel medicalSystemViewModel;
    private final String PROFILE_IMAGE = "https://png.pngtree.com/png-vector/20191027/ourlarge/pngtree-avatar-vector-icon-white-background-png-image_1884971.jpg";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_h_r, container, false);
        view = binding.getRoot();
        binding.setLifecycleOwner(this);
        inti();                               //Initialize variables
        onpressback();                        //Set On Press On Back
        setProfileImage();                    //Set Profile Image
        onClickOnLogout();                    //Set On Click On Logout Icon
        onClickOnEmplyeeHRIcon();             //Set On CLick On Employee Icon
        onClickOnCardImageProfile();          //Set On Click On Profile Image
        onClickonAttendance();                //Set On Click On Attendance Icon
        onClickOnTasksIcon();                 //Set On CLick On Tasks Icon
        onClickOnReportsIcon();               //Set On CLick On Reports Icon
        return view;
    }
    private void onClickOnReportsIcon() {
        binding.reportsHR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_HRFragment_to_reportFragment);
            }
        });
    }
    private void onClickOnTasksIcon() {
        binding.tasksHR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_HRFragment_to_tasksFragment);
            }
        });
    }

    private void inti() {
        //Initialize medicalSystemViewModel
        medicalSystemViewModel = new ViewModelProvider(requireActivity()).get(MedicalSystemViewModel.class);
        //Set ViewModel for Data Binding
        binding.setViewModel(medicalSystemViewModel);
        //Get Data Of Login From SharedPrefrences
        medicalSystemViewModel.getLoginData();
    }
    private void onClickonAttendance() {
        binding.attendanceHR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_HRFragment_to_attendanceandLeavingFragment);
            }
        });
    }
    private void onClickOnCardImageProfile() {
        binding.cardimageHR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_HRFragment_to_myProfileFragment);
            }
        });
    }
    private void setProfileImage() {
        Glide.with(this).load(PROFILE_IMAGE).into(binding.imageHR); //Load Image From Link
    }


    private void onClickOnEmplyeeHRIcon() {
        binding.employeeHR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_HRFragment_to_employeeListHRFragment);
            }
        });
    }


    private void onClickOnLogout() {
        binding.logoutHR.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomDialogClass cdd = new CustomDialogClass(getActivity());
                cdd.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                cdd.show(); //Show Dialog

                cdd.findViewById(R.id.btn_yes).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        medicalSystemViewModel.deleteDataOfLogin();       //Delete Data of Login From SharedPrefrences
                        medicalSystemViewModel.deleteDate();              //Delete Date From SharedPrefrences
                        Navigation.findNavController(view).popBackStack();//Back to previous Fragment
                        cdd.dismiss();  //Hide Dialog
                    }
                });
                cdd.findViewById(R.id.btn_no).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        cdd.dismiss();    //Hide Dialog
                    }
                });

            }
        });

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
}