package cn.appsys.controller;

import cn.appsys.pojo.*;
import cn.appsys.service.AppCategoryService;
import cn.appsys.service.AppInfoService;
import cn.appsys.service.AppVersionService;
import cn.appsys.service.DataDictionaryService;
import cn.appsys.tools.Constants;
import com.mysql.cj.util.StringUtils;
import com.alibaba.fastjson.JSONArray;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value="/dev/flatform/app")
public class AppController {
    //用户的值devUserSession
    @Autowired
    private AppInfoService appInfoService;
    @Autowired
    private AppCategoryService appCategoryService;
    @Autowired
    private DataDictionaryService dataDictionaryService;
    @Autowired
    private AppVersionService appVersionService;
    @RequestMapping("/list")
    public String getAppInfoBypage(Model model, @RequestParam(value = "querySoftwareName", required = false) String querySoftwareName, HttpSession httpSession,
                                   @RequestParam(value = "queryStatus", required = false) String queryStatus, @RequestParam(value = "queryFlatformId", required = false) String queryFlatformId,
                                   @RequestParam(value = "queryCategoryLevel1", required = false) String queryCategoryLevel1, @RequestParam(value = "queryCategoryLevel2", required = false) String queryCategoryLevel2,
                                   @RequestParam(value = "queryCategoryLevel3", required = false) String queryCategoryLevel3, @RequestParam(value = "pageIndex", required = false) String pageIndex) {

        System.out.println("通用平台的值queryFlatformId="+queryFlatformId);
        //appInfo对象 获取前端表单数据 存入map
        Map<String, Object> map = new HashMap<String, Object>();
        String SoftwareName = null;
        long Status = 0;
        long FlatformId = 0;
        long CategoryLeve11 = 0;
        long CategoryLeve12 = 0;
        long CategoryLeve13 = 0;
        if (querySoftwareName != null && !("").equals(querySoftwareName)) {
            SoftwareName = querySoftwareName.toString();
        }
        if (queryStatus != null && !("").equals(queryStatus)) {
            Status = Long.parseLong(queryStatus);
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
            System.out.println("通用平台的值FlatformId==="+FlatformId);
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

        DevUser devUser = (DevUser) httpSession.getAttribute("devUserSession");
        model.addAttribute("devUserSession", devUser);
        long devId = devUser.getId();
        //System.out.println(devUser);


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
        int totalPageCount = appInfoService.totall(SoftwareName, Status, FlatformId, CategoryLeve11, CategoryLeve12, CategoryLeve13, devId);//________总记录数
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
        //_______________________-AppInfo列表 传入model查询条件  pageInfo分页显示  devUser当前开发者
        appInfoList = appInfoService.getAppByPage(SoftwareName, Status, FlatformId, CategoryLeve11, CategoryLeve12, CategoryLeve13, devId, currentPageNo, everPageNum, getSelectEverPageFirst);
       /* for (AppInfo app : appInfoList) {
            System.out.println("controller:" + app);
        }*/

        model.addAttribute("appInfoList", appInfoList);

        //appInfo对象 获取前端表单数据 存入map
        if(querySoftwareName!=null&& !("").equals(querySoftwareName)){
            SoftwareName=querySoftwareName.toString();}
        if(queryStatus!=null&& !("").equals(queryStatus)){
            Status=Long.parseLong(queryStatus);}
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
            System.out.println("通用平台的值查询FlatformId==="+FlatformId);}
        if(queryCategoryLevel1!=null && !queryCategoryLevel1.equals("")){
            CategoryLeve11=Long.parseLong(queryCategoryLevel1);}
        if(queryCategoryLevel2!=null && !queryCategoryLevel2.equals("")){
            CategoryLeve12=Long.parseLong(queryCategoryLevel2);}
        if(queryCategoryLevel3!=null && !queryCategoryLevel3.equals("")){
            CategoryLeve13=Long.parseLong(queryCategoryLevel3);}

        //获取status状态信息
        List<DataDictionary> statuss=dataDictionaryService.selectAllStatus();
        model.addAttribute("statusList",statuss);
        //获取平台信息
        List<DataDictionary> floars=dataDictionaryService.selectAllfloar();
        model.addAttribute("flatFormList",floars);
        //获取一级
        List<AppCategory> categoryLevel1List=appCategoryService.selectCategoryLevel1List("0");
        model.addAttribute("categoryLevel1List",categoryLevel1List);
        model.addAttribute("queryStatus",Status);
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
       return "/developer/appinfolist";
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
    @RequestMapping(value = "categorylevellist.json")
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
//查询平台列表.json
@RequestMapping(value = "datadictionarylist.json")
@ResponseBody
    public List<DataDictionary> allFolatList(Model model){
        List<DataDictionary> APP_FLATFORM=dataDictionaryService.selectAllfloar();
        model.addAttribute("APP_FLATFORM",APP_FLATFORM);
        return APP_FLATFORM;
    }
    //跳转到添加页面
    @RequestMapping(value="/appinfoadd",method= RequestMethod.GET)
    public String add(@ModelAttribute("appInfo") AppInfo appInfo){
        return "/developer/appinfoadd";
    }


    //添加appinfoaddsave
    //添加appinfoaddsave
    @RequestMapping("/appinfoaddsave")
    public String addAppInfo(AppInfo appInfo, @RequestParam(value = "categoryLevel1",required = false) String categoryLevel1,@RequestParam(value = "categoryLevel2",required = false) String categoryLevel2,
                             @RequestParam(value = "categoryLevel3",required = false) String categoryLevel3,
                             @RequestParam(value = "a_logoPicPath",required = false) MultipartFile attach,
                             HttpServletRequest request, Model model) {
        System.out.println("进入添加。。。");
        System.out.println("添加查看123级：" + "\t1:" + categoryLevel1 + "\t2:" + categoryLevel2 + "\t3:" + categoryLevel3);
        appInfo.setCategoryLeve11(Integer.parseInt(categoryLevel1));
        appInfo.setCategoryLeve12(Integer.parseInt(categoryLevel2));
        appInfo.setCategoryLeve13(Integer.parseInt(categoryLevel3));
        //获取当前用户的值
        HttpSession session = request.getSession();
        DevUser devUser = (DevUser) session.getAttribute(Constants.DEV_USER_SESSION);
        System.out.println("开发者的姓名：" + devUser.getDevName() + "===app的名字：" + appInfo.getSoftwareName());
        if (devUser != null) {
            appInfo.setDevId(devUser.getId());
        } else {
            appInfo.setDevId(1000);
        }
        appInfo.setVersionId(0);
        Date createDate = new Date();
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");
        System.out.println("时间" + format1.format(createDate));
        appInfo.setCreationDate(createDate);
        int flatform = (int) appInfo.getFlatformId();
        System.out.println("平台id:flatform=" + flatform);
        //1=9,2=10,3=14
        if (flatform == 1) {
            appInfo.setFlatformId(9);
        } else if (flatform == 2) {
            appInfo.setFlatformId(10);
        } else {
            appInfo.setFlatformId(14);
        }
        String logoPicPath =  null;
        String logoLocPath =  null;
        if(!attach.isEmpty()){
            String path = request.getSession().getServletContext().getRealPath("statics"+ File.separator+"uploadfiles");

            String oldFileName = attach.getOriginalFilename();//原文件名
            String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
            int filesize = 500000;
            if(attach.getSize() > filesize){//上传大小不得超过 50k
                request.setAttribute("fileUploadError", Constants.FILEUPLOAD_ERROR_4);
                return "developer/appinfoadd";
            }else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
                    ||prefix.equalsIgnoreCase("jepg") || prefix.equalsIgnoreCase("pneg")){//上传图片格式
                String fileName = appInfo.getAPKName() + ".jpg";//上传LOGO图片命名:apk名称.apk
                File targetFile = new File(path,fileName);
                if(!targetFile.exists()){
                    targetFile.mkdirs();
                }
                try {
                    attach.transferTo(targetFile);
                } catch (Exception e) {

                    e.printStackTrace();
                    request.setAttribute("fileUploadError", Constants.FILEUPLOAD_ERROR_2);
                    return "developer/appinfoadd";
                }
                logoPicPath = request.getContextPath()+"/statics/uploadfiles/"+fileName;
                logoLocPath = path+File.separator+fileName;
            }else{
                request.setAttribute("fileUploadError", Constants.FILEUPLOAD_ERROR_3);
                return "developer/appinfoadd";
            }
        }
        appInfo.setCreatedBy(((DevUser)session.getAttribute(Constants.DEV_USER_SESSION)).getId());
        appInfo.setCreationDate(new Date());
        appInfo.setLogoPicPath(logoPicPath);
        appInfo.setLogoLocPath(logoLocPath);
        appInfo.setStatus(1);
        try {
            int flag=appInfoService.add(appInfo);
            if(flag==1){
                return "redirect:/dev/flatform/app/list";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "developer/appinfoadd";
    }
    //查找APKName不可以重复
    /*noexist可以用
    empty为空
    exist存在*/
    @RequestMapping(value="/apkexist.json",method=RequestMethod.GET)
    @ResponseBody
    public Map searchByAPKName(@RequestParam(value = "APKName",required = false) String APKName){
       System.out.println("APKName:"+APKName);
        Map resultMap = new HashMap();
        if(StringUtils.isNullOrEmpty(APKName)){
            resultMap.put("APKName", "empty");
        }else{
            AppInfo appInfo = null;
            try {
                appInfo = appInfoService.searchByAPKName( APKName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            if(null != appInfo)
                resultMap.put("APKName", "exist");
            else
                resultMap.put("APKName", "noexist");
        }
        return resultMap;
    }
    //通过id查询app基本信息appInfo,为了修改
    @RequestMapping(value = "appinfomodify")
    public String updateById(Integer id,@RequestParam(value="error",required= false)String fileUploadError,Model model, HttpSession session){
        //System.out.println("id=="+id);
        AppInfo appInfo = null;

        if(null != fileUploadError && fileUploadError.equals("error1")){
            fileUploadError = Constants.FILEUPLOAD_ERROR_1;
        }else if(null != fileUploadError && fileUploadError.equals("error2")){
            fileUploadError	= Constants.FILEUPLOAD_ERROR_2;
        }else if(null != fileUploadError && fileUploadError.equals("error3")){
            fileUploadError = Constants.FILEUPLOAD_ERROR_3;
        }else if(null != fileUploadError && fileUploadError.equals("error4")){
            fileUploadError = Constants.FILEUPLOAD_ERROR_4;
        }

        appInfo=appInfoService.searchApp(id);
        int flatformNo=3;//来返回所属平台的9=1,10=2,14=3
        int flatformId=(int)appInfo.getFlatformId();
        if(flatformId==9){
            flatformNo=1;
        }
        else if(flatformId==10){
            flatformNo=2;
        }
        model.addAttribute("flatformNo",flatformNo);
        //System.out.println("来返回所属平台的flatformId:"+flatformId);
        //把状态值保存下来
        int stat=3;
        stat=(int)appInfo.getStatus();
        System.out.println("把状态值保存下来:"+stat);
        session.setAttribute("stat",stat);
        if(appInfo!=null){
            System.out.println("通过id查询app成功"+appInfo);
            model.addAttribute("appInfo",appInfo);
            model.addAttribute("fileUploadError",flatformId);
            return "/developer/appinfomodify";
        }
        else{
            System.out.println("通过id查询app失败");
            return "/developer/appinfolist";
        }

    }
    //修改信息
    @RequestMapping(value="/appinfomodifysave",method=RequestMethod.POST)
    public String modifySave(AppInfo appInfo,HttpSession session,HttpServletRequest request,
                             @RequestParam(value="attach",required= false) MultipartFile attach,@RequestParam(value = "categoryLevel1",required = false) String categoryLevel1,@RequestParam(value = "categoryLevel2",required = false) String categoryLevel2,@RequestParam(value = "categoryLevel3",required = false) String categoryLevel3){
        System.out.println("进入修改。。。。");
        System.out.println("添加查看123级："+"\t1:"+categoryLevel1+"\t2:"+categoryLevel2+"\t3:"+categoryLevel3);
        appInfo.setCategoryLeve11(Integer.parseInt(categoryLevel1));
        appInfo.setCategoryLeve12(Integer.parseInt(categoryLevel2));
        appInfo.setCategoryLeve13(Integer.parseInt(categoryLevel3));
        int flatform= (int)appInfo.getFlatformId();
        System.out.println("平台id:flatform="+flatform);
        //1=9,2=10,3=14
        if(flatform==1){
            appInfo.setFlatformId(9);
        }
        else if(flatform==2){
            appInfo.setFlatformId(10);
        }
        else {
            appInfo.setFlatformId(14);
        }
        HttpSession session1 = request.getSession();
        Object stat=session1.getAttribute("stat");
        int status=Integer.parseInt(stat.toString());;
        //System.out.println("status==="+status);
        appInfo.setStatus(status);
        String logoPicPath =  null;
        String logoLocPath =  null;
        String APKName = appInfo.getAPKName();
        if(!attach.isEmpty()){
            String path = request.getSession().getServletContext().getRealPath("statics"+File.separator+"uploadfiles");

            String oldFileName = attach.getOriginalFilename();//原文件名
            String prefix = FilenameUtils.getExtension(oldFileName);//原文件后缀
            int filesize = 500000;
            if(attach.getSize() > filesize){//上传大小不得超过 50k
                return "redirect:/dev/flatform/app/appinfomodify?id="+appInfo.getId()
                        +"&error=error4";
            }else if(prefix.equalsIgnoreCase("jpg") || prefix.equalsIgnoreCase("png")
                    ||prefix.equalsIgnoreCase("jepg") || prefix.equalsIgnoreCase("pneg")){//上传图片格式
                String fileName = APKName + ".jpg";//上传LOGO图片命名:apk名称.apk
                File targetFile = new File(path,fileName);
                if(!targetFile.exists()){
                    targetFile.mkdirs();
                }
                try {
                    attach.transferTo(targetFile);
                } catch (Exception e) {
                    e.printStackTrace();
                    return "redirect:/dev/flatform/app/appinfomodify?id="+appInfo.getId()
                            +"&error=error2";
                }
                logoPicPath = request.getContextPath()+"/statics/uploadfiles/"+fileName;
                logoLocPath = path+File.separator+fileName;
            }
        }
        try{
            appInfoService.update(appInfo);
            return "redirect:/dev/flatform/app/list";
        }catch (Exception e){
            return "/developer/appinfomodify";
        }
    }
    //查看app基本信息和版本信息
    @RequestMapping(value="/appview/{id}",method=RequestMethod.GET)
    public String view(@PathVariable String id,Model model){
        System.out.println("查看app基本信息和版本信息....");
        AppInfo appInfo=null;
        List<AppVersion> appVersionList=null;
        try {
            appInfo=appInfoService.searchApp(Integer.parseInt(id));
            model.addAttribute("appInfo",appInfo);
           // int flag=appVersionService.searchByAppId(400);
           // System.out.println("flag="+flag);
            //if(flag==1){
            //保存软件的名字
            String appIdName=null;
                    appVersionList=appVersionService.getAppVersionList(Integer.parseInt(id));
                if(appVersionList!=null){
                     appIdName= appInfo.getSoftwareName();
                     model.addAttribute("appIdName",appIdName);
                }
                System.out.println("显示版本信息中。。。。");
               /* for(AppVersion a:appVersionList){
                    System.out.println(a);
                }*/
            model.addAttribute("appIdName",appIdName);
                model.addAttribute("appVersionList", appVersionList);
            //}
        }catch (Exception e){
            e.printStackTrace();
        }
        return "/developer/appinfoview";
    }


//------------------------------------------------------------

    @RequestMapping(value = "delapp.json", method = RequestMethod.GET)
    @ResponseBody
    public Map delApp(@RequestParam(value = "id") Integer id) {
        HashMap<String, Object> map = new HashMap<String, Object>();
        try {
            if (StringUtils.isNullOrEmpty(id.toString())) {
                map.put("delResult", "notexist");
            } else {
                if (appInfoService.deleteApp(id) == 1) {
                    if (appVersionService.delVersion(id) != -1) {
                        map.put("delResult", "true");
                    } else {
                        map.put("delResult", "false");
                    }
                } else {
                    map.put("delResult", "false");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("delResult", "false");
        }
        return map;//JSONArray.toJSONString(map);
    }

    //上下架    先获取appinfo   判断如果已经上架4，就下架5，如果下架 ，就上架，如果审核通过2时，可以进行上架操作
    @RequestMapping(value = "{appid}/sale.json", method = RequestMethod.PUT)
    @ResponseBody
    public Object sale(@PathVariable String appid, HttpSession session) {
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        long appIdInteger = 0;
        try {
            appIdInteger = Long.parseLong(appid);
        } catch (Exception e) {
            appIdInteger = 0;
        }
        System.out.println("11111111" + appIdInteger);
        resultMap.put("errorCode", "0");
        resultMap.put("appId", appid);
        if (appIdInteger > 0) {
            try {
                System.out.println("12222222222222222222222222222222222222222222222222222222222222222222222" + appIdInteger);
                DevUser devUser = (DevUser) session.getAttribute("devUserSession");
                AppInfo appInfo = new AppInfo();
                appInfo = appInfoService.selectAppInfoById(appIdInteger);//获取appInfo ById
                System.out.println("13333333333333333333333333333333333333333333333333333333333333333333333333" + appInfo);
                long id = appInfo.getId();
                if (appInfo.getStatus() == 2) {//如果审核通过5时，可以上架
                    long status = 4;
                    int flag = 0;
                    flag = appInfoService.updateStatusByAppId(status, id);

                    if (flag == 1) {
                        resultMap.put("resultMsg", "success");
                    }
                } else if (appInfo.getStatus() == 5) {//如果下架时，可以上架
                    long status = 4;
                    int flag = 0;
                    flag = appInfoService.updateStatusByAppId(status, id);
                    if (flag == 1) {
                        resultMap.put("resultMsg", "success");
                    }
                } else if (appInfo.getStatus() == 4) {//如果上架时，可以下架
                    long status = 5;
                    int flag = 0;
                    flag = appInfoService.updateStatusByAppId(status, id);
                    System.out.println("17777777flag:" + flag);
                    if (flag == 1) {
                        resultMap.put("resultMsg", "success");
                    }
                }
            } catch (Exception e) {
                resultMap.put("errorCode", "exception000001");
            }
        } else {
            //errorCode:0为正常
            resultMap.put("errorCode", "param000001");
        }

        /*
         * resultMsg:success/failed
         * errorCode:exception000001
         * appId:appId
         * errorCode:param000001
         */
        return resultMap;
    }
    //新增App版本信息页面
    @RequestMapping("/appversionadd")
    public String appversionadd(Model model,@RequestParam(value = "id") String id){
        long appId=0;
        if(id!=null){
            appId=Long.parseLong(id);
        }
        //根据Appid查找APPInfo
        AppInfo appInfo=appInfoService.selectAppInfoById(appId);
        String softwareName=appInfo.getSoftwareName();
        //根据Appid查找APPVersion
        List<AppVersion> appVersionList=appVersionService.selectAppVersionByAppId(appId);
        for (AppVersion appVersion: appVersionList) {
            appVersion.setAppIdName(softwareName);
        }
        for (AppVersion appVersion: appVersionList) {
            System.out.println(appVersion);
        }
        model.addAttribute("appVersionList",appVersionList);
        model.addAttribute("appId",appId);

        return "/developer/appversionadd";
    }



    //新增App版本信息保存
    @RequestMapping("/addversionsave")
    public String addversionsave(HttpSession session, HttpServletRequest request,
                                 @RequestParam(value = "appId")String appId, @RequestParam(value = "versionNo")String versionNo,
                                 @RequestParam(value = "versionSize")String versionSize, @RequestParam(value = "publishStatus")String publishStatus,
                                 @RequestParam(value = "versionInfo")String versionInfo, @RequestParam(value = "a_downloadLink",required= false) MultipartFile a_downloadLink){

//获取前端用户session
        DevUser devUser = (DevUser) session.getAttribute("devUserSession");
        System.out.println("进入增App版本信息保存。。。。");
        System.out.println(devUser);
        //获取前端数据
        long appid = 0;
        String versionno = null;
        Double versionsize =0.0;
        long publishstatus = 0;
        String versioninfo = null;
        String a_downloadlink=null;
        String apklocPath=null;

        if (appId != null && !("").equals(appId)) {

            appid= Long.parseLong(appId);
        }
        if (versionNo!= null && !("").equals(versionNo)) {
            versionno= versionNo.toString();
        }
        if (versionSize != null && !("").equals(versionSize)) {
            versionsize= Double.parseDouble(versionSize);
        }
        if (publishStatus!= null && !("").equals(publishStatus)) {
            publishstatus= Long.parseLong(publishStatus);
        }
        if (versionInfo!= null && !("").equals(versionInfo)) {
            versioninfo= versionInfo.toString();
        }
        if(a_downloadLink!=null&&!a_downloadLink.isEmpty()){//上传至服务器保存Apk文件
            //获取文件名
            String uploadFileName=a_downloadLink.getOriginalFilename();
            a_downloadlink=uploadFileName;//获取前端参数
            // 获取服务器的 物理路径
            ///static/upload/b311d43e-a952-42f3-ad73-6899342fcfa87353.jpg
            String path = request.getSession().getServletContext().getRealPath("static")+"\\upload\\apk\\";
            String path1 ="http://localhost:8080/static/upload/apk/";
            apklocPath =path+uploadFileName;
            System.out.println("测试"+path);//测试
            System.out.println("测试"+path1);//测试
            System.out.println("测试3"+apklocPath);//测试
        }
        String apkFileName=a_downloadlink;
        long createdBy=devUser.getId();
        Date creationDate=new Date();
        AppVersion appVersion=new AppVersion();
        appVersion.setAppId(appid);
        appVersion.setCreationDate(creationDate);
        appVersion.setCreatedBy(createdBy);
        appVersion.setApkFileName(apkFileName);
        appVersion.setVersionNo(versionno);
        BigDecimal Vsize=new BigDecimal(versionsize);//
        appVersion.setVersionSize(Vsize);
        appVersion.setPublishStatus(publishstatus);
        appVersion.setVersionInfo(versioninfo);
        appVersion.setDownloadLink(a_downloadlink);
        appVersion.setApkLocPath(apklocPath);
        System.out.println(appVersion);//测试

//更新APP版本表
        int flag=0;
        System.out.println("_____________________-"+appid);
        flag=appVersionService.addAppVersion(appid,versionno,Vsize, publishstatus,versioninfo,creationDate,createdBy,apkFileName,a_downloadlink,apklocPath);
        System.out.println("+flag++++++++++++++++"+flag);
        if(flag==1){
//更新APP基础信息表的viersion_id字段（该字段记录最新的APP版本号）
            long versionId=0;
            versionId=appVersionService.selectNewAppVersionId();
            int success=appInfoService.updateVersionIdByAppId(appid,versionId);

            System.out.println("+++++++++++++++++"+success);
            if(success==1){
                return "redirect:/dev/flatform/app/list";}

        }
        return "redirect:/dev/flatform/app/addversionsave";
    }


//修改版本号页面
    @RequestMapping("/appversionmodify")
    public String appversionmodify(Model model,@RequestParam(value = "aid") String aid,@RequestParam(value = "vid") String vid){
        long appId=0;
        if(aid!=null){
            appId=Long.parseLong(aid);
        }
        //根据Appid查找APPInfo
        AppInfo appInfo=appInfoService.selectAppInfoById(appId);
        String softwareName=appInfo.getSoftwareName();
        //根据Appid查找APPVersion
        List<AppVersion> appVersionList=appVersionService.selectAppVersionByAppId(appId);
        for (AppVersion appVersion: appVersionList) {
            appVersion.setAppIdName(softwareName);
        }
        for (AppVersion appVersion: appVersionList) {
            System.out.println(appVersion);
        }
        //查找app的最新版本号
        long versionid=appInfo.getVersionId();
        AppVersion appVersion=appVersionService.searchById((int)versionid);

        model.addAttribute("appVersionList",appVersionList);
        model.addAttribute("appId",appId);
        model.addAttribute("appVersion",appVersion);

        return "/developer/appversionmodify";
    }
//修改后保存
@RequestMapping("/appversionmodifysave")
public String appversionmodifysave(HttpSession session, HttpServletRequest request,  @RequestParam(value = "id")String id,
                             @RequestParam(value = "appId")String appId, @RequestParam(value = "versionNo")String versionNo,
                             @RequestParam(value = "versionSize")String versionSize, @RequestParam(value = "publishStatus")String publishStatus,
                             @RequestParam(value = "versionInfo")String versionInfo, @RequestParam(value = "a_downloadLink",required= false) MultipartFile a_downloadLink){

//获取前端用户session
    DevUser devUser = (DevUser) session.getAttribute("devUserSession");
    System.out.println("。。。。。。。。。。。。。。。。。。。进入修改App版本信息保存。。。。。。。。。。。。。。。。");
    System.out.println(devUser);
    //获取前端数据
    long vid=0;
    long appid = 0;
    String versionno = null;
    Double versionsize =0.0;
    long publishstatus = 0;
    String versioninfo = null;
    String a_downloadlink=null;
    String apklocPath=null;
    if (id != null && !("").equals(id)) {

        vid= Long.parseLong(id);
    }
    if (appId != null && !("").equals(appId)) {

        appid= Long.parseLong(appId);
    }
    if (versionNo!= null && !("").equals(versionNo)) {
        versionno= versionNo.toString();
    }
    if (versionSize != null && !("").equals(versionSize)) {
        versionsize= Double.parseDouble(versionSize);
    }
    if (publishStatus!= null && !("").equals(publishStatus)) {
        publishstatus= Long.parseLong(publishStatus);
    }
    if (versionInfo!= null && !("").equals(versionInfo)) {
        versioninfo= versionInfo.toString();
    }
    if(a_downloadLink!=null&&!a_downloadLink.isEmpty()){//上传至服务器保存Apk文件
        //获取文件名
        String uploadFileName=a_downloadLink.getOriginalFilename();
        a_downloadlink=uploadFileName;//获取前端参数
        // 获取服务器的 物理路径
        ///static/upload/b311d43e-a952-42f3-ad73-6899342fcfa87353.jpg
        String path = request.getSession().getServletContext().getRealPath("static")+"\\upload\\apk\\";
        String path1 ="http://localhost:8080/static/upload/apk/";
        apklocPath =path+uploadFileName;
        System.out.println("测试"+path);//测试
        System.out.println("测试"+path1);//测试
        System.out.println("测试3"+apklocPath);//测试
    }
    String apkFileName=a_downloadlink;
    long modifyBy=devUser.getId();
    Date modifyDate=new Date();
    AppVersion appVersion=new AppVersion();
    appVersion.setAppId(appid);
    appVersion.setModifyDate(modifyDate);
    appVersion.setModifyBy(modifyBy);
    appVersion.setApkFileName(apkFileName);
    appVersion.setVersionNo(versionno);
    BigDecimal Vsize=new BigDecimal(versionsize);//
    appVersion.setVersionSize(Vsize);
    appVersion.setPublishStatus(publishstatus);
    appVersion.setVersionInfo(versioninfo);
    appVersion.setDownloadLink(a_downloadlink);
    appVersion.setApkLocPath(apklocPath);
    System.out.println(appVersion);//测试

//更新APP版本表
    int flag=0;
    System.out.println("_____________________-"+appid);
    flag=appVersionService.updateAppVersion(versionno,Vsize,versioninfo,modifyDate,modifyBy,vid);
    System.out.println("+flag++++++++++++++++"+flag);
    if(flag==1){
//更新APP基础信息表的viersion_id字段（该字段记录最新的APP版本号）

            return "redirect:/dev/flatform/app/list";}
    return "redirect:/dev/flatform/app/appversionmodifysave";
}






}


