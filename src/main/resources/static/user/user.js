angular.module('app').controller('userController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.saveUserProfile = function () {
        $http({
            url: contextPath + '/api/v1/profile',
            method: 'PUT',
            params: {
                password: $scope.newProfile.password ? $scope.newProfile.password : null,
                firstname: $scope.newProfile.firstname ? $scope.newProfile.firstname : null,
                lastname: $scope.newProfile.lastname ? $scope.newProfile.lastname : null,
                birthday: $scope.newProfile.birthday ? $scope.newProfile.birthday : null,
                phone: $scope.newProfile.phone ? $scope.newProfile.phone : null,
                hometown: $scope.newProfile.hometown ? $scope.newProfile.hometown : null,
                gender: $scope.newProfile.gender ? $scope.newProfile.gender : null
            }
        }).then(function (response) {
            alert('Изменения успешно сохранены');
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