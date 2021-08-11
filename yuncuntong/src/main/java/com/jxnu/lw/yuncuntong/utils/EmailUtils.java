package com.jxnu.lw.yuncuntong.utils;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailUtils {

    /**
     * 邮箱正则判断
     */
    public static boolean isMail(String str) {
        boolean flag = false;
        String regEx1 = "^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
        Pattern p;
        Matcher m;
        p = Pattern.compile(regEx1);
        m = p.matcher(str);
        if(m.matches())
            flag = true;
        /*else
            System.out.println("输入邮箱格式错误......");*/
        return flag;
    }

    /**
     * 实现通过邮箱找回密码
     * receiverEmail 收件人
     * username 用户的账号
     * password 用户的密码
     */
    private static final String AccountEmail = "qq1063837213@163.com";//发送密码的邮箱
    private static final String AccountPassword = "EIMFVWBYNYWVRWQP";//smtp服务器认证密码
    private static final String AccountHost = "smtp.163.com";//smtp服务器

    static Properties properties = new Properties();
    public static void sendEmail(String receiverEmail, String username, String password) throws Exception {
        //设置参数
        properties.setProperty("mail.transport.protocol","smtp");//需要遵循的协议
        properties.setProperty("mail.smtp.host",AccountHost);//服务器的认证
        properties.setProperty("mail.smtp.auth","true");//邮箱认证
        //让参数生效
        Session session = Session.getDefaultInstance(properties);
        //获取发送邮箱的对象
        Transport transport = session.getTransport();
        //获取连接
        transport.connect(AccountEmail,AccountPassword);
        //创建一封邮件
        Message message = getMessage(session, AccountEmail, receiverEmail, username, password);
        //发送邮件
        transport.sendMessage(message,message.getAllRecipients());
        //关流
        transport.close();
    }

    /**
     * 写一封邮件
     */
    private static Message getMessage(Session session, String sendMail, String receiverMail, String username, String password) throws Exception {
        //写邮件的对象
        MimeMessage mimeMessage = new MimeMessage(session);
        //发件人
        mimeMessage.setFrom(new InternetAddress(sendMail,"用户资源管理系统","utf-8"));
        //收件人
        mimeMessage.setRecipient(Message.RecipientType.TO,new InternetAddress(receiverMail,username+"用户","utf-8"));
        //主题
        mimeMessage.setSubject("密码找回");
        //时间
        mimeMessage.setSentDate(new Date());
        //正文
        mimeMessage.setContent("尊敬的用户，您的账号是"+username+"，您的密码是"+password,"text/html;charset=utf-8");
        //保存
        mimeMessage.saveChanges();
        return mimeMessage;
    }

}
