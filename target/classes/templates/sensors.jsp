<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1">
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/css/bootstrap.min.css">

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.css">
<link rel="stylesheet"
	href="https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.1/jquery-ui.theme.min.css">
<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
  src="https://code.jquery.com/ui/1.12.1/jquery-ui.min.js"
  integrity="sha256-VazP97ZCwtekAsvgPBSUwPFKdrwD3unUfSGVYrahUqU="
  crossorigin="anonymous"></script>
<!-- Popper JS -->
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.4.1/js/bootstrap.min.js"></script>
<style>
body {
	font-family: Arial, Helvetica, sans-serif;
	margin: 0;
}

html {
	box-sizing: border-box;
}

*, *:before, *:after {
	box-sizing: inherit;
}

.card {
	box-shadow: 0 4px 8px 0 rgba(0, 0, 0, 0.2);
	margin: 8px;
}

.about-section {
	padding: 5px;
	text-align: center;
	background-color: #337ab7;
	color: white;
}

.container {
	padding: 0 16px;
}
.navbar-brand{
    color: white !important;
    font-size: 2.9em !important;
    
}
#navbarNav {
    color: white !important;
    font-size: 1.9em !important;
    
    }
.container::after, .row::after {
	content: "";
	clear: both;
	display: table;
}
 .navbar {
  min-height: 80px;
}
.title {
	color: grey;
}

.card:hover {
	background-color: #DCDCDC;
}

a.custom-card, a.custom-card:hover {
	color: inherit;
	text-decoration: none;
}
</style>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	  <a class="navbar-brand" href="/home">E-Hospital</a>
	  
	  <div class="collapse navbar-collapse" id="navbarNav">
	    <ul class="navbar-nav">
	      <li class="nav-item ">
	        <a class="nav-link" href="/home">Patients </a>
	      </li>
	      <li class="nav-item active">
	        <a class="nav-link" href="/sensors">Sensors </a>
	      </li>
	      <li class="nav-item">
	        	      <a class="nav-link" href="/admin"
							th:if='${user.getRole().equals("Admin")}'>Admin Page</a>
	      </li>
	    </ul>

	  </div>
		<form class="form-inline" action="search">
			<input class="form-control mr-sm-2" type="search" id="search"
				placeholder="Search" aria-label="Search">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Search</button>
		</form>
		
		<form class="form-inline" action="logout">
			<button class="btn btn-outline-success my-2 my-sm-0" type="submit">Logout</button>
		</form>
	</nav>

	<h1 style="text-align: center">Sensors</h1>
	<div class="container">
		<div class="row">
				
				<table id="sensorsTable" class="table table-light">
	                <thead>
		                <tr>
		                    <th>Type</th>
		                    <th>Description</th>
		                    <th>Assigned Doctor</th>
		                    <th>Patient</th>
		                </tr>
	                </thead>
	                
	                <tbody>
                            <tr th:each="sensor : ${sensors}">
                                <td th:text="${sensor.getType()}"></td>
                                <td th:text="${sensor.getDescription()}"></td>
                                <td th:text="${sensor.getAssignedDoctor().getName()}"></td>
                                <td th:text="${sensor.getPatient().getName()}"></td>
                            </tr>
                    </tbody>
                </table>
	
		</div>

		<h1 style="text-align: center">Add new Sensor</h1>

		<div class="row" style="padding-bottom: 100px">

			<form class="col-md-12" method="POST"
				th:action="@{/sensors}">
				<div class="form-group" style="text-align: center">
					
					<input class="form-check-input position-static" type="text" id="type" name="type" placeholder="Sensor Type"><br>
					<input class="form-check-input position-static" type="text" id="description" name="description" placeholder="Sensor Description"><br>
					
					<select id="patient" name="patient">
					    <option th:each="patient : ${patients}" th:value="${patient.getId()}" th:text="${patient.getName()}">
					    </option>
					</select>
					
				</div>
				<button type="submit" class="btn btn-primary col-md-12">Submit</button>
			</form>

		</div>
	</div>
</body>
</html>