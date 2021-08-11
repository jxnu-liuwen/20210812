<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>软通云-随存通</title>
    <link rel="stylesheet" type="text/css" href="css/admin_login.css"/>
    <script src="js/jquery-3.3.1.js"></script>
    <script type="text/javascript">
        $(function (){
            $("#uname").blur(function (){
                var uname = $(this).val()
                $.get("/data",{uname:uname},function (data){
                    //var parse = JSON.parse(data);//解析
                    var span = $("#uname_span");
                    if(uname.length<6){
                        span.css("color","red");
                        span.html("账号或密码不足6位")
                    }else{
                        if(data.userExit){
                            span.css("color","red");
                            span.html("该用户名已存在")
                        }else{
                            span.css("color","green");
                            span.html("该用户名可使用")
                        }
                    }
                })
            })
        })
    </script>

</head>
<body>
<div class="admin_login_wrap">
    <div id="loginbck_top"><img src="images/login/logo_top.jpg" /></div>
    <div id="loginbck_left"><img src="images/login/logo_left2.jpg" /></div>
    <div id="loginbck_main">
        <div class="adming_login_border">
            <div class="admin_input">
                <form action="userRegister" method="post" >

                    <ul class="admin_items">
                        <li>
                            <h3 align="center">
                                <label for="uname">小月学堂注册</label>
                            </h3>
                            <div align="center"><label for="uname"></label>
                                <input type="text" name="username" placeholder="请输入注册用户名" id="uname" size="40" class="admin_input_style" />
                                <span id="uname_span"></span>
                            </div></li>
                        <li>
                            <label for="pwd"></label>
                            <input type="password" name="password"  id="pwd"  placeholder="请输入注册密码" size="40" class="admin_input_style" />
                            <span id="pwd_span"></span>
                        </li>
                        <li>
                            <label for="email"></label>
                            <input type="text" name="email"  id="email"  placeholder="请输入注册邮箱" size="40" class="admin_input_style" />
                        </li>

                        <li>
                            <div align="center"><input type="submit" tabindex="3" value="注册" class="btn btn-primary" href="javascript:subRegister()"/>
                            </div></li>
                    </ul>
                </form>
            </div>
            <div align="center"><p class="admin_copyright" style="font-size: 15px"><a tabindex="5" href="/" target="_self">返回</a></p>

            </div>
        </div>

    </div>
</div>
</body>
</html>