<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<%@ page   import="org.apache.hadoop.fs.FileStatus"  %>
<%@ page import="java.util.Date" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%
    FileStatus[] filelist=(FileStatus[])request.getAttribute("filelist");
    String currenturl="";
    String currenturl1="";
    if(request.getAttribute("currenturl")!=null){
        currenturl=request.getAttribute("currenturl").toString();
        currenturl1=request.getAttribute("currenturl1").toString();
    }
    String haha="";
%>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>软通云-随存通</title>
    <link rel="stylesheet" type="text/css" href="css/common.css"/>
    <link rel="stylesheet" type="text/css" href="css/main.css"/>


</head>
<body>
<div class="topbar-wrap white">
    <div class="topbar-inner clearfix">
        <div class="topbar-logo-wrap clearfix">
            <h1 class="topbar-logo none"><a href="index.html" class="navbar-brand">后台管理</a></h1>
            <ul class="navbar-list clearfix">
                <li><a  href="index.html"><img src="images/login/logo_top.jpg"  width="100px" /></a></li>
            </ul>
        </div>
        <div class="top-info-wrap">
            <ul class="top-info-list clearfix">
                <li><a href="#">${user.username}</a></li>
                <li><a href="alter">修改密码</a></li>
                <li><a href="sign" target="_self">签到</a></li>
<%--                <li><a href="guanli" target="_self">用户管理系统</a></li>--%>
                <li><a href="/" target="_self">退出</a></li>
            </ul>
        </div>
    </div>
</div>
<div class="container clearfix">
    <div class="sidebar-wrap">
        <div class="sidebar-title">
            <h1>小月学堂</h1>
        </div>
        <div class="sidebar-content">
            <ul class="sidebar-list">
                <li>
                    <a href="#"><i class="icon-font">&#xe003;</i>常用操作</a>
                    <ul class="sub-menu">
                        <li><a href="#"><i class="icon-font">&#xe008;</i>个人文件</a></li>
                        <li><a href="#"><i class="icon-font">&#xe005;</i>群组文件</a></li>
                        <li><a href="#"><i class="icon-font">&#xe006;</i>我的收藏</a></li>
                        <li><a href="#"><i class="icon-font">&#xe004;</i>本地同步</a></li>
                        <li><a href="#"><i class="icon-font">&#xe012;</i>我的分享</a></li>
                        <li><a href="#"><i class="icon-font">&#xe052;</i>我的订阅</a></li>
                        <li><a href="#"><i class="icon-font">&#xe033;</i>标签分类</a></li>
                        <li><a href="design.html"><i class="icon-font">&#xe033;</i>回收站</a></li>
                    </ul>
                </li>
                <li>
                    <a href="#"><i class="icon-font">&#xe018;</i>帮助中心</a>
                    <ul class="sub-menu">
                        <li><a href="system.html"><i class="icon-font">&#xe017;</i>系统设置</a></li>
                        <li><a href="system.html"><i class="icon-font">&#xe037;</i>清理缓存</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </div>
    <!--/sidebar-->
    <div class="main-wrap">
        <div class="crumb-wrap">
            <div class="crumb-list">
                <i class="icon-font">&#xe06b;</i>
                <span>当前路径>>
                    <a href="readdir?url=/" >个人文件</a>
                    <%
                        String splits[]=currenturl.split("/");//  hdfs://192.168.119.110:9000/shida分成了4部分

                        if(splits.length>3){//从0开始,等于3为根目录
                            int len;//标记
                            String path=splits[0]+"/"+splits[1]+"/"+splits[2]+"/"+splits[3];//  组成hdfs://192.168.119.110:9000/shida
                            for(int i = 4; i < splits.length; i++){
                                len = path.length();//记录下标
                                path = path+"/"+splits[i];//组合
                                String path1=path.substring(len+1);//去掉len下标之前的内容以及/
                    %>
                    &nbsp;&nbsp;/&nbsp;&nbsp;&nbsp;<a href="readdir?url=<%=path%>"><%=path1%></a>
                            <%}%>
                    <%}%>
                </span>
               <%--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a href="shangyiji?papa=<%=currenturl%>">返回上一级</a>--%>
            </div>

        </div>
        <div class="result-wrap">
            <div class="result-title">
                <h1>快捷操作</h1>
            </div>
            <div class="result-content">
                <div class="short-wrap">
                    <form method="post" action="/upload?url=<%=currenturl %>" enctype="multipart/form-data" id="myfrm" >
                        <a href="javascript:;" class="a-upload"><input type="file" name="file" id="file" onchange="uploadfile()"><i class="icon-font">&#xe005;</i>上传</a>
                        <script language="JavaScript">
                            function uploadfile(){
                                document.getElementById("myfrm").submit();
                            }
                        </script>
                        <a href="javascript:createdir()" class="a-upload"><input type="button" name="" id="createdir" ><i class="icon-font">&#xe005;</i>新建文件夹</a>
                        <script language="JavaScript">
                            function createdir(){
                                var dirname=prompt('请输入文件夹名称', '新建文件夹1');
                                if(dirname.length>0){
                                    location.href="createdir?newdirname=<%=currenturl1%>/"+dirname;
                                }
                            }
                        </script>
                        <a href="javascript:;" class="a-upload"><input type="button" name="downfile" id="downfile"><i class="icon-font">&#xe048;</i>下载文件</a>
                        <a href="javascript:;" class="a-upload"><input type="button" name="downfile1" id="downfile1"><i class="icon-font">&#xe041;</i>下载文件</a>
                        <a href="javascript:;" class="a-upload"><input type="button" name="other" id="other"><i class="icon-font">&#xe01e;</i>更多操作.....</a>
                    </form>
                </div>
            </div>
        </div>
        <div class="result-wrap">
            <div class="result-title">
                <h1>系统基本信息</h1>
            </div>
            <div class="result-content">
                <table class="result-tab" width="100%">
                    <tr>
                        <th class="tc" width="5%"><input class="allChoose" name="" type="checkbox"></th><th>文件名</th><th>修改时间</th><th>大小</th><th>备注信息</th>  <th>操作</th>
                    </tr>
                    <%
                        if(filelist!=null){
                            for(FileStatus fs:filelist){

                    %>
                    <tr>
                        <td class="tc"><input name="id[]" value="59" type="checkbox"></td>
                        <td>
                            <% if(fs.isDirectory()==true){ %>
                            <a target="_self"    href="readdir?url=<%=fs.getPath() %>"   title="进入目录"><img src="images/icon_dir.png" /><%=fs.getPath().getName() %></a>
                            <% } else{%>
                            <img src="images/icon_file.png" /><%=fs.getPath().getName() %></td>
                        <% }%>
                        </td>

                        <td ><%=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(fs.getModificationTime()) %></td>

                        <td title="大小"><%=(!fs.isDirectory())? ((fs.getLen()/1024>=1024)? ((fs.getLen()/1024/1024>=1024)? fs.getLen()/1024/1024/1024+"GB" : fs.getLen()/1024/1024+"MB") : fs.getLen()/1024+"KB") : "0B" %>
                            <a target="_blank" href="#" title=""></a>
                        </td>
                        <td></td>
                        <td> <% if(fs.isDirectory()==false) {%> <a class="link-update" href="download?fileName=<%=fs.getPath().toString() %>">下载</a>&nbsp;&nbsp;<% } %>
                            <a class="link-del" href="delete?filePath=<%=fs.getPath().toString()%>" onclick="javascript:return confirm('确认删除：<%=fs.getPath().getName()%> 文件吗？');">删除&nbsp;&nbsp;</a>
                            <a class="link-del" id="wode2" href="#" onclick="javascript:
                                    {
                                    var altername= prompt('你要修改的文件是：<%=fs.getPath().getName()%> ，请输入修改后的名字');
                                    if(altername!=null){
                                    location.href='alterDir?filePath2=<%=fs.getPath().toString()%>&altername='+altername;
                                    }else{
                                    location.href='#';
                                    }
                                    }
                                    ">修改</a>
                        </td>
                    </tr>
                    <% } }
                    %>

                </table>
                <div class="list-page"> 7 条 1/1 页</div>
            </div>
        </div>
        <div class="result-wrap">
            <div class="result-title">
                <h1>使用帮助</h1>
            </div>
            <div class="result-content">
                <ul class="sys-info-list">
                    <li>
                        <label class="res-lab">更多功能</label><span class="res-info"></span>
                    </li>
                </ul>
            </div>
        </div>
    </div>
    <!--/main-->
</div>
</body>
</html>