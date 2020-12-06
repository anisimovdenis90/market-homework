angular.module('app').controller('signupController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.tryToRegister = function () {
        $http.post(contextPath + '/register', $scope.newUser)
           .then(function successCallback(response) {
                alert('Регистрация выполнена успешно');
                $scope.newUser = null;
           }, function errorCallback(response) {
                window.alert(response.data.message);
           });
    };
});