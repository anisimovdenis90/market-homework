angular.module('app').controller('adminController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.getCategoriesList = function () {
        $http.get(contextPath + '/api/v1/categories')
            .then(function (response) {
                $scope.CategoriesList = response.data;
            });
    };

    $scope.submitCreateNewProduct = function () {
        $http.post(contextPath + '/api/v1/products', $scope.newProduct)
            .then(function successCallback(response) {
                alert('Регистрация выполнена успешно');
                $scope.newProduct = null;
            }, function errorCallback(response) {
                window.alert(response.data.message);
            });
    };

    $scope.submitCreateNewCategory = function () {
        $http.post(contextPath + '/api/v1/categories', $scope.category)
            .then(function successCallback(response) {
                alert('Создана новая категория');
                $scope.category = null;
            }, function errorCallback(response) {
                window.alert(response.data.message);
            });
    };

    $scope.getCategoriesList();
});