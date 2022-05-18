package com.example.medicalsystem.pojo;

import com.example.medicalsystem.pojo.usersbytype.UserItem;

import java.io.Serializable;

public class HospitalEmployeeModle implements Serializable {
    private UserItem userItem;
    private boolean statue , checked;

    public HospitalEmployeeModle(UserItem userItem, boolean statue, boolean checked) {
        this.userItem = userItem;
        this.statue = statue;
        this.checked = checked;
    }

    public UserItem getUserItem() {
        return userItem;
    }

    public void setUserItem(UserItem userItem) {
        this.userItem = userItem;
    }

    public boolean isStatue() {
        return statue;
    }

    public void setStatue(boolean statue) {
        this.statue = statue;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
