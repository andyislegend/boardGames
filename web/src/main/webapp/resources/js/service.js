app.factory('friendService', function($http){

    function getCount(){
       return $http.get('allOffering');
    }
    function getAllFriends(){
        return $http.get("allFriends");
    }
    function getAllOfferedUsers(){
        return $http.get("allOfferedUsers");
    }
    
    function addUsertoFriend(userId){
        return $http.post('addUserToFriend', userId);
    }
    
    return{
        getCount: getCount,
        getAllFriends: getAllFriends,
        getAllOfferedUsers: getAllOfferedUsers,
        addUsertoFriend: addUsertoFriend
    };
    
    

});