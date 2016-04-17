<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@page session="true"%>
<!DOCTYPE html>
<html >
<head>
		<meta charset="utf-8">
      <link rel="stylesheet" href="resources/bower_components/bootstrap/dist/css/bootstrap.min.css"/>
      <link href="resources/bower_components/bootstrap/dist/css/bootstrap.css" rel="stylesheet"/>
      <link rel="stylesheet" href="resources/css/mainStyleSheet.css"
         />
      <script	src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
   </head>
<body>

	<nav class="navbar navbar-inverse">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">Cross Games</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="#">Championship</a></li>
				<li><a href="#">All Games</a></li>
				<li><a href="#">Events</a></li>
				<li><a href="users">Users</a></li>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<c:url value="/logout" />"><span
						class="glyphicon glyphicon-log-in"></span> Logout </a></li>
			</ul>


		</div>
	</nav>

	<div class="container">
			<table class="table table-striped" id="clientTable">
			<tr>
				<th>First Name, Last Name</th>
				<th>Email</th>
				<th>PhoneNumber</th>
				<th>Address</th>
			</tr>
			<c:forEach items="${usersList}" var="user">
			<tr>

					<td><a href="clientVisasInfo.html?userId={{user.id}}">${user.lastName}
							${user.firstName}</a></td>
					<td>${user.email}</td>
					<td>${user.phoneNumber}</td>
					<td>${user.address.city},${user.address.street},
						${user.address.houseNumber}, ${user.address.roomNumber}</td>
				
			</tr>
			</c:forEach>
		</table>
		</div>


</body>
</html>