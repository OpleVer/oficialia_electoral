'use strict';

describe('Controller Tests', function() {

    describe('No_presente Management Detail Controller', function() {
        var $scope, $rootScope;
        var MockEntity, MockPreviousState, MockNo_presente;
        var createController;

        beforeEach(inject(function($injector) {
            $rootScope = $injector.get('$rootScope');
            $scope = $rootScope.$new();
            MockEntity = jasmine.createSpy('MockEntity');
            MockPreviousState = jasmine.createSpy('MockPreviousState');
            MockNo_presente = jasmine.createSpy('MockNo_presente');
            

            var locals = {
                '$scope': $scope,
                '$rootScope': $rootScope,
                'entity': MockEntity,
                'previousState': MockPreviousState,
                'No_presente': MockNo_presente
            };
            createController = function() {
                $injector.get('$controller')("No_presenteDetailController", locals);
            };
        }));


        describe('Root Scope Listening', function() {
            it('Unregisters root scope listener upon scope destruction', function() {
                var eventType = 'oficialiaElectoralApp:no_presenteUpdate';

                createController();
                expect($rootScope.$$listenerCount[eventType]).toEqual(1);

                $scope.$destroy();
                expect($rootScope.$$listenerCount[eventType]).toBeUndefined();
            });
        });
    });

});
