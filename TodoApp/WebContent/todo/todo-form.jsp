<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>Todo Management</title>

<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
	integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
	crossorigin="anonymous">

</head>

</head>
<body>
	<header>
		<nav class="navbar navbar-expand-md navbar-dark bg-primary">
			<div>
				<a href="https://github.com/KlassCode" class="navbar-brand">
					Todo App</a>
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
	<br>

	<div class="container col-md-5">
		<div class="card">
			<div class="card-body">

				<c:if test="${ errorInsert != null }">
					<div class="alert alert-warning center" role="alert">${ errorInsert }
					</div>
				</c:if>

				<c:if test="${ todo == null }">
					<form action="insert" method="post">
				</c:if>
				<c:if test="${ todo != null }">
					<form action="update" method="post">
				</c:if>

				<caption>
					<h2>
						<c:if test="${ todo != null }">
							Update Todo
						</c:if>
						<c:if test="${ todo == null }">
							Add Todo
						</c:if>
					</h2>
				</caption>

				<c:if test="${ todo != null }">
					<input type="hidden" value="<c:out value='${ todo.id }'></c:out>"
						name="id" />
				</c:if>

				<fieldset class="form-group">
					<label>Todo Title</label> <input type="text"
						value="<c:out value='${ todo.title }'></c:out>" name="title"
						  class="form-control">
				</fieldset>

				<fieldset class="form-group">
					<label>Todo Description</label> <input type="text"
						value="<c:out value='${ todo.description }'></c:out>"
						name="description" class="form-control" >
				</fieldset>

				<fieldset class="form-group">
					<label>Todo Status</label> <select name="isDone"
						class="form-control">
						
						<option value="false" <c:if test="${todo.status == false}">selected</c:if>>progress</option>
						<option value="true" <c:if test="${todo.status == true}">selected</c:if>>Complete</option>
					</select>
				</fieldset>

				<fieldset class="form-group">
					<label>Todo Target Date</label> <input type="date"
						value="<c:out value='${ todo.targetDate }'></c:out>"
						name="targetDate" class="form-control" required="required" >
				</fieldset>
				<button type="submit" class="btn btn-success">Save</button>
			</div>
		</div>
	</div>
	
	<jsp:include page="../common/footer.jsp"></jsp:include>
</body>
</html>