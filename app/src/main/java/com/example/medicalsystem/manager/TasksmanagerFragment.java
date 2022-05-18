package com.example.medicalsystem.manager;

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

import com.example.medicalsystem.adapters.MyRecycleAdapterTasks;
import com.example.medicalsystem.databinding.FragmentTasksmanagerBinding;

import com.example.medicalsystem.pojo.task.TaskResponseData;
import com.example.medicalsystem.pojo.task.TasksResponseModel;
import com.example.medicalsystem.singleliveevent.EventHandler;
import com.example.medicalsystem.singleliveevent.EventObserver;
import com.example.medicalsystem.viewmodel.MedicalSystemViewModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.ArrayList;

import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TasksmanagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TasksmanagerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TasksmanagerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TasksmanagerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TasksmanagerFragment newInstance(String param1, String param2) {
        TasksmanagerFragment fragment = new TasksmanagerFragment();
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
    private FragmentTasksmanagerBinding binding;
    private View view;
    private MyRecycleAdapterTasks myRecycleAdapterTasks;
    private View sheetview;
    private BottomSheetDialog bottomSheetDialog;
    private CalendarView calendarView;
    private MedicalSystemViewModel medicalSystemViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTasksmanagerBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        inti();                             //Initialize variables
        observeTasks();                     //Observe Tasks
        checkDateTextFromSharedPrefrence(); //Check Date From SharedPreferences
        onClickOnDate();                    //Set On Click On Date TextView
        onClickOnBackIcon();                //Set On Click On Back Icon
        onPressBack();                      //Set On Press On Back
        onClickOnAddIcon();                 //Set On Click On Add Icon
        onClickOnItemOFRecycler();          //Set On Click On Any Item From Recycler


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

    private void observeTasks() {
        medicalSystemViewModel.getSingleLiveEventTasks().observe(getViewLifecycleOwner(),new EventObserver<TasksResponseModel>(new EventHandler<TasksResponseModel>() {
            @Override
            public void onEventUnHandled(TasksResponseModel object) {
                if(object.getStatus() == 1){
                    ArrayList<TaskResponseData> taskResponseData = new ArrayList<>();
                    taskResponseData.addAll(object.getData());
                    setAdapter(taskResponseData);         //Set Adapter
                }else{
                    Toasty.error(getActivity(), object.getMessage(), Toast.LENGTH_SHORT, true).show();
                }
            }
        }));
    }

    private void checkDateTextFromSharedPrefrence() {
        medicalSystemViewModel.getDate();
        medicalSystemViewModel.getMutableLiveDataDate().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                binding.dateTasksmanager.setText(s); //Set date To TextView
                observeTasksByDate(s);               //Observe Tasks By Date

            }
        });

    }

    private void setAdapter(ArrayList<TaskResponseData> arrayList) {
        myRecycleAdapterTasks.setArrayList(arrayList);                //Set Array to Adapter
        binding.recycleTasksmanager.setAdapter(myRecycleAdapterTasks);//Set Adapter to Recycler
    }

    private void observeTasksByDate(String dateText) {
        medicalSystemViewModel.getDatafromTasksByDateResponse(dateText);
    }
    private void onClickOnDate() {
        binding.dateTasksmanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calendarView.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
                    @Override
                    public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                        String dateText = i + "-" + (i1+1) +"-" +i2;
                        medicalSystemViewModel.saveDate(dateText);    //Save Date In SharedPreferences
                        binding.dateTasksmanager.setText(dateText);   //Set Text of TextView
                        observeTasksByDate(dateText);                 //Observe Tasks By Date
                        bottomSheetDialog.dismiss();                  //Hide Bottom Sheet Dialog

                    }
                });

                bottomSheetDialog.setContentView(sheetview);         //Set Content View to Bottom Sheet Dialog
                bottomSheetDialog.show();                            //Show Bottom Sheet Dialog
            }
        });
    }



    private void onClickOnItemOFRecycler() {
         myRecycleAdapterTasks.setOnmyClickListenerrr(new MyRecycleAdapterTasks.OnmyClickListenerrr() {
             @Override
             public void onclick2(int id) {
                 Bundle bundle = new Bundle();
                 bundle.putInt("taskId",id);
                 Navigation.findNavController(view).navigate(R.id.action_tasksmanagerFragment_to_taskDetailsManagerFragment,bundle); //Send Task Id to Task Details Fragment
             }
         });
    }

    private void onClickOnAddIcon() {
        binding.addTasksmanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).navigate(R.id.action_tasksmanagerFragment_to_createTaskManagerFragment);
            }
        });
    }

    private void onClickOnBackIcon() {
        binding.backTasksmanager.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
            }
        });
    }

    private void inti() {
        //Initialize medicalSystemViewModel
        medicalSystemViewModel = new ViewModelProvider(requireActivity()).get(MedicalSystemViewModel.class);
        //Initialize myRecycleAdapterTasks
        myRecycleAdapterTasks = new MyRecycleAdapterTasks();
        //Initialize bottomSheetDialog
        bottomSheetDialog = new BottomSheetDialog(getContext(),R.style.AppBottomSheetDialogTheme);
        //Initialize sheetview
        sheetview = LayoutInflater.from(getActivity().getApplicationContext()).inflate(R.layout.datepickerr,
                (ViewGroup) view.findViewById(R.id.bottomsheet3));
        //Initialize calendarView
        calendarView = sheetview.findViewById(R.id.calender_datepicker);
    }
}