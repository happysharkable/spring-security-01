angular.module('app').controller('orderController', function ($scope, $http, $localStorage) {
    const contextPath = 'http://localhost:8189/market';

    $scope.getOrders = function () {
        $http({
            url: contextPath + '/api/v1/orders',
            method: 'GET',
            params: {
                username: $localStorage.currentUser.username
            }
        })
            .then(function (response) {
                $scope.OrdersList = response.data;
            });
    };

    $scope.getOrders();
});