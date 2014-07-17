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
   <div style="float:left;margin-top:10px;margin-bottom:10px">下载原因列表：</div>
     <div class="line1" style="width:100%;border:solid #5ab4f9 1px;" >&nbsp;</div>
     <!------------------------ 内容table---------------------------------------- -->
  <table id="taginfo_table" width="100%" border="0" cellspacing="0" cellpadding="5">
  <tr>
    <td style="color:#030;" width="20%">标签名称</td>
    <td style="color:#030;" width="15%">客户名称</td>
    <td style="color:#030;" width="10%">地区</td>
    <td style="color:#030;" width="10%">下载时间</td>
    <td style="color:#030;" width="14%">下载原因</td>  
  </tr>
  <s:iterator  value="downReasonmList"  >  	     	
 	     <tr>
 	        <td style="color:#666;"><s:property value="tagName" /></td>
    		<td style="color:#666" width="15%"><s:property value="userName" /></td>
    		<td style="color:#666" width="10%"><s:property value="regionName" /></td>
    		<td style="color:#666" width="10%"><s:property value="createTime" /></td>
    		<td style="color:#666;overflow:hidden;" width="10%" title="<s:property value='downReason' />"><s:property value="downReason" /></td>    	 
  		</tr>  	    	    	 
</s:iterator> 
       <tr>
       		<td colspan="8" align="center">
             <s:if test="%{pageNo==1}">
			 第一页&nbsp;  
			 上一页&nbsp;
			 </s:if>
			 <s:else>
			 <a href="javaScript:void(0)"  id="first" >第一页</a>&nbsp;
			  <a href="javaScript:void(0)"  id="front" >上一页</a>&nbsp;
			 </s:else>
			<s:if test="%{pageNo>=pageCounts}"> 
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
			
	if(num!=0){
			  $("#to_plan").css("display","block");
			}else{
			  $(".to_plan").css("display","none");
			}
			
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
var hrefFy="listReason.action?tagId=<s:property value='tagId'/>";
$("#first").click(function(){
	 var hrefFyf=hrefFy+"&pageNo=1";
	 $("#first").attr("href",hrefFyf);
});
$("#front").click(function(){
	 var hrefFye=hrefFy+"&pageNo=<s:property value='fontPage'/>";
	 $("#front").attr("href",hrefFye);
});
$("#next").click(function(){
	 var hrefFyt=hrefFy+"&pageNo=<s:property value='nextPage'/>";
	 $("#next").attr("href",hrefFyt);
});
$("#tail").click(function(){
	 var hrefFyfo=hrefFy+"&pageNo=<s:property value='pageCounts'/>";
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
