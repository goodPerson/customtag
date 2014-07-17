<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="struts" uri="/struts-tags" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'DownloadInfo.jsp' starting page</title>
   <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link rel="stylesheet" type="text/css" href="css/themes/gray/easyui.css">  
    <link rel="stylesheet" type="text/css" href="css/themes/icon.css">  
	<script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="js/jquery-1.8.0.min.js"></script>
	<script type="text/javascript" src="js/jquery.easyui.min.js"></script>  
	<script type="text/javascript" src="js/easyui-lang-zh_CN.js"></script> 

  </head>
  <style>
  * {
  font-family: "微软雅黑";
  font-size: 10pt;
  }
  html {
  height: 100%;
  overflow-x: auto;
  overflow-y: auto;
  }
  body {
  height: 100%;
  width: 1024px;
  font: 10pt "微软雅黑";
  margin: 0px auto;
  background:#FFF;
  overflow-x: hidden;
  overflow-y: auto;
  text-align:center;
  }
	.line {
	height: 1px;
	font-size: 0px;
	background: #E8E8E8;
	line-height: 1px;
	float: left;
}
#taginfo_table tr{
    cursor:pointer;
   }
a {
     font-size:10pt;
     font-family:"微软雅黑";
     font-weight: 550;
     text-decoration : none;
                 }
 .line1{
	height: 0px;
	font-size: 0px;
	background: #E8E8E8;
	line-height: 0px;
	float: left;	
}
.downloadreason{
	width:auto;
	height:25px;
	line-height:25px;
	overflow:hidden;
	}
  #wait_div{
  margin:0;
  width: 100%;
  height:100%;
  position: absolute;
  top:0px;
  left:0px;
  display:none;
  background:#666; 
  filter:alpha(opacity:80);
  z-index:2;
  opacity:0.8; 
  }


  #wait_div1 {
  margin:0;
  width: 400px;
  height:300px;
  position: absolute;
  top:100px;
  left:550px;
  display:none;
  background:#FFF;   
  z-index:3;
  }
  
  #treasury{
  background:#094AB2;
  height:30px;
  color:#FFF; 
  padding-left:20px;
  text-align:left;
  line-height:30px;
  }
  .buttons{
  display: block;
  background: #03F;
  width: 80px;
  height: 28px;
  text-align: center;
  font-size: 10pt;
  line-height: 28px;
  color:#FFFFFF;
  }

  </style>
  <body>
 <!-- <div id="wait_div" class="wait_div" >-->
 <div id="wait_div"></div>
 <div id="wait_div1">
 	<div id="treasury">金库短信验证</div>
 	<div style="text-align:left; padding-left:20px; margin-top:5px;">协同人：</br>
		<div style="padding-left:20px;">
			<input type="radio" name="joniuser" value=""/> 张三</br>
			<input type="radio" name="joniuser"   value=""/> 李四</br>
			<input type="radio" name="joniuser"   value=""/> 王五</br>
			<input type="radio" name="joniuser"   value=""/> 赵六</br>
			<input type="radio" name="joniuser"   value=""/> 李奇</br>
			<div id="div_dowreason" style="margin-top:30px;">
			下载原因：<input type="text" name="treasuny_downreason" size="20"/>
			</div>
			<div id="veri_code" style="margin-top:30px;">
			验证码：<input type="text" name="treasuny_pin" size="10"/>
			</div>
			<div style="margin-top:30px; text-align:center;">
			<div id="submits" class="buttons" >提交</div>
			<div id="sure" class="buttons" >确定</div>
			
			</div>
		</div>
 			
 	</div>
 </div>

  <div>
   <input type="hidden" id="regionName" value="<s:property value='#session.regionName'/>"  >
   <table  id="chartTable_one"  width="1024"  height="100%" border="0" cellspacing="0" cellpadding="0">
	<tr>
	  <td width="24px" height="24px" style="background:url(./img/leftup.png)  ">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	  <td width="24px" style="background:url(./img/upborder.png)  "></td>
	  <td width="24px" height="24px" style="background:url(./img/rightup.png)  ">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	</tr>
	<tr>
	  <td  width="24px" style="background:url(./img/leftborder.png) "></td>

	  <td width="100%" valign="top">
	  <!--主要内容开始-->
   <div style="float:left;margin-top:10px;margin-bottom:10px">下载列表：</div>
     <div class="line1" style="width:100%;border:solid #5ab4f9 1px;" >&nbsp;</div>
     <!------------------------ 内容table---------------------------------------- -->
  <table id="taginfo_table" width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr>
    <td style="color:#030;" width="20%">文件名称</td>
    <td style="color:#030;" width="15%">创建时间</td>
    <td style="color:#030;" width="10%">下载类型</td>
    <td style="color:#030;" width="10%">人数</td>
    <td style="color:#030;" width="14%">目标客户归属</td>
 <!-- <td style="color:#030;" width="10%">状态</td> -->
    <td style="color:#030;" width="10%">操作人</td>
    <td style="color:#030;" width="10%">下载原因</td>
    <td style="color:#030;" width="10%"  align="center">操作</td>    
  </tr>
  <s:iterator  value="listDown"   var="DownloadInfo">  	     	
 	     <tr>
 	        <td style="color:#666;display:none;"><s:property value="id" /></td>
    		<td style="color:#666" width="15%"><s:property value="file_name" /></td>
    		<td style="color:#666" width="10%"><s:property value="%{create_time.substring(0,4)}" />年<s:property value="%{create_time.substring(4,6)}" />月<s:property value="%{create_time.substring(6,8)}" />日</td>
    		<td style="color:#666" width="10%"><s:property value="file_type" /></td>
    		<td style="color:#666" width="10%"><s:property value="count" />人</td>
    		<td style="color:#666;" width="10%"><s:property value="region_name" /></td>
   <!--  	<td style="color:#666;" width="10%" ><s:property value="status" /></td> -->
    		<td style="color:#666" width="10%"> <s:property value="manager" /></td>
    		<%--<td style="color:#666" width="10%"><div class="downloadreason" title="<s:property value='downLoadReason'/>"><s:property value='downLoadReason'/></div></td> 
    		 --%><td style="color:#666" width="10%"><div class="downloadreason" >
    		<s:if test="%{#DownloadInfo['custlist_path']!='none'}" >
    		 <a href="listReason.action?tagId=<s:property value='id'/>">查看下载原因</a> 
    		</s:if>
    		</div></td>
    		 <s:if test="%{#DownloadInfo['custlist_path']=='none'}" >
   			 <!--<td style="color:#666 ">文件生成中</td>-->
   			<td align="center"> <div><img src="./img/load/load.jpg" width="20" height="20"   align="absmiddle" /><!-- <span class="downFile">下——载</span> --></div>   </td> 
            </s:if>
            <s:else>
               			<td  style="color:#666 " align="center">        
						
               			
               			<a href="requestDownReason.action?userId=<s:property value='manager_id'/>&createTime=<s:property value='create_time'/>&downPath=<s:property value='custlist_path'/>" >下载</a> 
               			<!--<a  href="http://www.hebmcbass.com/customTag/views/loadFile.jsp?url=/app1/file/khbq/<s:property value='custlist_path'/>" >下载</a> -->
               			<a href="delShow.action?createTime=<s:property value='create_time'/>"> 删除</a>
               			 <span class="to_plan"><a   href="<%=basePath%>entryplan_create.action?filename=<s:property value='file_name'/>&id=<s:property value='id'/>&filetype=<s:property value='file_type'/>" id="myplan"> 活动</a></span>
               			</td>
            </s:else>
    	 
  		</tr>  	    	    	 
</s:iterator> 
       <tr>
       		<td colspan="8" align="center">
			<input id="page1" type="hidden" value="1"/>
			<input id="page2" type="hidden" value="<s:property value='pageNo-1'/>"/>
			<input id="page3" type="hidden" value="<s:property value='pageNo+1'/>"/>
			<input id="page4" type="hidden" value="<s:property value='pageCounts'/>"/>
             <s:if test="%{pageNo==1}">
			 第一页&nbsp;  
			 上一页&nbsp;
			 </s:if>
			 <s:else>
			 <a href="javaScript:void(0)"  id="first" >第一页</a>&nbsp;
			  <a href="javaScript:void(0)"  id="front" >上一页</a>&nbsp;
			 </s:else>
			<s:if test="%{pageNo==pageCounts}"> 
			  下一页 &nbsp;
			  尾页 &nbsp;
			 </s:if>  
			 <s:else>
			  <a href="javaScript:void(0)"  id="next" >下一页</a>&nbsp;
			  <a href="javaScript:void(0)"  id="tail" >尾页</a>&nbsp;
			 </s:else>
			  当前 <s:property value="pageNo"/> 页 ， 共 <s:property value="totalRecords"/> 条记录 ，共计 <s:property value="pageCounts"/> 页
          </td>
       </tr>
  </table>

  	  <!--主要内容结束-->
	  </td>
	 <td  width="24px" style="background:url(./img/rightborder.png) "></td>
	</tr>
	<tr>
	  <td width="24px" height="24px" style="background:url(./img/leftdown.png)   ">&nbsp;&nbsp;&nbsp;&nbsp;</td>
	  <td   height="24px"   style="background:url(./img/downborder.png)   "></td>
	  <td width="24px" height="24px"  style="background:url(./img/rightdown.png)   ">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
    </tr>
  </table> 
   </div>
  <script type="text/javascript">
  $(function(){
/*	  
 for (i=1;i<$("#taginfo_table tr").length;i++){
	
     if($("#taginfo_table tr:eq("+i+") td:eq(6)").text()!="文件生成中"){
      var href=$("#taginfo_table tr:eq("+i+") td:eq(6)").find("a").attr("href");
     // alert(href);
		      if (typeof(href)!="undefined"){
		    	    //    alert("ok");
					      $.ajax({
					       url: href,
					      type: 'POST',
					      complete: function(response) {
						       if(response.status == 200) {
									    //alert('有效');
						          } else {
									     $(".href").css("display","none");
									     $(".img").css("display","block");
									     // $("#taginfo_table tr:eq("+i+") td:eq(6)").text("文件生成中");
									     //alert('无效');
						  		  }
					  		}
					       });   	  
		      }

      }
  }; 
  */
  var region=$("#regionName").val();
			//alert(region);
			var num=0;
			var a=["河北省","保定","沧州","承德","邯郸","衡水","廊坊","秦皇岛","石家庄","唐山","邢台","张家口"];
			 for (var i=0;i<a.length;i++){
			   if(a[i]==region){
			     num=num+1;
			   }else{
			     num=num;
			   }
			};
			
	/* if(num!=0){
			  $("#to_plan").css("display","block");
			}else{
			  $(".to_plan").css("display","none");
			} */
			
   $("#taginfo_table tr:gt(0)").mouseenter(
	function(){	
        var index =($("#taginfo_table tr").index(this));
         // alert(str); //此处是选择的li 标签下的选中的 checkbox 
           if(!$(this).find("input").attr("checked")) {
              $(this).css("background","#e7e7e7");
              }        
		});
$("#taginfo_table tr:gt(0)").mouseleave(
	function(){
	    var index =($("#taginfo_table tr").index(this));     
	   if(!$(this).find("input").attr("checked")){	
		$(this).css("background","");
		}
		});
$("#taginfo_table tr:eq(1)").css("background","#e7e7e7");	

var page1                      =$("#page1").val();
var page2                      =$("#page2").val();
var page3                      =$("#page3").val();
var page4                      =$("#page4").val();
var hrefFy="querydown.action?userId=<s:property value='#session.userId'/>";
$("#first").click(function(){
	 var hrefFyf=hrefFy+"&pageNo="+page1;
	 $("#first").attr("href",hrefFyf);
});
$("#front").click(function(){
	 var hrefFye=hrefFy+"&pageNo="+page2;
	 $("#front").attr("href",hrefFye);
});
$("#next").click(function(){
	 var hrefFyt=hrefFy+"&pageNo="+page3;
	 $("#next").attr("href",hrefFyt);
});
$("#tail").click(function(){
	 var hrefFyfo=hrefFy+"&pageNo="+page4;
	 $("#tail").attr("href",hrefFyfo);
}); 
$(".downFile").click(function(){
	//alert(screen.width);
	document.getElementById("wait_div").style.display="block";
	document.getElementById("wait_div").style.filter="alpha(opacity=20)";
	//$("#wait_div").css("width",screen.width);
	//$("#wait_div1").css("width",screen.width);
	
	$("#wait_div").show();
	$("#wait_div1").show();
	treasuryRequest();
	
});
var treasure_flag="";  //短信验证标志
if (treasure_flag==""){
	$("#sure").css("display","none");
	$("#veri_code").css("display","none");	
}
$("#submits").click(function(){
	$("#sure").css("display","block");
	$("#veri_code").css("display","block");	
	$("#submits").css("display","none");
	$("#div_dowreason").css("display","none");	
	
});

});	

	function treasuryRequest(){
			var tName='客户标签数据下载';
			tName = encodeURI(tName);			  
					$.ajax({
						url:'<%=request.getContextPath()%>/treasuryRequest.action',
						type:'post',
						data:{
							busCode:'117000',
							sceneId:'140000',//场景ID
							applyMaster:'zhangruilong-T300007725',//主账号4A
							tName:tName
						},
						dataType:'json',
						success:function(jsonObj){
							alert("成功");
						},
						error:function(){
								
						}
					}); 
			
		}
  </script>
  </body>
  
</html>
