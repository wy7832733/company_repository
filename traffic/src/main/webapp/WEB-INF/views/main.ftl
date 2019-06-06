<!DOCTYPE html>
<!--[if IE 8]> <html lang="en" class="ie8 no-js"> <![endif]-->
<!--[if IE 9]> <html lang="en" class="ie9 no-js"> <![endif]-->
<!--[if !IE]><!--> <html lang="en" class="no-js"> <!--<![endif]-->
<!-- BEGIN HEAD -->
<head>
<meta charset="utf-8" />
<title>华东石油技师学院证书管理系统</title>
<meta content="width=device-width, initial-scale=1.0" name="viewport" />
<meta content="" name="description" />
<meta content="" name="author" />
<link href="${base}/res/main/css/bootstrap.min.css" rel="stylesheet" type="text/css"/>
<link href="${base}/res/main/css/bootstrap-responsive.min.css" rel="stylesheet" type="text/css"/>
<link href="${base}/res/main/css/font-awesome.min.css" rel="stylesheet" type="text/css"/>
<link href="${base}/res/main/css/style-metro.css" rel="stylesheet" type="text/css"/>
<link href="${base}/res/main/css/style.css" rel="stylesheet" type="text/css"/>
<link href="${base}/res/main/css/style-responsive.css" rel="stylesheet" type="text/css"/>
<link href="${base}/res/main/css/default.css" rel="stylesheet" type="text/css" id="style_color"/>

 <link href="${base}/res/css/layui.css" rel="stylesheet">

</head>
<body class="page-header-fixed">
<div class="header navbar navbar-inverse navbar-fixed-top">
<div class="navbar-inner">
<div class="container-fluid">
	<!--
	<a class="brand" href="index.html"><img src="${base}/res/main/image/qmzs.png" alt="logo"/></a>
	-->
	<span style="font-size:20px;color:white;margin-top:15px;display:inline-block;font-family:SimHei">华东石油技师学院证书管理系统</span>
	<a href="javascript:;" class="btn-navbar collapsed" data-toggle="collapse" data-target=".nav-collapse">
	<img src="${base}/res/main/image/menu-toggler.png" alt="" /></a>
	<ul class="nav pull-right">
		
		<li class="dropdown user">

			<a href="#" class="dropdown-toggle" data-toggle="dropdown">

			<img alt="" src="${base}/res/main/image/avatar1_small.jpg" />

			<span class="username"><#if user?exists>${user.name?default()}</#if></span>

			<i class="icon-angle-down"></i>

			</a>

			<ul class="dropdown-menu">
				<!--
				<li><a href="rrxx.html"><i class="icon-user"></i> 个人中心</a></li>
				<li><a href="#"><i class="icon-tasks"></i> 我的任务</a></li>
				
				<li class="divider"></li>
				-->

				<li><a href="javascript:void(0)" onclick="changePwd()"><i class="icon-key"></i> 修改密码</a></li>
				<li><a href="javascript:void(0" onclick="confirmExit()"><i class="icon-off"></i> 退出</a></li>

			</ul>

		</li>

	</ul>
</div>
</div>
</div>
<div class="page-container">
	<div class="page-sidebar nav-collapse collapse">
		<ul class="page-sidebar-menu">
			<li class=" start active">
				<div class="sidebar-toggler hidden-phone"></div>
			</li>
			<li class="active">
				<a href="${base}/main">
					<i class="icon-home"></i>
					<span class="title">主页</span>
					<span class="selected"></span>
				</a>
			</li>
			<#if permissionList?exists>
			<#list permissionList as p>
			<li>
				<a onclick="getChild('${p.id}')" href="javascript:void(0)">
					<i class="${p.fontawesome}"></i>
					<span class="title">${p.name}</span>
					<span class="arrow "></span>
				</a>
				<ul class="sub-menu" id=${p.id}>
					
				</ul>
			</li>
			</#list>
			</#if>
		</ul>
	</div>
	<div class="page-content">
		
	</div>
</div>
<div class="footer">
		<div class="footer-inner">
			<p style="color:black">2019 &copy; 扬州昶昊网络科技有限公司</p>
		</div>
<!--回到顶部-->
		<div class="footer-tools">
			<span class="go-top">
			<i class="icon-angle-up"></i>
			</span>
		</div>
</div>

<script src="${base}/res/main/js/jquery-1.10.1.min.js" type="text/javascript"></script>

<!-- IMPORTANT! Load jquery-ui-1.10.1.custom.min.js before bootstrap.min.js to fix bootstrap tooltip conflict with jquery ui tooltip -->
<script src="${base}/res/main/js/bootstrap.min.js" type="text/javascript"></script>
<!--[if lt IE 9]>
<!--<script src="${base}/res/main/js/excanvas.min.js"></script>-->
<script src="${base}/res/main/js/respond.min.js"></script>

<![endif]-->
<script src="${base}/res/main/js/app.js" type="text/javascript"></script>
<!-- END PAGE LEVEL SCRIPTS -->
 <script src="${base}/res/lay/layui.js"></script>
<script>
	jQuery(document).ready(function() {
		App.init(); // initlayout and core plugins
	});
	
	function getChild(id) {
		var obj = $("#"+id);
		var userid = $('#userid').val();
		  $("+obj+").empty();
			$.ajax({
				url:"${base}/getPermissionChildList",
				type:"post",
				data:{
				id : id,
				user_id :'<#if user?exists>${user.id?default()}</#if>'
				},
				success:function(data){
						var html ="";
						$.each(data,function(i){
						var url = data[i]["url"];
						url = "'"+ url + "'";
						 html += '<li><a href="javascript:void(0);" onclick="addTab('+url+');">' +
												data[i]["name"]+ '</a></li>';
						
					});
					obj.html(html);
				}
			});
		}
			
		//iframe页面嵌套
	  	function addTab(url){	  	
	  	 var liobj = $(".page-sidebar-menu li");
	  		  var fobj=$(".sub-menu li");
			  fobj.each(function(){
	      	  $(this).click(function(){
	        	liobj.removeClass("active");
	        	fobj.removeClass("active");
	            $(this).addClass("active");
	            $(this).parent().parent().addClass("active");
			   });
		    });
	  	
		$(".page-content").html("<iframe scrolling='auto' marginwidth='0' marginheight='0' frameborder='0'  src='${base}/"+url+"' style='width:100%;height:750px;'></iframe>");		
	}		
	function getMyMsg() {
		$(".page-content").html("<iframe scrolling='auto' marginwidth='0' marginheight='0' frameborder='0'  src='${base}/examine/companyMsg' style='width:100%;height:750px;'></iframe>");
	}
	
	function changePwd(){
		layui.use('layer', function(){
			layer.open({
			  title:'修改密码',
			  type: 1,
              fixed: false, //不固定
              maxmin: true,
              area: ['30%', '50%'],
              shadeClose: true,			  
			  content: $("#changePwd")		  
			});			
		});	
	}
	
     layui.use(['form','layer' ], function() {
        var form = layui.form;
        
		form.verify({	  
		  //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
		  checkPwd:function(value, item){
			if("${user.password?default()}"!=value)  return "原密码错误！";
            				  	
		  },

	        confirmPwd:function(value, item){  
	            if(!new RegExp($("[name=password2]").val()).test(value)){  
	                return "两次输入密码不一致，请重新输入！";  
	            }  
	        } 
		});   
		       
        var layer = layui.layer;
        form.on('submit(submit1)', function(data) {
            var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.1});
            var d = data.field;
            $.post("${base}/user/changePwd",{id:"${user.id}",password:d.password3},function(result){
            	layer.msg("操作成功！",{icon: 1,time:1000,end:function(){
	           	 	layer.close(layer.index);
					window.parent.location.reload(); 
				}});
				          	
            });
            return false;        	
        });
     }); 
     
     function confirmExit(){
	     layer.confirm('确认退出么', {icon: 3, title:'提示'}, function(index){
	        window.location.href="${base}/logout"; 	
	     });     
     } 	
	
</script>
<div id="changePwd" style="display:none">
	<form class="layui-form" action="" style="margin-top:50px">
		 
		 <div class="layui-form-item">
		    <label class="layui-form-label">原密码</label>
		    <div class="layui-input-inline">
		      <input type="password" name="password1" required lay-verify="required|checkPwd" placeholder="请输入密码" autocomplete="off" class="form-control">
		    </div>
		    
		  </div>
		 
		 <div class="layui-form-item">
		    <label class="layui-form-label">新密码</label>
		    <div class="layui-input-inline">
		      <input type="password" name="password2" required lay-verify="required" placeholder="请输入密码" autocomplete="off" class="form-control">
		    </div>
		   
		  </div>
		  
		 <div class="layui-form-item">
		    <label class="layui-form-label">确认新密码</label>
		    <div class="layui-input-inline">
		      <input type="password" name="password3" required lay-verify="required|confirmPwd" placeholder="请输入密码" autocomplete="off" class="form-control">
		    </div>
		    
		  </div>

		  <div class="layui-form-item">
		    <div class="layui-input-block">
		      <button class="layui-btn" lay-submit lay-filter="submit1" type="button">确认</button>
		      &emsp;&emsp;&emsp;
		      <button type="reset" class="layui-btn layui-btn-primary">重置</button>
		    </div>
		  </div>		  		  		  		
	</form>
</div>
</body>
</html>