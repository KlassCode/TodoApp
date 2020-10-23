<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Todo Management</title>
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">
</head>
<body>

	<header>
		<nav class="navbar navbar-expand-md navbar-dark	bg-primary">
			<div>
				<a href="#" class="navbar-brand"> Todo
					App</a>
			</div>

			<ul class="navbar-nav">
				<li><a href="<%=request.getContextPath()%>/list"
					class="nav-link">My Todos</a></li>
			</ul>

			<ul class="navbar-nav navbar-collapse justify-content-end">
				<li><a href="<%=request.getContextPath()%>/logout"
					class="nav-link">Logout</a></li>
			</ul>
		</nav>
	</header>

	<div class="container">
		<h3 class="text-center">List of Todos</h3>
		<hr>
		<div class="container text-right">

			<a href="<%= request.getContextPath() %>/new" class="btn btn-warning">Add
				Todo</a>
		</div>

		<br>
		<table class="table table-bordered">
			<thead>
				<tr>
					<th>Title</th>
					<th>Target Date</th>
					<th>Todo Status</th>
					<th>Actions</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="todo" items="${ todoList }">
					<tr>
						<td>${todo.title}</td>
						<td>${todo.targetDate}</td>
						<td>${todo.status}</td>
							
						<td><a href="edit?id=${todo.id}" class="btn btn-success">Edit</a>
							<a href="delete?id=${todo.id}" class="btn btn-danger">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
	
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>