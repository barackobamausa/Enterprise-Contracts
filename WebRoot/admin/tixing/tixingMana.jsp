<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page isELIgnored="false" %> 
<%
String path = request.getContextPath();
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />

		<link rel="stylesheet" type="text/css" href="<%=path %>/css/base.css" />
		
        <script language="javascript">
           function tixingDel(id)
           {
               if(confirm('您确定删除吗？'))
               {
                   window.location.href="<%=path %>/tixing?type=tixingDel&id="+id;
               }
           }
           
           function tixingAdd()
           {
                 var url="<%=path %>/admin/tixing/tixingAdd.jsp";
				 window.location.href=url;
           }
       </script>
	</head>

	<body leftmargin="2" topmargin="2" background='<%=path %>/images/allbg.gif'>
			<table width="98%" border="0" cellpadding="2" cellspacing="1" bgcolor="#D1DDAA" align="center" style="margin-top:8px">
				<tr bgcolor="#E7E7E7">
					<td height="14" colspan="5" background="<%=path %>/images/tbg.gif">&nbsp;&nbsp;</td>
				</tr>
				<tr align="center" bgcolor="#FAFAF1" height="22">
					<td width="4%">序号</td>
					<td width="15%">合同名称</td>
					<td width="10%">收款时间</td>
					<td width="10%">收款金额</td>
					
					<td width="10%">操作</td>
		        </tr>	
				<c:forEach items="${requestScope.tixingList}" var="tixing" varStatus="ss">
				<tr align='center' bgcolor="#FFFFFF" onMouseMove="javascript:this.bgColor='red';" onMouseOut="javascript:this.bgColor='#FFFFFF';" height="22">
					<td bgcolor="#FFFFFF" align="center">
						${ss.index+1}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${tixing.hetong.mingcheng}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${tixing.shoukuanshijian}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						${tixing.shoukuanjine}
					</td>
					<td bgcolor="#FFFFFF" align="center">
						<input type="button" value="删除" onclick="tixingDel(${tixing.id})"/>
					</td>
				</tr>
				</c:forEach>
			</table>
			
			<table width='98%'  border='0'style="margin-top:8px;margin-left: 8px;">
			  <tr>
			    <td>
			      <input type="button" value="添加收款提醒" style="width: 80px;" onclick="tixingAdd()" />
			    </td>
			  </tr>
		    </table>
	</body>
</html>
