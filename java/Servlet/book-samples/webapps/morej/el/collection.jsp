<%@page contentType="text/html; charset=Windows-31J"%>
<%@page import="java.util.*"%>

<html>
<body>

<h2>ELのテスト</h2>

<%
   List list = new ArrayList();
   list.add("list0");
   list.add("list1");
   list.add("list2");

   String[] array = new String[]{
      "array0",
      "array1",
      "array2"
   };

   Map map = new HashMap();
   map.put("keyA", "mapA");
   map.put("keyB", "mapB");
   map.put("keyC", "mapC");
   
   request.setAttribute("list", list);
   request.setAttribute("array", array);
   request.setAttribute("map", map);
%>


<p>リストから ${list[2]}

<p>配列から ${array[2]}

<p>マップから ${map["keyA"]} / これもOK ${map.keyA}


</body>
</html>
