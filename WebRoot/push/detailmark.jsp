<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>详细统计</title>
    
	 <meta http-equiv="pragma" content="no-cache">
      <meta http-equiv="cache-control" content="no-cache">
      <meta http-equiv="expires" content="0">
      <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
      <meta http-equiv="description" content="This is my page">
       <link rel="stylesheet" type="text/css" href="css/themes/gray/easyui.css">
      <link rel="stylesheet" type="text/css" href="css/themes/icon.css">
      <script type="text/javascript" src="<%=basePath%>js/jquery-1.8.1.min.js"></script>
      <script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>
      <script type="text/javascript" src="<%=basePath%>js/easyui-lang-zh_CN.js"></script>

  </head>
   <style type="text/css">
* {
	font-family: "微软雅黑";
	font-size: 10pt;
		}
		
 body {
	width: 100%;
	margin: 0 auto;
	text-align: center;			
	overflow-x: hidden;
	overflow-y: auto ;			
		}
.line {
	height: 1px;
	font-size: 1px;
	background: #74B5E5;
	line-height: 1px;
	float: left;
}
 #searchdiv a{ display:block; width:40px; height:23px; text-align:center; font-size:10pt; line-height:23px;}			
 #searchdiv a:link{text-decoration:none ; background:#094AB2; color:#FFF ;}
 #searchdiv a:visited {text-decoration:none ; background:#094AB2; color:#FFF ;}
 #searchdiv a:hover {text-decoration:none ; background:#03F; color:#FFF ;}
 #searchdiv a:active {text-decoration:none ;background:#03e; color:#FFF ;}
 .nav_bt_div{ float:left; cursor:pointer;
}
.img_div {
	height: 40px;
	float: left;
	line-height: 40px;
	margin-left:20px;
}
.title_div {
	cursor: pointer;
	height: 40px;
	float: left;
	line-height: 40px;
	margin-right: 35px;	
}
</style>
  <body>
   <table  id="chartTable_one"  width="1024"  height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td width="24px" height="24px"><img src="./img/leftup.png" width="24px" height="24px"></td>
    <td width="100%" style="background:url(./img/upborder.png)  repeat-x"></td>
    <td width="24px" height="24px"><img src="./img/rightup.png" width="24px" height="24px"></td>
  </tr>
  <tr>
    <td  style="background:url(./img/leftborder.png) repeat-y"></td>
    <td width="100%" valign="top">
      <!--主要内容开始-->
        <div id="dif_type" class="nav_bt_div">
			<div id="create_pic" class="img_div" ><img src="./img/maintag/type.png" align="absmiddle" /></div>
			<div id="" class="title_div">分类统计</div>
		</div>	
		<div id="top_20" class="nav_bt_div">
			<div id="no_finish" class="img_div" ><img src="./img/maintag/top_gray.png" align="absmiddle" /></div>
			<div id="" class="title_div">TOP20</div>
		</div>
        <div class="line" style="width:100%;border:solid red 0px;margin-top:5px;height:1px;" >&nbsp;</div>
       <div style="float:left;margin-top:10px;height:25px;" id="slt_region_div">
	       <span>地域选择：</span>
	       <select id="slt_region" style="width:200px;height:25px;"></select>
	       <!-- <input type="button" id="test" value="aaa"/> -->
       </div>
       <div style="float:left;margin-left:20px;margin-top:10px;height:25px;margin-bottom:20px;">
	       <span>类型选择：</span>
	       <select id="slt_type" style="width:180px;height:25px;"></select>
       </div>
       <div style="float:left;margin-top:10px;margin-left:10px;" id="searchdiv">
           <a id="serch_bt" href="javascript:void(0)" hidefocus="true">搜索</a>
       </div>
        <div id="datagrid" style="margin:20px 20px;position: absolute; right:5px;" ></div>
      <!--主要内容结束--></td>
    <td  width="24px" style="background:url(./img/rightborder.png) "></td>
  </tr>
  <tr>
    <td width="24px" height="24px" style="background:url(./img/leftdown.png)   ">&nbsp;&nbsp;&nbsp;&nbsp;</td>
    <td   height="24px"   style="background:url(./img/downborder.png) "></td>
    <td width="24px" height="24px"  style="background:url(./img/rightdown.png)   ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
  </tr>
</table>
<script type="text/javascript">
 $(function(){
   var region;
       $("#slt_region").combotree({
		         url:'treeregionaction.action',
		         multiple:true,
		         editable:true,
		         cascadeCheck:true,
		         onlyLeafCheck:true,
		         onLoadSuccess:function(node,data){
		         // alert(data[0].children[1].text)
		         // $('#slt_hall').combotree('setValues',data[0].children[1].id);	
		    	   },
		    	 onClick:function(node){
		    	   $("#slt_region").combotree("setValue",node.id);
		    	   $("#slt_region").combotree("setText",node.text);
		    	 }
           });
           
       $("#slt_type").combobox({
	    url:'treesjsonaction.action',
		editable:true,
		valueField:'id',
		textField:'text',
		panelHight:'auto',
		editable:false,
	    onLoadSuccess:function(data){
	     $("#slt_type").combobox("setValue","all");
	   }
	});
	
	   $("#datagrid").datagrid({
	       url:'plantypeaction.action',
	       queryParams:{regions:"",
	                    type:$("#slt_type").combobox("getText")            
	               },
	       title:'分类统计',
	       height: 500,
	       pagination:true,
	       rownumbers:true,
	       fitColumns:true,
	       columns : [ [  {
					field : 'region',
					title : '地域',
					width: 100,
					align : 'center'
				}, {
					field : 'plantype',
					title : '活动类型',
					width: 100,
					align : 'center'
				}, {
					field : 'plancnt',
					title : '营销活动次数',
					width: 100,
					align : 'center'
				}, {
					field : 'successrate',
					title : '营销活动接触成功率（%）',
					width: 100,
					align : 'center'
					
				}] ],
            rowStyler: function(index,row){
					if (row.plantype == '汇总'){
						return 'color:red;font-weight:bold;';
					}
				}
			}); 
      
   $("#top_20").click(function(){
       $("#slt_region_div").hide();
       $("#create_pic").children().attr("src","./img/maintag/type_gray.png");
       $("#no_finish").children().attr("src","./img/maintag/top.png");
           $("#datagrid").datagrid({
	       url:'plantopaction.action',
	       title:'TOP20',
	       height: 500,
	       pagination:true,
	       rownumbers:true,
	       fitColumns:true,
	       columns : [ [  {
					field : 'planname',
					title : '活动名称',
					width: 200,
					align : 'left'
				}, {
					field : 'randid',
					title : '排名',
					width: 60,
					align : 'center'
				},{
					field : 'creator',
					title : '创建人',
					width: 100,
					align : 'center'
				},  {
					field : 'regionname',
					title : '活动范围',
					width: 100,
					align : 'center'
				},  {
					field : 'successrate',
					title : '成功率（%）',
					width: 60,
					align : 'center'
				},  {
					field : 'plantype',
					title : '活动类型',
					width: 100,
					align : 'center'
				}] ],
             onLoadSuccess:function(){
			
			}
			});
   });
   
   $("#dif_type").click(function(){
       $("#slt_region_div").show();
       $("#no_finish").children().attr("src","./img/maintag/top_gray.png");
       $("#create_pic").children().attr("src","./img/maintag/type.png");
        $("#datagrid").datagrid({
	       url:'plantypeaction.action',
	       queryParams:{
	                    regions:"",
	                    type:$("#slt_type").combobox("getText")
	       },
	       title:'分类统计',
	       height: 500,
	       pagination:true,
	       rownumbers:true,
	       fitColumns:true,
	       columns : [ [  {
					field : 'region',
					title : '地域',
					width: 100,
					align : 'center'
				}, {
					field : 'plantype',
					title : '活动类型',
					width: 100,
					align : 'center'
				}, {
					field : 'plancnt',
					title : '营销活动次数',
					width: 100,
					align : 'center'
				}, {
					field : 'successrate',
					title : '营销活动接触成功率（%）',
					width: 100,
					align : 'center'
					
				}] ],
             rowStyler: function(index,row){
					if (row.plantype == '汇总'){
						return 'color:red;font-weight:bold;';
					}
				}
			}); 
   
   }); 
   
   $("#serch_bt").click(function(){
       var title=$("#datagrid").datagrid("options").title;
       
       var regions=$("#slt_region").combotree("getValues")+"";
       if("分类统计"==title){
          $("#datagrid").datagrid('load',{	          
	          regions:regions,
	          type:$("#slt_type").combobox("getText")
	      }
	      );
       }else{
          $("#datagrid").datagrid('load',{	          
	          type:$("#slt_type").combobox("getText")
	      }
	      );
       
       }
   
   });      
       
  
 });

</script>
  </body>
</html>
