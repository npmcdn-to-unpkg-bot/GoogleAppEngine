'use strict';
// App 0.0.1
var mBuild = '0168';
var myApp = angular.module('app', [], ctrlRead);
var uid;

$.ajaxSetup({headers: { 'Authorization': 'Bearer ' + localStorage.getItem('2shareJWTToken') }}); //JWT support

function ctrlRead($scope, $filter, $http, $rootScope, $console, $timeout) {
    //var offline = true;  //DEV only
    var offline = false;  //need to be uncommented for production
    $scope.backendReady = false;
    $scope.mBuild = mBuild;
    //	alert('version 3');
    //=== BEGIN - CAN NOT SET GLOBALLY AS THE APP EXPECT DEFAULT (ANGULARJS-STYLE/JSON) WITH RESPONSE BUT SUBMIT ALA JQUERY-STYLE!!! ========================
    //$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
    //$http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
    //=== END - CAN NOT SET GLOBALLY AS THE APP EXPECT DEFAULT (ANGULARJS-STYLE/JSON) WITH RESPONSE BUT SUBMIT ALA JQUERY-STYLE!!! ========================
    $scope.backendObject = "modelMovie";

    //=== for Safari and Android mobile browser
//    $('.input-large search-query').clearSearch({ callback: function() {
//            $scope.query = "";
//            window.console && console.log("cleared"); }
//        }
//    );

    // app specific init
    var sortingOrder = 'name';
    //$scope.title = "Custom Channels Profile";
    $scope.createButtonTitle = "Migrate All";
    $scope.saveLinkTitle = "Save";
    $scope.saveButtonLabel = "Save";
    $scope.updateButtonLabel = "Migrate";
    $scope.deleteLinkText = "Delete";
    $scope.header1 = "Action";
    $scope.header2 = "ID";
    $scope.header3 = "Title";
    $scope.header4 = "Description";    //TBD: mapped to URL!!!
    $scope.header5 = "URL";
    $scope.header6 = "Share It";
    // common init
    $scope.sortingOrder = sortingOrder;
    $scope.reverse = false;
    $scope.filteredItems = [];
    $scope.groupedItems = [];
    $scope.itemsPerPage = 10;
    $scope.pagedItems = [];
    $scope.searchResults = [];
    $scope.currentPage = 0;
    $scope.items =
    [];
    //[{  "key": {    "parent_key": null,    "kind": "User",    "app_id": null,    "id": 19,    "name": null  },  "id": 1,  "name": "test user 1",  "movie": [    {      "key": {        "parent_key": {          "parent_key": null,          "kind": "User",          "app_id": null,          "id": 19,          "name": null        },        "kind": "Movie",        "app_id": null,        "id": 20,        "name": null      },      "id": null,      "title": null,      "search_results": null,      "u_r_l": null,      "modified": "May 3, 2013 4:50:24 PM",      "jdo_detached_state": null    }  ],  "jdo_detached_state": null},{  "key": {    "parent_key": {      "parent_key": null,      "kind": "User",      "app_id": null,      "id": 19,      "name": null    },    "kind": "Movie",    "app_id": null,    "id": 20,    "name": null  },  "id": null,  "title": null,  "search_results": null,  "u_r_l": null,  "modified": "May 3, 2013 4:50:24 PM",  "jdo_detached_state": null},{  "key": {    "parent_key": null,    "kind": "User",    "app_id": null,    "id": 21,    "name": null  },  "id": 1,  "name": "test user 1",  "movie": [    {      "key": {        "parent_key": {          "parent_key": null,          "kind": "User",          "app_id": null,          "id": 21,          "name": null        },        "kind": "Movie",        "app_id": null,        "id": 22,        "name": null      },      "id": null,      "title": null,      "search_results": null,      "u_r_l": null,      "modified": "May 3, 2013 4:53:11 PM",      "jdo_detached_state": null    }  ],  "jdo_detached_state": null},{  "key": {    "parent_key": {      "parent_key": null,      "kind": "User",      "app_id": null,      "id": 21,      "name": null    },    "kind": "Movie",    "app_id": null,    "id": 22,    "name": null  },  "id": null,  "title": null,  "search_results": null,  "u_r_l": null,  "modified": "May 3, 2013 4:53:11 PM",  "jdo_detached_state": null}];
    $scope.api = "";
    $scope.movie_thumbnail = "";

    function escapeJson (str) {
        return encodeURIComponent(str)
            //.replace(/([\\]|[\"]|[\/])/g, "\\$1")
/*
            .replace(/[\b]/g, "")
            .replace(/[\f]/g, "")
            .replace(/[\n]/g, "")
            .replace(/[\r]/g, "")
            .replace(/[\t]/g, "")
*/
            ;
    };

    function unescapeJson (str) {
        return decodeURIComponent(str);
    };

    function getObjectId() {
        return $scope.objectId;  //Parse.User.current().id;  //does not work for some reason, always empty!
    }

    function getUsername() {
        var retVal;
        if(typeof Parse.User !== 'undefined' && Parse.User.current() !== null && typeof Parse.User.current() !== 'undefined' && typeof Parse.User.current().getUsername() !== 'undefined' && Parse.User.current().getUsername().trim() !== '') {
            retVal = Parse.User.current().getUsername();
            //alert("objectId [" + Parse.User.current().id + "]");
            $scope.objectId = Parse.User.current().id;
            $scope.userId = retVal;
        } else {
            window.console && console.log("2getting Google Plus id ...");
            retVal = $.url().param('username');     //TBD security risk
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
//                $('#results').append(load
//                    $('<p>You are:<br/><img src=\"' + profile.image.url + '\"><br/>' + profile.displayName + '</p>'));
                    var prefix = "gp";
                    currentGoogleUserName = prefix + profile.id;      //get id etc from https://developers.google.com/+/api/
                    retVal = currentGoogleUserName;
                    window.console && console.log("2Google Plus id = [" + currentGoogleUserName + "]");
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

    $scope.getYoutubeVideoId = function(youtube_url) {
        var youtube_id;

        //http://stackoverflow.com/questions/3452546/javascript-regex-how-to-get-youtube-video-id-from-url
        if(youtube_url.match('/?.*(?:youtu.be\\/|v\\/|u/\\w/|embed\\/|watch\\?.*&?v=)')){
            youtube_id=youtube_url.split(/v\/|v=|youtu\.be\//)[1].split(/[?&]/)[0];
        }

        return youtube_id;
    }

    //=== movie logic
    $scope.loadItems = function() {
        var uid = $.url().param('username');    //getUsername();
		window.console && console.log('3about to get the list for shared collections [' + uid + "] ...");
    	$scope.items.length = 0;	//clear everything and reload again :(
    	$scope.pagedItems.length = 0;
        $scope.searchResults.length = 0;
		window.console && console.log('4list reinit');
        //uid = "j";
        //alert(uid);
        $http.get('/api/jwt/ws/crud?type=' + $scope.backendObject + "&action=migrate&filter=ownedbyme&uid=" + uid)
	    .success(function(data, status, headers, config){
                window.console && console.log('3success entered');
	        var j;
            if(data !== undefined) {
                if(data.length === 0) {
                    $("#loadingTable").hide();
                }
//                $scope.delay = $timeout(function(){
//                    window.console && console.log('1 5 seconds delay')
//                    $("#loadingTable").hide();
//                }, 5000);
                for (var i = 0; i < data.length; i++) {
                    $scope.backendReady = false;
                    j = {id: data[i].key_string, owner: data[i].owner, oid: data[i].oid, title: data[i].title, description: data[i].description, url: data[i].u_r_l, createDate: data[i].modified, shared: data[i].shared};
                    j.search_results = data[i].search_results;
                    try{
                        $scope.items.push(j);
                    }
                    catch(e){
//                        alert('An error has occurred: '+ e.message + ' - The application will not function correctly. Please contact the developer!');
                        $console.error('migrate.js loadItems(): An error has occurred: '+ e.message + ' - The application will not function correctly. Please contact the developer!');
                    }
                }
                $("#loadingTable").hide();
            }

            $scope.backendReady = true;
            $scope.renderPagedTable(true);

//            $rootScope.pagedItems = $scope.items;   //[{title:'John', id:25}, {title:'Mary', id:26},{title:'Joe', id:27}];
            window.console && console.log("items loaded [gBuild " + mBuild + "]");
	    })
	    .error(function(data, status, headers, config){
	        $scope.error_message = data.error_message;
	    });
    };
    if(!offline) {
	    $scope.loadItems();
    }

    var searchMatch = function (haystack, needle) {
        var done = true;
        try{
            if (!needle) {
                done = true;
            } else {
                done = haystack.toLowerCase().indexOf(needle.toLowerCase()) !== -1;
            }
        }
        catch(e){
            window.console && console.log("search error: " + e);
        }
        return done;
    };

    // init the filtered items
    $scope.search = function () {
        window.console && console.log("searching ...");
        var api_url;
        //http://developer.rottentomatoes.com/docs/read/JSON
        //JSON_CALLBACK has to be spelled exactly in its case (AngularJS requires it like that :)!)
//        api_url = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=tv2yr43592jd6pdxaahjym4b&page_limit=1&q="+$scope.query+"&callback=JSON_CALLBACK";
//        //window.console && console.log(api_url);
//        // retrieve movie metadata
//        $http.jsonp(api_url)
//            .then( function ( response ) {
//                $scope.backendReady = false;
//                if(response !== undefined && response.data !== undefined && response.data.movies !== undefined && response.data.movies[0] !== undefined) {
//                    //$scope.data = response.data;
//                    $scope.movie_thumbnail = response.data.movies[0].posters.thumbnail;
//                    $scope.movie_title = "IMDB Review";
//                    if(response.data.movies[0].alternate_ids !== undefined) {
//                        $scope.movie_url = "http://www.imdb.com/title/tt" + response.data.movies[0].alternate_ids.imdb;
//                    }
//                    window.console && console.log("thumbnail is [" + $scope.movie_thumbnail + "] " + $scope.movie_title + " " + $scope.movie_url);
//                }
//                $scope.backendReady = true;
//            });
        //https://developers.google.com/youtube/2.0/developers_guide_protocol_video_feeds
        api_url = "https://gdata.youtube.com/feeds/api/videos?q="+$scope.query+"&start-index=21&max-results=1&v=2&alt=jsonc&callback=JSON_CALLBACK";
        window.console && console.log(api_url);
        // retrieve youtube video metadata
        $http.jsonp(api_url)
            .then( function ( response ) {
                $scope.backendReady = false;
                if(response !== undefined && response.data !== undefined && response.data.data !== undefined && response.data.data.items !== undefined && response.data.data.items[0] !== undefined) {
                    //$scope.data = response.data;
                    $scope.movie_thumbnail2 = response.data.data.items[0].thumbnail.sqDefault;
//                    $scope.movie_title2 = response.data.data.items[0].title;
                    $scope.movie_title2 = "YouTube Videos";
                    if(response && response.data && response.data.data && response.data.data.items[0] && response.data.data.items[0].player) {
                        $scope.movie_url2 = response.data.data.items[0].player && response.data.data.items[0].player["default"];
                    }
                    window.console && console.log("thumbnail is [" + $scope.movie_thumbnail2 + "] " + $scope.movie_title2 + " " + $scope.movie_url2);
                }
                $scope.backendReady = true;
            });
        //http://developers.facebook.com/docs/reference/api/search/
        api_url = "https://graph.facebook.com/search?q="+$scope.query+"&type=post&callback=JSON_CALLBACK";
        window.console && console.log(api_url);
        // retrieve public post metadata
        $http.jsonp(api_url)
            .then( function ( response ) {
                $scope.backendReady = false;
                if(response !== undefined && response.data !== undefined && response.data.data !== undefined && response.data.data[0] !== undefined) {
                    //$scope.data = response.data;
                    $scope.movie_thumbnail3 = response.data.data[0].picture;
//                    $scope.movie_title3 = response.data.data[0].message.substring(0, Math.min(100,response.data.data[0].message.length));
                    $scope.movie_title3 = "Facebook Post";
                    if(response.data.data[0].link !== undefined) {
                        $scope.movie_url3 = response.data.data[0].link;
                    }
                    window.console && console.log("thumbnail is [" + $scope.movie_thumbnail3 + "] " + $scope.movie_title3 + " " + $scope.movie_url3);
                }
                $scope.backendReady = true;
            });

        $scope.renderPagedTable();
    };

    $scope.renderPagedTable = function(paginate) {
        $scope.filteredItems = $filter('filter')($scope.items, function (item) {
            for(var attr in item) {
                if (searchMatch(item[attr], $scope.query))
                    return true;
            }
            return false;
        });
        // take care of the sorting order
        if ($scope.sortingOrder !== '') {
            $scope.filteredItems = $filter('orderBy')($scope.filteredItems, $scope.sortingOrder, $scope.reverse);
        }
        $scope.currentPage = 0;

        if($scope.query && $scope.query.length > 0) {
            paginate = false;
        } else {
            paginate = true;
        }
        // now group by pages
        $scope.groupToPages(paginate);
    }

    // calculate page in place
    $scope.groupToPages = function (paginate) {
        $scope.pagedItems = [];

        //KISS paginate with huge/ridiculous row window size if it is supposed to be none
        var pagedSize = $scope.itemsPerPage;
        if(!paginate) {
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
    $scope.sort_by = function(newSortingOrder) {
        if ($scope.sortingOrder == newSortingOrder)
            $scope.reverse = !$scope.reverse;

        $scope.sortingOrder = newSortingOrder;

        // icon setup
        $('th i').each(function(){
            // icon reset
            $(this).removeClass().addClass('icon-sort');
        });
        if ($scope.reverse)
            $('th.'+newSortingOrder+' i').removeClass().addClass('icon-chevron-up');
        else
            $('th.'+newSortingOrder+' i').removeClass().addClass('icon-chevron-down');
    };

    $scope.resetSearch = function() {
        $scope.query = "";
        $scope.renderPagedTable(true);
    };

    $scope.serializeSearch = function() {
        return $scope.searchResults = [
            {movie_thumbnail: escapeJson($scope.movie_thumbnail), movie_title: escapeJson($scope.movie_title), movie_url: escapeJson($scope.movie_url)},
            {movie_thumbnail: escapeJson($scope.movie_thumbnail2), movie_title: escapeJson($scope.movie_title2), movie_url: escapeJson($scope.movie_url2)},
            {movie_thumbnail: $scope.movie_thumbnail3, movie_title: escapeJson($scope.movie_title3), movie_url: escapeJson($scope.movie_url3)}
        ];
    };

    $scope.deserializeSearch = function(sr) {
        if(sr !== undefined) {
            $scope.movie_thumbnail = unescapeJson(sr[0].movie_thumbnail);
            $scope.movie_title = unescapeJson(sr[0].movie_title);
            $scope.movie_url =unescapeJson(sr[0].movie_url);
            $scope.movie_thumbnail2 = unescapeJson(sr[1].movie_thumbnail);
            $scope.movie_title2 = unescapeJson(sr[1].movie_title);
            $scope.movie_url2 = unescapeJson(sr[1].movie_url);
            $scope.movie_thumbnail3 = sr[2].movie_thumbnail;
            $scope.movie_title3 = unescapeJson(sr[2].movie_title);
            $scope.movie_url3 = unescapeJson(sr[2].movie_url);
        }
    };

    $scope.newItem = function() {
        $scope.enterNew = true;
        $scope.editing = false;
        $scope.item = {};
    };

    $scope.migrateAllItems = function() {
        $scope.migrateAllLegacyItems();
    };

    $scope.migrateItem = function() {
        $scope.createItemFromLegacyItem();

        $scope.error_message = "Migrated successfully. Please delete the old one manually if you like.";

//        $scope.deleteLegacyItem($scope.that);
    };

    $scope.createItemFromLegacyItem = function() {
        $scope.createItem(true);
    }

    $scope.createItem = function(legacy) {
        var temp = $scope.saveButtonLabel;
        $scope.saveButtonLabel = "Saving";
        $scope.item.search_results = angular.toJson($scope.serializeSearch());
        //alert('createItem  search_results [' + $scope.item.search_results + ']');
        if(!offline) {
            $scope.backendReady = false;
            var oid = getObjectId();
            var uid = getUsername();
            var fromLegacy = legacy?"&legacy=true":"";
            $scope.item.id = $scope.items.length + 1;
            var data = "id=" + $scope.item.id + "&" +
                "name=" + $scope.item.name + "&" +
                "title=" + $scope.item.title + "&" +
                "description=" + $scope.item.description + "&" +
                "url=" + $scope.item.url + "&" +
                "shared=" + $scope.item.shared + "&" +
                "search_results=" + $scope.item.search_results + "&" +
                "oid=" + oid + "&" +
                "type=" + $scope.backendObject + "&action=create&uid=" + uid + fromLegacy;
            //alert('about to create [' + data + ']');
            $http.post('/mcrud', data)
            .success(function(response, status, headers, config){
                //alert('created response [' + response.id + "]");
                //if(response.id === $scope.item.id) {
                if(response.id > 0) {
                    $scope.item.id = response.id;
                    $scope.item.owner = response.owner == $scope.userId?"MIGRATED":response.owner;
                    $scope.item.createDate = response.modified;
                    if($scope.pagedItems[$scope.currentPage] === undefined) {	//TBD
                        $scope.pagedItems[$scope.currentPage] = [];
                    }
                    $scope.pagedItems[$scope.currentPage].push($scope.item);
                    if($scope.items.length === 0) {	//TBD
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
                }
            })
            .error(function(response, status, headers, config){
                $scope.error_message = response.error_message;
            });
        } else {
            if($scope.pagedItems[$scope.currentPage] === undefined) {
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

    $scope.cancelSave = function() {
        $scope.error_message = "";
        $scope.enterNew = false;
        $scope.editing = false;
        $scope.item = {};
        $scope.backendReady = true;
    };

    $scope.viewItem = function(item) {
        $scope.error_message = "";

        $scope.item = { id: item.id, title: item.title, description: item.description, url: item.url, createDate: item.createDate};   //opt out data-binding
        window.console && console.log('viewItem  url [' + item.url + ']');
        //alert("viewItem url = [" + item.url + "]");
        function popitup(url) {
            var newwindow=window.open(url,'name','top=150px,left=200px,height=580px,width=1055px');
            if (window.focus) {newwindow.focus()}
            return false;
        };

        return popitup(item.url);
//        $('#element_to_pop_up').bPopup({
//            content:'iframe', //'ajax', 'iframe' or 'image'
//            contentContainer:'.content',
//            loadUrl:item.url //Uses jQuery.load()
//        });

    };

    $scope.editItem = function(item) {
        $scope.that = item;     //for the sake of deleteLegacyItem() :)
        $scope.error_message = "";

        $scope.item = { id: item.id, title: item.title, description: item.description, url: item.url, createDate: item.createDate, shared: item.shared};   //opt out data-binding
        $scope.originalItem = item;

        $scope.enterNew = false;
        $scope.editing = true;
        //$scope.item = item;   //no data-binding
/*
        var obj;
        if(item.search_results && item.search_results.value) {
            obj = angular.fromJson(
                item.search_results.value
            );
        }
        window.console && console.log('editItem  search_results [' + obj + '] obj[0].movie_thumbnail [' + obj[0].movie_thumbnail + ']');
        $scope.deserializeSearch(obj);
*/
    };

    $scope.updateItem = function() {
        var temp = $scope.updateButtonLabel;
        $scope.updateButtonLabel = "Updating";
        $scope.item.search_results = angular.toJson($scope.serializeSearch());
        if(!offline) {
            $scope.backendReady = false;
            //alert('updateItem  search_results [' + $scope.item.search_results + ']');
            var oid = getObjectId();
            var uid = getUsername();
            var data = "id=" + $scope.item.id + "&" +
                "name=" + $scope.item.name + "&" +
                "title=" + $scope.item.title + "&" +
                "description=" + $scope.item.description + "&" +
                "url=" + $scope.item.url + "&" +
                "shared=" + $scope.item.shared + "&" +
                "search_results=" + $scope.item.search_results + "&" +
                "oid=" + oid + "&" +
                "type=" + $scope.backendObject + "&action=update&uid=" + uid;
            //alert('about to update [' + data + ']');
            $http.post('/mcrud', data)
            .success(function(response, status, headers, config){
                $console.info('updated response id = ' + response.id + " $scope.item.id = " + $scope.item.id);
                if(response.id === $scope.item.id) {
                    $scope.originalItem.createDate = response.modified;
                    $scope.originalItem.owner = response.owner == $scope.userId?"MIGRATED":response.owner;
                    $scope.originalItem.title = $scope.item.title;   //copy the attributes you need to update
                    $scope.originalItem.description = $scope.item.description; //copy the attributes you need to update
                    $scope.originalItem.url = $scope.item.url; //copy the attributes you need to update
                    $scope.originalItem.search_results = $scope.item.search_results;

                    $scope.enterNew = false;
                    $scope.editing = false;
                    $scope.backendReady = true;
                    $scope.error_message = "";
                    //alert('updated');
                }
            })
            .error(function(response, status, headers, config){
                if(status == '403') {
                    $scope.error_message = "You do not have access to the resource. Are you the content owner?";
                    //alert("Error: " + $scope.error_message);
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

    $scope.deleteItem = function(item) {
        $scope.error_message = "";

        if (confirm("Delete the entry?")) {
            var temp = $scope.deleteLinkText;
            $scope.deleteLinkText = "Deleting";
            window.console && console.log("about to delete  [" + data + ']');
            if(!offline) {
                $scope.backendReady = false;
                var oid = getObjectId();
                var uid = getUsername();
                var data = "id=" + item.id + "&" +
                    "oid=" + oid + "&" +
                    "type=" + $scope.backendObject + "&action=delete&uid=" + uid;
                window.console && console.log("deleteItem: before POST data [" + data + "]");
                $http.post('/mcrud', data)
                .success(function(response, status, headers, config){
                        $console.info("deleteItem: checking response.id [" + response.id + "] with item.id [" + item.id + "]");
                        if(response.id === item.id) {
                        var index = $scope.pagedItems[$scope.currentPage].indexOf(item);
                        $scope.pagedItems[$scope.currentPage].splice(index,1);
                        //var index2 = $scope.items.indexOf(item);
                        //$scope.items.splice(index2,1);
                        $scope.backendReady = true;
                        $scope.error_message = "";
                        }
                })
                .error(function(response, status, headers, config){
                    if(status == '403') {
                        $scope.error_message = "You do not have access to the resource. Are you the content owner?";
                        //alert("Error: " + $scope.error_message);
                    }
                });
            } else {
                var index = $scope.pagedItems[$scope.currentPage].indexOf(item);
                $scope.pagedItems[$scope.currentPage].splice(index,1);
            }
            $scope.deleteLinkText = temp;
        }
    };

    //TODO delete method created specifically for legacy entity (not working!!!)
    $scope.deleteLegacyItem = function(item) {
        if (confirm("Confirm purging the legacy entry now (you don't actually need it anymore)?")) {
            var temp = $scope.deleteLinkText;
            $scope.deleteLinkText = "Deleting";
            window.console && console.log("about to delete  [" + data + ']');
            $scope.backendReady = false;
            var oid = getObjectId();
            //alert(oid);
            var uid = getUsername();
            var data = "id=" + item.id + "&" +
                "oid=" + oid + "&" +
                "type=" + $scope.backendObject + "&action=migrate_delete&uid=" + uid;
            window.console && console.log("deleteLegacyItem: before POST data [" + data + "]");
            $http.post('/mcrud', data)
                .success(function(response, status, headers, config){
                    $console.info("deleteLegacyItem: checking response.key_string [" + response.key_string + "] with item.id [" + item.id + "]");
                    if(response.key_string === item.id) {
                        var index = $scope.pagedItems[$scope.currentPage].indexOf(item);
                        $scope.pagedItems[$scope.currentPage].splice(index,1);
                        $scope.backendReady = true;
                        $scope.error_message = "";
                    }
                })
                .error(function(response, status, headers, config){
                    if(status == '403') {
                        $scope.error_message = "Migrated successfully but was not able to delete the legacy data. Please try to delete it manually.";
                        //alert("Error: " + $scope.error_message);
                    }
                });
            $scope.deleteLinkText = temp;
        } else {
            //not an error I know :(
            $scope.error_message = "Migrated successfully but purging of the legacy data was skipped.";
        }
    };

    $scope.migrateAllLegacyItems = function(item) {
        if (confirm("Confirm to migrate all the legacy entry now (action can not be undo)?")) {
            var temp = $scope.deleteLinkText;
            $scope.deleteLinkText = "Migrating";
            window.console && console.log("about to migrate all  [" + data + ']');
            $scope.backendReady = false;
            var oid = "dummy";   //getObjectId();
            //alert(oid);
            var uid = getUsername();
            var data = "id=-999" + "&" +
                "oid=" + oid + "&" +
                "type=" + $scope.backendObject + "&action=migrate&filter=migrate_all&uid=" + uid;
            window.console && console.log("migrateAllLegacyItems: before POST data [" + data + "]");
            $http.post('/mcrud', data)
                .success(function(response, status, headers, config){
                    $console.info("migrateAllLegacyItems: checking response");
                    if(response !== undefined) {
                        if(status === '200' && response.length === 0) {
                            $scope.error_message = "Congratulations! Migration was succesfull.";
                            $scope.items = [];
                        } else {
                            $scope.error_message = "We are sorry, something went wrong, migration was not succesfull.";
//                            $scope.loadItems();
                        }
                    }
                    $scope.backendReady = true;
                })
                .error(function(response, status, headers, config){
                    if(status == '403') {
                        $scope.error_message = "Migration failed. Are you the content owner?";
//                        $scope.loadItems();
//                        alert("Error: " + $scope.error_message);
                    }
                });
            $scope.deleteLinkText = temp;
        }
    };

    //=== calendar logic
    $scope.recurringButtonTitle = "One Time Event";
    $scope.cancelRecurringButtonTitle = "Close";
    $scope.calheader1 = "Do Not Repeat (one time event only)";
    $scope.calheader2 = "Repeat Daily (settings will be carried over to the subsequent year)";
    $scope.calheader3 = "Repeat Weekly (settings will be carried over to the subsequent year)";
    $scope.calheader4 = "Repeat Monthly (settings will be carried over to the subsequent year)";

    $scope.recurringItem = function() {
        $scope.repeatEvent = true;
    };

    $scope.updateRecurringItem = function() {
        $scope.repeatEvent = false;
        $scope.backendReady = true;
    };

    $scope.cancelRecurringItem = function() {
        $scope.repeatEvent = false;
        $scope.backendReady = true;
    };
}

ctrlRead.$inject = ['$scope', '$filter', '$http', '$rootScope', '$log', '$timeout'];


