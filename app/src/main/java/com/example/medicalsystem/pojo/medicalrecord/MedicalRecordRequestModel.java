package com.example.medicalsystem.pojo.medicalrecord;

import com.google.gson.annotations.SerializedName;

import java.io.File;
import java.io.Serializable;

public class MedicalRecordRequestModel implements Serializable {


    @SerializedName("call_id")
    private int callId;

    @SerializedName("image")
    private File imageFile;

    @SerializedName("note")
    private String note;

    @SerializedName("status")
    private String status;

    public MedicalRecordRequestModel(int callId, File imageFile, String note, String status) {
        this.callId = callId;
        this.imageFile = imageFile;
        this.note = note;
        this.status = status;
    }

    public int getCallId() {
        return callId;
    }

    public void setCallId(int callId) {
        this.callId = callId;
    }

    public File getImageFile() {
        return imageFile;
    }

    public void setImageFile(File image) {
        this.imageFile = image;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
