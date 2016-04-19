<%@page contentType="text/html; charset=Windows-31J"%>

<html>
<body>

<h2>attribute‚ÌƒeƒXƒg</h2>


<jsp:include page="hoge.jsp"/>


<jsp:include>
	<jsp:attribute name="page">hoge.jsp</jsp:attribute>
</jsp:include>


<jsp:element name="include">
	<jsp:attribute name="page">hoge.jsp</jsp:attribute>
</jsp:element>

</body>
</html>
