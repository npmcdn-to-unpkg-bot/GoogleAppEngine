//****** References:
//http://www.googleplusdaily.com/2013/03/add-google-sign-in-in-6-easy-steps.html
//https://developers.google.com/+/web/signin/
//https://github.com/devries/plus_sign_in/blob/master/views/jsindex.tpl
//https://developers.google.com/+/api/
//http://garage.socialisten.at/2013/03/hacking-google-plus-the-sign-in-button/
//https://developers.google.com/api-client-library/javascript/dev/dev_jscript
//=== Google Plus sign-in stuffin
var gpBuild = "0007";
var currentGoogleUserName;
//var gpSigninCallbackDone;

//source: https://developers.google.com/+/web/signin/
function initGoooglePlus() {
    var po = document.createElement('script');
    po.type = 'text/javascript'; po.async = true;
    po.src = 'https://apis.google.com/js/client:plusone.js?onload=render';
    var s = document.getElementsByTagName('script')[0];
    s.parentNode.insertBefore(po, s);
};

function render() {
    gapi.signin.render('customBtn', {
        'callback': 'signinCallback',
        'clientid': '875412382034.apps.googleusercontent.com',
        'cookiepolicy': 'single_host_origin',
        'requestvisibleactions': 'http://schemas.google.com/AddActivity',
        'scope': 'https://www.googleapis.com/auth/plus.login'
    });
}

function signinCallback(authResult) {
    //window.console && console.log("google plus gBuild " + gpBuild + " signed in");
//    $("#gp-login-notice").show();

//    if(gpSigninCallbackDone) return;    //do not do it again!

    if(authResult['g-oauth-window']){   //http://stackoverflow.com/questions/15484533/preventing-automatic-sign-in-when-using-google-sign-in/15980907#15980907
//    if (authResult['access_token']) {
        access_token = authResult['access_token'];
        //window.console && console.log("1google plus access token = [" + authResult['access_token'] + "]");

        // Successfully authorized
        // Hide the sign-in button now that the user is authorized, for example:
        $('#signinButton').hide();
        // Could trigger the disconnect on a button click
        $('#revokeButton').click(function () {
            disconnectUser(authResult['access_token']);
            $('#revokeButton').hide();
            $('#signinButton').show();
        });
        $('#revokeButton').show();
        // Get the user profile information from google
        gapi.client.load('plus','v1', function() {
            var request = gapi.client.plus.people.get( {'userId' : 'me'} );
            request.execute( function(profile) {
                $('#results').empty();
                if (profile.error) {
                    //alert("Google Plus access token " + access_token + " profile.error = " + profile.error.message);
                    window.console && console("Google Plus access token " + access_token + " profile.error = " + profile.error.message);
                    //Access Not Configured message with Google Plus switch turned on and good API key
                    //https://groups.google.com/forum/#!msg/google-plus-developers/k1_S6yVe52A/fdDd3naAvFIJ
                    /*
                     Visit the Google APIs Console, click on the "API Access" tab, and click on "Edit allowed referers..." next to your API key.
                     Check to see if the list of allowed referrers is correct. You can disable the referrer check by removing all entries from the textbox.
                     */
                    $('#results').append(profile.error);
                    return;
                }
//                $('#results').append(
//                    $('<p>You are:<br/><img src=\"' + profile.image.url + '\"><br/>' + profile.displayName + '</p>'));
                var prefix = "gp";
                currentGoogleUserName = prefix + profile.id;      //get id etc from https://developers.google.com/+/api/
                //alert("1Google Plus id = [" + currentGoogleUserName + "]");
                //window.console && console.log("1Google Plus id = [" + currentGoogleUserName + "]");
//                gpSigninCallbackDone = true;
                window.location.href = '/mcrud/movie.html?username='+currentGoogleUserName +'&access_token='+access_token+'&logintype=3';     //param has to be "username" as it is shared by init.js (Facebook)
            });
        });
    } else if (authResult['error']) {
        // There was an error.
        // Possible error codes:
        //   "access_denied" - User denied access to your app
        //   "immediate_failed" - Could not automatically log in the user
        window.console && console.log('2Google Plus authentication error: ' + authResult['error']);
        $("#gp-login-notice").hide();
    }
}

/*
function disconnectUser(access_token) {
    var revokeUrl = 'https://accounts.google.com/o/oauth2/revoke?token=' +
        access_token;

    // Perform an asynchronous GET request.
    $.ajax({
        type: 'GET',
        url: revokeUrl,
        async: false,
        contentType: "application/json",
        dataType: 'jsonp',
        success: function(nullResponse) {
            // Do something now that user is disconnected
            // The response is always undefined.
            alert("You have been disconnected from Google Plus!");
        },
        error: function(e) {
            // Handle the error
            // console.log(e);
            // You could point users to manually disconnect if unsuccessful
            // https://plus.google.com/apps
        }
    });
}
*/

//****** Source:
//https://developers.google.com/api-client-library/javascript/start/start-js ******
function checkAuth() {
    gapi.auth.authorize({client_id: clientId, scope: scopes, immediate: true}, handleAuthResult);
    //window.console && console.log("Google API initialized.");
}

function handleAuthResult(authResult) {
    var authorizeButton = document.getElementById('authorize-button');
    if (authResult && !authResult.error) {
        authorizeButton.style.visibility = 'hidden';
        makeApiCall();
    } else {
        authorizeButton.style.visibility = '';
        authorizeButton.onclick = handleAuthClick;
    }
}

function handleAuthClick(event) {
    // Step 3: get authorization to use private data
    gapi.auth.authorize({client_id: clientId, scope: scopes, immediate: false}, handleAuthResult);
    return false;
}

//define('google', [], function() {
//    //does nothing for now - just to suppress "Uncaught Error: No define call for ..." error
//    return function() {
//    };
//});
