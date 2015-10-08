requirejs.config({
    baseUrl: '/js',

    paths: {
//        jquery: '//ajax.googleapis.com/ajax/libs/jquery/1.10.0/jquery',
        jquery: ['//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery', 'jquery-1.9.1'],    //for some reason, this is needed for IE 8 otherwise  'jQuery' is undefined or  '$' is undefined will occur
        movieApp: ['../mcrud/movie'],
        //===used by calendar.html
//        jQueryUI: '../jquery/jquery-ui-1.10.2.custom.min',
        jQueryUI: '//ajax.aspnetcdn.com/ajax/jquery.ui/1.9.1/jquery-ui.min',
        fullCalendar: '../fullcalendar/fullcalendar.min',
        calendarApp: '../html/calendar',
//        facebook: '../parse/facebook'
        initUser: ['../parse/init']
    },

    shim: {
        //run as specified in the following orders
        movieApp: {
            deps: ['jquery', 'parse', 'angular']
        },
        initUser: {
            deps: ['movieApp']
        },
        fullCalendar: {
            deps: ['jquery']
        },
        jQueryUI: {
            deps: ['jquery']
        },
        calendarApp: {
            deps: ['jquery', 'parse', 'fullCalendar', 'jQueryUI']
        }
    },

    waitSeconds: 240     //timeout in seconds if not able to load any of the external URL above
});


//Start the main app logic.
requirejs(['./main-config', './app'], function (common, app) {
    requirejs(
        ['jquery', 'purl', 'parse', 'facebook', 'angular', 'loglevel', 'prefs',
            'movieApp', 'initUser', 'fullCalendar', 'calendarApp', 'dateFormat', 'loglevel'], function (
        $, purl, Parse, facebook, angular, log, prefs,
            movie, init, fullCalendar, calendar, dateFormat, loglevel) {
        $(document).ready(function () {
            $('body').attr('ng-controller', 'MovieController');
            angular.bootstrap(document);
            // Registering a controller after app bootstrap
//            $controllerProvider.register('ctrlRead', ctrlRead);
            //loglevel.info("5.11AngularJS initialized (RequireJS).");

            initCalendar();
        });
    });
});
