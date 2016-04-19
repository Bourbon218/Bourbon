<%@page contentType="text/html; charset=Windows-31J"%>

<HTML>
<BODY>

<H2>数字当てゲーム</H2>
<P>(画面遷移＆リクエスト属性バージョン)

<P>0～9の数字をいれてください
<FORM ACTION="../servlet/reqattr.GuessServlet">
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
