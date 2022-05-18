package com.example.medicalsystem.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.medicalsystem.R;
import com.example.medicalsystem.pojo.usersbytype.UserItem;

import java.util.ArrayList;

public class MyRecycleAdapterHospitalEmployee extends RecyclerView.Adapter<MyRecycleAdapterHospitalEmployee.Holder> {
    private ArrayList<UserItem> arrayList;
    private Context context;
    private OnmyClickListenerrr onmyClickListenerrr;

    public MyRecycleAdapterHospitalEmployee(Context context) {
        this.context = context;
    }

    public void setArrayList(ArrayList<UserItem> arrayList) {
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_employee,parent,false));
    }
    public interface OnmyClickListenerrr {
        void onclick2(int id) ;
    }
    public void setOnClickListeners(OnmyClickListenerrr onClickListeners) {
        this.onmyClickListenerrr = onClickListeners;
    }
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
          Glide.with(context).load(arrayList.get(position).getAvatar()).into(holder.imageView);
          holder.textView1.setText(arrayList.get(position).getFirstName());
          holder.textView2.setText(context.getResources().getString(R.string.specialist) + " , " +arrayList.get(position).getType());
          holder.imageView2.setImageResource(R.drawable.ic_ellipse_592);
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

        public Holder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.name_listemployee);
            textView2 = itemView.findViewById(R.id.Specialist_listemployee);
            imageView = itemView.findViewById(R.id.doctorimage_listemployee);
            imageView2 = itemView.findViewById(R.id.statue_listemployee);


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
