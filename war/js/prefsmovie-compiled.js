'use strict';

var showLog = true;
//var showLog = false;
var gHeaderReleaseBuildTitle;
var gCacheProxy = '';
var gAppId;
var gServiceName;
var gPlayNowShuffle = 'playNowShuffle'; //have to be unique, mainly for the cache to work
var gPlayNowAllInSeq = 'playNowAllInSeq'; //have to be unique, mainly for the cache to work
var gManageColMainTitle;
var gSharedColMainTitle;
var gManageShoMainTitle;
var gManageChaMainTitle;
var gHomeLink;
var gAboutLink;
var gHelpLink;
var col1;
var col2;
var col3;
var col4;
var col5;
var col6;
var entry;
function showSlotView(show) {
    var el = $("#slotview1");
    if (typeof show === 'undefined' || show) {
        el.show();
        el.attr('display', 'block');
        $("#slotview2").show();
    } else {
        el.hide();
        el.attr('display', 'none');
        $("#slotview2").hide();
    }
}
function showModernView(show) {
    if (typeof show === 'undefined' || show) {
        $("#modernview1").show();
        $("#modernview2").show();
        $("#modernview3").show();
        $("#modernview4").show();
        $("#modernview5").show();
        $("#modernview6").show();
    } else {
        $("#modernview1").hide();
        $("#modernview2").hide();
        $("#modernview3").hide();
        $("#modernview4").hide();
        $("#modernview5").hide();
        $("#modernview6").hide();
    }
}
/* just for the individual sheet */
baseImportGSS = function importGSS(json) {
    for (var i = 0; i < json.feed.entry.length; i++) {
        entry = json.feed.entry[i];
        col1 = entry.gsx$index && entry.gsx$index.$t;
        col2 = entry.gsx$_cokwr && entry.gsx$_cokwr.$t;
        col3 = entry.gsx$_cpzh4 && entry.gsx$_cpzh4.$t;
        col4 = entry.gsx$_cre1l && entry.gsx$_cre1l.$t;
        col5 = entry.gsx$_chk2m && entry.gsx$_chk2m.$t;
        col6 = entry.gsx$_ciyn3 && entry.gsx$_ciyn3.$t;
        if (col1 && col2 && col3 && col4 && col5) {
            showLog && window.console && console.log('[' + (i + 1) + '] = ' + '[' + col1 + '] ' + '[' + col2 + '] ' + '[' + col3 + '] ' + '[' + col4 + '] ' + '[' + col5 + '] ' + '[' + col6 + '] ');
            if (col1 === 'cache' && col2 === 'proxy' && col3 === 'host' && col4 === 'rest' && col5 === 'url') {
                //alert('rest host cache proxy url [' + col6 + ']');
                gCacheProxy = col6;
                if (typeof gCacheProxy === 'undefined') gCacheProxy = '';
            } else if (col1 === 'cache' && col2 === 'proxy' && col3 === 'origin' && col4 === 'app' && col5 === 'id') {
                //alert('app id [' + col6 + ']');
                gAppId = col6;
            } else if (col1 === 'app' && col2 === 'common' && col3 === 'ui' && col4 === 'debug' && col5 === 'enabled' && col6 === 'TRUE') {
                $('#upgrade').show();
            } else if (col1 === 'header' && col2 === 'release' && col3 === 'build' && col4 === 'title' && col5 === 'text') {
                try {
                    gHeaderReleaseBuildTitle = col6;
                    document.getElementById("releaseLabel").innerHTML = col6;
                    showLog && window.console && console.log("movie.html:gHeaderReleaseBuildTitle set [" + gHeaderReleaseBuildTitle + "]");
                } catch (e) {
                    console && console.log("releaseLabel error: " + e);
                }
            } else if (col1 === 'app' && col2 === 'common' && col3 === 'main' && col4 === 'brand' && col5 === 'text') {
                if (col6 === undefined) col6 = '';
                gServiceName = col6;
                document.getElementById("serviceName").innerHTML = col6;
                showLog && window.console && console.log("movie.html:gServiceName set [" + gServiceName + "]");
            } else if (col1 === 'manage' && col2 === 'collections' && col3 === 'main' && col4 === 'brand' && col5 === 'text') {
                gHomeText = col6;
                document.getElementById("homeText").innerHTML = col6;
                showLog && window.console && console.log("movie.html:gHomeText set [" + gHomeText + "]");
            } else if (col1 === 'manage' && col2 === 'collections' && col3 === 'main' && col4 === 'brand' && col5 === 'url') {
                gHomeLink = col6;
                $("#homeLink").attr('href', gHomeLink);
                showLog && window.console && console.log("movie.html:gHomeLink set [" + gHomeLink + "]");
            } else if (col1 === 'manage' && col2 === 'collections' && col3 === 'main' && col4 === 'title' && col5 === 'text') {
                gManageColMainTitle = col6;
                try {
                    document.getElementById("manageCollections").innerHTML = col6;
                    showLog && window.console && console.log("movie.html:gManageColMainTitle set [" + gManageColMainTitle + "]");
                } catch (e) {
                    showLog && window.console && console.log('movie.html prefs 1 error [' + e + ']');
                }
            } else if (col1 === 'manage' && col2 === 'collections' && col3 === 'main' && col4 === 'migrate' && col5 === 'active' && col6 === 'TRUE') {
                $("#oldCollections").show();
            } else if (col1 === 'manage' && col2 === 'collections' && col3 === 'main' && col4 === 'slotview' && col5 === 'active' && col6 === 'TRUE') {
                showSlotView();
            } else if (col1 === 'manage' && col2 === 'collections' && col3 === 'main' && col4 === 'modernview' && col5 === 'active' && col6 === 'TRUE') {
                showModernView();
            } else if (col1 === 'manage' && col2 === 'collections' && col3 === 'main' && col4 === 'listview' && col5 === 'active' && col6 === 'TRUE') {
                $("#listview1").show();
                $("#listview2").show();
                $("#listview3").show();
            } else if (col1 === 'manage' && col2 === 'collections' && col3 === 'main' && col4 === 'playview' && col5 === 'active' && col6 === 'FALSE') {
                $("#playview1").hide();
                $("#playview2").hide();
            } else if (col1 === 'manage' && col2 === 'shared' && col3 === 'main' && col4 === 'title' && col5 === 'text') {
                gSharedColMainTitle = col6;
                try {
                    document.getElementById("sharedCollections").innerHTML = col6;
                    showLog && window.console && console.log("movie.html:gSharedColMainTitle set [" + gSharedColMainTitle + "]");
                } catch (e) {
                    showLog && window.console && console.log('movie.html prefs 2 error [' + e + ']');
                }
            } else if (col1 === 'manage' && col2 === 'shared' && col3 === 'main' && col4 === 'status' && col5 === 'active' && col6 === 'TRUE') {
                $("#sharedCollections").show();
                showLog && window.console && console.log("movie.html:shared set [" + col6 + "]");
            } else if (col1 === 'manage' && col2 === 'collections' && col3 === 'advanced' && col4 === 'channel-pattern' && col5 === 'active' && col6 === 'TRUE') {
                $(".row.advanced.channel-pattern").show();
                showLog && window.console && console.log("movie.html:channel-pattern set [" + col6 + "]");
            } else if (col1 === 'manage' && col2 === 'collections' && col3 === 'advanced' && col4 === 'channel-event' && col5 === 'active' && col6 === 'TRUE') {
                $(".row.advanced.channel-event").show();
                showLog && window.console && console.log("movie.html:channel-pattern set [" + col6 + "]");
            } else if (col1 === 'manage' && col2 === 'showtime' && col3 === 'main' && col4 === 'title' && col5 === 'text') {
                if (col6 === undefined) col6 = '';

                try {
                    gManageShoMainTitle = col6;
                    document.getElementById("manageShowtime").innerHTML = col6;
                    $("#manageShowtime").show();
                    $("#manageShowtime1").show();
                    showLog && window.console && console.log("movie.html:gManageShoMainTitle set [" + gManageShoMainTitle + "]");
                } catch (e) {
                    console && console.log("showtime 1 error: " + e);
                }
            } else if (col1 === 'manage' && col2 === 'channels' && col3 === 'main' && col4 === 'title' && col5 === 'text') {
                if (col6 === undefined) col6 = '';

                try {
                    gManageShoMainTitle = col6;
                    document.getElementById("manageChannels").innerHTML = col6;
                    $("#manageChannels").show();
                    $("#manageChannels1").show();
                    //$("#playview").show();
                    showLog && window.console && console.log("movie.html:gManageShoMainTitle set [" + gManageShoMainTitle + "]");
                } catch (e) {
                    console && console.log("channels 1 error: " + e);
                }
            } else if (col1 === 'about' && col2 === 'index' && col3 === 'main' && col4 === 'brand' && col5 === 'url') {
                gAboutLink = col6;
                $("#aboutLink").attr('href', gAboutLink);
                showLog && window.console && console.log("movie.html:gAboutLink set [" + gAboutLink + "]");
            } else if (col1 === 'manage' && col2 === 'collections' && col3 === 'main' && col5 === 'color') {
                if (col4 === 'background') {}
                //$('body').css('background-color', col6);  //commented out as this interfere with Play Now subtitle background!!!

                /*else if (col4 === 'table-text') {
                 $('#brand').css('color', col6);
                 } else if (col4 === 'navigator-text') {
                 $('#motto').css('color', col6);
                }*/
            }
        }
    }
};

function requestUpgrade() {
    if (confirm("Do you really want to upgrade the collection? Upgrading the collection more than once can cause the duplicates in your collection!")) {
        window.open("/api/jwt/ws/crud?type=modelMovie&action=upgrade&uid=" + $.url().param('username'), "");
    }
}

//# sourceMappingURL=prefsmovie-compiled.js.map