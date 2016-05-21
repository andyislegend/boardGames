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
        }
    }
    
});