package com.example.medicalsystem.customdialog;



import android.app.Activity;
import android.app.Dialog;

import android.os.Bundle;

import android.view.Window;


import com.example.medicalsystem.R;

public class CustomDialogClass2 extends Dialog {
    public Activity c;
    public Dialog d;

    public CustomDialogClass2(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog2);

    }


}
