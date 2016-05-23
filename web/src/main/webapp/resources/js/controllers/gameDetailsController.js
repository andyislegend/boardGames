angular.module('homeApp').controller('getGameDetailedInfoController', function($scope, $route, $http, $rootScope, $filter, $timeout, ngTableParams) {
	
		$scope.$on('broadcastingGameId', function(event, data) {
			$scope.currentGameId = data;
		});   
		
		$scope.$on('sharingUserGamesOfGame',
			function(event, data) {
			$scope.userGamesOfGameTable = new ngTableParams({
				page: 1,
				count: 8
				}, {
				    total: data.length, 
				    getData: function ($defer, params) {	 
				    $scope.userGamesOfGameDisplay = params.sorting() ? 
				      	$filter('orderBy')(data, params.orderBy()) 
				        : data;
				    $scope.userGamesOfGameDisplay = params.filter() ? 
				       	$filter('filter')($scope.userGamesOfGameDisplay, params.filter()) 
				        : $scope.userGamesOfGameDisplay;
				    $scope.userGamesOfGameDisplay = $scope.userGamesOfGameDisplay.slice((params.page() - 1) 
				         * params.count(), params.page() * params.count());
				    $defer.resolve($scope.userGamesOfGameDisplay);
				}
			});
		});

		$scope.ratingClick = function(param) {
			
			$http({
				method : "POST",
				url : 'calculateRatings/' + $scope.currentGameId + '/' + param,
			}).then(function mySucces(response) {
				$scope.$emit('refreshingGameDetails', $scope.currentGameId);
			}, function myError(response) {
				
			});
		};

		$scope.ratingHover = function(param) {
			$scope.hoverRating = param;
			if (param >= 0 && param <= 1)
				$scope.hoverRatingText = "Bad as hell";
			else if (param >= 2 && param <= 3)
				$scope.hoverRatingText = "Not worth to be mensioned";
			else if (param >= 4 && param <= 5)
				$scope.hoverRatingText = "Could be better";
			else if (param >= 6 && param <= 7)
				$scope.hoverRatingText = "Pretty good";
			else if (param >= 8 && param <= 9)
				$scope.hoverRatingText = "Awesome thing";
			else $scope.hoverRatingText = "Must have!";
		};

		$scope.ratingLeave = function(param) {
			$scope.hoverRating = param + '*';
		};

		// comment
		$scope.gameuserId = 0;
		$scope.isShowComment = false;
		$scope.showComments = function(id) {

			$scope.gameuserId = id;
			$scope.isShowComment = !$scope.isShowComment
			$scope.commentForGame = [];

			$http.get('getCommentsForGame/' + id).then(
					function(result) {
						$scope.commentForGame = result.data;
					});

			if (document.getElementById("UserGameNum" + id).className === "glyphicon glyphicon-envelope") {
				$http.put(
						"updateCountOfComment/" + id + "/"
								+ $rootScope.NN).then(
						function(result) {
						});
				document.getElementById("UserGameNum" + id).className = "glyphicon glyphicon-comment";
			}
		}

		$scope.list = [];
		$scope.submit = function() {
		var comment = {
			"gameID" : '' + $scope.gameuserId,
			"commentText" : $scope.comment,
			"username" : "",
			"date" : new Date()
		};
		$http({
			method : 'POST',
			url : 'newComment',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : comment
		}).then(function successCallback(response) {
				$scope.list.push(response.data);	
				
		}, function errorCallback(response) {
		})
		$timeout(function() {
			for (var i = 0; i < $rootScope.getAllUsersGame.length; i++) {
				$rootScope.isNewComments($rootScope.getAllUsersGame[i].id);
			}
    }, 200);
		
		$scope.commentForGame.push(comment);
		$scope.comment = '';
			
	}
});

angular.module('homeApp').directive('starRating', function() {
	return {
		scope : {
		rating : '=',
		maxRating : '@',
		readOnly : '@',
		click : "&",
		mouseHover : "&",
		mouseLeave : "&"
	},
		restrict : 'EA',
		template : "<div style='display: inline-block; margin: 0px; padding: 0px; cursor:pointer;' "
					+ "ng-repeat='idx in maxRatings track by $index'> \
					<img width='30px' ng-src='{{((hoverValue + _rating) <= $index) "
					+ "&& \"resources/images/star_empty.png\" "
					+ "|| \"resources/images/star_filled.png\"}}' \
                    ng-Click='isolatedClick($index + 1)' \
                    ng-mouseenter='isolatedMouseHover($index + 1)' \
                    ng-mouseleave='isolatedMouseLeave($index + 1)'></img> \
            </div>",
		compile : function(element, attrs) {
			if (!attrs.maxRating
				|| (Number(attrs.maxRating) <= 0)) {
					attrs.maxRating = '5';
				};
		},
		controller : function($scope, $element, $attrs) {
			$scope.maxRatings = [];
				for (var i = 1; i <= $scope.maxRating; i++) {
					$scope.maxRatings.push({});
				};

				$scope._rating = $scope.rating;

				$scope.isolatedClick = function(param) {
					$scope.rating = $scope._rating = param;
					$scope.hoverValue = 0;
					$scope.click({
						param : param
					});
				};

				$scope.isolatedMouseHover = function(param) {

				$scope._rating = 0;
				$scope.hoverValue = param;
				$scope.mouseHover({
					param : param
				});
			};

			$scope.isolatedMouseLeave = function(param) {

				$scope._rating = $scope.rating;
				$scope.hoverValue = 0;
				$scope.mouseLeave({
					param : param
				});
			};
		}
	};
});