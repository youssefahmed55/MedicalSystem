package com.example.medicalsystem.pojo.login;

import com.google.gson.annotations.SerializedName;

public class LoginRequestModel {
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("device_token")
    private String deviceToken;

    public LoginRequestModel(String email, String password, String deviceToken) {
        this.email = email;
        this.password = password;
        this.deviceToken = deviceToken;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDeviceToken() {
        return deviceToken;
    }

    public void setDeviceToken(String deviceToken) {
        this.deviceToken = deviceToken;
    }
}
