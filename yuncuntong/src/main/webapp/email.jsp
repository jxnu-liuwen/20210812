<%@ page language="java" contentType="text/html; charset=utf-8"  pageEncoding="utf-8"%>
<!doctype html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>软通云-随存通</title>
    <link rel="stylesheet" type="text/css" href="css/admin_login.css"/>
</head>
<body>
<div class="admin_login_wrap">
    <div id="loginbck_top"><img src="images/login/logo_top.jpg" /></div>
    <div id="loginbck_left"><img src="images/login/logo_left4.jpg" /></div>
    <div id="loginbck_main">
        <div class="adming_login_border">
            <div class="admin_input">
                <form action="findByEmail" method="post" >

                    <ul class="admin_items">
                        <li>
                            <h3 align="center">
                                <label for="email">小月学堂密码找回</label>
                            </h3>
                            <div align="center"><label for="email"></label>
                                <input type="text" name="email" placeholder="请输入邮箱名" id="email" size="40" class="admin_input_style" />
                            </div></li>

                        <li>
                            <div align="center"><input type="submit" tabindex="3" value="密码找回" class="btn btn-primary" href="javascript:subRegister()"/>
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