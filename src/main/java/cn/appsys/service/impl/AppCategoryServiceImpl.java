package cn.appsys.service.impl;

import cn.appsys.dao.AppCategoryDao;
import cn.appsys.pojo.AppCategory;
import cn.appsys.service.AppCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("appCategoryService")
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
public class AppCategoryServiceImpl implements AppCategoryService {
@Autowired
private AppCategoryDao appCategoryDao;
    public String selectAppCategoryById(long categoryLeve) {
        return appCategoryDao.selectAppCategoryById(categoryLeve);
    }

    public List<AppCategory> selectCategoryLevel1List(String pid) {
        return appCategoryDao.selectCategoryLevel1List(pid);
    }

    public List<AppCategory> selectCategoryLevel2List() {
        return appCategoryDao.selectCategoryLevel2List();
    }

    public List<AppCategory> selectCategoryLevel3List() {
        return appCategoryDao.selectCategoryLevel3List();
    }
}
