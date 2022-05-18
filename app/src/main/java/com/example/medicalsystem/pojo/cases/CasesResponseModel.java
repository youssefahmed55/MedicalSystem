package com.example.medicalsystem.pojo.cases;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class CasesResponseModel {

	@SerializedName("data")
	private List<CasesResponseData> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private int status;

	public void setData(List<CasesResponseData> data){
		this.data = data;
	}

	public List<CasesResponseData> getData(){
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
			"CaseResponseModel{" + 
			"data = '" + data + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}