(function() {
    'use strict';

    angular
        .module('oficialiaElectoralApp')
        .controller('AnexosDialogController', AnexosDialogController);

    AnexosDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Anexos'];

    function AnexosDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Anexos) {
        var vm = this;

        vm.anexos = entity;
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
            if (vm.anexos.id !== null) {
                Anexos.update(vm.anexos, onSaveSuccess, onSaveError);
            } else {
                Anexos.save(vm.anexos, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('oficialiaElectoralApp:anexosUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
