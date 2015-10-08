requirejs.config({
    //enforceDefine: true,

    baseUrl: '/js',

    paths: {
        storejs: '//cdnjs.cloudflare.com/ajax/libs/store.js/1.3.14/store.min',
        jquery: ['//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery', 'jquery-1.9.1'],    //for some reason, this is needed for IE 8 otherwise  'jQuery' is undefined or  '$' is undefined will occur
        movieApp: ['../html/migrate'],
        initUser: ['../parse/init']
        //,
//        popup: 'jquery.bpopup.min'
    },

    shim: {
        storejs: {
            exports: 'storejs'
        },
        jquery: {
            exports: '$'
        },
//        popup: {
//            deps: ['jquery'],
//            exports: '$'
//        },
        movieApp: {
            deps: ['jquery', 'parse', 'angular']
        },
        initUser: {
            deps: ['movieApp']
        }
    },

    waitSeconds: 120     //timeout in seconds if not able to load any of the external URL above
});

//define('main-movie', [], function() {
//    //does nothing for now - just to suppress "Uncaught Error: No define call for ..." error
//    return function() {
//    };
//});

//Start the main app logic.
require(['./main-config', './app'], function (common, app) {
    require(
        ['jquery', 'purl', 'parse', 'facebook', 'angular',
            //'loglevel',
            'prefs',
            'movieApp', 'initUser'
            //, 'popup'
            , 'storejs'
        ], function (
        $, purl, Parse, facebook, angular,
            //loglevel,
            prefs,
            movie, init
            //, popup
            , store
        ) {
            'use strict';
            $(document).ready(function () {
                //=== begin store.js initialization
                //(function() {
                //        try {
                //            if(store === undefined) {
                //                //stupid workaround! :(
                //            }
                //        } catch(e) {
                //            alert('Not able to access your local storage. Fatal error.');
                //            return;
                //        }
                //        if (!store.enabled) {
                //            alert('Local storage is not supported by your browser. Please disabled "Private Mode", or upgrade to a modern browser')
                //            return;
                //        }
                //        //=== BEGIN store.js magic!!!
                //        var msg;
                //        var logintype;
                //        var userid;
                //        restoreStates(store);
                //        msg = 'in init.js: userid [' + userid + '] logintype [' + logintype + "]";
                //        window.console && console.log(msg);
                //        //alert(msg);
                //        window.console && console.log("init.js store.js initialized!");
                //        //=== END store.js magic!!!
                //})();
                //=== end store.js initialization

                $('body').attr('ng-controller', 'ctrlRead');
                // Registering a controller after app bootstrap
//                    $controllerProvider.register('ctrlRead', ctrlRead);
                angular.bootstrap(document);

            });

                window.console && console.log("main-migrate: config initialized (RequireJS).");
    }, function (err) {
        //=== http://requirejs.org/docs/api.html#ieloadfail
        //The errback, error callback
        //The error has a list of modules that failed
//        var failedId = err.requireModules && err.requireModules[0];
//        if (failedId === 'jquery') {
//            //undef is function only on the global requirejs object.
//            //Use it to clear internal knowledge of jQuery. Any modules
//            //that were dependent on jQuery and in the middle of loading
//            //will not be loaded yet, they will wait until a valid jQuery
//            //does load.
//            requirejs.undef(failedId);
//
//            //Set the path to jQuery to local path
//            requirejs.config({
//                baseUrl: '/js',
//                paths: {
//                    jquery: 'jquery-1.9.1'
//                }
//            });
//
//            //Try again. Note that the above require callback
//            //with the "Do something with $ here" comment will
//            //be called if this new attempt to load jQuery succeeds.
//            require(['jquery'], function () {});
//        } else {
//            //Some other error. Maybe show message to the user.
//        }
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