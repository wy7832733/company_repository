<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>单位添加</title>
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
    <!-- zTree -->
	<link rel="stylesheet" href="${base}/res/zTree/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${base}/res/zTree/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="${base}/res/zTree/jquery.ztree.excheck.js"></script>    
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
									<th colspan="6" >单位信息</th>
						      	</tr>
					   			<tr>
						      		<th>
							      		<i style="color:red;">*</i>&nbsp;单&nbsp;&nbsp;&nbsp;&nbsp;位
							      	</th>
							        <td colspan="2" style="vertical-align:middle;">
										<input type="text" class="form-control" id="depart_name" name="depart_name" value="" lay-verify="required"/>
							        </td>
							        
							    </tr>
							    <tr>
							     <th>
							      		&nbsp;简&nbsp;&nbsp;&nbsp;&nbsp;介
							      	</th>
							        <td colspan="5" style="vertical-align:middle;">
										<textarea class="form-control" rows="8" id="intro" name="intro"></textarea>
							        </td>
							     </tr>
					   			<tr>
						      		<th>
							      		<i style="color:red;">*</i>上级部门
							      	</th>
							        <td colspan="2" style="vertical-align:middle;">
										<input type="text" class="form-control" id="pid_name" name="pid_name" value="" onclick="showTree()" readonly/>
							        	<input type="hidden" name="pid" id="pid" class="form-control">
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


<div id="treeDiv" style="display:none;width:350px;height:350px" >
	<div id="treeDemo" class="ztree">
	
	</div>
</div>

<script type="text/javascript">
 	var nodename="";
 	var nodeid="";
 	
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
        
        initTree();
        
        var id = $("#id").val();
        if(id != ""){
            $.ajax({
                url: "${base}/depart/getDepartManageMap",
                data:{"id":id},
                dataType:"json",
                success: function(data){
                  	$('#depart_name').val(data.depart_name);
                  	$('#intro').val(data.intro);
                  	//自动选中上级单位,并在相应的文本框赋值
                  	if(data.pid){        	
					  	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");	  	
					  	treeObj.expandAll(true);					  	               	
					  	var node = treeObj.getNodeByParam("id",data.pid,null);
					  	$("#"+node.tId+"_a").addClass("curSelectedNode");   				  	
					  	$("#pid").val(node.id);
					  	$("#pid_name").val(node.name);
                  	}

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
            var url = "${base}/depart/insertDepart";
            if(d.id != ""){
                url = "${base}/depart/updateDepart";
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
    
	var setting = {
		check: {
			enable:false,
			//chkboxType: { "Y" : "s", "N" : "s" }
			chkboxType:{ "Y" : "", "N" : "" }
		},
        data : {  
                 
               simpleData : {  
                  enable : true,  
                  idKey : "id",  
                  pIdKey : "pid",  
                  rootPId : 1  
                }  
         },   
		key:{  
			checked:"checked",//zTree 节点数据中保存check状态的属性名称。默认值："checked"  
			name:"depart_name"
		},  
		view:{  
	    	selectedMulti:false//允许选多个  
		},
		callback:{
			onClick: zTreeOnClick
		}  
	};
	function initTree(){
		$.ajax({
			url: "${base}/depart/getDepartTreeData",
			type: "post",
			dataType: "json",
			ContentType: "application/json; charset=utf-8",
			async: false,
			success: function (data) {
				zNodes = data;
				//zNodes = eval(zNodes);                          //序列化json数据
				for(var i=0;i<zNodes.length;i++){
					zNodes[i].name=zNodes[i].depart_name;
				}
				$.fn.zTree.init($("#treeDemo"), setting, zNodes);//初始化树
			},
			error: function () {
				alert("zNodes");
			}
	    });
	} 
	
	function showTree(){
		layer.open({
		  title:'上级部门选择',
		  type: 1,
		  content: $("#treeDiv"), 
		  btn: ['确定', '取消'],
		  yes: function(index, layero){
		  	$("#pid").val(nodeid);
		  	$("#pid_name").val(nodename);
		  	layer.close(index);
		  },
		  btn2: function(index, layero){
				resetContent();
				layer.close(index);
		  },
		  cancel: function(){
				resetContent(); 
		  }		  
		});
	}
	
	function zTreeOnClick(event, treeId, treeNode) {
	    //alert(treeNode.id + ", " + treeNode.name);
	    nodename=treeNode.name
	    nodeid=treeNode.id;
	    $("#treeDemo").find("a").removeClass("curSelectedNode");
	    $("#"+treeNode.tId+"_a").addClass("curSelectedNode");   
	}
	
	function resetContent(){
		$("#treeDemo").find("a").removeClass("curSelectedNode");
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo");	  	
		var node = treeObj.getNodeByParam("id",$("#pid").val(),null);
		if(node) $("#"+node.tId+"_a").addClass("curSelectedNode");	
	}

</script>


</html>