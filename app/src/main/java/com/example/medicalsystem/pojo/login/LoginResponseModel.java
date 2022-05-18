package com.example.medicalsystem.pojo.login;

import com.example.medicalsystem.pojo.login.LoginData;
import com.google.gson.annotations.SerializedName;

public class LoginResponseModel{

	@SerializedName("data")
	private LoginData loginData;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private int status;

	public void setData(LoginData loginData){
		this.loginData = loginData;
	}

	public LoginData getData(){
		return loginData;
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
}