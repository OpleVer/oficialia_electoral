(function() {
    'use strict';
    angular
        .module('oficialiaElectoralApp')
        .factory('Procede', Procede);

    Procede.$inject = ['$resource'];

    function Procede ($resource) {
        var resourceUrl =  'api/procedes/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
