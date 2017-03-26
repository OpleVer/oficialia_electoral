(function() {
    'use strict';

    angular
        .module('oficialiaElectoralApp')
        .controller('AnexosDetailController', AnexosDetailController);

    AnexosDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'Anexos'];

    function AnexosDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, Anexos) {
        var vm = this;

        vm.anexos = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('oficialiaElectoralApp:anexosUpdate', function(event, result) {
            vm.anexos = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
