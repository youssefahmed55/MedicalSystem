package com.example.medicalsystem.customdialog;



import android.app.Activity;
import android.app.Dialog;

import android.os.Bundle;
import android.view.Window;
import android.widget.Button;



import com.example.medicalsystem.R;


public class CustomDialogClass extends Dialog {
    public Activity c;
    public Dialog d;
    public Button yes, no;

    public CustomDialogClass(Activity a) {
        super(a);
        // TODO Auto-generated constructor stub
        this.c = a;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.custom_dialog);
        yes = (Button) findViewById(R.id.btn_yes);
        no = (Button) findViewById(R.id.btn_no);

    }
}
