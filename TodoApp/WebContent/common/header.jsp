<header>
	<nav class="navbar navbar-expand-lg fixed-top navbar-light bg-primary">
		<a class="navbar-brand" href="#">TodoApp</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup"
			aria-expanded="false" aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarNavAltMarkup">
			<div class="navbar-nav justify-content-end">
				<a class="nav-link text-light" href="<%= request.getContextPath() %>/register">Sign Up <span class="sr-only">(current)</span></a>
				<a class="nav-link text-light" href="<%= request.getContextPath() %>/login">Sign in</a> 
			</div>
		</div>
	</nav>
</header>
