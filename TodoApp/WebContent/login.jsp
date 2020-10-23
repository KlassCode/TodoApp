<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

</head>
<body>

	<jsp:include page="common/header.jsp"></jsp:include>
	<div class="container col-md-8 col-md-offset-3" style="overflow: auto">

		<h1>Login Form</h1>
		<form action="login" method="post">
			
			<c:if test="${ erreurConnexion != null }">
				<div class="alert alert-warning center" role="alert">${ erreurConnexion }</div>
			</c:if>
			
			<br>
			<c:if test="${ error != null }">
				<div class="alert alert-danger center" role="alert">${ error }</div>
			</c:if>
			
			<div class="form-group">
				<label for="uname">User Name:</label> <input type="text"
					class="form-control" id="username" placeholder="User Name"
					name="username" >
			</div>

			<div class="form-group">
				<label for="uname">Password:</label> <input type="password"
					class="form-control" id="password" placeholder="Password"
					name="password" >
			</div>


			<button type="submit" class="btn btn-primary">Submit</button>
		</form>
	</div>
	<jsp:include page="common/footer.jsp"></jsp:include>

</body>
</html>