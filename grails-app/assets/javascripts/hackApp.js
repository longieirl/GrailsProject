var hackApp = angular.module('hackApp', []);

// Greeting filter
hackApp.filter('greet', function() {
    return function(name) {
        return 'Hello, ' + name + '!';
    };
});