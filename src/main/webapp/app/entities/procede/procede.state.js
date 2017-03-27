(function() {
    'use strict';

    angular
        .module('oficialiaElectoralApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('procede', {
            parent: 'entity',
            url: '/procede?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Procedes'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/procede/procedes.html',
                    controller: 'ProcedeController',
                    controllerAs: 'vm'
                }
            },
            params: {
                page: {
                    value: '1',
                    squash: true
                },
                sort: {
                    value: 'id,asc',
                    squash: true
                },
                search: null
            },
            resolve: {
                pagingParams: ['$stateParams', 'PaginationUtil', function ($stateParams, PaginationUtil) {
                    return {
                        page: PaginationUtil.parsePage($stateParams.page),
                        sort: $stateParams.sort,
                        predicate: PaginationUtil.parsePredicate($stateParams.sort),
                        ascending: PaginationUtil.parseAscending($stateParams.sort),
                        search: $stateParams.search
                    };
                }]
            }
        })
        .state('procede-detail', {
            parent: 'procede',
            url: '/procede/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Procede'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/procede/procede-detail.html',
                    controller: 'ProcedeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Procede', function($stateParams, Procede) {
                    return Procede.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'procede',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('procede-detail.edit', {
            parent: 'procede-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/procede/procede-dialog.html',
                    controller: 'ProcedeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Procede', function(Procede) {
                            return Procede.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('procede.new', {
            parent: 'procede',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/procede/procede-dialog.html',
                    controller: 'ProcedeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                acta: null,
                                actaContentType: null,
                                acuerdo: null,
                                acuerdoContentType: null,
                                id_procede: null,
                                notificacion: null,
                                notificacionContentType: null,
                                id_numero_solicitud: null,
                                num_acta: null,
                                num_acuerdo: null,
                                num_notificacion: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('procede', null, { reload: 'procede' });
                }, function() {
                    $state.go('procede');
                });
            }]
        })
        .state('procede.edit', {
            parent: 'procede',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/procede/procede-dialog.html',
                    controller: 'ProcedeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Procede', function(Procede) {
                            return Procede.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('procede', null, { reload: 'procede' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('procede.delete', {
            parent: 'procede',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/procede/procede-delete-dialog.html',
                    controller: 'ProcedeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Procede', function(Procede) {
                            return Procede.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('procede', null, { reload: 'procede' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
