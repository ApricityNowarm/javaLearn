package com.smbms.bean;

import java.util.Date;

public class User {
    private int id;     //用户id
    private String userCode;    //用户编码
    private String userName;    //用户名
    private String userPassword;    //用户密码
    private int gender;             //用户性别 1：女 2：男
    private Date birthday;  //用户生日
    private String phone;   //用户电话号码
    private String address;  //用户住址
    private int userRole;   //用户角色编号
    private int createdBy;  //创建者id
    private Date creationDate;  //创建时间
    private int modifyBy;   //修改人id
    private Date modifyDate;    //修改时间
    private String roleName;    //用户角色名称

    public User(){
    }

    public User(int id,String userCode,String userName,
                String userPassword,int gender,Date birthday,
                String phone,String address,int userRole,
                int createdBy,Date creationDate,int modifyBy,
                Date modifyDate,String roleName) {
        this.id = id;
        this.userCode = userCode;
        this.userName = userName;
        this.userPassword = userPassword;
        this.gender = gender;
        this.birthday = birthday;
        this.phone = phone;
        this.address = address;
        this.userRole =userRole;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
        this.roleName =roleName;
    }


    public void setId(int id){
        this.id =id;
    }
    public int getId(){
        return id;
    }


    public void setUserCode(String userCode){
        this.userCode = userCode;
    }
    public String getUserCode(){
        return userCode;
    }


    public void setUserName(String userName){
        this.userName = userName;
    }
    public String getUserName(){
        return userName;
    }


    public void setUserPassword(String userPassword){
        this.userPassword = userPassword;
    }
    public String getUserPassword(){
        return userPassword;
    }


    public void setGender(int gender){
        this.gender = gender;
    }
    public int getGender(){
        return gender;
    }


    public void setBirthday(Date birthday){
        this.birthday = birthday;
    }
    public Date getBirthday(){
        return birthday;
    }


    public void setPhone(String phone){
        this.phone = phone;
    }
    public String getPhone(){
        return phone;
    }


    public void setAddress(String address){
        this.address = address;
    }
    public String getAddress(){
        return address;
    }


    public void setUserRole(int id){
        this.userRole = userRole;
    }
    public int getUserRole(){
        return userRole;
    }


    public void setCreatedBy(int createdBy){
        this.createdBy = createdBy;
    }
    public int getCreatedBy(){
        return createdBy;
    }


    public void setCreationDate(Date creationDate){
        this.creationDate = creationDate;
    }
    public Date getCreationDate(){
        return creationDate;
    }


    public void setModifyBy(int modifyBy){
        this.modifyBy = modifyBy;
    }
    public int getModifyBy(){
        return modifyBy;
    }


    public void setModifyDate(Date modifyDate){
        this.modifyDate = modifyDate;
    }
    public Date getModifyDate(){
        return modifyDate;
    }


    public  void setRoleName(String roleName){
        this.roleName = roleName;
    }
    public String getRoleName(){
        return roleName;
    }




}
