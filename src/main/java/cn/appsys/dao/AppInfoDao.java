package cn.appsys.dao;

import cn.appsys.pojo.AppInfo;
import cn.appsys.tools.AppInfoListSql;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/*app基本信息Dao*/
public interface AppInfoDao {
    //显示列表
    // 添加
    @Insert("INSERT INTO app_info (softwareName,APKName,supportROM,interfaceLanguage,softwareSize,updateDate,devId,appInfo,status,onSaleDate,offSaleDate,flatformId," +
            "categoryLeve13,downloads,createdBy,creationDate,modifyBy,modifyDate,categoryLeve11,categoryLeve12,logoPicPath,versionId,logoLocPath)" +
            "VALUES(#{softwareName},#{APKName},#{supportROM},#{interfaceLanguage},#{softwareSize},#{updateDate},#{devId},#{appInfo},#{status},#{onSaleDate}," +
            "#{offSaleDate},#{flatformId},#{categoryLeve13},#{downloads},#{createdBy},#{creationDate},#{modifyBy},#{modifyDate},#{categoryLeve11},#{categoryLeve12}," +
            "#{logoPicPath},#{versionId},#{logoLocPath})")
    public int add(AppInfo appInfo);
    //修改
   @Update("UPDATE app_info SET softwareName = #{softwareName},APKName = #{APKName},supportROM = #{supportROM},interfaceLanguage = #{interfaceLanguage},softwareSize = #{softwareSize}," +
           "appInfo = #{appInfo},status = #{status},flatformId = #{flatformId}," +
           "categoryLeve13 = #{categoryLeve13},downloads = #{downloads}," +
           "categoryLeve11 = #{categoryLeve11},categoryLeve12 = #{categoryLeve12},logoPicPath = #{logoPicPath},logoLocPath = #{logoLocPath} WHERE id = #{id}")
    public int update(AppInfo appInfo);
   //查询APKName是否存在
   @Select("SELECT * FROM app_info WHERE APKName=#{APKName}")
    public AppInfo searchByAPKName(@Param("APKName")String APKName);
   //查看全部app信息
   @Results({
           @Result(id=true,column = "id",property = "id"),
           @Result(column = "softwareName",property = "softwareName"),
           @Result(column = "APKName",property = "APKName"),
           @Result(column = "softwareSize",property = "softwareSize"),
           @Result(column = "downloads",property = "downloads"),
           @Result(column = "devId",property = "devId"),
           @Result(column = "devId",property = "devName",one=@One(select="cn.appsys.dao.DevUserDao.selectUserNameByDevIdToAppList",fetchType= FetchType.EAGER)),
           @Result(column = "flatformId",property = "flatformId"),
           @Result(column = "flatformId",property = "flatformName",one=@One(select="cn.appsys.dao.DateDictionDao.selectAppStatusName",fetchType= FetchType.EAGER)),
           @Result(column = "categoryLeve11",property = "categoryLeve11"),
           @Result(column = "categoryLeve11",property = "categoryLevel1Name",one=@One(select="cn.appsys.dao.AppCategoryDao.selectAppCategoryById",fetchType= FetchType.EAGER)),
           @Result(column = "categoryLeve12",property = "categoryLeve12"),
           @Result(column = "categoryLeve12",property = "categoryLevel2Name",one=@One(select="cn.appsys.dao.AppCategoryDao.selectAppCategoryById",fetchType= FetchType.EAGER)),
           @Result(column = "categoryLeve13",property = "categoryLeve13"),
           @Result(column = "categoryLeve13",property = "categoryLevel3Name",one=@One(select="cn.appsys.dao.AppCategoryDao.selectAppCategoryById",fetchType= FetchType.EAGER)),
           @Result(column = "status",property = "status"),
           @Result(column = "status",property = "statusName",one=@One(select="cn.appsys.dao.DateDictionDao.selectAppStatusName",fetchType= FetchType.EAGER)),
           @Result(column = "versionId",property = "versionId"),
           @Result(column = "versionId",property = "versionNo",one=@One(select="cn.appsys.dao.AppVersionDao.searchByVerId",fetchType= FetchType.EAGER))
   })
   @SelectProvider(type = AppInfoListSql.class,method ="selectAppInfoListByPage")
   public List<AppInfo> getAppByPage(@Param("SoftwareName")String SoftwareName,@Param("Status")long Status,
                                     @Param("FlatformId")long FlatformId,@Param("CategoryLeve11")long CategoryLeve11,
                                     @Param("CategoryLeve12")long CategoryLeve12,@Param("CategoryLeve13")long CategoryLeve13,
                                     @Param("devId")long devId,@Param("currentPageNo")int currentPageNo,
                                     @Param("everPageNum")int everPageNum,@Param("getSelectEverPageFirst")int getSelectEverPageFirst);//传入页码，行数，用户id

    //2.记录总数totall
    @SelectProvider(type =AppInfoListSql.class,method ="selectTotall")
    public Integer totall(@Param("SoftwareName") String SoftwareName,@Param("Status")long Status,
                          @Param("FlatformId")long FlatformId,@Param("CategoryLeve11")long CategoryLeve11,
                          @Param("CategoryLeve12")long CategoryLeve12,@Param("CategoryLeve13")long CategoryLeve13,
                          @Param("devId")long devId);

    //查看基本信息通过id
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "softwareName",property = "softwareName"),
            @Result(column = "APKName",property = "APKName"),
            @Result(column = "supportROM",property = "supportROM"),
            @Result(column = "interfaceLanguage",property = "interfaceLanguage"),
            @Result(column = "softwareSize",property = "softwareSize"),
            @Result(column = "downloads",property = "downloads"),
            @Result(column = "flatformId",property = "flatformId"),
            @Result(column = "flatformId",property = "flatformName",one=@One(select="cn.appsys.dao.DateDictionDao.selectAppStatusName",fetchType= FetchType.EAGER)),
            @Result(column = "categoryLeve11",property = "categoryLeve11"),
            @Result(column = "categoryLeve11",property = "categoryLevel1Name",one=@One(select="cn.appsys.dao.AppCategoryDao.selectAppCategoryById",fetchType= FetchType.EAGER)),
            @Result(column = "categoryLeve12",property = "categoryLeve12"),
            @Result(column = "categoryLeve12",property = "categoryLevel2Name",one=@One(select="cn.appsys.dao.AppCategoryDao.selectAppCategoryById",fetchType= FetchType.EAGER)),
            @Result(column = "categoryLeve13",property = "categoryLeve13"),
            @Result(column = "categoryLeve13",property = "categoryLevel3Name",one=@One(select="cn.appsys.dao.AppCategoryDao.selectAppCategoryById",fetchType= FetchType.EAGER)),
            @Result(column = "status",property = "status"),
            @Result(column = "status",property = "statusName",one=@One(select="cn.appsys.dao.DateDictionDao.selectAppStatusName",fetchType= FetchType.EAGER)),
            @Result(column = "appInfo",property = "appInfo"),
            @Result(column = "logoPicPath",property = "logoPicPath"),
            @Result(column = "logoLocPath",property = "logoLocPath")
    })
    //通过id查看基本信息
    @Select("SELECT * FROM app_info WHERE id=#{id}")
    public AppInfo searchApp(@Param("id")Integer id);//传入

    //审核通过（2）或不通过（3）
    @Update("UPDATE app_info SET status=#{status} WHERE id=#{id} ")
    public int updateStatus(@Param("status")long status,@Param("id")long id);


//删除----------------------------------------------------------------------
    @Delete("delete  from app_info where id=#{id}")
    public int deleteApp(@Param("id")long id);

//查找Appinfo
    @Select("select * from app_info where id=#{appIdInteger}")
    public AppInfo selectAppInfoById(@Param("appIdInteger")long appIdInteger);

    //上下架
    @Update("update app_info set status=#{status} where id=#{id}")
    public int updateStatusByAppId(@Param("status")long status,@Param("id") long id);

    //更新最新版本号
    @Update("update app_info set versionId=#{versionId} where id=#{appid}")
    public int updateVersionIdByAppId(@Param("appid")long appid,@Param("versionId")long versionId);



}


