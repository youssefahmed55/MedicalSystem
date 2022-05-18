package com.example.medicalsystem.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalsystem.R;
import com.example.medicalsystem.pojo.calls.callsreceptionist.CallsData;

import java.util.ArrayList;

public class MyRecycleAdapterCalls extends RecyclerView.Adapter<MyRecycleAdapterCalls.Holder> {
    private ArrayList<CallsData> arrayList;
    private OnMyClickListenerrr onmyClickListenerrr;
    public void setArrayList(ArrayList<CallsData> arrayList) {
        this.arrayList = arrayList;
    }
    public interface OnMyClickListenerrr {
        void onclick2(CallsData callsData) ;
    }

    public void setOnmyClickListenerrr(OnMyClickListenerrr onmyClickListenerrr) {
        this.onmyClickListenerrr = onmyClickListenerrr;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list1_calls,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
          holder.textView1.setText(arrayList.get(position).getPatientName());
          holder.textView2.setText(arrayList.get(position).getCreatedAt());
          if(arrayList.get(position).getStatus().equals("logout")){
              holder.imageView.setImageResource(R.drawable.ic_group_4464__1_);
          }else{
              holder.imageView.setImageResource(R.drawable.ic_group_4465);
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
        TextView textView1,textView2;
        ImageView imageView;
        public Holder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.name_list1_calls);
            textView2 = itemView.findViewById(R.id.date_list1_calls);
            imageView = itemView.findViewById(R.id.image_list1_calls);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAbsoluteAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        // you can trust the adapter position
                        // do whatever you intend to do with this position
                        if (onmyClickListenerrr != null)
                            onmyClickListenerrr.onclick2(arrayList.get(getAbsoluteAdapterPosition()));
                    }

                }
            });
        }
    }
}
