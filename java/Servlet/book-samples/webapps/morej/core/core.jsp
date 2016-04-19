<%@page contentType="text/html; charset=Windows-31J"%>

<HTML>
<BODY>

<H1>JSPのコア要素</H1>

<H2>スクリプトレット</H2>

<%
for(int i=0; i<10; i++){
    out.println("count:" + i + "<br>");
}
%>


<H2>式</H2>

<%="hello" %>

<%= 3 * 4 * 5 %>


<H2>宣言</H2>

<%!
  String message;
%>

<%!
  int add(int a, int b){
     return a + b;
  }
%>


<%
message = "foo";
out.println(message);
%>

<P>

<%= add(1, 2) %>



<H2>コメント</H2>

<%--JSPコメント--%>

<!--HTMLコメント-->


<H2>EL</H2>

<%
   java.util.Map map = new java.util.HashMap();
   map.put("key1","hoge");
   request.setAttribute("map", map);
%>

${map.key1}



</BODY>
</HTML>
