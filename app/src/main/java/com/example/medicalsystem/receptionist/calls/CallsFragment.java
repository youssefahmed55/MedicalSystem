package com.example.medicalsystem.receptionist.calls;


import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CalendarView;
import android.widget.Toast;

import com.example.medicalsystem.R;
import com.example.medicalsystem.adapters.MyRecycleAdapterCalls;
import com.example.medicalsystem.databinding.FragmentCallsBinding;
import com.example.medicalsystem.pojo.calls.callsreceptionist.CallsData;
import com.example.medicalsystem.pojo.calls.callsreceptionist.CallsResponse;

import com.example.medicalsystem.singleliveevent.EventHandler;
import com.example.medicalsystem.singleliveevent.EventObserver;
import com.example.medicalsystem.viewmodel.MedicalSystemViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;


import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link CallsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class CallsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public CallsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment CallsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static CallsFragment newInstance(String param1, String param2) {
        CallsFragment fragment = new CallsFragment();
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
    private FragmentCallsBinding binding;
    private View view;
    private View sheetview;
    private BottomSheetDialog bottomSheetDialog;
    private CalendarView calendarView;
    private MyRecycleAdapterCalls recycleAdapterCalls;
    private MedicalSystemViewModel medicalSystemViewModel;
    private static final String TAG = "CallsFragment";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentCallsBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        inti();                              //Initialize variables
        observeCalls();                      //Observe Calls
        checkDateTextFromSharedPrefrence();  //Check Date From SharedPreference
        onCLickOnBackIcon();                 //Set On Click On Back Icon
        onPressBack();                       //Set On Press On Back
        onClickOnAddIcon();                  //Set On Click On Add Icon
        onClickOnDate();                     //Set On Click On Date TextView
        onClickOnItemOFRecycler();           //Set On Click On Any Item From Recycler

        return view;
    }

    private void onPressBack() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                medicalSystemViewModel.deleteDate();               //Delete Date From SharedPreferences
                Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
    }


    private void observeCalls() {
        medicalSystemViewModel.getSingleLiveEventCalls().observe(getViewLifecycleOwner(), new EventObserver<CallsResponse>(new EventHandler<CallsResponse>() {
            @Override
            public void onEventUnHandled(CallsResponse object) {
                ArrayList<CallsData> arrayList = new ArrayList();
                arrayList.addAll(object.getData());
                recycleAdapterCalls.setArrayList(arrayList);         //Set Array to Adapter
                binding.recycleCalls.setAdapter(recycleAdapterCalls);//Set Adapter to Recycler
                if(arrayList.size() ==0){
                    Toasty.info(getActivity(), getActivity().getString(R.string.Empty_Calls),Toast.LENGTH_SHORT,true).show();
                }
            }
        }));
    }

    private void checkDateTextFromSharedPrefrence() {
        medicalSystemViewModel.getDate();
        medicalSystemViewModel.getMutableLiveDataDate().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                    binding.dateCalls.setText(s);    //Set Date of SharedPreferences In TextView
                    observeCallsByDate(s);           //Observe Response Of Calls By Date

            }
        });

    }


    private void onClickOnItemOFRecycler(){
        recycleAdapterCalls.setOnmyClickListenerrr(new MyRecycleAdapterCalls.OnMyClickListenerrr() {
            @Override
            public void onclick2(CallsData callsData) {
                Bundle bundle = new Bundle();
                bundle.putSerializable("callData", callsData);
                Navigation.findNavController(view).navigate(R.id.action_callsFragment_to_caseDetailsReceptionistFragment,bundle); //Send Call Data To Case Details Fragment
            }
        });


    }

    private void onClickOnDate() {
        binding.dateCalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                        String dateText = i + "-" + (i1+1) +"-" +i2;
                        medicalSystemViewModel.saveDate(dateText);  //Save Date In SharedPreferences
                        binding.dateCalls.setText(dateText);        //Set Date In TextView
                        observeCallsByDate(dateText);               //Observe Response Of Calls By Date
                        bottomSheetDialog.dismiss();                //Hide Bottom Sheet Dialog

                    }
                });

                bottomSheetDialog.setContentView(sheetview);        //Set Content View To Bottom Sheet Dialog
                bottomSheetDialog.show();                           //Show Bottom Sheet Dialog
            }
        });
    }

    private void onClickOnAddIcon() {
        binding.addCalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_callsFragment_to_createCallFragment);
            }
        });
    }

    private void onCLickOnBackIcon() {
        binding.backCalls.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
            }
        });
    }



    private void observeCallsByDate(String date) {
        medicalSystemViewModel.getDatafromCallsByDateResponse(date);
    }

    private void inti() {
        //Initialize medicalSystemViewModel
        medicalSystemViewModel = new ViewModelProvider(requireActivity()).get(MedicalSystemViewModel.class);
        //Initialize recycleAdapterCalls
        recycleAdapterCalls = new MyRecycleAdapterCalls();
        //Initialize bottomSheetDialog
        bottomSheetDialog = new BottomSheetDialog(getContext(),R.style.AppBottomSheetDialogTheme);
        //Initialize sheetview
        sheetview = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.datepickerr,
                (ViewGroup) view.findViewById(R.id.bottomsheet3));
        //Initialize calendarView
        calendarView = sheetview.findViewById(R.id.calender_datepicker);
    }

}