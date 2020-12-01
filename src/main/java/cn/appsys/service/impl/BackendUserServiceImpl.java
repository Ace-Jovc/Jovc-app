package cn.appsys.service.impl;

import cn.appsys.dao.BackendUserDao;
import cn.appsys.pojo.BackendUser;
import cn.appsys.service.BackendUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service("backendUserService")
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
public class BackendUserServiceImpl implements BackendUserService {

    @Autowired
    private BackendUserDao backendUserDao;

    public BackendUser devLogin(String userCode,String userPassword){
        BackendUser backendUser=backendUserDao.devLogin(userCode,userPassword);
        return  backendUser;
    }

}
