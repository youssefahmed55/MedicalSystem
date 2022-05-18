package com.example.medicalsystem.pojo.showprofile;

import com.google.gson.annotations.SerializedName;

public class ShowProfileResponse{

	@SerializedName("data")
	private ShowProfileData showProfileData;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private int status;

	public void setData(ShowProfileData showProfileData){
		this.showProfileData = showProfileData;
	}

	public ShowProfileData getData(){
		return showProfileData;
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
			"ShowProfileResponse{" + 
			"data = '" + showProfileData + '\'' +
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}