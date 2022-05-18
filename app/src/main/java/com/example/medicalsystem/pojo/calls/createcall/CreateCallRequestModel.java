package com.example.medicalsystem.pojo.calls.createcall;

import com.google.gson.annotations.SerializedName;

public class CreateCallRequestModel {
    @SerializedName("patient_name")
    private String patientName;
    @SerializedName("doctor_id")
    private String doctorId;
    @SerializedName("age")
    private String age;
    @SerializedName("phone")
    private String phone;
    @SerializedName("description")
    private String description;

    public CreateCallRequestModel(String patientName, String doctorId, String age, String phone, String description) {
        this.patientName = patientName;
        this.doctorId = doctorId;
        this.age = age;
        this.phone = phone;
        this.description = description;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(String doctorId) {
        this.doctorId = doctorId;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
