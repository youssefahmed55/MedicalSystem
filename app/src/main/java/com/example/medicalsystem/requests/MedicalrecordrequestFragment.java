package com.example.medicalsystem.requests;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;


import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.medicalsystem.R;
import com.example.medicalsystem.adapters.MyRecycleAdapterMedical;
import com.example.medicalsystem.databinding.FragmentMedicalrecordrequestBinding;


import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MedicalrecordrequestFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MedicalrecordrequestFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MedicalrecordrequestFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MedicalrecordrequestFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static MedicalrecordrequestFragment newInstance(String param1, String param2) {
        MedicalrecordrequestFragment fragment = new MedicalrecordrequestFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private String medicalRequests;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            medicalRequests =  getArguments().getString("recordRequests");  //Get Record Requests that Selected Before
        }
        setHasOptionsMenu(true);
    }
    private FragmentMedicalrecordrequestBinding binding;
    private View view;
    private ArrayList<String> arrayList ;
    private final String medicalRecordRequestsArray[] = {"blood pressure","sugar analysis","tempreture","fluid balance","respiratory rate","heart rate"};
    private final int medicalRecordRequestsIdArray[] = {R.id.blood_pressure,R.id.sugar_analysis,R.id.tempreture,R.id.fluid_balance,R.id.respiratory_rate,R.id.heart_rate};
    private MyRecycleAdapterMedical myRecycleAdapterMedical;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMedicalrecordrequestBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        inti();                          //Initialize variables
        setAdapter();                    //Set Adapter

        if (medicalRequests != null){
        appearsSelectedRequested();      //Appears Saved Selected Requested
        }
        onClickOnSaveButton();          //Set On Click On Save Button
        onClickOnDeleteRequest();       //Set On Click On Delete Icon
        onClickOnAddMeasurement();      //Set On Click On Add Measurement TextView
        onclickOnCloseIcon();           //Set On Click On Close Icon

        return view;
    }

    private void onclickOnCloseIcon() {
        binding.closeMedicalrecordrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
            }
        });
    }

    private void appearsSelectedRequested() {
        if(medicalRequests.contains("-")){
            String s[] = medicalRequests.split("-");

            for (int i = 0  ; i < s.length ; i++){
                arrayList.add(""+s[i]);
            }
        }else{
            arrayList.add(medicalRequests);
        }

        myRecycleAdapterMedical.notifyDataSetChanged();
    }

    private void setAdapter() {
        myRecycleAdapterMedical.setArrayList(arrayList);                         //Set Array of Adapter
        binding.recycleMedicalrecordrequest.setAdapter(myRecycleAdapterMedical); //Set Adapter of Recycler
    }

    private void onClickOnAddMeasurement() {
          binding.AddmeasurementMedicalrecordrequest.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  PopupMenu popupMenu = new PopupMenu(getActivity(), binding.AddmeasurementMedicalrecordrequest);

                  // Inflating popup menu from popup_menu.xml file
                  popupMenu.getMenuInflater().inflate(R.menu.popup_menu, popupMenu.getMenu());
                  popupMenu =RemoveSelectedRequests(arrayList,popupMenu);    //Remove Requests that Selected Before
                  popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                      @Override
                      public boolean onMenuItemClick(MenuItem menuItem) {
                          // Toast message on menu item clicked
                          arrayList.add(menuItem.getTitle().toString());
                          myRecycleAdapterMedical.notifyDataSetChanged();
                          return true;
                      }
                  });
                  // Showing the popup menu
                  popupMenu.show();
              }
          });
    }

    private PopupMenu RemoveSelectedRequests(ArrayList<String> arrayList, PopupMenu popupMenu) {
        for(int i = 0 ; i < arrayList.size() ; i++){
            String s = arrayList.get(i);
        for (int j = 0 ; j < medicalRecordRequestsArray.length ; j++){
            if(s.equals(medicalRecordRequestsArray[j])){
                popupMenu.getMenu().removeItem(medicalRecordRequestsIdArray[j]);   //remove item from menu
            }
        }
        }
        return popupMenu ;


    }

    private void onClickOnDeleteRequest() {
        myRecycleAdapterMedical.setOnClickListeners(new MyRecycleAdapterMedical.OnmyClickListenerrr() {
            @Override
            public void onclick2(int pos) {
                arrayList.remove(pos);      //remove it from array
                myRecycleAdapterMedical.notifyDataSetChanged();
            }
        });
    }

    private void onClickOnSaveButton() {
        binding.savebuttonMedicalrecordrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (arrayList.size() > 0) {
                    if(arrayList.contains("sugar analysis" )&& arrayList.contains("blood pressure")){
                    String s = "";
                   for (int i = 0 ; i  < arrayList.size() ; i++){
                        if(i == arrayList.size() - 1)
                        s += arrayList.get(i) ;
                        else
                        s += arrayList.get(i) + "-";

                    }
                    //save data to get it from previous Fragment
                    Navigation.findNavController(view).getPreviousBackStackEntry().getSavedStateHandle().set("keyRecoredRequests", s); //Save Data To Previous Fragment
                    Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
                    }else{
                        Toast.makeText(getActivity(), getString(R.string.sugar_analysis_and_blood_pressure_is_required), Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(getActivity(), getString(R.string.You_didnot_Add_Any_Measurement), Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private void inti() {
        //initialize Arraylist
        arrayList = new ArrayList<>();
        //initialize myRecycleAdapterMedical
        myRecycleAdapterMedical = new MyRecycleAdapterMedical();

    }


}