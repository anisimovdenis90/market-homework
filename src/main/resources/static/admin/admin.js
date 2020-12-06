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
            .then(function (response) {
                $scope.newProduct = null;
                alert('Добавлен новый продукт');
            });
    };

    $scope.submitCreateNewCategory = function () {
        $http.post(contextPath + '/api/v1/categories', $scope.category)
            .then(function (response) {
                $scope.category = null;
                alert('Создана новая категория');
                $scope.getCategoriesList();
            });
    };

    $scope.getCategoriesList();
});