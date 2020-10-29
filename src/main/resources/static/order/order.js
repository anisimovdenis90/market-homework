angular.module('app').controller('orderController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.cartContentRequest = function () {
        $http({
            url: contextPath + '/api/v1/cart',
            method: "GET",
        }).then(function (response) {
            console.log(response.data);
            $scope.cart = response.data;
        });
    };

    $scope.fillTable = function () {
        $http({
            url: contextPath + '/api/v1/orders',
            method: 'GET',
        }).then(function (response) {
                $scope.OrdersList = response.data;
            });
    };
    $scope.fillTable();
});