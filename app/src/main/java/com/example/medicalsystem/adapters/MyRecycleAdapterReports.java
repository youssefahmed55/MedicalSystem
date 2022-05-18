package com.example.medicalsystem.adapters;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalsystem.R;
import com.example.medicalsystem.pojo.report.ReportResponseData;

import java.util.ArrayList;

public class MyRecycleAdapterReports extends RecyclerView.Adapter<MyRecycleAdapterReports.Holder> {
    private ArrayList<ReportResponseData> arrayList;
    private OnmyClickListenerrr onmyClickListenerrr;
    public void setArrayList(ArrayList<ReportResponseData> arrayList) {
        this.arrayList = arrayList;
    }
    public interface OnmyClickListenerrr {
        void onclick2(int id) ;
    }

    public void setOnmyClickListenerrr(OnmyClickListenerrr onmyClickListenerrr) {
        this.onmyClickListenerrr = onmyClickListenerrr;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_report,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
          holder.textView1.setText(arrayList.get(position).getReportName());
          holder.textView2.setText(arrayList.get(position).getCreatedAt());

          if(arrayList.get(position).getStatus().equalsIgnoreCase("pending")){
              holder.textView3.setBackgroundResource(R.drawable.shape12);
              holder.textView3.setTextColor(Color.parseColor("#F38C31"));
              holder.textView3.setText(holder.view.getResources().getString(R.string.Process));
          }else{
              holder.textView3.setBackgroundResource(R.drawable.shape11);
              holder.textView3.setTextColor(Color.parseColor("#23E483"));
              holder.textView3.setText(holder.view.getResources().getString(R.string.finished));
          }
    }

    @Override
    public int getItemCount() {
        if(arrayList != null)
            return arrayList.size();
        else
        return 0;
    }

    class Holder extends RecyclerView.ViewHolder{
        TextView textView1,textView2 , textView3;
        View view;
        public Holder(@NonNull View itemView) {
            super(itemView);
            view = itemView;
            textView1 = itemView.findViewById(R.id.name_list1_report);
            textView2 = itemView.findViewById(R.id.date_list1_report);
            textView3 = itemView.findViewById(R.id.reportstatue_list1_report);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAbsoluteAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        // you can trust the adapter position
                        // do whatever you intend to do with this position
                        if (onmyClickListenerrr != null)
                            onmyClickListenerrr.onclick2(arrayList.get(getAbsoluteAdapterPosition()).getId());
                    }

                }
            });


        }
    }
}
