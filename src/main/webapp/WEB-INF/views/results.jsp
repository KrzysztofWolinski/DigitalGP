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
	  <c:forEach items="${list}" var="attribute" varStatus="loop">
	    <tr>
	      <td>${attribute.description}</td>
	      <td><input type="checkbox" checked="${attribute.value}" /></td>
	    </tr>
	  </c:forEach>
	</table>

</body>
</html>
