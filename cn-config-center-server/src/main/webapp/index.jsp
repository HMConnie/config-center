<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="X-UA-Compatible" content="IE=7" />
		<title>配置管理管理后台</title>

		<link href="http://cdn.dui1dui.com/dwz/themes/default/style.css" rel="stylesheet" type="text/css" media="screen"/>
		<link href="http://cdn.dui1dui.com/dwz/themes/css/core.css" rel="stylesheet" type="text/css" media="screen"/>
		<link href="http://cdn.dui1dui.com/dwz/themes/css/print.css" rel="stylesheet" type="text/css" media="print"/>
		<link href="http://cdn.dui1dui.com/dwz/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" media="screen"/>
		<!--[if IE]>
		<link href="http://cdn.dui1dui.com/dwz/themes/css/ieHack.css" rel="stylesheet" type="text/css" media="screen"/>
		<![endif]-->

		<!--[if lte IE 9]>
		<script src="http://cdn.dui1dui.com/dwz/js/speedup.js" type="text/javascript"></script>
		<![endif]-->
		<script src="http://cdn.dui1dui.com/dwz/js/jquery-2.1.4.min.js" type="text/javascript"></script>
		<script src="http://cdn.dui1dui.com/dwz/js/jquery.cookie.js" type="text/javascript"></script>
		<script src="http://cdn.dui1dui.com/dwz/js/jquery.validate.js" type="text/javascript"></script>
		<script src="http://cdn.dui1dui.com/dwz/js/jquery.bgiframe.js" type="text/javascript"></script>
		<script src="http://cdn.dui1dui.com/dwz/xheditor/xheditor-1.2.2.min.js" type="text/javascript"></script>
		<script src="http://cdn.dui1dui.com/dwz/xheditor/xheditor_lang/zh-cn.js" type="text/javascript"></script>
		<script src="http://cdn.dui1dui.com/dwz/uploadify/scripts/jquery.uploadify.min.js" type="text/javascript"></script>
		<script src="http://cdn.dui1dui.com/dwz/uploadify/scripts/jquery.uploadify.js" type="text/javascript"></script>
		<script src="http://cdn.dui1dui.com/dwz/xheditor/xheditor_plugins/ubb.js" type="text/javascript"></script>
		
		

		<script src="http://cdn.dui1dui.com/dwz/bin/dwz.min.js" type="text/javascript"></script>
		<script src="http://cdn.dui1dui.com/dwz/js/dwz.regional.zh.js" type="text/javascript"></script>

		<script type="text/javascript">
		$(function(){
			DWZ.init("dwz.frag.xml", {
				loginUrl:"login_dialog.jsp", loginTitle:"登录",	// 弹出登录对话框
		//		loginUrl:"login.html",	// 跳到登录页面
				statusCode:{ok:200, error:300, timeout:301}, //【可选】
				keys: {statusCode:"statusCode", message:"message"}, //【可选】
				pageInfo:{pageNum:"pageNum", numPerPage:"numPerPage", orderField:"orderField", orderDirection:"orderDirection"}, //【可选】
				debug:false,	// 调试模式 【true|false】
				ui:{hideMode:'offsets'},
				callback:function(){
					initEnv();
					$("#themeList").theme({themeBase:"http://cdn.dui1dui.com/dwz/themes"});
				}
			});
		});
		</script>
	</head>

	<body scroll="no">
		<div id="layout">
			<div id="header">
				<div class="headerNav">
					<ul class="nav">
						<li><a href="goeditpwd.do" target="dialog">修改密码</a></li>
						<li><a href="logout.do">退出</a></li>
					</ul>
					<ul class="themeList" id="themeList">
						<li theme="default"><div class="selected">蓝色</div></li>
						<li theme="green"><div>绿色</div></li>
						<!--<li theme="red"><div>红色</div></li>-->
						<li theme="purple"><div>紫色</div></li>
						<li theme="silver"><div>银色</div></li>
						<li theme="azure"><div>天蓝</div></li>
					</ul>
				</div>
			</div>

			<div id="leftside">
				<div id="sidebar_s">
					<div class="collapse">
						<div class="toggleCollapse"><div></div></div>
					</div>
				</div>
				<div id="sidebar">
					<div class="toggleCollapse"><h2>主菜单</h2><div>收缩</div></div>
					<div class="accordion" fillSpace="sidebar">
				        <div class="accordionHeader">
							<h2><span>Folder</span>主菜单</h2>
						</div>
						<div class="accordionContent">
							<ul class="tree treeFolder">
								<li><a href="manager/whitelist/list.html" target="navTab" rel="white_list_manager" >白名单管理</a></li>
						        <li><a href="manager/module/list.html" target="navTab" rel="module_list" >模块管理管理</a></li>
							</ul>
						</div>
					</div>
				</div>
			</div>

			<div id="container">
				<div id="navTab" class="tabsPage">
					<div class="tabsPageHeader">
						<div class="tabsPageHeaderContent"><!-- 显示左右控制时添加 class="tabsPageHeaderMargin" -->
							<ul class="navTab-tab">
								<li tabid="main" class="main"><a href="javascript:;"><span><span class="home_icon">我的主页</span></span></a></li>
							</ul>
						</div>
						<div class="tabsLeft">left</div><!-- 禁用只需要添加一个样式 class="tabsLeft tabsLeftDisabled" -->
						<div class="tabsRight">right</div><!-- 禁用只需要添加一个样式 class="tabsRight tabsRightDisabled" -->
						<div class="tabsMore">more</div>
					</div>
					<ul class="tabsMoreList">
						<li><a href="javascript:;">我的主页</a></li>
					</ul>
					<div class="navTab-panel tabsPageContent layoutBox">
						<div class="page unitBox">
						</div>
					</div>
				</div>
			</div>

		</div>

		<div id="footer">Copyright &copy; 2016 <a href="https://www.zhulebei.com" target="blank">兑一兑团队</a></div>

	</body>
</html>