requirejs.config({
    baseUrl: '/js',

//    deps: '../html/channel',

    paths: {
        jquery: ['//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery', 'jquery-1.9.1'],    //for some reason, this is needed for IE 8 otherwise  'jQuery' is undefined or  '$' is undefined will occur
        purl: '../jquery/purl',
        //===used by channel.html
        galleria: ['../galleria-1.2.9/galleria-1.2.9.min'],     //Note: remember to change GALLERIA_VERSION in channel.js too!!!
        //galleria: ['../galleria-1.4.2/galleria-1.4.2.min'],   //Note: remember to change GALLERIA_VERSION in channel.js too!!!
//        movieApp: ['../mcrud/movie'],
        subsTitle: ['../jquery/YoutubeSubtitiles'],
        channelApp: ['../html/channel']
    },

    shim: {
        //run as specified in the following orders
        jquery: {
            exports: '$'
        },
        parse: {
            deps: ["jquery"],
            exports: 'Parse'
        },
        purl: {
            deps: ["jquery"],
            exports : '$'
        },
//        angular: {
//            deps: ["parse", "purl"],
//            exports : 'angular'
//        },
//        movieApp: {
//            deps: ['jquery', 'parse', 'angular']
//        },
        subsTitle: {
            deps: ['jquery', 'purl', 'channelApp']
        },
        channelApp: {
        	deps: ['parse', 'prefs', 'galleria']
        },
//        initUser: {
//            deps: ['movieApp']
//        },
        galleria: {
            deps: ['jquery'],
            exports : 'galleria'
        },
        countDown: {
            deps: ["jquery"],
            exports : '$'
        },
        storejs: {
            exports: 'store'
        }
    },

    waitSeconds: 60     //timeout in seconds if not able to load any of the external URL above
});

//Start the main app logic.
require(['./main-config', './app'], function (common, app) {
        require(
            ['jquery', 'purl',
                'parse',
                'facebook',
                //'angular', 
             	'loglevel', 'prefs',
                //'movieApp', 
                //'initUser',
                'storejs',
                'galleria', 'subsTitle', 'channelApp'], function (
            $, purl,
                parse,
                facebook,
            	//angular,
            	log, prefs,
                //movie, 
                //init,
                store,
            	galleria, subsTitle, channel) {
            //$(document).ready(function() {
                //log.info("ready!");
//                $('body').attr('ng-controller', 'ctrlRead');
//                angular.bootstrap(document);
                //log.info("6.11AngularJS initialized (RequireJS).");

                //=== http://support.galleria.io/discussions/questions/1502-stop-autoplay-on-any-kind-of-interaction
                (function(){
                    var s = document.createElement("script");
                    s.src = "https://www.youtube.com/player_api"; /* Load Player API*/
                    var before = document.getElementsByTagName("script")[0];
                    before.parentNode.insertBefore(s, before);
                })();
                //noinspection CommaExpressionJS
                var username = store.get('userid');
                if(typeof username === 'undefined') {
                    alert('main-channelshuffle.js username is undefined!');
                }
                if(loadMovieShuffle(username)) {
                    $('#progress').hide();
                    console && console.log("main-channelshuffle: channel UI loaded");
                }
            });
    //});
});
