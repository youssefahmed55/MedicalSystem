package com.example.medicalsystem.pojo;

public class MedicalModel {
   private String medicalName , value ;

    public MedicalModel(String medicalName, String value) {
        this.medicalName = medicalName;
        this.value = value;
    }

    public String getMedicalName() {
        return medicalName;
    }

    public void setMedicalName(String medicalName) {
        this.medicalName = medicalName;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
