<% /**�����·�չͨ����������û���ϸ*/%>
<%@ page language="java" import="java.util.*" pageEncoding="GBK" %>
<%@ page import="com.miapsoft.viewquery.pojo.UserInfo" %>
<%@include file="/WEB-INF/content/common/tag.jsp" %>
<%UserInfo userInfo = (UserInfo)session.getAttribute("userInfo"); %>
<html>
  <head>
  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/jquery.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/themes/liferay/classic/css/main.css" />
 	
  	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js//grid/gt_grid.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/grid/skin/vista/skinstyle.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/grid/skin/pink/skinstyle.css" />
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/grid/skin/mac/skinstyle.css" />
  	<script type="text/javascript" src="<%=request.getContextPath() %>/js/grid/gt_msg_en.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/grid/gt_grid_all.js"></script> 
	<script type="text/javascript" src="<c:url value="/js/meizzDate.js"/>"></script>
			<style  type="text/css">
			table {
				border:solid 1px #97A6BD; 
			}
			table tr td{
				border:solid 1px #97A6BD; 
				text-align:center;
			}
			.mybutton-cls { 
        		background : url(<%=request.getContextPath()%>/js/grid/skin/default/images/tool_xls.gif) no-repeat center center; 
			}
		</style>
			
	<script type="text/javascript">
		    
		var loadurl = "initQhdRptChnlCall5BelowSubsDetlD.action?statisDate=${statisDate}&regionId=${regionId}";
		var exporturl = "exportQhdRptChnlCall5BelowSubsDetlD.xls?statisDate=${statisDate}&regionId=${regionId}";
		var grid_demo_id = "myGrid1" ;

		var dsOption = {
				
				fields : [
						{name:'statisDate'			, type:'string'		, index:'statisDate'		},
						{name:'regionName'			, type:'string'		, index:'regionName'		},
						{name:'phoneNo'				, type:'string'		, index:'phoneNo'		},
						{name:'checkinTime'			, type:'string'		, index:'checkinTime'		},
						{name:'checkinChnlId'		, type:'string'		, index:'checkinChnlId'		},
						{name:'checkinChnlName'		, type:'string'		, index:'checkinChnlName'		},
						{name:'checkinChnlType'		, type:'string'		, index:'checkinChnlType'		},
						{name:'monExpCurDayCommCnt'	, type:'string'		, index:'monExpCurDayCommCnt'		}
				]
		};

		var colsOption = [
						{name:'statisDate'			, header:'statisDate'			, fieldName:'statisDate'			,width:'80',sortable:false},
						{name:'regionName'			, header:'regionName'			, fieldName:'regionName'			,width:'60',sortable:false},
						{name:'phoneNo'				, header:'phoneNo'				, fieldName:'phoneNo'			,width:'90',sortable:false},
						{name:'checkinTime'			, header:'checkinTime'			, fieldName:'checkinTime'			,width:'80',sortable:false},
						{name:'checkinChnlId'		, header:'checkinChnlId'		, fieldName:'checkinChnlId'			,width:'90',sortable:false},
						{name:'checkinChnlName'		, header:'checkinChnlName'		, fieldName:'checkinChnlName'			,width:'160',sortable:false},
						{name:'checkinChnlType'		, header:'checkinChnlType'		, fieldName:'checkinChnlType'			,width:'100',sortable:false},
						{name:'monExpCurDayCommCnt'	, header:'monExpCurDayCommCnt'	, fieldName:'monExpCurDayCommCnt'			,width:'140',sortable:false}
						];
		Sigma.ToolFactroy.register(
	   		    'mybutton',  
	   		    {
	   		        cls : 'mybutton-cls',  
	   		        toolTip : '����',
	   		        action : function(event,grid) {  beforeExport();  }
	   		    }
	   		);
      	var gridOption={
      			id : grid_demo_id,
      			width: '100%', 
      			height: '100%',  	
      			container : 'gridbox', 
      			loadURL : loadurl,
      			exportURL : exporturl,
      			dataset : dsOption ,
      			columns : colsOption ,
      			pageSizeList : [10,20,50,100],
      			customHead : 'myhead',
      			skin : 'vista',
      			stripeRows : true,//�Ƿ�����ʾ����ɫ
      			remotePaging : true,
      			pageSize:20,
      			toolbarPosition : 'bottom',
      			toolbarContent : 'print | mybutton | pagesize | nav | goto | state',
      			showGridMenu : true,
      			allowCustomSkin	: false ,
      			allowGroup : true,
      			allowHide	: true,
      			allowGroup	: true
      		};
  		
		var mygrid = new Sigma.Grid( gridOption );
		Sigma.Util.onLoad( Sigma.Grid.render(mygrid) );

		function excuteQuery(){
			var regionIds = $("#regionId")[0].options;
			var regionId = "";
			var regionName = "";
			$.each(regionIds,function(){
	    		if($(this).attr("selected")==true){
	    			regionId += $(this).val()+",";
	    			regionName += $(this).attr("title")+",";
	    		}
    		});
    		if(regionId!=""){
    			regionId = regionId.substring(0,regionId.length-1);
    			regionName = regionName.substring(0,regionName.length-1);
    		}
    		
    		
			var statisDate = $("#statisDate").val();
			
    		if(statisDate=="" || null==statisDate){
				alert("��ѡ��ͳ������");
				return false;
        	}
    		
    		if(regionIds=="" || null == regionIds){
				alert("��ѡ�����");
				return false;
        	}
        	
    		return true;
	   	}

	   	function resetAll(){
			$("#statisDate").val("");
			$("select option").attr("selected",false);
		}
	</script>

  </head>
  
  <body style="text-align:center;">
  <div id="container">
  <div id="searchArean">
  	<form action="qhdRptChnlCall5BelowSubsDetlD.action" method="post" id="form1" name="form1" onsubmit="return excuteQuery();">
		<table width="99.8%">
				<tr>
					<td valign="middle" style="background-color:#DFEBF7;width:6%" align="center">
						<strong>ͳ������</strong>
					</td>
					<td width="12%">
						<input type="text" size="15" style="height:22px;" readonly="readonly" id="statisDate" onclick="setday(this,null,'yyyymmdd');" name="statisDate" value="${statisDate}" />
					 </td>
					 <td valign="middle" style="background-color:#DFEBF7;width:6%" align="center">
					 	<strong>����ѡ��</strong>
					 </td>
					 <td width="12%">
						<select	id="regionId" name="regionId" size="4" multiple="multiple" style="width:90%">
							<c:forEach items="${regions}" var="region">
		           	  	  		<c:choose>
		           	  	  			<c:when test="${fn:indexOf(regionId,region.regionId)>=0 }">
		           	  	  				<option value="${region.regionId }" title="${region.regionName }" selected="selected">${region.regionName }</option>
		           	  				</c:when>
		           	  				<c:otherwise>
		           	  	  				<option value="${region.regionId }" title="${region.regionName }">${region.regionName }</option>
		           	  				</c:otherwise>
		           	  			</c:choose>
							</c:forEach>
						</select>
					</td>
					<td width="8%">
						<input type="hidden" id="userId" name="userId" value="${userId }">
						<input type="button" class="button" value="�� ѯ" onclick="beforeQuery();"/> 
					</td>
					<td width="8%">
						<input type="button" class="button" value="�� ��" onclick="resetAll();">
					</td>
				</tr>
		</table>
	</form>
  </div>


   <div id="gridbox" style="width:100%;height:370px;"></div>
  
    <table id="myhead" cellspacing="0" cellpadding="0" style="display:none">
    	<tr>
    		<td columnId="statisDate">ͳ������</td>
    		<td columnId="regionName">�����ʶ</td>
    		<td columnId="phoneNo">�绰����</td>
    		<td columnId="checkinTime">����ʱ��</td>
    		<td columnId="checkinChnlId">������������</td>
    		<td columnId="checkinChnlName">������������</td>
    		<td columnId="checkinChnlType">������������</td>
    		<td columnId="monExpCurDayCommCnt">�³���ֹ����ͨ�Ŵ���</td>
    	</tr>
    </table>
	</div>
	
	<!-- Ӧ�ý��--> 
	<script type="text/javascript" src="<%=request.getContextPath() %>/js/simplemodal/jquery.simplemodal.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath() %>/js/simplemodal/css/basic.css" />
	<div id="treasuryText" style="display:none;height:300px;width:300px;padding:45px 50px 7px 60px;font-size:12px;text-align:left;">
		��������Ӧ�ý�⣬���Ե�...
	</div>
	
	<!-- �������� -->
	<a id="exportA" href="exportQhdRptChnlCall5BelowSubsDetlD.xls?statisDate=${statisDate}&regionId=${regionId}"></a>	
	<script type="text/javascript">
		var treasuryId ="";
		var treasuryAction = "";
		var treasuryUserInfo = ""
			
		
		function beforeQuery(){
			var flag = excuteQuery();
			if(flag){
				treasuryAction ="query";
				$("#treasuryText").modal();
				treasuryRequest();
				
			}
		}
		function beforeExport(){
			treasuryAction ="export";
			$("#treasuryText").modal();
			treasuryRequest();
		}
		function treasuryRequest(){
			var tName='�����·�չͨ����������û���ϸ';
			tName = encodeURI(tName);
			var appModule='�����·�չͨ����������û���ϸ';
			appModule = encodeURI(appModule);
			if(treasuryAction == "query"){
				var opText='<%=userInfo.getUser().getUserIdAccounts()%>��ѯ�������·�չͨ����������û���ϸģ���µ�����';
				opText = encodeURI(opText);
				}else if(treasuryAction =="export"){
					var opText='<%=userInfo.getUser().getUserIdAccounts()%>�����������·�չͨ����������û���ϸģ���µ�����';
					opText = encodeURI(opText);
				}

			var optObj='�����·�չͨ����������û���ϸ';
			optObj = encodeURI(optObj);
			var subUser='<%=userInfo.getUser().getUserIdAccounts()%>';
			subUser = encodeURI(subUser);   
					$.ajax({
						url:'<%=request.getContextPath()%>/treasuryRequest.action',
						type:'post',
						data:{
							busCode:'117000',
							sceneId:'110004',//����ID
							applyMaster:'${masterId}',//���˺�4A
							tName:tName
						},
						dataType:'json',
						success:function(jsonObj){
							if("off"==jsonObj.result){
								$.modal.close();//�ر�ģʽ����
								if("export" == treasuryAction){
									document.getElementById("exportA").click();
								}else if("query" == treasuryAction){
									$("#form1").submit();
								}
							}else if("1111"==jsonObj.result){
								$.ajax({
									url:'<%=request.getContextPath()%>/insertType.action',
									type:'post',
									data:{
										treasuryId:treasuryId,
										busCode:'117000',
										sceneId:'110004',
										logType:'2',
										logTime : '',
										subUser : subUser,
										status:'1',
										app : 'BASS',
										sip : '<%=request.getLocalAddr()%>',
										appModule : appModule,
										opType : 'View',
										opText : opText,
										ticket : '',
										optObj : optObj,
										logLevel : '1',
										masterAcc : '${masterId}'
									
									},
									success:function(tb){
										$.modal.close();//�ر�ģʽ����
										if("export" == treasuryAction){
											document.getElementById("exportA").click();
										}else if("query" == treasuryAction){
											$("#form1").submit();
										}
									},
									error:function(){
										alert("���ݿ����ʧ��");
								 	}
								});     
							}else if("2222" == jsonObj.applyMaster){
                                       alert("4A�˺�û�����ã�����ϵ4A����Ա��");
								}else if("123"==jsonObj.usersInfo){
								        alert("��ǰ�������������룩δ����Эͬ�����ˣ�����ϵ4A����������ã�");
								}else if("9999"==jsonObj.resultCodeStr){//Ӧ�����ģʽ
								        $.ajax({
												url:'<%=request.getContextPath()%>/insertType.action',
												type:'post',
												data:{
													treasuryId:treasuryId,
													busCode:'117000',
													sceneId:'111005',
													logType:'2',
													logTime : '',
													subUser : subUser,
													status:'1',
													app : 'BASS',
													sip : '<%=request.getLocalAddr()%>',
													appModule : appModule,
													opType : 'View',
													opText : opText,
													ticket : '',
													optObj : optObj,
													logLevel : '1',
													masterAcc : '${masterId}'
												
												},
												success:function(tb){
													$.modal.close();//�ر�ģʽ����
													if("export" == treasuryAction){
														document.getElementById("exportA").click();
													}else if("query" == treasuryAction){
														$("#form1").submit();
													}
												},
												error:function(){
													alert("���ݿ����ʧ��");
											 	}
											});     
								}else{
								
								       if("0" == jsonObj.ResultCode){
									
										treasuryId = jsonObj.TreasuryId;
										var jointUsers = jsonObj.JointUsers;
										var text = "<div>��֤��ʽ��</div><div><input style='filter:alpha(opacity=50);' name='AuthType' id='duanxi' type='radio' value='1' checked='checked' onclick='gongdanElm();'/>����<input  style='filter:alpha(opacity=50);'  name='AuthType' id='mima' type='radio' value='2' onclick='gongdanElm();'/>��̬����";//<input style='filter:alpha(opacity=50);' name='AuthType' id='gondan' type='radio' value='5' onclick='gongdanElm();'/>����</div> 
										 text += "<div id='g' name='gond'>";
										text += "<div>Эͬ�ˣ�</div>" ;
										text += "<div style='height:132px;overflow:auto;'>";
										for(var i=0;i<jointUsers.length;i++){
											text += "<div><input type='checkbox' style='filter:alpha(opacity=50);' class='button' id='"+jointUsers[i].Account+"' value='"+jointUsers[i].RealName+"' name='jointUsersChk'/>"+jointUsers[i].RealName+"</div>";
										}
										text += "</div>";
										
										text += "<div>����ԭ��</div><input style='filter:alpha(opacity=50);' type='text' id='ApplyReason' />";
										text += "</div>";
										
										 text += "<div><button type='button' class='button' style='filter:alpha(opacity=50);' onclick='trea()'>�ύ</button></div>"
										   $("#treasuryText").html(text);
									
								}else if("1" == jsonObj.ResultCode){//���ǽ��ģʽ
									 $.modal.close();//�ر�ģʽ����
										if("export" == treasuryAction){
											document.getElementById("exportA").click();
										}else if("query" == treasuryAction){
											$("#form1").submit();
										}
								}else if("1000" == jsonObj.ResultCode){
									$.ajax({
											url:'<%=request.getContextPath()%>/treasuryLog2.action',
											type:'post',
											data:{
												busCode:'117000',
												sceneId:'110004',
												logType:'1',
												subUser : subUser,
												status:'2',
												logTime : '',
												app : 'BASS',
												sip : '',
												appModule : appModule,
												opType : 'View',
												opText : opText,
												ticket : '',
												optObj : optObj,
												logLevel : '1',
												masterAcc : '${masterId}',
												treasuryId:treasuryId
											},
											success:function(responseString){
												$.modal.close();//�ر�ģʽ����
												if("export" == treasuryAction){
													document.getElementById("exportA").click();
												}else if("query" == treasuryAction){
													$("#form1").submit();
												}
											},
											error:function(){
												alert("����ʧ�ܣ�");
										 }
									}); 
							    }
						  }
						},
						error:function(){
								
								}
					}); 
			
		}


		function gongdanElm(){
               
	    	   var checkBoxGroup=document.getElementsByName("AuthType");
			   	var rvalue="";
	   			for(var i=0;i<checkBoxGroup.length;i++)
	   			{
				  if(checkBoxGroup[i].checked)
				   {
					 rvalue=checkBoxGroup[i].value;
					 if(rvalue=="5"){
							jQuery("div[name='gond']").each(function(){
								jQuery(this).css("display","none");
							});
						 }else{
							 jQuery("div[name='gond']").each(function(){
									jQuery(this).css("display","block");
								});
							 }
				   }
	   			}
	           }
		
		function trea(){//��֤ѡ��
		   var checkBoxGroup=document.getElementsByName("AuthType");
		   	var rvalue="";
   			for(var i=0;i<checkBoxGroup.length;i++)
   			{
			  if(checkBoxGroup[i].checked)
			   {
				 rvalue=checkBoxGroup[i].value;
				  var appModule='�����·�չͨ����������û���ϸ';
				appModule = encodeURI(appModule);
				if(treasuryAction == "query"){
					var opText='<%=userInfo.getUser().getUserIdAccounts()%>��ѯ�������·�չͨ����������û���ϸģ���µ�����';
					opText = encodeURI(opText);
					}else if(treasuryAction =="export"){
						var opText='<%=userInfo.getUser().getUserIdAccounts()%>�����������·�չͨ����������û���ϸģ���µ�����';
						opText = encodeURI(opText);
					}
	
				var optObj='�����·�չͨ����������û���ϸ';
				optObj = encodeURI(optObj);
				var subUser='<%=userInfo.getUser().getUserIdAccounts()%>';
				subUser = encodeURI(subUser);
		      if(rvalue=="2"){//��̬����
		       var jointUser = "";
		        var userInfo="";
			    var jointUsersInfo = document.getElementsByName("jointUsersChk");
			    for(var i=0;i<jointUsersInfo.length;i++){
					var temp = jointUsersInfo[i];
					if(temp.checked){
						userInfo += temp.id+"::"+temp.value;
						jointUser += temp.id;
					    userInfo += ";;";
				        jointUser += "::";
					}	
				}
				
				userInfo=userInfo.substring(0,userInfo.length-2);
			    treasuryUserInfo = userInfo;
			    
			    if(jointUser == ""){
					alert("��ѡ��Эͬ�ˣ�");
					return;
				}
				var applyReason = $("#ApplyReason").val();
				$("#treasuryText").attr("JointUser",jointUser);
				var userInfo = treasuryUserInfo.split(";;");
							var text = "������Эͬ����֤��Ϣ��<br/><div style='height:170px;overflow:auto;'>";
							var userInfo = treasuryUserInfo.split(";;");
							for(var i=0;i<userInfo.length;i++){
								var temp = userInfo[i].split("::");
								var uId = temp[0];
								text += "</br>"+temp[1];
								text += "</br><input style='filter:alpha(opacity=50);' id='"+uId+"' name='JointUserValue' type='password'/>";
							}
							text += "</div></br>";
							text +="<button type='button' class='button' style='filter:alpha(opacity=50);' onclick='treasuryAuth2()'>�ύ</button>";
							$("#treasuryText").html(text);
				var jointUserValue = "";
				var jointuv = document.getElementsByName('JointUserValue');
				for(var i=0;i<jointuv.length;i++){
					var uid = jointuv[i].id;
					var uval = jointuv[i].value;
					jointUserValue += uid+"::"+uval;
					if(i!=jointuv.length){
						jointUserValue +=";;";
					}
				}
		    	 
			}else if(rvalue=="1"){//������֤
				
			    var userInfo="";
			    var jointUser = "";
			    var jointUsersInfo = document.getElementsByName("jointUsersChk");
			    for(var i=0;i<jointUsersInfo.length;i++){
					var temp = jointUsersInfo[i];
					if(temp.checked){
						userInfo += temp.id+"::"+temp.value;
						jointUser += temp.id;
							userInfo += ";;";
							jointUser += "::";
					}	
				}
				userInfo=userInfo.substring(0,userInfo.length-2);
			    treasuryUserInfo = userInfo;
			    
			    if(jointUser == ""){
					alert("��ѡ��Эͬ�ˣ�");
					return;
				}
				var applyReason = $("#ApplyReason").val();
				$("#treasuryText").attr("JointUser",jointUser);
				
				$.ajax({
					url:'<%=request.getContextPath()%>/treasuryAuthInit.action',
					type:'post',
					data:{
						busCode:'117000',
						sceneId:'110004',
						applyMaster:'${masterId}',
						jointUser:jointUser,
						treasuryId:treasuryId,
						applyReason:applyReason,
						authType:'1'
					},
					success:function(responseString){
						if("0" == responseString){
							var text = "������Эͬ����֤��Ϣ��<br/><div style='height:170px;overflow:auto;'>"
							var userInfo = treasuryUserInfo.split(";;");
							for(var i=0;i<userInfo.length;i++){
								var temp = userInfo[i].split("::");
								var uId = temp[0];
								text += "</br>"+temp[1];
								text += "</br><input style='filter:alpha(opacity=50);' id='"+uId+"' name='JointUserValue' type='text'/>";
							}
							text += "</div></br><button type='button' class='button' style='filter:alpha(opacity=50);' onclick='treasuryAuth()'>�ύ</button>"
							$("#treasuryText").html(text);
						}else if("1" == responseString){
							alert("��֤��ʼ��ʧ�ܣ�");
						}else if("1000" == responseString){
									$.ajax({
										url:'<%=request.getContextPath()%>/treasuryLog2.action',
										type:'post',
										data:{
											busCode:'117000',
											sceneId:'110004',
											logType:'1',
											subUser : subUser,
											status:'2',
											logTime : '',
											app : 'BASS',
											sip : '',
											appModule : appModule,
											opType : 'View',
											opText : opText,
											ticket : '',
											optObj : optObj,
											logLevel : '1',
											masterAcc : '${masterId}',
											treasuryId:treasuryId
										},
										success:function(responseString){
											$.modal.close();//�ر�ģʽ����
											if("export" == treasuryAction){
												document.getElementById("exportA").click();
											}else if("query" == treasuryAction){
												$("#form1").submit();
											}
										},
										error:function(){
											alert("����ʧ�ܣ�");
									 }
								}); 
							}
					},
					error:function(){
						alert("����ʧ�ܣ�");
					}
				});
			  }else	if(rvalue=="5"){
				
				    $.ajax({
						url:'<%=request.getContextPath()%>/getWorkOrderList.action',
						type:'post',
						data:{
							busCode:'117000',
							sceneId:'110004',
							applyMaster:'${masterId}'

						},
						dataType:'json',
						success:function(jsonObj){
							if("0" == jsonObj.ResultCode){
								
								var  orderList= jsonObj.OrderList;
								var text = "<div>";
								text += "<div>�����б�</div>";
								text += "<div style='height:170px;'>";
								
								for(var i=0;i<orderList.length;i++){
									text +="<input style='filter:alpha(opacity=50);' type='checkbox' id='"+orderList[i].Orderid+"' value='"+orderList[i].OrderName+"' name='jointOrdersChk'/>"+orderList[i].OrderName+"</div>";
									}
								
								text += "</div>";
											text += "</br><button type='button' class='button' style='filter:alpha(opacity=50);' onclick='getValidateOrder()'>�ύ</button>"
											$("#treasuryText").html(text);
								
							}else if("1" == jsonObj.ResultCode){
								alert("��֤��ʼ��ʧ�ܣ�");
							}else if("1000" == jsonObj.ResultCode){
										$.ajax({
										url:'<%=request.getContextPath()%>/treasuryLog2.action',
										type:'post',
										data:{
											busCode:'117000',
											sceneId:'110004',
											logType:'1',
											subUser : subUser,
											status:'2',
											logTime : '',
											app : 'BASS',
											sip : '',
											appModule : appModule,
											opType : 'View',
											opText : opText,
											ticket : '',
											optObj : optObj,
											logLevel : '1',
											masterAcc : '${masterId}',
											treasuryId:treasuryId
										},
										success:function(responseString){
											$.modal.close();//�ر�ģʽ����
											if("export" == treasuryAction){
												document.getElementById("exportA").click();
											}else if("query" == treasuryAction){
												$("#form1").submit();
											}
										},
										error:function(){
											alert("����ʧ�ܣ�");
									 }
								}); 
							}
						},
						error:function(){
							alert("����ʧ�ܣ�");
					 }
					});   
				}	
		  }
   	   }
	}
		
		function treasuryAuth(){//������֤
			var jointUserValue = "";
			var jointuv = document.getElementsByName('JointUserValue');
			for(var i=0;i<jointuv.length;i++){
				var uid = jointuv[i].id;
				var uval = jointuv[i].value;
				if(uval == ""){
					alert("����дЭͬ����֤��Ϣ��");
					return;
				}
				var validateObj = /^[A-Za-z0-9]+$/;
				if(!validateObj.test(uval)){
					alert("��֤��Ϣֻ��Ϊ��ĸ�����֣�");
					return;
				}
				
				jointUserValue += uid+"::"+uval;
				if(i!=jointuv.length){
					jointUserValue +=";;";
				}
			}
			      var appModule='�����·�չͨ����������û���ϸ';
				appModule = encodeURI(appModule);
				if(treasuryAction == "query"){
					var opText='<%=userInfo.getUser().getUserIdAccounts()%>��ѯ�������·�չͨ����������û���ϸģ���µ�����';
					opText = encodeURI(opText);
					}else if(treasuryAction =="export"){
						var opText='<%=userInfo.getUser().getUserIdAccounts()%>�����������·�չͨ����������û���ϸģ���µ�����';
						opText = encodeURI(opText);
					}
	
				var optObj='�����·�չͨ����������û���ϸ';
				optObj = encodeURI(optObj);
				var subUser='<%=userInfo.getUser().getUserIdAccounts()%>';
				subUser = encodeURI(subUser);  
			$.ajax({
				url:'<%=request.getContextPath()%>/treasuryAuth.action',
				type:'post',
				data:{
					busCode:'117000',
					sceneId:'110004',
					applyMaster:'${masterId}',
					jointUserValue : jointUserValue,
					treasuryId:treasuryId,
					authType:'1'
				},
				success:function(responseString){
					if("0" == responseString){
						alert("��֤�ɹ���");
						$.modal.close();//�ر�ģʽ����
						if("export" == treasuryAction){
							document.getElementById("exportA").click();
						}else if("query" == treasuryAction){
							$("#form1").submit();
						}
						
					}else if("9999"==jsonObj.resultCodeStr){//Ӧ�����ģʽ
								        $.ajax({
												url:'<%=request.getContextPath()%>/insertType.action',
												type:'post',
												data:{
													treasuryId:treasuryId,
													busCode:'117000',
													sceneId:'111005',
													logType:'2',
													logTime : '',
													subUser : subUser,
													status:'1',
													app : 'BASS',
													sip : '<%=request.getLocalAddr()%>',
													appModule : appModule,
													opType : 'View',
													opText : opText,
													ticket : '',
													optObj : optObj,
													logLevel : '1',
													masterAcc : '${masterId}'
												
												},
												success:function(tb){
													$.modal.close();//�ر�ģʽ����
													if("export" == treasuryAction){
														document.getElementById("exportA").click();
													}else if("query" == treasuryAction){
														$("#form1").submit();
													}
												},
												error:function(){
													alert("���ݿ����ʧ��");
											 	}
											});     
								}else if("1000" == responseString){
								$.ajax({
										url:'<%=request.getContextPath()%>/treasuryLog2.action',
										type:'post',
										data:{
											busCode:'117000',
											sceneId:'110004',
											logType:'1',
											subUser : subUser,
											status:'2',
											logTime : '',
											app : 'BASS',
											sip : '',
											appModule : appModule,
											opType : 'View',
											opText : opText,
											ticket : '',
											optObj : optObj,
											logLevel : '1',
											masterAcc : '${masterId}',
											treasuryId:treasuryId
										},
										success:function(responseString){
											$.modal.close();//�ر�ģʽ����
											if("export" == treasuryAction){
												document.getElementById("exportA").click();
											}else if("query" == treasuryAction){
												$("#form1").submit();
											}
										},
										error:function(){
											alert("����ʧ�ܣ�");
									 }
								}); 																
						}else if("1001" == responseString){
						      alert("��֤��ʱ���ֵ��");
						}else{
							alert("��֤ʧ�ܣ�");
							$.ajax({
								url:'<%=request.getContextPath()%>/treasuryLog.action',
								type:'post',
								data:{
									busCode:'117000',
									sceneId:'110004',
									logType:'2',
									subUser : subUser,
									status:'1',
									logTime : '',
									app : 'BASS',
									sip : '',
									appModule : appModule,
									opType : 'View',
									opText : opText,
									ticket : '',
									optObj : optObj,
									logLevel : '1',
									masterAcc : '${masterId}',
									treasuryId:treasuryId
								
								},
								success:function(responseString){
									
								},
								error:function(){
									alert("����ʧ�ܣ�");
							 }
							});
						}
				},
				error:function(){
					alert("����ʧ�ܣ�");
			 }
			});
			
		}
		function treasuryAuth2(){//��̬������֤
			var jointUserValue = "";
			var jointuv = document.getElementsByName('JointUserValue');
			for(var i=0;i<jointuv.length;i++){
				var uid = jointuv[i].id;
				var uval = jointuv[i].value;
				if(uval == ""){
					alert("����дЭͬ����֤��Ϣ��");
					return;
				}			
				jointUserValue += uid+"::"+uval;
				if(i!=jointuv.length){
					jointUserValue +=";;";
				}
			}
			       var appModule='�����·�չͨ����������û���ϸ';
					appModule = encodeURI(appModule);
					if(treasuryAction == "query"){
						var opText='<%=userInfo.getUser().getUserIdAccounts()%>��ѯ�������·�չͨ����������û���ϸģ���µ�����';
						opText = encodeURI(opText);
						}else if(treasuryAction =="export"){
							var opText='<%=userInfo.getUser().getUserIdAccounts()%>�����������·�չͨ����������û���ϸģ���µ�����';
							opText = encodeURI(opText);
						}
		
					var optObj='�����·�չͨ����������û���ϸ';
					optObj = encodeURI(optObj);
					var subUser='<%=userInfo.getUser().getUserIdAccounts()%>';
					subUser = encodeURI(subUser);
			$.ajax({
				url:'<%=request.getContextPath()%>/treasuryAuth.action',
				type:'post',
				data:{
					busCode:'117000',
					sceneId:'110004',
					applyMaster:'${masterId}',
					jointUserValue : jointUserValue,
					treasuryId:treasuryId,
					authType:'2'
				},
				success:function(responseString){
					if("0" == responseString){
						alert("��֤�ɹ���");
						$.modal.close();//�ر�ģʽ����
						if("export" == treasuryAction){
							document.getElementById("exportA").click();
						}else if("query" == treasuryAction){
							$("#form1").submit();
						}
						
					}else if("9999"==jsonObj.resultCodeStr){//Ӧ�����ģʽ
								        $.ajax({
												url:'<%=request.getContextPath()%>/insertType.action',
												type:'post',
												data:{
													treasuryId:treasuryId,
													busCode:'117000',
													sceneId:'111005',
													logType:'2',
													logTime : '',
													subUser : subUser,
													status:'1',
													app : 'BASS',
													sip : '<%=request.getLocalAddr()%>',
													appModule : appModule,
													opType : 'View',
													opText : opText,
													ticket : '',
													optObj : optObj,
													logLevel : '1',
													masterAcc : '${masterId}'
												
												},
												success:function(tb){
													$.modal.close();//�ر�ģʽ����
													if("export" == treasuryAction){
														document.getElementById("exportA").click();
													}else if("query" == treasuryAction){
														$("#form1").submit();
													}
												},
												error:function(){
													alert("���ݿ����ʧ��");
											 	}
											});     
								}else if("1000" == responseString){
						alert(responseString);
								$.ajax({
										url:'<%=request.getContextPath()%>/treasuryLog2.action',
										type:'post',
										data:{
											busCode:'117000',
											sceneId:'110004',
											logType:'1',
											subUser : subUser,
											status:'2',
											logTime : '',
											app : 'BASS',
											sip : '',
											appModule : appModule,
											opType : 'View',
											opText : opText,
											ticket : '',
											optObj : optObj,
											logLevel : '1',
											masterAcc : '${masterId}',
											treasuryId:treasuryId
										},
										success:function(responseString){
											$.modal.close();//�ر�ģʽ����
											if("export" == treasuryAction){
												document.getElementById("exportA").click();
											}else if("query" == treasuryAction){
												$("#form1").submit();
											}
										},
										error:function(){
											alert("����ʧ�ܣ�");
									 }
								}); 		
						}else if("1001" == responseString){
						      alert("��֤��ʱ���ֵ��");
						}else{
							alert(responseString);
						     	$.ajax({
										url:'<%=request.getContextPath()%>/treasuryLog.action',
										type:'post',
										data:{
											busCode:'117000',
											sceneId:'110004',
											logType:'2',
											subUser : subUser,
											status:'1',
											logTime : '',
											app : 'BASS',
											sip : '',
											appModule : appModule,
											opType : 'View',
											opText : opText,
											ticket : '',
											optObj : optObj,
											logLevel : '1',
											masterAcc : '${masterId}',
											treasuryId:treasuryId
										
									},
									success:function(responseString){
									},
									error:function(){
										alert("����ʧ�ܣ�");
								 }
							});
					}
				},
				error:function(){
					alert("����ʧ�ܣ�");
			 }
			});
			
		}

	function getValidateOrder(){
		var orderList = "";
		var jointuv = document.getElementsByName('jointOrdersChk');
		for(var i=0;i<jointuv.length;i++){
			var uid = jointuv[i].id;
			var uname =  jointuv[i].name;
			if(uid == ""){
				alert("����дЭͬ�˹����ţ�");
				return;
			}	
			if(uname == ""){
				alert("����дЭͬ�˹������ƣ�");
				return;
			}
					
			orderList += uid+"::"+uname;
			if(i!=jointuv.length){
				orderList +=";;";
			}
		}
		            var appModule='�����·�չͨ����������û���ϸ';
					appModule = encodeURI(appModule);
					if(treasuryAction == "query"){
						var opText='<%=userInfo.getUser().getUserIdAccounts()%>��ѯ�������·�չͨ����������û���ϸģ���µ�����';
						opText = encodeURI(opText);
						}else if(treasuryAction =="export"){
							var opText='<%=userInfo.getUser().getUserIdAccounts()%>�����������·�չͨ����������û���ϸģ���µ�����';
							opText = encodeURI(opText);
						}
		
					var optObj='�����·�չͨ����������û���ϸ';
					optObj = encodeURI(optObj);
					var subUser='<%=userInfo.getUser().getUserIdAccounts()%>';
					subUser = encodeURI(subUser);	
					var orderType ='���ι���';
					orderType = encodeURI(orderType);	
			$.ajax({
				url:'<%=request.getContextPath()%>/getValidateOrder.action',
				type:'post',
				data:{
					busCode:'117000',
					sceneId:'110004',
					orderType:orderType,
					orderid:'201201',
					applyMaster:'${masterId}'
				},
				success:function(responseString){
					if("0" == responseString){
						alert("��֤�ɹ���");
						$.modal.close();//�ر�ģʽ����
						if("export" == treasuryAction){
							document.getElementById("exportA").click();
						}else if("query" == treasuryAction){
							$("#form1").submit();
						}
						
					}else if("1000" == responseString){
						alert(responseString);
								$.ajax({
								url:'<%=request.getContextPath()%>/treasuryLog2.action',
								type:'post',
								data:{
									busCode:'117000',
									sceneId:'110004',
									logType:'1',
									subUser : subUser,
									status:'2',
									logTime : '',
									app : 'BASS',
									sip : '',
									appModule : appModule,
									opType : 'View',
									opText : opText,
									ticket : '',
									optObj : optObj,
									logLevel : '1',
									masterAcc : '${masterId}',
									treasuryId:treasuryId
								},
								success:function(responseString){
									$.modal.close();//�ر�ģʽ����
									if("export" == treasuryAction){
										document.getElementById("exportA").click();
									}else if("query" == treasuryAction){
										$("#form1").submit();
									}
								},
								error:function(){
									alert("����ʧ�ܣ�");
							 }
						}); 					
						}else{
							alert(responseString);
					     	$.ajax({
								url:'<%=request.getContextPath()%>/treasuryLog.action',
								type:'post',
								data:{
									busCode:'117000',
									sceneId:'110004',
									logType:'2',
									subUser : subUser,
									status:'1',
									logTime : '',
									app : 'BASS',
									sip : '',
									appModule : appModule,
									opType : 'View',
									opText : opText,
									ticket : '',
									optObj : optObj,
									logLevel : '1',
									masterAcc : '${masterId}',
									treasuryId:treasuryId
								
							},
							success:function(responseString){
							},
							error:function(){
								alert("����ʧ�ܣ�");
						    }
						});
					}
				},
				error:function(){
					alert("����ʧ�ܣ�");
			     }
			});
		}
		</script>
  </body>
</html>
