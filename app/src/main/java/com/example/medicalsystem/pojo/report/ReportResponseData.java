package com.example.medicalsystem.pojo.report;

import com.google.gson.annotations.SerializedName;

public class ReportResponseData {

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("report_name")
	private String reportName;

	@SerializedName("status")
	private String status;

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

	public void setReportName(String reportName){
		this.reportName = reportName;
	}

	public String getReportName(){
		return reportName;
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
			"DataItem{" + 
			"created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",report_name = '" + reportName + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}