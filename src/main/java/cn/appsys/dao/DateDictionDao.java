package cn.appsys.dao;

import cn.appsys.pojo.DataDictionary;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface DateDictionDao {
    //根据Id获取名字
    @Select("select valueName from data_dictionary where id=#{status}")
    public String selectAppStatusName(long status);

    //所有Status的值
    @Select("select * from data_dictionary where typeCode='status' ")
    public List<DataDictionary> selectAllStatus();

    //所有floar的值 平台列表
    @Select("select * from data_dictionary where typeCode='floar' ")
    public List<DataDictionary> selectAllfloar();
}
