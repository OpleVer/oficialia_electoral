(function() {
    'use strict';
    angular
        .module('oficialiaElectoralApp')
        .factory('No_procede', No_procede);

    No_procede.$inject = ['$resource'];

    function No_procede ($resource) {
        var resourceUrl =  'api/no-procedes/:id';

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
