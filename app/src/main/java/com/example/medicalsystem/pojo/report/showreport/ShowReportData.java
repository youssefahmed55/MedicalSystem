package com.example.medicalsystem.pojo.report.showreport;

import com.google.gson.annotations.SerializedName;

public class ShowReportData {

	@SerializedName("manger")
	private Manger manger;

	@SerializedName("note")
	private String note;

	@SerializedName("answer")
	private String answer;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("description")
	private String description;

	@SerializedName("id")
	private int id;

	@SerializedName("report_name")
	private String reportName;

	@SerializedName("user")
	private User user;

	@SerializedName("status")
	private String status;

	public void setManger(Manger manger){
		this.manger = manger;
	}

	public Manger getManger(){
		return manger;
	}

	public void setNote(String note){
		this.note = note;
	}

	public String getNote(){
		return note;
	}

	public void setAnswer(String answer){
		this.answer = answer;
	}

	public String getAnswer(){
		return answer;
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

	public void setId(int id){
		this.id = id;
	}

	public int getId(){
		return id;
	}

	public void setReportName(String reportName){
		this.reportName = reportName;
	}

	public String getReportName(){
		return reportName;
	}

	public void setUser(User user){
		this.user = user;
	}

	public User getUser(){
		return user;
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
			"manger = '" + manger + '\'' + 
			",note = '" + note + '\'' + 
			",answer = '" + answer + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",description = '" + description + '\'' + 
			",id = '" + id + '\'' + 
			",report_name = '" + reportName + '\'' + 
			",user = '" + user + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}