//=== Different from Facebook and Google Plus, Twitter is not based on OAuth 2.0!
//=== Thus we use thirdparty JavaScript library, Codebird-js and set the account to read only
//****** References:
//https://dev.twitter.com/apps
//https://github.com/jublonet/codebird-js

//=== Twitter sign-in stuff
var twBuild = "0001";
var currentTwitterUserName;
var twConsumerKey = "Nd56KDQfhkDVuxtEcn8CLw";                       //boku no key
var twConsumerSecret = "6e82Lqnj1exbBnPuK7IB18EGv4pjvZmkRwllxw";    //boku no secret

//source: https://github.com/jublonet/codebird-js
var cb = new Codebird;
cb.setConsumerKey(twConsumerKey, twConsumerSecret);
//cb.setToken("45429619-fTCwOAR4qVkzlZ4FUtGmK61nlAg10UEx9Z93aIM4D", "UaN3OSNoR392ZmNUdJ5HQcasewFowgnu9yGzSaNsw");

<!-- Twitter -->
//=== source: http://www.nigraphic.com/blog/java-script/how-open-new-window-popup-center-screen
function PopupCenter(pageURL, title, w, h) {
    var left = (screen.width / 2) - (w / 2);
    var top = (screen.height / 2) - (h / 2);
    var targetWin = window.open(pageURL, title, 'toolbar=no, location=no, directories=no, status=no, menubar=no, scrollbars=no, resizable=no, copyhistory=no, width=' + w + ', height=' + h + ', top=' + top + ', left=' + left);
}

function loginTwitter() {

    var host = "https://" + window.location.hostname;
    var pageURL = "https://c7oud.us/twitteroauth/connect.php?origin=" + host;
    //alert(host);
    //PopupCenter(pageURL, "Twitter Sign-in", 480, 320);
    window.location.href = pageURL;
}
