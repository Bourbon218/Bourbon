<%@page contentType="text/html; charset=Windows-31J"%>

<HTML>
<BODY>

<H2>MethodServletの入力</H2>

<A HREF="../servlet/input.MethodServlet?message=hello">リンクでGET送信</A>


<FORM ACTION="../servlet/input.MethodServlet" METHOD="GET">
	<INPUT TYPE="TEXT" NAME="message">
	<INPUT TYPE="SUBMIT" VALUE="フォームでGET送信">
</FORM>

<FORM ACTION="../servlet/input.MethodServlet" METHOD="POST">
	<INPUT TYPE="TEXT" NAME="message">
	<INPUT TYPE="SUBMIT" VALUE="フォームでpost送信">
</FORM>

</BODY>
</HTML>
