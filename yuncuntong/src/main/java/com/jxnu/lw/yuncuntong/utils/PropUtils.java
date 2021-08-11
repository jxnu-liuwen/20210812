package com.jxnu.lw.yuncuntong.utils;

import java.io.IOException;
import java.util.Properties;

public class PropUtils {
    //设置properties文件路径并返回
    public Properties getProperties(){
        Properties prop=  new Properties();
        try {
            prop.load(this.getClass().getClassLoader().getResourceAsStream("application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prop;
    }

}
