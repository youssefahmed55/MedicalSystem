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
import com.example.medicalsystem.adapters.MyRecycleAdapterSelectHospitalEmployee;
import com.example.medicalsystem.databinding.FragmentSelectEmployeeManagerBinding;
import com.example.medicalsystem.pojo.HospitalEmployeeModle;

import com.example.medicalsystem.pojo.usersbytype.UserItem;
import com.example.medicalsystem.singleliveevent.EventHandler;
import com.example.medicalsystem.singleliveevent.EventObserver;
import com.example.medicalsystem.viewmodel.MedicalSystemViewModel;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectEmployeeManagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectEmployeeManagerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SelectEmployeeManagerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectDoctorManagerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectEmployeeManagerFragment newInstance(String param1, String param2) {
        SelectEmployeeManagerFragment fragment = new SelectEmployeeManagerFragment();
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
    private FragmentSelectEmployeeManagerBinding binding;
    private View view;
    private ArrayList<HospitalEmployeeModle> hospitalEmployeeModleArrayList;
    private MyRecycleAdapterSelectHospitalEmployee myRecycleAdapterSelectHospitalEmployee;
    private MedicalSystemViewModel medicalSystemViewModel;
    private ArrayList<TextView> textViews;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSelectEmployeeManagerBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        inti();                            //Initialize variables
        setOnClickOnCloseIcon();           //Set On Click On Close Icon
        observeUsers();                    //Observe Users
        observeUsersByType("All");         //Observe Users By Type
        onClickOnAll();                    //Set On Click On (All) TextView
        onClickOnAnalysis();               //Set On Click On (Analysis) TextView
        onClickOnHR();                     //Set On Click On (HR) TextView
        onClickOnNurse();                  //Set On Click On (Nurse) TextView
        onClickOnDoctor();                 //Set On Click On (Doctor) TextView
        onClickOnItemOfRecycle();          //Set On Click On Any Item From Recycler
        onSearchTextListener();            //Set On Text Changed Listener
        onClickOnItemOfRecycle();          //Set On Click On Any Item From Recycler
        setOnClickOnSelectEmployeeButton();//Set On Click On Select Employee Button




        return view;
    }

    private void observeUsers() {
        medicalSystemViewModel.getSingleLiveEventUsers().observe(getViewLifecycleOwner() , new EventObserver<ArrayList<UserItem>>(new EventHandler<ArrayList<UserItem>>() {
            @Override
            public void onEventUnHandled(ArrayList<UserItem> object) {
                hospitalEmployeeModleArrayList.clear();
                for(UserItem userItem : object){
                    hospitalEmployeeModleArrayList.add(new HospitalEmployeeModle(userItem,true,false));
                }

                setAdapter(hospitalEmployeeModleArrayList);  //Set Adapter
            }
        }));
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
    private void onClickOnAll() {
        binding.allSelectemployeemanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeColorAndBackgroundOfTextViews(binding.allSelectemployeemanager); //Change Color And Background Of TextView
                observeUsersByType("All"); //Observe Users By Type
            }
        });
    }

    private void onClickOnAnalysis() {
        binding.analysisEmployeeSelectemployeemanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeColorAndBackgroundOfTextViews(binding.analysisEmployeeSelectemployeemanager); //Change Color And Background Of TextView
                observeUsersByType("analysis"); //Observe Users By Type
            }
        });
    }

    private void onClickOnHR() {
        binding.hrSelectemployeemanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeColorAndBackgroundOfTextViews(binding.hrSelectemployeemanager); //Change Color And Background Of TextView
                observeUsersByType("hr"); //Observe Users By Type
            }
        });
    }

    private void onClickOnNurse() {
        binding.nurseSelectemployeemanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeColorAndBackgroundOfTextViews(binding.nurseSelectemployeemanager); //Change Color And Background Of TextView
                observeUsersByType("nurse"); //Observe Users By Type
            }
        });
    }
    private void onClickOnDoctor() {
        binding.doctorSelectemployeemanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangeColorAndBackgroundOfTextViews(binding.doctorSelectemployeemanager); //Change Color And Background Of TextView
                observeUsersByType("doctor"); //Observe Users By Type
            }
        });

    }

    private void setOnClickOnCloseIcon() {
        binding.closeSelectemployeemanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
            }
        });
    }

    private void onSearchTextListener() {
        binding.edittextsearchSelectemployeemanager.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().isEmpty()){
                    setAdapter(hospitalEmployeeModleArrayList);                                       //Set Adapter
                }else {
                    setAdapter(getsearchitem(charSequence.toString(),hospitalEmployeeModleArrayList));//Set Adapter
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }
    private ArrayList<HospitalEmployeeModle> getsearchitem(String s, ArrayList<HospitalEmployeeModle> s2) {

        ArrayList<HospitalEmployeeModle> arrayList1 = new ArrayList<>();
        if (!s.equals(""))
            for (HospitalEmployeeModle e : s2) {
                if (e.getUserItem().getFirstName().toLowerCase().charAt(0) == s.toLowerCase().charAt(0))
                    if (e.getUserItem().getFirstName().toLowerCase().contains(s.toLowerCase())) {
                        arrayList1.add(e);
                    }
            }
        return arrayList1;
    }

    private void observeUsersByType(String type) {
        medicalSystemViewModel.getDatafromUsersResponseByType(type);

    }
    private void setOnClickOnSelectEmployeeButton() {
        binding.selectemployeebuttonSelectemployeemanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(myRecycleAdapterSelectHospitalEmployee.getArrayList() != null) {
                    ArrayList<HospitalEmployeeModle> arrayListt = myRecycleAdapterSelectHospitalEmployee.getArrayList();
                    for (HospitalEmployeeModle hospitalEmployeeModle : arrayListt){
                        if(hospitalEmployeeModle.isChecked()){
                            Navigation.findNavController(view).getPreviousBackStackEntry().getSavedStateHandle().set("key",hospitalEmployeeModle); //Save Data To Previous Fragment
                            Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
                            break;
                        }

                    }
                }
            }
        });
    }

    private void onClickOnItemOfRecycle() {
        myRecycleAdapterSelectHospitalEmployee.setOnClickListeners(new MyRecycleAdapterSelectHospitalEmployee.OnmyClickListenerrr() {
            @Override
            public void onclick2(HospitalEmployeeModle hospitalEmployeeModle) {
                for(int i = 0 ; i < hospitalEmployeeModleArrayList.size() ;i++){
                    if(hospitalEmployeeModleArrayList.get(i).getUserItem().getId() != hospitalEmployeeModle.getUserItem().getId()){
                        hospitalEmployeeModleArrayList.get(i).setChecked(false);
                    }else {
                        hospitalEmployeeModleArrayList.get(i).setChecked(true);
                    }
                }
                myRecycleAdapterSelectHospitalEmployee.notifyDataSetChanged();
            }
        });
    }

    private void setAdapter(ArrayList<HospitalEmployeeModle> arrayList) {
        myRecycleAdapterSelectHospitalEmployee.setArrayList(arrayList);                         //Set Array to Adapter
        binding.recycleSelectemployeemanager.setAdapter(myRecycleAdapterSelectHospitalEmployee);//Set Adapter to Recycler
    }



    private void inti() {
        //Initialize hospitalEmployeeModleArrayList
        hospitalEmployeeModleArrayList = new ArrayList<>();
        //Initialize textViews
        textViews = new ArrayList<>();
        textViews.add(binding.allSelectemployeemanager);
        textViews.add(binding.doctorSelectemployeemanager);
        textViews.add(binding.nurseSelectemployeemanager);
        textViews.add(binding.hrSelectemployeemanager);
        textViews.add(binding.analysisEmployeeSelectemployeemanager);
        //Initialize myRecycleAdapterSelectHospitalEmployee
        myRecycleAdapterSelectHospitalEmployee = new MyRecycleAdapterSelectHospitalEmployee(getActivity());
        //Initialize medicalSystemViewModel
        medicalSystemViewModel = new ViewModelProvider(requireActivity()).get(MedicalSystemViewModel.class);
    }
}