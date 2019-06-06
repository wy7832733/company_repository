<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>${remark?default()}添加</title>
    <link rel="shortcut icon" href="favicon.ico"> <link href="${"$"}{base}/res/css/bootstrap/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${"$"}{base}/res/css/bootstrap/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${"$"}{base}/res/css/bootstrap/bootstrap-table.min.css" rel="stylesheet">
    <link href="${"$"}{base}/res/css/layui.css" rel="stylesheet">
       <!-- 全局js -->
    <script src="${"$"}{base}/res/js/jquery.min.js?v=2.1.4"></script>
    <script src="${"$"}{base}/res/js/bootstrap/bootstrap.min.js?v=3.3.6"></script>
    
    <script src="${"$"}{base}/res/lay/layui.js"></script>
    
    <!-- Bootstrap table -->
    <script src="${"$"}{base}/res/js/bootstrap/bootstrap-table.min.js"></script>
    <script src="${"$"}{base}/res/js/bootstrap/bootstrap-table-zh-CN.min.js"></script>
</head>
<body class="childrenBody">
	<div class="container" style="width:100%;overflow:auto;border:1px solid #d6d6d6">
		<div class="row clearfix">
			<div class="col-md-12 column" style="margin-top:20px">
				<form class="layui-form changePwd">
						<table class="table table-hover table-bordered">
							<input type="hidden" class="form-control" id="id" name="id" value="${'$'}{id?default('')}"/>
					   		<tbody>
					   			<tr>
									<th colspan="6" >${remark?default()}信息</th>
						      	</tr>
						      	<#if columns?exists>
								<#list columns as col>
					   			 <tr>
						      		<th>
							      		<i style="color:red;">*</i>${col.remark?default()}
							      	</th>
							        <td colspan="2" style="vertical-align:middle;">
										<input type="text" class="form-control" id="${col.propertyName?default()}" name="${col.propertyName?default()}" value="" lay-verify="required"/>
							        </td>
							    </tr>
							   </#list>
           					   </#if>
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
                url: "${"$"}{base}/${entityName}/get${entityCamelName}ManageMap",
                data:{"id":id},
                dataType:"json",
                success: function(data){
        	 <#if columns?exists>
			  <#list columns as col>
			  	$('#${col.propertyName?default()}').val(data.${col.propertyName?default()});
			  </#list>
    		</#if>

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
            var url = "${"$"}{base}/${entityName}/insert${entityCamelName}";
            if(d.id != ""){
                url = "${"$"}{base}/${entityName}/update${entityCamelName}";
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