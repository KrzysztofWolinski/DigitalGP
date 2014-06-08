<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Input</title>
</head>
<body>
<h1>
	Input
</h1>

<p>${buzka}</p>

<form action="input.jsp" method="POST">
	<h1>Please select data for the diagnose:</h1>
	<table>
	  <c:forEach items="${list}" var="attribute" varStatus="loop">
	    <tr>
	      <td>${attribute.description}</td>
	      <td><input id="attribute.description" type="checkbox"/></td>
	    </tr>
	  </c:forEach>
	</table>
	<input type="submit" value="Send"/>
</form>

</body>
</html>

