package com.jxnu.lw.yuncuntong.controller;

import com.jxnu.lw.yuncuntong.dao.HDFSDao;
import org.apache.hadoop.fs.FileStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HDFSDemo {

    @RequestMapping("/readdir")
    public String getReadDir(@RequestParam String url, Model model){

        HDFSDao hdfsDao = new HDFSDao();
        String url1 = url.replace(hdfsDao.getHdfsPath(), "");
        FileStatus[] fileStatuses = hdfsDao.hDFSGetDir(url1);
        //添加全目录和当前目录
        model.addAttribute("currenturl",hdfsDao.getHdfsPath()+url1);
        model.addAttribute("currenturl1",url1);
        //设置作用域
        model.addAttribute("filelist",fileStatuses);
        return "index";
    }

    /**
     * 创建文件夹
     */
    @RequestMapping("/createdir")
    public String getCreateDir(@RequestParam String newdirname){
        HDFSDao hdfsDao = new HDFSDao();
        hdfsDao.HDFSMkdir(newdirname);
        //取最后一个字符/前一位的数组下标
        int index = newdirname.lastIndexOf("/");
        //保留newdirname字符串的第0位到第index位
        String substring = newdirname.substring(0, index);
        String hdfsPath = hdfsDao.getHdfsPath();
        //跳转到另一个controller方法
        //组装路径
        return "redirect:/readdir?url="+substring;
    }

    /**
     * 删除文件或目录
     */
    @RequestMapping("/delete")
    public String getDeleteDir(@RequestParam String filePath){
        HDFSDao hdfsDao = new HDFSDao();
        hdfsDao.HDFSDeleteDir(filePath);
        int index = filePath.lastIndexOf("/");
        String substring = filePath.substring(0, index);

        if(substring.equals(hdfsDao.getHdfsPath())){//在根目录 需要加上/
            substring=substring+"/";
        }
        return "redirect:/readdir?url="+substring;
    }

    /**
     * 文件名修改
     */
    @RequestMapping("/alterDir")
    public String getAlterDir(String filePath2, String altername){
        HDFSDao hdfsDao = new HDFSDao();
        String substring = hdfsDao.HDFSAlterDir(filePath2, altername);

        if(substring.equals(hdfsDao.getHdfsPath())){//在根目录 需要加上/
            substring=substring+"/";
        }
        return "redirect:/readdir?url="+substring;
    }

}
