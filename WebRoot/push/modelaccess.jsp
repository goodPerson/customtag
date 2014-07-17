<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'modelaccess.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">  
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>  
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="<%=path%>/css/themes/default/easyui.css">
	<link rel="stylesheet" type="text/css" href="css/themes/icon.css"> 
	<link rel="stylesheet" href="<%=basePath%>js/demo.css" type="text/css"></link>
    <script type="text/javascript" src="<%=basePath%>/js/jquery-1.8.0.min.js"></script>
    <script type="text/javascript" src="<%=basePath%>/js/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script> 	
    <style type="text/css">
	* {
		font-family: "微软雅黑";
		font-size: 10pt;
	}
	
	html {
		overflow-x: hidden;
		overflow-y: auto;
	}
	
	body {
		width: 1024px;
		font: 10pt "微软雅黑";
		text-align: center;
		margin: 0px auto;
		background: #fff;
		overflow-x: hidden;
		overflow-y: auto;
	}
	.border {
	      border:1px solid #95B8E7;
	     } 
#find_bt a {
	display: block;
	width: 40px;
	height: 25px;
	text-align: center;
	font-size: 10pt;
	line-height: 25px;
}
#find_bt a:link {
	text-decoration: none;
	background: #0bf;
	color: #FFF;
}
#find_bt a:visited {
	text-decoration: none;
	background: #0bf;
	color: #FFF;
}
#find_bt a:hover {
	text-decoration: none;
	background: #0dd;
	color: #FFF;
}
#find_bt a:active {
	text-decoration: none;
	background: #0bf;
	color: #FFF;
}
		</style>  
 </head>
  
  <body>
    <table  id="chartTable_one"  width="1024"  height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
	  <td width="24px" height="24px" style="background:url(./img/leftup.png)  ">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	  <td width="24px" style="background:url(./img/upborder.png)  "></td>
	  <td width="24px" height="24px" style="background:url(./img/rightup.png)  ">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	</tr>
	<tr>
	  <td width="24px" style="background:url(./img/leftborder.png) "></td>
	  <td width="100%" valign="top">	  
	  <div id="cc" class="easyui-layout" style="width:1000px;height:650px;">
		<div data-options="region:'west',split:true" title="West" style="width:180px;">
					<ul id="menu" class="easyui-tree" data-options="url:'<%=basePath%>/js/tree_data1.json',animate:false,dnd:false"></ul>
			</div>
		<div data-options="region:'center',title:'Center'" style="padding:10px;height:500px;">
			    <div style="height:500px;">
				    <div style="border:1px solid #95B8E7;height:40px;margin:10px auto 10px auto;">
				    	<div style="float:left;background:#C0C0C0;height:40px;font-weight:bold;line-height:40px;padding-left:15px;padding-right:20px;">条件选择</div>
				         <div style="float:left;padding:9px;">
						       &nbsp;日期:
						      <input  id="input_date"  style="max-width:180px;float:left;" class="easyui-datebox"></input>
						       &nbsp;地市：
							 <!--  <select class="easyui-combobox" panelHeight="auto" style="max-width:180px;float:left;" id="slt_county">
							  </select> -->
							  <select id="slt_county" class="easyui-combobox" panelHeight="auto" style="width:150px;"></select>
							   &nbsp;条件2：
							  <!-- <select class="easyui-combobox" panelHeight="auto" style="max-width:180px;float:left;" id="slt_hall">
							  </select> -->
							     <input type="text" id="input_salerId" class="border" style="height:21px;max-width:120px;"></input>
						</div>
						 <div style="float:left;cursor:pointer;padding-top:8px;padding-left:0px;" id="find_bt"><a href="javascript:void(0)" hidefocus="true">搜索</a></div>
				    </div>
					<div id="datagrid" style="height:100px;"> </div>	
			 </div>
		</div>
	</div> 
	  </td>
	  <td width="24px" style="background:url(./img/rightborder.png) "></td>
	</tr>
	<tr>
	  <td width="24px" height="24px" style="background:url(./img/leftdown.png)   ">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	  <td height="24px"   style="background:url(./img/downborder.png)   "></td>
	  <td width="24px" height="24px"  style="background:url(./img/rightdown.png)   ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
    </tr>
</table> 
<script type="text/javascript">
     $(function(){
        $("#menu").tree({
			onClick: function(node){
				//alert(node.text);
				   if(node.text=='index.html'){  // alert node text property when clicked
             $("#datagrid").datagrid({
        	     url:'<%=basePath%>/js/datagrid_data1.json',
				       title : '指标测试',
				        height:500,
                       pagination:true,
				       fitColumns:true,
				       columns : [ [  {
										field : 'itemid',
										title : '系列号',
										width: 80,
										align : 'center'
									}, {
										field : 'productid',
										title : '产品号',
										width: 100,
										align : 'center'
									}, {
										field : 'listprice',
										title : '价格',
										width: 80,
										align : 'left'
									}, {
										field : 'unitcost',
										title : '消费',
										width: 80,
										align : 'left'
									}, {
										field : 'attr1',
										title : '属性',
										width: 150,
										align : 'center'
									}, {
										field : 'status',
										title : '状态',
										width: 50,
										align : 'center'
									}] ]
				 });
				   
         
	             }
	            //alert ($("#datagrid").datagrid("options").url);       
	}
}); 
          var a= $.getJSON('<%=basePath%>/js/datagrid_data1.json');
        alert(a);
        $("#datagrid").datagrid({
        	           url:'<%=basePath%>/js/datagrid_data1.json',
				       title : '指标内容',
				       height:500,
				       pagination:true,
				       fitColumns:true,
				       columns : [ [  {
										field : 'itemid',
										title : 'itemid',
										width: 80,
										align : 'center'
									}, {
										field : 'productid',
										title : 'productid',
										width: 100,
										align : 'center'
									}, {
										field : 'listprice',
										title : 'listprice',
										width: 80,
										align : 'left'
									}, {
										field : 'unitcost',
										title : 'unitcost',
										width: 80,
										align : 'left'
									}, {
										field : 'attr1',
										title : 'attr1',
										width: 150,
										align : 'center'
									}, {
										field : 'status',
										title : 'status',
										width: 50,
										align : 'center'
									}] ]
				 });
		
		
		//屏蔽右键菜单。		 
       $(document).bind('contextmenu',function(e){ return false; });
     	
     	});
	</script>
  </body>
</html>
