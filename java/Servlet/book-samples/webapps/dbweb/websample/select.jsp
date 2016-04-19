<%@page contentType="text/html; charset=Windows-31J"%>
<%@page import="java.util.List, websample.Account"%>

<html>
<body>


<table border="1">

<tr>
<th>ID
<th>NAME
<th>MONEY

<%
	List<Account> list = (List<Account>)request.getAttribute("list");
	for(int i=0; i<list.size(); i++){
		Account account = list.get(i);
%>
		<tr>
			<td><%=account.getId()%>
			<td><%=account.getName()%>
			<td><%=account.getMoney()%>	
<%
	}
%>

</table>


</body>
</html>
