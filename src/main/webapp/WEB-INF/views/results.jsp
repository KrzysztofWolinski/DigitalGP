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
	    <tr>
	    	<td>${attributes[0].description}</td>
	    	<c:forEach items=${attributes} var="attribute" varStatus="poop">
		      <td><input type="checkbox" checked="${attribute.value} disabled="true" /></td>
	        </c:forEach>
	    </tr>
	  </c:forEach>
	</table>

</body>
</html>
