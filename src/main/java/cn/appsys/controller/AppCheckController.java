package cn.appsys.controller;

import cn.appsys.pojo.*;
import cn.appsys.service.AppCategoryService;
import cn.appsys.service.AppInfoService;
import cn.appsys.service.AppVersionService;
import cn.appsys.service.DataDictionaryService;
import jdk.jfr.events.ExceptionThrownEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value="/manager/backend/app")
public class AppCheckController {
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private AppCategoryService appCategoryService;
    @Autowired
    private DataDictionaryService dataDictionaryService;
    @Autowired
    private AppVersionService appVersionService;
    //跳转app审核列表
    @RequestMapping("/list")
    public String getAppInfoList(Model model,HttpSession session,
                                 @RequestParam(value="querySoftwareName",required=false) String querySoftwareName,
                                 @RequestParam(value="queryCategoryLevel1",required=false) String queryCategoryLevel1,
                                 @RequestParam(value="queryCategoryLevel2",required=false) String queryCategoryLevel2,
                                 @RequestParam(value="queryCategoryLevel3",required=false) String queryCategoryLevel3,
                                 @RequestParam(value="queryFlatformId",required=false) String queryFlatformId,
                                 @RequestParam(value="pageIndex",required=false) String pageIndex) {
        //appInfo对象 获取前端表单数据 存入map
        Map<String, Object> map = new HashMap<String, Object>();
        String SoftwareName = null;
        long FlatformId = 0;
        long CategoryLeve11 = 0;
        long CategoryLeve12 = 0;
        long CategoryLeve13 = 0;
        if (querySoftwareName != null && !("").equals(querySoftwareName)) {
            SoftwareName = querySoftwareName.toString();
        }
        if (queryFlatformId != null && !queryFlatformId.equals("")) {
            //FlatFformId,1==9  2==10 3==14
            Long flag=Long.parseLong(queryFlatformId);
            if(flag==1){
                FlatformId=9;
            }else if(flag==2){
                FlatformId=10;
            }
            else{
                FlatformId=14;
            }
        }
        if (queryCategoryLevel1 != null && !queryCategoryLevel1.equals("")) {
            CategoryLeve11 = Long.parseLong(queryCategoryLevel1);
        }
        if (queryCategoryLevel2 != null && !queryCategoryLevel2.equals("")) {
            CategoryLeve12 = Long.parseLong(queryCategoryLevel2);
        }
        if (queryCategoryLevel3 != null && !queryCategoryLevel3.equals("")) {
            CategoryLeve13 = Long.parseLong(queryCategoryLevel3);
        }

        //获取前端表单数据
        PageInfo pageInfo = new PageInfo();//分页显示
        //当前页码
        Integer currentPageNo = 1;
        if (pageIndex != null) {
            try {
                currentPageNo = Integer.valueOf(pageIndex);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //总记录数
        int totalPageCount = appInfoService.totall(SoftwareName, 1, FlatformId, CategoryLeve11, CategoryLeve12, CategoryLeve13, 0);//________总记录数
        pageInfo.setTotalCount(totalPageCount);
        pageInfo.setEverPageNum(5);//每页记录数
        int everPageNum = pageInfo.getEverPageNum();
        //控制首页和尾页
        if (currentPageNo < 1) {
            currentPageNo = 1;
        } else if (currentPageNo > totalPageCount) {
            currentPageNo = totalPageCount;
        }
        pageInfo.setCurrentPageNo(currentPageNo);
        int getSelectEverPageFirst = pageInfo.getSelectEverPageFirst();
        int aaa = pageInfo.getTotalPageCount();
        pageInfo.setTotalPageCount(aaa);
        // System.out.println(pageInfo + "===" + getSelectEverPageFirst);
        model.addAttribute("pages",pageInfo);
        //初始化对象
        List<AppInfo> appInfoList = null;
        System.out.println("SoftwareName:" + SoftwareName);
        System.out.println("FlatformId:" + FlatformId);
        //_______________________-AppInfo列表 传入model查询条件  pageInfo分页显示  devUser当前开发者
        appInfoList = appInfoService.getAppByPage(SoftwareName, 1, FlatformId, CategoryLeve11, CategoryLeve12, CategoryLeve13, 0, currentPageNo, everPageNum, getSelectEverPageFirst);
       /* for (AppInfo app : appInfoList) {
            System.out.println("controller:" + app);
        }*/

        model.addAttribute("appInfoList", appInfoList);

        if(querySoftwareName!=null&& !("").equals(querySoftwareName)){
            SoftwareName=querySoftwareName.toString();}
        if(queryFlatformId!=null && !queryFlatformId.equals("")){
            //FlatFformId,1==9  2==10 3==14
            Long flag=Long.parseLong(queryFlatformId);
            if(flag==1){
                FlatformId=9;
            }else if(flag==2){
                FlatformId=10;
            }
            else{
                FlatformId=14;
            }
        }
        if(queryCategoryLevel1!=null && !queryCategoryLevel1.equals("")){
            CategoryLeve11=Long.parseLong(queryCategoryLevel1);}
        if(queryCategoryLevel2!=null && !queryCategoryLevel2.equals("")){
            CategoryLeve12=Long.parseLong(queryCategoryLevel2);}
        if(queryCategoryLevel3!=null && !queryCategoryLevel3.equals("")){
            CategoryLeve13=Long.parseLong(queryCategoryLevel3);}

        //获取平台信息
        List<DataDictionary> floars=dataDictionaryService.selectAllfloar();
        model.addAttribute("flatFormList",floars);
        //获取一级
        List<AppCategory> categoryLevel1List=appCategoryService.selectCategoryLevel1List("0");
        model.addAttribute("categoryLevel1List",categoryLevel1List);
        model.addAttribute("querySoftwareName",SoftwareName);
        model.addAttribute("queryCategoryLevel1",CategoryLeve11);
        model.addAttribute("queryCategoryLevel2",CategoryLeve12);
        model.addAttribute("queryCategoryLevel3",CategoryLeve13);
        model.addAttribute("queryFlatformId",FlatformId);

        //二级分类列表和三级分类列表---回显
        if (queryCategoryLevel2!=null && !queryCategoryLevel2.equals("")) {
            //categoryLevel2List  通一级分类Ajax获取
            System.out.println("queryCategoryLevel2.====="+queryCategoryLevel2.toString());
            List<AppCategory> categoryLevel2List=getCategoryList(queryCategoryLevel1.toString());
            model.addAttribute("categoryLevel2List",categoryLevel2List);
          /*  for (AppCategory appCategory:categoryLevel2List) {
                System.out.println("appCategory12----:"+appCategory);
            }*/
        }
        if (queryCategoryLevel3 != null && !queryCategoryLevel3.equals("")) {
            //categoryLevel3List 通二级分类Ajax获取
            System.out.println("queryCategoryLevel3.====="+queryCategoryLevel3.toString());
            List<AppCategory> categoryLevel3List=getCategoryList(queryCategoryLevel2.toString());
            model.addAttribute("categoryLevel3List",categoryLevel3List);
           /* for (AppCategory appCategory:categoryLevel3List) {
               // System.out.println("appCategory13----:"+appCategory);
            }*/

        }
        //appInfo对象 获取前端表单数据 存入map
        return "/backend/applist";
    }
    public List<AppCategory> getCategoryList(String pid) {

        List<AppCategory> categoryLevelList = null;
        try {
            if(pid == "" || pid == null){
                categoryLevelList =appCategoryService.selectCategoryLevel1List( null);
               /* for (AppCategory appCategory:categoryLevelList) {
                    System.out.println("getCategoryList++++++++++:"+appCategory);
                }*/
            }else{
                categoryLevelList = appCategoryService.selectCategoryLevel1List(pid.toString());}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryLevelList;
    }
    @RequestMapping(value = "/categorylevellist.json")
    @ResponseBody
    public List<AppCategory> getCategoryListToAdd(String pid) {
        List<AppCategory> categoryLevelList = null;
        System.out.println("pid====="+pid);
        try {
            if(pid == "" || pid == null){
                categoryLevelList =appCategoryService.selectCategoryLevel1List( "0");
               /* for (AppCategory appCategory:categoryLevelList) {
                    System.out.println("appCategory----:"+appCategory);
                }*/
            }else{
                categoryLevelList = appCategoryService.selectCategoryLevel1List(pid.toString());}
        } catch (Exception e) {
            e.printStackTrace();
        }
        return categoryLevelList;
    }
    //查询

    //跳转审核列表
    @RequestMapping(value="/check",method= RequestMethod.GET)
    public String check(@RequestParam(value="aid",required=false) String appId,
                        @RequestParam(value="vid",required=false) String versionId,
                        Model model){
        //通过id查看app基本信息
        if(appId!=null){
           AppInfo appInfo= appInfoService.searchApp(Integer.parseInt(appId));
           model.addAttribute("appInfo",appInfo);
        }
        //通过id查看最新版本信息
        if(versionId!=null){
            AppVersion appVersion=appVersionService.searchById(Integer.parseInt(versionId));
            model.addAttribute("appVersion",appVersion);
        }
        return "/backend/appcheck";
    }
    //审核
    @RequestMapping(value="/checksave",method=RequestMethod.POST)
    public String checkSave(AppInfo appInfo,@RequestParam(value="status",required=false) String status){
        //return "redirect:/manager/backend/app/list";
            System.out.println("审核中的status值：" +status+"appInfo:"+appInfo);
            try {
                if(status!=null&&appInfo.getId()>0){

                    appInfoService.updateStatus(Integer.parseInt(status),appInfo.getId());
                    return "redirect:/manager/backend/app/list";
                }
            }catch (Exception e){
                e.printStackTrace();
            }

        return "/backend/appcheck";
    }
 }
