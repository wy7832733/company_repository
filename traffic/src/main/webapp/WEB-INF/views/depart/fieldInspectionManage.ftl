<!DOCTYPE html>
<html>

<head>
    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>员工管理</title>
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
<style>
.treeDiv {
	margin: 30px 0px 0px 30px;
    padding: 15px;
    min-width: 250px;
    background: #f9f9f9;
    border: solid 1px #f0f0f0;
    border-radius: 5px;
	position: absolute;
    z-index: 999;
}
.hotelkind{
		    background: #ffb752;
    display: inline-block;
    padding: 5px 10px;
    border-radius: 18px;
    color: #fff;
    font-weight: bold;
	}
.infoDiv {
	margin:30px 30px 0px 300px;
	width: 71%;
	background: #f9f9f9; 
	padding:50px 20px 50px 20px;  
	overflow:auto; 
}

.left {
	float: left;
}

.table tr{
	border:0px;
}
.ztree li a.curSelectedNode{background:none; color:#f00; border:none;}

td,th{
	text-align:center;
	
}
</style>
<body  style="margin:0;padding:0;">
	<div class="layui-container" style="width:100%">  
	  <div class="layui-row">
	    <div class="left treeDiv" style="height:1000px">
			<span class="hotelkind">组织架构图</span> </label>
			<div id="treeDiv" style="width:100%;height:100%">
				<div id="treeDemo" class="ztree">
				
				</div>
			</div>	     
	    </div>
	    <div class="left infoDiv" style="height:1000px">

		<div class="row clearfix">
			<div class="col-md-12 column" style="margin-top:-25px" >
					<div id="depart_table">
						<table id="table" class="table table-hover table-sm" data-response-handler="responseHandler"></table>						
						<table id="table2" class="table table-hover table-sm" data-response-handler="responseHandler"></table>						
					</div>
					<div id="workers_table">
						<table class="table table-hover table-bordered" id="table3">
					   		<tbody>
					   			<tr>
									<th colspan="7" >人员信息</th>
						      	</tr>
					   			<tr>
						      		<th style="width:20%;vertical-align:middle;" rowspan="4">
							      		人员<br>基本信息
							      	</th>						      		
						      		<th style="width:20%">
							      		&nbsp;姓&nbsp;&nbsp;&nbsp;&nbsp;名
							      	</th>
							        <td style="vertical-align:middle;">
										<lable id="worker_name"></lable>
							        </td>
						      		<th style="width:20%">
							      		&nbsp;性&nbsp;&nbsp;&nbsp;&nbsp;别
							      	</th>							        
							        <td style="vertical-align:middle;">
										<lable id="worker_gender"></lable>
							        </td>
							    </tr>
					   			<tr>
						      		<th style="width:20%">
							      		&nbsp;岗&nbsp;&nbsp;位
							      	</th>
							        <td style="vertical-align:middle;">
										<lable id="worker_post"></lable>
							        </td>
						      		<th style="width:20%">
							      		&nbsp;职&nbsp;&nbsp;称
							      	</th>							        
							        <td style="vertical-align:middle;">
										<lable id="worker_title"></lable>
							        </td>
							    </tr>							    						    
					   			<tr>
						      		<th style="width:20%">
							      		&nbsp;身&nbsp;&nbsp;份&nbsp;&nbsp;证&nbsp;&nbsp;号
							      	</th>
							        <td style="vertical-align:middle;">
										<lable id="worker_idCode"></lable>
							        </td>
						      		<th style="width:20%">
							      		&nbsp;HR&nbsp;&nbsp;编&nbsp;&nbsp;码
							      	</th>							        
							        <td style="vertical-align:middle;">
										<lable id="worker_hrCode"></lable>
							        </td>
							    </tr>
					   			<tr>
						      		<th style="width:20%">
							      		&nbsp;单&nbsp;&nbsp;位
							      	</th>
							        <td style="vertical-align:middle;">
										<lable id="worker_unit"></lable>
							        </td>
						      		<th style="width:20%">
							      		&nbsp;基&nbsp;&nbsp;层&nbsp;单&nbsp;&nbsp;位
							      	</th>							        
							        <td style="vertical-align:middle;">
										<lable id="worker_depart"></lable>
							        </td>
							    </tr>
							    								    
					   			<tr>
						      		<th style="width:20%;vertical-align:middle;" rowspan="6" id="ros1">
							      		人员<br>证书信息
							      	</th>						      		
						      		<th style="width:20%" rowspan="2" style="vertical-align:middle;" id="ros2">
							      		岗位<br>应持证书
							      	</th>
							      	
							        <td style="vertical-align:middle;" colspan="5" class="dynamicGenerate">
										<lable></lable>
							        </td>
							    </tr>
							    							    
					   			<tr class="dynamicGenerate">
							        <td style="vertical-align:middle;" colspan="5">
										<lable ></lable>
							        </td>
							    </tr>
							    
					   			<tr>
						      		<th style="width:20%" rowspan="2" style="vertical-align:middle;" id="ros3">
							      		岗位<br>缺少证书
							      	</th>
							      	
							        <td style="vertical-align:middle;" colspan="5" class="dynamicGenerate">
										<lable></lable>
							        </td>
							    </tr>	
					   			<tr class="dynamicGenerate">
							        <td style="vertical-align:middle;" colspan="5">
										<lable></lable>
							        </td>
							    </tr>
							    							    							    							    
					   			<tr>
						      		<th style="width:20%" rowspan="2" style="vertical-align:middle;" id="ros4">
							      		当前<br>有效证书
							      	</th>
							      	
							        <td style="vertical-align:middle;" colspan="5" class="dynamicGenerate">
										<lable></lable>
							        </td>
							    </tr>
							    	
					   			<tr class="dynamicGenerate">
							        <td style="vertical-align:middle;" colspan="5">
										<lable></lable>
							        </td>
							    </tr>							    							    							    
					   		</tbody>
						</table>
					</div>
			</div>
		</div>

	     	
	    </div>
	  </div>
	</div>
</body>
<script type="text/javascript">
	$(function(){
		initTree();
	});
	
	
	function initTree(){
		$.ajax({
			url: "${base}/depart/getOrgTreeData",
			type: "post",
			dataType: "json",
			ContentType: "application/json; charset=utf-8",
			async: false,
			success: function (data) {
				zNodes = data;
				//zNodes = eval(zNodes);                          //序列化json数据
				for(var i=0;i<zNodes.length;i++){
					zNodes[i].name=zNodes[i].name;
				}
				var roletree= $.fn.zTree.init($("#treeDemo"), setting, zNodes);//初始化树
	            var node = roletree.getNodes()[0];
	            roletree.selectNode(node);
	            roletree.expandAll(true);
	            setting.callback.onClick(null, roletree.setting.treeId, node);				
			},
			error: function () {
				alert("zNodes");
			}
	    });
	} 
	
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
			name:"name"
		},  
		view:{  
	    	selectedMulti:false//允许选多个  
		},
		callback:{
			onClick: zTreeOnClick
		}  
	};	


	function zTreeOnClick(event, treeId, treeNode) {
	    var nodename=treeNode.name;
	    var nodeid=treeNode.id;
	    if(treeNode.type=="depart"){
	    	$("#depart_table").show();
	    	$("#workers_table").hide();
	    	var departId=nodeid;
 			$('#table').bootstrapTable('refresh', {url: '${base}/departCertificate/getDepartCertificateCondition?departId='+departId});	    	
 			$('#table2').bootstrapTable('refresh', {url: '${base}/post/getPostCertificateCondition?departId='+departId});	    	
	    }else if(treeNode.type=="workers"){
	    	$("#depart_table").hide();
	    	$("#workers_table").show();	
	    	initWorkerInfo(nodeid.replace("workers_",""));
	    	    
	    }

	}

	
	var winHeight = $(window).height(); 
    $(".left").css("height",winHeight); 
    $(window).resize(function() { 
        winHeight = $(window).height(); 
        $("#show").css("height",winHeight); 
    });	
    
     function responseHandler(res) {
         $.each(res.rows, function (i, row) {
             row.state = $.inArray(row.id, selections) !== -1;
         });
         return res;
     }
     
     
     //生成用户数据
    $('#table').bootstrapTable({
        method: 'post',
        contentType: "application/x-www-form-urlencoded",//必须要有！！！！
        url:"${base}/departCertificate/getDepartCertificateCondition",//要请求数据的文件路径
        //height:292,//高度调整
        //toolbar: '#toolbar',//指定工具栏
        striped: true, //是否显示行间隔色
        cache: false,//是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        dataField: "rows",
        //bootstrap table 可以前端分页也可以后端分页，这里
        //我们使用的是后端分页，后端分页时需返回含有total：总记录数,这个键值好像是固定的  
        //rows： 记录集合 键值可以修改  dataField 自己定义成自己想要的就好
        pageNumber: 1, //初始化加载第一页，默认第一页
        pagination:true,//是否分页
        queryParamsType:'limit',//查询参数组织方式
        queryParams:queryParams,//请求服务器时所传的参数
        sidePagination:'server',//指定服务器端分页
        pageSize:5,//单页记录数
        pageList:[5,10,20,30],//分页步进值
        //search: true,
        //showRefresh:true,//刷新按钮
        //showColumns:true,
        clickToSelect: true,//是否启用点击选中行
        toolbarAlign:'right',//工具栏对齐方式
        buttonsAlign:'right',//按钮对齐方式
        columns:[
            [
	             {
	                title:'单位应持证情况',
	                align: 'center',
	                valign: 'middle',
	                sortable:true,
	                colspan: 6
	            }        
            ],
            [{
                title:'应持证名称',
                field:'certificateTypeName',
                visible:true,
                align: 'center',
                valign: 'middle',
                sortable:true
            },
            {
                title:'应持证数量',
                field:'certificateNum',
                align: 'center',
                valign: 'middle',
                sortable:true
            },
            {
                title:'应持证率（%）',
                field:'certificatePercent',
                align: 'center',
                valign: 'middle',
                sortable:true
            },
            {
                title:'当前持证数量',
                field:'realCertificateNum',
                align: 'center',
                valign: 'middle',
                sortable:true
            },
            {
                title:'当前持证率（%）',
                field:'realCertificatePercent',
                align: 'center',
                valign: 'middle',
                sortable:true
            },                       
            {
                    field:'ID',
                    title: '操作',
                    align: 'center',
                    valign: 'middle',
                    formatter: rowformater1
                },]
        ],
        onEditableSave: function (field, row, oldValue, $el) {
        	$.ajax({
                  type: "post",
                  url: "${base}/depart/updateDepart",
                  data: { strJson: JSON.stringify(row) },
                  success: function (data, status) {
                      if (status == "success") {
                          alert("编辑成功");
                      }
                  },
                  error: function () {
                      alert("Error");
                  },
                  complete: function () {
 
                  }
 
              });
        },
        locale:'zh-CN',//中文支持,
        responseHandler:function(result){
        	var rows = [];
        	var rows = {rows:result.rows,total:result.total};
            return rows;
        }
    });   
    
        //请求服务数据时所传参数
    function queryParams(params){
        return{
            //每页多少条数据
            limit: params.limit,
            //请求第几页
            offset:params.offset,
            depart_name:$('#depart_name').val(),
        }
    }   
    
 	function rowformater1(value,row,index){
      	 var buttons="";
  
    	 buttons+='<button class="layui-btn layui-btn-xs layui-btn-radius" onclick="showDetail1(\''+row.departId+'\',\''+row.certificateType+'\')">';
    	 buttons+='<i class="layui-icon">&#xe655;</i>详情';
    	 buttons+='</button>';   
    	     	 
    	 return buttons;
	}
 	function rowformater2(value,row,index){
      	 var buttons="";
  
    	 buttons+='<button class="layui-btn layui-btn-xs layui-btn-radius" onclick="showDetail2('+row.id+',\''+row.name+'\')">';
    	 buttons+='<i class="layui-icon">&#xe655;</i>详情';
    	 buttons+='</button>';   
    	     	 
    	 return buttons;
	}
	
	function showDetail1(departId,certificateTypeId){
		layui.use('layer', function(){
		  var layer = layui.layer;
			layer.open({
	                   title: '单位持证情况详情',
	                    type: 2,
	                    area: ['100%', '100%'],
	                    shade: 0.1, //遮罩透明度
	                    fixed: false, //不固定
	                    maxmin: true,
	                    shadeClose: true,
	                    content: '${base}/certificate/cerWorkersDetail?departId='+departId+'&certificateTypeId='+certificateTypeId
	                });
		}); 	                
	}
	
	
	function showDetail2(postId,postName){
       var zTreeObj = $.fn.zTree.getZTreeObj("treeDemo");
		var departId = zTreeObj.getSelectedNodes()[0].id;	
		layui.use('layer', function(){
		  var layer = layui.layer;
			layer.open({
	                   title: '岗位持证情况详情',
	                    type: 2,
	                    area: ['100%', '100%'],
	                    shade: 0.1, //遮罩透明度
	                    fixed: false, //不固定
	                    maxmin: true,
	                    shadeClose: true,
	                    content: '${base}/postCertificate/postCertificateManage2?departId='+departId+'&postId='+postId+"&postName="+postName
	                });
		}); 
	}
	
	
     //生成用户数据
    $('#table2').bootstrapTable({
        method: 'post',
        contentType: "application/x-www-form-urlencoded",//必须要有！！！！
        url:"${base}/post/getPostCertificateCondition",//要请求数据的文件路径
        //height:292,//高度调整
        //toolbar: '#toolbar',//指定工具栏
        striped: true, //是否显示行间隔色
        cache: false,//是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        dataField: "rows",
        //bootstrap table 可以前端分页也可以后端分页，这里
        //我们使用的是后端分页，后端分页时需返回含有total：总记录数,这个键值好像是固定的  
        //rows： 记录集合 键值可以修改  dataField 自己定义成自己想要的就好
        pageNumber: 1, //初始化加载第一页，默认第一页
        pagination:true,//是否分页
        queryParamsType:'limit',//查询参数组织方式
        queryParams:queryParams1,//请求服务器时所传的参数
        sidePagination:'server',//指定服务器端分页
        pageSize:5,//单页记录数
        pageList:[5,10,20,30],//分页步进值
        //search: true,
        //showRefresh:true,//刷新按钮
        //showColumns:true,
        clickToSelect: true,//是否启用点击选中行
        toolbarAlign:'right',//工具栏对齐方式
        buttonsAlign:'right',//按钮对齐方式
        columns:[

	            [
		             {
		                title:'人员岗位应持证情况',
		                align: 'center',
		                valign: 'middle',
		                sortable:true,
		                colspan: 5
		            }        
	            ],

            [{
                title:'id',
                field:'id',
                visible:false
            },
            {
                title:'岗位名称',
                field:'name',
                visible:true,
                align: 'center',
                valign: 'middle',
                sortable:true
            },
            {
                title:'持证合格人数',
                field:'qualifiedNum',
                align: 'center',
                valign: 'middle',
                sortable:true
            },
            {
                title:'持证不合格人数',
                field:'notQualifiedNum',
                align: 'center',
                valign: 'middle',                
                sortable:true
            },          
            {
                    field:'ID',
                    title: '操作',
                    align: 'center',
                    valign: 'middle',
                    formatter: rowformater2
                }
           ]
        ],
        onEditableSave: function (field, row, oldValue, $el) {
        	$.ajax({
                  type: "post",
                  url: "${base}/depart/updateDepart",
                  data: { strJson: JSON.stringify(row) },
                  success: function (data, status) {
                      if (status == "success") {
                          alert("编辑成功");
                      }
                  },
                  error: function () {
                      alert("Error");
                  },
                  complete: function () {
 
                  }
 
              });
        },
        locale:'zh-CN',//中文支持,
        responseHandler:function(result){
        	var rows = [];
        	var rows = {rows:result.rows,total:result.total};
            return rows;
        }
    });   
    
        //请求服务数据时所传参数
    function queryParams1(params){
        return{
            //每页多少条数据
            limit: params.limit,
            //请求第几页
            offset:params.offset,
        }
    }	
    
	
	function initWorkerInfo(id){
		$.post("${base}/workers/getWorkerInfo",{id:id},function(data){
			$("#worker_name").html(data.worker.name);
			$("#worker_gender").html(data.worker.gender);
			$("#worker_post").html(data.worker.postText);
			$("#worker_title").html(data.worker.title);
			$("#worker_idCode").html(data.worker.identifyCode);
			$("#worker_hrCode").html(data.worker.hrCode);
			$("#worker_unit").html(data.worker.unitText);
			$("#worker_depart").html(data.worker.depart);
			//alert(JSON.stringify(data.worker));
			var size1=data.list1.length;
			var size2=data.list2.length;
			var size3=data.list3.length;
			
			$(".dynamicGenerate").remove();
			var x=0;
			x=x+(size1==0?1:size1);
			x=x+(size2==0?1:size2);
			x=x+(size3==0?1:size3);
			$("#ros1").attr("rowspan",x);
			drawTrTd(size1,"#ros2",data.list1);
			drawTrTd(size2,"#ros3",data.list2);
			drawTrTd(size3,"#ros4",data.list3);
			
			
		},'json');
	}	
	
	function drawTrTd(size,id,data){
			if(size>0){
				if(id=="#ros4"){
					for(var j=0;j<size;j++){
						data[j].certificateTypeText=data[j].certificateName;
					}
				}
				var name=data[0].certificateTypeText;
				
				$(id).attr("rowspan",size);
				var html="";
				html+='<td style="vertical-align:middle;" colspan="5" class="dynamicGenerate">';
				html+=		'<lable>'+name+'</lable>';
				html+='</td>';				
				$(id).parent().append(html);
				
				html="";
				
				for(var i=1;i<size;i++){
					var name2=data[i].certificateTypeText;
					html+='<tr class="dynamicGenerate">';
					html+=		'<td style="vertical-align:middle;" colspan="5">';
					html+=			'<lable>'+name2+'</lable>';
					html+=		'</td>';
					html+='</tr>';
				}
				$(id).parent().after(html);
			}else{
				$(id).attr("rowspan",1);
				var html="";
				html+='<td style="vertical-align:middle;" colspan="5" class="dynamicGenerate">';
				html+=		'<lable>无</lable>';
				html+='</td>';				
				$(id).parent().append(html);				
			}	
	}
	    
	      
</script>

</html>
