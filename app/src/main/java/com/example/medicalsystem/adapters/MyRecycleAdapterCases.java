package com.example.medicalsystem.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalsystem.R;
import com.example.medicalsystem.pojo.cases.CasesResponseData;

import java.util.ArrayList;

public class MyRecycleAdapterCases extends RecyclerView.Adapter<MyRecycleAdapterCases.Holder> {
    private ArrayList<CasesResponseData> arrayList;
    private OnmyClickListenerrr onmyClickListenerrr;

    public void setArrayList(ArrayList<CasesResponseData> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_cases,parent,false));
    }
    public interface OnmyClickListenerrr {
        void onclick2(int id) ;
    }
    public void setOnClickListeners(OnmyClickListenerrr onClickListeners) {
        this.onmyClickListenerrr = onClickListeners;
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
        Button button;
        public Holder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.name_list_cases);
            textView2 = itemView.findViewById(R.id.date_list_cases);
            button = itemView.findViewById(R.id.showDetails_list_cases);

            button.setOnClickListener(new View.OnClickListener() {
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
