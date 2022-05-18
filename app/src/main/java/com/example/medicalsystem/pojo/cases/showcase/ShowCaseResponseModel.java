package com.example.medicalsystem.pojo.cases.showcase;

import com.google.gson.annotations.SerializedName;

public class ShowCaseResponseModel{

	@SerializedName("data")
	private ShowCaseResponseData showCaseResponseData;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private int status;

	public void setData(ShowCaseResponseData showCaseResponseData){
		this.showCaseResponseData = showCaseResponseData;
	}

	public ShowCaseResponseData getData(){
		return showCaseResponseData;
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
			"ShowCaseResponseModel{" + 
			"data = '" + showCaseResponseData + '\'' +
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}