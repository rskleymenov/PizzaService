<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

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
			<div class="col-xs-4"></div>
			<div class="col-xs-4">
				<form action="/mvc/login" method="POST">
					<div class="input-group input-group-lg">
						<span class="input-group-addon" id="sizing-addon1">@</span> 
						<input name="customerId"
							type="text" class="form-control" placeholder="Username"
							aria-describedby="sizing-addon1">
					</div>
					<br><br>
					<input style="width: 100%;" class="btn btn-success" type="submit" value="Submit" />
				</form>
			</div>
			<div class="col-xs-4"></div>
		</div>
	</div>
</body>