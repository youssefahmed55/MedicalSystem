package com.example.medicalsystem.pojo.cases;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CasesResponseData implements Serializable {

	@SerializedName("patient_name")
	private String patientName;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	public void setPatientName(String patientName){
		this.patientName = patientName;
	}

	public String getPatientName(){
		return patientName;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	@Override
 	public String toString(){
		return 
			"DataItem{" + 
			"patient_name = '" + patientName + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			"}";
		}
}