package com.example.medicalsystem.pojo.report;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class ReportResponseModel{

	@SerializedName("data")
	private List<ReportResponseData> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private int status;

	public void setData(List<ReportResponseData> data){
		this.data = data;
	}

	public List<ReportResponseData> getData(){
		return data;
	}

	public void setMessage(String message){
		this.message = message;
	}

	public String getMessage(){
		return message;
	}

	public void setStatus(int status){
		this.status = status;
	}

	public int getStatus(){
		return status;
	}

	@Override
 	public String toString(){
		return 
			"ReportResponseModel{" + 
			"data = '" + data + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}