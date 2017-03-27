(function() {
    'use strict';

    angular
        .module('oficialiaElectoralApp')
        .controller('AnexosDetailController', AnexosDetailController);

    AnexosDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Anexos'];

    function AnexosDetailController($scope, $rootScope, $stateParams, previousState, entity, Anexos) {
        var vm = this;

        vm.anexos = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('oficialiaElectoralApp:anexosUpdate', function(event, result) {
            vm.anexos = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
