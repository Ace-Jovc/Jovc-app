package cn.appsys.service.impl;

import cn.appsys.dao.DateDictionDao;
import cn.appsys.dao.DateDictionDao;
import cn.appsys.pojo.DataDictionary;
import cn.appsys.service.DataDictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("dataDictionaryService")
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.DEFAULT)
public class DataDictionaryServiceImpl implements DataDictionaryService{
    @Autowired
    private DateDictionDao dateDictionDao;
    public String selectAppStatusName(long status) {
        return dateDictionDao.selectAppStatusName(status);
    }

    public List<DataDictionary> selectAllStatus() {
        return dateDictionDao.selectAllStatus();
    }

    public List<DataDictionary> selectAllfloar() {
        return dateDictionDao.selectAllfloar();
    }
}
