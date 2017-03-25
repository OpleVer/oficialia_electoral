(function() {
    'use strict';

    angular
        .module('oficialiaElectoralApp')
        .controller('OrigenDeleteController',OrigenDeleteController);

    OrigenDeleteController.$inject = ['$uibModalInstance', 'entity', 'Origen'];

    function OrigenDeleteController($uibModalInstance, entity, Origen) {
        var vm = this;

        vm.origen = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Origen.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
