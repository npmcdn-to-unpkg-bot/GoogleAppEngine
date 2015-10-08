;'use strict';

angular.module('ui-gallery-image', [])
    .directive('galleryImage', ['manager', function (manager) {
        return {
            restrict: 'EA',
            replace: true,
            transclude: true,
            scope: {
                eventHandler: '&ngClick'
                //,text: '@dataSrc'
                //,twoWayBind: "=myTwoWayBind"
            },
            template: '<div class="img-wrap" id="gHolder"><span class="close">&times;</span><img id="gImg{{imgId}}" src="{{imgSrc}}" data-ng-click="eventHandler()" alt="Empty Image"/></div>',
            link:function(scope, element, attrs) {
                attrs.$observe('id', function(value) {
                    scope.imgId = value;
                    window.console && console.log('gallery-image-directive.js: id=', value);
                });

                attrs.$observe('src', function(value) {
                    scope.imgSrc = value;
                    window.console && console.log('gallery-image-directive.js: src=', value);
                });

                element.on('click', function () {
                    scope[scope.imgId] = attrs.src;
                    window.console && console.log('gallery-image-directive.js: scope['+scope.imgId+']=', scope[scope.imgId]);
                    scope.selectedGalleryImage = scope[scope.imgId];
                    scope.someObject = { name:scope[scope.imgId], id:scope.imgId };

                    //=== begin of remove function
                    window.console && console.log('current img selected id [' + 'gImg' + attrs.id + ']');
                    //window.console && console.dir($('gImg' + attrs.id));
                    //scope.currentImg = element;
                    //=== end of remove function

                    scope.$apply();
                });


            }
        };
    }])
    .controller('GalleryController', ['$scope', '$element', '$attrs', function($scope, $element, $attrs) {
        $scope.handleDelete = function(msg, id) {
            window.console && console.log(msg + '!!! handleDelete function call! msg [' + msg + "] $scope.currentImg [" + $scope.currentImg + "]");
            //console.dir(obj);
            if(msg === true) {
                alert("image to be deleted is " + id);
                //obj.remove();
            }

            return true;
        };
//        $scope.toggleSelect = function(msg) {
//            //window.console && console.log($attrs.controllerId);
//            window.console && console.log(msg + '!!! toggleSelect function call! $scope.selectedGalleryImage [' + $scope.selectedGalleryImage + "]");
//        };
//        $scope.test1Function = function(msg) {
//            window.console && console.log(msg + '!!! first function call!');
//        };
//        $scope.test2Function = function(msg) {
//            window.console && console.log(msg + '!!! second function call!');
//        };
}   ]);