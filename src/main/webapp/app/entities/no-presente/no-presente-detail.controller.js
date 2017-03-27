(function() {
    'use strict';

    angular
        .module('oficialiaElectoralApp')
        .controller('No_presenteDetailController', No_presenteDetailController);

    No_presenteDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'No_presente'];

    function No_presenteDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, No_presente) {
        var vm = this;

        vm.no_presente = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('oficialiaElectoralApp:no_presenteUpdate', function(event, result) {
            vm.no_presente = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
