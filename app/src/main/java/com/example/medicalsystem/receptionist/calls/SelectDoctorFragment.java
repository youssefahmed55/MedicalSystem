package com.example.medicalsystem.receptionist.calls;


import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.medicalsystem.adapters.MyRecycleAdapterSelectHospitalEmployee;
import com.example.medicalsystem.databinding.FragmentSelectDoctorBinding;
import com.example.medicalsystem.pojo.HospitalEmployeeModle;
import com.example.medicalsystem.pojo.usersbytype.UserItem;
import com.example.medicalsystem.singleliveevent.EventHandler;
import com.example.medicalsystem.singleliveevent.EventObserver;
import com.example.medicalsystem.viewmodel.MedicalSystemViewModel;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectDoctorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectDoctorFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SelectDoctorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectDoctorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectDoctorFragment newInstance(String param1, String param2) {
        SelectDoctorFragment fragment = new SelectDoctorFragment();
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
    private FragmentSelectDoctorBinding binding;
    private View view;
    private ArrayList<HospitalEmployeeModle> doctormodelsarray;
    private MyRecycleAdapterSelectHospitalEmployee myRecycleAdapterSelectHospitalEmployee;
    private MedicalSystemViewModel medicalSystemViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSelectDoctorBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        inti();                         //Initialize variables
        onClickOnCloseIcon();           //Set On Click On Close Icon
        onClickOnSelectDoctorButton();  //Set On Click On Select Doctor Button
        ObserveUsers("doctor");    //Observe Doctors
        onClickOnItemOfRecycle();       //Set On Click On Any Item From Recycler
        onSearchTextListener();         //Set On Text Changed Listener
        return view;
    }

    private void onSearchTextListener() {
        binding.edittextsearchSelectdoctor.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                  if(charSequence.toString().isEmpty()){
                      setAdapter(doctormodelsarray);                                       //Set Adapter
                  }else {
                     setAdapter(getsearchitem(charSequence.toString(),doctormodelsarray)); //Set Adapter
                  }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setAdapter(ArrayList<HospitalEmployeeModle> arrayList) {
        myRecycleAdapterSelectHospitalEmployee.setArrayList(arrayList);                //Set Array To Adapter
        binding.recycleSelectdoctor.setAdapter(myRecycleAdapterSelectHospitalEmployee);//Set Adapter To Recycler
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

    private void onClickOnItemOfRecycle() {
        myRecycleAdapterSelectHospitalEmployee.setOnClickListeners(new MyRecycleAdapterSelectHospitalEmployee.OnmyClickListenerrr() {
            @Override
            public void onclick2(HospitalEmployeeModle hospitalEmployeeModle) {
                for(int i = 0 ; i < doctormodelsarray.size() ;i++){
                    if(doctormodelsarray.get(i).getUserItem().getId() != hospitalEmployeeModle.getUserItem().getId()){
                        doctormodelsarray.get(i).setChecked(false);
                    }else {
                        doctormodelsarray.get(i).setChecked(true);
                    }
                }
                myRecycleAdapterSelectHospitalEmployee.notifyDataSetChanged();
            }
        });
    }

    private void ObserveUsers(String type) {
        medicalSystemViewModel.getDatafromUsersResponseByType(type);
        medicalSystemViewModel.getSingleLiveEventUsers().observe(getViewLifecycleOwner() , new EventObserver<ArrayList<UserItem>>(new EventHandler<ArrayList<UserItem>>() {
            @Override
            public void onEventUnHandled(ArrayList<UserItem> object) {
                doctormodelsarray.clear();
                for(UserItem userItem : object){
                    doctormodelsarray.add(new HospitalEmployeeModle(userItem,true,false));
                }

                setAdapter(doctormodelsarray);   //Set Adapter
            }
        }));
    }

    private void onClickOnSelectDoctorButton() {
        binding.selectdoctorbuttonSelectdoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myRecycleAdapterSelectHospitalEmployee.getArrayList() != null) {
                    ArrayList<HospitalEmployeeModle> arrayListt = myRecycleAdapterSelectHospitalEmployee.getArrayList();
                    for (HospitalEmployeeModle hospitalEmployeeModle : arrayListt){
                        if(hospitalEmployeeModle.isChecked()){
                            Navigation.findNavController(view).getPreviousBackStackEntry().getSavedStateHandle().set("key",hospitalEmployeeModle); //Save Data To Previous Fragment
                            Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
                        }

                    }
                }

            }
        });

    }

    private void onClickOnCloseIcon() {
        binding.closeSelectdoctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack();  //Back to previous Fragment
            }
        });

    }


    private void inti() {
        //Initialize doctormodelsarray
        doctormodelsarray = new ArrayList<>();
        //Initialize myRecycleAdapterSelectHospitalEmployee
        myRecycleAdapterSelectHospitalEmployee = new MyRecycleAdapterSelectHospitalEmployee(getActivity());
        //Initialize medicalSystemViewModel
        medicalSystemViewModel = new ViewModelProvider(requireActivity()).get(MedicalSystemViewModel.class);
    }
}