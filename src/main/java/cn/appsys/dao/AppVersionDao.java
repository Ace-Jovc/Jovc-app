package cn.appsys.dao;

import cn.appsys.pojo.AppVersion;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

public interface AppVersionDao {
    //查看版本信息，通过id
    @Results({
            @Result(id=true,column = "id",property = "id"),
            /*@Result(column = "appId",property = "appId"),
            @Result(column = "appId",property = "appIdName",one=@One(select="cn.appsys.dao.AppInfoDao.searchById",fetchType= FetchType.EAGER)),*/
            @Result(column = "publishStatus",property = "publishStatus"),
            @Result(column = "publishStatus",property = "publishStatusName",one=@One(select="cn.appsys.dao.DateDictionDao.selectAppStatusName",fetchType= FetchType.EAGER)),
    })

    @Select("SELECT * FROM app_version WHERE appId=#{appId} ")
    public List<AppVersion> getAppVersionList(@Param("appId")Integer appId);
    //通过Appid查询
    @Select("SELECT * FROM app_version WHERE appId=#{appId} ")
    public int searchByAppId(@Param("appId")Integer appId);
    //通过id查询版本号
    @Select("SELECT versionNo FROM app_version WHERE id=#{id}")
    public String searchByVerId(@Param("id")int id);
    //通过id查询最新版本
    @Select("SELECT * FROM app_version WHERE id=#{id}")
    public AppVersion searchById(@Param("id")int id);

    //------------------------------------
    @Delete("delete from app_verion where appId=#{id}")
    public  int delVersion(@Param("id")long id);


    @Select("select * from app_version where appId=#{appId}")
    public List<AppVersion> selectAppVersionByAppId(@Param("appId") long appId);

    //添加app_version
    @Insert("insert into app_version (appId,versionNo,versionInfo,publishStatus,downloadLink,versionSize,createdBy,creationDate,apkLocPath,apkFileName) " +
            "  values (#{appid},#{versionno},#{versioninfo},#{publishstatus},#{a_downloadlink},#{Vsize},#{createdBy},#{creationDate},#{apklocPath},#{apkFileName})")
    public int addAppVersion(@Param("appid") long appid, @Param("versionno")String versionno, @Param("Vsize") BigDecimal Vsize, @Param("publishstatus")long publishstatus,
                             @Param("versioninfo")String versioninfo, @Param("creationDate") Date creationDate, @Param("createdBy") long createdBy,
                             @Param("apkFileName")String apkFileName, @Param("a_downloadlink")String a_downloadlink, @Param("apklocPath")String apklocPath);

    //查找新添加的appversionId
    @Select("select MAX(id) from app_version")
    public long selectNewAppVersionId();

    //修改版本号
    @Update("update app_version set versionNo=#{versionno},versionInfo=#{versioninfo},versionSize=#{Vsize}," +
            " modifyDate=#{modifyDate},modifyBy=#{modifyBy} where id=#{vid}")
    public int updateAppVersion( @Param("versionno")String versionno, @Param("Vsize") BigDecimal Vsize, @Param("versioninfo")String versioninfo,
                                 @Param("modifyDate") Date modifyDate, @Param("modifyBy") long modifyBy, @Param("vid")long vid);


}
