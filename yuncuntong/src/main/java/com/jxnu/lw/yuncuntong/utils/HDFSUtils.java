package com.jxnu.lw.yuncuntong.utils;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;

public class HDFSUtils {
    static FileSystem fileSystem;

    public static FileSystem getFileSystem() {
        return fileSystem;
    }

    public static void setFileSystem(FileSystem fileSystem) {
        HDFSUtils.fileSystem = fileSystem;
    }

    static {
        //只有一份且不用new

        //导hadoop的包
        //需要uri，conf，user
        Configuration conf = new Configuration();
        //windows无法使用Linux的xml，没设置就以默认为准
        //配置副本数
        conf.set("dfs.replication", "2");
        try {
            fileSystem = FileSystem.get(URI.create("hdfs://192.168.119.110:9000"), conf, "root");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     *创建文件或目录
     */

    public static void mkdir(){
        try {
            boolean flag = fileSystem.mkdirs(new Path("/day01"));
            if(flag){
                System.out.println("成功");
            }else{
                System.out.println("失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *删除文件或目录
     */

    public static void delete(){
        try {
            boolean delete = fileSystem.delete(new Path("/shida/hadoop-common-2.2.0-bin-master.zip"),true);
            if(delete){
                System.out.println("成功");
            }else{
                System.out.println("失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *修改文件或目录
     */

    public static void alter(){
        try {
            boolean rename = fileSystem.rename(new Path("/day01"), new Path("/day02"));
            if(rename){
                System.out.println("成功");
            }else{
                System.out.println("失败");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *查询所有文件
     */

    public static void select(){
        try {
            FileStatus[] fileStatuses = fileSystem.listStatus(new Path("/shida"));
            //获取数据，循环遍历
            for(FileStatus fs : fileStatuses){
                System.out.println("获取当前文件路径：" + fs.getPath());
                System.out.println("副本数：" + fs.getReplication());
                System.out.println("实际大小：" + DataConversion.formatFileSize(fs.getLen()));
                System.out.println("block块大小：" + DataConversion.formatFileSize(fs.getBlockSize()));
                //时间戳：long格式表示时间
                /*//文件上次访问时间
                System.out.println("文件上次访问时间：" + TimeUtil.ConversionTime(fs.getAccessTime()));*/
                //文件上次修改时间
                System.out.println("文件上次修改时间：" + TimeUtil.ConversionTime(fs.getModificationTime()));
                System.out.println("---------------------------------------------------");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *本地 上传到hdfs集群
     */

    public static void upload(){
        try {
            fileSystem.copyFromLocalFile(new Path("C:\\Users\\Administrator\\Desktop\\gz\\大学\\大三下暑期培训\\20210805\\HDFS详解.docx"), new Path("/shida/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     *从hdfs集群  下载到本地
     */

    public static void download(){
        try {
            fileSystem.copyToLocalFile(new Path("/shida/20210804/a.txt"), new Path("F:\\谷歌下载\\"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
