package com.cosmo.manager.entity;

public class Staff {
    private int id;
    private String staffCode;
    private String name;
    private String gender;
    private int age;
    private String phone;
    private int dId;
    private int pId;
    private double kpi;

    public Staff() {
    }

    public Staff(int id, String staffCode, String name, String gender, int age, String phone, int dId, int pId, double kpi) {
        this.id = id;
        this.staffCode = staffCode;
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.phone = phone;
        this.dId = dId;
        this.pId = pId;
        this.kpi = kpi;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getdId() {
        return dId;
    }

    public void setdId(int dId) {
        this.dId = dId;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public double getKpi() {
        return kpi;
    }

    public void setKpi(double kpi) {
        this.kpi = kpi;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "id=" + id +
                ", staffCode='" + staffCode + '\'' +
                ", name='" + name + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", phone='" + phone + '\'' +
                ", dId=" + dId +
                ", pId=" + pId +
                ", kpi=" + kpi +
                '}';
    }
}
