(function() {
    'use strict';

    angular
        .module('oficialiaElectoralApp')
        .controller('OrigenDialogController', OrigenDialogController);

    OrigenDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Origen'];

    function OrigenDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Origen) {
        var vm = this;

        vm.origen = entity;
        vm.clear = clear;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.origen.id !== null) {
                Origen.update(vm.origen, onSaveSuccess, onSaveError);
            } else {
                Origen.save(vm.origen, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('oficialiaElectoralApp:origenUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
