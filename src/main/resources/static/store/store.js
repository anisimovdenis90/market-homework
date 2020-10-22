angular.module('app').controller('storeController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.fillTable = function (pageIndex = 1) {
        console.log('fill');
        $http({
            url: contextPath + '/api/v1/products',
            method: "GET",
            params: {
                p: pageIndex,
                title: $scope.newFilter ? $scope.newFilter.title : null,
                min_price: $scope.newFilter ? $scope.newFilter.min_price : null,
                max_price: $scope.newFilter ? $scope.newFilter.max_price : null,
                category: $scope.newFilter ? $scope.newFilter.category : null
            }
        }).then(function (response) {
            console.log(response.data);
            $scope.Categories = response.data[0];
            $scope.ProductsPage = response.data[1];
            $scope.PaginationArray = $scope.generatePagesInd(1, $scope.ProductsPage.totalPages);
        });
    };

    $scope.generatePagesInd = function(startPage, endPage) {
        let arr = [];
        for (let i = startPage; i < endPage + 1; i++) {
            arr.push(i);
        }
        return arr;
    };

     $scope.clearFilter = function () {
        $scope.newFilter = null;
        $scope.fillTable();
     };

     $scope.addToCart = function (productId) {
        $http({
            url: contextPath + '/api/v1/cart/add/' + productId,
            method: 'GET'
        })
            .then(function (response) {
                console.log('ok');
            });
     };

    $scope.fillTable();
});