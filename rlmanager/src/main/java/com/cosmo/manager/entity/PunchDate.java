package com.cosmo.manager.entity;

import java.sql.Timestamp;
import java.sql.Date;

public class PunchDate {
    private int id;
    private String staffCode;
    private Date punchDate;
    private Timestamp onTime;
    private String onState;
    private Timestamp offTime;
    private String offState;

    public PunchDate() {
    }

    public PunchDate(int id, String staffCode, Date punchDate, Timestamp onTime, String onState, Timestamp offTime, String offState) {
        this.id = id;
        this.staffCode = staffCode;
        this.punchDate = punchDate;
        this.onTime = onTime;
        this.onState = onState;
        this.offTime = offTime;
        this.offState = offState;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStaffCode() {
        return staffCode;
    }

    public void setStaffCode(String staffCode) {
        this.staffCode = staffCode;
    }

    public Date getPunchDate() {
        return punchDate;
    }

    public void setPunchDate(Date punchDate) {
        this.punchDate = punchDate;
    }

    public Timestamp getOnTime() {
        return onTime;
    }

    public void setOnTime(Timestamp onTime) {
        this.onTime = onTime;
    }

    public String getOnState() {
        return onState;
    }

    public void setOnState(String onState) {
        this.onState = onState;
    }

    public Timestamp getOffTime() {
        return offTime;
    }

    public void setOffTime(Timestamp offTime) {
        this.offTime = offTime;
    }

    public String getOffState() {
        return offState;
    }

    public void setOffState(String offState) {
        this.offState = offState;
    }

    @Override
    public String toString() {
        return "PunchDate{" +
                "id=" + id +
                ", staffCode='" + staffCode + '\'' +
                ", punchDate=" + punchDate +
                ", onTime=" + onTime +
                ", onState='" + onState + '\'' +
                ", offTime=" + offTime +
                ", offState='" + offState + '\'' +
                '}';
    }
}
