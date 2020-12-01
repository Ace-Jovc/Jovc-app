package cn.appsys.service;

import cn.appsys.pojo.AppVersion;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface AppVersionService {
    //查看版本信息
    public List<AppVersion> getAppVersionList(Integer appId);
    //通过AppId查询
    public int searchByAppId(Integer appId);
    //通过id查询版本号
    public String searchByVerId(int id);
    //通过id查询最新版本
    public AppVersion searchById(int id);

   ///===========================
   public  int delVersion(long id);


    public List<AppVersion> selectAppVersionByAppId(long appId);

    public int addAppVersion(long appid, String versionno,BigDecimal Vsize, long publishstatus,
                             String versioninfo,Date creationDate, long createdBy,
                             String apkFileName, String a_downloadlink, String apklocPath);



    public long selectNewAppVersionId();


    public int updateAppVersion( String versionno, BigDecimal Vsize, String versioninfo, Date modifyDate,  long modifyBy, long vid);


}
