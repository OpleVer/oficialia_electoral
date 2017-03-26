(function() {
    'use strict';

    angular
        .module('oficialiaElectoralApp')
        .controller('AnexosDeleteController',AnexosDeleteController);

    AnexosDeleteController.$inject = ['$uibModalInstance', 'entity', 'Anexos'];

    function AnexosDeleteController($uibModalInstance, entity, Anexos) {
        var vm = this;

        vm.anexos = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            Anexos.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
