define('prefs', [], function() {
//=== http://stackoverflow.com/questions/13847755/css-media-queries-for-screen-sizes
loadExternalScriptFile = function (filename) {
    $("#mixedcontentadvice").show();
    $.ajax({
        url: filename,
        crossDomain: true,
        type: 'GET',
        dataType: 'jsonp',
        jsonpCallback: 'importGSS',
        success: function () {
            // script is loaded
            //window.console && console.log("script loaded " + filename);
            $('#loginTitle').text("Login");
            $("#mixedcontentadvice").hide();
        },
        error: function () {
            // handle errors
//            $("#mixedcontentadvice").show();
            window.console && console.log("script not loaded " + filename);
        }
    });
};

//dynamically load and add this .js file
//************************************************************************************************
//********* DO NOT FORGET to publish the spreadsheet (File | Publish to the web ...) !!! *********
//************************************************************************************************
if(location.hostname.indexOf('cbiit') > -1 || location.hostname.indexOf('cadsr') > -1) {
	//https://docs.google.com/spreadsheet/pub?key=0AkAXtx48HdvbdHpSQzhiWUE4Y0R2WVdNSFc4Q1VMSHc&output=html
    loadExternalScriptFile("https://spreadsheets.google.com/feeds/list/0AkAXtx48HdvbdHpSQzhiWUE4Y0R2WVdNSFc4Q1VMSHc/1/public/values?alt=json-in-script&amp;callback=importGSS");
}
else
if(location.hostname.indexOf('teev.rhc') > -1 || location.hostname.indexOf('hudoon') > -1 || location.hostname.indexOf('hudoo.ne') > -1 || location.hostname.indexOf('hudoo.tv') > -1) {
    //ref implementation
	//https://docs.google.com/spreadsheet/pub?key=0AkAXtx48HdvbdFJoem1NMTdBc1NwUVFUVVp0NXNZR2c&output=html
    loadExternalScriptFile("https://spreadsheets.google.com/feeds/list/0AkAXtx48HdvbdFJoem1NMTdBc1NwUVFUVVp0NXNZR2c/1/public/values?alt=json-in-script&amp;callback=importGSS");
}
else
if(location.hostname.indexOf('creative') > -1) {
    //ref implementation
    //https://docs.google.com/spreadsheet/pub?key=0Aov1RWgHNTwLdGxtN3FMbkpMX0o1WG54UkVDWlBDMlE&output=html
    loadExternalScriptFile("https://spreadsheets.google.com/feeds/list/0Aov1RWgHNTwLdGxtN3FMbkpMX0o1WG54UkVDWlBDMlE/1/public/values?alt=json-in-script&amp;callback=importGSS");
}
/*
else
if(location.hostname.indexOf('share') > -1) {
    //ShareIt2
    //ref implementation
    //https://docs.google.com/spreadsheet/pub?key=0AkAXtx48HdvbdFpkOUY3Q0VlNDZSTzVNRjktcHlqdXc&output=html
    loadExternalScriptFile("https://spreadsheets.google.com/feeds/list/0AkAXtx48HdvbdFpkOUY3Q0VlNDZSTzVNRjktcHlqdXc/1/public/values?alt=json-in-script&amp;callback=importGSS");
}
*/
else
if(location.hostname.indexOf('aware') > -1 ||
    location.hostname.indexOf('share') > -1
) {
    //2ShareIt
    //ref implementation
    //https://docs.google.com/spreadsheet/pub?key=0Aov1RWgHNTwLdFZyc3V1ZllPWU4yVUV2c3FQVDZEMEE&output=html
    loadExternalScriptFile("https://spreadsheets.google.com/feeds/list/0AkAXtx48HdvbdE00RVp5OW4yQUl6RGZPcVFCY05NX2c/1/public/values?alt=json-in-script&amp;callback=importGSS");
}
else
if(location.hostname.indexOf('service') > -1 ||
    location.hostname.indexOf('localhost') > -1 || //comment out this line to see how the default 2Share look and feel + behaviours!!!
    location.hostname.indexOf('lacaltunnel.me') > -1    //support localtunnel.me for moble debugging
) {
    //common
    loadExternalScriptFile("https://spreadsheets.google.com/feeds/list/0AkAXtx48HdvbdE00RVp5OW4yQUl6RGZPcVFCY05NX2c/1/public/values?alt=json-in-script&amp;callback=importGSS");
}

    //does nothing for now - just to suppress "Uncaught Error: No define call for ..." error
    return function() {
    };
});
