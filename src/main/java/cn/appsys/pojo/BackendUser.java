package cn.appsys.pojo;

import java.util.Date;

/*超级管理员信息表*/
public class BackendUser {
    long id;
    String userCode;//用户编码
    String userName;//姓名
    String userType;//角色类型id,对应date_dictionary
    long createdBy;//创建者
    Date creationDate;//创建时间
    long modifyBy;//更新者
    Date modifyDate;//更新时间
    String userPassword;//登录密码
    /*String userTypeName;//类型名字，管理员*/

    public BackendUser() {
    }

    public BackendUser(long id, String userCode, String userName, String userType, long createdBy, Date creationDate, long modifyBy, Date modifyDate, String userPassword) {
        this.id = id;
        this.userCode = userCode;
        this.userName = userName;
        this.userType = userType;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
        this.userPassword = userPassword;
    }

   /* public String getUserTypeName() {
        return userTypeName;
    }

    public void setUserTypeName(String userTypeName) {
        this.userTypeName = userTypeName;
    }*/

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public long getModifyBy() {
        return modifyBy;
    }

    public void setModifyBy(long modifyBy) {
        this.modifyBy = modifyBy;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "BackendUser{" +
                "id=" + id +
                ", userCode='" + userCode + '\'' +
                ", userName='" + userName + '\'' +
                ", userType='" + userType + '\'' +
                ", createdBy=" + createdBy +
                ", creationDate=" + creationDate +
                ", modifyBy=" + modifyBy +
                ", modifyDate=" + modifyDate +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }
}
