(function() {
    'use strict';

    angular
        .module('oficialiaElectoralApp')
        .controller('PeticionDetailController', PeticionDetailController);

    PeticionDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'Peticion'];

    function PeticionDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, Peticion) {
        var vm = this;

        vm.peticion = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('oficialiaElectoralApp:peticionUpdate', function(event, result) {
            vm.peticion = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
