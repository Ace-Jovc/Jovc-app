package cn.appsys.service.impl;

import cn.appsys.dao.AppVersionDao;
import cn.appsys.pojo.AppVersion;
import cn.appsys.service.AppVersionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
@Service("appVersionService")
public class AppVersionServiceImpl implements AppVersionService {
    @Autowired
    private AppVersionDao appVersionDao;
    //查询版本信息，通过id
    public List<AppVersion> getAppVersionList(Integer appId) {
        return appVersionDao.getAppVersionList(appId);
    }
//通过id查询
    public int searchByAppId(Integer appId) {
        return appVersionDao.searchByAppId(appId);
    }

    public String searchByVerId(int id) {
        return appVersionDao.searchByVerId(id);
    }
    //通过id查询最新版本
    public AppVersion searchById(int id) {
        return appVersionDao.searchById(id);
    }


    //============================================================
    public  int delVersion(long id){
        int flag=appVersionDao.delVersion(id);
        return flag;
    }



    public List<AppVersion> selectAppVersionByAppId(long appId){
        List<AppVersion> appVersionList=appVersionDao.selectAppVersionByAppId(appId);
        return  appVersionList;
    }

    public int addAppVersion(long appid, String versionno, BigDecimal Vsize, long publishstatus,
                             String versioninfo, Date creationDate, long createdBy,
                             String apkFileName, String a_downloadlink, String apklocPath){

        System.out.println("cehsisisissisisi"+appid);
        int flag=appVersionDao.addAppVersion(appid,versionno,Vsize,publishstatus,versioninfo,creationDate,createdBy,apkFileName,a_downloadlink,apklocPath);
        return flag;
    }


    public long selectNewAppVersionId(){
        long versionId=appVersionDao.selectNewAppVersionId();
        return versionId;
    }

    public int updateAppVersion( String versionno, BigDecimal Vsize, String versioninfo, Date modifyDate,  long modifyBy, long vid)
    {
        int flag=appVersionDao.updateAppVersion(versionno,Vsize,versioninfo,modifyDate,modifyBy,vid);
   return  flag;
    }


}
