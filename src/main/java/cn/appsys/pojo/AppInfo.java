package cn.appsys.pojo;

import java.math.BigDecimal;
import java.util.Date;

/*App基本信息表*/
public class AppInfo {
    long id;
    String softwareName;//软件名
    String APKName;//APK名
    String supportROM;//支持的ROM
    String interfaceLanguage;//界面所用语言
    BigDecimal softwareSize;//软件的大小(Mb)
    Date updateDate;//更新时间1
    long  devId;//开发者Id1
    String appInfo;//app基本信息,应用简介
    long status;//状态（对应：data_dictionary，1 待审核 2 审核通过 3 审核不通过 4 已上架 5 已下架）
    Date onSaleDate;//上架时间1
    Date offSaleDate;//下架时间1
    long flatformId;//平台类型(对应date_dictionary，1，手机，2，平板，3，通用)
    long categoryLeve13;//三级分类id（来自data_dictionary）
    long downloads;//下载量
    long createdBy;//谁添加的(对应dev_user{id})1
    Date creationDate;//添加时间1
    long modifyBy;//更新者id（对应dev_user{id}）1
    Date modifyDate;//更新时间1
    long categoryLeve11;//一级分类id（来自data_dictionary）
    long categoryLeve12;//二级分类id（来自data_dictionary）
    String  logoPicPath;//logo在web服务器存储路径
    String logoLocPath;//logo存储在本地电脑的路径
    long versionId;//版本id

    private String statusName;//app状态名称
    private String flatformName;//app所属平台名称
    private String categoryLevel3Name;//所属三级分类名称
    private String devName;//开发者名称
    private String categoryLevel1Name;//所属一级分类名称
    private String categoryLevel2Name;//所属二级分类名称
    private String versionNo;//最新的版本号

    DataDictionary dataDictionary;//关联app所属状态status，发布平台1
    AppVersion appVersion;//关联app版本

    public AppInfo() {
    }

    public AppInfo(long id, String softwareName, String APKName, String supportROM, String interfaceLanguage, BigDecimal softwareSize, Date updateDate, long devId, String appInfo, long status, Date onSaleDate, Date offSaleDate, long flatformId, long categoryLeve13, long downloads, long createdBy, Date creationDate, long modifyBy, Date modifyDate, long categoryLeve11, long categoryLeve12, String logoPicPath, String logoLocPath, long versionId, DataDictionary dataDictionary, AppVersion appVersion) {
        this.id = id;
        this.softwareName = softwareName;
        this.APKName = APKName;
        this.supportROM = supportROM;
        this.interfaceLanguage = interfaceLanguage;
        this.softwareSize = softwareSize;
        this.updateDate = updateDate;
        this.devId = devId;
        this.appInfo = appInfo;
        this.status = status;
        this.onSaleDate = onSaleDate;
        this.offSaleDate = offSaleDate;
        this.flatformId = flatformId;
        this.categoryLeve13 = categoryLeve13;
        this.downloads = downloads;
        this.createdBy = createdBy;
        this.creationDate = creationDate;
        this.modifyBy = modifyBy;
        this.modifyDate = modifyDate;
        this.categoryLeve11 = categoryLeve11;
        this.categoryLeve12 = categoryLeve12;
        this.logoPicPath = logoPicPath;
        this.logoLocPath = logoLocPath;
        this.versionId = versionId;
        this.dataDictionary = dataDictionary;
        this.appVersion = appVersion;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSoftwareName() {
        return softwareName;
    }

    public void setSoftwareName(String softwareName) {
        this.softwareName = softwareName;
    }

    public String getAPKName() {
        return APKName;
    }

    public void setAPKName(String APKName) {
        this.APKName = APKName;
    }

    public String getSupportROM() {
        return supportROM;
    }

    public void setSupportROM(String supportROM) {
        this.supportROM = supportROM;
    }

    public String getInterfaceLanguage() {
        return interfaceLanguage;
    }

    public void setInterfaceLanguage(String interfaceLanguage) {
        this.interfaceLanguage = interfaceLanguage;
    }

    public BigDecimal getSoftwareSize() {
        return softwareSize;
    }

    public void setSoftwareSize(BigDecimal softwareSize) {
        this.softwareSize = softwareSize;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public long getDevId() {
        return devId;
    }

    public void setDevId(long devId) {
        this.devId = devId;
    }

    public String getAppInfo() {
        return appInfo;
    }

    public void setAppInfo(String appInfo) {
        this.appInfo = appInfo;
    }

    public long getStatus() {
        return status;
    }

    public void setStatus(long status) {
        this.status = status;
    }

    public Date getOnSaleDate() {
        return onSaleDate;
    }

    public void setOnSaleDate(Date onSaleDate) {
        this.onSaleDate = onSaleDate;
    }

    public Date getOffSaleDate() {
        return offSaleDate;
    }

    public void setOffSaleDate(Date offSaleDate) {
        this.offSaleDate = offSaleDate;
    }

    public long getFlatformId() {
        return flatformId;
    }

    public void setFlatformId(long flatformId) {
        this.flatformId = flatformId;
    }

    public long getCategoryLeve13() {
        return categoryLeve13;
    }

    public void setCategoryLeve13(long categoryLeve13) {
        this.categoryLeve13 = categoryLeve13;
    }

    public long getDownloads() {
        return downloads;
    }

    public void setDownloads(long downloads) {
        this.downloads = downloads;
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

    public long getCategoryLeve11() {
        return categoryLeve11;
    }

    public void setCategoryLeve11(long categoryLeve11) {
        this.categoryLeve11 = categoryLeve11;
    }

    public long getCategoryLeve12() {
        return categoryLeve12;
    }

    public void setCategoryLeve12(long categoryLeve12) {
        this.categoryLeve12 = categoryLeve12;
    }

    public String getLogoPicPath() {
        return logoPicPath;
    }

    public void setLogoPicPath(String logoPicPath) {
        this.logoPicPath = logoPicPath;
    }

    public String getLogoLocPath() {
        return logoLocPath;
    }

    public void setLogoLocPath(String logoLocPath) {
        this.logoLocPath = logoLocPath;
    }

    public long getVersionId() {
        return versionId;
    }

    public void setVersionId(long versionId) {
        this.versionId = versionId;
    }

    public DataDictionary getDataDictionary() {
        return dataDictionary;
    }

    public void setDataDictionary(DataDictionary dataDictionary) {
        this.dataDictionary = dataDictionary;
    }

    public AppVersion getAppVersion() {
        return appVersion;
    }

    public void setAppVersion(AppVersion appVersion) {
        this.appVersion = appVersion;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public String getFlatformName() {
        return flatformName;
    }

    public void setFlatformName(String flatformName) {
        this.flatformName = flatformName;
    }

    public String getCategoryLevel3Name() {
        return categoryLevel3Name;
    }

    public void setCategoryLevel3Name(String categoryLevel3Name) {
        this.categoryLevel3Name = categoryLevel3Name;
    }

    public String getDevName() {
        return devName;
    }

    public void setDevName(String devName) {
        this.devName = devName;
    }

    public String getCategoryLevel1Name() {
        return categoryLevel1Name;
    }

    public void setCategoryLevel1Name(String categoryLevel1Name) {
        this.categoryLevel1Name = categoryLevel1Name;
    }

    public String getCategoryLevel2Name() {
        return categoryLevel2Name;
    }

    public void setCategoryLevel2Name(String categoryLevel2Name) {
        this.categoryLevel2Name = categoryLevel2Name;
    }

    public String getVersionNo() {
        return versionNo;
    }

    public void setVersionNo(String versionNo) {
        this.versionNo = versionNo;
    }

    @Override
    public String toString() {
        return "AppInfo{" +
                "id=" + id +
                ", softwareName='" + softwareName + '\'' +
                ", APKName='" + APKName + '\'' +
                ", supportROM='" + supportROM + '\'' +
                ", interfaceLanguage='" + interfaceLanguage + '\'' +
                ", softwareSize=" + softwareSize +
                ", updateDate=" + updateDate +
                ", devId=" + devId +
                ", appInfo='" + appInfo + '\'' +
                ", status=" + status +
                ", onSaleDate=" + onSaleDate +
                ", offSaleDate=" + offSaleDate +
                ", flatformId=" + flatformId +
                ", categoryLeve13=" + categoryLeve13 +
                ", downloads=" + downloads +
                ", createdBy=" + createdBy +
                ", creationDate=" + creationDate +
                ", modifyBy=" + modifyBy +
                ", modifyDate=" + modifyDate +
                ", categoryLeve11=" + categoryLeve11 +
                ", categoryLeve12=" + categoryLeve12 +
                ", logoPicPath='" + logoPicPath + '\'' +
                ", logoLocPath='" + logoLocPath + '\'' +
                ", versionId=" + versionId +
                ", dataDictionary=" + dataDictionary +
                ", appVersion=" + appVersion +
                '}';
    }
}
