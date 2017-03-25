(function() {
    'use strict';

    angular
        .module('oficialiaElectoralApp')
        .controller('OrigenDetailController', OrigenDetailController);

    OrigenDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Origen'];

    function OrigenDetailController($scope, $rootScope, $stateParams, previousState, entity, Origen) {
        var vm = this;

        vm.origen = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('oficialiaElectoralApp:origenUpdate', function(event, result) {
            vm.origen = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
