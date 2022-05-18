package com.example.medicalsystem.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalsystem.R;

import java.util.ArrayList;

public class MyRecycleAdapterMedical extends RecyclerView.Adapter<MyRecycleAdapterMedical.Holder> {
    private ArrayList<String> arrayList;

    public void setArrayList(ArrayList<String> arrayList) {
        this.arrayList = arrayList;
    }
    private OnmyClickListenerrr onmyClickListenerrr;
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_medical,parent,false));
    }
    public interface OnmyClickListenerrr {
        void onclick2(int pos) ;
    }
    public void setOnClickListeners(OnmyClickListenerrr onClickListeners) {
        this.onmyClickListenerrr = onClickListeners;
    }
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

          holder.textView1.setText(arrayList.get(position));


    }

    @Override
    public int getItemCount() {
        if(arrayList != null)
            return arrayList.size();
        else
        return 0;
    }

    class Holder extends RecyclerView.ViewHolder{
        TextView textView1;
        ImageView imageView;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.text_listmedical);
            imageView = itemView.findViewById(R.id.delete_listmedical);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAbsoluteAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        // you can trust the adapter position
                        // do whatever you intend to do with this position
                        if (onmyClickListenerrr != null)
                            onmyClickListenerrr.onclick2(getAbsoluteAdapterPosition());
                    }
                }
            });


        }
    }
}
