<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="utf-8" name="viewport"
	content="width=device-width, initial-scale=1">

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
					<h4 style="text-align: center;" class="modal-title" >
					<spring:message code="label.title"></spring:message></h4>
				</div>
				<div class="modal-body">

					<form name="form" ng-submit="login()" AutoCompleteType="Disabled"
						autocomplete="off">

						<div class="form-group">
							<input style="margin-left: 25%; width: 50%" type="text"
								name="username" id="username" class="form-control"
								ng-model="loginForm.username" placeholder="Enter Username"
								required />
						</div>
						<div class="form-group">
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
									here!</b></a> <a style="margin-left: 35%;" type="button"
								ng-click="closeModal()" class="btn btn-link"><b>Cancel</b></a>
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

					<h4 style="text-align: center;" class="modal-title">REGISTARTION
						OF NEW USER</h4>
					<p style="color: red; font-size: 10pt; text-align: center;"
						ng-hide="regUserMssg">{{regUserResp}}</p>
				</div>
				<div class="modal-body">

					<form class="form-horizontal" name="form" ng-submit="register()"
						role="form">

						<div class="input-group">
							<span style="width: 200px;" class="input-group-addon"
								id="basic-addon1"> Username <span style="color: red">*</span>
							</span> <input ng-model="regusername" style="width: 400px;" type="text"
								class="form-control" aria-describedby="basic-addon1"
								required="required"
								placeholder="Enter Your Username. From 3 to 15 symbols required">
						</div>
						<br>
						<div class="input-group">
							<span style="width: 200px;" class="input-group-addon"
								id="basic-addon2"> First Name</span> <input
								ng-model="regfirstName" style="width: 400px;" type="text"
								class="form-control" aria-describedby="basic-addon2"
								placeholder="Enter Your Name">
						</div>
						<br>
						<div class="input-group">
							<span style="width: 200px;" class="input-group-addon"
								id="basic-addon3"> Last Name</span> <input
								ng-model="reglastName" style="width: 400px;" type="text"
								class="form-control" aria-describedby="basic-addon3"
								placeholder="Enter Your Last Name">
						</div>
						<br>
						<div class="input-group">
							<span style="width: 200px;" class="input-group-addon"
								id="basic-addon4"> Email <span style="color: red">*</span>
							</span> <input ng-model="regemail" style="width: 400px;" type="email"
								class="form-control" aria-describedby="basic-addon4"
								required="required" placeholder="Enter Your email address">
						</div>
						<br>
						<div class="input-group">
							<span style="width: 200px;" class="input-group-addon"
								id="basic-addon5"> Password <span style="color: red">*</span>
							</span> <input ng-model="regpassword" style="width: 400px;"
								type="password" class="form-control"
								aria-describedby="basic-addon5" required="required"
								placeholder="From 6 to 20 symbols. 1 Upper case and 1 number is required">
						</div>
						<br>
						<div class="input-group">
							<span style="width: 200px;" class="input-group-addon"
								id="basic-addon6"> Confirm Password <span
								style="color: red">*</span>
							</span> <input ng-model="regconfirmPassword" style="width: 400px;"
								type="password" class="form-control"
								aria-describedby="basic-addon6" required="required"
								placeholder="Please, confirm Your password">
						</div>
						<br>
						<div class="input-group">
							<span style="width: 200px;" class="input-group-addon"
								id="basic-addon7"> Gender <span style="color: red">*</span>
							</span> <select ng-model="reggender" style="width: 400px;"
								class="form-control" aria-describedby="basic-addon7" id="gender"
								name="gender" required="required">
								<option value="male">Male</option>
								<option value="female">Female</option>
							</select>
						</div>
						<br>

					</form>

					<button ng-click="register()" class="btn btn-info btn-md">Sign
						up</button>

					<button style="margin-left: 75%;" type="submit"
						class="btn btn-danger btn-md" ng-click="closeModal()">Cancel</button>

				</div>

			</div>
			<!-- End of Modal Content -->

		</div>
	</div>
	<!-- End of Registration Modal -->

	<!-- Modal UNDER_VERIFICATION USER -->
	<div id="myUnderVer" class="modal fade  col-md-6 col-md-offset-3"
		role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content" ng-controller="indexCntrl">
				<div class="modal-header">

					<h4 style="text-align: center;" class="modal-title">UNDER
						VERIFICATION</h4>

				</div>
				<div class="modal-body">

					<p style="text-align: center;">Your account is temporary unavailable due to its
						verification.</p>
					<p style="text-align: center;">We have sent You email with confirmation link.</p>
					<p style="text-align: center;">After You confirm Your registration You will be able to log
						in</p>

				</div>

				<a style="margin-left: 45%; margin-bottom: 5%" type="button" data-dismiss="modal"
					class="btn btn-danger btn-md">Cancel</a>
			</div>
			<!-- End of Modal Content -->

		</div>
	</div>
	<!-- End of UNDER_VERIFICATION Modal -->

	<!-- Modal BANNED USER -->
	<div id="myBanned" class="modal fade  col-md-6 col-md-offset-3"
		role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content" ng-controller="indexCntrl">
				<div class="modal-header">

					<h4 style="text-align: center;" class="modal-title">YOU'VE BEEN BANNED</h4>

				</div>
				<div class="modal-body">

					<p style="text-align: center;">The access to your account is temporary disabled</p>
					<p style="text-align: center;">Your account have been banned due to Your inappropriate behavior</p>
					<p style="text-align: center;">If there is some mistake, please contact with the administration</p>

				</div>

				<a style=" margin-bottom: 5%" type="button" data-dismiss="modal"
					class="btn btn-danger btn-md">Cancel</a>
				<a style="margin-left: 75%; margin-bottom: 5%" type="button" href=''
					class="btn btn-infj btn-md"> Contact with Admins</a>
			</div>
			<!-- End of Modal Content -->

		</div>
	</div>
	<!-- End of BANNED Modal -->
	
</body>
</html>