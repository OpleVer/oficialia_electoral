(function() {
    'use strict';

    angular
        .module('oficialiaElectoralApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('no-presente', {
            parent: 'entity',
            url: '/no-presente?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'No_presentes'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/no-presente/no-presentes.html',
                    controller: 'No_presenteController',
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
        .state('no-presente-detail', {
            parent: 'no-presente',
            url: '/no-presente/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'No_presente'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/no-presente/no-presente-detail.html',
                    controller: 'No_presenteDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'No_presente', function($stateParams, No_presente) {
                    return No_presente.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'no-presente',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('no-presente-detail.edit', {
            parent: 'no-presente-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/no-presente/no-presente-dialog.html',
                    controller: 'No_presenteDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['No_presente', function(No_presente) {
                            return No_presente.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('no-presente.new', {
            parent: 'no-presente',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/no-presente/no-presente-dialog.html',
                    controller: 'No_presenteDialogController',
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
                    $state.go('no-presente', null, { reload: 'no-presente' });
                }, function() {
                    $state.go('no-presente');
                });
            }]
        })
        .state('no-presente.edit', {
            parent: 'no-presente',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/no-presente/no-presente-dialog.html',
                    controller: 'No_presenteDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['No_presente', function(No_presente) {
                            return No_presente.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('no-presente', null, { reload: 'no-presente' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('no-presente.delete', {
            parent: 'no-presente',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/no-presente/no-presente-delete-dialog.html',
                    controller: 'No_presenteDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['No_presente', function(No_presente) {
                            return No_presente.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('no-presente', null, { reload: 'no-presente' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
