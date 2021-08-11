package com.jxnu.lw.yuncuntong.dao;

import com.jxnu.lw.yuncuntong.utils.PropUtils;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;

import java.io.IOException;
import java.net.URI;
import java.util.Properties;

/**
 * 获取所有hdfs路径下的所有目录与文件
 */
public class HDFSDao {

    static Properties properties;

    static {
        PropUtils prop = new PropUtils();
        properties = prop.getProperties();
    }

    //获取hdfs的基础路径
    String hdfsPath = properties.getProperty("hdfs.path");

    public String getHdfsPath() {
        return hdfsPath;
    }

    /*public FileStatus[] hDFSGetDir(){
        Configuration conf = new Configuration();
        FileStatus[] filelist = null;
        try {
            //获取hdfs对象
            FileSystem fileSystem = FileSystem.get(URI.create(hdfsPath), conf, properties.getProperty("hdfs.user"));
            //获取所有路径
            filelist = fileSystem.listStatus(new Path(hdfsPath));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return filelist;
    }*/

    //重写
    public FileStatus[] hDFSGetDir(String url) {
        Configuration conf = new Configuration();
        FileStatus[] filelist = null;
        try {
            //获取hdfs对象
            FileSystem fileSystem = FileSystem.get(URI.create(hdfsPath+url), conf, properties.getProperty("hdfs.user"));
            //获取所有路径
            filelist = fileSystem.listStatus(new Path(hdfsPath+url));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return filelist;
    }

    /**
     * 创建目录
     */
    public void HDFSMkdir(String newDir){
        Configuration conf=new Configuration();
        try {
            FileSystem fileSystem = FileSystem.get(URI.create(hdfsPath), conf, properties.getProperty("hdfs.user"));
            fileSystem.mkdirs(new Path(hdfsPath+newDir));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 删除文件或目录
     */
    public void HDFSDeleteDir(String filePath){
        Configuration conf=new Configuration();
        FileSystem fileSystem = null;
        try {
            fileSystem = FileSystem.get(URI.create(filePath), conf, properties.getProperty("hdfs.user"));
            fileSystem.delete(new Path(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 修改文件名
     */
    public String HDFSAlterDir(String filePath2, String altername){
        Configuration conf=new Configuration();
        FileSystem fileSystem = null;
        String filePath = filePath2.substring(0, filePath2.lastIndexOf("/"));
        try {
            fileSystem = FileSystem.get(URI.create(filePath2), conf, properties.getProperty("hdfs.user"));
            fileSystem.rename(new Path(filePath2), new Path(filePath+"/"+altername));
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return filePath;
    }


}
