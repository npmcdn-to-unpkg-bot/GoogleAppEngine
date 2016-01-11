"use strict";
//Facebook stuff
var fbBuild = "0033";     //Facebook is huge, it even has its own build #!
var gMovieURL = '/mcrud/movie.html';
//gLogoutURL = '/parse/index1.html';
var gLogoutURL = App.login_url;

//TBD not able to put this in AMD and make it to work, thus it is here! :(
Parse.initialize("Ld70ODLxjXFkhhRux6kQqVCiJ4rQeXU8dISafNJa", "dLHhDNKnLimXHzSzxvQcGuwle5iLwnn3bFahDS9q");
//window.console && console.log("main-config: Parse initialized (facebook.js).");
//=== https://www.parse.com/tutorials/session-migration-tutorial
Parse.User.enableRevocableSession();

(function(d, debug){
    var js, id = 'facebook-jssdk', ref = d.getElementsByTagName('script')[0];
    if (d.getElementById(id)) {return;}
    js = d.createElement('script'); js.id = id; js.async = true;
    js.src = "//connect.facebook.net/en_US/all" + (debug ? "/debug" : "") + ".js";
    ref.parentNode.insertBefore(js, ref);
}(document, false));

window.fbAsyncInit = function() {
    //initialize facebook SDK via parse
    var realAppId;
    if(location.hostname.indexOf('cbiit') > -1 || location.hostname.indexOf('cadsr') > -1) {
        realAppId = '1391742604391122';
    }
    else
    if(location.hostname.indexOf('teev.rhc') > -1) {
        realAppId = '395343103935527';
    }
    else
    if(location.hostname.indexOf('oo.tv') > -1 || location.hostname.indexOf('hudoone') > -1) {
        realAppId = '522052734496612';
    }
    else
    if(location.hostname.indexOf('service') > -1) {
        realAppId = '333890893403449';
    }
    else
    if(location.hostname.indexOf('share') > -1) {
        realAppId = '351597214975046';
    }
    else
    if(location.hostname.indexOf('aware') > -1) {
        realAppId = '1434646580086725';
    }
    else
    if(location.hostname.indexOf('localhost') > -1) {
        realAppId = '149411878572809';
    }
    else {
    	window.console && console.log("ERROR: No appdomain registered with Facebook, please visit https://developers.facebook.com/apps to create one!!!");
        realAppId = '149411878572809';
    }

    Parse.FacebookUtils.init({
        status: false,  //https://parse.com/questions/what-does-a-non-null-parseusercurrent-guarantee
        'appId'      : realAppId,
        'channelUrl' : '/channel.html',
        'cookie'     : true,
        'xfbml'      : true
    });
//  status     : true, // check the login status upon init?
    //Code to be executed after initializing the Facebook SDK.
};

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
//    if(store !== undefined) {
//        store.clear();
//    }

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

    //try {
    //    //window.console && console.log("removing app client session ...");
    //    var removeSessionUrl = '/jsp/logout2Share.jsp';
    //    $.ajax({
    //        type: 'GET',
    //        url: removeSessionUrl,
    //        async: false,
    //        success: function(data) {
    //            //purgeStates();
    //            window.location=gLogoutURL;
    //        },
    //        error: function(e) {
    //            window.console && console.log("session cleanup error: " + e.message + "]");
    //            alert("Logout error: " + e.message + ". Please try again.");
    //
    //        }
    //    });
    //} catch(e) {
    //    window.console && console.log("Not able to logout and clean up the server side session, error: " + e);
    //}
    //===TODO JWT to clear the token!

    window.location=gLogoutURL;

    //window.location=gLogoutURL;
}

function logoutParse() {
    if(Parse.User.current() !== undefined) {
        Parse.User.logOut();
//        try {
//            FB.logout();    //as we are using the native Facebook <fb:login-button ...
//        } catch(e) {
//            window.console && console.log("Not able to logout Facebook!");
//        }
    }
}

//define('facebook', [], function() {
//    //does nothing for now - just to suppress "Uncaught Error: No define call for ..." error
//    return function() {
//    };
//});

