<%@page contentType="text/html; charset=Windows-31J"%>
<%@page import="guess.*"%>

<html>
<body>

<h1>数字当てゲーム</h1>

<p>４桁の数字を入力してください

<form action="<%=request.getContextPath()%>/servlet/guess.GuessNumberServlet">

	<p>数字
	<input type="text" name="number">

	<p>
	<input type="submit">
	
</form>

<%
	GuessNumber guess = GuessNumber.getInstanceFromSession(request);

	//out.println(guess + "<br>");	

	if(guess.isInputError()){
		
		out.println(guess.getInputErrorMessage());
		
	}else if(guess.getCount() != 0){

		out.println(
			guess.getCount()
			+ "回目の数字「"
			+ guess.getInput()
			+ "」"
			+ guess.getHit() + "ヒット"
			+ guess.getError() + "エラー"
		);
	}
%>

</body>
</html>
