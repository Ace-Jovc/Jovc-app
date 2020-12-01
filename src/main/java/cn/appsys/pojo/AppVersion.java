package cn.appsys.pojo;

import java.math.BigDecimal;
import java.util.Date;

/*App版本信息表*/
public class AppVersion {
    long id;
    long appId;//app的id（外键app_info{id}）,
    String versionNo;//版本编号
    String versionInfo;//版本信息
    long publishStatus;//发布状态，对应data_dictionary，1 不发布 2 已发布 3 预发布
    String downloadLink;//下载链接
    BigDecimal versionSize;//版本大小
    long createdBy;//创建者id（外键dev_user{id}）
    Date creationDate;//创建日期时间
    long modifyBy;//修改者id（外键dev_user{id}）
    Date modifyDate;//修改时间
    String apkLocPath;//apk在服务器的存储路径
    String apkFileName;//apk文件名

    String appIdName;//软件名
    String publishStatusName;//版本的发布状态名
    public AppVersion() {
    }

    public AppVersion(long id, long appId, String versionNo, String versionInfo, long publishStatus, String downloadLink, BigDecimal versionSize, long createdBy, Date creationDate, long modifyBy, Date modifyDate, String apkLocPath, String apkFileName) {
        this.id = id;
        this.appId = appId;
        this.versionNo = versionNo;
        this.versionInfo = versionInfo;
        this.publishStatus = publishStatus;
        this.downloadLink = downloadLink;
        this.versionSize = versionSize;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
        this.apkLocPath = apkLocPath;
        this.apkFileName = apkFileName;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getAppId() {
        return appId;
    }

    public void setAppId(long appId) {
        this.appId = appId;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    public String getVersionInfo() {
        return versionInfo;
    }

    public void setVersionInfo(String versionInfo) {
        this.versionInfo = versionInfo;
    }

    public long getPublishStatus() {
        return publishStatus;
    }

    public void setPublishStatus(long publishStatus) {
        this.publishStatus = publishStatus;
    }

    public String getDownloadLink() {
        return downloadLink;
    }

    public void setDownloadLink(String downloadLink) {
        this.downloadLink = downloadLink;
    }

    public BigDecimal getVersionSize() {
        return versionSize;
    }

    public void setVersionSize(BigDecimal versionSize) {
        this.versionSize = versionSize;
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

    public String getApkLocPath() {
        return apkLocPath;
    }

    public void setApkLocPath(String apkLocPath) {
        this.apkLocPath = apkLocPath;
    }

    public String getApkFileName() {
        return apkFileName;
    }

    public void setApkFileName(String apkFileName) {
        this.apkFileName = apkFileName;
    }

    public String getAppIdName() {
        return appIdName;
    }

    public void setAppIdName(String appIdName) {
        this.appIdName = appIdName;
    }

    public String getPublishStatusName() {
        return publishStatusName;
    }

    public void setPublishStatusName(String publishStatusName) {
        this.publishStatusName = publishStatusName;
    }

    @Override
    public String toString() {
        return "AppVersion{" +
                "id=" + id +
                ", appId=" + appId +
                ", versionNo='" + versionNo + '\'' +
                ", versionInfo='" + versionInfo + '\'' +
                ", publishStatus=" + publishStatus +
                ", downloadLink='" + downloadLink + '\'' +
                ", versionSize=" + versionSize +
                ", createdBy=" + createdBy +
                ", creationDate=" + creationDate +
                ", modifyBy=" + modifyBy +
                ", modifyDate=" + modifyDate +
                ", apkLocPath='" + apkLocPath + '\'' +
                ", apkFileName='" + apkFileName + '\'' +
                '}';
    }
}
