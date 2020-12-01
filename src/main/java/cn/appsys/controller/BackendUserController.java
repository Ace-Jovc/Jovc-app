package cn.appsys.controller;
import cn.appsys.pojo.*;
import cn.appsys.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import javax.servlet.http.HttpSession;


@Controller
@RequestMapping("/manager")
@SessionAttributes(value = {"userSession"})
public class BackendUserController {

    @Autowired
    private BackendUserService backendUserService;

    @RequestMapping("/login" )
    public String  login(){//跳转到登录页面
        return "/backendlogin";
    }

    @RequestMapping("/dologin" )
    public String  devLogin(@RequestParam(value="userCode")String userCode, @RequestParam(value="userPassword")String userPassword, Model model) {
        String userTypeName="超级管理员";
        model.addAttribute("userTypeName",userTypeName);
        BackendUser backendUser=backendUserService.devLogin(userCode,userPassword);
        if(backendUser!=null){//登录成功
            model.addAttribute("userSession",backendUser);
            System.out.println("登录成功"+backendUser);
            return "/backend/main";
        }else{
            model.addAttribute("error", "账号或密码错误，请重新登录！");
            return "/backendlogin";
        }

    }

    @RequestMapping("/logout" )//注销
    public String logout(HttpSession session){
        session.invalidate();
        return "/backendlogin";
    }

}
