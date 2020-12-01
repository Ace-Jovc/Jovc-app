package cn.appsys.service;

import cn.appsys.pojo.DevUser;
import org.apache.ibatis.annotations.Param;

public interface DevUserService {
    public DevUser devLogin(String devCode);

    public String selectUserNameByDevIdToAppList(long devId);
}
