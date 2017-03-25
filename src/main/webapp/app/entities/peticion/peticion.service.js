(function() {
    'use strict';
    angular
        .module('oficialiaElectoralApp')
        .factory('Peticion', Peticion);

    Peticion.$inject = ['$resource', 'DateUtils'];

    function Peticion ($resource, DateUtils) {
        var resourceUrl =  'api/peticions/:id';

        return $resource(resourceUrl, {}, {
            'query': { method: 'GET', isArray: true},
            'get': {
                method: 'GET',
                transformResponse: function (data) {
                    if (data) {
                        data = angular.fromJson(data);
                        data.fechayhora = DateUtils.convertDateTimeFromServer(data.fechayhora);
                    }
                    return data;
                }
            },
            'update': { method:'PUT' }
        });
    }
})();
