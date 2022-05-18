package com.example.medicalsystem.pojo.measurement;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MeasurementRequestModel implements Serializable {


   @SerializedName("call_id")
   private int callId;

   @SerializedName("blood_pressure")
   private String bloodPressure;

   @SerializedName("sugar_analysis")
   private String sugarAnalysis;

   @SerializedName("note")
   private String note;

   @SerializedName("status")
   private String status;

   @SerializedName("fluid_balance")
   private String fluidBalance;

   @SerializedName("respiratory_rate")
   private String respiratoryRate;

   @SerializedName("heart_rate")
   private String heartRate;

   @SerializedName("tempreture")
   private String tempreture;

    public MeasurementRequestModel(int callId, String bloodPressure, String sugarAnalysis, String note, String status, String fluidBalance, String respiratoryRate, String heartRate, String tempreture) {
        this.callId = callId;
        this.bloodPressure = bloodPressure;
        this.sugarAnalysis = sugarAnalysis;
        this.note = note;
        this.status = status;
        this.fluidBalance = fluidBalance;
        this.respiratoryRate = respiratoryRate;
        this.heartRate = heartRate;
        this.tempreture = tempreture;
    }

    public int getCallId() {
        return callId;
    }

    public void setCallId(int callId) {
        this.callId = callId;
    }

    public String getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(String bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public String getSugarAnalysis() {
        return sugarAnalysis;
    }

    public void setSugarAnalysis(String sugarAnalysis) {
        this.sugarAnalysis = sugarAnalysis;
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

    public String getFluidBalance() {
        return fluidBalance;
    }

    public void setFluidBalance(String fluidBalance) {
        this.fluidBalance = fluidBalance;
    }

    public String getRespiratoryRate() {
        return respiratoryRate;
    }

    public void setRespiratoryRate(String respiratoryRate) {
        this.respiratoryRate = respiratoryRate;
    }

    public String getHeartRate() {
        return heartRate;
    }

    public void setHeartRate(String heartRate) {
        this.heartRate = heartRate;
    }

    public String getTempreture() {
        return tempreture;
    }

    public void setTempreture(String tempreture) {
        this.tempreture = tempreture;
    }
}
