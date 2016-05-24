<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

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
		<div class="row">
			<div class="col-xs-12">
				<form:form method="post" action="submitorder"
					modelAttribute="orderForm">
					<h2>Customer info</h2>
					<table class="table table-striped table-hover text-center">
						<tr>
							<td>#ID</td>
							<td>${customer.id}</td>
						</tr>
						<tr>
							<td>Name</td>
							<td>${customer.name}</td>
						</tr>
						<tr>
							<td>Surname</td>
							<td>${customer.surname}</td>
						</tr>
					</table>

					<h2>Customer addresses</h2>
					<div class="form-group">
						<form:select class="form-control" path="deliveryAddress"
							items="${customer.addresses}" itemValue="id"></form:select>
					</div>
					<h2>Pizzas</h2>
					<table class="table table-striped table-hover text-center">
						<thead>
							<tr>
								<td>id</td>
								<td>Name</td>
								<td>Price</td>
								<td>Type</td>
								<td>Select</td>
							</tr>
						</thead>
						<c:forEach items="${pizzas}" var="item">
							<tr>
								<td>${item.id}</td>
								<td>${item.name}</td>
								<td>${item.price}</td>
								<td>${item.type}</td>
								<td><form:input path="pizzaMap[${item.id}]" value="0" /></td>
							</tr>
						</c:forEach>
					</table>

					<INPUT style="width: 100%;" class="btn btn-success" TYPE="submit" VALUE="Submit order" />
					
					<h2>Customer orders</h2>
					<table class="table table-striped table-hover text-center">
						<tr>
							<td>id</td>
							<td>State</td>
							<td>Price</td>
							<td>Discount</td>
							<td>Address</td>
							<td>Pizzas</td>
						</tr>
						<c:forEach items="${customer.orders}" var="item">
							<tr>
								<td>${item.id}</td>
								<td>${item.state}</td>
								<td>${item.price}</td>
								<td>${item.discount}</td>
								<td>${item.address.street}${item.address.city}</td>
								<td><select style="width: 350px;">
										<c:forEach items="${item.pizzasInOrder}" var="pizzasItem">
											<option>Number: ${pizzasItem.value} Name:
												${pizzasItem.key.name} Price: ${pizzasItem.key.price} Type:
												${pizzasItem.key.type}</option>
										</c:forEach>
								</select></td>
							</tr>
						</c:forEach>
					</table>
				</form:form>
			</div>
		</div>
	</div>

</body>