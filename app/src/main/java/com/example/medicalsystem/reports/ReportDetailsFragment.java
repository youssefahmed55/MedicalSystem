package com.example.medicalsystem.reports;

import android.content.res.Configuration;
import android.os.Bundle;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import com.bumptech.glide.Glide;
import com.example.medicalsystem.R;
import com.example.medicalsystem.databinding.FragmentReportDetailsBinding;
import com.example.medicalsystem.pojo.MessageResponseModel;
import com.example.medicalsystem.singleliveevent.EventHandler;
import com.example.medicalsystem.singleliveevent.EventObserver;
import com.example.medicalsystem.viewmodel.MedicalSystemViewModel;
import es.dmoral.toasty.Toasty;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ReportDetailsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ReportDetailsFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ReportDetailsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ReportDetailsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ReportDetailsFragment newInstance(String param1, String param2) {
        ReportDetailsFragment fragment = new ReportDetailsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private int repordId;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            repordId =  getArguments().getInt("reportId");

        }
    }
    private FragmentReportDetailsBinding binding;
    private View view;
    private final String PROFILE_IMAGE = "https://png.pngtree.com/png-vector/20191027/ourlarge/pngtree-avatar-vector-icon-white-background-png-image_1884971.jpg";
    private Configuration config;
    private MedicalSystemViewModel medicalSystemViewModel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_report_details, container, false);
        binding.setLifecycleOwner(this);
        view = binding.getRoot();
        inti();                                                              //Initialize variables
        checkIfRTL();                                                        //Check If RTL To Change Background
        onClickOnBackIcon();                                                 //Set On Click On Back Icon
        setProfileImage();                                                   //Set Profile Image

        if(repordId != 0){                                                   //If Report Id Not Null Not Equal 0
            medicalSystemViewModel.getDatafromShowReportResponse(repordId);  //Get Data Of Case By Id
            onClickOnFinishButton();                                         //Set On Click On Finsih Button
            onClickOnEnd();                                                  //Set On Click On End TextView
        }

        return view;
    }

    private void onClickOnEnd() {
        binding.EndReportdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medicalSystemViewModel.getDatafromEndReportResponse(repordId);   //Get Response After Ending Report By Id
                medicalSystemViewModel.getSingleLiveEventEndReport().observe(getViewLifecycleOwner(),new EventObserver<MessageResponseModel>(new EventHandler<MessageResponseModel>() {
                    @Override
                    public void onEventUnHandled(MessageResponseModel object) {
                        if(object.getStatus() == 1){
                            Toasty.success(getActivity(), getString(R.string.Done), Toast.LENGTH_SHORT, true).show();
                            Navigation.findNavController(view).popBackStack();    //Back to previous Fragment
                        }else {
                            Toasty.error(getActivity(), object.getMessage(), Toast.LENGTH_SHORT, true).show();
                        }
                    }
                }));
            }
        });

    }

    private void onClickOnFinishButton() {
        binding.FinishbuttonReportdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                medicalSystemViewModel.getDatafromFinishReportResponse(repordId); //Get Response After Finishing Report By Id
                medicalSystemViewModel.getSingleLiveEventFinishReport().observe(getViewLifecycleOwner(),new EventObserver<MessageResponseModel>(new EventHandler<MessageResponseModel>() {
                    @Override
                    public void onEventUnHandled(MessageResponseModel object) {
                        if(object.getStatus() == 1){
                            Toasty.success(getActivity(), getString(R.string.Done), Toast.LENGTH_SHORT, true).show();
                            Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
                        }else {
                            Toasty.error(getActivity(), object.getMessage(), Toast.LENGTH_SHORT, true).show();
                        }
                    }
                }));
            }
        });

    }

    private void setProfileImage() {
        Glide.with(this).load(PROFILE_IMAGE).into(binding.profileimageReportdetails);   //Load Image From Link
        Glide.with(this).load(PROFILE_IMAGE).into(binding.profileimage2Reportdetails);  //Load Image From Link
    }
    private void onClickOnBackIcon() {
        binding.backReportdetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
            }
        });
    }
    private void checkIfRTL() {
        if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
            binding.linear3Reportdetails.setBackgroundResource(R.drawable.layer2);
        }
    }

    private void inti() {
        //Initialize medicalSystemViewModel
        medicalSystemViewModel = new ViewModelProvider(requireActivity()).get(MedicalSystemViewModel.class);
        //Set ViewModel For DataBinding
        binding.setViewModel(medicalSystemViewModel);
        //Initialize config
        config = getContext().getResources().getConfiguration();

    }
}