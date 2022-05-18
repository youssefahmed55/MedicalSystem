package com.example.medicalsystem.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.medicalsystem.R;
import com.example.medicalsystem.pojo.HospitalEmployeeModle;

import java.util.ArrayList;

public class MyRecycleAdapterSelectHospitalEmployee extends RecyclerView.Adapter<MyRecycleAdapterSelectHospitalEmployee.Holder> {
    private ArrayList<HospitalEmployeeModle> arrayList;
    private Context context;

    public MyRecycleAdapterSelectHospitalEmployee(Context context) {
        this.context = context;
    }

    public void setArrayList(ArrayList<HospitalEmployeeModle> arrayList) {
        this.arrayList = arrayList;
    }
    private OnmyClickListenerrr onmyClickListenerrr;
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_selectemployee,parent,false));
    }
    public interface OnmyClickListenerrr {
        void onclick2(HospitalEmployeeModle hospitalEmployeeModle) ;
    }
    public void setOnClickListeners(OnmyClickListenerrr onClickListeners) {
        this.onmyClickListenerrr = onClickListeners;
    }
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        Glide.with(context).load(arrayList.get(position).getUserItem().getAvatar()).into(holder.imageView);
        holder.textView1.setText(arrayList.get(position).getUserItem().getFirstName());
        holder.textView2.setText(context.getResources().getString(R.string.specialist) + " , " +arrayList.get(position).getUserItem().getType());
        if(arrayList.get(position).isStatue()){
              holder.imageView2.setImageResource(R.drawable.ic_ellipse_592);
          }else{
              holder.imageView2.setImageResource(R.drawable.ic_ellipse_592_1);
          }

          if(arrayList.get(position).isChecked()){
              holder.radioButton.setChecked(true);
          }else {
              holder.radioButton.setChecked(false);
          }
    }

    public ArrayList<HospitalEmployeeModle> getArrayList() {
        return arrayList;
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
        ImageView imageView , imageView2;
        RadioButton radioButton;
        public Holder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.name_listselectemployee);
            textView2 = itemView.findViewById(R.id.Specialist_listselectemployee);
            imageView = itemView.findViewById(R.id.doctorimage_listselectemployee);
            imageView2 = itemView.findViewById(R.id.statue_listselectemployee);
            radioButton= itemView.findViewById(R.id.radio_listselectemployee);

            radioButton.setOnClickListener(new View.OnClickListener() {
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
