<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
String resourcePath = basePath+"page/home/";
%>
<!DOCTYPE html>
<html lang="en" class="app">
<head>  
  <meta charset="utf-8" />
  <title>We have Dreams</title>
  <meta name="description" content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav" />
  <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />
  <link rel="shortcut icon" href="<%=resourcePath %>images/favicon.ico" />
  <link rel="stylesheet" href="<%=resourcePath %>js/jPlayer/jplayer.flat.css" type="text/css" />
  <link rel="stylesheet" href="<%=resourcePath %>css/bootstrap.css" type="text/css" />
  <link rel="stylesheet" href="<%=resourcePath %>css/animate.css" type="text/css" />
  <link rel="stylesheet" href="<%=resourcePath %>css/font-awesome.min.css" type="text/css" />
  <link rel="stylesheet" href="<%=resourcePath %>css/simple-line-icons.css" type="text/css" />
  <link rel="stylesheet" href="<%=resourcePath %>css/font.css" type="text/css" />
  <link rel="stylesheet" href="<%=resourcePath %>css/app.css" type="text/css" />  
    <!--[if lt IE 9]>
    <script src="<%=resourcePath %>js/ie/html5shiv.js"></script>
    <script src="<%=resourcePath %>js/ie/respond.min.js"></script>
    <script src="<%=resourcePath %>js/ie/excanvas.js"></script>
  <![endif]-->
</head>
<body class="bg-info dker">
  <section id="content" class="m-t-lg wrapper-md animated fadeInDown">
    <div class="container aside-xl">
     <a class="navbar-brand block" href="<%=basePath %>">
      <img src="<%=resourcePath %>images/logo.png" alt=".">
          <span class="hidden-nav-xs m-l-sm">Dreams</span></a>
      <section class="m-b-lg">
        <header class="wrapper text-center">
          <strong>注册发现有趣的事情</strong>
        </header>
        <form id="signup_form" action="<%=basePath %>user/signup" method="post">
          <input type="hidden" name="data" id="signupdata">
          <div class="form-group">
            <input id="j_loginname" name="loginname" placeholder="请输入用户名" class="form-control rounded input-lg text-center no-border">
          </div>
          <div class="form-group">
            <input id="j_email" name="email" type="email" placeholder="请输入电子邮箱" class="form-control rounded input-lg text-center no-border">
          </div>
          <div class="form-group">
             <input id="j_password" name="password" type="password" placeholder="请输入密码" class="form-control rounded input-lg text-center no-border">
          </div>
          <div class="form-group">
             <input id="j_repassword" name="repassword" type="password" placeholder="请重复密码" class="form-control rounded input-lg text-center no-border">
          </div>
          <div class="checkbox i-checks m-b">
            <label class="m-l">
              <input id="j_agree" type="checkbox"><i></i> 同意 <a href="#">条款和政策</a>
            </label>
          </div>
          <a class="btn btn-lg btn-warning lt b-white b-2x btn-block btn-rounded" onclick="signup()"><i class="icon-arrow-right pull-right"></i><span class="m-r-n-lg">注册</span></a>
          <div class="line line-dashed"></div>
          <p class="text-muted text-center"><small>已经拥有帐号?</small></p>
          <a href="<%=resourcePath %>/signin.jsp" class="btn btn-lg btn-info btn-block btn-rounded">登录</a>
        </form>
      </section>
    </div>
  </section>
  <!-- footer -->
  <footer id="footer">
    <div class="text-center padder clearfix">
      <p>
        <small>We have dreams<br>&copy; 2016</small>
      </p>
    </div>
  </footer>
  <!-- / footer -->
  <script src="<%=resourcePath %>js/jquery.min.js"></script>
  <!-- Bootstrap -->
  <script src="<%=resourcePath %>js/bootstrap.js"></script>
  <!-- App -->
  <script src="<%=resourcePath %>js/app.js"></script>  
  <script src="<%=resourcePath %>js/slimscroll/jquery.slimscroll.min.js"></script>
    <script src="<%=resourcePath %>js/app.plugin.js"></script>
  <script type="text/javascript" src="<%=resourcePath %>js/jPlayer/jquery.jplayer.min.js"></script>
  <script type="text/javascript" src="<%=resourcePath %>js/jPlayer/add-on/jplayer.playlist.min.js"></script>
  <script type="text/javascript" src="<%=resourcePath %>js/jPlayer/demo.js"></script>
  <script type="text/javascript" src="<%=resourcePath %>js/security.js"></script>
<!-- javascript -->
  <script type="text/javascript">
	  //注册
	  function signup(){
		  	//检查参数是否完整
		  	var loginname=$('#j_loginname').val(),email=$('#j_email').val(),password=$('#j_password').val(),repassword=$('#j_repassword').val();
		  	if(!loginname){
				alert("请输入用户名");
				return false;
			}else if(!email){
				alert("请输入电子邮箱");
				return false;
			}else if(!password){
				alert("请输入密码");
				return false;
			}else if(!repassword){
				alert("请输入重复密码");
				return false;
			}
		  	if(password!=repassword){
		  		alert("密码不一致");
				return false;
		  	}
		  	//拼接参数串
		  	var json ="{\"loginname\":\""+loginname+
			"\",\"email\":\""+email+"\",\"password\":\""+password+
			"\",\"repassword\":\""+repassword+"\"}";
			console.log(json);
			//获取公钥
			$.ajax({
				type:'post',
				url:'<%=basePath%>user/getrsakey',
				success:function(msg){
					RSAUtils.setMaxDigits(200); 
					var key = RSAUtils.getKeyPair(msg.exponent, '', msg.modulus);
					var data = RSAUtils.encryptedString(key , json);
					$("#signupdata").val(data);
					$("#j_loginname").val("");
					$("#j_email").val("");
					$("#j_password").val("");
					$("#j_repassword").val("");
					$("#signup_form").submit();
				}
			});
		  
	  }
  </script>
</body>
</html>