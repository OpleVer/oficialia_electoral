(function() {
    'use strict';

    angular
        .module('oficialiaElectoralApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('anexos', {
            parent: 'entity',
            url: '/anexos?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Anexos'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/anexos/anexos.html',
                    controller: 'AnexosController',
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
        .state('anexos-detail', {
            parent: 'anexos',
            url: '/anexos/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Anexos'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/anexos/anexos-detail.html',
                    controller: 'AnexosDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Anexos', function($stateParams, Anexos) {
                    return Anexos.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'anexos',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('anexos-detail.edit', {
            parent: 'anexos-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/anexos/anexos-dialog.html',
                    controller: 'AnexosDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Anexos', function(Anexos) {
                            return Anexos.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('anexos.new', {
            parent: 'anexos',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/anexos/anexos-dialog.html',
                    controller: 'AnexosDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                descripcion: null,
                                archivo: null,
                                id_numero_solicitud: null,
                                id_procede: null,
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('anexos', null, { reload: 'anexos' });
                }, function() {
                    $state.go('anexos');
                });
            }]
        })
        .state('anexos.edit', {
            parent: 'anexos',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/anexos/anexos-dialog.html',
                    controller: 'AnexosDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Anexos', function(Anexos) {
                            return Anexos.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('anexos', null, { reload: 'anexos' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('anexos.delete', {
            parent: 'anexos',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/anexos/anexos-delete-dialog.html',
                    controller: 'AnexosDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Anexos', function(Anexos) {
                            return Anexos.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('anexos', null, { reload: 'anexos' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
