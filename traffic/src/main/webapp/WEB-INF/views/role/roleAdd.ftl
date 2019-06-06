<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>角色添加</title>
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
			<div class="col-md-12 column" style="margin-top:20px">
				<form class="layui-form changePwd">
						<table class="table table-hover table-bordered">
							<input type="hidden" class="form-control" id="id" name="id" value="${id?default('')}"/>
					   		<tbody>
					   			<tr>
									<th colspan="6" >角色信息</th>
						      	</tr>
					   			<tr>
						      		<th>
							      		<i style="color:red;">*</i>&nbsp;角色名称
							      	</th>
							        <td colspan="2" style="vertical-align:middle;">
										<input type="text" class="form-control" id="role" name="role" value="" lay-verify="required"/>
							        </td>
							    </tr>
							    <tr>
							     <th>
							      		&nbsp;角色描述
							      	</th>
							        <td colspan="5" style="vertical-align:middle;">
										<textarea class="form-control" rows="8" id="description" name="description"></textarea>
							        </td>
							     </tr>
							   
					   		</tbody>
						</table>
						<div class="btnClass" style="float:right">
							
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
                url: "${base}/role/getRoleManageMap",
                data:{"id":id},
                dataType:"json",
                success: function(data){
                  	$('#role').val(data.role);
              		$('#description').val(data.description);

                },error:function(){
                }
            });
        }
    })

    layui.use(['form','layer' ], function() {
        var form = layui.form;
        var layer = layui.layer;
        form.on('submit(*)', function(data) {
        
            var index = layer.msg('提交中，请稍候',{icon: 16,time:false,shade:0.1});
            var d = data.field;
            var url = "${base}/role/inserRole";
            if(d.id != ""){
                url = "${base}/role/updateRole";
            }
            $.ajax({
                url: url,
                data:d,
                dataType:"text",
                success: function(data){
                	
                   	 	layer.msg("操作成功！",{icon: 1,time:1000,end:function(){
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