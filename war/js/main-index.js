"use strict";

requirejs.config({
    //enforceDefine: true,

    baseUrl: '/js',

    paths: {
//        jquery: ['//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery', 'jquery-1.9.1'],    //for some reason, this is needed for IE 8 otherwise  'jQuery' is undefined or  '$' is undefined will occur
        //===used by index1.html
        index: '../html/index'
        //,
//        facebook: '../parse/facebook',
//        plusone:  '//apis.google.com/js/plusone',
//        gapi: 'https://apis.google.com/js/client.js?onload=load',
//        google: '../js/google',
//        sha1: '../js/sha1',
        //=== commented out, currently this outh 2.0 workaround does not work (caused: Mismatched anonymous define() module error)
//        codebird: '../js/codebird',
//        twitter: '../js/twitter'
    },

    shim: {
//        jquery: {
//            exports: '$'
//        },
        index: {
            exports: 'index'
        }
//        //run as specified in the following orders
//        facebook: {
//            deps: ['jquery', 'parse'],
//            exports: 'facebook'
//        },
//        plusone: {
//            deps: ['jquery'],
//            exports: 'plusone'
//        },
//        gapi: {
//            deps: ['jquery'],
//            exports: 'gapi'
//        },
//        google: {
//	        deps: ['jquery', 'plusone', 'gapi'],
//	        exports: 'google'
//	    },
//        sha1: {
//            exports: 'sha1'
//        },
        //=== commented out, currently this outh 2.0 workaround does not work (caused: Mismatched anonymous define() module error)
//        codebird: {
//            deps: ['sha1'],
//            exports: 'codebird'
//        },
//        twitter: {
//            deps: ['codebird'],
//            exports: 'twitter'
//        }
    },

    waitSeconds: 120     //timeout in seconds if not able to load any of the external URL above
});

//define('main-index', [], function() {
//    //does nothing for now - just to suppress "Uncaught Error: No define call for ..." error
//    return function() {
//    };
//});

//Start the main app logic.
require(['./main-config', './app'], function (common, app) {
    require(
        [
//            'jquery',
            'purl', 'parse', 'facebook',
//            'angular',
            'prefs',
        'index', 'google'
        //,
        //'twitter',
        //'loglevel'
        ,'storejs'
        ],
        function(
//            $,
            purl, Parse, facebook,
//            angular,
            prefs,
            index, google
            //,
            //twitter,
            //loglevel
            ,store
            ) {
            $(document).ready(function () {
                $('#offlineStatus').hide();
//            $('#captchaForm').show();
//            requirejs(['//www.google.com/recaptcha/api/js/recaptcha_ajax.js'], function() {
//                var public_key;
//                
//                if(location.hostname.indexOf('cbiit') > -1 || location.hostname.indexOf('cadsr') > -1) {
//                	public_key = '6Ld4gOYSAAAAAIhBHyv2iH1Zej_1LVmoNdzXgEhp';
//                }
//                else
//                if(location.hostname.indexOf('hudoone') > -1) {
//                	public_key = '6LfNgeESAAAAAAppEbVa-VNLv2FlNVS0loNmvy-h';
//                }
//                else
//                if(location.hostname.indexOf('service') > -1 || location.hostname.indexOf('share') > -1) {
//                	public_key = '6LfOiMASAAAAAO3mxDZRXIkA2t16nx4_LJzGWtyF';
//                }
//                else
//                if(location.hostname.indexOf('localhost') > -1) {
//                	public_key = '6LfOiMASAAAAAO3mxDZRXIkA2t16nx4_LJzGWtyF';
//                }
//                
//                Recaptcha.create(public_key, 'captchadiv', {
//                    tabindex: 1,
//                    theme: "clean"
//                    //,
//                    //callback: Recaptcha.focus_response_field
//                });
//            });

                    //=== logout an existing session
                    logoutWithoutPrompt();

                    var el = $('#username');
                    el.removeAttr("disabled");
                    $('#password').removeAttr("disabled");
                    //$('.facebook').show();
                    //$('.twitter').show();
                    //console.assert && console.assert(document.getElementById("username"), "bad: username [" + document.getElementById("username") + "]");
                    //console.assert && console.assert(document.getElementById("password"), "bad: password [" + document.getElementById("password") + "]");
                    el.focus();
                    purgeStates();
//                    alert('main-index.js store [' + store + "]");    //unfortunately store is bootrappd in global namespace (c.f. main-config)
                }
            );
        });
});