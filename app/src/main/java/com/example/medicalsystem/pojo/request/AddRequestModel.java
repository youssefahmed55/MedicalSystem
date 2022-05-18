package com.example.medicalsystem.pojo.request;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class AddRequestModel {


    @SerializedName("call_id")
    private int callId;

    @SerializedName("user_id")
    private int userId;

    @SerializedName("note")
    private String note;

    @SerializedName("types")
    @Expose
    private String[] types = null;

    public AddRequestModel(int callId, int userId, String note, String[] types) {
        this.callId = callId;
        this.userId = userId;
        this.note = note;
        this.types = types;
    }

    public int getCallId() {
        return callId;
    }

    public void setCallId(int callId) {
        this.callId = callId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String[] getTypes() {
        return types;
    }

    public void setTypes(String[] types) {
        this.types = types;
    }
}
