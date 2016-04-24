angular.module('indexModule').factory('CreateUserService', ['$http', '$q', function($http, $q){
	
	 return {
	
		 createUser: function(userDTO){
             return $http.post('newuser', userDTO)
                     .then(
                             function(response){
                                 return response.data;
                             }, 
                             function(errResponse){
                                 console.error('Error while creating user');
                                 return $q.reject(errResponse);
                             }
                     );
		 }
	 };
	 
}]);