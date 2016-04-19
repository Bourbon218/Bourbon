<%@page contentType="text/html; charset=Windows-31J"%>
<%@page import="guess.*"%>

<html>
<body>

<h1>”Žš“–‚ÄƒQ[ƒ€</h1>

<%
	GuessNumber guess = GuessNumber.getInstanceFromSession(request);
%>

<%=guess.getCount()%>‰ñ–Ú‚Å‚ ‚½‚èI


<%
	guess.reset();
%>

<p>
<a href="<%=request.getContextPath()%>/guess.jsp">‚à‚¤ˆê‰ñ</a>

</body>
</html>