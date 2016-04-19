<%@page contentType="text/html; charset=Windows-31J"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="myel.*, java.util.*"%>

<HTML>
<BODY>

<P>コメントアウトしています。

<H2>c:import</H2>

<H3>例</H3>

<%--
<c:import url="/jstl/include.txt"/>
--%>

<H3>例</H3>

<%--
<c:import url="http://yahoo.co.jp/"/>
--%>

<H2>c:redirect</H2>

<%--
<c:edirect url="http://yahoo.co.jp/"/>
--%>

<H2>c:url</H2>


<c:url var="url" value = "/jstl/hello.jsp" >
	<c:param name="name1" value="ほげ"/>
</c:url>

<A HREF="${url}">link</A>



</BODY>
</HTML>
