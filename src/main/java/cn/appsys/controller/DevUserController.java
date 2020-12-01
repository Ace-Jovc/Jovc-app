package cn.appsys.controller;

import cn.appsys.pojo.DevUser;
import cn.appsys.service.DevUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/dev")
@SessionAttributes(value = {"devUserSession"})
public class DevUserController {
    @Autowired
    private DevUserService devUserService;

    @RequestMapping("/login" )
    public String  login(){//跳转到登录页面
        return "/devlogin";
    }
    //登录后点击左边的图片
    @RequestMapping("/flatform/main")
    public String picMain(){
        return "/developer/main";
    }
    @RequestMapping("/dologin" )
    public String  devLogin(@RequestParam(value="devCode")String devCode,@RequestParam(value="devPassword")String devPassword,Model model){
        DevUser devUser= devUserService.devLogin(devCode);

        System.out.println(devCode);
        System.out.println("devPassword=="+devPassword);
        if(devUser!=null){//用户存在
        String userpassword=devUser.getDevPassword();
        boolean flag=userpassword.equals(devPassword);
        System.out.println("flag=="+flag);
        if(flag){
            model.addAttribute("devUserSession",devUser);
            System.out.println("登录成功");
            return "/developer/main";}
        else {
            model.addAttribute("error", "密码错误，请重新登录！");
            System.out.println("登录失败");
            return "/devlogin";
        }
        }else{
            model.addAttribute("error", "账号错误，请重新登录！");
            System.out.println("登录失败");
            return "/devlogin";
        }
    }

    @RequestMapping("/logout" )//注销
    public String logout(HttpSession session){
        session.invalidate();
        return "/devlogin";
    }

}

