package cn.appsys.dao;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import cn.appsys.pojo.*;

public interface BackendUserDao {
    @Select("select * from backend_user where userCode=#{userCode} and userPassword=#{userPassword}")
    public  BackendUser devLogin(@Param("userCode") String userCode, @Param("userPassword") String userPassword);


}
