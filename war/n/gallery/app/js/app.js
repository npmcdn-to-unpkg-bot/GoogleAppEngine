;'use strict';

var napp = angular.module('YourApp', ['ui-gallery-image', 'manager', 'md5']);
napp.directive('galleryImage1', ['manager','md5', function (manager,md5) {   //=== remove 1 to test
    return {
        restrict: 'E',
        link:function(scope, element, attrs) {
            //=== keep the following just for sanity test!!!
            var facebookId = attrs.facebookId;
            var githubUsername = attrs.githubUsername;
            var email = attrs.email;

            var tag = '';
            if ((facebookId !== null) && (facebookId !== undefined) && (facebookId !== '')) {
                tag = '<img src="http://graph.facebook.com/' + facebookId + '/picture?width=200&height=200" class="img-responsive"/>';
            } else if ((githubUsername !== null) && (githubUsername !== undefined) && (githubUsername !== '')){
                tag = '<img src="https://identicons.github.com/' + githubUsername + '.png" style="width:200px; height:200px" class="img-responsive"/>';
            } else {
                var hash = "";
                if ((email !== null) && (email !== undefined) && (email !== '')){
                    hash = md5.createHash(email.toLowerCase());
                }
                var src = 'https://secure.gravatar.com/avatar/' + hash + '?s=200&d=mm';
                tag = '<img src=' + src + ' class="img-responsive"/>';
            }
            element.append(tag);
            //=== end of test codes
        }
    };
}]);

var selectedImageSrc;

function UsersController($scope) {
    //=== c.f. http://stackoverflow.com/questions/11872832/how-to-respond-to-clicks-on-a-checkbox-in-an-angularjs-directive
    $scope.updateSelected = function(imgObj) {
        window.console && console.log("updateSelection entered");
        window.console && console.log(imgObj);
        $scope.selectedImageSrc = imgObj;
    };
}
