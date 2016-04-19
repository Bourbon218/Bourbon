<%@page contentType="text/html; charset=Windows-31J"%>
<%@page import="java.util.*, el.User"%>

<html>
<body>

<h2>練習問題</h2>

<%
   List list = new ArrayList();
   list.add(new User("太郎",true));
   list.add(new User("次郎",true));
   list.add(new User("花子",false));
   request.setAttribute("list",list);
%>

${list[2].name}
は
${list[2].male ? "男" : "男以外"}

</body>
</html>
