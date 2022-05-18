package com.example.medicalsystem.adapters;

import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medicalsystem.R;
import com.example.medicalsystem.pojo.task.showtask.ToDoItem;

import java.util.List;

public class MyRecycleAdapterCheckedToDoTask extends RecyclerView.Adapter<MyRecycleAdapterCheckedToDoTask.Holder> {
    private List<ToDoItem> arrayList;
    private Configuration config;
    public void setArrayList(List<ToDoItem> arrayList) {
        this.arrayList = arrayList;
    }
    private OnmyClickListenerrr onmyClickListenerrr;
    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_checkedtodotask,parent,false);
        config = parent.getContext().getResources().getConfiguration();

        return new Holder(view);
    }
    public interface OnmyClickListenerrr {
        void onclick2(int pos) ;
    }
    public void setOnClickListeners(OnmyClickListenerrr onClickListeners) {
        this.onmyClickListenerrr = onClickListeners;
    }
    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

          holder.textView1.setText(arrayList.get(position).getTitle());
        /*if (config.getLayoutDirection() == View.LAYOUT_DIRECTION_RTL) {
           holder.frameLayout.setBackgroundResource(R.drawable.layer4);
        }*/

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
        CheckBox checkBox;


        public Holder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.text_listcheckedtodotask);
            checkBox = itemView.findViewById(R.id.checkbox_listcheckedtodotask);

            checkBox.setOnClickListener(new View.OnClickListener() {
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
