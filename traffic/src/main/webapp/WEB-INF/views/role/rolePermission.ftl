<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>角色权限配置</title>
    <link rel="shortcut icon" href="favicon.ico"> <link href="${base}/res/css/bootstrap/bootstrap.min.css?v=3.3.6" rel="stylesheet">
    <link href="${base}/res/css/bootstrap/font-awesome.css?v=4.4.0" rel="stylesheet">
    <link href="${base}/res/css/bootstrap/bootstrap-table.min.css" rel="stylesheet">
    <link href="${base}/res/css/layui.css" rel="stylesheet">
       <!-- 全局js -->
    <script src="${base}/res/js/jquery.min.js?v=2.1.4"></script>
    <script src="${base}/res/js/bootstrap/bootstrap.min.js?v=3.3.6"></script>
    <link rel="stylesheet" href="${base}/res/zTree/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="${base}/res/zTree/jquery.ztree.core.js"></script>
	<script type="text/javascript" src="${base}/res/zTree/jquery.ztree.excheck.js"></script>
    
    <script src="${base}/res/lay/layui.js"></script>
    
    <!-- Bootstrap table -->
    <script src="${base}/res/js/bootstrap/bootstrap-table.min.js"></script>
    <script src="${base}/res/js/bootstrap/bootstrap-table-zh-CN.min.js"></script>
</head>
<body class="childrenBody">
	<div class="container" style="width:100%;overflow:auto;border:1px solid #d6d6d6">
		<div class="row clearfix">
			<!--
			<div class="col-md-6 " style="margin-top:20px">
				<div class="panel panel-default" style="height:460px;">
					<div class="panel-heading"><h4 style="text-align:center;">权限配置<button class="btn btn-default" type="button" style="float:right;margin-bottom:25px;" onclick="getCheckNodes();">保存</button></h4></div>
				        <div class="panel-body">
				        	<input type="hidden" id="id" value="${id}"/>
				        	<div class="content_wrap">
				        		<div class="zTreeDemoBackground">
				                   <ul id="treeDemo" class="ztree" ></ul>
			                    </div>
			                   
			                    <input type="checkbox" id="py" class="checkbox first" checked style="display:none;"/>
								<input type="checkbox" id="sy" class="checkbox first" checked style="display:none;"/>
								<input type="checkbox" id="pn" class="checkbox first" checked style="display:none;"/>
								<input type="checkbox" id="sn" class="checkbox first" checked style="display:none;"/>
				           	</div>
				        </div>
				    </div>
			  	</div>   
			</div>	
			-->
			
			<input type="hidden" id="id" value="${id}"/>
			<div class="layui-tab">
			  <ul class="layui-tab-title">
			    <li class="layui-this">菜单权限</li>
			    <li>数据权限</li>
			  </ul>
			  <div class="layui-tab-content">
			    <div class="layui-tab-item layui-show">
				        		<div class="zTreeDemoBackground">
				                   <ul id="treeDemo" class="ztree" ></ul>
			                    </div>
			                   
			                    <input type="checkbox" id="py" class="checkbox first" checked style="display:none;"/>
								<input type="checkbox" id="sy" class="checkbox first" checked style="display:none;"/>
								<input type="checkbox" id="pn" class="checkbox first" checked style="display:none;"/>
								<input type="checkbox" id="sn" class="checkbox first" checked style="display:none;"/>   
			    </div>
			    <div class="layui-tab-item">
				        		<div class="zTreeDemoBackground">
									 <ul id="treeDemo2" class="ztree">
									 
									 </ul>
			                    </div>
			    </div>
			  </div>
			</div>		
							
		</div>
	</div>
</body>

<script type="text/javascript">
	var setting = {
		check: {
			enable: true
		},
		data: {  
    		simpleData: {  
        		enable: true//使用简单模式  
    		}  
		},  
		key:{  
			checked:"checked"//zTree 节点数据中保存check状态的属性名称。默认值："checked"  
		},  
		view:{  
	    	selectedMulti:true//允许选多个  
		}  
	};

	$.ajax({
		url: "${base}/role/getPermissionManageList",
		type: "post",
		dataType: "json",
		ContentType: "application/json; charset=utf-8",
		async: false,
		success: function (data) {
			zNodes = data;
			zNodes = eval(zNodes);   
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);//初始化树
		},
		error: function () {
			alert("zNodes");
		}
    });
	var code;
	function setCheck() {
		var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
		py = $("#py").attr("checked")? "p":"",
		sy = $("#sy").attr("checked")? "s":"",
		pn = $("#pn").attr("checked")? "p":"",
		sn = $("#sn").attr("checked")? "s":"",
		type = { "Y":py + sy, "N":pn + sn};
		zTree.setting.check.chkboxType = type;
		showCode('setting.check.chkboxType = { "Y" : "' + type.Y + '", "N" : "' + type.N + '" };');
	}
	function showCode(str) {
		if (!code) code = $("#code");
		code.empty();
		code.append("<li>"+str+"</li>");
	}
		
	$(document).ready(function(){
		$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		setCheck();
		$("#py").bind("change", setCheck);
		$("#sy").bind("change", setCheck);
		$("#pn").bind("change", setCheck);
		$("#sn").bind("change", setCheck);
	});
	/** 
	 *点击时获取勾选/未勾选的节点数量 
	 */  
	function getCheckNodes(){
    	var treeObj = $.fn.zTree.getZTreeObj("treeDemo");//获取树对象  
    	var checkNodes=null;  
    	//getCheckedNodes:获取当前树中输入框被勾选或未勾选的节点集合,true:被勾选的集合 false：未勾选的集合  
    	checkNodes= treeObj.getCheckedNodes(true);//勾选的数量  
		var Str='';
		for(var i=0;i<checkNodes.length;i++){
			Str+=$(checkNodes[i]).attr('id')+",";
	  	}
		return Str;
   	} 
   	
   	function saveMenu(){
		var flag=false;
		$.ajax({
	    	type:'post',
	    	url:'${base}/role/saveRolePermission',
	    	dataType:'json',
	    	async: false,
	    	data:{
	       		role_id:$('#id').val(),
	       		Str:getCheckNodes()
	    	},
		    success:function(data){
				if(data.errorCode==0) flag=true;
            },error:function(){
                layer.msg("保存失败！");
            }
	 	});	 	
	 	return flag;   	
   	}
</script>


<script type="text/javascript">
	//在树状权限菜单中显示对应角色可以操作的功能
	
	 $(function(){
	 	var id = $('#id').val();
	    $.ajax({
	       type:'post',
	       url:'${base}/role/getPermissionByRole',
	       data:{
	          id:id
	       },
	       dataType:'json',
	       success:function(d){
	           	//获取所有的节点数据
			   	$.fn.zTree.init($("#treeDemo"), setting, zNodes);
				var zTree = $.fn.zTree.getZTreeObj("treeDemo");
				var nodes=zTree.getNodes();
				var	nodes_array = zTree.transformToArray (nodes);
				for(var i=0;i<nodes_array.length;i++){
					var obj = d; //把字符串转化为json对象
				     //用jquery的方法遍历json对象数组
				    $.each(obj, function(index, content){
					  if((content.permission_id)==(nodes_array[i].id)){
					 
					     nodes_array[i].checked=true;
					     zTree.updateNode(nodes_array[i]);
					  } 
					});
				 }
	       	 }
	    });
     });
</script>



<script type="text/javascript">

    layui.use('form', function(){
	  var form = layui.form;
	  var layer = layui.layer;
	  
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
    
//注意：选项卡 依赖 element 模块，否则无法进行功能性操作
layui.use('element', function(){
  var element = layui.element;
});   

//保存权限
function saveAuthor(){
	if(!getCheckNodes()){
		layer.alert('请选择菜单！', {icon: 8});
		return;
	}
	if(!getCheckNodes2()){
		layer.alert('请选部门！', {icon: 8});
		return;
	}
	//保存可见部门
	saveUnit();
	//保存可见菜单
	saveMenu();
	if(saveUnit()&saveMenu()){
	    layer.msg("操作成功！",{icon: 1,time:1000,end:function(){
          	//layer.close(layer.index);
			window.parent.location.reload();
		}});
	}

} 



	//获取所有选中的节点
	function getCheckNodes2(){
    	var treeObj = $.fn.zTree.getZTreeObj("treeDemo2");//获取树对象  
    	var checkNodes=null;  
    	//getCheckedNodes:获取当前树中输入框被勾选或未勾选的节点集合,true:被勾选的集合 false：未勾选的集合  
    	checkNodes= treeObj.getCheckedNodes(true);//勾选的数量  
		var Str='';
		for(var i=0;i<checkNodes.length;i++){
			Str+=$(checkNodes[i]).attr('id');
			if(i!=checkNodes.length-1) Str+=",";
	  	}
	  	return Str;
   	} 
	var setting2 = {
		check: {
			enable: true,
			chkboxType: { "Y" : "s", "N" : "s" }
		},
        data : {  
                 
               simpleData : {  
                  enable : true,  
                  idKey : "id",  
                  pIdKey : "pid",  
                  rootPId : 0  
                }  
         },   
		key:{  
			checked:"checked",//zTree 节点数据中保存check状态的属性名称。默认值："checked"  
			name:"depart_name"
		},  
		view:{  
	    	selectedMulti:true//允许选多个  
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
				var zNodes2=data;
				for(var i=0;i<zNodes2.length;i++){
					data[i].name=data[i].depart_name;
				}
				$.fn.zTree.init($("#treeDemo2"), setting2, zNodes2);//初始化树
			},
			error: function () {
				alert("zNodes");
			}
	    });
	}
	
	function saveUnit(){
		var treeObj = $.fn.zTree.getZTreeObj("treeDemo2");//获取树对象  	
		var flag=false;
		$.ajax({
			url: "${base}/role/updateVisualDep",
			type: "post",
			dataType: "json",
			ContentType: "application/json; charset=utf-8",
			async: false,
	    	data:{
	       		id:$('#id').val(),
	       		visualDep:getCheckNodes2()
	    	},			
			success: function (result) {
				if(result.errorCode==0) flag=true;
			},
			error: function () {
				//alert("zNodes");
			}
	    });
	    return flag;
	}

	$(function(){
		initTree();
		//为可见部门树设置初始值
		$.post("${base}/role/getRoleManageMap",{id:$("#id").val()},function(result1){		
			initTree();
			var visual_dep=result1.visualDep;
			var treeObj2 = $.fn.zTree.getZTreeObj("treeDemo2");
			treeObj2.expandAll(true);	
			if(visual_dep){	
				visual_dep=visual_dep.split(",");			
				for(var i=0;i<visual_dep.length;i++){						
					var node = treeObj2.getNodeByParam("id",visual_dep[i],null);
					node.checked=true;
					treeObj2.updateNode(node,false);
				}
			}	
		},'json');		
	});
</script>




</html>