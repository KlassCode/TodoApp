<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Register</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>

<body>
	<jsp:include page="/common/header.jsp"></jsp:include>
	<br>
	<br>
	<br>
	<br>
	<div class="container-fluid col-md-8">

		<div class="alert alert-success center" role="alert"><p>${ NOTIFICATION }</p></div>
		
		<c:forEach items="${ErrorMessage}" var="error">

			<div class="alert alert-danger" role="alert">${error}</div>
			<br>
		</c:forEach>
		
		<form action="register" method="post">
			<div class="form-group row">
				<label for="colFormLabel" class="col-sm-2 col-form-label">First
					Name</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="colFormLabel"
						name="firstName">
				</div>
			</div>

			<div class="form-group row">
				<label for="colFormLabel" class="col-sm-2 col-form-label">Last
					Name</label>
				<div class="col-sm-10">
					<input type="text" class="form-control" id="colFormLabel"
						name="lastName">
				</div>
			</div>

			<div class="form-group row">
				<label for="colFormLabelLg"
					class="col-sm-2 col-form-label col-form-label-lg">UserName</label>
				<div class="col-sm-10">
					<input type="text" class="form-control form-control-lg"
						id="colFormLabelLg" name="userName">
				</div>
			</div>


			<div class="form-group row">
				<label for="colFormLabelLg"
					class="col-sm-2 col-form-label col-form-label-lg">Password</label>
				<div class="col-sm-10">
					<input type="password" class="form-control form-control-lg"
						id="colFormLabelLg" name="password">
				</div>
			</div>
			<button type="submit" name="btnRegister" class="btn btn-primary my-1">S'inscrire</button>
		</form>
	</div>
	<jsp:include page="common/footer.jsp"></jsp:include>
</body>

</html>