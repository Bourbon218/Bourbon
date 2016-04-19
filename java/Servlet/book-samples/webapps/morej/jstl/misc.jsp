<%@page contentType="text/html; charset=Windows-31J"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="myel.*, java.util.*"%>

<HTML>
<BODY>


<H2>c:catch</H2>

<H3>—á</H3>

<c:catch var="error">
<%
	if(true){
		throw new IllegalArgumentException();
	}
%>
</c:catch>

${error}



<H2>c:out</H2>

<H3>—á</H3>


<c:set var="message" value="hello"/>
<c:out value="${message}"/>


</BODY>
</HTML>
