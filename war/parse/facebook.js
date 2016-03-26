"use strict";
//Facebook stuff
var fbBuild = "0033";     //Facebook is huge, it even has its own build #!
var gMovieURL = '/mcrud/movie.html';
//gLogoutURL = '/parse/index1.html';
var gLogoutURL = App.login_url;

function logout() {
    var r = confirm("Are you sure? This will log you out from the application.");
    if (r === false)
    {
        //x="You pressed Cancel!";
        return;
    }

    doLogout();

    //window.console && console.log("facebook.js logout() invoked!");
}

function logoutWithoutPrompt() {
    logoutParse();

    //TODO clean up the states saved with store.js here
    if(typeof store !== 'undefined') {
        purgeStates();
        store.clear();
    }

    //window.console && console.log("facebook.js logoutWithoutPrompt() invoked!");
}

function doLogout() {
    logoutParse();

//    if(currentGoogleUserName !== undefined && currentGoogleUserName !== null) {
    try {
        var access_token = $.url().param('access_token');
        //window.console && console.log("2google plus access token = [" + access_token + "]");
        if(access_token) {
            var revokeUrl = 'https://accounts.google.com/o/oauth2/revoke?token=' +
                access_token;
            $.ajax({
                type: 'GET',
                url: revokeUrl,
                async: false,
                contentType: "application/json",
                dataType: 'jsonp',
                success: function(data) {
                    // Do something now that user is disconnected
                    // The response is always undefined.
                    //alert("You have been disconnected from Google Plus!");
                    //alert("Disconnected from Google Plus with access token " + access_token);
                    //currentGoogleUserName = '';
                    window.location=gLogoutURL;
                },
                error: function(e) {
                    // Handle the error
                    // console.log(e);
                    // You could point users to manually disconnect if unsuccessful
                    // https://plus.google.com/apps
                    //window.console && console.log("Google plus disconnect error: " + e.message + "]");
                    alert("G+ logout error: " + e.message);
                }
            });
        }
    } catch(e) {
        window.console && console.log("Not able to logout Google Plus!");
    }
//    }

    $("#gp-login-notice").hide();

    //JWT to clear the token!
    localStorage.removeItem('2shareJWTToken');
    purgeStates();

    window.location=gLogoutURL;
}

function logoutParse() {
    //debugger

    if(typeof Parse !== 'undefined' && typeof Parse.User.current() !== 'undefined') {
        Parse.User.logOut();
//        try {
//            FB.logout();    //as we are using the native Facebook <fb:login-button ...
//        } catch(e) {
//            window.console && console.log("Not able to logout Facebook!");
//        }
    }
}
