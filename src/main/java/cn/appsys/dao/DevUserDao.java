package cn.appsys.dao;

import cn.appsys.pojo.DevUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

public interface DevUserDao {
    //登录，通过开发者的账号
    @Select("select * from dev_user where devCode=#{devCode}")
    public DevUser devLogin(@Param("devCode") String devCode);

    @Select("select devName from dev_user where id=#{devId}")
    public String selectUserNameByDevIdToAppList(long devId);
}
