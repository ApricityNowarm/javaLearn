package com.cosmo.manager.entity;

public class Department {
    private int id;
    private int dId;
    private String name;

    public Department() {
    }

    public Department(int id, int dId, String name) {
        this.id = id;
        this.dId = dId;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getdId() {
        return dId;
    }

    public void setdId(int dId) {
        this.dId = dId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Department{" +
                "id=" + id +
                ", dId=" + dId +
                ", name='" + name + '\'' +
                '}';
    }
}
