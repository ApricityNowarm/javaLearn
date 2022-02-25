package com.cosmo.manager.entity;

public class Position {
    private int id;
    private int pId;
    private String name;
    private int salary;

    public Position() {
    }

    public Position(int id, int pId, String name, int salary) {
        this.id = id;
        this.pId = pId;
        this.name = name;
        this.salary = salary;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getpId() {
        return pId;
    }

    public void setpId(int pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Position{" +
                "id=" + id +
                ", pId=" + pId +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
}
