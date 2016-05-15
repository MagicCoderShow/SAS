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
     <a class="navbar-brand block" href="<%=resourcePath %>index.jsp">
      <img src="<%=resourcePath %>images/logo.png" alt=".">
          <span class="hidden-nav-xs m-l-sm">Dreams</span></a>
      <section class="m-b-lg">
        <header class="wrapper text-center">
          <strong>注册发现有趣的事情</strong>
        </header>
        <form action="<%=resourcePath %>user/signup" method="post">
          <div class="form-group">
            <input placeholder="请输入用户名" class="form-control rounded input-lg text-center no-border">
          </div>
          <div class="form-group">
            <input type="email" placeholder="请输入电子邮箱" class="form-control rounded input-lg text-center no-border">
          </div>
          <div class="form-group">
             <input type="password" placeholder="请输入密码" class="form-control rounded input-lg text-center no-border">
          </div>
          <div class="form-group">
             <input type="password" placeholder="请重复密码" class="form-control rounded input-lg text-center no-border">
          </div>
          <div class="checkbox i-checks m-b">
            <label class="m-l">
              <input type="checkbox" checked=""><i></i> 同意 <a href="#">条款和政策</a>
            </label>
          </div>
          <button type="submit" class="btn btn-lg btn-warning lt b-white b-2x btn-block btn-rounded"><i class="icon-arrow-right pull-right"></i><span class="m-r-n-lg">注册</span></button>
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

</body>
</html>