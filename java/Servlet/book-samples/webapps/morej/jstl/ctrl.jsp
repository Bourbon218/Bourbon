<%@page contentType="text/html; charset=Windows-31J"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="el.*, java.util.*"%>

<HTML>
<BODY>


<H2>c:if</H2>

<H3>例</H3>

<c:set var="age" value="8"/>

<c:if test="${age < 20}">
20未満です
</c:if>


<H3>例</H3>


<c:if test="${age < 20}" var="result"/>
${result}



<H2>c:choose</H2>

<H3>例</H3>

<c:choose>
    <c:when test="${age >= 20}">
		成人
    </c:when>
    <c:when test="${age >= 10}">
    	10台
    </c:when>
    <c:otherwise>
    	子供
    </c:otherwise>
</c:choose>



<H2>c:forEach</H2>


<H3>例</H3>

<%
{
   List list = new ArrayList();
   list.add("aaa");
   list.add("bbb");
   list.add("ccc");
   request.setAttribute("list", list);
}
%>

<c:forEach var="item" items="${list}">
	${item}
</c:forEach>


<H3>例</H3>

<c:forEach var="item" items="${list}" varStatus="status">
	${status.index} 
	${status.current} 
	${item}
</c:forEach>



<H3>例</H3>

<%
{
   List list = new ArrayList();
   list.add(new User("太郎",true));
   list.add(new User("次郎",true));
   list.add(new User("花子",false));
   request.setAttribute("list",list);
}
%>

<UL>
<c:forEach var="user" items="${list}">
	<LI>${user.name}
</c:forEach>
</UL>


<H3>例</H3>

<%
Map map = new HashMap();
map.put("aaa","AAA");
map.put("bbb","BBB");
request.setAttribute("map", map);
%>

<c:forEach var="item" items="${map}">
	${item.key}
	${item.value}
</c:forEach>



<H2>c:forTokens</H2>


<%
String text = "aaa,bbb,ccc";
request.setAttribute("text",text);
%>
<c:forTokens var="item" items="${text}" delims=", ">
	${item}
</c:forTokens>


</BODY>
</HTML>
