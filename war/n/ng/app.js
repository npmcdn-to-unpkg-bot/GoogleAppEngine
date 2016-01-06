<!-- JWT and navigations stuff -->
angular.module('myApp', [])
    .controller('MainCtrl', ['mainService','$scope','$http',
        function(mainService, $scope, $http, $compile) {
            $scope.greeting = 'Welcome to the JSON Web Token / AngularJR / Spring example!';
            $scope.token = null;
            $scope.error = null;
            $scope.roleUser = false;
            $scope.roleAdmin = false;
            $scope.roleFoo = false;

            $scope.login = function() {
                $scope.error = null;
                mainService.login($scope.userName).then(function(token) {
                    $scope.token = token;
                    $http.defaults.headers.common.Authorization = 'Bearer ' + token;
                    $scope.checkRoles();
                },
                function(error){
                    $scope.error = error
                    $scope.userName = '';
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
                    location.href = "fusrstart.html";
                }
                return token !== null;
            }

            $scope.enter = function() {
                location.href='fusrstart.html';
            }

        } ])
    .service('mainService', function($http) {
        return {
            login : function(username) {
                return $http.post('/api/user/login', {name: username}).then(function(response) {
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
