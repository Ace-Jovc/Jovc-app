package cn.appsys.dao;

import cn.appsys.pojo.AppCategory;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface AppCategoryDao {
    //根据id查询
    @Select("select categoryName from app_category where id=#{categoryLeve}")
    public String selectAppCategoryById(long categoryLeve);

    //categoryLevel1List
    @Select("select * from app_category where parentId=#{pid}")
    public List<AppCategory> selectCategoryLevel1List(@Param("pid") String pid);

    //categoryLevel2List
    @Select("select * from app_category where parentId=1 OR parentId=2")
    public List<AppCategory> selectCategoryLevel2List();

    //categoryLevel3List
    @Select("select * from app_category where parentId>=3 AND parentId<=8")
    public List<AppCategory> selectCategoryLevel3List();
}
