angular.module('app').controller('signupController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.saveNewUser = function () {
        $http({
            url: contextPath + '/register',
            method: 'POST',
            params: {
                username: $scope.newUser.username,
                password: $scope.newUser.password,
                email: $scope.newUser.email
            }
        })
       .then(function (response) {
            alert('Регистрация выполнена успешно');
            $scope.user = null;
       });
    };
});