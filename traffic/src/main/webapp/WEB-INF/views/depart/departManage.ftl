<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>单位管理</title>
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

<body  style="margin:0;padding:0;">
		<div class="panel panel-default">
			   	<div class="layui-form-item" style="margin-top:5px;margin-left:5px">
				     <div class="layui-block">
				     	<div class="layui-input-inline">
				            <input type="text" class="form-control" name="depart_name" id="depart_name" placeholder="部门名称"/>
				        </div> 
				        <div class="col-sm-1 col-sm-offset-1">
				            <button class="btn btn-primary" id="search_btn">查询</button>
				        </div>
				     </div>
				</div>
		</div>
		<table id="table" class="table table-hover" data-response-handler="responseHandler"></table>
		<div id="toolbar" class="btn-group pull-right" style="margin-right: 20px;">
			  <button id="remove" class="btn btn-danger">
			   		<i class="glyphicon glyphicon-remove"></i> 批量删除
              </button>
		       <button id="btn_add" type="button" class="btn  btn-success" onclick="add()">
		       		<span class="glyphicon glyphicon-plus" aria-hidden="true"></span>新增
               </button>
		 </div>

	
</body>

<script type="text/javascript">   
	layui.use('layer', function(){
	  var layer = layui.layer;
	});
	
	 //根据窗口调整表格高度
   	 $(window).resize(function() {
        $('#table').bootstrapTable('resetView', {
            height: tableHeight()
        })
   	 })
	       
      //生成用户数据
    $('#table').bootstrapTable({
        method: 'post',
        contentType: "application/x-www-form-urlencoded",//必须要有！！！！
        url:"${base}/depart/getDepartListData",//要请求数据的文件路径
        //height:tableHeight()-80,//高度调整
        toolbar: '#toolbar',//指定工具栏
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
        pageSize:10,//单页记录数
        pageList:[5,10,20,30],//分页步进值
        //search: true,
        showRefresh:true,//刷新按钮
        showColumns:true,
        clickToSelect: true,//是否启用点击选中行
        toolbarAlign:'right',//工具栏对齐方式
        buttonsAlign:'right',//按钮对齐方式
        columns:[
            {
                title:'全选',
                field:'select',
                //复选框
                checkbox:true,
                width:25,
                align:'center',
                valign:'middle'
            },
            {
                title:'id',
                field:'id',
                visible:false
            },
            {
                title:'单位名称',
                field:'depart_name',
                visible:true,
                sortable:true
            },
            {
                title:'单位描述',
                field:'intro',
                sortable:true
            },
            {
                    field:'ID',
                    title: '操作',
                    width: 220,
                    align: 'center',
                    valign: 'middle',
                    formatter: rowformater
                }, 
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
    })
  
	function rowformater(value,row,index){
      	 var buttons="";
    	 buttons+='<button class="layui-btn layui-btn-xs layui-btn-radius layui-btn-normal"  onclick="edit(\''+row.id+'\')" >';
    	 buttons+=		'<i class="layui-icon">&#xe642;</i>修改';
    	 buttons+='</button>';
    	 
    	 buttons+='<button class="layui-btn layui-btn-xs layui-btn-danger layui-btn-radius"  onclick="deleteUser(\''+row.id+'\')" >';
    	 buttons+=		'<i class="layui-icon">&#xe640;</i>删除';
    	 buttons+='</button>'; 
  
    	 buttons+='<button class="layui-btn layui-btn-xs layui-btn-radius" onclick="detail(\''+row.id+'\',\''+row.depart_name+'\')">';
    	 buttons+='<i class="layui-icon">&#xe655;</i>应持证';
    	 buttons+='</button>';   
    	     	 
    	 return buttons;
	}
	
	
	
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
     //查询按钮事件
    $('#search_btn').click(function(){
        $('#table').bootstrapTable('refresh', {url: '${base}/depart/getDepartListData'});
    })
    //tableHeight函数
    function tableHeight(){
        //可以根据自己页面情况进行调整
        return $(window).height();
    }

</script>
<script type="text/javascript">
	//批量删除获取ids
	 selections = [];
	 function getIdSelections() {
         return $.map($('#table').bootstrapTable('getSelections'), function (row) {
         
             return row.id
         });
     }
     
     function responseHandler(res) {
         $.each(res.rows, function (i, row) {
             row.state = $.inArray(row.id, selections) !== -1;
         });
         return res;
     }
		
	 $('#remove').click(function () {
	 		var ids = getIdSelections();
	 		if(ids=="") {
	 			layer.msg("请选择需删除数据！");
	 			return false;
	 		}
	 		$('#table').bootstrapTable('remove', {
	             field: 'id',
	             values: ids
	    	 });
			layer.confirm('确定要删除么', {icon: 3, title:'提示'}, function(index){
		    	 $.ajax({
		             type: "post",
		             url: "${base}/depart/deleteDepart",
		             data: { strJson: ids.toString() },
		             success: function (data) {
		                 layer.msg("删除成功");
		                  $('#table').bootstrapTable('refresh');
		             },
		             error: function () {
		                 layer.msg("删除失败");
		             },
		             complete: function () {
		 
		             }
		 
		         });				
				layer.close(index);
			
			});		  
			

	 });
	 
	 //单个删除
	 function deleteUser(id) {
	  
			layer.confirm('确定要删除么', {icon: 3, title:'提示'}, function(index){
				 	$.ajax({
				             type: "post",
				             url: "${base}/depart/deleteDepart",
				             data: { strJson: id.toString() },
				             success: function (data) {
				                 layer.msg("删除成功");
				                  $('#table').bootstrapTable('refresh');
				             },
				             error: function () {
				                 layer.msg("删除失败");
				             },
				             complete: function () {
				 
				             }
				 
				         });			
				layer.close(index);
			});		  

	 }
	 
	
</script>

<script type="text/javascript">
	//添加页面及添加操作
	function add(){
		layer.open({
                   title: '新增单位',//Layer提供了5种层类型。可传入的值有：0（信息框，默认）1（页面层）2（iframe层）3（加  载层）4（tips层）,
                    type: 2,
                    area: ['90%', '80%'],
                    shade: 0.1, //遮罩透明度
                    fixed: false, //不固定
                    maxmin: true,
                    shadeClose: true,
                    content: '${base}/depart/departAdd'
                });
	}
</script>

<script type="text/javascript">
	//修改页面及添加操作
	function edit(id){
		layer.open({
                   title: '修改单位',
                    type: 2,
                    area: ['90%', '90%'],
                    fixed: false, //不固定
                    maxmin: true,
                    shadeClose: true,
                    content: '${base}/depart/departAdd?id='+id
                });
	}
		
</script>

<script type="text/javascript">
	//单位应持证页面
	function detail(id,departName){
		layer.open({
                   title: '单位应持证',
                    type: 2,
                    area: ['100%', '100%'],
                    shade: 0.1, //遮罩透明度
                    fixed: false, //不固定
                    maxmin: true,
                    shadeClose: true,
                    content: '${base}/departCertificate/departCertificateManage?id='+id+'&departName='+departName
                });
	}
</script>

</html>
