<%@page contentType="text/html; charset=Windows-31J" %>
<HTML>
<HEAD>
<TITLE>サーブレットへのリンク２</TITLE>
</HEAD>
<BODY>

<H2>サーブレットへのリンク２</H2>

<P><A HREF="../servlet/HelloServlet">HelloServlet</a>
(カレントからのパス)

<P><A HREF="<%=request.getContextPath()%>/servlet/HelloServlet">HelloServlet</A>
(サーバールートからのパス)

</BODY>
</HTML>
