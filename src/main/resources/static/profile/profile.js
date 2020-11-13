angular.module('app').controller('profileController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.saveUserProfile = function () {
        $http.put(contextPath + '/api/v1/profile', $scope.newProfile)
            .then(function (response) {
                alert('Профиль обновлен');
                $scope.newProfile = null;
                $scope.fillTable();
           });
    };

    $scope.fillTable = function () {
        $http({
            url: contextPath + '/api/v1/profile',
            method: 'GET'
        })
            .then(function (response) {
                $scope.userProfile = response.data;
            });
    };

    $scope.fillTable();
});