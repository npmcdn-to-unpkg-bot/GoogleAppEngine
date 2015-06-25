"use strict";

var currentUserName;  //global name of Facebook username
    //var appCache = window.applicationCache;
    //appCache.update(); // Attempt to update the user's cache.
    /*if (appCache.status == window.applicationCache.UPDATEREADY) {
     appCache.swapCache();  // The fetch was successful, swap in the new cache.
     }*/
    //Be nice to the user :) But sadly for IE, 10+ only
    if(typeof applicationCache !== "undefined") {
        applicationCache.onupdateready = function() {
            if (confirm("A new version of this application is available. Would you like to reload now?")) {
                window.location.reload();
            }
        }
    }

    //=== TBD these should be refactored into common codes!!!! ===========================================================
    var userid = 'Unknown';
    var logintype;
    //alert('in init.js: Parse.User [' + Parse.User + '] Parse.User.current() [' + Parse.User.current() + '] Parse.User.current().getUsername() [' + Parse.User.current().getUsername() + ']');
    if(App.isValidSession()) {
        userid = Parse.User.current().getUsername();
    } else
    //alert('init.js: userid [' + userid + ']');
    if(userid === 'Unknown') {
        //userid = $.url().param('username');     //security risk here if subjected to brute force attack!
        alert(App.session_expired_msg + "We are sorry that you have to be redirected to the login page.");
        //alert(App.session_expired_msg + " uid [" + Parse.User.current().getUsername() + "]");
        location.href = App.login_url;
    }
    //=== TBD these should be refactored into common codes!!!! ===========================================================

    if($.url().param('logintype') === '2') {
        $('#currentUser').html("Signed In via Facebook (" + userid + ")");
        $('#currentUserName').html($.url().param('username'));  //just the display, not to pull data, so no security risk here :)
    } else
    if($.url().param('logintype') === '3') {
        $('#currentUser').html("Signed In via Google Plus (" + userid + ")");
        $('#currentUserName').html(userid);  //just the display, not to pull data, so no security risk here :)
    } else
    if($.url().param('logintype') === '4') {
        $('#currentUser').html("Signed In via Twitter (" + userid + ")");
        $('#currentUserName').html(userid);  //just the display, not to pull data, so no security risk here :)
    } else
    //TODO begin common account has to be before the above account, just in case the user does not logout
    if($.url().param('logintype') === '1') {
        $('#currentUser').html("User ID: "+ userid + " ");
        $('#currentUserName').html(userid);  //just the display, not to pull data, so no security risk here :)
    }
    //TODO end common account has to be before the above account, just in case the user does not logout
