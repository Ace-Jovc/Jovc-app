package cn.appsys.service;

import cn.appsys.pojo.AppCategory;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AppCategoryService {
    //根据id查询

    public String selectAppCategoryById(long categoryLeve);

    //categoryLevel1List

    public List<AppCategory> selectCategoryLevel1List(String pid);

    //categoryLevel2List

    public List<AppCategory> selectCategoryLevel2List();

    //categoryLevel3List

    public List<AppCategory> selectCategoryLevel3List();
}
