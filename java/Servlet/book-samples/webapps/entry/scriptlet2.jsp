<HTML>
<HEAD>
<TITLE>Scriptlet2</TITLE>
</HEAD>
<BODY>
<H2>JSP Scriptlet2</H2>
<%
int count = 0;
for(int i=0; i< 10; i++){
	count += i;
}
out.println("count=" + count);
%>
</BODY>
</HTML>
