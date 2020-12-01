package cn.appsys.service;

import cn.appsys.pojo.AppInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface AppInfoService {
    //添加
    public int add(AppInfo appInfo);
    //修改
    public int update(AppInfo appInfo);
    //查询APKName是否存在
    public AppInfo searchByAPKName(String APKName);
    //查看allgetAppByPage
    public List<AppInfo> getAppByPage(String SoftwareName,long Status,long FlatformId,long CategoryLeve11,long CategoryLeve12, long CategoryLeve13,long devId, int currentPageNo,int everPageNum,int getSelectEverPageFirst);
    //2.记录总数totall
    public Integer totall(String SoftwareName,long Status,long FlatformId,long CategoryLeve11,long CategoryLeve12, long CategoryLeve13,long devId);
    //通过id查看基本信息
    public AppInfo searchApp(Integer id);
    //审核通过（2）或不通过（3）
    public int updateStatus(long Status,long id);


    //--------------------------------------------
    public int deleteApp(long appid);//删除qpp
    public AppInfo selectAppInfoById(long appIdInteger);
    //上下架
    public int updateStatusByAppId(long status,long id);
    public int updateVersionIdByAppId(long appid,long versionId);
}
