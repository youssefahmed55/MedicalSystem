package com.example.medicalsystem.pojo.addnurse;

import com.google.gson.annotations.SerializedName;

public class AddNurseModleRequest {

    @SerializedName("call_id")
    private int callId;

    @SerializedName("user_id")
    private int userId;

    public AddNurseModleRequest(int callId, int userId) {
        this.callId = callId;
        this.userId = userId;
    }

    public int getCallId() {
        return callId;
    }

    public void setCallId(int callId) {
        this.callId = callId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
