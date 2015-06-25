function Hello($scope, $http) {
    $http.get('/web/greet/AngularJS').
        success(function(data) {
            $scope.greeting = data;
        });
}