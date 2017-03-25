(function() {
    'use strict';

    angular
        .module('oficialiaElectoralApp')
        .controller('SolicitudDialogController', SolicitudDialogController);

    SolicitudDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'entity', 'Solicitud'];

    function SolicitudDialogController ($timeout, $scope, $stateParams, $uibModalInstance, entity, Solicitud) {
        var vm = this;

        vm.solicitud = entity;
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
            if (vm.solicitud.id !== null) {
                Solicitud.update(vm.solicitud, onSaveSuccess, onSaveError);
            } else {
                Solicitud.save(vm.solicitud, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('oficialiaElectoralApp:solicitudUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


    }
})();
