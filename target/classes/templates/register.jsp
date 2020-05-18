<!DOCTYPE html>
<html 
	xmlns="http://www.w3.org/1999/xhtml" 
	xmlns:th="http://www.thymeleaf.org" 
	xmlns:sec="http://www.thymeleaf.org/thymeleaf-extras-springsecurity3"
	xmlns:layout="http://www.ultraq.net.nz/thymeleaf/layout">

	<head>
		<title>Registration Form</title>
		<!-- link rel="stylesheet" type="text/css" th:href="@{/css/registration.css}" /-->
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
		<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	</head>
	
	<body style="background-color: #ededed;">
		<div style="background-color: #343a40!important;; height: 80px;"></div>
		<div class="container-fluid" style="margin-top: 30px;">
	
			<div class="row col-lg-4 col-lg-offset-4" style="margin-top: 10px; background-color: #fff; padding: 20px; border: solid 1px #ddd;">
				<h1 style="text-align: center">E-Hospital</h1>
				<form autocomplete="off" action="#" th:action="@{/register}" method="post" class="form-signin" role="form">
					<h3 class="form-signin-heading" style="font-size:30px; text-align: center">Registration Form Medical Personnel</h3>
					<div class="form-group">
						<div class="">
							<input type="text" placeholder="Name" class="form-control" name="name" id="name" style="font-size:25px"/>
						</div>
					</div>
					<div class="form-group">
						<div class="">
							<input type="text" placeholder="Email" class="form-control" name="email" id="email" style="font-size:25px"/>
						</div>
					</div>
					<div class="form-group">
						<div class="">
							<input type="password" placeholder="Password" class="form-control" name="password" id="password" style="font-size:25px"/>
						</div>
					</div>
					<div class="form-group">
						<div class="">
							<input type="password" placeholder="Repeat Password" class="form-control" style="font-size:25px"/>
						</div>
					</div>
					<div class="form-group">
						<div class="">
							<input type="text" placeholder="Phone Number" class="form-control" name="phoneNumber" id="phoneNumber" style="font-size:25px"/>
						</div>
					</div>
					<div class="form-group">
						<div class="">
							<input type="text" placeholder="Age" class="form-control" name="age" id="age" style="font-size:25px"/>
						</div>
					</div>
					<div class="form-group">
						<div class="">
							<input type="text" placeholder="Specialization" class="form-control" name="specialization" id="specialization" style="font-size:25px"/>
						</div>
					</div>
					<div class="form-group">
						<div class="" style="text-align: center">
							<button type="submit" class="btn btn-primary btn-block"style="font-size:25px">Register Medical Personnel</button>
							<br>
							<a href="/login" style="font-size:25px">Back to Login</a>
						</div>
					</div>
					<span th:utext="${successMessage}"></span>
				</form>
			</div>
		</div>
	</body>
</html>