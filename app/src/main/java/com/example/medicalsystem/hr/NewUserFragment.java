package com.example.medicalsystem.hr;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.medicalsystem.adapters.MyArrayAdapter;
import com.example.medicalsystem.R;
import com.example.medicalsystem.databinding.FragmentNewUserBinding;
import com.example.medicalsystem.pojo.login.LoginResponseModel;
import com.example.medicalsystem.pojo.register.RegisterRequestModel;
import com.example.medicalsystem.singleliveevent.EventHandler;
import com.example.medicalsystem.singleliveevent.EventObserver;
import com.example.medicalsystem.viewmodel.MedicalSystemViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NewUserFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NewUserFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public NewUserFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment NewUserFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static NewUserFragment newInstance(String param1, String param2) {
        NewUserFragment fragment = new NewUserFragment();
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
    private FragmentNewUserBinding binding;
    private View view;
    private View sheetview;
    private BottomSheetDialog bottomSheetDialog;
    private CalendarView calendarView;
    private MedicalSystemViewModel medicalSystemViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentNewUserBinding.inflate(inflater, container, false);
        view = binding.getRoot();

        inti();                     //Initialize variables
        onClickOnBackIcon();        //Set On Click On Back Icon
        onclickondateofbirth();     //Set On Click On Date
        onclickoneyeimage();        //Set On Click On Eye Image
        editGenderSpinner();        //Set On Click On Gender
        editSpecialistSpinner();    //Set On Click On Specialist
        editStatuesSpinner();       //Set On Click On Status
        onClickOnCreateuserButton();//Set On Click On Create User Button


        return view;
    }

    private void onClickOnBackIcon() {
        binding.backNewuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack();    //Back to previous Fragment
            }
        });
    }

    private void onClickOnCreateuserButton() {
        binding.createuserbuttonNewuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isNetworkAvailable()) { //If Internet Not Connected
                    createUser();
                } else
                    Toast.makeText(getActivity(), getString(R.string.Internet_is_Not_Connected), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void createUser() {
        if (binding.firstnameNewuser.getText().toString().trim().equals("")) { //If First Name Not Equal Null
            binding.firstnameNewuser.setError(getString(R.string.First_Name_is_Required));
            return;
        }
        if (binding.lastnameNewuser.getText().toString().trim().equals("")) {  //If Last Name Not Equal Null
            binding.lastnameNewuser.setError(getString(R.string.Last_Name_is_Required));
            return;
        }
        if (binding.genderNewuser.getSelectedItem().toString().trim().equals(getString(R.string.gender))) {  //If Gender Not Selected Yet
            Toast.makeText(getActivity(), getString(R.string.Gender_Required), Toast.LENGTH_SHORT).show();
            return;
        }
        if (binding.specialistNewuser.getSelectedItem().toString().trim().equals(getString(R.string.specialist)) ) { //If Specialist Not Selected Yet
            Toast.makeText(getActivity(), getString(R.string.Specialist_Required), Toast.LENGTH_SHORT).show();
            return;
        }
        if (binding.dateofbirthNewuser.getText().toString().trim().equals("")) { //If Date Not Equal Null
            Toast.makeText(getActivity(), getString(R.string.Date_of_birth_is_Required), Toast.LENGTH_SHORT).show();
            return;
        }
        if (binding.statuesNewuser.getSelectedItem().toString().trim().equals(getString(R.string.status))) { //If Statues Not Equal Null
            Toast.makeText(getActivity(), getString(R.string.Status_Required), Toast.LENGTH_SHORT).show();
            return;
        }
        if (binding.phonenumberNewuser.getText().toString().trim().equals("")) { //If Phone Number Not Equal Null
            binding.phonenumberNewuser.setError(getString(R.string.Phone_Number_is_Required));
            return;
        }
        if (binding.emailNewuser.getText().toString().trim().equals("")) { //If Email Not Equal Null
            binding.emailNewuser.setError(getString(R.string.Email_is_Required));
            return;
        }
        if(!isEmailValid(binding.emailNewuser.getText().toString().trim())){ //If Email Address is Valif
            binding.emailNewuser.setError(getString(R.string.The_email_address_is_badly_formatted));
            return;
        }
        if (binding.addressNewuser.getText().toString().trim().equals("")) { //If Address Not Equal Null
            binding.addressNewuser.setError(getString(R.string.Address_is_Required));
            return;
        }
        if (binding.passwordNewuser.getText().toString().trim().equals("")) { //If Password Not Equal Null
            binding.passwordNewuser.setError(getString(R.string.Password_is_Required));
            return;
        }
        if(binding.passwordNewuser.getText().toString().trim().length() < 7){  //If Password Length < 7
            binding.passwordNewuser.setError(getString(R.string.Minimum_Password_Length_Is_6));
        }

        //Observe Response Of Register
        getResponseDateRegister(binding.firstnameNewuser.getText().toString().trim(),
                                binding.lastnameNewuser.getText().toString().trim(),
                                binding.genderNewuser.getSelectedItem().toString().trim(),
                                binding.specialistNewuser.getSelectedItem().toString().trim(),
                                binding.dateofbirthNewuser.getText().toString().trim(),
                                binding.statuesNewuser.getSelectedItem().toString().trim(),
                                binding.phonenumberNewuser.getText().toString().trim(),
                                binding.emailNewuser.getText().toString().trim(),
                                binding.addressNewuser.getText().toString().trim(),
                                binding.passwordNewuser.getText().toString().trim(),
                                binding.specialistNewuser.getSelectedItem().toString().trim());



    }
    private void getResponseDateRegister(String firstname , String lastname , String gender , String specialist , String dateofbirth , String statues , String phoneNumber , String email , String address , String password,String type){
           medicalSystemViewModel.getDatafromRegisterResponse(new RegisterRequestModel(email,password,firstname,lastname,gender,specialist,dateofbirth,statues,address,phoneNumber,type));
           medicalSystemViewModel.getSingleLiveDataRegister().observe(getViewLifecycleOwner(), new EventObserver<>(new EventHandler<LoginResponseModel>() {
               @Override
               public void onEventUnHandled(LoginResponseModel object) {
                   if (object.getStatus() == 1){
                       Toasty.success(getActivity(), getString(R.string.Create_New_User_Successed), Toast.LENGTH_SHORT, true).show();
                       Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
                   }else {
                       Toasty.info(getActivity(), object.getMessage(), Toast.LENGTH_SHORT, true).show();
                   }
               }
           }));


    }

    private void onclickoneyeimage() {
        binding.eyeimageNewUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(binding.passwordNewuser.getTransformationMethod().equals(PasswordTransformationMethod.getInstance())){
                    //Show Password
                    binding.passwordNewuser.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    //Hide Password
                    binding.passwordNewuser.setTransformationMethod(PasswordTransformationMethod.getInstance());

                }
            }
        });

    }
    private void onclickondateofbirth() {
        binding.dateofbirthNewuser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                        binding.dateofbirthNewuser.setText(i + "-" + (i1+1) +"-" +i2);
                        bottomSheetDialog.dismiss();         //Hide Bottom Sheet Dialog
                    }
                });

                bottomSheetDialog.setContentView(sheetview); //Set Content View To Bottom Sheet Dialog
                bottomSheetDialog.show();                    //Show Bottom Sheet Dialog
            }
        });
    }

    private void inti() {
        //Initialize medicalSystemViewModel
        medicalSystemViewModel = new ViewModelProvider(requireActivity()).get(MedicalSystemViewModel.class);
        //Initialize bottomSheetDialog
        bottomSheetDialog = new BottomSheetDialog(getContext(),R.style.AppBottomSheetDialogTheme);
        //Initialize sheetview
        sheetview = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.datepickerr,
                (ViewGroup) view.findViewById(R.id.bottomsheet3));
        //Initialize calendarView
        calendarView = sheetview.findViewById(R.id.calender_datepicker);
    }


    private void editGenderSpinner() {
        final List<String> gendersList = new ArrayList<String>(Arrays.asList(getActivity().getResources().getStringArray(R.array.genders)));
        final MyArrayAdapter spinnerarrayadaptergender = new MyArrayAdapter(getActivity(), R.layout.spinner_item,gendersList){
            @Override
            public boolean isEnabled(int position) {
                if(position == 0)
                {
                    return false; // Disable the first item from Spinner _ First item will be use for hint
                }
                else
                {
                    return true;
                }
            }
        };

        spinnerarrayadaptergender.setDropDownViewResource(R.layout.spinner_item2); //Set Drop Down View Resource To Spinner
        binding.genderNewuser.setAdapter(spinnerarrayadaptergender);                //Set Spinner Adapter
    }
    private void editSpecialistSpinner() {
        final List<String> spicaialistsList = new ArrayList<String>(Arrays.asList(getActivity().getResources().getStringArray(R.array.Specialist)));
        final MyArrayAdapter spinnerarrayadapterspicaialist = new MyArrayAdapter(getActivity(), R.layout.spinner_item,spicaialistsList){
            @Override
            public boolean isEnabled(int position) {
                if(position == 0)
                {
                    return false; // Disable the first item from Spinner _ First item will be use for hint
                }
                else
                {
                    return true;
                }
            }
        };

        spinnerarrayadapterspicaialist.setDropDownViewResource(R.layout.spinner_item2);       //Set Drop Down View Resource To Spinner
        binding.specialistNewuser.setAdapter(spinnerarrayadapterspicaialist);                 //Set Spinner Adapter
    }

    private void editStatuesSpinner() {
        final List<String> statuesList = new ArrayList<String>(Arrays.asList(getActivity().getResources().getStringArray(R.array.Status)));
        final MyArrayAdapter spinnerarrayadapterstatues = new MyArrayAdapter(getActivity(), R.layout.spinner_item,statuesList){
            @Override
            public boolean isEnabled(int position) {
                if(position == 0)
                {
                    return false; // Disable the first item from Spinner _ First item will be use for hint
                }
                else
                {
                    return true;
                }
            }
        };

        spinnerarrayadapterstatues.setDropDownViewResource(R.layout.spinner_item2);//Set Drop Down View Resource To Spinner
        binding.statuesNewuser.setAdapter(spinnerarrayadapterstatues);             //Set Spinner Adapter
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
}