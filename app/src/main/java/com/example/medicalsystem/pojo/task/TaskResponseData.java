package com.example.medicalsystem.pojo.task;

import com.google.gson.annotations.SerializedName;

public class TaskResponseData {

	@SerializedName("task_name")
	private String taskName;

	@SerializedName("created_at")
	private String createdAt;

	@SerializedName("id")
	private int id;

	@SerializedName("status")
	private String status;

	public void setTaskName(String taskName){
		this.taskName = taskName;
	}

	public String getTaskName(){
		return taskName;
	}

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
			"task_name = '" + taskName + '\'' + 
			",created_at = '" + createdAt + '\'' + 
			",id = '" + id + '\'' + 
			",status = '" + status + '\'' + 
			"}";
		}
}