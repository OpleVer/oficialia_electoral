(function() {
    'use strict';
    angular
        .module('oficialiaElectoralApp')
        .factory('No_presente', No_presente);

    No_presente.$inject = ['$resource'];

    function No_presente ($resource) {
        var resourceUrl =  'api/no-presentes/:id';

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
