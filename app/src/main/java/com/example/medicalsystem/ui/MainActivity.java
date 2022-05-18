package com.example.medicalsystem.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import com.example.medicalsystem.R;
import com.example.medicalsystem.viewmodel.MedicalSystemViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class MainActivity extends AppCompatActivity {
    private MedicalSystemViewModel medicalSystemViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStop() {
        medicalSystemViewModel = new ViewModelProvider(this).get(MedicalSystemViewModel.class);
        medicalSystemViewModel.deleteDate();   //Delete Date From SharedPreferences
        super.onStop();
    }
}