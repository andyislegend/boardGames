<!DOCTYPE html>
<html>
<head>

<link rel="stylesheet"
	href="resources/bower_components/bootstrap/dist/css/bootstrap.min.css"
	crossorigin="anonymous" />
<link href="resources/bower_components/bootstrap/dist/css/bootstrap.css"
	rel="stylesheet" crossorigin="anonymous" />
<link rel="stylesheet"
	href="resources/bower_components/bootstrap/dist/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">
<link rel="stylesheet" href="resources/css/index.css" />
<link rel="stylesheet" href="resources/css/fonts.css" />

<script type="text/javascript"
	src="resources/bower_components/angular/angular.js"></script>
<script src="resources/bower_components/jquery/dist/jquery.js"></script>
<script
	src="resources/bower_components/bootstrap/dist/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>
<script type="text/javascript"
	src="resources/bower_components/angular-ui-bootstrap-bower/ui-bootstrap-tpls.js"></script>
<script src="resources/js/index.js"></script>
<script type="text/javascript" src="resources/js/registration.js"></script>

</head>
<body ng-app="indexModule">
	<header>
		<nav class="navbar navbar-default">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="index"><img alt="logo"
						src="resources/images/logo.png"
						style="height: 100px; weight: 100px;"></a>
				</div>

				<!-- <ul class="nav navbar-nav navbar-center">
					<li><a href="#"><i class="fa fa-home"></i> Home</a></li>
					<li><a href="#allGames"><i class="fa fa-home"></i> All
							Games</a></li>
					<li><a href="#about"><i class="fa fa-shield"></i> Events</a></li>
					<li><a href="#tournaments"><i class="fa fa-comment"></i>
							Tournaments</a></li>
				</ul> -->
				<ul class="nav navbar-nav navbar-right">
					<li><a type="button" class="btn btn-link" data-toggle="modal"
						data-target="#myModal">Login</a></li>
					<li><a type="button" class="btn btn-link" data-toggle="modal"
						data-target="#myReg"> Registration</a></li>
				</ul>
			</div>
		</nav>
	</header>

	<!-- Carousel -->
	<div class="col-md-8 col-md-offset-2">
		<div id="myCarousel" class="carousel slide" data-ride="carousel">

			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="1"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
				<li data-target="#myCarousel" data-slide-to="3"></li>
				<li data-target="#myCarousel" data-slide-to="4"></li>
				<li data-target="#myCarousel" data-slide-to="5"></li>
				<li data-target="#myCarousel" data-slide-to="6"></li>
			</ol>

			<!-- Wrapper for slides -->
			<div class="carousel-inner" role="listbox">

				<div class="item active">
					<img src="resources/images/index-carousel/image0.jpg">
				</div>

				<div class="item">
					<img src="resources/images/index-carousel/image1.jpg">
				</div>

				<div class="item">
					<img src="resources/images/index-carousel/image2.jpg">
				</div>

				<div class="item">
					<img src="resources/images/index-carousel/image3.jpg">
				</div>

				<div class="item">
					<img src="resources/images/index-carousel/image4.jpg">
				</div>

				<div class="item">
					<img src="resources/images/index-carousel/image5.jpg">
				</div>

				<div class="item">
					<img src="resources/images/index-carousel/image6.jpg">
				</div>

			</div>

			<!-- Left and right controls -->
			<a class="left carousel-control" href="#myCarousel" role="button"
				data-slide="prev"> <span
				class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
				<span class="sr-only">Previous</span>
			</a> <a class="right carousel-control" href="#myCarousel" role="button"
				data-slide="next"> <span
				class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
				<span class="sr-only">Next</span>
			</a>
		</div>
	</div>
	<!-- Carousel End -->


	<!-- Login Modal -->
	<div id="myModal" class="modal fade  col-md-6 col-md-offset-3"
		role="dialog" ng-controller="loginCntrl">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">
					<h4 style="text-align: center;" class="modal-title">Login with
						Your Username and Password</h4>
				</div>
				<div class="modal-body">

					<form name="form" ng-submit="login()" AutoCompleteType="Disabled"
						autocomplete="off">

						<div class="form-group"
							ng-class="{ 'has-error': form.username.$dirty && form.username.$error.required }">
							<input style="margin-left: 25%; width: 50%" type="text"
								name="username" id="username" class="form-control"
								ng-model="loginForm.username" placeholder="Enter Username"
								required />
						</div>
						<div class="form-group"
							ng-class="{ 'has-error': form.password.$dirty && form.password.$error.required }">
							<input style="margin-left: 25%; width: 50%" type="password"
								name="password" id="password" class="form-control"
								ng-model="loginForm.password" placeholder="Enter Password"
								required />
						</div>

						<p style="color: red; font-size: 10pt; text-align: center;"
							id="incorrectLoginMessage" ng-show="loginCorrect"
							class="incortLebStyle">
							<b>INCORRECT LOGIN OR PASSWORD!</b>
						</p>
						<div class="form-actions">
							<button type="submit" ng-disabled="form.$invalid"
								class="btn btn-info btn-lg btn-block"
								style="width: 50%; margin-left: 25%">Login</button>
							<br> <a style="margin-left: 1%;" type="button"
								data-dismiss="modal" class="btn btn-link" data-toggle="modal"
								data-target="#myReg"><b>Don't Have an Account? Click
									here!<b></a> <a style="margin-left: 35%;" type="button"
								data-dismiss="modal" class="btn btn-link"><b>Cancel</b></a>
						</div>
					</form>


				</div>

			</div>
			<!-- Modal Content End -->
		</div>
		<!-- End of LOGIN Modal -->
	</div>

	<!-- Modal Registration -->
	<div id="myReg" class="modal fade  col-md-6 col-md-offset-3"
		role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content" ng-controller="registerCntrl">
				<div class="modal-header">
					<button ng-click="register()" class="btn btn-info">Sign up</button>
					<a style="margin-left: 15%; font-size: 12pt;" class="btn disabled">REGISTARTION
						OF NEW USER</a>
					<button style="margin-left: 14%;" type="submit"
						class="btn btn-danger" ng-click="closeModal()">Cancel</button>
					<span style="color: red; font-size: 10pt; margin-left: 5%"
						ng-hide="regUserMssg"> {{regUserResp}} </span>
				</div>
				<div class="modal-body">

					<div class="form-group">
						<label>Username</label><span style="color: red">*</span> <input
							ng-model="regusername" type="text" class="form-control"
							required="required" />

					</div>

					<div class="form-group">
						<label>Email</label><span style="color: red">*</span> <input
							ng-model="regemail" type="email" class="form-control"
							required="required" />

					</div>

					<div class="form-group">
						<label>First name</label> <input ng-model="regfirstName"
							type="text" class="form-control" />
					</div>

					<div class="form-group">
						<label>Last name</label> <input ng-model="reglastName" type="text"
							class="form-control" />

					</div>

					<div class="form-group">
						<label>Password</label><span style="color: red">*</span> <input
							ng-model="regpassword" type="password" class="form-control"
							required="required" />

					</div>

					<div class="form-group">
						<label>Confirm Password</label><span style="color: red">*</span> <input
							ng-model="regconfirmPassword" type="password"
							class="form-control" required="required" />

					</div>

					<div class="form-group">
						<label>Gender</label> <select ng-model="reggender"
							class="form-control" id="gender" required="required">
							<option value="male">Male</option>
							<option value="female">Female</option>
						</select>

					</div>

					<div class="row">
						<div class="form-group col-md-12" style="margin-left: 1%;">
							<div class="col-md-3">

								Choose foto to upload : <input type="file" name="fileUpload"
									class="fileUpload" accept="image/*" onchange="loadFile(event)">
							</div>
							<div class="col-md-4">
								<img id="avatar" src="#"
									class="img-circle dropdown-toggle profile-image" />
							</div>
						</div>
					</div>

				</div>
			</div>
			<!-- End of Modal Content -->

		</div>
	</div>
	<!-- End of Registration Modal -->

</body>
</html>