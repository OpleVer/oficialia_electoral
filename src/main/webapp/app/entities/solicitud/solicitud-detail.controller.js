(function() {
    'use strict';

    angular
        .module('oficialiaElectoralApp')
        .controller('SolicitudDetailController', SolicitudDetailController);

    SolicitudDetailController.$inject = ['$scope', '$rootScope', '$stateParams', 'previousState', 'entity', 'Solicitud'];

    function SolicitudDetailController($scope, $rootScope, $stateParams, previousState, entity, Solicitud) {
        var vm = this;

        vm.solicitud = entity;
        vm.previousState = previousState.name;

        var unsubscribe = $rootScope.$on('oficialiaElectoralApp:solicitudUpdate', function(event, result) {
            vm.solicitud = result;
        });
        $scope.$on('$destroy', unsubscribe);
    }
})();
