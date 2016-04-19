<HTML>
<HEAD>
<TITLE>Scriptlet</TITLE>
</HEAD>
<BODY>
<H2>JSP Scriptlet</H2>
<%
int count = 0;
for(int i=0; i< 10; i++){
	count += i;
}
%>
count = <%=count%>
</BODY>
</HTML>
