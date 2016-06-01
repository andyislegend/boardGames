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
<link rel="stylesheet" href="resources/css/users.css" />
<link rel="stylesheet" href="resources/css/loading.css" />

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
<script
	src="resources/bower_components/angular-translate/angular-translate.js"></script>
<script type="text/javascript" src="resources/js/localization.js"></script>
<script
	src="resources/bower_components/angular-translate-storage-cookie/angular-translate-storage-cookie.js"></script>
<script
	src="resources/bower_components/angular-cookies/angular-cookies.js"></script>

</head>
<body ng-app="indexModule" ng-controller="localizationController">
	<header>
		<nav class="navbar navbar-default">
			<div class="container">
				<div class="navbar-header">
					<a class="navbar-brand" href="index"><img alt="logo"
						src="resources/images/logo.png"
						style="height: 100px; weight: 100px;"></a>
				</div>

				<p style="font-size: 14pt;" class="navbar-text">
					<b translate="PROJECT_NAME"></b>
				</p>
				
				<ul class="nav navbar-nav navbar-right">
					<li><a type="button" class="btn btn-link" data-toggle="modal"
						data-target="#myModal" translate="LOGIN"></a></li>
					<li><a type="button" class="btn btn-link" data-toggle="modal"
						data-target="#myReg" translate="REGISTRATION"></a></li>
					<li><span class="btn btn-link"> <img id="flag"
							src="resources/images/gb.png" ng-click="changeLanguage('en')" />
							<img id="flag" src="resources/images/ua.png"
							ng-click="changeLanguage('ua')" /></span></li>
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
					<h4 style="text-align: center;" class="modal-title"
						translate="LOGINFORM_HEADER"></h4>
				</div>
				<div class="modal-body">
					<form name="form" ng-submit="login()" AutoCompleteType="Disabled"
						autocomplete="off">


						<div class="form-group">

							<input style="margin-left: 25%; width: 50%" type="text"
								name="username" id="username" class="form-control"
								ng-model="loginForm.username"
								placeholder="{{'LOGINFORM_USERNAME' | translate}}" required />
						</div>
						<div class="form-group">
							<input style="margin-left: 25%; width: 50%" type="password"
								name="password" id="password" class="form-control"
								ng-model="loginForm.password"
								placeholder="{{'LOGINFORM_PASSWORD' | translate}}" required />
						</div>

						<p style="color: red; font-size: 10pt; text-align: center;"
							id="incorrectLoginMessage" ng-show="loginCorrect"
							class="incortLebStyle">
							<b translate="INCORRECT_LOGIN_OR_PASS"></b>
						</p>
						<div class="form-actions">
							<button type="submit" ng-disabled="form.$invalid"
								class="btn btn-info btn-lg btn-block"
								style="width: 50%; margin-left: 25%" translate="LOGINFORM_ENTER">
							</button>
							<br> <a style="margin-left: 1%;" type="button"
								data-dismiss="modal" class="btn btn-link" data-toggle="modal"
								data-target="#myReg"> <b translate="DONT_HAVE_ACCOUNT">
							</b>
							</a> <a style="margin-left: 35%;" type="button"
								ng-click="closeModal()" class="btn btn-link"> <b
								translate="CANCEL"> </b></a>
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
					<h4 style="text-align: center;" class="modal-title"
						translate="REGFORM_HEADER"></h4>
					<p style="color: red; font-size: 10pt; text-align: center;"
						ng-hide="regUserMssg">{{regUserResp}}</p>
				</div>
				<div class="modal-body">

					<form class="form-horizontal" name="form" ng-submit="register()"
						role="form">

						<div class="input-group">
							<span style="width: 200px;" class="input-group-addon"
								id="basic-addon1"><span translate="REGFORM_USERNAME"></span><span
								style="color: red">*</span> </span> <input ng-model="regusername"
								style="width: 400px;" type="text" class="form-control"
								aria-describedby="basic-addon1" required="required"
								placeholder="{{'REGFORM_USERNAME_PLACEHOLDER' | translate}}" maxlength="9">
						</div>
						<br>
						<div class="input-group">
							<span style="width: 200px;" class="input-group-addon"
								id="basic-addon2"><span translate="REGFORM_FIRSTNAME"></span></span>
							<input ng-model="regfirstName" style="width: 400px;" type="text"
								class="form-control" aria-describedby="basic-addon2"
								placeholder="{{'REGFORM_FIRSTNAME_PLACEHOLDER' | translate}}" maxlength="100">
						</div>
						<br>
						<div class="input-group">
							<span style="width: 200px;" class="input-group-addon"
								id="basic-addon3"> <span translate="REGFORM_LASTNAME"></span></span>
							<input ng-model="reglastName" style="width: 400px;" type="text"
								class="form-control" aria-describedby="basic-addon3"
								placeholder="{{'REGFORM_LASTNAME_PLACEHOLDER' | translate}}" maxlength="100">
						</div>
						<br>
						<div class="input-group">
							<span style="width: 200px;" class="input-group-addon"
								id="basic-addon4"><span translate="REGFORM_EMAIL"></span><span
								style="color: red">*</span> </span> <input ng-model="regemail"
								style="width: 400px;" type="text" class="form-control"
								aria-describedby="basic-addon4" required="required"
								placeholder="{{'REGFORM_EMAIL_PLACEHOLDER' | translate}}" maxlength="150">
						</div>
						<br>
						<div class="input-group">
							<span style="width: 200px;" class="input-group-addon"
								id="basic-addon5"><span translate="REGFORM_PASSWORD"></span><span
								style="color: red">*</span> </span> <input ng-model="regpassword"
								style="width: 400px;" type="password" class="form-control"
								aria-describedby="basic-addon5" required="required"
								placeholder="{{'REGFORM_PASSWORD_PLACEHOLDER' | translate}}" maxlength="20">
						</div>
						<br>
						<div class="input-group">
							<span style="width: 200px;" class="input-group-addon"
								id="basic-addon6"><span
								translate="REGFORM_CONFIRMPASSWORD"></span><span
								style="color: red">*</span> </span> <input
								ng-model="regconfirmPassword" style="width: 400px;"
								type="password" class="form-control"
								aria-describedby="basic-addon6" required="required"
								placeholder="{{'REGFORM_CONFIRMPASSWORD_PLACEHOLDER' | translate}}" maxlength="20">
						</div>
						<br>
						<div class="input-group">
							<span style="width: 200px;" class="input-group-addon"
								id="basic-addon7"> <span translate="REGFORM_GENDER"></span><span
								style="color: red">*</span>
							</span> <select ng-model="reggender" style="width: 400px;"
								class="form-control" aria-describedby="basic-addon7" id="gender"
								name="gender" required="required">
								<option value="male" translate="REGFORM_GENDER_MALE"></option>
								<option value="female" translate="REGFORM_GENDER_FEMALE">Female</option>
							</select>
						</div>
						<br>

					</form>

					<button style="margin-left: 30%;" ng-click="register()" class="btn btn-info btn-md"
						translate="REGFORM_SIGNUP" ng-disabled="form.$invalid"></button>

					<button style="margin-left: 15%;" type="submit"
						class="btn btn-danger btn-md" ng-click="closeModal()" translate="CANCEL"></button>

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
			<div class="modal-content">
				<div class="modal-header">

					<h4 style="text-align: center;" class="modal-title" translate="UNDER_VERIFICATION_HEADER"></h4>

				</div>
				<div class="modal-body">

					<p style="text-align: center;" translate="UNDER_VERIFICATION_FIRST"></p>
					<p style="text-align: center;" translate="UNDER_VERIFICATION_SECOND"></p>
					<p style="text-align: center;" translate="UNDER_VERIFICATION_THIRD"></p>

				</div>

				<a style="margin-left: 45%; margin-bottom: 5%" type="button"
					data-dismiss="modal" class="btn btn-danger btn-md" translate="CLOSE"></a>
			</div>
			<!-- End of Modal Content -->

		</div>
	</div>
	<!-- End of UNDER_VERIFICATION Modal -->

	<!-- Modal BANNED USER -->
	<div id="myBanned" class="modal fade  col-md-6 col-md-offset-3"
		role="dialog">
		<div class="modal-dialog" ng-controller="sendEmailToUnban">

			<!-- Modal content-->
			<div class="modal-content" >
				<div class="modal-header">

					<h4 style="text-align: center;" class="modal-title" translate="BAN_MODAL_HEADER"></h4>

				</div>
				<div class="modal-body">

					<p style="text-align: center;" translate="BAN_MODAL_FIRST"></p>
					<p style="text-align: center;" translate="BAN_MODAL_SECOND"></p>
					<p style="text-align: center;" translate="BAN_MODAL_THIRD"></p>

				</div>

				<a style="margin-left: 45%; margin-bottom: 5%" type="button" data-dismiss="modal"
					class="btn btn-danger btn-md" translate="CLOSE"></a>
				<a style="margin-left: 75%; margin-bottom: 5%" type="button" data-toggle="modal" 
					class="btn btn-danger btn-md" translate="CONTACT_WITH_ADMINS" 
					data-target="#sendToUnban"></a>
			</div>
			<!-- End of Modal Content -->

		</div>
	</div>
	<!-- End of BANNED Modal -->
	<div id="sendToUnban" class="modal fade  col-md-6 col-md-offset-3"
		role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content" ng-controller="registerCntrl">
				<div class="modal-header">
				</div>
				<div class="modal-body">
				<form action="mailto:you@yourdomain" method="get" enctype="text/plain">
  					<p>Name: <input type="text" name="name" /></p>
  					<p>Email: <input type="text" name="email" /></p>
  					<p>Comments:<br />
  					<textarea cols="30" rows="20" name="comments"></textarea></p>
  					<p><input type="submit" name="submit" value="Send" />
  					<input type="reset" name="reset" value="Clear Form" /></p>
				</form>
				</div>
			</div>
		</div>
	</div>
	<!-- Modal ON REGISTRATION SUCCESS -->
	<div id="myRegSuccess" class="modal fade  col-md-6 col-md-offset-3"
		role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->
			<div class="modal-content">
				<div class="modal-header">

					<h4 style="text-align: center;" class="modal-title" translate="ON_REGISTRATION_HEADER"></h4>

				</div>
				<div class="modal-body">

					<p style="text-align: center;" translate="ON_REGISTRATION_FIRST"></p>
					<p style="text-align: center; color: red;" translate="ON_REGISTRATION_SECOND"></p>
					<p style="text-align: center;" translate="ON_REGISTRATION_THIRD"></p>

				</div>

				<a style="margin-bottom: 5%; margin-left: 43%;" type="button"
					data-dismiss="modal" class="btn btn-info btn-lg" translate="CLOSE"> </a>
			</div>
			<!-- End of Modal Content -->

		</div>
	</div>
	<!-- End of ON REGISTRATION SUCCESS -->

	<!-- Modal LOADING -->
	<div id="myLoading" class="modal fade  col-md-6 col-md-offset-3"
		role="dialog">
		<div class="modal-dialog">

			<!-- Modal content-->

					<div class="loading">
						<div class="intro-stars"></div>

						<div class="ufo light"></div>
						<div class="ufo">
							<div class="loading-small-cloud"></div>
							<div class="loading-small-cloud reverse"></div>
							<div class="small-ghost"></div>
						</div>

						<div class="loading-text" translate="LOADING"></div>
					</div>

			<!-- End of Modal Content -->

		</div>
	</div>
	<!-- End of LOADING -->

</body>
</html>