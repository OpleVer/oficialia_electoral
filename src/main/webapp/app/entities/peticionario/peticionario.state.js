(function() {
    'use strict';

    angular
        .module('oficialiaElectoralApp')
        .config(stateConfig);

    stateConfig.$inject = ['$stateProvider'];

    function stateConfig($stateProvider) {
        $stateProvider
        .state('peticionario', {
            parent: 'entity',
            url: '/peticionario?page&sort&search',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Peticionarios'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/peticionario/peticionarios.html',
                    controller: 'PeticionarioController',
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
        .state('peticionario-detail', {
            parent: 'peticionario',
            url: '/peticionario/{id}',
            data: {
                authorities: ['ROLE_USER'],
                pageTitle: 'Peticionario'
            },
            views: {
                'content@': {
                    templateUrl: 'app/entities/peticionario/peticionario-detail.html',
                    controller: 'PeticionarioDetailController',
                    controllerAs: 'vm'
                }
            },
            resolve: {
                entity: ['$stateParams', 'Peticionario', function($stateParams, Peticionario) {
                    return Peticionario.get({id : $stateParams.id}).$promise;
                }],
                previousState: ["$state", function ($state) {
                    var currentStateData = {
                        name: $state.current.name || 'peticionario',
                        params: $state.params,
                        url: $state.href($state.current.name, $state.params)
                    };
                    return currentStateData;
                }]
            }
        })
        .state('peticionario-detail.edit', {
            parent: 'peticionario-detail',
            url: '/detail/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/peticionario/peticionario-dialog.html',
                    controller: 'PeticionarioDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Peticionario', function(Peticionario) {
                            return Peticionario.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('^', {}, { reload: false });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('peticionario.new', {
            parent: 'peticionario',
            url: '/new',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/peticionario/peticionario-dialog.html',
                    controller: 'PeticionarioDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: function () {
                            return {
                                id: null
                            };
                        }
                    }
                }).result.then(function() {
                    $state.go('peticionario', null, { reload: 'peticionario' });
                }, function() {
                    $state.go('peticionario');
                });
            }]
        })
        .state('peticionario.edit', {
            parent: 'peticionario',
            url: '/{id}/edit',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/peticionario/peticionario-dialog.html',
                    controller: 'PeticionarioDialogController',
                    controllerAs: 'vm',
                    backdrop: 'static',
                    size: 'lg',
                    resolve: {
                        entity: ['Peticionario', function(Peticionario) {
                            return Peticionario.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('peticionario', null, { reload: 'peticionario' });
                }, function() {
                    $state.go('^');
                });
            }]
        })
        .state('peticionario.delete', {
            parent: 'peticionario',
            url: '/{id}/delete',
            data: {
                authorities: ['ROLE_USER']
            },
            onEnter: ['$stateParams', '$state', '$uibModal', function($stateParams, $state, $uibModal) {
                $uibModal.open({
                    templateUrl: 'app/entities/peticionario/peticionario-delete-dialog.html',
                    controller: 'PeticionarioDeleteController',
                    controllerAs: 'vm',
                    size: 'md',
                    resolve: {
                        entity: ['Peticionario', function(Peticionario) {
                            return Peticionario.get({id : $stateParams.id}).$promise;
                        }]
                    }
                }).result.then(function() {
                    $state.go('peticionario', null, { reload: 'peticionario' });
                }, function() {
                    $state.go('^');
                });
            }]
        });
    }

})();
