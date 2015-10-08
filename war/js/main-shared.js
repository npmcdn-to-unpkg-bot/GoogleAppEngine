requirejs.config({
    baseUrl: '/js',

    paths: {
        movieApp: ['../html/shared'],
        initUser: ['../parse/init'],
        popup: 'jquery.bpopup.min'
    },

    shim: {
        popup: {
            deps: ['jquery'],
            exports: '$'
        },
        movieApp: {
            deps: ['jquery', 'parse', 'angular']
        },
        initUser: {
            deps: ['movieApp']
        }
    },

    waitSeconds: 20     //timeout in seconds if not able to load any of the external URL above
});

//Start the main app logic.
require(['./main-config', './app'], function (common, app) {
    require(
        ['jquery', 'purl', 'parse', 'facebook', 'angular', 'prefs',
            'movieApp', 'initUser', 'loglevel', 'popup'], function (
        $, purl, Parse, facebook, angular, prefs,
            movie, init, log, popup) {
//        requirejs(['domReady!'], function (doc) {
            //This function is called once the DOM is ready,
            //notice the value for 'domReady!' is the current
            //document.
            log.info("main-shared: AngularJS initialized (RequireJS).");

            $(document).ready(function () {
                $('body').attr('ng-controller', 'ctrlRead');
                // Registering a controller after app bootstrap
//                $controllerProvider.register('ctrlRead', ctrlRead);
                angular.bootstrap(document);
                log.info("main-movie: AngularJS initialized (RequireJS).");
            });
    }, function (err) {
        //=== http://requirejs.org/docs/api.html#ieloadfail
        //The errback, error callback
        //The error has a list of modules that failed
        var failedId = err.requireModules && err.requireModules[0];
        if (failedId === 'jquery') {
            //undef is function only on the global requirejs object.
            //Use it to clear internal knowledge of jQuery. Any modules
            //that were dependent on jQuery and in the middle of loading
            //will not be loaded yet, they will wait until a valid jQuery
            //does load.
            requirejs.undef(failedId);

            //Set the path to jQuery to local path
            requirejs.config({
                baseUrl: '/js',
                paths: {
                    jquery: 'jquery-1.9.1'
                }
            });

            //Try again. Note that the above require callback
            //with the "Do something with $ here" comment will
            //be called if this new attempt to load jQuery succeeds.
            require(['jquery'], function () {});
        } else {
            //Some other error. Maybe show message to the user.
        }
    });
});

requirejs.onError = function (err) {
    window.console && console.log(err.requireType);
    if (err.requireType === 'timeout') {
        window.console && console.log('modules: ' + err.requireModules);
        alert("We're sorry, we are having a connection issue and was not able to load the scripts. Please try again later.");
    }

    throw err;
};