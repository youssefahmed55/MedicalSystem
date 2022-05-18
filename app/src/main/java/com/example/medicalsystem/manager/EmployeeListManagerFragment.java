package com.example.medicalsystem.manager;


import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.medicalsystem.R;
import com.example.medicalsystem.adapters.MyRecycleAdapterHospitalEmployee;

import com.example.medicalsystem.databinding.FragmentEmployeeListManagerBinding;

import com.example.medicalsystem.pojo.usersbytype.UserItem;
import com.example.medicalsystem.singleliveevent.EventHandler;
import com.example.medicalsystem.singleliveevent.EventObserver;
import com.example.medicalsystem.viewmodel.MedicalSystemViewModel;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EmployeeListManagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmployeeListManagerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EmployeeListManagerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EmployeeListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EmployeeListManagerFragment newInstance(String param1, String param2) {
        EmployeeListManagerFragment fragment = new EmployeeListManagerFragment();
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
    private FragmentEmployeeListManagerBinding binding;
    private View view;
    private ArrayList<UserItem> userItemArrayList;
    private ArrayList<TextView> textViews;
    private MyRecycleAdapterHospitalEmployee myRecycleAdapterHospitalEmployee;
    private MedicalSystemViewModel medicalSystemViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentEmployeeListManagerBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        inti();                    //Initialize variables
        observeUsers();            //Observe Users
        observeUsersByType("All"); //Observe Users By Type
        onClickOnBackIcon();       //Set On Click On Back Icon
        onClickOnItemOFRecycler(); //Set On Click On Any Item From Recycler
        onClickOnAddIcon();        //Set On Click On Add Icon
        onClickOnAll();            //Set On Click On (All) TextView
        onClickOnDoctor();         //Set On Click On (Doctor) TextView
        onClickOnNurse();          //Set On Click On (Nurse) TextView
        onClickOnHR();             //Set On Click On (HR) TextView
        onClickOnAnalysis();       //Set On Click On (Analysis) TextView
        onTextChangeInEditText();  //Set On Text Changed Listener

        return view;
    }

    private void onTextChangeInEditText() {
        binding.edittextsearchEmployeelistmanagerfragment.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().isEmpty()){
                    setAdapter(userItemArrayList);                                       //Set Adapter
                }else {
                    setAdapter(getsearchitem(charSequence.toString(),userItemArrayList));//Set Adapter
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void onClickOnAll() {
        binding.allEmployeelistmanagerfragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeColorAndBackgroundOfTextViews(binding.allEmployeelistmanagerfragment); //Change Color And Background Of TextView
                observeUsersByType("All");  //Observe Users By Type
            }
        });
    }

    private void onClickOnAnalysis() {
        binding.analysisEmployeeEmployeelistmanagerfragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeColorAndBackgroundOfTextViews(binding.analysisEmployeeEmployeelistmanagerfragment); //Change Color And Background Of TextView
                observeUsersByType("analysis"); //Observe Users By Type
            }
        });
    }

    private void onClickOnHR() {
        binding.hrEmployeelistmanagerfragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeColorAndBackgroundOfTextViews(binding.hrEmployeelistmanagerfragment); //Change Color And Background Of TextView
                observeUsersByType("hr"); //Observe Users By Type
            }
        });
    }

    private void onClickOnNurse() {
        binding.nurseEmployeelistmanagerfragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeColorAndBackgroundOfTextViews(binding.nurseEmployeelistmanagerfragment); //Change Color And Background Of TextView
                observeUsersByType("nurse"); //Observe Users By Type
            }
        });
    }
    private void onClickOnDoctor() {
        binding.doctorEmployeelistmanagerfragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeColorAndBackgroundOfTextViews(binding.doctorEmployeelistmanagerfragment); //Change Color And Background Of TextView
                observeUsersByType("doctor"); //Observe Users By Type
            }
        });

    }
    private void observeUsers() {
        medicalSystemViewModel.getSingleLiveEventUsers().observe(getViewLifecycleOwner(),new EventObserver<ArrayList<UserItem>>(new EventHandler<ArrayList<UserItem>>() {
            @Override
            public void onEventUnHandled(ArrayList<UserItem> object) {
                userItemArrayList.clear();
                userItemArrayList.addAll(object);
                setAdapter(userItemArrayList);    //Set Adapter
            }
        }));
    }

    private void setAdapter(ArrayList<UserItem> arrayList){
        myRecycleAdapterHospitalEmployee.setArrayList(arrayList);                                //Set Array to Adapter
        binding.recycleEmployeelistmanagerfragment.setAdapter(myRecycleAdapterHospitalEmployee); //Set Adapter to Recycler
    }


    private void observeUsersByType(String type) {
        medicalSystemViewModel.getDatafromUsersResponseByType(type);
    }

    private void ChangeColorAndBackgroundOfTextViews(TextView textVieww) {
        for (TextView textView : textViews){
            if (textView.getText().toString().equals(textVieww.getText().toString())){
                textView.setBackgroundResource(R.drawable.shape4);
                textView.setTextColor(Color.WHITE);
            }else {
                textView.setBackgroundResource(R.drawable.shape10);
                textView.setTextColor(Color.BLACK);
            }

        }
    }

    private void onClickOnAddIcon() {
        binding.addemployeebuttonEmployeelistmanagerfragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_employeeListManagerFragment_to_newUserFragment);
            }
        });

    }

    private void onClickOnBackIcon() {
        binding.backEmployeelistmanagerfragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
            }
        });
    }

    private void onClickOnItemOFRecycler() {
        myRecycleAdapterHospitalEmployee.setOnClickListeners(new MyRecycleAdapterHospitalEmployee.OnmyClickListenerrr() {
            @Override
            public void onclick2(int id) {
                Bundle bundle = new Bundle();
                bundle.putInt("userId",id);
                Navigation.findNavController(view).navigate(R.id.action_employeeListManagerFragment_to_employeeProfileManagerFragment,bundle);
            }
        });
    }

    private void inti() {
        //Initialize userItemArrayList
        userItemArrayList = new ArrayList<>();
        //Initialize textViews
        textViews = new ArrayList<>();
        textViews.add(binding.allEmployeelistmanagerfragment);
        textViews.add(binding.doctorEmployeelistmanagerfragment);
        textViews.add(binding.nurseEmployeelistmanagerfragment);
        textViews.add(binding.hrEmployeelistmanagerfragment);
        textViews.add(binding.analysisEmployeeEmployeelistmanagerfragment);
        //Initialize myRecycleAdapterHospitalEmployee
        myRecycleAdapterHospitalEmployee = new MyRecycleAdapterHospitalEmployee(getActivity());
        //Initialize medicalSystemViewModel
        medicalSystemViewModel = new ViewModelProvider(requireActivity()).get(MedicalSystemViewModel.class);
    }


    private ArrayList<UserItem> getsearchitem(String s, ArrayList<UserItem> s2) {

        ArrayList<UserItem> arrayList1 = new ArrayList<>();
        if (!s.equals(""))
            for (UserItem e : s2) {
                if (e.getFirstName().toLowerCase().charAt(0) == s.toLowerCase().charAt(0))
                    if (e.getFirstName().toLowerCase().contains(s.toLowerCase())) {
                        arrayList1.add(e);
                    }
            }
        return arrayList1;
    }

}