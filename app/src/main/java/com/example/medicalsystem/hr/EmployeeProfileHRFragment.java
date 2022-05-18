package com.example.medicalsystem.hr;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;

import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.medicalsystem.R;
import com.example.medicalsystem.databinding.FragmentEmployeeProfileHRBinding;
import com.example.medicalsystem.viewmodel.MedicalSystemViewModel;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EmployeeProfileHRFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EmployeeProfileHRFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public EmployeeProfileHRFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EmployeeProfileHRFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EmployeeProfileHRFragment newInstance(String param1, String param2) {
        EmployeeProfileHRFragment fragment = new EmployeeProfileHRFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }
    private int id ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
            id =  getArguments().getInt("userId");
        }
    }
    private FragmentEmployeeProfileHRBinding binding;
    private View view;
    private MedicalSystemViewModel medicalSystemViewModel;
    private final String PROFILE_IMAGE = "https://png.pngtree.com/png-vector/20191027/ourlarge/pngtree-avatar-vector-icon-white-background-png-image_1884971.jpg";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_employee_profile_h_r, container, false);
        view = binding.getRoot();
        binding.setLifecycleOwner(this);
        onClickOnBackIcon();            //Set On Click On Back Icon
        setProfileImage();              //Set Profile Image
        inti();                         //Initialize variables

        if(id != 0){                    //If Id Not Null Not Equal 0
            medicalSystemViewModel.getDataFromShowProfileResponse(id); //Get Details Of Profile By Id
        }

        return view;
    }

    private void setProfileImage() {
        binding.imageEmployeeProfile.setImageBitmap(getBitmapFromVectorDrawable(getContext(),R.drawable.ic_ellipse_2_22));
        Glide.with(this).load(PROFILE_IMAGE).into(binding.profileimageEmployeeProfile); //Load Image From Link

    }

    private void inti() {
        //Initialize medicalSystemViewModel
        medicalSystemViewModel = new ViewModelProvider(requireActivity()).get(MedicalSystemViewModel.class);
        //Set ViewModel for Data Binding
        binding.setViewModel(medicalSystemViewModel);
    }



    private void onClickOnBackIcon() {
        binding.backEmployeeProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Navigation.findNavController(view).popBackStack(); //Back to previous Fragment
            }
        });
    }

    public static Bitmap getBitmapFromVectorDrawable(Context context, int drawableId) {
        Drawable drawable = ContextCompat.getDrawable(context, drawableId);
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            drawable = (DrawableCompat.wrap(drawable)).mutate();
        }

        Bitmap bitmap = Bitmap.createBitmap(drawable.getIntrinsicWidth(),
                drawable.getIntrinsicHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        drawable.setBounds(0, 0, canvas.getWidth(), canvas.getHeight());
        drawable.draw(canvas);

        return bitmap;
    }
}