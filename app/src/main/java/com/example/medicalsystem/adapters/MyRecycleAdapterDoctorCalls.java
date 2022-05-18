package com.example.medicalsystem.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalsystem.R;
import com.example.medicalsystem.pojo.calls.callsreceptionist.CallsData;

import java.util.ArrayList;

public class MyRecycleAdapterDoctorCalls extends RecyclerView.Adapter<MyRecycleAdapterDoctorCalls.Holder> {
    private ArrayList<CallsData> arrayList;
    OnmyClickListenerrr onmyClickListenerrr;
    public void setArrayList(ArrayList<CallsData> arrayList) {
        this.arrayList = arrayList;
    }

    public interface OnmyClickListenerrr {
        void onclick2(int id, View view) ;
    }

    public void setOnmyClickListenerrr(OnmyClickListenerrr onmyClickListenerrr) {
        this.onmyClickListenerrr = onmyClickListenerrr;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_callsdoctor,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
          holder.textView1.setText(arrayList.get(position).getPatientName());
          holder.textView2.setText(arrayList.get(position).getCreatedAt());

    }

    @Override
    public int getItemCount() {
        if(arrayList != null)
            return arrayList.size();
        else
        return 0;
    }

    class Holder extends RecyclerView.ViewHolder{
        TextView textView1,textView2;
        Button acceptButton , busyButton;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.name_list_callsdoctor);
            textView2 = itemView.findViewById(R.id.date_list_callsdoctor);
            acceptButton = itemView.findViewById(R.id.accept_list_callsdoctor);
            busyButton = itemView.findViewById(R.id.busy_list_callsdoctor);

            acceptButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    sendIdAndView(view);

                }
            });

            busyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   sendIdAndView(view);
                }

            });
        }
        private void sendIdAndView(View view){
            int position = getAbsoluteAdapterPosition();
            if (position != RecyclerView.NO_POSITION) {
                // you can trust the adapter position
                // do whatever you intend to do with this position
                if (onmyClickListenerrr != null)
                    onmyClickListenerrr.onclick2(arrayList.get(getAbsoluteAdapterPosition()).getId(),view);
            }

        }

}}
