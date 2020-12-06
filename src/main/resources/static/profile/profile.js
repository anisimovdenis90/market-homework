angular.module('app').controller('profileController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.saveUserProfile = function () {
        $http.put(contextPath + '/api/v1/profile', $scope.newProfile)
            .then(function successCallback(response) {
                alert('Профиль успешно обновлен');
                $scope.newProfile = null;
            }, function errorCallback(response) {
                window.alert(response.data.message);
            });
    };

    $scope.fillTable = function () {
        $http.get(contextPath + '/api/v1/profile')
            .then(function (response) {
                $scope.userProfile = response.data;
            });
    };

    $scope.getCategoriesList = function () {
        $http.get(contextPath + '/api/v1/categories')
            .then(function (response) {
                $scope.CategoriesList = response.data;
            });
    };

    $scope.fillTable();
});