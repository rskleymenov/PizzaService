<%@ page session="false"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html>
<html>
<head>
<title>PizzaService</title>
<link rel="stylesheet"
	href="https://bootswatch.com/simplex/bootstrap.css">
</head>
<body>
	<div class="container">
		<br> <br> <br>
		<div class="row">
			<div class="col-xs-12">
				<spring:url value="/pizzas" var="pizzaSaveOrUpdate" />
				<form:form method="POST" modelAttribute="pizzaForm"
					action="${pizzaSaveOrUpdate}">
					<form:hidden path="id" />
					<table class="table table-striped table-hover text-center">
						<tr>
							<td>Name</td>
							<td><form:input class="form-control" path="name" /></td>
						</tr>
						<tr>
							<td>Price</td>
							<td><form:input class="form-control" path="price" /></td>
						</tr>
						<tr>
							<td>Type</td>
							<td><form:input class="form-control" path="type" /></td>
						</tr>
						<tr>
							<td colspan="2"><input style="width: 30%;" class="btn btn-success" type="submit" value="Submit" /></td>
						</tr>
					</table>
				</form:form>
			</div>
		</div>
	</div>
</body>
</html>