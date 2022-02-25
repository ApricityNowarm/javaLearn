package com.cosmo.manager.entity;

import java.util.Date;

public class Event {
    private int id;
    private String staffCode;
    private String category;
    private String reason;
    private int onVacation;
    private Date startTime;
    private Date endTime;
    private int onFinish;

    public Event() {
    }

    public Event(int id, String staffCode, String category, String reason, int onVacation, Date startTime, Date endTime, int onFinish) {
        this.id = id;
        this.staffCode = staffCode;
        this.category = category;
        this.reason = reason;
        this.onVacation = onVacation;
        this.startTime = startTime;
        this.endTime = endTime;
        this.onFinish = onFinish;
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

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public int getOnVacation() {
        return onVacation;
    }

    public void setOnVacation(int onVacation) {
        this.onVacation = onVacation;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getOnFinish() {
        return onFinish;
    }

    public void setOnFinish(int onFinish) {
        this.onFinish = onFinish;
    }

    @Override
    public String toString() {
        return "Event{" +
                "id=" + id +
                ", staffCode='" + staffCode + '\'' +
                ", category='" + category + '\'' +
                ", reason='" + reason + '\'' +
                ", onVacation=" + onVacation +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", onFinish=" + onFinish +
                '}';
    }
}
