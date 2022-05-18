package com.example.medicalsystem.manager;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


import com.bumptech.glide.Glide;
import com.example.medicalsystem.R;
import com.example.medicalsystem.adapters.MyRecycleAdapterCheckedToDoTask;
import com.example.medicalsystem.databinding.FragmentTaskDetailsManagerBinding;

import com.example.medicalsystem.pojo.task.showtask.ShowTaskData;

import com.example.medicalsystem.viewmodel.MedicalSystemViewModel;


/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TaskDetailsManagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TaskDetailsManagerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TaskDetailsManagerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TaskDetailsManagerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TaskDetailsManagerFragment newInstance(String param1, String param2) {
        TaskDetailsManagerFragment fragment = new TaskDetailsManagerFragment();
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
    private FragmentTaskDetailsManagerBinding binding;
    private View view;
    private MyRecycleAdapterCheckedToDoTask myRecycleAdapterCheckedToDoTask;
    private Configuration config;
    private MedicalSystemViewModel medicalSystemViewModel;
    private final String PROFILE_IMAGE = "https://png.pngtree.com/png-vector/20191027/ourlarge/pngtree-avatar-vector-icon-white-background-png-image_1884971.jpg";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_task_details_manager, container, false);
        binding.setLifecycleOwner(this);
        view = binding.getRoot();
        inti();                        //Initialize variables
        checkIfRTL();                  //Check If RTL
        setProfileImage();             //Set Profile Image


        if(taskId != 0) {              //If Task Id Is Not Null Not Equal 0
            observeShowTaskResponse(); //Observe Response Of Task Details
        }


        return view;
    }

    private void setProfileImage() {
        Glide.with(this).load(PROFILE_IMAGE).into(binding.profileimageTaskdetailsmanager); //Load Image From Link
    }
    private void observeShowTaskResponse() {
        medicalSystemViewModel.getDatafromShowTaskResponse(taskId);
        medicalSystemViewModel.getMutableLiveDataShowTask().observe(getViewLifecycleOwner(), new Observer<ShowTaskData>() {
            @Override
            public void onChanged(ShowTaskData showTaskData) {
                myRecycleAdapterCheckedToDoTask.setArrayList(showTaskData.getToDo());           //Set Array to Adapter
                binding.recycleeTaskdetailsmanager.setAdapter(myRecycleAdapterCheckedToDoTask); //Set Adapter to Recycler
                binding.setViewModel(medicalSystemViewModel);
            }
        });
    }

    private void checkIfRTL() {
        if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
            binding.linear1Taskdetailsmanager.setBackgroundResource(R.drawable.layer2);
            binding.linear3Taskdetailsmanager.setBackgroundResource(R.drawable.layer2);
            binding.lineartodoTaskdetailsmanager.setBackgroundResource(R.drawable.layer4);
        }
    }

    private void inti() {
        //Initialize myRecycleAdapterCheckedToDoTask
        myRecycleAdapterCheckedToDoTask = new MyRecycleAdapterCheckedToDoTask();
        //Initialize medicalSystemViewModel
        medicalSystemViewModel = new ViewModelProvider(requireActivity()).get(MedicalSystemViewModel.class);
        //Initialize config
        config = getContext().getResources().getConfiguration();
    }
}