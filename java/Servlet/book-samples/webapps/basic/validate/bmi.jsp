<%@page contentType="text/html; charset=Windows-31J"%>

<html>
<body>

<h2>BMI計算</h2>

<form action="../servlet/validate.BMIServlet">
	身長<input type="text" name="height">(kg)
	体重<input type="text" name="weight">(cm)
	<input type="submit" value="計算">
</form>

</body>
</html>
