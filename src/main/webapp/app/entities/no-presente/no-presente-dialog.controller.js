(function() {
    'use strict';

    angular
        .module('oficialiaElectoralApp')
        .controller('No_presenteDialogController', No_presenteDialogController);

    No_presenteDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'No_presente'];

    function No_presenteDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, No_presente) {
        var vm = this;

        vm.no_presente = entity;
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
            if (vm.no_presente.id !== null) {
                No_presente.update(vm.no_presente, onSaveSuccess, onSaveError);
            } else {
                No_presente.save(vm.no_presente, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('oficialiaElectoralApp:no_presenteUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setAcuerdo = function ($file, no_presente) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        no_presente.acuerdo = base64Data;
                        no_presente.acuerdoContentType = $file.type;
                    });
                });
            }
        };

        vm.setNotificacion = function ($file, no_presente) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        no_presente.notificacion = base64Data;
                        no_presente.notificacionContentType = $file.type;
                    });
                });
            }
        };

    }
})();
