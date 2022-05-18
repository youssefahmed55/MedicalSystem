package com.example.medicalsystem.pojo.calls.showcall;

import com.google.gson.annotations.SerializedName;


public class ShowCallResponse {

	@SerializedName("data")
	private ShowCallData showCallData;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private int status;

	public void setData(ShowCallData showCallData){
		this.showCallData = showCallData;
	}

	public ShowCallData getData(){
		return showCallData;
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
			"ShowCallResponse{" + 
			"data = '" + showCallData + '\'' +
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}