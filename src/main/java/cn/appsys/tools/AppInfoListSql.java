package cn.appsys.tools;

import cn.appsys.pojo.AppInfo;
import cn.appsys.pojo.DevUser;
import cn.appsys.pojo.PageInfo;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.ui.Model;

import java.util.Map;

public class AppInfoListSql {

    public String selectAppInfoListByPage(@Param("SoftwareName") final String SoftwareName, @Param("Status") final long Status, @Param("FlatformId") final long FlatformId,
                                          @Param("CategoryLeve11") final long CategoryLeve11, @Param("CategoryLeve12") final long CategoryLeve12,
                                          @Param("CategoryLeve13") final long CategoryLeve13, @Param("devId") final long devId, @Param("currentPageNo")int currentPageNo,
                                          @Param("everPageNum")int everPageNum, @Param("getSelectEverPageFirst")int getSelectEverPageFirst){
        //动态查询语句，查询所需要的商品信息列表)   sql语句
        String sql =new SQL() {
            {
                SELECT("*");
                FROM("app_info");
                if(devId > 0){
                    System.out.println(devId);
                    WHERE(" devId=#{devId} ");
                }
                if(SoftwareName !=null && !SoftwareName.equals("")){
                    System.out.println("SoftwareName++++++++++++++++ggghdvhrd+++++++++++++++++++++++++"+SoftwareName);
                    WHERE(" softwareName like CONCAT('%',#{SoftwareName},'%') "); }
                if(Status >0){
                    System.out.println("Status++++++++++"+Status);
                    WHERE(" status=#{Status}"); }
                if(FlatformId >0){
                    System.out.println("FlatformId++++++++++"+FlatformId);
                    WHERE(" flatformId=#{FlatformId}"); }
                if(CategoryLeve11 >0){
                    System.out.println("CategoryLeve11++++++++++"+CategoryLeve11);
                    WHERE(" categoryLeve11=#{CategoryLeve11}"); }
                if(CategoryLeve12 >0){
                    System.out.println("CategoryLeve12++++++++++"+CategoryLeve12);
                    WHERE(" categoryLeve12=#{CategoryLeve12}"); }
                if(CategoryLeve13 >0){
                    System.out.println("CategoryLeve13++++++++++"+CategoryLeve13);
                    WHERE(" categoryLeve13=#{CategoryLeve13}"); }
            }
        }.toString();



        if(currentPageNo > 0 && everPageNum > 0){
            sql+=" limit #{getSelectEverPageFirst},#{everPageNum}";
        }
        System.out.println("++++++++++"+sql);
        return sql;
    }

    //    （根据条件动态查询 查询商品信息列表的  总数) sql语句
    public String  selectTotall(@Param("SoftwareName") final String SoftwareName, @Param("Status") final long Status, @Param("FlatformId") final long FlatformId,
                                @Param("CategoryLeve11") final long CategoryLeve11, @Param("CategoryLeve12") final long CategoryLeve12,
                                @Param("CategoryLeve13") final long CategoryLeve13, @Param("devId") final long devId) {
        return new SQL() {
            {
                SELECT("count(*)");
                FROM("app_info");
                if(devId > 0 ){
                    System.out.println(devId);
                    WHERE(" devId=#{devId} "); }
                if(SoftwareName !=null && !SoftwareName.equals("")){
                    System.out.println("SoftwareName++++++++++++++++ggghdvhrd+++++++++++++++++++++++++"+SoftwareName);
                    WHERE(" softwareName like CONCAT('%',#{SoftwareName},'%') "); }
                if(SoftwareName !=null &&!SoftwareName.equals("")){
                    WHERE(" softwareName like CONCAT('%',#{SoftwareName},'%')"); }
                if(Status >0){
                    WHERE(" status=#{Status}"); }
                if(FlatformId >0){
                    WHERE(" flatformId=#{FlatformId}"); }
                if(CategoryLeve11 >0){
                    WHERE(" categoryLeve11=#{CategoryLeve11}"); }
                if(CategoryLeve12 >0){
                    WHERE(" categoryLeve12=#{CategoryLeve12}"); }
                if(CategoryLeve13 >0){
                    WHERE(" categoryLeve13=#{CategoryLeve13}"); }


            }
        }.toString();
    }
}
