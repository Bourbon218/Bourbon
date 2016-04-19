<%@page contentType="text/html; charset=Windows-31J"%>

<html>
<body>

<h2>数字当てゲーム</h2>

<p>(入力チェックバージョン)

<p>0～9の数字をいれてください
<form action="../servlet/validate.GuessServlet">
	<input type="text" name="number">
	<input type="submit" value="送信">
</form>

</body>
</html>
