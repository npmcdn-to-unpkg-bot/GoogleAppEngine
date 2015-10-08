"use strict";

requirejs.config({
    //enforceDefine: true,

    baseUrl: '/js',

    paths: {
        //===used by index1.html
        index: '../html/index'
    },

    shim: {
        index: {
            exports: 'index'
        }
    },

    waitSeconds: 120     //timeout in seconds if not able to load any of the external URL above
});

//Start the main app logic.
require(['./main-config', './app'], function (common, app) {
    require(
        [
        'index', 'google'
        ,'storejs'
        ],
        function(
            index, google
            ,store
            ) {
            $(document).ready(function () {
                //
            });
        });
});