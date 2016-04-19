<%@page contentType="text/html; charset=Windows-31J"%>
<%@page import="java.util.List, java.sql.*"%>

<html>
<body>


RS‚Å

<table border="1">

<tr>
<th>ID
<th>NAME
<th>MONEY

<%
	ResultSet rs = (ResultSet)request.getAttribute("crs");
	while(rs.next()){
%>
		<tr>
			<td><%=rs.getInt("ID")%>
			<td><%=rs.getString("NAME")%>
			<td><%=rs.getInt("MONEY")%>	
<%
	}
%>

</table>


</body>
</html>
