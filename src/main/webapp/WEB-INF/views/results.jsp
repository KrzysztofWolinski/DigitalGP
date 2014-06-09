<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Results</title>
</head>
<body>
<h1>
	Your diagnose:
</h1>

	<table>
	  <c:forEach items="${result_list}" var="attributes" varStatus="loop">
    	<c:forEach items="${attributes}" var="attribute" varStatus="loop">
    		<tr>
	    		<td>${attribute.description}</td>
		     	<td>"${attribute.value}"</td>
	     	</tr>
        </c:forEach>
	  </c:forEach>
	</table>

</body>
</html>
