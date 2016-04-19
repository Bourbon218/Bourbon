<%@page contentType="text/html; charset=Windows-31J"%>


<html>
<body>

<h2>beanタグのテスト</h2>

<jsp:useBean id="user" class="core.User"/>
<jsp:setProperty name="user" property="age" value="20"/>
<jsp:getProperty name="user" property="age"/>

</body>
</html>
