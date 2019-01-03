<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <script language='javascript'>
		function logout()
		{
		   if(confirm("确定要退出本系统吗??"))
		   {
			   window.parent.location="<%=path %>/login.jsp";
		   }
		}
    </script>
  </head>
  
  <body bgColor='#9ad075' style="margin: 0;padding: 0">
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
	  <tr>
	    <td width='50%' height="90" style="font-size:26px; font-weight: bold;">&nbsp;&nbsp;合同管理系统</td>
	    <td width='50%' align="right">
	    	<table width="550" border="0" cellspacing="0" cellpadding="0">
		      <tr>
			      <td align="right" style="padding-right:10px;line-height:26px;font-size:17px">
			        	<font style="font-size:14px; font-weight: bold;">
			        	    <c:if test="${sessionScope.userType==0}">
			        	         您好：系统管理员&nbsp;&nbsp;&nbsp;&nbsp;
			        	    </c:if>
			        	</font>
			        	
			        	[<a href="#" onclick="logout()" style="font-size:13px; font-weight: bold;text-decoration: none;color: black">注销退出</a>]
			      </td>
	          </tr>
	        </table>
	    </td>
	  </tr>
	</table>
  </body>
</html>
