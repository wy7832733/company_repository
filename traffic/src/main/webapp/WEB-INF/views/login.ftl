<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>华东石油技师学院证书管理系统</title>
<link href="${base}/res/css/style.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="${base}/res/js/jquery.js"></script>
<script src="${base}/res/js/cloud.js" type="text/javascript"></script>
<script src="${base}/res/js/md5.js" type="text/javascript"></script>
<script src="${base}/res/js/jquery.js" type="text/javascript"></script>
<style>
	 #errorMessage{
        width: 100%;
        height:20px;
        position: relative;
        top: 5px;
        font-size: 14px;
        color: red;
        text-align: center;
    }
</style>
<script language="javascript">
	$(function(){
    	$('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
		$(window).resize(function(){  
	    	$('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
	    });  
	});  
</script> 

</head>

<body style="background-color:#1c77ac; background-image:url(${base}/res/images/light.png); background-repeat:no-repeat; background-position:center top; overflow:hidden;">



    <div id="mainBody">
      <div id="cloud1" class="cloud"></div>
      <div id="cloud2" class="cloud"></div>
    </div>  


<div class="logintop">    
    <span>欢迎登录华东石油技师学院证书管理系统</span>    
    <ul>
    <li><a href="#">回首页</a></li>
    <li><a href="#">帮助</a></li>
    <li><a href="#">关于</a></li>
    </ul>    
    </div>
    
    <div class="loginbody">
    
    <span class="systemlogo"></span> 
    <form action="${base}/doLogin" id="form" method="post">   
	    <div class="loginbox">
		    <ul>
		    <li><input  id="account" name="account" type="text" class="loginuser"/></li>
		    <li><input id="password" name="password" type="password" class="loginpwd"/></li>
		    <li><button  type="submit" class="loginbtn"   onclick="login()">登录</button><label><input name="rememberMe" type="checkbox"/>记住密码</label> <div id="errorMessage">${message?default('')}</div></li>
		   
		    </ul>
	
	    
	    </div>
	</form>
    </div>
    
    
    
    <div class="loginbm">版权所有  扬州昶昊网络科技有限公司</div>
</body>

<script type="text/javascript">
	function login(){
		$('#form').submit;
	}
	function check(){
	    if($('#account').val()==""){
	        $('#errorMessage').html("用户名不为空！");
	        return false;
	    }else if($('#password').val()==""){
	        $('#errorMessage').html("密码不为空！");
	        return false;
	    } else {
	    	
	    	$('#form').submit();
	    }
	}
	
	
	//keydowm登录
	function keydown_login() {
		document.onkeydown = function (e){
		var theEvent = window.event || e;
	    var code = theEvent.keyCode || theEvent.which;
	    if (code == 13 && (null != $("#pwd").val())) {
	        	theEvent.returnValue = false;
	        	theEvent.cancel = true;
	            document.getElementById("form").submit();
	    }
		}
	}
	//如果是iframe页面则父页面跳转到首页
	$(function(){
		 if(window.top!=window.self){//不存在父页面
            parent.location.href="${base}/login?message=overTime";
     }
	});
</script>
</html>
