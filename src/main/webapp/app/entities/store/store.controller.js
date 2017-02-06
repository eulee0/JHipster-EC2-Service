(function() {
    'use strict';

    angular
        .module('cs499A2App')
        .controller('StoreController', StoreController);

    StoreController.$inject = ['$scope', '$state', 'Store'];

    function StoreController ($scope, $state, Store) {
        var vm = this;

        vm.stores = [];

        loadAll();

        function loadAll() {
            Store.query(function(result) {
                vm.stores = result;
                vm.searchQuery = null;
            });
        }
    }
})();
