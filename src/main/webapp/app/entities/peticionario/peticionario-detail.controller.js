(function() {
    'use strict';

    angular
        .module('oficialiaElectoralApp')
        .controller('PeticionarioDetailController', PeticionarioDetailController);

    PeticionarioDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Peticionario'];

    function PeticionarioDetailController($scope, $rootScope, $stateParams, previousState, entity, Peticionario) {
        var vm = this;

        vm.peticionario = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('oficialiaElectoralApp:peticionarioUpdate', function(event, result) {
            vm.peticionario = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
