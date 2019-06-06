 var LODOP = getLodop(document.getElementById('LODOP_OB'), document.getElementById('LODOP_EM'));	   
 function view() {  
	 //init();  
	 prn1_preview2();
	 LODOP.SET_SHOW_MODE("BKIMG_IN_PREVIEW",1);  
	 LODOP.PREVIEW();  
 }  
 function design() {  
	 //init();  
	 prn1_preview2();
	 document.getElementById('templateCode').value=LODOP.PRINT_DESIGN();  
 }  
 
 function init() {  
	 LODOP.PRINT_INIT("打印身份证");  
	 LODOP.ADD_PRINT_SETUP_BKIMG("<img border='0' src='"+base+"/res/id.png'>");  
	 
	 LODOP.SET_PRINT_STYLE("FontSize",11);  
	 LODOP.ADD_PRINT_TEXT(245,162,208,28,"1234567890X");  
	 LODOP.SET_PRINT_STYLEA(0,"FontName","新宋体");  
	 LODOP.SET_PRINT_STYLEA(0,"FontSize",10);  
	 LODOP.ADD_PRINT_TEXT(54,89,50,21,"张三");  
	 LODOP.SET_PRINT_STYLEA(0,"FontSize",9);  
	 LODOP.ADD_PRINT_TEXT(90,87,34,21,"男");  
	 LODOP.SET_PRINT_STYLEA(0,"FontSize",9);  
	 LODOP.ADD_PRINT_TEXT(91,183,31,25,"汉");  
	 LODOP.SET_PRINT_STYLEA(0,"FontSize",9);  
	 LODOP.ADD_PRINT_TEXT(159,83,198,35,"北京市东城区xxx街道第201号");  
	 LODOP.SET_PRINT_STYLEA(0,"FontSize",9);  
	 LODOP.ADD_PRINT_TEXT(125,93,39,17,"2015");  
	 LODOP.SET_PRINT_STYLEA(0,"FontSize",9);  
	 LODOP.ADD_PRINT_TEXT(126,153,29,17,"01");  
	 LODOP.SET_PRINT_STYLEA(0,"FontSize",9);  
	 LODOP.ADD_PRINT_TEXT(126,215,26,20,"31");  
	 LODOP.SET_PRINT_STYLEA(0,"FontSize",9); 
	 //以纸张边缘为基点
	 LODOP.SET_PRINT_MODE("POS_BASEON_PAPER",true);
	 //纸张大小
	 LODOP.SET_PRINT_PAGESIZE(1,1250,800,"身份证");	        
	 
	 //LODOP.ADD_PRINT_IMAGE(30,247,63,63,"<img border='0' src='${base}/res/touxiang.png'>");  
	 //LODOP.SET_PRINT_STYLEA(0,"FontSize",9); 
	 LODOP.ADD_PRINT_IMAGE(59,307,134,141,"<img border='0' style='z-index:-1' src='"+base+"/res/touxiang.png'>");  
	 //LODOP.ADD_PRINT_IMAGE(0,0,600,300,"<img border='0' src='${base}/res/id.png'>");  
 } 
 
 function prn1_preview2() {    //一个任务中循环多页，每页内容不同
	 LODOP=getLodop();  
	 var wenben=["一号文本","二号文本","三号文本","四号文本"];
	 LODOP.PRINT_INIT("");//初始化在循环外
	 LODOP.SET_PRINT_PAGESIZE(1,300,500,"");
	 for (i=0;i<wenben.length;i++){  
		 LODOP.NewPage();
		 LODOP.ADD_PRINT_TEXT(75,6,100,20,wenben[i]);
	 }
	 //LODOP.PRINT_DESIGN();
	 //LODOP.PREVIEW();
 }
 
 function downloadLodop(){
	 window.location.href=base+"/CLodop_Setup_for_Win32NT.exe";
 }
 
 

