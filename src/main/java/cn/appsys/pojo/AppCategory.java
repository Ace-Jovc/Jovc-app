package cn.appsys.pojo;

import java.util.Date;

/*
* app类别*/
public class AppCategory {
    long id;
    String categoryCode;//类型编码
    String categoryName;//类型名
    long parentId;//父类型id
    long createdBy;//谁创建的(外键backend_user{id})
    Date creationTime;//生成时间
    long modifyBy;//修改人(外键backend_user{id})
    Date modifyDate;//上次修改时间

    public AppCategory() {
    }

    public AppCategory(long id, String categoryCode, String categoryName, long parentId, long createdBy, Date creationTime, long modifyBy, Date modifyDate) {
        this.id = id;
        this.categoryCode = categoryCode;
        this.categoryName = categoryName;
        this.parentId = parentId;
        this.createdBy = createdBy;
        this.creationTime = creationTime;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategoryCode() {
        return categoryCode;
    }

    public void setCategoryCode(String categoryCode) {
        this.categoryCode = categoryCode;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public long getParentId() {
        return parentId;
    }

    public void setParentId(long parentId) {
        this.parentId = parentId;
    }

    public long getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(long createdBy) {
        this.createdBy = createdBy;
    }

    public Date getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(Date creationTime) {
        this.creationTime = creationTime;
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

    @Override
    public String toString() {
        return "AppCategory{" +
                "id=" + id +
                ", categoryCode='" + categoryCode + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", parentId=" + parentId +
                ", createdBy=" + createdBy +
                ", creationTime=" + creationTime +
                ", modifyBy=" + modifyBy +
                ", modifyDate=" + modifyDate +
                '}';
    }
}
