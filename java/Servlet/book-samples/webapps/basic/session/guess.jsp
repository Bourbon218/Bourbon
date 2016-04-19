<%@page contentType="text/html; charset=Windows-31J"%>

<HTML>
<BODY>

<H2>数字当てゲーム</H2>
<P>(セッションバージョン)

<P>0～9の数字をいれてください
<FORM ACTION="../servlet/session.GuessServlet">
	<INPUT TYPE="TEXT" NAME="number">
	<INPUT TYPE="SUBMIT" VALUE="送信">
</FORM>

<%
String message = (String)request.getAttribute("message");
if(message != null){
	out.println(message);
}
%>

</BODY>
</HTML>
