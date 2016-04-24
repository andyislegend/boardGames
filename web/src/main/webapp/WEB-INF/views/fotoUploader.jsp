<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet"
	href="resources/bower_components/bootstrap/dist/css/bootstrap.min.css"
	crossorigin="anonymous" />
<link href="resources/bower_components/bootstrap/dist/css/bootstrap.css"
	rel="stylesheet" crossorigin="anonymous" />
<link
	href="resources/bower_components/bootstrap/dist/css/bootstrap-theme.min.css"
	rel="stylesheet" crossorigin="anonymous" />
<link rel="stylesheet" href="resources/css/mainStyleSheet.css" />
<link rel="stylesheet" href="resources/css/main.css" />

<script type="text/javascript"
	src="resources/bower_components/angular/angular.js"></script>
<script src="resources/bower_components/jquery/dist/jquery.min.js"></script>
<script type="text/javascript"
	src="resources/bower_components/bootstrap/dist/js/bootstrap.min.js"></script>
</head>
<body>
<div ng-controller = "CreateGameCtrl">
<form enctype="multipart/form-data" action="uploadFile" method="POST"> 
    Choose foto to upload : 
    <input type="file" name="fileUpload" class="fileUpload">
    <br>
    <input type="text" placeholder="Name of the foto" name="imageName">
    <input type="submit" value="Upload">
  </form>
  <br>
  <img src="http://localhost/ava.png">
</body>
</html>