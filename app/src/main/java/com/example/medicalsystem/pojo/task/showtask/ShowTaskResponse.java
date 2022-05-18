package com.example.medicalsystem.pojo.task.showtask;

import com.google.gson.annotations.SerializedName;

public class ShowTaskResponse{

	@SerializedName("data")
	private ShowTaskData data;

	@SerializedName("message")
	private String message;

	@SerializedName("status")
	private int status;

	public void setData(ShowTaskData data){
		this.data = data;
	}

	public ShowTaskData getData(){
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
			"ShowTaskResponse{" + 
			"data = '" + data + '\'' + 
			",message = '" + message + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}