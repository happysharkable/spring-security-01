angular.module('app').controller('profileController', function ($scope, $http) {
    const contextPath = 'http://localhost:8189/market';

    $scope.getProfile = function () {
        $http({
            url: contextPath + '/api/v1/profile',
            method: 'GET'
        })
            .then(function (response) {
                $scope.Profile = response.data;
            });
    };

    $scope.changeProfile = function () {
        $http({
            url: contextPath + '/api/v1/profile',
            method: 'POST',
            params: {
                firstname: $scope.Profile.firstname,
                lastname: $scope.Profile.lastname,
                phone: $scope.Profile.phone,
                email: $scope.Profile.email,
                birthyear: $scope.Profile.birthyear,
                gender: $scope.Profile.gender,
                city: $scope.Profile.city,
                password: $scope.password
            }
        })
            .then(function (response) {
                $scope.getProfile();
            });

    };

    $scope.getProfile();
});