angular.module('homeApp').service('friendsUsername', function() {
    var objectValue = {
        data: 'My profile'
    };
    
    return {
        getObject: function() {
            return objectValue;
        },
        setObject: function(value) {
        	objectValue.data = value;
        }
    }

});