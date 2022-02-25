package com.cosmo.manager.entity;

public class ImageUser {
    private int id;
    private String userCode;
    private String uri;

    public ImageUser() {
    }

    public ImageUser(int id, String userCode, String uri) {
        this.id = id;
        this.userCode = userCode;
        this.uri = uri;
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

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    @Override
    public String toString() {
        return "ImageUser{" +
                "id=" + id +
                ", userCode='" + userCode + '\'' +
                ", uri='" + uri + '\'' +
                '}';
    }
}
