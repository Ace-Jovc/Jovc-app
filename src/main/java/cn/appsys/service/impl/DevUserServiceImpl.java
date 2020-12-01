package cn.appsys.service.impl;

import cn.appsys.dao.DevUserDao;
import cn.appsys.pojo.DevUser;
import cn.appsys.service.DevUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("DevUserService")
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
public class DevUserServiceImpl implements DevUserService {
    @Autowired
    private DevUserDao devUserDao;

    //通过账号登录
    public DevUser devLogin(String devCode) {
        DevUser devUser=devUserDao.devLogin(devCode);
        return devUser;
    }

    public String selectUserNameByDevIdToAppList(long devId) {
        return devUserDao.selectUserNameByDevIdToAppList(devId);
    }
}