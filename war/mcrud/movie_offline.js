'use strict';
// App 0.0.1 mBuild 107
var myApp = angular.module('app', [], ctrlRead);
function ctrlRead($scope, $filter, $http) {
    var offline = true;  //DEV only
    //var offline = false;  //need to be uncommented for production
    $scope.backendReady = true;	//DEV only
    //$scope.backendReady = false;	//need to be uncommented for production
    $scope.error_message = "Application is in offline mode. Some functionalities will not be available. Please hit refresh if you are connected again.";
    //	alert('version 3');
    //=== BEGIN - CAN NOT SET GLOBALLY AS THE APP EXPECT DEFAULT (ANGULARJS-STYLE/JSON) WITH RESPONSE BUT SUBMIT ALA JQUERY-STYLE!!! ========================
    //$http.defaults.headers.post["Content-Type"] = "application/x-www-form-urlencoded";
    //$http.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded; charset=UTF-8';
    //=== END - CAN NOT SET GLOBALLY AS THE APP EXPECT DEFAULT (ANGULARJS-STYLE/JSON) WITH RESPONSE BUT SUBMIT ALA JQUERY-STYLE!!! ========================

    // app specific init
    //$scope.title = "Custom Channels Profile";
    $scope.createButtonTitle = "New Profile";
    $scope.header1 = "Action";
    $scope.header2 = "ID";
    $scope.header3 = "Name";
    $scope.header4 = "Description";
    // common init
    $scope.sortingOrder = sortingOrder;
    $scope.reverse = false;
    $scope.filteredItems = [];
    $scope.groupedItems = [];
    $scope.itemsPerPage = 5;
    $scope.pagedItems = [];
    $scope.currentPage = 0;
    $scope.items =
    [];
    //[{  "id": 1,  "name": "1",  "created_date": null,  "modified_date": null,  "last_accessed": null,  "owner": null,  "description": "1",  "summary": null,  "short_url": null,  "number": null,  "hit": -1,  "disabled": false},{  "id": 2,  "name": "name 2",  "created_date": null,  "modified_date": null,  "last_accessed": null,  "owner": null,  "description": "desc 2",  "summary": null,  "short_url": null,  "number": null,  "hit": -1,  "disabled": false},{  "id": 21,  "name": "1",  "created_date": null,  "modified_date": null,  "last_accessed": null,  "owner": null,  "description": "1",  "summary": null,  "short_url": null,  "number": null,  "hit": -1,  "disabled": false}];
    $scope.api = "";
    $scope.movie_thumbnail = "";

/*
    $scope.items = [
        {"id":"1","name":"name 1","description":"description 1","createDate":"","field4":"field4 1","field5 ":"field5 1"},
        {"id":"2","name":"name 2","description":"description 2","createDate":"field3 2","field4":"field4 2","field5 ":"field5 2"},
        {"id":"3","name":"name 3","description":"description 3","createDate":"field3 3","field4":"field4 3","field5 ":"field5 3"},
        {"id":"4","name":"name 4","description":"description 4","createDate":"field3 4","field4":"field4 4","field5 ":"field5 4"},
        {"id":"5","name":"name 5","description":"description 5","createDate":"field3 5","field4":"field4 5","field5 ":"field5 5"},
        {"id":"6","name":"name 6","description":"description 6","createDate":"field3 6","field4":"field4 6","field5 ":"field5 6"},
        {"id":"7","name":"name 7","description":"description 7","createDate":"field3 7","field4":"field4 7","field5 ":"field5 7"},
        {"id":"8","name":"name 8","description":"description 8","createDate":"field3 8","field4":"field4 8","field5 ":"field5 8"},
        {"id":"9","name":"name 9","description":"description 9","createDate":"field3 9","field4":"field4 9","field5 ":"field5 9"},
        {"id":"10","name":"name 10","description":"description 10","createDate":"field3 10","field4":"field4 10","field5 ":"field5 10"},
        {"id":"11","name":"name 11","description":"description 11","createDate":"field3 11","field4":"field4 11","field5 ":"field5 11"},
        {"id":"12","name":"name 12","description":"description 12","createDate":"field3 12","field4":"field4 12","field5 ":"field5 12"},
        {"id":"13","name":"name 13","description":"description 13","createDate":"field3 13","field4":"field4 13","field5 ":"field5 13"},
        {"id":"14","name":"name 14","description":"description 14","createDate":"field3 14","field4":"field4 14","field5 ":"field5 14"},
        {"id":"15","name":"name 15","description":"description 15","createDate":"field3 15","field4":"field4 15","field5 ":"field5 15"},
        {"id":"16","name":"name 16","description":"description 16","createDate":"field3 16","field4":"field4 16","field5 ":"field5 16"},
        {"id":"17","name":"name 17","description":"description 17","createDate":"field3 17","field4":"field4 17","field5 ":"field5 17"},
        {"id":"18","name":"name 18","description":"description 18","createDate":"field3 18","field4":"field4 18","field5 ":"field5 18"},
        {"id":"19","name":"name 19","description":"description 19","createDate":"field3 19","field4":"field4 19","field5 ":"field5 19"},
        {"id":"20","name":"name 20","description":"description 20","createDate":"field3 20","field4":"field4 20","field5 ":"field5 20"}
    ];
*/
    $scope.loadItems = function() {
    	$scope.items = [];	//clear everything and reload again :(
		console.log('about to get the list');
	    $http.get('/ws/crud?type=modelCalendar')
	    .success(function(data, status, headers, config){
	        var j;
			for (var i = 0; i < data.length; i++) {
				j = {id: data[i].id, name: data[i].name, description: data[i].description};
	            //console.log('data[' + i + '] = ' + j.id + " " + j.name + " " + j.description);
	            $scope.item = { id: j.id, name: j.name, description: j.description};   //opt out data-binding
	            $scope.items.push($scope.item);
	        }
            //$scope.items.push(angular.fromJson(data));

            $scope.backendReady = true;
		    $scope.search();	//for some unknown reason, it requires this invocation!? :(
	    })
	    .error(function(data, status, headers, config){
	        $scope.error_message = data.error_message;
	    });
    }
    if(!offline) {
	    $scope.loadItems();
    }

    var searchMatch = function (haystack, needle) {
        if (!needle) {
            return true;
        }
        return haystack.toLowerCase().indexOf(needle.toLowerCase()) !== -1;
    };

    // init the filtered items
    $scope.search = function () {
        //http://developer.rottentomatoes.com/docs/read/JSON
        //JSON_CALLBACK has to be spelled exactly in its case (AngularJS requires it like that :)!)
        var api_url = "http://api.rottentomatoes.com/api/public/v1.0/movies.json?apikey=tv2yr43592jd6pdxaahjym4b&page_limit=1&q="+$scope.query+"&callback=JSON_CALLBACK";
        console.log(api_url);
        // retrieve movie metadata
        $http.jsonp(api_url)
            .then( function ( response ) {
                if(response.data !== undefined && response.data !== undefined && response.data.movies[0] !== undefined) {
                    //$scope.data = response.data;
                    $scope.movie_thumbnail = response.data.movies[0].posters.thumbnail;
                    $scope.movie_title = response.data.movies[0].title;
                    if(response.data.movies[0].alternate_ids !== undefined) {
                        $scope.movie_url = "http://www.imdb.com/title/tt" + response.data.movies[0].alternate_ids.imdb;
                    }
                    console.log("thumbnail is [" + $scope.movie_thumbnail + "] " + $scope.movie_title + " " + $scope.movie_url);
                }
            });

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
        // now group by pages
        $scope.groupToPages();

    };

    // calculate page in place
    $scope.groupToPages = function () {
        $scope.pagedItems = [];

        for (var i = 0; i < $scope.filteredItems.length; i++) {
            if (i % $scope.itemsPerPage === 0) {
                $scope.pagedItems[Math.floor(i / $scope.itemsPerPage)] = [ $scope.filteredItems[i] ];
            } else {
                $scope.pagedItems[Math.floor(i / $scope.itemsPerPage)].push($scope.filteredItems[i]);
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
            $('th.'+new_sorting_order+' i').removeClass().addClass('icon-chevron-up');
        else
            $('th.'+new_sorting_order+' i').removeClass().addClass('icon-chevron-down');
    };

    $scope.newItem = function() {
        $scope.enterNew = true;
        $scope.editing = false;
        $scope.item = {};
    };

    $scope.createItem = function() {
        //alert('about to create [' + data + ']');
        if(!offline) {
            $scope.item.id = $scope.items.length + 1;
            var data = "id=" + $scope.item.id + "&" +
                "name=" + $scope.item.name + "&" +
                "description=" + $scope.item.description + "&" +
                "type=modelCalendar&action=create";
            $http.post('/mcrud', data)
            .success(function(response, status, headers, config){
                //alert('created response [' + response.id + "]");
                //if(response.id === $scope.item.id) {
                if(response.id > 0) {
                    $scope.item.id = response.id;
                    $scope.item.createdDate = new Date();
                    $scope.pagedItems[$scope.currentPage].push($scope.item);
                    if($scope.items.length === 0) {
                        $scope.items = [];
                    }
                    $scope.items.push($scope.item);
                    $scope.enterNew = false;
                    $scope.editing = false;
                    $scope.item = "";
                    //alert('created');
                } else {
                	$scope.error_message = response.error_message;
                }
            })
            .error(function(response, status, headers, config){
                $scope.error_message = response.error_message;
            });
        } else {
            if($scope.pagedItems.length === 0) {
                $scope.pagedItems[$scope.currentPage] = [];
            }
            $scope.item.id = $scope.pagedItems[$scope.currentPage].length + 1;
            $scope.item.createdDate = new Date();
            $scope.pagedItems[$scope.currentPage].push($scope.item);

            $scope.enterNew = false;
            $scope.editing = false;
            $scope.item = "";
        }

	    if(!offline) {
		    $scope.loadItems();
	    }
        $scope.groupToPages();
    };

    $scope.cancelSave = function() {
        $scope.enterNew = false;
        $scope.editing = false;
        $scope.item = {};
    };

    $scope.editItem = function(item) {
        $scope.item = { id: item.id, name: item.name, description: item.description};   //opt out data-binding
        $scope.originalItem = item;

        $scope.enterNew = false;
        $scope.editing = true;
        //$scope.item = item;   //no data-binding
    };

    $scope.updateItem = function() {
		var data = "id=" + $scope.item.id + "&" +
            "name=" + $scope.item.name + "&" +
            "description=" + $scope.item.description + "&" +
            "type=modelCalendar&action=update";
        //alert('about to update [' + data + ']');
        if(!offline) {
            $http.post('/mcrud', data)
            .success(function(response, status, headers, config){
                //alert('updated response ...');
                if(response.id === $scope.item.id) {
                    $scope.originalItem.createdDate = new Date();
                    $scope.originalItem.name = $scope.item.name;   //copy the attributes you need to update
                    $scope.originalItem.description = $scope.item.description; //copy the attributes you need to update
                    //clear temp items to remove any bindings from the input
                    $scope.enterNew = false;
                    $scope.editing = false;
                    //alert('updated');
                }
            })
            .error(function(response, status, headers, config){
                $scope.error_message = response.error_message;
            });
        } else {
            $scope.originalItem.createdDate = new Date();
            $scope.originalItem.name = $scope.item.name;   //copy the attributes you need to update
            $scope.originalItem.description = $scope.item.description; //copy the attributes you need to update
            //clear temp items to remove any bindings from the input
            $scope.enterNew = false;
            $scope.editing = false;
        }
        
	    if(!offline) {
		    //$scope.loadItems();
	    }
        //$scope.groupToPages();

    };

    $scope.deleteItem = function(item) {
		var data = "id=" + item.id + "&" +
            "type=modelCalendar&action=delete";
        //alert("about to delete  [" + data + ']');
        if(!offline) {
            $http.post('/mcrud', data)
            .success(function(response, status, headers, config){
                if(response.id === item.id) {
                    var index = $scope.pagedItems[$scope.currentPage].indexOf(item);
                    $scope.pagedItems[$scope.currentPage].splice(index,1);
                    //var index2 = $scope.items.indexOf(item);
                    //$scope.items.splice(index2,1);
                }
            })
            .error(function(response, status, headers, config){
                $scope.error_message = response.error_message;
            });
        } else {
            var index = $scope.pagedItems[$scope.currentPage].indexOf(item);
            $scope.pagedItems[$scope.currentPage].splice(index,1);
        }
        
	    if(!offline) {
		    $scope.loadItems();
	    }
        $scope.groupToPages();

    };

};

ctrlRead.$inject = ['$scope', '$filter', '$http'];

