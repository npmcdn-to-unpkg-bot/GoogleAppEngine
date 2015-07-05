requirejs.config({
    baseUrl: '/js',

//    deps: '../html/channel',

    paths: {
//      jquery: '//ajax.googleapis.com/ajax/libs/jquery/1.10.0/jquery',
        jquery: ['//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery', 'jquery-1.9.1'],    //for some reason, this is needed for IE 8 otherwise  'jQuery' is undefined or  '$' is undefined will occur
//        purl: '../jquery/purl',
//        parse: 'https://www.parsecdn.com/js/parse-1.2.7.min',
        //===used by channel.html
        galleria: ['../galleria/galleria-1.2.9.min'],
//        channelApp: ['../html/channel'],
        movieApp: ['../mcrud/movie'],
        calendarApp: '../html/calendar',
        countDown: '../jquery/jquery.countdown',
        initUser: ['../parse/init']
    },

    shim: {
        //run as specified in the following orders
        jquery: {
            exports: 'jquery'
        },
//        parse: {
//            deps: ["jquery"],
//            exports: 'Parse'
//        },
//        purl: {
//            deps: ["jquery"],
//            exports : '$'
//        },
//        angular: {
//            deps: ["parse", "purl"],
//            exports : 'angular'
//        },
        movieApp: {
            deps: ['jquery', 'parse', 'angular']
        },
        calendarApp: {
            deps: ['jquery', 'parse'
//                , 'fullCalendar',
//                   ,'jQueryUI'
            ]
        },
        initUser: {
            deps: ['movieApp']
        },
        galleria: {
            deps: ['jquery', 'initUser'],
            exports : 'galleria'
        },
        countDown: {
            deps: ["jquery"],
            exports : '$'
        }
//        channel: {
//            deps: ['galleria'],
//            exports : ['channel']
//        }
    },

    waitSeconds: 20     //timeout in seconds if not able to load any of the external URL above
});

//Start the main app logic.
requirejs(['./main-config', './app'], function (common, app) {
        requirejs(
        ['jquery', 'purl', 'parse', 'facebook', 'angular', 'loglevel', 'prefs',
            'movieApp',
            'calendarApp',
            'initUser', 'galleria', '../html/channel', 'countDown'], function (
        $, purl, Parse, facebook, angular, loglevel, prefs,
            movie,
            calendar,
            init, galleria, channel, countDown) {
            $(document).ready(function() {
                //loglevel.info("ready!");
                $('body').attr('ng-controller', 'MovieController');
                //noinspection JSCheckFunctionSignatures
                angular.bootstrap(document);
                //loglevel.info("6.11AngularJS initialized (RequireJS).");

                //=== TBD these should be refactored into common codes!!!! ===========================================================
                var username = 'Unknown';
                if(App.isValidSession()) {
                    username = Parse.User.current().getUsername();
                }
                if(username === 'Unknown') {
//                    username = $.url().param('username');     //TBD - might have security risk here if subjected to brute force attack
                    location.href = App.login_url;
                    alert(App.session_expired_msg + "We are sorry that you have to be redirected to the login page.");
//                    alert(App.session_expired_msg + " uid [" + Parse.User.current().getUsername() + "]");
                    location.href = App.login_url;
                }
                //=== TBD these should be refactored into common codes!!!! ===========================================================

                if(username && loadMovie(username)) {
                    $('#progress').hide();
                    window.console && console.info("channel UI loaded");
                } else {
                    username = $.url().param('username');       //TBD security risk - need to get from third party API directly
                    alert("username [" + username + "]");
                }

                //var playTime = new Date ( 2013, 7-1, 27, 14, 13, 0 );
//                var liftoffTime = new Date ( 2013, 8-1, 23, 21, 51, 40 );
                var el = $("#playingOrsoon");
                var tempDateStr = el.attr("data-datetime");
                var subtitle = el.attr("data-subtitle");
                var eventId = el.attr("data-eventid");
                var movieId = el.attr("data-movieid");
                var dateStr = tempDateStr.split(",");
                var liftoffTime = new Date ( dateStr[0], dateStr[1], dateStr[2], dateStr[3], dateStr[4], dateStr[5] );
                var youtubeUrl = el.val();
                //var liftoffTime = new Date();
                //liftoffTime.setDate(liftoffTime.getDate() + 5);
                $('#noDays').countdown({until: liftoffTime, onExpiry: function () {
                    //alert('We have lift off!');
                    window.location= "/html/yt.html?eventid=" + eventId + "&movieid=" + movieId + "&url=" + youtubeUrl + "&uid=" + username + "&sub=" + subtitle;	//https://www.youtube.com/embed/WsPGho7ZE9Q";
                } , compact: true});
            });
    });
});
