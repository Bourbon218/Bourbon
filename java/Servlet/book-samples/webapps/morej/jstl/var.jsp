<%@page contentType="text/html; charset=Windows-31J"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="el.*, java.util.*"%>

<HTML>
<BODY>


<H2>c:set</H2>


<H3>例1</H3>

<c:set var="name1" value="value1"/>

${name1}


<H3>例2</H3>

<c:set var="name1">
value1
</c:set>

${name1}


<H3>例3</H3>

<c:set var="name1" value="value1" scope="request"/>

${name1}


<H3>例4</H3>

<%
Object o = new java.util.Date();
request.setAttribute("o",o);
%>

<c:set var="name2" value="<%=o%>"/>





<H3>例5</H3>

<%
User user = new User();
request.setAttribute("user", user);
%>

<c:set target="${user}" property="name" value="john"/>

${user.name}




<H2>c:remove</H2>


<c:set var="name1" value="value1" scope="request"/>
<c:remove var="name1" scope="request"/>

${requestScope.name1}
<!--pageスコープは上でセットされたのがまだある-->



</BODY>
</HTML>
