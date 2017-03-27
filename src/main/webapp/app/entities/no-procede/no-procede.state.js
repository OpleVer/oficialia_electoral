(function() {
    'use strict';

    angular
        .module('oficialiaElectoralApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('no-procede', {
            parent: 'entity',
            url: '/no-procede?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'No_procedes'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/no-procede/no-procedes.html',
                    controller: 'No_procedeController',
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
        .state('no-procede-detail', {
            parent: 'no-procede',
            url: '/no-procede/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'No_procede'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/no-procede/no-procede-detail.html',
                    controller: 'No_procedeDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'No_procede', function($stateParams, No_procede) {
                    return No_procede.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'no-procede',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('no-procede-detail.edit', {
            parent: 'no-procede-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/no-procede/no-procede-dialog.html',
                    controller: 'No_procedeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['No_procede', function(No_procede) {
                            return No_procede.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('no-procede.new', {
            parent: 'no-procede',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/no-procede/no-procede-dialog.html',
                    controller: 'No_procedeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                id_numero_solicitud: null,
                                acuerdo: null,
                                acuerdoContentType: null,
                                num_acuerdo: null,
                                notificacion: null,
                                notificacionContentType: null,
                                num_notificacion: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('no-procede', null, { reload: 'no-procede' });
                }, function() {
                    $state.go('no-procede');
                });
            }]
        })
        .state('no-procede.edit', {
            parent: 'no-procede',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/no-procede/no-procede-dialog.html',
                    controller: 'No_procedeDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['No_procede', function(No_procede) {
                            return No_procede.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('no-procede', null, { reload: 'no-procede' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('no-procede.delete', {
            parent: 'no-procede',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/no-procede/no-procede-delete-dialog.html',
                    controller: 'No_procedeDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['No_procede', function(No_procede) {
                            return No_procede.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('no-procede', null, { reload: 'no-procede' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
