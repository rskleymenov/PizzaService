<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html>
<head>
<title>PizzaService</title>
<link rel="stylesheet"
	href="https://bootswatch.com/simplex/bootstrap.css">
<style>
th {
	text-align: center;
}
</style>
</head>
<body>
	<div class="container">
		<br> <br> <br>
		<div class="row">
			<div class="col-xs-12" style="font-size: 20px;">
				<table class="table table-striped table-hover text-center ">
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
							<td><spring:url value="/pizzas/${pizza.id}" var="updateUrl" />
								<button class="btn btn-warning"
									onclick="location.href='${updateUrl}'">Update</button></td>
							<td><spring:url value="/pizzas/${pizza.id}" var="deleteUrl" />
								<form:form method="POST" action="${deleteUrl}">
									<button class="btn btn-success" type="submit">Delete</button>
								</form:form></td>
					</c:forEach>
				</table>


				<div align="center">
					<spring:url value="/pizzas/add" var="addPizzaUrl" />
					<button style="width: 30%;" class="btn btn-success"
						onclick="location.href='${addPizzaUrl}'">Add pizza</button>
				</div>
				<div align="center">
					<br> <label style="color: red;">${msg}</label>
				</div>
			</div>
		</div>
	</div>
</body>
</html>