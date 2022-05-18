package com.example.medicalsystem.reports;

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
import com.example.medicalsystem.adapters.MyRecycleAdapterReports;
import com.example.medicalsystem.databinding.FragmentReportBinding;
import com.example.medicalsystem.pojo.report.ReportResponseData;
import com.example.medicalsystem.pojo.report.ReportResponseModel;
import com.example.medicalsystem.singleliveevent.EventHandler;
import com.example.medicalsystem.singleliveevent.EventObserver;
import com.example.medicalsystem.viewmodel.MedicalSystemViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReportFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReportFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReportFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReportFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReportFragment newInstance(String param1, String param2) {
        ReportFragment fragment = new ReportFragment();
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
    private FragmentReportBinding binding;
    private View view;
    private View sheetview;
    private BottomSheetDialog bottomSheetDialog;
    private CalendarView calendarView;
    private MedicalSystemViewModel medicalSystemViewModel;
    private MyRecycleAdapterReports myRecycleAdapterReports;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentReportBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        inti();                              //Initialize variables
        observeReports();                    //Observe Reports
        checkDateTextFromSharedPrefrence();  //Check Date From SharedPrefernces
        onClickOnDate();                     //Set On Click On Date TextView
        onClickOnBackIcon();                 //Set On Click On Back Icon
        onPressBack();                       //Set On Press Back
        onClickOnAddIcon();                  //Set On Click On Add Icon
        onClickOnItemOFRecycler();           //Set On Click On Any Item From Recycler

        return view;
    }

    private void onPressBack() {
        OnBackPressedCallback callback = new OnBackPressedCallback(true /* enabled by default */) {
            @Override
            public void handleOnBackPressed() {
                medicalSystemViewModel.deleteDate();                 //Delete Date From SharedPrefrences
                Navigation.findNavController(view).popBackStack();   //Back to previous Fragment
            }
        };
        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), callback);
    }

    private void observeReports() {
        medicalSystemViewModel.getSingleLiveEventReports().observe(getViewLifecycleOwner(),new EventObserver<ReportResponseModel>(new EventHandler<ReportResponseModel>() {
            @Override
            public void onEventUnHandled(ReportResponseModel object) {
                if(object.getStatus() == 1){
                    ArrayList<ReportResponseData> reportResponseData = new ArrayList<>();
                    reportResponseData.addAll(object.getData());
                    setAdapter(reportResponseData);            //Set Adapter
                }else{
                    Toasty.error(getActivity(), object.getMessage(), Toast.LENGTH_SHORT, true).show();
                }
            }
        }));
    }

    private void checkDateTextFromSharedPrefrence() {
        medicalSystemViewModel.getDate();   //Get Date From SharedPreferences
        medicalSystemViewModel.getMutableLiveDataDate().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
               binding.dateReport.setText(s);  //Set Text to Date TextView
               observeReportsByDate(s);        //Observe Reports By Date

            }
        });

    }

    private void setAdapter(ArrayList<ReportResponseData> arrayList) {
        myRecycleAdapterReports.setArrayList(arrayList);           //Set Array of Adapter
        binding.recycleReport.setAdapter(myRecycleAdapterReports); //Set Adapter of Recycler
    }

    private void observeReportsByDate(String dateText) {
          medicalSystemViewModel.getDatafromReportsByDateResponse(dateText);
    }
    private void onClickOnDate() {
        binding.dateReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                        String dateText = i + "-" + (i1+1) +"-" +i2;
                        medicalSystemViewModel.saveDate(dateText);  //Save Date in SharedPreferences
                        binding.dateReport.setText(dateText);       //Set Text To date TextView
                        observeReportsByDate(dateText);             //Observe Reports By Date
                        bottomSheetDialog.dismiss();                //Hide Bottom Sheet Dialog

                    }
                });

                bottomSheetDialog.setContentView(sheetview);//Set Content View To Bottom Sheet Dialog
                bottomSheetDialog.show();                   //Show Bottom Sheet Dialog
            }
        });
    }



    private void onClickOnItemOFRecycler() {
       myRecycleAdapterReports.setOnmyClickListenerrr(new MyRecycleAdapterReports.OnmyClickListenerrr() {
           @Override
           public void onclick2(int id) {
               Bundle bundle = new Bundle();
               bundle.putInt("reportId" ,id);
               //Send Report ID To Report Details Fragment
               Navigation.findNavController(view).navigate(R.id.action_reportFragment_to_reportDetailsFragment,bundle);
           }
       });
    }

    private void onClickOnAddIcon() {
        binding.addReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_reportFragment_to_createReportFragment);
            }
        });
    }

    private void onClickOnBackIcon() {
        binding.backReport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
            }
        });
    }

    private void inti() {
        //Initialize medicalSystemViewModel
        medicalSystemViewModel = new ViewModelProvider(requireActivity()).get(MedicalSystemViewModel.class);
        //Initialize myRecycleAdapterReports
        myRecycleAdapterReports = new MyRecycleAdapterReports();
        //Initialize bottomSheetDialog
        bottomSheetDialog = new BottomSheetDialog(getContext(),R.style.AppBottomSheetDialogTheme);
        //Initialize sheetview
        sheetview = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.datepickerr,
                (ViewGroup) view.findViewById(R.id.bottomsheet3));
        //Initialize calendarView
        calendarView = sheetview.findViewById(R.id.calender_datepicker);
    }
}