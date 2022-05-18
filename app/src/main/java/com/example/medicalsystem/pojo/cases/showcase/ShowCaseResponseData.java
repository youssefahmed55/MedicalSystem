package com.example.medicalsystem.pojo.cases.showcase;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ShowCaseResponseData implements Serializable {

	@SerializedName("image")
	private String image;

	@SerializedName("respiratory_rate")
	private String respiratoryRate;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("description")
	private String description;

	@SerializedName("heart_rate")
	private String heartRate;

	@SerializedName("blood_pressure")
	private String bloodPressure;

	@SerializedName("sugar_analysis")
	private String sugarAnalysis;

	@SerializedName("doc_id")
	private int docId;

	@SerializedName("analysis_id")
	private String analysisId;

	@SerializedName("measurement_note")
	private String measurementNote;

	@SerializedName("doctor_id")
	private String doctorId;

	@SerializedName("medical_record_note")
	private String medicalRecordNote;

	@SerializedName("tempreture")
	private String tempreture;

	@SerializedName("phone")
	private String phone;

	@SerializedName("case_status")
	private String caseStatus;

	@SerializedName("patient_name")
	private String patientName;

	@SerializedName("fluid_balance")
	private String fluidBalance;

	@SerializedName("id")
	private int id;

	@SerializedName("nurse_id")
	private String nurseId;

	@SerializedName("age")
	private String age;

	@SerializedName("status")
	private String status;

	public void setImage(String image){
		this.image = image;
	}

	public String getImage(){
		return image;
	}

	public void setRespiratoryRate(String respiratoryRate){
		this.respiratoryRate = respiratoryRate;
	}

	public String getRespiratoryRate(){
		return respiratoryRate;
	}

	public void setCreatedAt(String createdAt){
		this.createdAt = createdAt;
	}

	public String getCreatedAt(){
		return createdAt;
	}

	public void setDescription(String description){
		this.description = description;
	}

	public String getDescription(){
		return description;
	}

	public void setHeartRate(String heartRate){
		this.heartRate = heartRate;
	}

	public String getHeartRate(){
		return heartRate;
	}

	public void setBloodPressure(String bloodPressure){
		this.bloodPressure = bloodPressure;
	}

	public String getBloodPressure(){
		return bloodPressure;
	}

	public void setSugarAnalysis(String sugarAnalysis){
		this.sugarAnalysis = sugarAnalysis;
	}

	public String getSugarAnalysis(){
		return sugarAnalysis;
	}

	public void setDocId(int docId){
		this.docId = docId;
	}

	public int getDocId(){
		return docId;
	}

	public void setAnalysisId(String analysisId){
		this.analysisId = analysisId;
	}

	public String getAnalysisId(){
		return analysisId;
	}

	public void setMeasurementNote(String measurementNote){
		this.measurementNote = measurementNote;
	}

	public String getMeasurementNote(){
		return measurementNote;
	}

	public void setDoctorId(String doctorId){
		this.doctorId = doctorId;
	}

	public String getDoctorId(){
		return doctorId;
	}

	public void setMedicalRecordNote(String medicalRecordNote){
		this.medicalRecordNote = medicalRecordNote;
	}

	public String getMedicalRecordNote(){
		return medicalRecordNote;
	}

	public void setTempreture(String tempreture){
		this.tempreture = tempreture;
	}

	public String getTempreture(){
		return tempreture;
	}

	public void setPhone(String phone){
		this.phone = phone;
	}

	public String getPhone(){
		return phone;
	}

	public void setCaseStatus(String caseStatus){
		this.caseStatus = caseStatus;
	}

	public String getCaseStatus(){
		return caseStatus;
	}

	public void setPatientName(String patientName){
		this.patientName = patientName;
	}

	public String getPatientName(){
		return patientName;
	}

	public void setFluidBalance(String fluidBalance){
		this.fluidBalance = fluidBalance;
	}

	public String getFluidBalance(){
		return fluidBalance;
	}

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setNurseId(String nurseId){
		this.nurseId = nurseId;
	}

	public String getNurseId(){
		return nurseId;
	}

	public void setAge(String age){
		this.age = age;
	}

	public String getAge(){
		return age;
	}

	public void setStatus(String status){
		this.status = status;
	}

	public String getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"Data{" + 
			"image = '" + image + '\'' + 
			",respiratory_rate = '" + respiratoryRate + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",description = '" + description + '\'' + 
			",heart_rate = '" + heartRate + '\'' + 
			",blood_pressure = '" + bloodPressure + '\'' + 
			",sugar_analysis = '" + sugarAnalysis + '\'' + 
			",doc_id = '" + docId + '\'' + 
			",analysis_id = '" + analysisId + '\'' + 
			",measurement_note = '" + measurementNote + '\'' + 
			",doctor_id = '" + doctorId + '\'' + 
			",medical_record_note = '" + medicalRecordNote + '\'' + 
			",tempreture = '" + tempreture + '\'' + 
			",phone = '" + phone + '\'' + 
			",case_status = '" + caseStatus + '\'' + 
			",patient_name = '" + patientName + '\'' + 
			",fluid_balance = '" + fluidBalance + '\'' + 
			",id = '" + id + '\'' + 
			",nurse_id = '" + nurseId + '\'' + 
			",age = '" + age + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}