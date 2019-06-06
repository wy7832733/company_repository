function initChart1(departId){
	if(!departId) departId=1;
	$.post(base+"/certificate/getUnitCerCountChartData",{departId:departId},function(result){
		disposeChart("barChart");
		var myChart = echarts.init(document.getElementById("barChart"));
		option = {
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'cross',
		            crossStyle: {
		                color: '#999'
		            }
		        },
		        formatter:function(params){
		        	var result = params[0].axisValue;
		        	for(var i=0;i<params.length;i++){
		        		var item=params[i];
		        		result+="<br>"+item.marker+item.seriesName+":"+item.value;
		        		if(item.seriesIndex==2) result+="%";
		        	}
		        	return result;
		        }
		    },
		    toolbox: {
		        feature: {
		            //dataView: {show: true, readOnly: false},
		            magicType: {show: true, type: ['line', 'bar']},
		            restore: {show: true},
		            //saveAsImage: {show: true}
		        }
		    },
		    legend: {
		        data:['持证人数','未持证人数','持证率']
		    },
		    xAxis: [
		        {
		            type: 'category',
		            data: result.list1,
		            axisPointer: {
		                type: 'shadow'
		            }
		        }
		    ],
		    yAxis: [
		        {
		            type: 'value',
		            name: '人数',
		            //min: 0,
		            //max: 250,
		            //interval: 50,
		            minInterval:1,
		            axisLabel: {
		                formatter: '{value} 人'
		            }
		        },
		        {
		            type: 'value',
		        	
		            name: '持证率',
		            min: 0,
		            max: 100,
		            interval:20,
		            axisLabel: {
		                formatter: '{value} %'
		            },
		            splitLine:{
		            	show:false
		            }
		        }
		    ],
		    series: [
		        {
		            name:'持证人数',
		            type:'bar',
		            //barGap: '0',
		            data:result.list2
		        },
		        {
		            name:'未持证人数',
		            type:'bar',
		            data:result.list3
		        },
		        {
		            name:'持证率',
		            type:'line',
		            yAxisIndex: 1,
		            data:result.list4
		        }
		    ]
		};

		myChart.setOption(option, true);	

		myChart.on('click', function (params) {
		    drawPie1(result.list5[params.dataIndex],departId);
		    drawPie2(result.list5[params.dataIndex],departId);
		    drawPie3(result.list5[params.dataIndex],departId);
		});
	},'json');
}


function drawPie1(type,departId){
	$.post(base+"/certificate/getDepartGenderCerAnalysis",{departId:departId,type:type},function(result){
		if(result.length==0){
			result=[{name:'男',value:0},{name:'女',value:0}]
		}else if(result.length==1){
			if(result[0].name=="男") result.push({name:'女',value:0});
			if(result[0].name=="女") result.push({name:'男',value:0});
		}
		
		if(result[0].name=="女"){
			var x=result[0];
			result[0]=result[1];
			result[1]=x;
		}
		disposeChart("pieChart1");
		var myChart = echarts.init(document.getElementById("pieChart1"));
		
		option = {
			    title : {
			        text: '持证人员性别分布',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: ['男','女']
			    },
			    color:['#749f83','#d48265'],
			    series : [
			        {
			            name: '性别',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:result,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ]
			};
		myChart.setOption(option, true);
		
	});
	
}

function drawPie2(type,departId){
	
	$.post(base+"/certificate/getDepartAgeCerAnalysis",{departId:departId,type:type},function(result){
		disposeChart("pieChart2");
		var myChart = echarts.init(document.getElementById("pieChart2"));
		option = {
			    title : {
			        text: '持证人员年龄分布',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: ['<=30','31~40','41~50','>=51']
			    },
			    color:['#91c7ae','#3baeff',  '#ca8622', '#bda29a','#6e7074', '#546570', '#c4ccd3'],
			    series : [
			        {
			            name: '年龄',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:result,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ]
			};
		myChart.setOption(option, true);
		
	});	
}



function drawPie3(type,departId){
	
	$.post(base+"/certificate/getDepartPostCerAnalysis",{departId:departId,type:type},function(result){
		var list=[];
		for(var i=0;i<result.length;i++){
			list.push(result[i].name);
		}
		disposeChart("pieChart3");
		
		var myChart = echarts.init(document.getElementById("pieChart3"));
		option = {
			    title : {
			        text: '持证人员岗位分布',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: list
			    },
			    color:["#ad46f3","#44aff0",'#c23531','#2f4554', '#61a0a8', '#d48265', '#91c7ae','#749f83',  '#ca8622', '#bda29a','#6e7074', '#546570', '#c4ccd3'],
			    series : [
			        {
			            name: '岗位',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:result,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ]
			};
		myChart.setOption(option, true);
		
	});	
}


function disposeChart(container){
	var compareChart = echarts.getInstanceByDom(document.getElementById(container));	
	if(compareChart) compareChart.dispose();
	
}



function initBarChart2(year){
	if(!year) year=new Date().getFullYear();
	$.post(base+"/certificate/getMonthCerAnalysis",{year:year},function(result){
		var lengendList=[];
		for(var i=0;i<result.length;i++){
			lengendList.push(result[i].seriesName);
			var dataList=[];
			for(var j=1;j<13;j++){
				for(var k=0;k<result[i].data.length;k++){
					//console.log(result[i].data[k].month1);
					if(result[i].data[k].month1==j){
						//alert(1);
						dataList.push(result[i].data[k].cerCount);
					}else{
						dataList.push(0);
					}
				}
			}
			result[i].dataList=dataList;
		}
		
		//alert(JSON.stringify(lengendList));
		//alert(JSON.stringify(result));
		disposeChart("barChart2");
		var myChart = echarts.init(document.getElementById("barChart2"));
		option = {
		    tooltip: {
		        trigger: 'axis',
		        axisPointer: {
		            type: 'cross',
		            crossStyle: {
		                color: '#999'
		            }
		        },
		        formatter:function(params){
		        	var result = params[0].axisValue;
		        	for(var i=0;i<params.length;i++){
		        		var item=params[i];
		        		result+="<br>"+item.marker+item.seriesName+":"+item.value;
		        		if(item.seriesIndex==2) result+="%";
		        	}
		        	return result;
		        }
		    },
		    toolbox: {
		        feature: {
		            //dataView: {show: true, readOnly: false},
		            magicType: {show: true, type: ['line', 'bar']},
		            restore: {show: true},
		            //saveAsImage: {show: true}
		        }
		    },
		    legend: {
		        data:lengendList
		    },
		    xAxis: [
		        {
		            type: 'category',
		            data: ["1月","2月","3月","4月","5月","6月","7月","8月","9月","10月","11月","12月"],
		            axisPointer: {
		                type: 'shadow'
		            }
		        }
		    ],
		    yAxis: [
		        {
		            type: 'value',
		            name: '人数',
		            //min: 0,
		            //max: 250,
		            //interval: 50,
		            minInterval:1,
		            axisLabel: {
		                formatter: '{value} 人'
		            }
		        }
		    ],
		    
		    series:(function(){
		    	var serList=[];
		    	for(var i=0;i<result.length;i++){
		    		var ser={
					            name:result[i].seriesName,
					            type:'line',
					            //barGap: '0',
					            data:result[i].dataList		    				
		    				}
		    		serList.push(ser);
		    	}
		    	return serList;
		    })()
		};

		myChart.setOption(option, true);	

		myChart.on('click', function (params) {
		    var month=params.dataIndex+1;
		    var type=result[params.seriesIndex].type;
		    
		    
			drawPie4(type,year,month);
			drawPie5(type,year,month);
			drawPie6(type,year,month);
		});
	},'json');
}


function drawPie4(type,year,month){
	
	$.post(base+"/certificate/getMonthGenderCerAnalysis",{type:type,year:year,month:month},function(result){
		if(result.length==0){
			result=[{name:'男',value:0},{name:'女',value:0}]
		}else if(result.length==1){
			if(result[0].name=="男") result.push({name:'女',value:0});
			if(result[0].name=="女") result.push({name:'男',value:0});
		}
		
		if(result[0].name=="女"){
			var x=result[0];
			result[0]=result[1];
			result[1]=x;
		}
		disposeChart("pieChart4");
		var myChart = echarts.init(document.getElementById("pieChart4"));
		
		option = {
			    title : {
			        text: '所发证书性别分布',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: ['男','女']
			    },
			    color:['#749f83','#d48265'],
			    series : [
			        {
			            name: '性别',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:result,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ]
			};
		myChart.setOption(option, true);
		
	});
	
}

function drawPie5(type,year,month){
	
	$.post(base+"/certificate/getMonthAgeCerAnalysis",{type:type,year:year,month:month},function(result){
		disposeChart("pieChart5");
		var myChart = echarts.init(document.getElementById("pieChart5"));
		option = {
			    title : {
			        text: '所发证书年龄分布',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: ['<=30','31~40','41~50','>=51']
			    },
			    color:['#91c7ae','#3baeff',  '#ca8622', '#bda29a','#6e7074', '#546570', '#c4ccd3'],
			    series : [
			        {
			            name: '年龄',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:result,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ]
			};
		myChart.setOption(option, true);
		
	});	
}


function drawPie6(type,year,month){
	
	$.post(base+"/certificate/getMonthPostCerAnalysis",{type:type,year:year,month:month},function(result){
		var list=[];
		for(var i=0;i<result.length;i++){
			list.push(result[i].name);
		}
		disposeChart("pieChart6");
		
		var myChart = echarts.init(document.getElementById("pieChart6"));
		option = {
			    title : {
			        text: '所发证书岗位分布',
			        x:'center'
			    },
			    tooltip : {
			        trigger: 'item',
			        formatter: "{a} <br/>{b} : {c} ({d}%)"
			    },
			    legend: {
			        orient: 'vertical',
			        left: 'left',
			        data: list
			    },
			    color:["#ad46f3","#44aff0",'#c23531','#2f4554', '#61a0a8', '#d48265', '#91c7ae','#749f83',  '#ca8622', '#bda29a','#6e7074', '#546570', '#c4ccd3'],
			    series : [
			        {
			            name: '岗位',
			            type: 'pie',
			            radius : '55%',
			            center: ['50%', '60%'],
			            data:result,
			            itemStyle: {
			                emphasis: {
			                    shadowBlur: 10,
			                    shadowOffsetX: 0,
			                    shadowColor: 'rgba(0, 0, 0, 0.5)'
			                }
			            }
			        }
			    ]
			};
		myChart.setOption(option, true);
		
	});	
}


