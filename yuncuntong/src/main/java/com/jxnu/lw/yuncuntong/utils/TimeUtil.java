package com.jxnu.lw.yuncuntong.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TimeUtil {

    public static String ConversionTime(Long time){
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//自定义格式
        String sd = sdf.format(new Date(Long.parseLong(String.valueOf(time))));//时间戳转换成时间

        //SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy 年 MM 月 dd 日 HH 时 mm 分 ss 秒");

        return sd;
    }
}
