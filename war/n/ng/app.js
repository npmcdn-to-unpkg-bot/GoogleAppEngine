var gVersion = "0.0.1R5d";

// var angular = require('angular');
// require('angular-ui-router');

function redirectNonSSL(url) {
    if(typeof url !== 'undefined'  && url.trim().indexOf('localhost') == -1 && url.trim().startsWith('http://')) {
        if (window.location.protocol != "https:")
            window.location.href = "https:" + window.location.href.substring(window.location.protocol.length);
    }
}
function handleSSL(url) {
    if(typeof url !== 'undefined'  && url.trim().indexOf('localhost') == -1 && url.trim().startsWith('http://')) {
        var ret = url.trim().replace('http://', 'https://');
        console.log('url changed to SSL-based -> [' + ret + ']');
        return ret;
    } else {
        return url;
    }
}

//console.log("app.js 2 $state:");
//console.log($state);

//=== JWT and navigations stuff
angular.module('myApp', ['ui.router'])
    .config(function($stateProvider, $urlRouterProvider) {
        $urlRouterProvider.otherwise('/home');

        $stateProvider
            .state('home', {
                url: '/home',
                templateUrl: '../react/fusrstart.html',
                controller: function($scope, $stateParams, $state) {
                    window.$state = $state; //TODO how do we avoid global here???!!!
                    if(typeof SRStart !== 'undefined') {
                        ReactDOM.render(React.createElement(SRStart, {$state: $state}), document.getElementById('sr-start'));
                    }
                }
            })
            .state('update', {
                url: '/update/{id:int}',
                templateUrl: '../react/fusrupdate.html',
                controller: function($scope, $stateParams, $state) {
                    //$scope.inboxId = $stateParams.inboxId;
                    //console.log('app.js $stateProvider update: ', $stateParams);
                    var id = $stateParams.id;    //$stateParams.obj.id;
                    //console.log('app.js id = [' + id + ']');
                    ReactDOM.render(React.createElement(SRUpdate, {$state: $state, id: id}), document.getElementById('sr-update'));
                },
                params: {
                    obj: null
                }
            })
            .state('create', {
                url: '/create',
                templateUrl: '../react/fusrcreate.html',
                controller: function($scope, $stateParams, $state) {
                    //$scope.inboxId = $stateParams.inboxId;
                    ReactDOM.render(React.createElement(SRCreate, {$state: $state}), document.getElementById('sr-create'));
                }
            });


    })
    .controller('MainCtrl', ['mainService','$scope','$http', '$state',
        function(mainService, $scope, $http, $compile, $state) {
            $scope.version = gVersion;
            $scope.status = 'Please sign in';
            $scope.greeting = 'Welcome!';
            $scope.token = null;
            $scope.error = null;
            $scope.roleUser = false;
            $scope.roleAdmin = false;
            $scope.roleFoo = false;

            $scope.login = function() {
                $scope.status = "Logging in ...";
                $scope.error = null;
                mainService.login($scope.userName, $scope.passWord).then(function(token) {
                    $scope.token = token;
                    $http.defaults.headers.common.Authorization = 'Bearer ' + token;
                    $scope.checkRoles();
                },
                function(error){
                    $scope.status = "Invalid login. Please try again.";
                    $scope.error = error
                    $scope.userName = '';
                    $scope.passWord = '';
                });
            }

            $scope.checkRoles = function() {
                mainService.hasRole('user').then(function(user) {$scope.roleUser = user});
                mainService.hasRole('admin').then(function(admin) {$scope.roleAdmin = admin});
                mainService.hasRole('foo').then(function(foo) {$scope.roleFoo = foo});
            }

            $scope.logout = function() {
                $scope.userName = '';
                $scope.token = null;
                $http.defaults.headers.common.Authorization = '';
                location.href = "index.html";
                // Clear the JWT token.
                localStorage.removeItem('userJWTToken');
            }

            $scope.loggedIn = function() {
                var token = $scope.token;
                if(token !== null) {
                    // Save the JWT token.
                    localStorage.setItem('userJWTToken', token);
                    location.href = "app.html";
                }
                return token !== null;
            }

            $scope.enter = function() {
                location.href='app.html';
            }

        } ])
    .service('mainService', function($http) {
        return {
            login : function(username, pwd) {
                return $http.post('/api/user/login', {name: username, password: pwd}).then(function(response) {
                    return response.data.token;
                });
            },

            hasRole : function(role) {
                return $http.get('/api/jwt/role/' + role).then(function(response){
                    console.log(response);
                    return response.data;
                });
            }
        };
    });

// Common directive for Focus
angular.module('myApp').directive('focus',
    function ($timeout) {
        return {
            scope: {
                trigger: '@focus'
            },
            link: function (scope, element) {
                scope.$watch('trigger', function (value) {
                    if (value === "true") {
                        $timeout(function () {
                            element[0].focus();
                        });
                    }
                });
            }
        };
    }
);