app.factory('friendService', function($http){
   
    return{
        getAllFriends: function(successCallBack){
            $http({method: 'GET', url: 'allFriends'}).
            success(function(data, status, headers, config){
                successCallBack(data);
            }).
            error(function(data, status, headers, config){
                console.log(data);
            });
        },
        
        allMyOffering: function(successCallBack){
            $http({method: 'GET', url: 'allMyOffering'}).
            success(function(data, status, headers, config){
                successCallBack(data);
            }).
            error(function(data, status, headers, config){
                console.log(data);
            });
        },
        
        allOffering: function(successCallBack){
            $http({method: 'GET', url: 'allOffering'}).
            success(function(data, status, headers, config){
                successCallBack(data);
            }).
            error(function(data, status, headers, config){
                console.log(data);
            });
        },
        
        allOfferedUsers: function(successCallBack){
            $http({method: 'GET', url: 'allOfferedUsers'}).
            success(function(data, status, headers, config){
                successCallBack(data);
            }).
            error(function(data, status, headers, config){
                console.log(data);
            });
        },
        
        allMessageByCurrentUserFriends: function(successCallBack){
            $http({method: 'GET', url: 'allMessageByCurrentUserFriends'}).
            success(function(data, status, headers, config){
                successCallBack(data);
            }).
            error(function(data, status, headers, config){
                console.log(data);
            });
        }
    }
    
});
