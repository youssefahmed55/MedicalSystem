package com.example.medicalsystem.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalsystem.R;
import com.example.medicalsystem.pojo.MedicalModel;

import java.util.ArrayList;

public class MyRecycleAdapterMedical3 extends RecyclerView.Adapter<MyRecycleAdapterMedical3.Holder> {
    private ArrayList<MedicalModel> arrayList;

    public void setArrayList(ArrayList<MedicalModel> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_medicalmeasrement,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

          holder.textView1.setText(arrayList.get(position).getMedicalName());
          holder.textView2.setText(arrayList.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        if(arrayList != null)
            return arrayList.size();
        else
        return 0;
    }

    class Holder extends RecyclerView.ViewHolder{
        TextView textView1 , textView2;


        public Holder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.measurementname_listmedicalmeasurement);
            textView2 = itemView.findViewById(R.id.measurementvalue_listmedicalmeasurement);

        }
    }
}
