<%@page contentType="text/html; charset=Windows-31J"%>
<%@page import="guess.*"%>

<html>
<body>

<h1>数字当てゲーム</h1>

<%
	GuessNumber guess = GuessNumber.getInstanceFromSession(request);
%>

<%=guess.getCount()%>回目であたり！


<%
	guess.reset();
%>

<p>
<a href="<%=request.getContextPath()%>/guess.jsp">もう一回</a>

</body>
</html>