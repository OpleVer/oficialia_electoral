(function() {
    'use strict';

    angular
        .module('oficialiaElectoralApp')
        .controller('No_presenteDeleteController',No_presenteDeleteController);

    No_presenteDeleteController.$inject = ['$uibModalInstance', 'entity', 'No_presente'];

    function No_presenteDeleteController($uibModalInstance, entity, No_presente) {
        var vm = this;

        vm.no_presente = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            No_presente.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
