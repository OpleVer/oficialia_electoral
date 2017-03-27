(function() {
    'use strict';

    angular
        .module('oficialiaElectoralApp')
        .controller('No_procedeDeleteController',No_procedeDeleteController);

    No_procedeDeleteController.$inject = ['$uibModalInstance', 'entity', 'No_procede'];

    function No_procedeDeleteController($uibModalInstance, entity, No_procede) {
        var vm = this;

        vm.no_procede = entity;
        vm.clear = clear;
        vm.confirmDelete = confirmDelete;

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function confirmDelete (id) {
            No_procede.delete({id: id},
                function () {
                    $uibModalInstance.close(true);
                });
        }
    }
})();
