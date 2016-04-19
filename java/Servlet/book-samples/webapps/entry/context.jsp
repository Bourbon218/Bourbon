<%@page contentType="text/html; charset=Windows-31J" %>
<HTML>
<HEAD>
<TITLE>ルートへのリンク</TITLE>
</HEAD>
<BODY>

<H2>ルートへのリンク</H2>

<P><A HREF="/">サーバーのルート</A>

<P><A HREF="<%=request.getContextPath()%>">Webアプリケーションのルート</A>

</BODY>
</HTML>
