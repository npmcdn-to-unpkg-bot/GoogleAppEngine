"use strict";
var uid;

//var myApp = angular.module('app', ['summernote'], ctrlRead)   //summernote messed up with Bootstrap 2.3.1 woraround for mobile!!!
//var myApp = angular.module('app', ['ui.bootstrap', 'afkl.lazyImage'], ctrlRead)
//=== https://docs.angularjs.org/api/ng/service/$sce
//myApp.config(function($sce) {
//    $sce.enabled(false);
//    alert('$SCE disabled!');
//});

function MovieController($scope, $filter, $http, $rootScope,
                         //log,
                         $timeout, dateFilter, $location
    , $sce
) {
    var mc = this;
    mc.error_message ="";

    $scope.singleClick = function() {
        alert('Single Click');
    }
    $scope.doubleClick = function() {
        alert('Double Click');
    }
    //log.setLevel("silent");
    //log.setLevel("trace");
    //log.enableAll();
    //log.disableAll();

    //=== bootstrap rich text editor (summernote) stuff
    //$scope.options = {
    //    height: 150,
    //    toolbar: [
    //        ['style', ['bold', 'italic', 'underline', 'clear']],
    //        ['fontsize', ['fontsize']],
    //        ['color', ['color']],
    //        ['para', ['ul', 'ol', 'paragraph']],
    //        ['height', ['height']]
    //    ]
    //};
    //$scope.text = "Hello World";

    //var offline = true;  //DEV only
    var offline = false;  //need to be uncommented for production
    $scope.backendReady = false;
    $scope.mBuild = gBuild;
    $scope.savedQuery = "";
    //	alert('version 3');
    //=== BEGIN - CAN NOT SET GLOBALLY AS THE APP EXPECT DEFAULT (ANGULARJS-STYLE/JSON) WITH RESPONSE BUT SUBMIT ALA JQUERY-STYLE!!! ========================
    //$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
    //$http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
    //=== END - CAN NOT SET GLOBALLY AS THE APP EXPECT DEFAULT (ANGULARJS-STYLE/JSON) WITH RESPONSE BUT SUBMIT ALA JQUERY-STYLE!!! ========================
    $scope.backendObject = "modelMovie";

    //=== for Safari and Android mobile browser$sce.trustAsHtml(input
//    $('.input-large search-query').clearSearch({ callback: function() {
//            $scope.query = "";
//            console.log("cleared"); }
//        }
//    );

    //AngularJS 1.2+ stuff
    //$scope.unsafeTitle = "";
    // app specific init
    var sortingOrder = 'name';
    //$scope.title = "Custom Channels Profile";
    $scope.createButtonTitle = "+";
    $scope.saveLinkTitle = "Save";
    $scope.saveButtonLabel = "Save";
    $scope.updateButtonLabel = "Update";
    $scope.doneButtonLabel = "Schedule";
    $scope.deleteLinkText = "Delete";
    $scope.header1 = "Action";
    $scope.thumbnail = "Thumbnail";
    $scope.owner = "Owner";
    $scope.header2 = "ID";
    $scope.header3 = "Title";
    $scope.header4 = "Description";    //TBD: mapped to URL!!!
    $scope.header5 = "URL";
    $scope.header6 = "Share It";
    $scope.header7 = "Play Date and Time";
    $scope.header8 = "Shuffle It";

    // common init
    $scope.sortingOrder = sortingOrder;
    $scope.reverse = false;
    $scope.filteredItems = [];
    $scope.groupedItems = [];
    $scope.itemsPerPage = 21;
    $scope.pagedItems = [];
    $scope.itemsPerPageForCalendar = 6;
    $scope.pagedItemsForCalendar = [];
    $scope.searchResults = [];
    $scope.currentPage = 0;
    $scope.items =
        [];
    $scope.api = "";
    $scope.movie_thumbnail = "";
    $scope.parsedHashtags = [];
    function escapeJson(str) {
        var ret = str;
        try {
            ret = encodeURIComponent(str);
        } catch (e) {
            console.log("movie.js escapeJson error: " + e);
        }
        return ret;
        //.replace(/([\\]|[\"]|[\/])/g, "\\$1")
        /*
         .replace(/[\b]/g, "")
         .replace(/[\f]/g, "")
         .replace(/[\n]/g, "")
         .replace(/[\r]/g, "")
         .replace(/[\t]/g, "")
         ;
         */
    }

    $scope.page = { max:6, number:1 };  //for some reason, this needs to be repeated even though we already did ng-init in the movie.html!

    function unescapeJson(str) {
        var ret = str;
        try {
            ret = decodeURIComponent(str);
        } catch (e) {
            console.log("movie.js unescapeJson error: " + e);
        }
        return ret;
    }

    function getObjectId() {
        return $scope.objectId;  //Parse.User.current().id;  //does not work for some reason, always empty!
    }

    function getUsername() {
        var retVal;
        if(typeof Parse !== 'undefined' && typeof Parse.User !== 'undefined' && Parse.User.current() !== null && typeof Parse.User.current() !== 'undefined' && typeof Parse.User.current().getUsername() !== 'undefined' && Parse.User.current().getUsername().trim() !== '') {
            retVal = Parse.User.current().getUsername();
            //alert("objectId [" + Parse.User.current().id + "]");
            $scope.objectId = Parse.User.current().id;
            $scope.userId = retVal;
        } else {
            //$console && $console.log("2getting Google Plus id ...");
            retVal = $.url().param('username');     //TODO security risk
            /*
             handleClientLoad();

             // Get the user profile information from google
             gapi.client.load('plus','v1', function() {
             var request = gapi.client.plus.people.get( {'userId' : 'me'} );
             request.execute( function(profile) {
             $('#results').empty();
             if (profile.error) {
             $('#results').append(profile.error);
             return;
             }
             //                $('#results').append(
             //                    $('<p>You are:<br/><img src=\"' + profile.image.url + '\"><br/>' + profile.displayName + '</p>'));
             var prefix = "gp";
             currentGoogleUserName = prefix + profile.id;      //get id etc from https://developers.google.com/+/api/
             retVal = currentGoogleUserName;
             //$console && $console.log("2Google Plus id = [" + currentGoogleUserName + "]");
             });
             });
             */
        }

        //=== parse timeout handling
//        if (retVal === undefined) {
//            if(location.hostname.indexOf('localhost') === -1) {
//                window.location= "https://" + location.hostname + ":" + location.port + "/ui/index.html";
//            } else {
//                window.location= "http://" + location.hostname + ":" + location.port +  "/ui/index.html";
//            }
//        }

        return retVal;
    }

    $scope.getYoutubeVideoId = function (youtube_url) {
        var youtube_id;

        //http://stackoverflow.com/questions/3452546/javascript-regex-how-to-get-youtube-video-id-from-url
        if (typeof youtube_url !== 'undefined' && youtube_url.match('/?.*(?:youtu.be\\/|v\\/|u/\\w/|embed\\/|watch\\?.*&?v=)')) {
            youtube_id = youtube_url.split(/v\/|v=|youtu\.be\//)[1].split(/[?&]/)[0];
        }

        return youtube_id;
    };

    $scope.getHashtagUrl = function (hashtag) {
        var retVal = "";

        try {
            retVal = '/html/channelshuffle.html?username=' + $.url().param('username') + '&logintype=' + $.url().param('logintype') + '&filter=' + hashtag.substr(1) + '&type=' + gPlayNowShuffle;
        }
        catch (e) {
            //$console && $console.error('1.1.1.0 movie.js $scope.handleHashtag(): An error has occurred: ' + e.message + ' - The application will not function correctly. Please contact the developer!');
            console.log("movie.js: $scope.getHashtagUrl error: " + e);
        }

        return retVal;
    };

    typeof module !== 'undefined'?module.exports.getHashtagUrl = getHashtagUrl:"";

    //=== parse hashtags in the description
    $scope.handleHashtag = function (desc1) {
        //$console && $console.log('movie.js $scope.handleHashtag entered 11');
        try {
            if (typeof desc1 !== 'undefined') {
                var params_tmp = [];
                try {
                    params_tmp = desc1.split(' ');
                    if(typeof params_tmp === 'undefined') {
                        params_tmp = [];
                    }
                }
                catch (e) {
                    //$console && $console.error('1.1.1 movie.js $scope.handleHashtag(): An error has occurred: ' + e.message + ' - The application will not function correctly. Please contact the developer!');
                    alert("movie.js: 2 error: " + e);
                }
                var u, val;
                for(var i= 0; i < params_tmp.length; i++) {
                    val = params_tmp[i];
                    if (val !== undefined && val.substr(0, 1) === '#') {
                        try {
                            u = {
                                name1: val,
                                url: $scope.getHashtagUrl(val)
                            };
                        }
                        catch (e) {
                            //$console && $console.error('1.1.2 movie.js $scope.handleHashtag(): An error has occurred: ' + e.message + ' - The application will not function correctly. Please contact the developer!');
                            alert("movie.js: 3 error: " + e);
                        }
                        try {
                            $scope.parsedHashtags[val.substr(0, val.length)] = u;
                        }
                        catch (e) {
                            //$console && $console.error('1.1.3 movie.js $scope.handleHashtag(): An error has occurred: ' + e.message + ' - The application will not function correctly. Please contact the developer!');
                            alert("movie.js: 4 error: " + e);
                        }
                    }
                }
            }
        }
        catch (e) {
            //$console && $console.error('1.1.0 movie.js $scope.handleHashtag(): An error has occurred: ' + e.message + ' - The application will not function correctly. Please contact the developer!');
            alert("movie.js: 5 error: " + e);
        }
    };

    $scope.serviceCheck = function(page) {
        if(typeof page.server_max !== 'undefined' && typeof page.server_number !== 'undefined') {
            //TODO for some reason, search made page.number and page.max both 0s!!!
            if (page.number > 0 && page.server_number !== page.number && page.server_number !== 0) {    //page.server_number === 0 is the same as the client side page 1
                alert('page.server_number [' + page.server_number + '] page.number [' + page.number + ']')
                throw new Error("Page number " + page.number + " is out of sync with the server" +
                    "s page (" + page.server_number + "). The page number should be in sync/the same!");
            }
            if (page.max > 0 && page.server_max !== page.max) {
                throw new Error("Maximum per page " + page.max + " is out of sync with the server" +
                    "s maximum (" + page.server_max + "). The max count per page should be in sync/the same!");
            }
            //window.console && console.log("movie.js serviceCheck() ok: page#=" + page.number + " max#=" + page.max + " total page#=" + page.totalPage + " total item#=" + page.totalItem);
        }
    };

    function isFullSearchMode() {
        var ret;

        if($scope.page.max === 0 && $scope.page.number === 0) ret = true;

        return ret;
    }

    $scope.loadItems = function () {
        $scope.backendReady = false;
//        var currentTotalCount = -1;  //the total in the current page (not the total counts of all pages!)
        var uid = getUsername();
        //$console && $console.log('1about to get the list for user [' + uid + "] ...");
        $scope.items.length = 0;	//clear everything and reload again :(
        $scope.pagedItems.length = 0;
        $scope.searchResults.length = 0;
        //$console && $console.log('3.1 list reinit');
        $http.get(gCacheProxy + '/api/jwt/ws/crud?type=' + $scope.backendObject + "&origin=" + location.hostname + "&aid=" + gAppId + "&uid=" + uid + "&maxPerPage=" + $scope.page.max + "&pageNumber=" + $scope.page.number )
            .success(function (data, status1, headers, config) {
                if(data.indexOf(App.NO_PARENT_ERR) > -1) {
                    alert(App.NO_PARENT_ERR_MSG);
                    location.href = App.login_url;
                }
                //$console && $console.log('loadItems success entered');
                var j;
                var htmlTitle;
                var htmlDescription;
                if (typeof data !== 'undefined') {
                    for (var i = 0; i < data.length; i++) {
                        if (i === App.header_index) {
                            //=== parsing the metadata first
                            $scope.page.server_number = data[i].pageNumber;
                            $scope.page.server_max = data[i].maxPerPage;
                            $scope.page.totalItem = data[i].totalItem;
                            $scope.page.totalPage = data[i].totalPage;
                            $scope.serviceCheck($scope.page);
                        } else {
                            //=== now parsing the movies
                            htmlTitle = $sce.trustAsHtml(data[i].title);
                            //htmlDescription = $sce.trustAsHtml(data[i].description);
                            //htmlTitle = data[i].title;
                            j = {
                                id: data[i].id,
                                owner: data[i].owner,
                                oid: data[i].oid,
                                title: htmlTitle,   //data[i].title,
                                //description: htmlDescription,   //data[i].description,
                                description: unescapeJson(data[i].description.value),
                                url: data[i].u_r_l,
                                createDate: data[i].modified,
                                shared: data[i].shared,
                                channelPattern: data[i].channel_pattern
                            };
                            j.search_results = data[i].search_results;
                            try {
                                /*
                                 if (j.search_results && j.search_results.value !== undefined) {
                                 try {
                                 var obj = angular.fromJson(
                                 j.search_results.value
                                 );
                                 //                                    //$console && $console.log('data[' + i + '] obj = [' + obj +']');
                                 //                                    //$console && $console.log('data[' + i + '] search_results = [' + obj[0].movie_thumbnail + ']');
                                 }
                                 catch (e) {
                                 //$console && $console.error('1.0 movie.js loadItems(): An error has occurred: ' + e.message + ' - The application will not function correctly. Please contact the developer!');
                                 alert("movie.js: 6 error: " + e);
                                 }
                                 //                                    try {
                                 //                                        $scope.handleHashtag(j.description);
                                 //                                        //$console && $console.log('data[' + i + '] = id=' + j.id + " desc=" + j.description + " title=" + j.title + " url=" + j.url + " createDate=" + j.createDate + " shared=" + j.shared);
                                 //                                    }
                                 //                                    catch (e) {
                                 //                                        //$console && $console.error('1.1 movie.js loadItems(): An error has occurred: ' + e.message + ' - The application will not function correctly. Please contact the developer!');
                                 //                                        alert("movie.js: 7 error: " + e);
                                 //                                    }
                                 }
                                 */
                                try {
                                    $scope.items.push(j);
                                }
                                catch (e) {
                                    //$console && $console.error('1.2 movie.js loadItems(): An error has occurred: ' + e.message + ' - The application will not function correctly. Please contact the developer!');
                                    alert("movie.js: 8 error: " + e);
                                }
                            }
                            catch (e) {
                                //$console && $console.error('1 movie.js loadItems(): An error has occurred: ' + e.message + ' - The application will not function correctly. Please contact the developer!');
                                alert("movie.js: 9 error: " + e);
                            }
                        }
                    }
                    //noinspection JSJQueryEfficiency
                    $("#loadingTable").hide();
                    if(isFullSearchMode()) {
                        $scope.query = $scope.savedQuery;   //restore the current term to filter the right item(s)
                        $("#loadingSearch").hide();
                        $scope.allItemsLoaded = true;
                        //alert("all items for search loaded!");
                        try {
                            showSlotView(false);
                            showModernView();
                        } catch (e) {
                            console.log("movie.js showSlotView/showModernView error 1: " + e);
                        }
                    }
                    $scope.loadHashtag();
                    $scope.backendReady = true;
                } else {
                    //$console && $console.log('loadItems data is ' + data + '], skipped!');
                    return;
                }

                $scope.renderPagedTable(true);

                //$console && $console.log("items loaded [gBuild " + gBuild + "]");
            }
        ).error(function (data, status, headers, config) {
            if(status == 0) {
                alert("Server encountered an error. Is the cache service up and running?");
            }
            if(typeof data === 'undefined') {
                $scope.error_message = status + ": unknown error, please try again later";
            } else {
                $scope.error_message = status + ": " + data;
            }
            if(typeof status !== 'undefined') {	//TODO workaround to suppress the weird error! :(
                if(status == 0 /* yes, two aka == is on purpose */ && data.length === 0) {
                    //TODO ignoring weird error from the server - maybe this error will be known/resolved one day by Google/us!??
                    //$console && $console.log("movie.js: 9.1 error ignored [gBuild " + gBuild + "]");
                } else
                if (status.indexOf(App.gaej_server_error_msg) > -1) {
                    alert("Server encountered an error. Please log out and try again later.");
                } else {
                    alert("movie.js: 9.2 error: data [" + data + "] status [" + status + "] headers [" + headers + "] config [" + config + "] Hint: Is the json response sent by the server proper?");
                }
            }
        });
    };

    /** load the items just for the play now hash tags */
    $scope.loadItemsJustForHashtag = function () {
        $scope.backendReady = false;
        var uid = getUsername();
        //$console && $console.log('4.1 list reinit');
        $http.get(gCacheProxy + '/api/jwt/ws/crud?type=' + $scope.backendObject + "&origin=" + location.hostname + "&aid=" + gAppId + "&uid=" + uid + "&maxPerPage=" + $scope.page.max + "&pageNumber=" + $scope.page.number )
            .success(function (data, status1, headers, config) {
                //$console && $console.log('loadItems success entered');
                var j;
                var htmlTitle;
                var htmlDescription;
                if (typeof data !== 'undefined') {
                    for (var i = 0; i < data.length; i++) {
                        if (i === App.header_index) {
                            //=== parsing the metadata first
//                            $scope.page.server_number = data[i].pageNumber;
//                            $scope.page.server_max = data[i].maxPerPage;
//                            $scope.page.totalItem = data[i].totalItem;
//                            $scope.page.totalPage = data[i].totalPage;
                            $scope.serviceCheck($scope.page);
                        } else {
                            //=== now parsing the movies
                            htmlTitle = $sce.trustAsHtml(data[i].title);
                            //htmlDescription = $sce.trustAsHtml(data[i].description);
                            j = {
                                id: data[i].id,
                                owner: data[i].owner,
                                oid: data[i].oid,
                                title: htmlTitle,   //data[i].title,
                                //description: htmlDescription,   //data[i].description,
                                description: data[i].description.value,
                                url: data[i].u_r_l,
                                createDate: data[i].modified,
                                shared: data[i].shared,
                                channelPattern: data[i].channel_pattern
                            };

                            try {
                                if(typeof j.description !== 'undefined' && j.description.indexOf('data:image/') === -1 && j.description.trim().toLowerCase().substring(0, 4) !== 'http') {
                                    $scope.handleHashtag(j.description);
                                    //$console && $console.log('data[' + i + '] = id=' + j.id + " desc=" + j.description + " title=" + j.title + " url=" + j.url + " createDate=" + j.createDate + " shared=" + j.shared);
                                }
                            }
                            catch (e) {
                                //$console && $console.error('2.1 movie.js loadItems(): An error has occurred: ' + e.message + ' - The application will not function correctly. Please contact the developer!');
                                alert("movie.js: 7.1 error: " + e);
                            }

                        }
                    }
                    $scope.backendReady = true;
                } else {
                    //$console && $console.log('loadItems data is ' + data + '], skipped!');
                    return;
                }

                //begin play now setup
                try {
                    $scope.templates =
                        [
                            { name1: 'Shuffle Mode', url: '/html/channelshuffle.html?username=' + $.url().param('username') + '&logintype=' + $.url().param('logintype') + '&type=' + gPlayNowShuffle},
                            { name1: 'All In Sequence', url: '/html/channelall.html?username=' + $.url().param('username') + '&logintype=' + $.url().param('logintype') + '&type=' + gPlayNowAllInSeq}
                        ];
                    //var start = 2;   //after the above 2!
                    //=== begin play now setup
                    $scope.template = $scope.templates[0];   //default play now url
                    //=== begin hashtags processing
                    for (var l in $scope.parsedHashtags) {
                        $scope.templates[$scope.templates.length] = { name1: l, url: $scope.getHashtagUrl(l) };
                    }
                    //=== end hashtags processing
                    $scope.play = function () {
                        window.location = $scope.template.url;
                    };
                }catch (e) {
                    //$console && $console.error('3 movie.js loadItems(): An error has occurred: ' + e.message + ' - The application will not function correctly. Please contact the developer!');
                    alert("movie.js: 9a error: " + e);
                }
                //=== end play now setup

                //$console && $console.log("items loaded [gBuild " + gBuild + "]");
            }
        ).error(function (data, status, headers, config) {
                $scope.error_message = status + ": " + data;
                if(typeof status !== 'undefined') {	//TODO workaround to suppress the weird error! :(
                    //alert("movie.js: loadItemsJustForHashtag error: data [" + data + "] status [" + status + "] headers [" + headers + "] config [" + config + "] Hint: Is the json response sent by the server proper?");
                    console.log("movie.js: loadItemsJustForHashtag error: data [" + data + "] status [" + status + "] headers [" + headers + "] config [" + config + "] Hint: Is the json response sent by the server proper?");
                }
            });
    };

    //=== non-paginated movie load (ONLY and ONLY IF the page.number and page.max are not set, e.g. from movie.html!!!)
    $scope.loadHashtag = function () {
        //=== saved existing values
        $scope.savedPageMax = $scope.page.max;
        $scope.savedPageNumber = $scope.page.number;
        //now change them
        $scope.page.number = 0;
        $scope.page.max = 0;
        $scope.loadItemsJustForHashtag();
        //=== restore saved values
        $scope.page.max = $scope.savedPageMax;
        $scope.page.number = $scope.savedPageNumber;
    };

    if(!offline) {
        $scope.loadItems();
    }

    //=== just for Next button of the server-side paginated results (front end UI)
    $scope.loadMoreItems = function () {
        var count = $scope.loadItems();
    };

    //=== paginated movie load
    $scope.loadItemsPerPage = function () {
        $scope.loadItems();
    };

    //=== just for calendar
    $scope.loadItemsForCalendar = function (datasource) {
        showInProgress();

        var uid = getUsername();
        //$console && $console.log('1about to get the list for user [' + uid + "] ...");
        $scope.items.length = 0;	//clear everything and reload again :(
        $scope.pagedItemsForCalendar.length = 0;
        $scope.pagedItemsForCalendar = [];
        $scope.searchResults.length = 0;
        //$console && $console.log('3list reinit');

        var endpoint;
        //var host = $.url().attr('protocol') + '://' + $.url().attr('host');
        var host = gCacheProxy;  //https://' + $.url().attr('host');

        if (datasource === 2) {
            endpoint = host + '/api/jwt/ws/crud?type=' + $scope.backendObject + "&uid=" + uid + "&action=scheduled&filter=scheduled";
        } else if (datasource === 1) {
            endpoint = host + '/api/jwt/ws/crud?type=' + $scope.backendObject + "&action=shared&filter=shared&uid=" + uid;
        } else {
            endpoint = host + '/api/jwt/ws/crud?type=' + $scope.backendObject + "&uid=" + uid;
        }
        endpoint = endpoint + "&origin=" + location.hostname + "&aid=" + gAppId

        $http.get(endpoint)
            .success(function (data, status, headers, config) {
                //$console && $console.log('4success entered');
                var j;
                if (data !== undefined) {
                    if (data.length === 0) {
                        $("#loadingTable").hide();
                        $("#emptyTable").show();
                    }
                    var dateStr, scheduled;
                    for (var i = 0; i < data.length; i++) {
                        $scope.backendReady = false;

                        //=== begin TODO this have to be placed in a common module/routine!!!
                        if(data[i].event_pattern !== undefined) {
                            dateStr = data[i].event_pattern.split(",");
                            scheduled = new Date(dateStr[0], dateStr[1], dateStr[2], dateStr[3], dateStr[4], dateStr[5]);
                        }
                        //=== end TODO this have to be placed in a common module/routine!!!

                        j = {id: data[i].id, owner: data[i].owner, oid: data[i].oid, title: data[i].title, description: data[i].description.value, url: data[i].u_r_l, createDate: data[i].modified, shared: data[i].shared, scheduled: scheduled};
//                        j.search_results = data[i].search_results;
                        try {
/*
                            if (j.search_results && j.search_results.value) {
                                try {
                                    var obj = angular.fromJson(
                                        j.search_results.value
                                    );
                                } catch (e) {
                                    console.log("movie.js error loadItemsForCalendar(): " + e);
                                }
                                //$console && $console.log('data[' + i + '] = id=' + j.id + " title=" + j.title + " url=" + j.url + " createDate=" + j.createDate + " search_results=" + obj[0].movie_thumbnail + " shared=" + j.shared);
                                //$console && $console.log('data[' + i + '] = obj=' + obj);
                            }
*/
                            $scope.items.push(j);
                        }
                        catch (e) {
                            //$console && $console.error('movie.js $http.get: An error has occurred: ' + e.message + ' - The application will not function correctly. Please contact the developer!');
                            alert("movie.js: 10 error: " + e);
                        }
                    }
                    $("#loadingTable").hide();
                }

                $scope.backendReady = true;
                //alert("calendar: calling groupToPagesForCalendar!");
                $scope.renderPagedTableForCalendar(true);

                showDone();

                //$console && $console.log("items loaded for calendar [gBuild " + gBuild + "]");
            }
        ).error(function (data, status, headers, config) {
                $scope.error_message = status + ": " + data;
                if(status !== 0) {	//TODO workaround to suppress the weird error! :(
                    alert("movie.js: 11 error: data [" + data + "] status [" + status + "] headers [" + headers + "] config [" + config + "]");
                }
            });
    };
//    if(!offline) {
//        $scope.loadItemsForCalendar();
//    }

    var searchMatch = function (haystack, needle) {
        var done = true;
        try {
            if (!needle) {
                done = true;
            } else {
                done = haystack.toLowerCase().indexOf(needle.toLowerCase()) !== -1;
            }
        }
        catch (e) {
            //$console && $console.log("search error: " + e);
        }
        return done;
    };

    $scope.startFullSearchMode = function () {
        $scope.page.max = $scope.savedPageMax;
        $scope.page.number = $scope.savedPageNumber;
        $scope.allItemsLoaded = false;
    };
    $scope.setFullSearchMode = function () {
        $scope.page.max = 0;        //the max and number both set to zero is understood by the backend as no paging request!
        $scope.page.number = 0;     //ditto
    };
    $scope.stopFullSearchMode = function () {
        if($scope.savedPageMax && $scope.savedPageNumber) {
            $scope.page.max = $scope.savedPageMax;
            $scope.page.number = $scope.savedPageNumber;
            $scope.loadItems();
        }
        $scope.allItemsLoaded = false;
        try {
            showSlotView();
            showModernView(false);
        } catch (e) {
            console.log("movie.js showSlotView/showModernView error 2: " + e);
        }
        //alert("reset back to default page max = " + $scope.page.max + " page number = " + $scope.page.number);
    };

    // init the filtered items
    $scope.search = function () {
        $scope.savedQuery = $scope.query;
        if($scope.query && $scope.savedQuery.length > 1) {
            //=== support "type-ahead" while the full items based on the single character is being loaded ...
            console.log("saved keyword = [" + $scope.savedQuery + "]");
            return;
        }

        //$console && $console.log("searching ...");
        var api_url;
        //http://developer.rottentomatoes.com/docs/read/JSON
        //JSON_CALLBACK has to be spelled exactly in its case (AngularJS requires it like that :)!)
//        api_url1 = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=tv2yr43592jd6pdxaahjym4b&page_limit=1&q="+$scope.query+"&callback=JSON_CALLBACK";
//        ////$console && $console.log(api_url1);
//        // retrieve movie metadata
//        $http.jsonp(api_url1)
//            .then( function ( response ) {
//                $scope.backendReady = false;
//                if(response !== undefined && response.data !== undefined && response.data.movies !== undefined && response.data.movies[0] !== undefined) {
//                    //$scope.data = response.data;
//                    $scope.movie_thumbnail = response.data.movies[0].posters.thumbnail;
//                    $scope.movie_title = "IMDB Review";
//                    if(response.data.movies[0].alternate_ids !== undefined) {
//                        $scope.movie_url = "http://www.imdb.com/title/tt" + response.data.movies[0].alternate_ids.imdb;
//                    }
//                    //$console && $console.log("thumbnail is [" + $scope.movie_thumbnail + "] " + $scope.movie_title + " " + $scope.movie_url);
//                }
//                $scope.backendReady = true;
//            });
        //https://developers.google.com/youtube/2.0/developers_guide_protocol_video_feeds
        api_url = "https://gdata.youtube.com/feeds/api/videos?q=" + $scope.query + "&start-index=21&max-results=1&v=2&alt=jsonc&callback=JSON_CALLBACK";
        //$console && $console.log(api_url);
        //TODO does JSONP work for IE 8???
        // retrieve youtube video metadata
//        $http.jsonp(api_url)
//            .then(function (response) {
//                $scope.backendReady = false;
//                if (response !== undefined && response.data !== undefined && response.data.data !== undefined && response.data.data.items !== undefined && response.data.data.items[0] !== undefined) {
//                    //$scope.data = response.data;
//                    $scope.movie_thumbnail2 = response.data.data.items[0].thumbnail.sqDefault;
//                    $scope.movie_title2 = "YouTube Videos";
//                    if (response && response.data && response.data.data && response.data.data.items[0] && response.data.data.items[0].player) {
//                        $scope.movie_url2 = response.data.data.items[0].player && response.data.data.items[0].player["default"];
//                    }
//                    //$console && $console.log("thumbnail is [" + $scope.movie_thumbnail2 + "] " + $scope.movie_title2 + " " + $scope.movie_url2);
//                }
//                $scope.backendReady = true;
//            });
        //http://developers.facebook.com/docs/reference/api/search/
        api_url = "https://graph.facebook.com/search?q=" + $scope.query + "&type=post&callback=JSON_CALLBACK";
        //$console && $console.log(api_url);
        //TODO does JSONP work for IE 8???
        // retrieve public post metadata
//        $http.jsonp(api_url)
//            .then(function (response) {
//                $scope.backendReady = false;
//                if (response !== undefined && response.data !== undefined && response.data.data !== undefined && response.data.data[0] !== undefined) {
//                    //$scope.data = response.data;
//                    $scope.movie_thumbnail3 = response.data.data[0].picture;
//                    $scope.movie_title3 = "Facebook Post";
//                    if (response.data.data[0].link !== undefined) {
//                        $scope.movie_url3 = response.data.data[0].link;
//                    }
//                    //$console && $console.log("thumbnail is [" + $scope.movie_thumbnail3 + "] " + $scope.movie_title3 + " " + $scope.movie_url3);
//                }
//                $scope.backendReady = true;
//            });

        //=== handle paginated results and non-paginated for now
        //TODO should treat paginated results with a different approach, possibly with SOLR
        if($scope.savedPageMax && $scope.savedPageNumber) { //do not override if it is initial load (page just bootstrapped)
            $scope.startFullSearchMode();
            //alert("search reset!");
        }
//        try {
//            showSlotView();
//            showModernView(false);
//        } catch (e) {
//            console.log("movie.js showSlotView/showModernView error 3: " + e);
//        }
        if($scope.query && $scope.query.length === 1) {
            if(typeof $scope.allItemsLoaded === 'undefined' || !$scope.allItemsLoaded) {
                $scope.savedPageMax = $scope.page.max;
                $scope.savedPageNumber = $scope.page.number;
                $scope.setFullSearchMode();
//                try {
//                    if ($scope.query != $scope.savedQuery) {
                $("#loadingSearch").show();
                $scope.loadItems();
//                    } else {
//                        $("#loadingSearch").hide();
//                    }
//                }
//                catch(e) {
//                    console.log("movie.js query check error 1: " + e);
//                }
            }
        }
        if(typeof $scope.query === 'undefined' || typeof $scope.query !== 'undefined' && $scope.query.length === 0 ) {
            $scope.stopFullSearchMode();
            //alert("search reset!");
        }
        $scope.renderPagedTable();
    };

    $scope.searchForCalendar = function () {
        //$console && $console.log("searching for calendar ...");
        $scope.renderPagedTableForCalendar();
    };

    $scope.renderPagedTable = function (paginate) {
        $scope.filteredItems = $filter('filter')($scope.items, function (item) {
            if(item !== undefined) {
//                var m, n = item.length;
//                for (m = 0; m < n; ++m) {
                //TODO
                for (var attr in item) {
                    if (item.hasOwnProperty(attr)) {    //http://stackoverflow.com/questions/18451795/undefined-var-in-ie8-javascript-jquery
                        if (searchMatch(item[attr], $scope.query)) {
                            return true;
                        }
                    }
                }
            }
            return false;
        });
        // take care of the sorting order
        if ($scope.sortingOrder !== '') {
            $scope.filteredItems = $filter('orderBy')($scope.filteredItems, $scope.sortingOrder, $scope.reverse);
        }
        $scope.currentPage = 0;

        paginate = !($scope.query !== undefined && $scope.query.length > 0);
        // now group by pages
        $scope.groupToPages(paginate);
    };

    $scope.renderPagedTableForCalendar = function (paginate) {
        $scope.filteredItems = $filter('filter')($scope.items, function (item) {
            //TODO
            for (var attr in item) {
                if (searchMatch(item[attr], $scope.query1)) {
                    return true;
                }
            }
            return false;
        });
        // take care of the sorting order
        if ($scope.sortingOrder !== '') {
            $scope.filteredItems = $filter('orderBy')($scope.filteredItems, $scope.sortingOrder, $scope.reverse);
        }
        $scope.currentPage = 0;

        paginate = !(typeof $scope.query1 !== 'undefined' && $scope.query1.length > 0);
        //if (typeof $scope.query1 !== 'undefined' && $scope.query1.length > 0) {
        //    paginate = false;
        //} else {
        //    paginate = true;
        //}
        // now group by pages
        $scope.groupToPagesForCalendar(paginate);
    };

    // calculate page in place
    $scope.groupToPages = function (paginate) {
        $scope.pagedItems = [];

        //KISS paginate with huge/ridiculous row window size if it is supposed to be none
        var pagedSize = $scope.itemsPerPage;
        if (!paginate) {
            pagedSize = 100000;   //TBD need a better approach in the future!
        }

        for (var i = 0; i < $scope.filteredItems.length; i++) {
            if (i % pagedSize === 0) {
                //paginate it
                $scope.pagedItems[Math.floor(i / pagedSize)] = [ $scope.filteredItems[i] ];
            } else {
                //no pagination
                $scope.pagedItems[Math.floor(i / pagedSize)].push($scope.filteredItems[i]);
            }
        }
    };

    $scope.groupToPagesForCalendar = function (paginate) {
        $scope.pagedItemsForCalendar = [];

        //KISS paginate with huge/ridiculous row window size if it is supposed to be none
        var pagedSize = $scope.itemsPerPageForCalendar;
        if (!paginate) {
            pagedSize = 100000;   //TBD need a better approach in the future!
        }

        for (var i = 0; i < $scope.filteredItems.length; i++) {
            if (i % pagedSize === 0) {
                //paginate it
                $scope.pagedItemsForCalendar[Math.floor(i / pagedSize)] = [ $scope.filteredItems[i] ];
            } else {
                //no pagination
                $scope.pagedItemsForCalendar[Math.floor(i / pagedSize)].push($scope.filteredItems[i]);
            }
        }
    };

    $scope.range = function (start, end) {
        var ret = [];
        if (!end) {
            end = start;
            start = 0;
        }
        for (var i = start; i < end; i++) {
            ret.push(i);
        }
        return ret;
    };

    $scope.prevPage = function () {
        if ($scope.currentPage > 0) {
            $scope.currentPage--;
        }
    };

    $scope.nextPage = function () {
        if ($scope.currentPage < $scope.pagedItems.length - 1) {
            $scope.currentPage++;
        }
    };

    $scope.setPage = function () {
        $scope.currentPage = this.n;
    };

    // functions have been describe process the data for display
    $scope.search();

    // change sorting order
    $scope.sort_by = function (newSortingOrder) {
        if ($scope.sortingOrder === newSortingOrder) {
            $scope.reverse = !$scope.reverse;
        }

        $scope.sortingOrder = newSortingOrder;

        // icon setup
        $('th i').each(function () {
            // icon reset
            $(this).removeClass().addClass('icon-sort');
        });
        if ($scope.reverse)
            $('th.' + newSortingOrder + ' i').removeClass().addClass('icon-chevron-up');
        else
            $('th.' + newSortingOrder + ' i').removeClass().addClass('icon-chevron-down');
    };

    $scope.resetSearch = function () {
        $scope.query = "";
        $scope.renderPagedTable(true);
    };

    $scope.resetSearch1 = function () {
        $scope.query1 = "";
        $scope.renderPagedTableForCalendar(true);
    };

    $scope.serializeSearch = function () {
        return $scope.searchResults = [
            {movie_thumbnail: escapeJson($scope.movie_thumbnail), movie_title: escapeJson($scope.movie_title), movie_url: escapeJson($scope.movie_url)},
            {movie_thumbnail: escapeJson($scope.movie_thumbnail2), movie_title: escapeJson($scope.movie_title2), movie_url: escapeJson($scope.movie_url2)},
            {movie_thumbnail: $scope.movie_thumbnail3, movie_title: escapeJson($scope.movie_title3), movie_url: escapeJson($scope.movie_url3)}
        ];
    };

    $scope.deserializeSearch = function (sr) {
        if (sr !== undefined) {
            $scope.movie_thumbnail = unescapeJson(sr[0].movie_thumbnail);
            $scope.movie_title = unescapeJson(sr[0].movie_title);
            $scope.movie_url = unescapeJson(sr[0].movie_url);
            $scope.movie_thumbnail2 = unescapeJson(sr[1].movie_thumbnail);
            $scope.movie_title2 = unescapeJson(sr[1].movie_title);
            $scope.movie_url2 = unescapeJson(sr[1].movie_url);
            $scope.movie_thumbnail3 = sr[2].movie_thumbnail;
            $scope.movie_title3 = unescapeJson(sr[2].movie_title);
            $scope.movie_url3 = unescapeJson(sr[2].movie_url);
        }
    };

    $scope.newItem = function () {
        $scope.enterNew = true;
        $scope.editing = false;
        $scope.item = {};

        //AngularJS 1.2+ stuff
        //$scope.unsafeTitle = "";
    };

    $scope.createItem = function () {
        //alert($scope.item.channelPattern);
        var temp = $scope.saveButtonLabel;
        $scope.saveButtonLabel = "Saving";
        try {
            $scope.item.search_results = angular.toJson($scope.serializeSearch());
        } catch (e) {
            console.log("movie.js error createItem(): " + e);
        }
        //alert('createItem  search_results [' + $scope.item.search_results + ']');
        if (!offline) {
            //AngularJS 1.2+ stuff
            //$scope.item.title = $sce.trustAsHtml($scope.unsafeTitle);
            //$scope.item.description = $sce.trustAsHtml($scope.unsafeDescription);

            $scope.backendReady = false;
            var oid = getObjectId();
            var uid = getUsername();
            $scope.item.id = $scope.items.length + 1;
            var data = "id=" + $scope.item.id + "&" +
                "name=" + $scope.item.name + "&" +
                "title=" + $scope.item.title + "&" +
                "description=" + escapeJson($scope.item.description) + "&" +
                "url=" + $scope.item.url + "&" +
                "shared=" + $scope.item.shared + "&" +
                "channelPattern=" + $scope.item.channelPattern + "&" +
                "search_results=" + $scope.item.search_results + "&" +
                "oid=" + oid + "&" +
                "type=" + $scope.backendObject + "&action=create&uid=" + uid
                + "&origin=" + location.hostname + "&aid=" + gAppId;
            //alert('about to create [' + data + ']');
//            $http.post('/api/jwt/ws/crud?', data)
            $http({
                method: 'POST',
                url: gCacheProxy + '/api/jwt/ws/crud',
                data: data,
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                //headers: {'Content-type': 'application/json'}
            })
                .success(function (response, status, headers, config) {
                    //alert('created response [' + response.id + "]");
                    //if(response.id === $scope.item.id) {
                    try {
                        if (response.id) {
                            $scope.item.id = response.id;
                            $scope.item.owner = response.owner === $scope.userId ? "Me" : response.owner;
                            $scope.item.createDate = response.modified;
                            if ($scope.pagedItems[$scope.currentPage] === undefined) {	//TBD
                                $scope.pagedItems[$scope.currentPage] = [];
                            }
                            $scope.pagedItems[$scope.currentPage].push($scope.item);
                            if ($scope.items.length === 0) {	//TBD
                                $scope.items = [];
                            }
                            $scope.items.push($scope.item);
                            $scope.enterNew = false;
                            $scope.editing = false;
                            $scope.item = "";
                            $scope.backendReady = true;
                            //alert('created');
                        } else {
                            $scope.error_message = response.error_message;
                            alert("movie.js: 12a2 error: response.id [" + response.id + "] status [" + status + "] headers [" + headers + "] config [" + config + "]");
                        }
                    }
                    catch (e) {
                        $scope.error_message = response.error_message;
                        alert("movie.js: 12b2 error: response [" + response + "] status [" + status + "] headers [" + headers + "] config [" + config + "]");
                    }
                }
            ).error(function (response, status, headers, config) {
                    $scope.error_message = response.error_message;
                    //$console && $console.log("movie.js 12c: We might have a bit of issue here :( ************************** response = [" + response + "] response.error_message = [" + response.error_message + "] **************************");
                    if(status !== 0) {	//TODO workaround to suppress the weird error! :(
                        alert("movie.js: 12c2 error: status [" + status + "] headers [" + headers + "] config [" + config + "] data [" + data + "]");
                    }
                });
        } else {
            if ($scope.pagedItems[$scope.currentPage] === undefined) {
                $scope.pagedItems[$scope.currentPage] = [];
            }
            $scope.item.id = $scope.pagedItems[$scope.currentPage].length + 1;
            $scope.item.createDate = new Date();
            $scope.pagedItems[$scope.currentPage].push($scope.item);

            $scope.enterNew = false;
            $scope.editing = false;
            $scope.item = "";
        }
        $scope.saveButtonLabel = temp;
    };

    $scope.cancelSave = function () {
        $scope.error_message = null;
        $scope.enterNew = false;
        $scope.editing = false;
        $scope.item = {};
        $scope.backendReady = true;
    };

    $scope.cancelSaveForCalendar = function () {
        $scope.cancelSave();
        showDone();
    };

    $scope.viewItem = function (item) {
        item.title = $sce.trustAsHtml(item.title);
        //$scope.item.description = $sce.trustAsHtml($scope.unsafeDescription);

        $scope.item = { id: item.id, title: item.title, description: item.description, url: item.url, createDate: item.createDate};   //opt out data-binding
        //$console && $console.log('viewItem  url [' + item.url + ']');
        //alert("viewItem url = [" + item.url + "]");
        function popitup(url) {
            var newwindow = window.open(url, 'name', 'top=150px,left=200px,height=580px,width=1055px');
            if (window.focus) {
                newwindow.focus();
            }
            return false;
        }

        return popitup(item.url);
//        $('#element_to_pop_up').bPopup({
//            content:'iframe', //'ajax', 'iframe' or 'image'
//            contentContainer:'.content',
//            loadUrl:item.url //Uses jQuery.load()
//        });

    };

    $scope.editItem = function (item) {
        $scope.item = { id: item.id, title: item.title, description: item.description, url: item.url, createDate: item.createDate, shared: item.shared, channelPattern: item.channelPattern};   //opt out data-binding
        $scope.originalItem = item;

        //alert($scope.originalItem.channelPattern);

        $scope.enterNew = false;
        $scope.editing = true;
        //$scope.item = item;   //no data-binding
/*
        var obj;
        if (item.search_results && item.search_results.value) {
            try {
                obj = angular.fromJson(
                    item.search_results.value
                );
            } catch (e) {
                console.log("movie.js error editItem(): " + e);
            }
            //$console && $console.log('editItem  search_results [' + obj + '] obj[0].movie_thumbnail [' + obj[0].movie_thumbnail + ']');
        }
        $scope.deserializeSearch(obj);
*/

        //AngularJS 1.2+ stuff
//        $scope.unsafeTitle = $scope.item.title;

    };

    $scope.editItemForCalendar = function (item) {
//        $scope.item = { id: item.id, title: item.title, description: item.description, url: item.url, createDate: item.createDate, shared: item.shared, channelPattern: item.channelPattern};   //opt out data-binding
//        $scope.originalItem = item;
//
//        //alert($scope.originalItem.channelPattern);
//
//        $scope.enterNew = false;
//        $scope.editing = true;
//        //$scope.item = item;   //no data-binding
//        var obj;
//        if (item.search_results && item.search_results.value) {
//            obj = angular.fromJson(
//                item.search_results.value
//            );
//            //$console && $console.log('editItem  search_results [' + obj + '] obj[0].movie_thumbnail [' + obj[0].movie_thumbnail + ']');
//        }
//        $scope.deserializeSearch(obj);

        $scope.isDisabled = false;

        $scope.editItem(item);
        handleNonDraggable(item, item.url);

    };

    $scope.updateItem = function () {
        //alert($scope.item.channelPattern);

        var temp = $scope.updateButtonLabel;
        $scope.updateButtonLabel = "Updating";
        try {
            $scope.item.search_results = angular.toJson($scope.serializeSearch());
        } catch (e) {
            console.log("movie.js error updateItem(): " + e);
        }
        if (!offline) {
            //AngularJS 1.2+ stuff
            //$scope.item.title = $sce.trustAsHtml($scope.unsafeTitle); //already sanitized by editItem, avoid error "Attempted to trust a non-string value in a content requiring a string: Context: html

            $scope.backendReady = false;
            //alert('updateItem  search_results [' + $scope.item.search_results + ']');
            var oid = getObjectId();
            var uid = getUsername();
            var data = "id=" + $scope.item.id + "&" +
                "name=" + $scope.item.name + "&" +
                "title=" + $scope.item.title + "&" +
                "description=" + escapeJson($scope.item.description) + "&" +
                "url=" + $scope.item.url + "&" +
                "shared=" + $scope.item.shared + "&" +
                "channelPattern=" + $scope.item.channelPattern + "&" +
                "search_results=" + $scope.item.search_results + "&" +
                "oid=" + oid + "&" +
                "type=" + $scope.backendObject + "&action=update&uid=" + uid
                + "&origin=" + location.hostname + "&aid=" + gAppId;
            //alert('about to update [' + data + ']');
//            $http.post('/api/jwt/ws/crud?', data)
            $http({
                method: 'POST',
                url: gCacheProxy + '/api/jwt/ws/crud',
                data: data,
                headers: {'Content-Type': 'application/x-www-form-urlencoded'}
                //headers: {'Content-type': 'application/json'}
            })
                .success(function (response, status, headers, config) {
                    //$console && $console.log('updated response id = ' + response.id + " $scope.item.id = " + $scope.item.id);
                    if (response.id === $scope.item.id) {
                        $scope.originalItem.createDate = response.modified;
                        $scope.originalItem.owner = response.owner === $scope.userId ? "Me" : response.owner;
                        $scope.originalItem.title = $scope.item.title;   //copy the attributes you need to update
                        $scope.originalItem.description = $scope.item.description; //copy the attributes you need to update
                        $scope.originalItem.url = $scope.item.url; //copy the attributes you need to update
                        $scope.originalItem.channelPattern = $scope.item.channelPattern; //copy the attributes you need to update
                        $scope.originalItem.search_results = $scope.item.search_results;
                        $scope.originalItem.shared = $scope.item.shared;

                        $scope.enterNew = false;
                        $scope.editing = false;
                        $scope.backendReady = true;
                        //alert('updated');
                    }
                }
            ).error(function (response, status, headers, config) {
                    if (status === '403') {
                        $scope.error_message = "You do not have access to the resource. Are you the content owner?";
                        //alert("Error: " + $scope.error_message);
                        alert("movie.js: 14 error: response [" + response + "] status [" + status + "] headers [" + headers + "] config [" + config + "]");
                    }
                });
        } else {
            $scope.originalItem.createDate = new Date();
            $scope.originalItem.title = $scope.item.title;   //copy the attributes you need to update
            $scope.originalItem.description = $scope.item.description; //copy the attributes you need to update
            $scope.originalItem.url = $scope.item.url; //copy the attributes you need to update
            $scope.originalItem.search_results = $scope.item.search_results;
            //clear temp items to remove any bindings from the input
            $scope.enterNew = false;
            $scope.editing = false;
        }
        $scope.updateButtonLabel = temp;
    };

    $scope.deleteItem = function (item) {
        //alert(item.id);
        if (confirm("Delete the entry?")) {
            var temp = $scope.deleteLinkText;
            $scope.deleteLinkText = "Deleting";
            if (!offline) {
                $scope.backendReady = false;
                var oid = getObjectId();
                var uid = getUsername();
                var data = "id=" + item.id + "&" +
                    "oid=" + oid + "&" +
                    "type=" + $scope.backendObject + "&action=delete&uid=" + uid
                    + "&origin=" + location.hostname + "&aid=" + gAppId;
                $http({
                    method: 'POST',
                    url: gCacheProxy + '/api/jwt/ws/crud',
                    data: data,
                    headers: {
                        'Content-Type': 'application/x-www-form-urlencoded; charset=UTF-8'
                    }
                })
                    .success(function (response, status, headers, config) {
                        //$console && $console.log("deleteItem: checking response.id [" + response.id + "] with item.id [" + item.id + "]");
                        if (response.id === item.id) {
                            var index;  // = $scope.pagedItems[$scope.currentPage].indexOf(item);
                            for(var i = 0; i < $scope.pagedItems[$scope.currentPage].length; i++) {
                                if ($scope.pagedItems[$scope.currentPage][i].id === item.id) {
                                    // i is the index
                                    index = i;
                                    break;  //match only the first one
                                }
                            }
                            //alert("index to be deleted = " + index);
                            $scope.pagedItems[$scope.currentPage].splice(index, 1);
                            //$console && $console.log("item index deleted was " + index + " id [" + item.id + "]");

                            $scope.editing = false;
                            $scope.backendReady = true;
                        }
                    })
                    .error(function (response, status, headers, config) {
                        $scope.error_message = response.error_message;
                        if(status !== 0) {	//TODO workaround to suppress the weird error! :(
                            alert("movie.js: 15 error: response [" + response + "] status [" + status + "] headers [" + headers + "] config [" + config + "]");
                        }
                    });
            } else {
                var index = $scope.pagedItems[$scope.currentPage].indexOf(item);
                $scope.pagedItems[$scope.currentPage].splice(index, 1);
            }
            $scope.deleteLinkText = temp;
        }
    };

    //=== begin of scheduling of shuffle play
    //c.f. http://stackoverflow.com/questions/10211145/getting-current-date-and-time-in-javascript
    Date.prototype.timeNow = function () {
        return ((this.getHours() < 10)?"0":"") + this.getHours() +":"+ ((this.getMinutes() < 10)?"0":"") + this.getMinutes() +":"+ ((this.getSeconds() < 10)?"0":"") + this.getSeconds();
    };
    //TODO begin watches are not working!
    $scope.$watch('userSelection.movie.playTime', function (playTime) {
        try {
            //alert("gTempEventId [" + gTempEventId + "]")
            if (gTempEventId !== undefined) {
                gCalendar.fullCalendar('removeEvents', gTempEventId);
            }
        } catch(e) {
            window.console && console.log('movie.js watch 1 error [' + e + ']');
        }
        //window.console && console.log('playTime [' + playTime + ']');
        //var startDate = $scope.startDate;	//new Date($scope.userSelection.movie.playDate);
        $scope.timeString = dateFilter(playTime,  'HH:mm');
        //alert($scope.timeString);
        $scope.showTempEvent($scope.userSelection.movie.playDate);
    });
    $scope.$watch('userSelection.movie.playDate', function (playDate) {
        $scope.showTempEvent(playDate);
    });
    //$scope.$watch('userSelection.gesture', function (swipe) {
    //    try {
    //        alert("userSelection.gesture [" + swipe + "]")
    //
    //        assert(swipe, "movie.js: swipe [" + swipe + "]");
    //        console && console.log("movie.js: swipe [" + swipe + "]");
    //        if(swipe === 'right') {
    //            $scope.selectNextPage(page);
    //        } else
    //        if(swipe === 'left') {
    //            $scope.selectPreviousPage(page);
    //        }
    //    } catch(e) {
    //        window.console && console.log('movie.js watch 2 error [' + e + ']');
    //    }
    //});
    //TODO end watches are not working!

    //AngularJS 1.2+ stuff
    //$scope.$watch('title', function(value) {
    //    $scope.unsafeTitle = $sce.trustAsHtml(value);
    //});

    $scope.showTempEvent = function(playDate) {
        //alert("gTempEventId [" + gTempEventId + "]")
        try {
            if (gTempEventId !== undefined) {
                gCalendar && gCalendar.fullCalendar('removeEvents', gTempEventId);
            }
        } catch(e) {
            window.console && console.log('movie.js showTempEvent() error [' + e + ']');
        }

        //window.console && console.log('playDate [' + dateFilter(playDate,  'yyyy-MM-dd') + ']');
        //var startDate = new Date(playDate);
        var tempDate = new Date(dateFilter(playDate + ' ' + $scope.timeString,  'yyyy-MM-dd HH:MM'));
        //window.console && console.log('tempDate [' + dateFilter(tempDate,  'yyyy-MM-dd') + ']');

        var startDate = new Date(tempDate.getFullYear(), tempDate.getMonth(), tempDate.getDate(), tempDate.getHours(), tempDate.getMinutes(), tempDate.getSeconds(), 0);
        //alert($scope.userSelection.movie.shuffleIt);
        if(!$scope.userSelection.movie.shuffleIt && isOverlapped(startDate) === true) {
            alert('Oops, but there is already one item scheduled at ' + startDate + '. Please try other date and time or remove the existing item.');
            return;
        }

        //alert('movie.js showTempEvent(): startDate [' + startDate + ']');
        $scope.userSelectedDateTime = startDate;
        var endDate = new Date(startDate.getFullYear(), startDate.getMonth(), startDate.getDate(), startDate.getHours(), startDate.getMinutes() + 60, startDate.getSeconds(), 0);
        var aDay = $scope.userSelection.movie.playAllDay;
        createTemporaryEvent(gCalendar, $('input[name=title]').val()+' ['+gTempEventId + ']', startDate, endDate, aDay, $scope.userSelection.movie.shuffleIt);
    };
    $scope.userSelection = {
        movie: {
            shuffleIt: false
            , playDate: new Date(dateFilter(new Date(),  'yyyy-MM-dd'))  //'1976-02-14' //leading zero is not optional!!!
            , playTime: new Date(dateFilter(new Date(),  'HH:mm'))   //new Date().timeNow()
            , playAllDay: false
        }
        //, gesture: 'Swipe left/right with LeapMotion!'
    };

    //TODO to call createTemporaryEvent() whenever shuffleIt checkbox is clicked!
//    var tempDate = new Date(dateFilter($scope.userSelection.movie.playDate + ' ' + $scope.userSelection.movie.playTime,  'yyyy-MM-dd HH:MM'));
//    window.console && console.log('tempDate [' + dateFilter(tempDate,  'yyyy-MM-dd') + ']');
//    var startDate = new Date(tempDate.getFullYear(), tempDate.getMonth(), tempDate.getDate(), tempDate.getHours(), tempDate.getMinutes(), tempDate.getSeconds(), 0);
//    createTemporaryEvent(gCalendar, $('input[name=title]').val()+' ['+gTempEventId + ']', startDate, endDate, aDay, $scope.userSelection.movie.shuffleIt);

    //TODO need to somehow avoid calling handleDraggable!
    $scope.scheduleItem = function (form) {
        $scope.isDisabled = true;
        showInProgress();

        //alert('movie.js scheduleItem() entered 4');
        if(form.$valid) {    //care only if there is no form validation error e.g. due to required etc c.f. http://scotch.io/tutorials/javascript/angularjs-form-validation
            if(!$scope.userSelection.movie.shuffleIt && isOverlapped(new Date($scope.userSelectedDateTime)) === true) {
                alert('Oops, but there is already one item scheduled at ' + $scope.userSelectedDateTime + '. Please try other date and time or remove the existing item.');
            } else {
                createNewEvent(gCalendar, new Date($scope.userSelectedDateTime), $scope.userSelection.movie.playAllDay, $scope.userSelection.movie.shuffleIt);
                refreshFullCalendar(true);
            }
            //window.console && console.log('movie.js scheduleItem() done');
        } else {
            window.console && console.log('movie.js scheduleItem() skipped due to form validation error!');
        }

        $scope.editing = false;
//        $scope.isDisabled = false;
    };
    //=== end of scheduling of shuffle play

    //=== calendar logic
    $scope.recurringButtonTitle = "One Time Event";
    $scope.cancelRecurringButtonTitle = "Close";
    $scope.calheader1 = "Do Not Repeat (one time event only)";
    $scope.calheader2 = "Repeat Daily (settings will be carried over to the subsequent year)";
    $scope.calheader3 = "Repeat Weekly (settings will be carried over to the subsequent year)";
    $scope.calheader4 = "Repeat Monthly (settings will be carried over to the subsequent year)";

    $scope.recurringItem = function () {
        $scope.repeatEvent = true;
    };

    $scope.updateRecurringItem = function () {
        $scope.repeatEvent = false;
        $scope.backendReady = true;
    };

    $scope.cancelRecurringItem = function () {
        $scope.repeatEvent = false;
        $scope.backendReady = true;
    };

    /** used by go('/html/calendar.html') in movie.html */
    $scope.go = function(url) {
        $scope.restoreStates();

        url = //$location.host + ':' + $location.port +
            url + '?username=' + $scope.userid + '&logintype=' + $scope.logintype;
        //alert(url);
//        $location.path(url);  //TODO didn't work for some reason with relative path
        window.location = url;
        event.preventDefault();
    };

    $scope.restoreStates = function() {
        try {
            if (gStore === undefined) {
                //stupid workaround! :(
            } else {
                //alert('movie.js store [' + store + "]");    //unfortunately store is bootrappd in global namespace (c.f. main-config)
                restoreStates(gStore);
                $scope.userid = userid;
                $scope.logintype = logintype;
            }
        } catch (e) {
            alert('movie.js Not able to access browser local storage. Fatal error [' + e + "]");
            return;
        }
        var msg = 'in init.js: userid [' + userid + '] logintype [' + logintype + ']';
        //window.console && console.log(msg);
        if (!userid && !logintype) {
            var loginUrl = "https://" + location.hostname + ":" + location.port + App.login_url;
            var message = "2 Your session is invalid, you will need to login again. Redirecting to " + loginUrl;
            alert(message);
            //window.console && console.log(message);
//            $('#currentUser').html("User ID: " + userid + " ");
//            $('#currentUserName').html(userid);  //just the display, not to pull data, so no security risk here :)
            window.location.assign(loginUrl);
            return false;
        }
    };

    $scope.selectPreviousPage = function(page) {
        if (page.number > 1) {
            --page.number;
            $scope.loadItems();
        }
        //alert('movie.js selectPreviousPage() pageNumber [' + page.number + ']');
    };
    $scope.selectNextPage = function(page) {
        if(page.number < page.totalPage) {
            ++page.number;
        }
        if(page.number == 1) { //has to put a check as the request might be accidentally called from non-paginated request :(
            page.number = 2;
        }
        //alert('movie.js selectNextPage() pageNumber [' + page.number + '] before loadMoreItems ...');
        $scope.loadMoreItems();
        //alert('movie.js selectNextPage() pageNumber [' + page.number + ']');
    };

    $rootScope.bootstrapper = 'You have successfully bootstrapped AngularJS';

}

//=== http://stackoverflow.com/questions/25111831/controller-not-a-function-got-undefined-while-defining-controllers-globally
//=== http://blog.bulte.net/12-24-2013/angular-wordpress-cors.html
/** Angular-Leap (https://github.com/angular-leap/angular-leap) does not work (yet) */
angular.module('app', [
    //'angularLeap',    //it breaks LeapStrap!!! :(
    'ngTouch'
    ])
.config(['$httpProvider', '$controllerProvider',function ($httpProvider, $controllerProvider) {
    //var sessionId = "{!$Api.Session_ID}";
    $controllerProvider.allowGlobals();     //thanks to 1.3
    $httpProvider.defaults.useXDomain = true;
    delete $httpProvider.defaults.headers.common["X-Requested-With"];
    //$httpProvider.defaults.headers.common["Access-Control-Allow-Origin"] = "*";
    //$httpProvider.defaults.headers.common["Accept"] = "application/json";
    //$httpProvider.defaults.headers.common["content-type"] = "application/json";
    //$httpProvider.defaults.headers.common['Authorization'] = "OAuth " + sessionId ;
    //$httpProvider.defaults.headers.common['X-User-Agent'] = "MyClient" ;
    $httpProvider.defaults.headers.common['Authorization'] = 'Bearer ' + localStorage.getItem('2shareJWTToken');   //JWT support
    console && console.log("movie.js config(): done 6")
}])
//angular.module('app',[])//dependency 'ui.bootstrap' is conflicting with 1.4, thus removed (c.f. http://stackoverflow.com/questions/26332202/using-ui-bootstrap-causing-issues-with-carousel)!
.controller('MovieController',
    ['$scope', '$filter', '$http', '$rootScope',
        //'loglevel',
        '$timeout', 'dateFilter', '$location'
        , '$sce'
        ,
        MovieController //1.3 does not allow global and we need it to be as angular.bootstrap needs a global function
    ]
)
//MovieController.$inject = ['$scope', '$filter', '$http', '$rootScope',
//    //'loglevel',
//    '$timeout', 'dateFilter', '$location'
//    , '$sce'
//];

//=== http://www.grobmeier.de/angular-js-binding-to-jquery-ui-datepicker-example-07092012.html#.UyIsZVFdVtZ
//angular.module('app')
//<<<<<<< HEAD
.directive('myDatepicker', function() {
    return function(scope, element, attrs) {
        element.datepicker({
            inline: true,
            dateFormat: 'dd.mm.yy',
            onSelect: function(dateText) {
                var modelPath = $(this).attr('ng-model');
                putObject(modelPath, scope, dateText);
                scope.$apply();
            }
        });
    }
})
//=======
//.directive('myDatepicker', function ($parse) {
//    return function (scope, element, attrs, controller) {
//        var ngModel = $parse(attrs.ngModel);
//        $(function(){
//            element.datepicker({
//                showOn:"both",
//                changeYear:true,
//                changeMonth:true,
//                dateFormat:'yy-mm-dd',
//                maxDate: new Date(),
//                yearRange: '1920:2012',
//                onSelect:function (dateText, inst) {
//                    scope.$apply(function(scope){
//                        // Change binded variable
//                        ngModel.assign(scope, dateText);
//                    });
//                }
//            });
//        });
//    };
//})
//
//>>>>>>> 2eb925d094e6ec5a442cea17398f6141cb3a3148
/*
 This directive allows us to pass a function in on an enter key to do what we want.
 Courtesy of http://ericsaupe.com/angularjs-detect-enter-key-ngenter/
 */
//angular.module('app')
.directive('ngEnter', function () {
    return function (scope, element, attrs) {
        element.bind("keydown keypress", function (event) {
            if(event.which === 13) {
                scope.$apply(function (){
                    scope.$eval(attrs.ngEnter);
                });

                event.preventDefault();
            }
        });
    };
})
;


//Error:
//[$injector:modulerr] Failed to instantiate module app due to:TypeError: Cannot set property 'Content-Type' of undefined