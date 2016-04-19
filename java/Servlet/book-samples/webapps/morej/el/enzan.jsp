<%@page contentType="text/html; charset=Windows-31J"%>
<%@page import="java.util.*"%>

<html>
<body>

<h2>EL‚ÌƒeƒXƒg</h2>

<%
  //€”õ
  List list = new ArrayList();
  request.setAttribute("list", list);
%>


<pre>

${ 1 + 2 }

${ 1 < 2 }

${ true && false }

${ 1 < 2 ? 2 : 1 }

${ empty list }

</pre>


</body>
</html>
