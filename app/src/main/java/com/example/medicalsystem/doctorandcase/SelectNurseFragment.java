package com.example.medicalsystem.doctorandcase;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.medicalsystem.R;
import com.example.medicalsystem.adapters.MyRecycleAdapterSelectHospitalEmployee;
import com.example.medicalsystem.databinding.FragmentSelectNurseBinding;
import com.example.medicalsystem.pojo.HospitalEmployeeModle;
import com.example.medicalsystem.pojo.MessageResponseModel;
import com.example.medicalsystem.pojo.addnurse.AddNurseModleRequest;
import com.example.medicalsystem.pojo.usersbytype.UserItem;
import com.example.medicalsystem.singleliveevent.EventHandler;
import com.example.medicalsystem.singleliveevent.EventObserver;
import com.example.medicalsystem.viewmodel.MedicalSystemViewModel;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SelectNurseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SelectNurseFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public SelectNurseFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment SelectNurseFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static SelectNurseFragment newInstance(String param1, String param2) {
        SelectNurseFragment fragment = new SelectNurseFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private int caseId;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            caseId =  getArguments().getInt("caseId");
        }
    }

    private FragmentSelectNurseBinding binding;
    private View view;
    private ArrayList<HospitalEmployeeModle> nurseModleArrayList;
    private MyRecycleAdapterSelectHospitalEmployee myRecycleAdapterSelectHospitalEmployee;
    private MedicalSystemViewModel medicalSystemViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSelectNurseBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        inti();                             //Initialize variables
        setOnClickOnCloseIcon();            //Set On Click On Close Icon
        ObserveUsers("nurse");         //Observe Users By Type
        onClickOnItemOfRecycle();           //Set On Click On Any Item From Recycler
        onSearchTextListener();             //Set On Text Changed Listener

        if(caseId != 0) {                   //If Case Id Not Null Not Equal 0
        setOnClickOnSelectNurseButton();    //Set On Click On Select Nurse Button
        }





        return view;
    }

    private void onSearchTextListener() {
        binding.edittextsearchSelectnurse.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(charSequence.toString().isEmpty()){
                    setAdapter(nurseModleArrayList);                                        //Set Adapter
                }else {
                    setAdapter(getsearchitem(charSequence.toString(),nurseModleArrayList)); //Set Adapter
                }
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void setAdapter(ArrayList<HospitalEmployeeModle> arrayList) {
        myRecycleAdapterSelectHospitalEmployee.setArrayList(arrayList);                //Set Array to Adapter
        binding.recycleSelectnurse.setAdapter(myRecycleAdapterSelectHospitalEmployee); //Set Adapter to Recycler
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
                for(int i = 0 ; i < nurseModleArrayList.size() ;i++){
                    if(nurseModleArrayList.get(i).getUserItem().getId() != hospitalEmployeeModle.getUserItem().getId()){
                        nurseModleArrayList.get(i).setChecked(false);
                    }else {
                        nurseModleArrayList.get(i).setChecked(true);
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
                nurseModleArrayList.clear();
                for(UserItem userItem : object){
                    nurseModleArrayList.add(new HospitalEmployeeModle(userItem,true,false));
                }

                setAdapter(nurseModleArrayList);       //Set Adapter
            }
        }));
    }

    private void setOnClickOnSelectNurseButton() {
        binding.selectnursebuttonSelectnurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(myRecycleAdapterSelectHospitalEmployee.getArrayList() != null) {
                    ArrayList<HospitalEmployeeModle> arrayListt = myRecycleAdapterSelectHospitalEmployee.getArrayList();
                    for (HospitalEmployeeModle hospitalEmployeeModle : arrayListt){
                        if(hospitalEmployeeModle.isChecked()){
                            //Observe Response Of Add Nurse To Case
                            ObserveAddNurseResponse(new AddNurseModleRequest(caseId,hospitalEmployeeModle.getUserItem().getId()),hospitalEmployeeModle.getUserItem().getFirstName());
                            break;
                        }

                    }
                }

            }
        });

    }

    private void ObserveAddNurseResponse(AddNurseModleRequest addNurseModleRequest , String nurseName) {
        medicalSystemViewModel.getDataFromAddNurseResponse(addNurseModleRequest);
        medicalSystemViewModel.getSingleLiveEventAddNurse().observe(getViewLifecycleOwner(),new EventObserver<MessageResponseModel>(new EventHandler<MessageResponseModel>() {
            @Override
            public void onEventUnHandled(MessageResponseModel object) {
                if (object.getStatus() ==1){
                    Toasty.success(getActivity(), getString(R.string.The_Nurse_Has_Been_Successfully_Added), Toast.LENGTH_SHORT, true).show();
                    Navigation.findNavController(view).getPreviousBackStackEntry().getSavedStateHandle().set("key",nurseName); //Save Data To Previous Fragment
                    Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
                }

            }
        }));
    }

    private void setOnClickOnCloseIcon() {
        binding.closeSelectnurse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
            }
        });

    }


    private void inti() {
        //Initialize nurseModleArrayList
        nurseModleArrayList = new ArrayList<>();
        //Initialize myRecycleAdapterSelectHospitalEmployee
        myRecycleAdapterSelectHospitalEmployee = new MyRecycleAdapterSelectHospitalEmployee(getActivity());
        //Initialize medicalSystemViewModel
        medicalSystemViewModel = new ViewModelProvider(requireActivity()).get(MedicalSystemViewModel.class);
    }
}