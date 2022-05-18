package com.example.medicalsystem.pojo.usersbytype;

import java.util.ArrayList;
import java.util.List;
import com.google.gson.annotations.SerializedName;

public class UsersResponseModel {

	@SerializedName("data")
	private ArrayList<UserItem> data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private int status;

	public void setData(ArrayList<UserItem> data){
		this.data = data;
	}

	public ArrayList<UserItem> getData(){
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
			"DoctorsResponseModel{" + 
			"data = '" + data + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}