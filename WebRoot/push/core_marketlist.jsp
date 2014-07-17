<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>重点营销活动列表</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
  


 <link rel="stylesheet" type="text/css" href="<%=basePath%>css/themes/gray/easyui.css">
    <link rel="stylesheet" type="text/css" href="css/themes/icon.css"> 
	<link rel="stylesheet" href="<%=basePath%>js/demo.css" type="text/css"></link>
    <script type="text/javascript" src="<%=basePath%>js/jquery-1.9.1.js"></script>
	<script type="text/javascript" src="<%=basePath%>js/highcharts.js"></script>

   
     <script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
  
    
    <script type="text/javascript" src="<%=basePath%>js/jquery.easyui.min.js"></script>
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

.line{
	height: 0px;
	font-size: 0px;
	background: #E8E8E8;
	line-height: 0px;
	float: left;	
}

#searchdiv a {
	display: block;
	width: 40px;
	height: 25px;
	text-align: center;
	font-size: 10pt;
	line-height: 25px;
}
#searchdiv a:link {
	text-decoration: none;
	background: #094AB2;
	color: #FFF;
}
#searchdiv a:visited {
	text-decoration: none;
	background: #094AB2;
	color: #FFF;
}
#searchdiv a:hover {
	text-decoration: none;
	background: #03F;
	color: #FFF;
}
#searchdiv a:active {
	text-decoration: none;
	background: #03e;
	color: #FFF;
}
#close_div{
    z-index:91;
     top:100px;
    float:center;
    position: absolute;
}
#close_div a {
	display: block;
	width: 40px;
	height: 25px;
	text-align: center;
	font-size: 10pt;
	line-height: 25px;
}
#close_div a:link {
	text-decoration: none;
	background: #094AB2;
	color: #FFF;
}
#close_div a:visited {
	text-decoration: none;
	background: #094AB2;
	color: #FFF;
}
#close_div a:hover {
	text-decoration: none;
	background: #03F;
	color: #FFF;
}
#close_div a:active {
	text-decoration: none;
	background: #03e;
	color: #FFF;
}

#wait_div {
	background: #666;
	margin: 0;
	width: 200%;
	height: 100%;
	position: absolute;
	left: 0px;
	opacity: 0.1;
	z-index: 99;
	display: none;
}
#plan_valuate{
    border:solid #2C92DD 1px;
    z-index:90;
    background:#fff;
    width:701px;
    height:500px;
    top:100px;
    position: absolute;
}
#serch_div{
position:absolute;left:425px; top:94px; width:427px;border-bottom:solid #CCC 1px; border-left:solid #CCC 1px; border-right:solid #CCC 1px; background:#FFF; height:200px; overflow:auto; display:none;	
}
.markt_tishi_div{
margin:2px;color:#666;cursor:pointer;
}
#benefit_div{
    height:500px;
    width:200px;
    border:solid #2C92DD 1px;
    position:absolute;
    right:-250px;
    top:50px;
    z-index:2;
}
#collapse{
  cursor:pointer; 
}
.taginfo_div_title{color:8d8f84;float:left;position:relative; width:90px; margin-left:10px; margin-top:10px; text-align:left;}
.taginfo_div_item{color:#000;float:left;position:relative; margin-left:5px;margin-top:10px;width:90px; text-align:left;v-align:center}

</style>


</head>

<body>
<div id="wait_div" class="wait_div" ></div>
  <img id="load_div" src="./img/load/load.jpg" width="32" height="32" align="absmiddle" style="z-index:100; position:absolute; left:49%;top:40%; display:none"> 
<input id="userid" type="hidden" value="<s:property value='#session.userId'/>">
<table  id="chartTable_one"  width="1024"  height="100%" border="0" cellspacing="0" cellpadding="0">
    <tr>
	  <td width="24px" height="24px" style="background:url(./img/leftup.png)  ">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	  <td width="24px" style="background:url(./img/upborder.png)  "></td>
	  <td width="24px" height="24px" style="background:url(./img/rightup.png)  ">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	</tr>
	<tr>
	  <td width="24px" style="background:url(./img/leftborder.png) "></td>
	  <td width="100%" valign="top">	  
	  <div style="float:left;margin-left:10px;color: #105C90;font-size: 13pt; font-weight: bold;">重点营销活动列表</div>
	  <div class="line" style="width:100%;border:solid #CCC 0px;margin-top:10px; color: #CCC;height: 1px;" >&nbsp;</div>
	  <div>
	     
	  </div>
	  <div  style="margin-bottom:20px;margin-top:10px; margin-left:15px;width:100%;">
	           <div style="float:left;v-align:middle;height:25px;line-height:25px;">类型：</div>
		       <div style="float:left;margin-right:80px;">
		       <select id="slt_chnl" class="easyui-combobox"  panelHeight="auto"  style="width:180px;height:25px;">
	              <option value="core" selected>全部</option>
	              <option value="corey">新业务营销</option>
	              <option value="corel">流量偏好分析模型</option>
	              <option value="corez">终端运营应用模型</option>
	              <option value="coreg">高价值客户流失预警</option>
	              <option value="corec">沉默与低使用量</option>
	             </select>	 
	          </div>   	  
		  <div style=" float:left; padding-right:10px; border:solid #CCC 1px; height:25px;width: 415px;">
				<input id="marketname_text" type="text" value="营销活动名称"  style=" width:415px;border-width: 0px; padding-top: 1px;color: gray;" onfocus="if(value=='营销活动名称'){value='';}" onblur="if(value==''){value='营销活动名称';this.style.color='gray'; $('#serch_div').hide();}" onkeydown="{this.style.color='#000';}">
		  </div>
		  <div id="searchdiv" style="float:left;margin: 0px 0px 0px 30px;">
			   <a id="market_serch_bt" href="javascript:void(0)" hidefocus="true">搜索</a>
		  </div>
	  </div>
	  <div id="serch_div" ></div>  
	  <div style="background:url(./img/push/background.png) repeat-x ;float:left;height:28px; margin-top:0px; border-top:0px solid #CCC; border-left:0px solid #CCC;; border-right:0px solid #CCC;;width:100%;">
	  		<img alt="创建" src="./img/push/creat.png" border="0" style="margin:4px; cursor:pointer;"><a id="market_creat_bt" href="javascript:void(0)" hidefocus="true" style="color: #fff;height: 26px;text-align:center; font-size: 10pt;	line-height: 26px; border: 0px red solid; margin-bottom: 8px;text-decoration: none;">创建</a>
	  		<img alt="删除" src="./img/push/delete.png" border="0" style="margin:4px;cursor:pointer;" id ="delimg"><a id="market_del_bt" href="javascript:void(0)" hidefocus="true" style="color:#fff;height: 26px;text-align:center; font-size: 10pt;	line-height: 26px;border: 0px red solid; margin-bottom: 8px;text-decoration: none;">删除</a>
	  </div>
	  <div style="float:left;width:100%;margin-bottom:5px;margin-top:-7px;border:1px solid #CCC;height: 73%;">
	  	<div id="datagrid" style="margin:20px 20px;position: absolute; right:5px;"></div>
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
	<div id="access" style="display:none">
 
		<div id="close_div" >
			<a id="close" href="javascript:void(0)" hidefocus="true">关闭</a>
		</div>
		<div id="plan_valuate">
			<div id="container"
				style="min-width: 600px; height: 350px;  margin: 1px 20px 0px auto"></div>
			<div id="datagrid_access"  style="width:600px;"></div>
			<div id="datagrid_model"  style="width:600px;"></div>
		</div>
	</div>
	<div id="benefit_div">
	    <div style="text-align:left; float:left"><img src="./img/maintag/benefit.png" width="70" height="70"  style="margin-top:10px;"></div>
		<div style="float:right"><img src="./img/right_menu1.png" id="collapse" align="right" style=" margin-right:5px; margin-top:10px"></div>
		<div style="clear:both" />
		<!-- <div style=" font-size:16pt;margin-top:10px;text-align:left;"><img src="./img/maintag/jiantou2.png" width="11" height="14" style="margin-left:10px; margin-top:30px;">&nbsp;信息</div> -->
		<div class="taginfo_div_title">活动名称：</div>
		<div class="taginfo_div_item" id="tag_name_div">测试</div>
		<div style="clear:both"></div>
		<div class="taginfo_div_title">统计月份：</div>
		<div class="taginfo_div_item" id="tag_profile_div">标签简介</div>
		<div style="clear:both"></div>
		<div class="taginfo_div_title">赠费客户数：</div>
		<div class="taginfo_div_item" id="tag_statement_div">测试</div>
		<div style="clear:both"></div>
		<div class="taginfo_div_title" >营销成本总投入：</div>
		<div class="taginfo_div_item" id="tag_subs_div" >测试</div>
		<div style="clear:both"></div>
		<div class="taginfo_div_title">营销案发展客户数 ：</div>
		<div class="taginfo_div_item" id="tag_region_div">石家庄</div>
		<div style="clear:both"></div>
		<div class="taginfo_div_title">营销活动收入：</div>
		<div class="taginfo_div_item" id="tag_creator_div">郑声远</div>
		<div style="clear:both"></div>
		<div class="taginfo_div_title">营销活动投入产出比：</div>
		<div class="taginfo_div_item" id="tag_income_div">123</div>
		<div style="clear:both"></div>
		<div class="taginfo_div_title">人均发展成本投入：</div>
		<div class="taginfo_div_item" id="tag_chengben_div">123</div>
		<div style="clear:both"></div>
		<div class="taginfo_div_title">离网客户数：</div>
		<div class="taginfo_div_item" id="tag_netcount_div">123</div>
		<div style="clear:both"></div>
		<div class="taginfo_div_title">离网率：</div>
		<div class="taginfo_div_item" id="tag_net_div">123</div>
		<div style="clear:both"></div>
		<div style="clear:both"></div>
	</div>
</body>
<script type="text/javascript">
  var columns0 = "";
  var columns1 = [[
						{field : 'A', title : '统计时间', align : 'center',width :100},
						{field : 'B', title : '地域', align : 'center',width :100},
						{field : 'C', title : '包月沉默客户占比', align : 'center',width : 130},
						{field : 'D', title : '低使用量客户占比', align : 'center',width : 130},
						{field : 'E', title : '活跃率', align : 'center',width : 130}
					]];
	var columns2 = [[
						{field : 'A', title : '统计时间', align : 'center',width :100},
						{field : 'B', title : '地域', align : 'center',width :100},
						{field : 'C', title : '高价值客户离网率', align : 'center',width : 200},
						{field : 'D', title : '高价值净增客户数', align : 'center',width : 200}
					]];
	var columns3 = [[
						{field : 'A', title : '统计时间', align : 'center',width :100},
						{field : 'B', title : '地域', align : 'center',width :100},
						{field : 'C', title : '推荐成功率', align : 'center',width : 200},
						{field : 'D', title : '竞品业务反挖规模', align : 'center',width : 200}
					]];
	var columns4 = [[
						{field : 'A', title : '统计时间', align : 'center',width : 70},
						{field : 'B', title : '地域', align : 'center',width : 80},
						{field : 'C', title : '接触总量', align : 'center',width : 80},
						{field : 'D', title : '响应量', align : 'center',width : 70},
						{field : 'E', title : '响应率', align : 'center',width : 70},
						{field : 'F', title : '业务类型', align : 'center',width :100},
						{field : 'G', title : '使用时机', align : 'center',width :100},
						{field : 'H', title : '位置', align : 'center',width :100}
					]];
	var columns5 = [[
						{field : 'A', title : '统计时间', align : 'center',width :100},
						{field : 'B', title : '地域', align : 'center',width :100},
						{field : 'C', title : '接触总量', align : 'center',width :100},
						{field : 'D', title : '响应量', align : 'center',width :100},
						{field : 'E', title : '转化率', align : 'center',width :100},
						{field : 'F', title : '渠道', align : 'center',width :100}
					]];
	var columns6 = [[
						{field : 'A', title : '统计时间', align : 'center',width :100},
						{field : 'B', title : '地域', align : 'center',width :100},
						{field : 'C', title : '换机成功率', align : 'center',width : 200},
						{field : 'D', title : '户均流量提升率', align : 'center',width : 200}
					]];
	var columns7 = [[
						{field : 'A', title : '统计时间', align : 'center',width :100},
						{field : 'B', title : '地域', align : 'center',width :100},
						{field : 'C', title : '换机成功率', align : 'center',width : 200},
						{field : 'D', title : '户均流量提升率', align : 'center',width : 200}
					]];
	var columns8 = [[
						{field : 'A', title : '统计时间', align : 'center',width : 80},
						{field : 'B', title : '地域', align : 'center',width : 70},
						{field : 'C', title : '零售裸机流量提升率', align : 'center',width : 80},
						{field : 'D', title : '零售裸机各类套餐营销成功率', align : 'center',width : 70},
						{field : 'E', title : '合约终端套餐融合率', align : 'center',width :100},
						{field : 'F', title : '合约终端流量提升率', align : 'center',width :100},
						{field : 'G', title : '合约终端各类套餐营销成功率', align : 'center',width :100}
					]];
	var columns9 = [[
						{field : 'A', title : '统计时间', align : 'center',width :100},
						{field : 'B', title : '地域', align : 'center',width :100},
						{field : 'C', title : '换机兴奋期用户PUSH响应率', align : 'center',width : 200},
						{field : 'D', title : '换机兴奋期用户户均流量提升率', align : 'center',width : 200}
					]];
	var columns10 = [[
						{field : 'A', title : '统计时间', align : 'center',width :100},
						{field : 'B', title : '地域', align : 'center',width:100},
						{field : 'C', title : '目标用户户均流量提升率', align : 'center',width : 200},
						{field : 'D', title : '各类套餐营销成功率', align : 'center',width : 200}
					]];
	var columns11 = [[
						{field : 'A', title : '统计时间', align : 'center',width:100},
						{field : 'B', title : '地域', align : 'center',width:100},
						{field : 'C', title : 'TD流量提升率', align : 'center',width:200},
						{field : 'D', title : 'TD分流效果', align : 'center',width:200}
					]];
$(function(){
	var bodywidth=$(document.body).width();	
	var bodylength=$(document.body).height();
	
	/* var columns12 = [[
						{field : 'A', title : '统计时间', align : 'center'},
						{field : 'B', title : '地域', align : 'center'},
						{field : 'C', title : '渠道触点接触客户数', align : 'center'},
						{field : 'D', title : '成功办理客户数', align : 'center'},
						{field : 'E', title : '成功办理客户数占比', align : 'center'},
						{field : 'F', title : '渠道成功率', align : 'center'}
					]];
	var columns13 = [[
						{field : 'A', title : '统计时间', align : 'center'},
						{field : 'B', title : '地域', align : 'center'},
						{field : 'C', title : '赠费客户数', align : 'center'},
						{field : 'D', title : '营销成本总投入', align : 'center'},
						{field : 'E', title : '营销案发展客户数', align : 'center'},
						{field : 'F', title : '营销活动收入', align : 'center'},
						{field : 'G', title : '营销活动投入产出比', align : 'center'},
						{field : 'H', title : '人均发展成本投入', align : 'center'},
						{field : 'I', title : '离网客户数', align : 'center'}
					]]; */
	 $("#datagrid").datagrid({
	   				       title : "",
	   				       url:"getCoreMarkPlanInfoList.action",
	   				       queryParams:{type:"core"},
					       pagination:true,
					       fit:true,
					       width:bodywidth,
					       rownumbers:true,
		       			   //fitColumns:true,
		       			   singleSelect:true,			   
		       			   border:false,
		       			   frozenColumns:[[{
		       			   	field : 'ck',checkbox:true
		       			   }]],
					       columns : [[{
									field : 'plan_id',
									title : '活动Id', 
									width:bodywidth*0.17,
									align : 'center',
									hidden:true
								}, {
									field : 'plan_name',
									title : '活动名称', 
									width:bodywidth*0.1,
									align : 'left'
								}, {
									field : 'creator_id',
									title : '创建人id',  
									width:bodywidth*0.05,
									align : 'left',
									hidden:true
								}, {
									field : 'creator_name',
									title : '创建人',  
									width:bodywidth*0.06,
									align : 'left'
								}, {
									field : 'plan_region_name',
									title : '归属地',  
									width:bodywidth*0.03,
									align : 'left'
								},{
									field : 'push_time', 
									title : '创建时间',  
									width: bodywidth*0.05,
									align : 'center'
								},{
									field : 'start_time',
									title : '开始时间', 
									width:bodywidth*0.05,
									align : 'center'
								},{
									field : 'end_time',
									title : '结束时间',  
									width:bodywidth*0.05,
									align : 'center'
								},{
									field : 'cust_type', 
									title : '目标客户类型',  
									width:bodywidth*0.05,
									align : 'left'
								},{
									field : 'plan_model', 
									title : '重点活动类型',  
									width:bodywidth*0.08,
									align : 'left'
								},{
									field : 'paln_state', 
									title : '活动状态',  
									width:bodywidth*0.05,
									align : 'left'
								},{
									field : 'push_chanl_id', 
									title : '推送渠道',  
									width:bodywidth*0.05,
									align : 'left'
								},{
									field : 'operate', 
									title : '操作',  
									width :bodywidth*0.03,
									align : 'center'
								},{
								    field : 'model_operate', 
									title : '评估',  
									width :bodywidth*0.03,
									align : 'center'
								}]],								
								onLoadSuccess:function(){
								   $("#wait_div").hide();  
								},
								onDblClickRow:function(rowIndex, rowData){
								   //alert(rowData.plan_id);
								}
	}); 
	
	var p = $('#datagrid').datagrid('getPager');
	$(p).pagination({
		pageSize : 10, 
		pageList : [10,20,30,40,50 ]  				
	});
	
	$("#slt_chnl").combobox({
	    onSelect:function(newValue){
	      $("#datagrid").datagrid('load', {   
		    type: $("#slt_chnl").combobox("getValue") 
		});  
	    }
	});
	
	//属性查询事件
	var oldtext = "";
	$("#marketname_text").keyup(function() {
		if ($(this).val() != oldtext) { 
			var planName = encodeURI($("#marketname_text").val());  
			$.markt_panel_tishi(planName);
		}
		
		if($("#marketname_text").val()==""){$("#serch_div").hide();}
	});

 

	$("#market_serch_bt").click(function() {
		   document.getElementById("wait_div").style.filter="alpha(opacity=20)";
 		$("#wait_div").show(); 
		var planName = encodeURI($("#marketname_text").val());//二级属性类
		
		if($("#marketname_text").val()=='营销活动名称'){planName="";}
		 
		$("#serch_div").hide();
		
		$('#datagrid').datagrid('load', {   
		    type: 'core',   
		    planName: planName  
		});  
		
		 
	}); 
	



    
   $.extend({"markt_panel_tishi":function(planName){	
	  $.ajax({
    		type: 'POST',
    		url: 'getCorePlanNameList.action',
    		data:{userId:'jyyr_999',planName:planName},
    		dataType:'json', 
    		success: function(data){ 
		    var table_str=""; 		    
			$("#serch_div").children().remove();			
			$.each(data,function(i,table){				 		
				table_str=table_str+"<div class='markt_tishi_div' onClick='$.markt_tishi(this)' onMouseover='$.markt_tishi_over(this)' onMouseOut='$.markt_tishi_out(this)'  >"+table.plan_name+"</div> ";  
			});	

			$("#serch_div").html(table_str);
			var offset=$("#marketname_text").offset();
			//alert(offset.left);
			$("#serch_div").css({
			       "display":"block",
			       "left":(parseInt(offset.left)-1)+"px"
			       });
			//$("#serch_div").focus();
			

            },
   			error: function(data){
            alert(data);
     		return false;
    		}
    	});			
		
	}
	}); 
	$.extend({'markt_tishi' : function(evet) {
			 
			$("#marketname_text").val($(evet).text()); 
			$("#serch_div").hide();

		}
	}); 
	$.extend({'markt_tishi_over' : function(evet) {
			$(evet).css('color', '#06F');
		}
	});

	$.extend({
		'markt_tishi_out' : function(evet) {
			$(evet).css('color', '#666');
		}
	});
	var type="";
	$("#serch_div").mouseover(function(){
	   type=1;
	});
	$("#serch_div").mouseout(function(){
	   type=0;
	});
	
	$("#marketname_text").blur(function () { 
	 if(type==0){
	 $("#serch_div").hide(); 
	 }
	 });
	 $("#close").click(function(){
	   $("#datagrid_access").datagrid('deleteRow',0);
	   $("#datagrid_model").datagrid('deleteRow',0);
	   $("#access").css("display","none");
	   $("#load_div").hide();
	  
	 });
  
  	$("#delimg").click(function(){window.$("#market_del_bt").click();});
    $("#market_del_bt").click(function() {
    		var selectRow = $("#datagrid").datagrid('getSelections');//获取选中行
    		//alert(selectRow[0].plan_id);
    		 
    		var userId=$("#userid").val();
    		
    		if(selectRow!=null&&selectRow.length!=0){ 
    		        var selectuserId=selectRow[0].creator_id;
    		        if(userId!=selectuserId){    		        	
    					alert("对不起，您不能删除他人的活动!");
    				}else{
						if (confirm('您确认删除这条记录么?')) {
							for(var i=0;i<selectRow.length;i++){ 
								var planId = selectRow[i].plan_id;
								var planStat=selectRow[i].paln_state;
								if(planStat=='活动创建'||planStat=='目标客户生成'){
						            $.ajax({
								            url : "delMarkt.action?planId=" + planId,
											type : "post",
											dataType : 'text',
											success : function(data) {  
												var a=eval(data);
												var result= a[0].delresult;
												if(result=="删除成功！"){}
												else{alert(result);}												 
												$('#datagrid').datagrid('reload'); 
											}
									});
								}else{
									alert("活动已推送,不能删除！");
								}
							}	
						} 
					} 
			}else{
				alert('请先选择要删除的活动！');
			}
		}
	);
	
   $("#market_creat_bt").click(function(){
   
   window.location.href="coreplan.action";
   
   }); 

}); 

	function PlanAccess(){
	      
	     
	     var bodywidth=$(document.body).width();	
	     var selectRow = $("#datagrid").datagrid('getSelected');//获取选中行
         var state = selectRow.paln_state;
         var plan_name=selectRow.plan_name;
         var plan_id = selectRow.plan_id;
         var region_name=selectRow.plan_region_name;
         var data="";
         columns0="";
         if(plan_id.substring(4,9)=="ccmd1"){
            columns0=columns1;
         }else if(plan_id.substring(4,9)=="ggjl1"){
            columns0=columns2;
         }else if(plan_id.substring(4,9)=="lllp0"){
            columns0=columns3;
         }else if(plan_id.substring(4,9)=="ysjxz"){
            columns0=columns4;
         }else if(plan_id.substring(4,9)=="yqdpg"){
            columns0=columns5;
         }else if(plan_id.substring(4,9)=="z2ghj"){
            columns0=columns6;
         }else if(plan_id.substring(4,9)=="zywhy"){
            columns0=columns7;
         }else if(plan_id.substring(4,9)=="zllrh"){
            columns0=columns8;
         }else if(plan_id.substring(4,9)=="zhjxf"){
            columns0=columns9;
         }else if(plan_id.substring(4,9)=="zllts"){
            columns0=columns10;
         }else if(plan_id.substring(4,9)=="ztwqy"){
            columns0=columns11;
         }
        // alert(columns0+"");
         if(state=='活动评估'){  
	 	  $.ajax({
            type: "POST",
    		url: "chart.action?plan_id="+plan_id,
    		dataType:"text", 
    		success: function(data){ 
    		 data=eval(data);
  		var plancount=data[0].totalcount;
    		if(plancount==0||data==null){
    		  alert("暂无反馈活动效果。");
    		}else{
    		// $("#load_div").show();
        $("#access").css({
                 "left"  :(parseInt(bodywidth)-500)/2+"px",
                 "display" : "block"
            });
          $("#plan_valuate").css({
                 "left"  :(parseInt(bodywidth)-500)/2+"px",
                 "display" : "block"
            }); 
          $("#close_div").css({
                 "left":(parseInt(bodywidth)-500)/2+660+"px"
                 }); 
  var chart;
 $(document).ready(function() {
        chart = new Highcharts.Chart({
            chart: {
                renderTo: 'container',
                zoomType: 'xy'
            },
            title: {
                text: '<b>'+plan_name+'活动效果'+'</b>'
            },
             xAxis: [{//mon
		                categories: ['同意办理', '拒绝办理', '考虑办理', '无法办理', '已办理', '其它'
                   ]
		            }],
		     yAxis: [ { // Secondary yAxis
                title: {
                    text: '指标值',
                    style: {
                        color: '#4572A7'
                    }
                },
                labels: {
                    formatter: function() {
                        return this.value +' 户';
                    },
                    style: {
                        color: '#4572A7'
                    }
                }
                
            },{ // Primary yAxis
                labels: {
                    formatter: function() {
                        return this.value +'%';
                    },
                    style: {
                        color: '#910000'
                    }
                },
                title: {
                    text: '占比',
                    style: {
                        color: '#910000'
                    }
                },
                opposite: true
            }],
            tooltip: {
                formatter: function() {
                if(this.series.name == '户'){
                    return ''+
                        this.x +': '+ this.y  +' 户';
                }else{
                   return ''+
                        this.x +': '+ Highcharts.numberFormat(this.y,5)  +'%';
                }}
            },
            legend: {
                layout: 'vertical',
                align: 'left',
                x: 500,
                verticalAlign: 'top',
                y: 50,
                floating: true,
                backgroundColor: '#FFFFFF'
            },
            series: [{
                name: '户',
                color: '#4572A7',
                type: 'column',             
                data: data[0].count
    
            },{
                name: '占比',
                color: '#910000',
                 yAxis: 1,
                type: 'spline',
                data: data[0].cnt
            } ]
        });
        });
         $("#load_div").hide();
       // alert(data);
       // alert(data[0].count[0]);
        var myDate = new Date();
	       var year = myDate.getFullYear();
		   var mon = myDate.getMonth()+1;
		   var day = myDate.getDate()-1;
		   var value = year+""+(mon<10?('0'+mon):mon)+""+(day<10?('0'+day):day);
		   
     $("#datagrid_access").datagrid({
	   				       title : "",
	   				      // url:"getplanaccess.action?plan_id="+plan_id, 
	   				       height:60,
	   				       width:700,
		       			   fitColumns:true, 
					       columns : [[{
									field : 'date_stamp',
									title : '统计日期', 
									width :  120,
									align : 'center'
								}, {
									field : 'plan_name',
									title : '活动名称', 
									width :  180,
									align : 'center'
								} ,{
									field : 'push',
									title : '推送人数',  
									width :  120,
									align : 'center'
								},{
									field : 'feel',
									title : '接触人数',  
									width :  120,
									align : 'center'
								},{
									field : 'agree',
									title : '同意办理',  
									width :  120,
									align : 'center'
								},{
									field : 'reject',
									title : '拒绝办理', 
									width :  120,
									align : 'center'
								},{
									field : 'think',
									title : '考虑办理',  
									width :  120,
									align : 'center'
								},{
									field : 'enable',
									title : '无法办理',  
									width :  120,
									align : 'center'
								},{
									field : 'already',
									title : '已办理',  
									width :  120,
									align : 'center'
								},{
									field : 'elsec',
									title : '其它',  
									width :  50,
									align : 'center'
								}]]								
								
	});
	
	$("#datagrid_model").datagrid({
	   				       title : "",
	   				       url:"getcoreplanaccess.action?plan_id="+plan_id+"&region="+encodeURI(encodeURI(region_name)), 
	   				       height:60,
	   				       width:700,
	   				       fit:true,
		       			   fitColumns:true, 
					       columns : columns0							
								
	});
	
	$("#datagrid_access").datagrid('insertRow',{
	index: 0,	// index start with 0
	row: {
		date_stamp: value,
		plan_name: plan_name,
		push: data[0].plancount,
		feel:data[0].totalcount,
		agree:data[0].count[0],
		reject:data[0].count[1],
		think:data[0].count[2],
		enable:data[0].count[3],
		already:data[0].count[4],
		elsec:data[0].count[5]
	}
	});  
        } 
            }
   }); 
	 }else{
         alert("活动正在推送,暂无反馈效果。");
         } 
	}
	
	function PlanModelAccess(){
	     var selectRow = $("#datagrid").datagrid('getSelected');//获取选中行
         var plan_id = selectRow.plan_id;
         var plan_name= selectRow.plan_name; 
	      $.ajax({
            type: "POST",
    		url: "getplanbenefit.action?plan_id="+plan_id,
    		dataType:"text", 
    		success: function(data){
    		   data=eval(data); 
    		   //alert(data);
    		   if(data==""|| data==null){
    		   alert("效益评估数据暂未生成。");
    		   }else{
    		  $("#tag_name_div").text(plan_name);
    		  $("#tag_profile_div").text(data[0].A);
    		  $("#tag_statement_div").text(data[0].B+"户");
    		  $("#tag_subs_div").text(data[0].C+"元");
    		  $("#tag_region_div").text(data[0].D+"户");
    		  $("#tag_creator_div").text("暂无");  //data[0].E+"元"
    		  $("#tag_income_div").text("暂无");   //data[0].F
    		  $("#tag_chengben_div").text(data[0].G+"元");
    		  $("#tag_netcount_div").text(data[0].H+"户");
    		  $("#tag_net_div").text(data[0].I);
    		    $("#benefit_div").animate({right:"0px"},500);
    		  }
    		  },
			error: function(data){	
			    return false;
			  }
		  });	
	
	
	
	};
	 $("#collapse").click(
	    function(){
	$("#benefit_div").animate({right:"-250px"},500);
			      });

</script>
</html>