package cn.appsys.service.impl;

import cn.appsys.dao.AppInfoDao;
import cn.appsys.pojo.AppInfo;
import cn.appsys.service.AppInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("appInfoService")
public class AppInfoServiceImpl implements AppInfoService {
    @Autowired
    private AppInfoDao appInfoDao;
    //添加
    public int add(AppInfo appInfo) {

        return appInfoDao.add(appInfo);
    }
    //修改
    public int update(AppInfo appInfo) {
        return appInfoDao.update(appInfo);
    }

    public AppInfo searchByAPKName(String APKName) {
        return appInfoDao.searchByAPKName(APKName);
    }
//查看所有
public List<AppInfo> getAppByPage(String SoftwareName,long Status,long FlatformId,long CategoryLeve11,long CategoryLeve12, long CategoryLeve13,long devId,int currentPageNo,int everPageNum,int getSelectEverPageFirst){
    List<AppInfo> appInfoList=appInfoDao.getAppByPage(SoftwareName,Status,FlatformId,CategoryLeve11,CategoryLeve12,CategoryLeve13,devId,currentPageNo,everPageNum,getSelectEverPageFirst);
    System.out.println("++++++++++++++++++++++++++++++++++++++++++SoftwareName:" + SoftwareName);
    System.out.println("+++++++++++++++++++++++++++++++++++++++++++FlatformId:" + FlatformId);
    return appInfoList;
}
//记录总数
public Integer totall(String SoftwareName,long Status,long FlatformId,long CategoryLeve11,long CategoryLeve12, long CategoryLeve13,long devId){
    Integer a=appInfoDao.totall(SoftwareName,Status,FlatformId,CategoryLeve11,CategoryLeve12,CategoryLeve13,devId);
    return a;
}
//通过id查看基本信息
    public AppInfo searchApp(Integer id) {
        return appInfoDao.searchApp(id);
    }



    //审核通过（2）或不通过（3）
    public int updateStatus(long Status,long id) {
        return appInfoDao.updateStatus(Status,id);
    }



    //---------------------------------------------
    public int deleteApp(long appid){
        int flag=0;
        flag= appInfoDao.deleteApp(appid);
        return flag;
    }


    public AppInfo selectAppInfoById(long appIdInteger){
        System.out.println(appIdInteger);
        AppInfo appInfo=appInfoDao.selectAppInfoById(appIdInteger);
        return  appInfo;
    }

    //上下架
    public int updateStatusByAppId(long status,long id){
        int flag=appInfoDao.updateStatusByAppId(status,id);
        return flag;
    }


    public int updateVersionIdByAppId(long appid,long versionId){

        System.out.println(appid+"-----"+versionId);
        int flag=appInfoDao.updateVersionIdByAppId(appid,versionId);
        System.out.println("ndchmb-----------"+flag);
        return flag;
    }

}
