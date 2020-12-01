package cn.appsys.service;

import cn.appsys.pojo.DataDictionary;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DataDictionaryService {
    //根据Id获取名字

    public String selectAppStatusName(long status);

    //所有Status的值

    public List<DataDictionary> selectAllStatus();

    //所有floar的值 平台列表

    public List<DataDictionary> selectAllfloar();
}
