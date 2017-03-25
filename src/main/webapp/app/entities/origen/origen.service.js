(function() {
    'use strict';
    angular
        .module('oficialiaElectoralApp')
        .factory('Origen', Origen);

    Origen.$inject = ['$resource'];

    function Origen ($resource) {
        var resourceUrl =  'api/origens/:id';

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
