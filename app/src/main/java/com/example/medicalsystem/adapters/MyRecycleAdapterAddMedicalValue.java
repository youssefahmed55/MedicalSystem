package com.example.medicalsystem.adapters;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import com.example.medicalsystem.R;
import com.example.medicalsystem.pojo.MedicalModel;

import java.util.ArrayList;

public class MyRecycleAdapterAddMedicalValue extends RecyclerView.Adapter<MyRecycleAdapterAddMedicalValue.Holder> {


    private ArrayList<MedicalModel> arrayList;
    private OntextchangeListenerrr ontextchangeListenerrr;
    public void setArrayList(ArrayList<MedicalModel> arrayList) {
        this.arrayList = arrayList;
    }
    public interface OntextchangeListenerrr {
        void onchange(int pos ,String s) ;
    }
    public void setOntextchangeListeners(OntextchangeListenerrr onClickListeners) {
        this.ontextchangeListenerrr = onClickListeners;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.list_addvaluemedical,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {

          holder.textView1.setText(arrayList.get(position).getMedicalName());
          holder.editText.setText(arrayList.get(position).getValue());

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
        EditText editText;

        public Holder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.text_list_addvaluemedical);
            editText = itemView.findViewById(R.id.Value_list_addvaluemedical);

            editText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                }

                @Override
                public void afterTextChanged(Editable editable) {
                    int position = getAbsoluteAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        // you can trust the adapter position
                        // do whatever you intend to do with this position
                        if (ontextchangeListenerrr != null)
                            ontextchangeListenerrr.onchange(getAbsoluteAdapterPosition(),editable.toString().trim());
                    }
                }
            });





        }
    }
}
