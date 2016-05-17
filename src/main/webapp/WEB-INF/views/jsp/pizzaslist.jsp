<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>

<head>
<title>PizzaService</title>
</head>

<body>
${msg}
	<table>
		<thead>
			<tr>
				<th>#ID</th>
				<th>Name</th>
				<th>Price</th>
				<th>Type</th>
				<th>Update</th>
				<th>Delete</th>
			</tr>
		</thead>

		<c:forEach var="pizza" items="${pizzas}">
			<tr>
				<td>${pizza.id}</td>
				<td>${pizza.name}</td>
				<td>${pizza.price}</td>
				<td>${pizza.type}</td>
				<td>
					<spring:url value="/pizzas/${pizza.id}/update" var="updateUrl" />
					<button onclick="location.href='${updateUrl}'">Update</button>
					
				</td>
				<td>
					<spring:url value="/pizzas/${pizza.id}/delete" var="deleteUrl" /> 
				 	<form:form method="POST" action="${deleteUrl}">
						<button type="submit"> Delete </button>
					</form:form>
				</td>
		</c:forEach>
	</table>
	<spring:url value="/pizzas/add" var="addPizzaUrl" /> 
	<button onclick="location.href='${addPizzaUrl}'">Add pizza</button>
</body>
</html>