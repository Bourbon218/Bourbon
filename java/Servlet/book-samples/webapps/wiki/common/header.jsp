<%@page contentType="text/html; charset=Windows-31J"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<HTML>

<HEAD>
	<TITLE>PuchiWiki</TITLE>
</HEAD>


<BODY>


<%--ロゴ--%>

<IMG SRC="img/logo.gif"> 



<%--ヘッダメニュー--%>

<A HREF="list">一覧</A>
|
<A HREF="create.jsp">新規</A>



<%--メッセージ表示--%>

<P>${message}


<HR>
