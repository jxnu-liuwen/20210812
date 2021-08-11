package com.jxnu.lw.yuncuntong.controller;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.jxnu.lw.yuncuntong.dao.HDFSDao;
import com.jxnu.lw.yuncuntong.entity.User;
import com.jxnu.lw.yuncuntong.service.UserService;
import com.jxnu.lw.yuncuntong.service.impl.UserServiceImpl;
import com.jxnu.lw.yuncuntong.utils.EmailUtils;
import com.jxnu.lw.yuncuntong.utils.HDFSUtils;
import org.apache.hadoop.fs.FileStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Controller
public class LoginDemo {

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String getLogin(){
        return "login";
    }

    @RequestMapping("/register")
    public String getRegister(){
        return "register";
    }

    @RequestMapping("/findEmail")
    public String getFindEmail(){
        return "email";
    }

    @RequestMapping("/index")
    public String getIndex(){
        return "index";
    }

    @RequestMapping("/data")
    @ResponseBody
    public JSONObject getAjax(@RequestParam String uname){
        User user = userService.findUserByUsername(uname);
        JSONObject jsonObject = new JSONObject();
        if(user!=null){//用户存在
            jsonObject.put("userExit",true);
        }else {
            jsonObject.put("userExit",false);
        }
        return jsonObject;
    }

    @RequestMapping("/userRegister")
    public void getUserRegister(User user, HttpServletResponse response) throws Exception {
        //解决乱码
        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();

        String username = user.getUsername();
        String password = user.getPassword();
        String email = user.getEmail();

        if(email!=null){
            writer.println("<script>alert('邮箱已被注册');</script>");
        }else{
            if(username.length()>=6 && password.length()>=6){
                if(EmailUtils.isMail(email)){
                    userService.addUser(user);
                    writer.println("<script>alert('注册成功');window.location='/';</script>");
                }else {
                    writer.println("<script>alert('邮箱格式有误');</script>");
                }
            }else {
                writer.println("<script>alert('账号或密码不够6位');</script>");
            }
        }
    }

    @RequestMapping("/findByEmail")
    public void getFindByEmail(HttpServletResponse response, HttpServletRequest request) throws Exception {
        String email = request.getParameter("email");
        //根据邮箱获取账号和密码
        User user = userService.findUserByEmail(email);

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();

        if(user!=null){
            EmailUtils.sendEmail(email,user.getUsername(),user.getPassword());
            writer.println("<script>alert('密码已发送至您的邮箱');window.location='/';</script>");
        }else{
            writer.println("<script>alert('邮箱未找到');window.location='/findEmail';</script>");
        }
    }

    @RequestMapping("/userlogin")
    public void userLogin(User user, HttpServletResponse response, HttpSession session) throws Exception {
        String username = user.getUsername();
        String password = user.getPassword();
        User user1 = userService.findByUsernameAndPassword(username,password);

        response.setContentType("text/html;charset=utf-8");
        response.setCharacterEncoding("utf-8");
        PrintWriter writer = response.getWriter();

        if(user1!=null){
            session.setAttribute("user",user1);
            writer.println("<script>window.location='/readdir?url=/';</script>");
        }else{
            writer.println("<script>alert('账号或密码错误');window.location='/';</script>");
            return ;
        }
    }
}
