(function() {
    'use strict';

    angular
        .module('oficialiaElectoralApp')
        .controller('PeticionDialogController', PeticionDialogController);

    PeticionDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Peticion'];

    function PeticionDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Peticion) {
        var vm = this;

        vm.peticion = entity;
        vm.clear = clear;
        vm.datePickerOpenStatus = {};
        vm.openCalendar = openCalendar;
        vm.byteSize = DataUtils.byteSize;
        vm.openFile = DataUtils.openFile;
        vm.save = save;

        $timeout(function (){
            angular.element('.form-group:eq(1)>input').focus();
        });

        function clear () {
            $uibModalInstance.dismiss('cancel');
        }

        function save () {
            vm.isSaving = true;
            if (vm.peticion.id !== null) {
                Peticion.update(vm.peticion, onSaveSuccess, onSaveError);
            } else {
                Peticion.save(vm.peticion, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('oficialiaElectoralApp:peticionUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }

        vm.datePickerOpenStatus.fechayhora = false;

        vm.setOficio = function ($file, peticion) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        peticion.oficio = base64Data;
                        peticion.oficioContentType = $file.type;
                    });
                });
            }
        };

        vm.setOficio_prevencion = function ($file, peticion) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        peticion.oficio_prevencion = base64Data;
                        peticion.oficio_prevencionContentType = $file.type;
                    });
                });
            }
        };

        vm.setNotificacion_prevencion = function ($file, peticion) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        peticion.notificacion_prevencion = base64Data;
                        peticion.notificacion_prevencionContentType = $file.type;
                    });
                });
            }
        };

        vm.setRespuesta_prevencion = function ($file, peticion) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        peticion.respuesta_prevencion = base64Data;
                        peticion.respuesta_prevencionContentType = $file.type;
                    });
                });
            }
        };

        function openCalendar (date) {
            vm.datePickerOpenStatus[date] = true;
        }
    }
})();
