(function() {
    'use strict';

    angular
        .module('oficialiaElectoralApp')
        .controller('ProcedeDeleteController',ProcedeDeleteController);

    ProcedeDeleteController.$inject = ['$uibModalInstance', 'entity', 'Procede'];

    function ProcedeDeleteController($uibModalInstance, entity, Procede) {
        var vm = this;

        vm.procede = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Procede.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
