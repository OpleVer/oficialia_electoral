(function() {
    'use strict';

    angular
        .module('oficialiaElectoralApp')
        .controller('ProcedeDetailController', ProcedeDetailController);

    ProcedeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'Procede'];

    function ProcedeDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, Procede) {
        var vm = this;

        vm.procede = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('oficialiaElectoralApp:procedeUpdate', function(event, result) {
            vm.procede = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
