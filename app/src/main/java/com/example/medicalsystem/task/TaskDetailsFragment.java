package com.example.medicalsystem.task;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.medicalsystem.R;
import com.example.medicalsystem.adapters.MyRecycleAdapterCheckedToDoTask;
import com.example.medicalsystem.databinding.FragmentTaskDetailsBinding;
import com.example.medicalsystem.pojo.MessageResponseModel;
import com.example.medicalsystem.pojo.task.showtask.ShowTaskData;
import com.example.medicalsystem.singleliveevent.EventHandler;
import com.example.medicalsystem.singleliveevent.EventObserver;
import com.example.medicalsystem.viewmodel.MedicalSystemViewModel;


import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TaskDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TaskDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskDetailsFragment newInstance(String param1, String param2) {
        TaskDetailsFragment fragment = new TaskDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private int taskId;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            taskId =  getArguments().getInt("taskId");
        }
    }
    private FragmentTaskDetailsBinding binding;
    private View view;
    private MyRecycleAdapterCheckedToDoTask myRecycleAdapterCheckedToDoTask;
    private Configuration config;
    private MedicalSystemViewModel medicalSystemViewModel;
    private final String PROFILE_IMAGE = "https://png.pngtree.com/png-vector/20191027/ourlarge/pngtree-avatar-vector-icon-white-background-png-image_1884971.jpg";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_details, container, false);
        binding.setLifecycleOwner(this);
        view = binding.getRoot();
        inti();                       //initialize variables
        setProfileImage();            //Set Profile Image
        checkIfRTL();                 //Check if it RTL to Change Background
        if(taskId != 0) {             //if Taskid not null not equal 0
            observeShowTaskResponse();//Observe Data Of Task By Id
            onClickOnExecutionTask(); //Set on Click on Execution Button
        }
        return view;
    }

    private void setProfileImage() {
        Glide.with(this).load(PROFILE_IMAGE).into(binding.profileimageTaskdetails); //Load Image From Link
    }

    private void onClickOnExecutionTask() {
        binding.ExecutiontaskTaskdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                observeExecutionResponse();  //Observe Response of Execution
            }
        });
    }

    private void observeExecutionResponse() {
        medicalSystemViewModel.getDatafromExecutionResponse(taskId);
        medicalSystemViewModel.getSingleLiveEventExecution().observe(getViewLifecycleOwner(),new EventObserver<MessageResponseModel>(new EventHandler<MessageResponseModel>() {
            @Override
            public void onEventUnHandled(MessageResponseModel object) {
                if(object.getStatus() == 1){
                    Toasty.success(getActivity(), getString(R.string.Task_Finished_Successfully), Toast.LENGTH_SHORT, true).show();
                    Navigation.findNavController(view).popBackStack();   //Back to previous Fragment
                }else{
                    Toasty.error(getActivity(), object.getMessage(), Toast.LENGTH_SHORT, true).show();
                }
            }
        }));
    }

    private void observeShowTaskResponse() {
        medicalSystemViewModel.getDatafromShowTaskResponse(taskId);
        medicalSystemViewModel.getMutableLiveDataShowTask().observe(getViewLifecycleOwner(), new Observer<ShowTaskData>() {
            @Override
            public void onChanged(ShowTaskData showTaskData) {
                myRecycleAdapterCheckedToDoTask.setArrayList(showTaskData.getToDo());   //Set Array of Adapter
                binding.recycleeTaskdetails.setAdapter(myRecycleAdapterCheckedToDoTask);//Set Adapter of Recycler
                binding.setViewModel(medicalSystemViewModel);                           //Set ViewModel for Data Binding
            }
        });
    }

    private void checkIfRTL() {
        if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
            binding.linear1Taskdetails.setBackgroundResource(R.drawable.layer2);
            binding.linear3Taskdetails.setBackgroundResource(R.drawable.layer2);
            binding.lineartodoTaskdetails.setBackgroundResource(R.drawable.layer4);
        }
    }

    private void inti() {
        //initialize myRecycleAdapterCheckedToDoTask
        myRecycleAdapterCheckedToDoTask = new MyRecycleAdapterCheckedToDoTask();
        //initialize medicalSystemViewModel
        medicalSystemViewModel = new ViewModelProvider(requireActivity()).get(MedicalSystemViewModel.class);
        //initialize config
        config = getContext().getResources().getConfiguration();
    }
}