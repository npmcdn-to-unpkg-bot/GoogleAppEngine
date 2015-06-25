requirejs.config({
    baseUrl: '/js',

    deps: ['jquery','gapi'],

    paths: {
        jquery: ['//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery', 'jquery-1.9.1']    //for some reason, this is needed for IE 8 otherwise  'jQuery' is undefined or  '$' is undefined will occur
    },

    waitSeconds: 90     //timeout in seconds if not able to load any of the external URL above
});

requirejs([
    'jquery',
    'gapi'
    ],
    function($, ApiManager) {
        var App = function() {
            this.connectGapi();
        };

        App.prototype = {
            connectGapi: function() {
                this.apiManager = new ApiManager();
            }
        };

        return App;
    }
);

//requirejs(
//    ['gapi'],
//    function   (gapi) {
//        gapi.client.load('plus','v1', function(){
//            // once we get this call back, gapi.client.plus.* will exist
//            console.log("Google Plus initialized (RequireJS:config.js https://www.googleapis.com/auth/plus.me).");
//        });
//    }
//);

//define([
//    'angular'
//], function (angular) {
//    'use strict';
//    return angular.module('app', []);
//});
