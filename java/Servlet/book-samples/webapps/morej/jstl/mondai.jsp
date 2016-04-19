<%@page contentType="text/html; charset=Windows-31J"%>
<%@page import="java.util.*, el.User"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<HTML>
<BODY>

<H2>練習問題</H2>

<%
   List list = new ArrayList();
   list.add(new User("太郎",true));
   list.add(new User("次郎",true));
   list.add(new User("花子",false));
   request.setAttribute("userList",list);
%>


<TABLE BORDER="1">
<TR>
	<TH>番号
	<TH>名前
	<TH>性別

<c:forEach var="user" items="${userList}" varStatus="status">
	
<TR>
	<TD>${status.count} 
	<TD>${user.name}
    <TD>
    	<c:choose>
    		<c:when test="${user.male}">
    			男
    		</c:when>
    		<c:otherwise>
    			男以外
    		</c:otherwise>
    	</c:choose>

</c:forEach>

</TABLE>

</BODY>
</HTML>
