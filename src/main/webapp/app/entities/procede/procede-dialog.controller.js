(function() {
    'use strict';

    angular
        .module('oficialiaElectoralApp')
        .controller('ProcedeDialogController', ProcedeDialogController);

    ProcedeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'Procede'];

    function ProcedeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, Procede) {
        var vm = this;

        vm.procede = entity;
        vm.clear = clear;
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
            if (vm.procede.id !== null) {
                Procede.update(vm.procede, onSaveSuccess, onSaveError);
            } else {
                Procede.save(vm.procede, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('oficialiaElectoralApp:procedeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setActa = function ($file, procede) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        procede.acta = base64Data;
                        procede.actaContentType = $file.type;
                    });
                });
            }
        };

        vm.setAcuerdo = function ($file, procede) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        procede.acuerdo = base64Data;
                        procede.acuerdoContentType = $file.type;
                    });
                });
            }
        };

        vm.setNotificacion = function ($file, procede) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        procede.notificacion = base64Data;
                        procede.notificacionContentType = $file.type;
                    });
                });
            }
        };

    }
})();
