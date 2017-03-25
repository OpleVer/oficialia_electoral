(function () {
    'use strict';

    angular
        .module('oficialiaElectoralApp')
        .factory('Register', Register);

    Register.$inject = ['$resource'];

    function Register ($resource) {
        return $resource('api/register', {}, {});
    }
})();
