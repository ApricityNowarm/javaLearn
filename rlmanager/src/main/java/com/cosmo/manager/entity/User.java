package com.cosmo.manager.entity;

public class User {
    private int id;
    private String userCode;
    private String psw;
    private String staffCode;

    public User() {
    }


    public User(int id, String userCode, String psw, String staffCode) {
        this.id = id;
        this.userCode = userCode;
        this.psw = psw;
        this.staffCode = staffCode;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userCode='" + userCode + '\'' +
                ", psw='" + psw + '\'' +
                ", staffCode='" + staffCode + '\'' +
                '}';
    }
}
