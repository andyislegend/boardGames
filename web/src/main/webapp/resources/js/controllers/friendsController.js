var app = angular.module('homeApp').controller("friendsCtrl", ['$scope', '$rootScope', '$http', '$interval', 'friendService', function($scope,$rootScope, $http, $interval, friendService) {
    $scope.friends = [];
    var findAllFriend = function(){
        friendService.getAllFriends(function(data){
           if($scope.friends.length !== data.length){
                    $scope.friends = data;
               }
        });
    };
        
    $scope.userOffered = [];
    var findAllMyOffering = function(){
        friendService.allMyOffering(function(data){
            if ($scope.userOffered.length !== data.length){    
                $scope.userOffered = data;
            }
        })
    };

    $scope.count;
    var getCountOfOffering = function(){
         friendService.allOffering(function(data){
             if ($scope.count !== data){
                $scope.count = data;
            }
         });
    };

    $scope.allOfferedUsers = [];
    var getAllOfferedUsers = function(){
         friendService.allOfferedUsers(function(data){
            if ($scope.allOfferedUsers.length !== data.length){
                $scope.allOfferedUsers = data;
            } 
         });
    }

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
                    $scope.allUsers = data;
                }).error(function(error){
            });
       }
   };
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
    $scope.setFriendName = function(friendName){
        $scope.currentFriend = friendName;
        getUpdate();
    };
    $scope.messages = [];
    var getUpdate = function() { 
          $http.post('getAllMessage/' +  $scope.currentFriend ,  $scope.currentFriend ).success(function(data){
            if(!arraysEqual($scope.messages, data)){
                $scope.messages = data;
            }
        });
    };
    
    function arraysEqual(a, b) {
      if (a === b) return true;
      if (a.length !== b.length) return false;
       if(a.length > 0 && b.length > 0){
          for (var i = 0; i < a.length; ++i) {
            if (a[a.length-1].id !== b[b.length-1].id || a[i].statusOfReading !== b[i].statusOfReading){
                return false;
            }
          }
        }
      return true;
    }
    
    $scope.sendMessage = function(message){
        $scope.currentFriend;
        var messagePOST = {
            body: message,
            friendUsername: $scope.currentFriend
        };
        $http.post('sendMessage/',messagePOST,{headers: {'Content-Type': 'application/json'}}).success(function(){
        	$scope.newMessage = '';
        }).error(function(error){
            console.log(error);
        });
    };
    
    $scope.readMessage = function(message){
        if(!message.statusOfReading && message.userSender.username == $scope.currentFriend){

            $http.post('readMessage/' + message.id, message.id).success(function(){

            }).error(function(error){

                console.log(error);
            });
        }
    };
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
    $scope.allNotReadMessagesByFriend = [];
    var getAllNumberNotReadMessageByFriend = function(){
        friendService.allMessageByCurrentUserFriends(function(data){
            if($scope.allNotReadMessagesByFriend !== data){
                $scope.allNotReadMessagesByFriend = data;
            };
        });
    };
    
    $http.get('tournaments').success(function(data){
        $scope.allTournaments = data;
    }).error(function(error){
       console.log(error);
    });
    
    
/*setInterval(function(){
        findAllFriend();
        getUpdate();
        getAllNumberNotReadMessageByFriend();
        getCountOfOffering();
        getAllOfferedUsers();
        findAllMyOffering();
        
}, 1000);*/

    $scope.setString = function(newValue) {
    	friendsUsername.setObject(newValue);
        var a = friendsUsername.getObject();
    };
}]);

app.directive('ngEnter', function() {
       return function(scope, element, attrs) {
           element.bind("keydown keypress", function(event) {
               if(event.which == 13) {
                       scope.$apply(function(){
                               scope.$eval(attrs.ngEnter);
                       });
                       
                       event.preventDefault();
               }
           });
       };
});
app.directive("jqScroll", function () {
    return function (scope, element, attrs) {
        scope.$watch("messages", function (value) {
            var objDiv = document.getElementById("messages");
            objDiv.scrollTop = objDiv.scrollHeight;
        });
    };
});
jQuery(document).ready(function() {
    jQuery('.tabs .tab-links a').on('click', function(e)  {
        var currentAttrValue = jQuery(this).attr('href');
 
        jQuery('.tabs ' + currentAttrValue).slideDown(400).siblings().slideUp(400);
 
        jQuery(this).parent('li').addClass('active').siblings().removeClass('active');
 
        e.preventDefault();
    });
});

