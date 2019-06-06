<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>账号添加</title>
    <link rel="shortcut icon" href="favicon.ico"> <link href="${base}/res/css/bootstrap/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${base}/res/css/bootstrap/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${base}/res/css/bootstrap/bootstrap-table.min.css" rel="stylesheet">
    <link href="${base}/res/css/layui.css" rel="stylesheet">
       <!-- 全局js -->
    <script src="${base}/res/js/jquery.min.js?v=2.1.4"></script>
    <script src="${base}/res/js/bootstrap/bootstrap.min.js?v=3.3.6"></script>
    
    <script src="${base}/res/lay/layui.js"></script>
    
    <!-- Bootstrap table -->
    <script src="${base}/res/js/bootstrap/bootstrap-table.min.js"></script>
    <script src="${base}/res/js/bootstrap/bootstrap-table-zh-CN.min.js"></script>
</head>
<body class="childrenBody">
	<div class="container" style="width:100%;overflow:auto;border:1px solid #d6d6d6">
		<div class="row clearfix">
			<div class="col-md-12 column" style="margin-top:20px;height:300px">
				<form class="layui-form changePwd">
						<table class="table table-hover table-bordered">
							<input type="hidden" class="form-control" id="id" name="id" value="${id?default('')}"/>
					   		<tbody>
					   			<tr>
									<th colspan="6" >账号信息</th>
						      	</tr>
					   			<tr>
						      		<th>
							      		<i style="color:red;">*</i>&nbsp;姓&nbsp;&nbsp;&nbsp;&nbsp;名
							      	</th>
							        <td colspan="2" style="vertical-align:middle;">
										<input type="text" class="form-control" id="name" name="name" value="" lay-verify="required"/>
							        </td>
							        
							         <th>
							      		<i style="color:red;">*</i>&nbsp;角&nbsp;&nbsp;&nbsp;&nbsp;色
							      	</th>
							        <td colspan="2" style="vertical-align:middle;">
										<select class="form-control" id="role_id" name="role_id">
											<option value=''>请选择角色</option>
											<#if roleLst?exists>
											<#list roleLst as r>
											<option value='${r.id}'>${r.role}</option>
											</#list>
											</#if>
										</select>
							        </td>							       
							        
							    </tr>
							  
							     <tr>
						      		<!--
						      		<th>
							      		<i style="color:red;">*</i>&nbsp;区&nbsp;&nbsp;&nbsp;&nbsp;域
							      	</th>
							        <td colspan="2" style="vertical-align:middle;">
										<select class="form-control" id="area_id" name="area_id">
											<option value=''>请选择所属区域</option>
											<#if areaList?exists>
											<#list areaList as a>
											<option value='${a.id}'>${a.area}</option>
											</#list>
											</#if>
										</select>
							        </td>
							        -->
							         <th>
							      		<i style="color:red;">*</i>&nbsp;部&nbsp;&nbsp;&nbsp;&nbsp;门
							      	</th>
							        <td colspan="2" style="vertical-align:middle;">
										<select class="form-control" id="p_id" name="p_id">
											<option value=''>请选择所属部门</option>
											<#if departList?exists>
											<#list departList as d>
											<option value='${d.id}'>${d.depart_name}</option>
											</#list>
											</#if>
										</select>
							        </td>
							        
							         <th>
							      		<i style="color:red;">*</i>&nbsp;邮&nbsp;&nbsp;&nbsp;&nbsp;箱
							      	</th>	
							      	<td colspan="2" style="vertical-align:middle;">
							      		<input type="text" class="form-control" id="email" name="email" value=""/>
							      	</td>						        
							    </tr>
							    
					   			<tr>
						      		<th>
							      		<i style="color:red;">*</i>&nbsp;账&nbsp;&nbsp;&nbsp;&nbsp;号
							      	</th>
							        <td colspan="2" style="vertical-align:middle;">
										<input type="text" class="form-control" id="account" name="account" value=""/>
							        </td>
							        
							        <th>
							      		<i style="color:red;">*</i>&nbsp;电&nbsp;&nbsp;&nbsp;&nbsp;话
							      	</th>
							        <td colspan="2" style="vertical-align:middle;">
										<input type="text" class="form-control" id="phone" name="phone" value=""/>
							        </td>							        
							    </tr>
							    
								
					   		</tbody>
						</table>
						
						<div class="btnClass" style="float:right;margin-top:30px">
							
				           	<button class="layui-btn" lay-submit="" lay-filter="*">保存</button>
						</div>
						
				</form>
			</div>
		</div>
	</div>
</body>
<script type="text/javascript">

    layui.use('form', function(){
	  var form = layui.form;
	  
	  //监听提交
	  form.on('submit(formDemo)', function(data){
	    layer.msg(JSON.stringify(data.field));
	    return false;
	  });
	});
</script>

<script>
    $(function(){
        var id = $("#id").val();
        if(id != ""){
            $.ajax({
                url: "${base}/user/getUserManageMap",
                data:{"id":id},
                dataType:"json",
                success: function(data){
                  	$('#name').val(data.name);
                  	$('#account').val(data.account);
                  	$('#role_id').val(data.role_id);
                  	$('#phone').val(data.phone);
                  	$('#p_id').val(data.p_id);
                  	$('#email').val(data.email);
					layui.use('form', function(){
					  var form = layui.form;
					  form.render('select');
					});
					
					
                },error:function(){
                }
            });
        }
    })

    layui.use(['form','layer' ], function() {
        var form = layui.form;
        var layer = layui.layer;
        form.on('submit(*)', function(data) {
        
            var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.8});
            var d = data.field;
            var url = "${base}/user/insertUser";
            if(d.id != ""){
                url = "${base}/user/updateUser";
            }
            $.ajax({
                url: url,
                data:d,
                dataType:"text",
                success: function(data){
                	
                   	 	layer.msg("账号信息操作成功！",{icon: 1,time:1000,end:function(){
                   	 	layer.close(layer.index);
						window.parent.location.reload();
						 
						}})
                },error:function(){
                    layer.msg("保存失败！");
                }
            });
            return false;
        });
    });

</script>


</html>