package com.example.medicalsystem.attendanceandleaving;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.example.medicalsystem.R;

import com.example.medicalsystem.databinding.FragmentTouchIDSensorBinding;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link TouchIDSensorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TouchIDSensorFragment extends Fragment implements Animation.AnimationListener {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public TouchIDSensorFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TouchIDSensorFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TouchIDSensorFragment newInstance(String param1, String param2) {
        TouchIDSensorFragment fragment = new TouchIDSensorFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private String status;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            status  = getArguments().getString("status");
        }
    }
    private FragmentTouchIDSensorBinding binding;
    private View view;
    private Animation animFadeIn,animFadeOut;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentTouchIDSensorBinding.inflate(inflater, container, false);
        view = binding.getRoot();
        inti();                //Initialize variables
        checkIfLeaving();      //Check If Leaving to Change TextView
        makeAnimation();       //Make Animation
        onClickOnNextImage();  //Set On Click On Next Image







        return view;
    }

    private void checkIfLeaving() {
        if (status != null)
            if (status.equals("leaving"))
                binding.text2Touchidsensor.setText(getString(R.string.Your_leaving_has_been_registered));

        }

    private void onClickOnNextImage() {
        binding.imagenextTouchidsensor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
            }
        });
    }

    private void makeAnimation() {
        animFadeIn.setAnimationListener(this);
        animFadeOut.setAnimationListener(this);

        // Make fade in elements Visible first
        binding.text2Touchidsensor.setVisibility(View.VISIBLE);
        binding.touchimage2Touchidsensor.setVisibility(View.VISIBLE);
        binding.touchimage3Touchidsensor.setVisibility(View.VISIBLE);
        binding.imagenextTouchidsensor.setVisibility(View.VISIBLE);

        // start fade in animation
        binding.text2Touchidsensor.startAnimation(animFadeIn);
        binding.touchimage2Touchidsensor.startAnimation(animFadeIn);
        binding.touchimage3Touchidsensor.startAnimation(animFadeIn);
        binding.imagenextTouchidsensor.startAnimation(animFadeIn);

        // start fade out animation
        binding.text1Touchidsensor.startAnimation(animFadeOut);
        binding.touchimageTouchidsensor.startAnimation(animFadeOut);
    }

    private void inti() {
        //Initialize animFadeIn
        animFadeIn = AnimationUtils.loadAnimation(getContext(),R.anim.fade_in);
        //Initialize animFadeOut
        animFadeOut = AnimationUtils.loadAnimation(getContext(),R.anim.fade_out);
    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

    }
}