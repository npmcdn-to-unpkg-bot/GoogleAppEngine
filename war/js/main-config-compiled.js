"use strict";

//username = Parse.User && Parse.User.current() && Parse.User.current().getUsername();
var gRecurringButtonTitle = "Recurring";
var gCalendar;
var gOriginalEventObject;
var gTempEventId;
var gEventArray = [];
var gHref;
var App = {
    major: '9',
    minor: '0103b',
    header_index: 0, //metadata header
    movie_index: 1, //the real movies data
    login_url: '/ui/index.html',
    ytplayer: {},
    session_expired_msg: 'Your session is invalid! You should logout and sign in again. ',
    isValidSession: function isValidSession() {
        var ret = false;
        try {
            var uid = Parse.User.current().getUsername().trim() !== '';
            ret = true;
        } catch (e) {
            //whatever, it does not matter
        }
        return ret;
    },
    gaej_server_error_msg: 'The server encountered an error and could not complete your request.<p>Please try again in 30 seconds.',
    /* the following error/message should be in sync with server's response!!! */
    NO_PARENT_ERR: 'no_user_found', NO_PARENT_ERR_MSG: 'Your user id is not configured correctly locally, please contact us and we will get it fixed. Click OK will redirect you to the login page.'
};
var gRelease = "[Release label will be replaced by spreasheet]";
var gBuild = "(Build " + App.major + "-" + App.minor + ")";
//define('main-config', [], function() {
//    //does nothing for now - just to suppress "Uncaught Error: No define call for ..." error
//    return function() {
//    };
//});

//TODO needs to be in a module!

typeof requirejs !== 'undefined' && requirejs.config({
    //To get timely, correct error triggers in IE, force a define/shim exports check.
    //enforceDefine: true,

    //urlArgs : "v0.0.2",   //http://stackoverflow.com/questions/9943041/avoid-url-cache-busting-parameters-with-requirejs-for-cdn

    baseUrl: '/js',

    paths: {
        //===common to all
        storejs: ['store.min', '//cdnjs.cloudflare.com/ajax/libs/store.js/1.3.14/store.min'],
        //===used by index1.html
        //        jquery: ['//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min', 'jquery-1.9.1'],
        jquery: ['//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery', 'jquery-1.9.1'],
        purl: '../jquery/purl',
        parse: 'https://www.parsecdn.com/js/parse-1.2.7.min',
        facebook: '../parse/facebook',
        gapi: 'https://apis.google.com/js/client.js?onload=load',
        google: 'google',
        angularBootstrap: '../js/ui-bootstrap-tpls-0.11.2.min',
        //===used by movie.html
        //bootstrap: '//cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.2.0/js/bootstrap.min',
        angular: '//ajax.googleapis.com/ajax/libs/angularjs/1.4.1/angular',
        //angular: '//ajax.googleapis.com/ajax/libs/angularjs/1.3.0/angular',  //latest as at 4/6/2014
        //angular: '//ajax.googleapis.com/ajax/libs/angularjs/1.1.5/angular',
        //angular: '//ajax.googleapis.com/ajax/libs/angularjs/1.2.0/angular',    //with ability to turn off $sce!?
        //angular: '//ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular',    //with  net::ERR_CONNECTION_REFUSED
        //        angularSanitize: '//ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular-sanitize',    //http://stackoverflow.com/questions/19770156/how-to-output-html-through-angularjs-template
        //angularAnimate: '//ajax.googleapis.com/ajax/libs/angularjs/1.2.26/angular-animate.min',
        //summernote: '../js/summernote',
        //angularSummernote: '../js/angular-summernote.min',

        init: '../parse/init', //cache and user initialization
        dateFormat: 'dateFormat',
        //        modernizr: '//cdnjs.cloudflare.com/ajax/libs/modernizr/2.6.2/modernizr.min',
        modernizr: '//cdnjs.cloudflare.com/ajax/libs/modernizr/2.6.2/modernizr',

        //TRACE:0,DEBUG:1,INFO:2,WARN:3,ERROR:4,SILENT:5
        loglevel: ['//cdnjs.cloudflare.com/ajax/libs/loglevel/0.6.0/loglevel.min', 'loglevel'],
        prefs: '../js/prefs',
        appAssert: '../js/assert'
    },

    shim: {
        gapi: {
            exports: 'gapi'
        },
        jquery: {
            exports: '$'
        },
        parse: {
            deps: ["jquery"],
            exports: 'Parse'
        },
        facebook: {
            deps: ["jquery", "parse"],
            exports: 'facebook'
        },
        google: {
            deps: ["jquery", "gapi"],
            exports: 'google'
        },
        purl: {
            deps: ["jquery"],
            exports: '$'
        },
        init: {
            deps: ["facebook", "jquery", "parse", "purl"],
            exports: 'init'
        },
        dateFormat: {
            exports: 'dateFormat'
        },
        angularBootstrap: {
            deps: ["angular"],
            exports: 'bootstrap'
        },
        angular: {
            deps: ["parse", "purl"],
            exports: 'angular'
        },
        //        angularSanitize: {
        //            deps: ["angular"],
        //            exports : 'angular'
        //        },
        //        summernote: {
        //            deps: ["jquery"],
        //            exports : 'summernote'
        //        },
        //        angularSummernote: {
        //            deps: ["angular", "summernote"],
        //            exports : 'angularSummernote'
        //        },
        loglevel: {
            exports: 'log'
        },
        prefs: {
            deps: ["jquery"],
            exports: 'prefs'
        },
        storejs: {
            exports: 'store'
        }
    },
    priority: ["angular"],

    waitSeconds: 120 //timeout in seconds if not able to load any of the external URL above
});

function restoreStates(store) {
    if (store) {
        var msg;
        var storedLogintype;
        var storedUserId;
        try {
            storedLogintype = store.get('logintype');
            storedUserId = store.get('userid');
        } catch (e) {
            alert('main-config.js: Not able to access your local storage. Fatal error.');
        }

        if (typeof storedUserId !== 'undefined' && storedUserId.trim().length > 0) {
            userid = storedUserId;
            msg = 'in init.js: userid [' + userid + '] restored from storejs';
            //window.console && console.log(msg);
        } else {
                //            userid = $.url().param('username');
                //            msg = 'in init.js: userid [' + userid + '] restored from url';
                //            window.console && console.log(msg);
                //            store.set('userid', userid);
                //            msg = 'in init.js: [' + userid + '] saved through storejs';
                //            window.console && console.log(msg);
                //window.console && console.log("main-config.js 1: " + App.session_expired_msg);
                location.href = App.login_url;
            }
        if (typeof storedLogintype !== 'undefined' && storedLogintype.trim().length > 0) {
            logintype = storedLogintype;
            msg = 'in init.js: logintype [' + logintype + '] restored from storejs';
            //window.console && console.log(msg);
        } else {
                //            logintype = $.url().param('logintype');
                //            msg = 'in init.js: logintype [' + logintype + '] restored from url';
                //            window.console && console.log(msg);
                //            store.set('logintype', logintype);
                //            msg = 'in init.js: [' + logintype + '] saved through storejs';
                //            window.console && console.log(msg);
                //window.console && console.log("main-config.js 2: " + App.session_expired_msg);
                location.href = App.login_url;
            }
    }
}

function purgeStates() {
    if (typeof store !== 'undefined') {
        try {
            store.set('userid', '');
            store.set('logintype', '');
        } catch (e) {
            alert("main-config.js purgeStates error: Not able to purge userid and logintype as storejs, error: " + e);
        }
    }
    //else {
    //    console && console.log("main-config.js purgeStates error: Not able to purge userid and logintype as storejs is not valid!");
    //}
}

typeof requirejs !== 'undefined' && requirejs(['jquery', 'purl',
//'parse', 'facebook',
'angular',
//        'angularSanitize',  //1.3 does not have this
'angularBootstrap',
//        'summernote',
//        'angularSummernote',
'loglevel',
//'prefs'
'storejs', 'appAssert'], function ($, purl,
//Parse, facebook,
angular,
//        angularSanitize,	//1.3 does not have this
angularBootstrap,
//        angularAnimate,
//        summernote,
log,
//prefs
store, appAssert) {
    'use strict';
    $(document).ready(function () {
        //            //=== begin store.js initialization
        //            (function() {
        //                    try {
        //                        if(store === undefined) {
        //                            //stupid workaround! :(
        //                        }
        //                    } catch(e) {
        //                        alert('Not able to access your local storage. Fatal error.');
        //                        return;
        //                    }
        //
        if (!store.enabled) {
            alert('Local storage is not supported by your browser. Please disabled "Private Mode", or upgrade to a modern browser');
            return;
        }
        //                    //=== BEGIN store.js magic!!!
        //                    var msg;
        //                    var logintype;
        //                    var userid;
        //                    restoreStates(store);
        //                    msg = 'in init.js: userid [' + userid + '] logintype [' + logintype + "]";
        //                    window.console && console.log(msg);
        //                    //alert(msg);
        //                    window.console && console.log("init.js store.js initialized!");
        //                    //=== END store.js magic!!!
        //            })();
        //            //=== end store.js initialization
        //
        //            window.console && console.log("main-config: config initialized (RequireJS).");
    });
});
//        angularSummernote,

//# sourceMappingURL=main-config-compiled.js.map