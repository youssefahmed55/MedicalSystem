package com.example.medicalsystem.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.medicalsystem.R;

import java.util.List;

public class MyArrayAdapter extends ArrayAdapter<String> {

   private Context context;
   private View view;

    public MyArrayAdapter(@NonNull Context context, int resource, @NonNull List<String> objects) {
        super(context, resource, objects);
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        view =   LayoutInflater.from(context).inflate(R.layout.spinner_item,parent,false);

        if(position > 0){
            view = LayoutInflater.from(context).inflate(R.layout.spinner_item2, parent, false);
        }


        return super.getView(position, view, parent);


    }
}
