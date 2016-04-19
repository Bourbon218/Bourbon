<%@page contentType="text/html; charset=Windows-31J"%>
<%@page import="el.*"%>

<html>
<body>

<h2>ELのテスト</h2>

<%
   //準備
   User user = new User();
   user.setName("太郎");
   user.setMale(true);
   request.setAttribute("user", user);
%>

<pre>
名前は ${user.name}

男ですか？ ${user.male}

ちなみに ${user}
</pre>

</body>
</html>
