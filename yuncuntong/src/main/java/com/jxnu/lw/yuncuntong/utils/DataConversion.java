package com.jxnu.lw.yuncuntong.utils;

import java.text.DecimalFormat;

public class DataConversion {

    /**
     *将文件大小转换成带单位的格式
     */

    public static String formatFileSize(long size) {
        //设置数据格式，取所有整数，并取两位小数
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (size < 1024) {
            fileSizeString = df.format((double) size) + "B";
        } else if (size < 1048576) {
            fileSizeString = df.format((double) size / 1024) + "K";
        } else if (size < 1073741824) {
            fileSizeString = df.format((double) size / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) size / 1073741824) + "G";
        }
        return fileSizeString;
    }

}
