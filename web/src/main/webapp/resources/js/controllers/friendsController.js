var app = angular.module('homeApp').controller("friendsCtrl", ['$scope', '$rootScope', '$interval', '$http', function($scope,$rootScope, friendService, $http, $interval) {
    $scope.users;
   var allf = $http.get("allFriends").success(function(data) {
		$scope.friends = data;
	}).error(function(error) {
		console.log(error);
	});
    
    $http.get("allMyOffering").success(function(data) {
		$scope.userOffered = data;
	}).error(function(error) {
		console.log(error);
	});
    
    $http.get('allOffering').success(function(data){
         $scope.count = data;
     }).error(function(error){
         console.log(error)
     });
    
    $http.get("allOfferedUsers").success(function(data) {
        $scope.allOfferedUsers = data;
	}).error(function(error) {
		console.log(error);
	});
    
     $scope.add = function(id){
        var userId = id;
        if($scope.count == 1){
            $scope.NameOfModalWindow = 'modal';
        }
        $scope.count =  $scope.count-1;
        $http.post('addUserToFriend', userId).success(function(data){
            var user = data;
            for(var i = 0; i < $scope.allOfferedUsers.length; i++){
                if($scope.allOfferedUsers[i].id === user.id){
                    $scope.friends.push($scope.allOfferedUsers[i]);
                    $scope.allOfferedUsers.splice(i, 1)
                };
            
            };
        }).error(function(error){
            console.log(error);
        });
    };

    $scope.rejected = function(id){
        var userId = id;
        if($scope.count == 1){
            $scope.NameOfModalWindow = 'modal';
        }
        $scope.count =  $scope.count-1;
        $http.post('rejectedUserToFriend', userId).success(function(data){
             var user = data;
            for(var i = 0; i < $scope.allOfferedUsers.length; i++){
                if($scope.allOfferedUsers[i].id === user.id){
                    $scope.allOfferedUsers.splice(i, 1)
                };
            };
            
        }).error(function(error){
            console.log(error);
        });
    };
   $scope.findAllUsers = function(){
       if($scope.name.length > 0){
                $http.post('findAllUsers/' + $scope.name, $scope.name).success(function(data){
                    $rootScope.$broadcast('message', data);
                }).error(function(error){
            });
       }
   };

    $scope.setFriendName = function(friendName){
        $scope.currentFriend = friendName;
        getUpdate(friendName);
    };
    var getUpdate = function() { 
          $http.post('getAllMessage/' +  $scope.currentFriend ,  $scope.currentFriend ).success(function(data){
             var objDiv = document.getElementById("messages");
            objDiv.scrollTop = objDiv.scrollHeight;
              if($scope.messages == undefined){
               $scope.messages = data;  
             }else{
                 if($scope.messages.length != data.length){
                      $scope.messages = data;
                 }
             }
            
        }).error(function(error){
            console.log(error);
        });
    };

    $scope.sendMessage = function(message){
        $scope.currentFriend
        $http.post('sendMessage/' + $scope.currentFriend + "/" + message, $scope.currentFriend, message).success(function(){
        }).error(function(error){
            console.log(error);
        });
    };
    $scope.readMessage = function(messageId){
        $http.post('readMessage/' + messageId, messageId).success(function(){
        }).error(function(error){
            console.log(error);
        });
        
    };
    
    $http.get('findAllNotReadMessage').success(function(data){
         $scope.countOfNotReadMessage = data;
     }).error(function(error){
         console.log(error)
     });
    $scope.cancelOffering = function(userName){
        $http.post('canselOffering/' + userName, userName).success(function(){
            for(var i = 0; i < $scope.userOffered.length; i++){
                if($scope.userOffered[i].user.username === userName || $scope.userOffered[i].userId.username === userName){
                    $scope.userOffered.splice(i, 1)
                };
            };
        }).error(function(error){
            console.log(error);
    });
};
    $http.get('allMessageByCurrentUserFriends').success(function(data){
        $scope.allNotReadMessagesByFriend = data;
    }).error(function(error){
        console.log(error);
    });
    
   
  setInterval(function(){
       getUpdate();
   }, 500)
   
}]);

app.controller('searchUserCtrl',['$scope', '$rootScope', '$http', function($scope, $rootScope, $http){
    $scope.message = 'Hello';
    $scope.$on('message', function(event,responce){
        if(responce.length < 1){
             $scope.open = false;
        }else{
             $scope.open = true;
        }
        $scope.allUsers = responce;
    });
    $scope.addUserToFriend = function(id){
         $http.post('addOfferToFriendship/', id).success(function(data){
             $scope.userWhoYouSentOffering = data;
             for(var i = 0; i < $scope.allUsers.length; i++){
                if($scope.allUsers[i].id === id){
                    $scope.allUsers.splice(i, 1)
                };
            };
         }).error(function(error){
             console.log(error);
         });
    };
}]);
