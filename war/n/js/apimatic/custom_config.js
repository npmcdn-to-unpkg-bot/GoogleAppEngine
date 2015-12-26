<!-- override the APIMATIC settings with the current host/deployed env -->
angular.module('ServiceManagerLib').factory('Configuration', function() {
    return {
        BASEURI : location.origin
    };
});