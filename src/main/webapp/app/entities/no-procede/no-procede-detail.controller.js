(function() {
    'use strict';

    angular
        .module('oficialiaElectoralApp')
        .controller('No_procedeDetailController', No_procedeDetailController);

    No_procedeDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'DataUtils', 'entity', 'No_procede'];

    function No_procedeDetailController($scope, $rootScope, $stateParams, previousState, DataUtils, entity, No_procede) {
        var vm = this;

        vm.no_procede = entity;
        vm.previousState = previousState.name;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;

        var unsubscribe = $rootScope.$on('oficialiaElectoralApp:no_procedeUpdate', function(event, result) {
            vm.no_procede = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
