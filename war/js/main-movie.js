var gStore;

requirejs.config({
    //enforceDefine: true,

    baseUrl: '/js',

    paths: {
        jquery: ['//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery', 'jquery-1.9.1'],    //for some reason, this is needed for IE 8 otherwise  'jQuery' is undefined or  '$' is undefined will occur
//        jQueryUI: '//ajax.aspnetcdn.com/ajax/jquery.ui/1.9.1/jquery-ui.min',
        movieApp: ['../mcrud/movie'],
//        lazyImage: ['../bower_components/afkl-lazy-image/release/lazy-image.min'],
        calendarApp: '../html/calendar',
        initUser: ['../parse/init']
        //,
//        popup: 'jquery.bpopup.min'
    },

    shim: {
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
//        jQueryUI: {
//            deps: ['jquery']
//        },
        calendarApp: {
            deps: ['jquery', 'parse'
//                , 'fullCalendar',
//                   ,'jQueryUI'
            ]
        },
        initUser: {
            deps: ['movieApp']
        }
    },

    waitSeconds: 120     //timeout in seconds if not able to load any of the external URL above
});

//Start the main app logic.
require(['./main-config', './app'], function (common, app) {
    require(
        ['jquery', 'purl', 'parse', 'facebook', 'angular',
//            'angularAnimate',
            //'loglevel',
            'prefs',
            'movieApp',
            'calendarApp',
            'dateFormat',
            'initUser',
            //'summernote',
            //'angularSummernote',
            'storejs'
            //, 'popup'
        ], function (
        $, purl, Parse, facebook, angular,
//        angularAnimate,
            //loglevel,
            prefs,
            movie,
            calendar,
            dateFormat,
            init,
            //summernote,
            //angularSummernote,
            store
            //, popup
        ) {
//        requirejs(['domReady!'], function (doc) {
            //This function is called once the DOM is ready,
            //notice the value for 'domReady!' is the current
            //document.
            $(document).ready(function () {
                //=== show the My Account
                if($.url().param('logintype') === '1') {
                    $("#myaccount").show();
                }

//                alert('main-movie.js store [' + store + "]");    //unfortunately store is bootrappd in global namespace (c.f. main-config)
                //TODO should not need global with AMD!
                gStore = store;


            });

            angular.element(document).ready(function() {
                try {
                    console.log("main-movie.js angular.boostrap() begin 2e ...");
                    // Registering a controller after app bootstrap
                    //$controllerProvider.register('MovieController', MovieController);
                    //$('body').attr('ng-controller', 'MovieController');
                    angular.bootstrap(document, ['app']);    //only for 1.3
                    //angular.bootstrap(document);    //only for 1.2 - this is the limitation of not able to upgrade to 1.3!
                    console.log("main-movie.js angular.boostrap() end");
                } catch (e2) {
                    alert('main-movie.js AngularJS 1.2 angular.bootstrap() 2 error [' + e2 + ']');
                }

                //=== courtesy of http://jsfiddle.net/codef0rmer/hvf6X/
                //angular.module('app').run(function($rootScope, $scope) {
                //});

                //window.console && console.log("main-index: AngularJS initialized (RequireJS).");
            });

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

//requirejs.onError = function (err) {
//    window.console && console.log($sce.trustAsHtml(err.requireType));
//    if (err.requireType === 'timeout') {
//        window.console && console.log('modules: ' + $sce.trustAsHtml(err.requireModules));
//        alert("We're sorry, we are having a connection issue and was not able to load the scripts. Please try again later.");
//    }
//
//    throw err;
//};