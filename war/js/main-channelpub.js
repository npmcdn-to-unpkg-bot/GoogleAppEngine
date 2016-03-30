requirejs.config({
    paths: {
        jquery: ['//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery', 'jquery-1.9.1'],    //for some reason, this is needed for IE 8 otherwise  'jQuery' is undefined or  '$' is undefined will occur
        purl: '../jquery/purl',
        galleria: ['../galleria-1.2.9/galleria-1.2.9.min'],
        subsTitle: ['../jquery/YoutubeSubtitiles'],
        channelApp: ['../html/channel']
    },

    shim: {
        //run as specified in the following orders
        jquery: {
            exports: 'jquery'
        },
        purl: {
            deps: ["jquery"],
            exports : '$'
        },
        subsTitle: {
            deps: ['jquery', 'purl']
        },
        channelApp: {
        	deps: ['galleria', 'subsTitle']
        },
        galleria: {
            deps: ['jquery'],
            exports : 'galleria'
        },
        countDown: {
            deps: ["jquery"],
            exports : '$'
        }
    },

    waitSeconds: 120     //timeout in seconds if not able to load any of the external URL above
});

//Start the main app logic.
require(['./main-config', './app'], function (common, app) {
        require(
            ['jquery', 'purl', 'parse', 'facebook',
             	'loglevel',
                'galleria', 'subsTitle', 'channelApp'], function (
            $, purl, Parse, facebook,
            	log,
            	galleria, subsTitle, channel) {
            $(document).ready(function() {

                //=== http://support.galleria.io/discussions/questions/1502-stop-autoplay-on-any-kind-of-interaction
                (function(){
                    var s = document.createElement("script");
                    s.src = "https://www.youtube.com/player_api"; /* Load Player API*/
                    var before = document.getElementsByTagName("script")[0];
                    before.parentNode.insertBefore(s, before);
                })();

                //noinspection CommaExpressionJS
                if(loadMoviePub('pub')) {
                    $('#progress').hide();
                    //console && console.log("main-channelpub: channel UI loaded");
                }
            });
    });
});
