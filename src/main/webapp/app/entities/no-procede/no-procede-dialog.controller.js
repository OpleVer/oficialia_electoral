(function() {
    'use strict';

    angular
        .module('oficialiaElectoralApp')
        .controller('No_procedeDialogController', No_procedeDialogController);

    No_procedeDialogController.$inject = ['$timeout', '$scope', '$stateParams', '$uibModalInstance', 'DataUtils', 'entity', 'No_procede'];

    function No_procedeDialogController ($timeout, $scope, $stateParams, $uibModalInstance, DataUtils, entity, No_procede) {
        var vm = this;

        vm.no_procede = entity;
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
            if (vm.no_procede.id !== null) {
                No_procede.update(vm.no_procede, onSaveSuccess, onSaveError);
            } else {
                No_procede.save(vm.no_procede, onSaveSuccess, onSaveError);
            }
        }

        function onSaveSuccess (result) {
            $scope.$emit('oficialiaElectoralApp:no_procedeUpdate', result);
            $uibModalInstance.close(result);
            vm.isSaving = false;
        }

        function onSaveError () {
            vm.isSaving = false;
        }


        vm.setAcuerdo = function ($file, no_procede) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        no_procede.acuerdo = base64Data;
                        no_procede.acuerdoContentType = $file.type;
                    });
                });
            }
        };

        vm.setNotificacion = function ($file, no_procede) {
            if ($file) {
                DataUtils.toBase64($file, function(base64Data) {
                    $scope.$apply(function() {
                        no_procede.notificacion = base64Data;
                        no_procede.notificacionContentType = $file.type;
                    });
                });
            }
        };

    }
})();
