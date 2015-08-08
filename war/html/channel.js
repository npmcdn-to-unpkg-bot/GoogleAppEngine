var GALLERIA_VERSION = '1.2.9';
//var GALLERIA_VERSION = '1.4.2';
var GALLERIA_LIMIT = 10;     //limit to 10 movies/images only
var galleria_type = 0;      //0 - video; 1 - image; 2 - RSS
//var galleriaData = [];
var cBuild = '0166';
var gDefaultPhoto = "../images/stock-photo-closeup-of-a-photographic-lens-106033496.jpg";
//var gYTPlayer;  //global youtube player
var ytplayer;

var movieArray = [];
var galleria;
var galleriaData = [];
//begin === play all and shuffle shares the following two counts, thus opening two separate windows for all and shuffle use case is not suppported! :(
var moviePreviewCount = 0;
var currentMoviePreviewCount = 1;
//end === play all and shuffle shares the following two counts, thus opening two separate windows for all and shuffle use case is not suppported! :(

$("#cBuild").val("(" + cBuild + ")");

/** load my scheduled movies */
function loadMovie(username) {
    var stat = false;
    //alert('channel.js loadMovie() entered');
    //window.console && console.log("**************>>>> cBuild = " + cBuild + "<<<<**************");

    $.ajax({
        type: "POST",
//        url: "/ws/crud?type=modelCalendar&uid=" + username + "&filter=next5",
        url: "/ws/crud?type=modelMovie&uid=" + username + "&filter=next5",
        async: false,
        success: function(data) {

            ////window.console && console.log("calendar event created, response = [" + data + "]");
            if(data !== undefined) {
                var obj = jQuery.parseJSON(data);
                //=== need to sort the obj first based on the datetime!!!
                //TODO - preview icon bug?

                var temp; var YOUTUBE_INDEX = 1;	//=== assumption: youtube is the second results!!!
                var currentDate;
                var tempDateStr; var tempMovieUrl;
                var dateStr;
                var liftoffTime;
                var title;
                var moviePreviewCount111 = 0;
                var lastComparedDate = new Date("February 24, 2970 03:24:00");  //wish I live that long to see it :)!
                $("#playingOrsoon").attr("data-datetime", "1970,1,1,0,0,0");
                $("#playingOrsoon").val("none");
                for (var i = 0; i < obj.length; i++) {
                    if (i === App.header_index) {
                        //=== parsing the metadata first
//                        $scope.page.server_number = data[i].pageNumber;
//                        $scope.page.server_max = data[i].maxPerPage;
//                        $scope.page.totalItem = data[i].totalItem;
//                        $scope.page.totalPage = data[i].totalPage;
//                        $scope.serviceCheck($scope.page);
                    } else {
                        if (obj[i].search_results && obj[i].search_results.value !== undefined) {
                            temp = jQuery.parseJSON(obj[i].search_results.value);
                        }
                        var desc;
                        if(typeof obj[i].description !== 'undefined')
                            desc = obj[i].description.value;
                        else
                            desc ="";
                        j = {id: obj[i].id, movie_url: temp[YOUTUBE_INDEX].movie_url, shuffled: obj[i].shuffled, datetime: obj[i].event_pattern, title: obj[i].title, description: desc, url: obj[i].u_r_l, createDate: obj[i].modified, event_id: obj[i].calendar_id, allDay: obj[i].calendar_all_day
                        };
    //                    alert(temp);
    //                    j = {id: obj[i].id, movie_url:temp, datetime:obj[i].event_pattern, title: obj[i].title, description: obj[i].description, url: temp, createDate: obj[i].created_date};
                        //window.console && console.log('1loadMovie: data[' + i + '] = id=' + j.id + " datetime=" + j.datetime + " title=" + j.title + " desc=" + j.description + " url=" + j.url + " createDate=" + j.createDate + " shuffled=" + j.shuffled);
                        try {
                            if (j.url !== undefined
    //                            && j.allDay !== true
                                ) {
                                currentDate = new Date();
                                tempDateStr = j.datetime ? j.datetime : "1970,1,1,0,0,0";
    //                            alert(tempDateStr);
                                if (tempDateStr && tempDateStr.indexOf(",") > -1) {
                                    //=== begin TBD this have to be placed in a common module/routine!!!
                                    dateStr = tempDateStr.split(",");
                                    liftoffTime = new Date(dateStr[0], dateStr[1], dateStr[2], dateStr[3], dateStr[4], dateStr[5]);
                                    //=== end TBD this have to be placed in a common module/routine!!!
                                    //alert("currentDate [" + currentDate + "] liftoffTime = [" + liftoffTime + "]");
                                    if (liftoffTime > currentDate) {
                                        if (liftoffTime < lastComparedDate) {
                                            $("#playingOrsoon").attr("data-datetime", j.datetime);
                                            $("#playingOrsoon").attr("data-eventid", j.event_id);
                                            $("#playingOrsoon").attr("data-movieid", j.id);
                                            //=== convert to youtube embed to avoid x-origin iframe issue (http://stackoverflow.com/questions/7168987/how-to-convert-a-youtube-video-url-to-the-iframe-embed-code-using-jquery-on-pag)
                                            tempMovieUrl = j.url;
                                            tempMovieUrl.replace(/(?:http:\/\/)?(?:www\.)?(?:youtube\.com|youtu\.be)\/(?:watch\?v=)?(.+)/g, 'https://www.youtube.com/embed/$1');
                                            $("#playingOrsoon").val(tempMovieUrl);
                                            lastComparedDate = liftoffTime;
                                        }
                                        movieArray.push({id: i, previewDate: j.datetime, url: j.url, description: j.decription, shuffled: j.shuffled});



                                        //alert(array);
                                        moviePreviewCount111++;
                                    }
                                }
                            }
    //                        else if (j.url !== undefined && j.allDay === true) {
    //                            movieArray.push({id: i, previewDate: j.datetime, url: j.url, description: j.decription, shuffled: j.shuffled});
    //                            //alert(array);
    //                            moviePreviewCount111++;
    //                        }
                        } catch (e) {
                            //alert('Oops, I am dead :( Please refresh me? :)');
                            console && console.log('channel.js loadMovie 1: An error has occurred: ' + e.message + ' - The application will not function correctly. Please contact the developer!');
                        }
                    }
                }
                try{
                    //=== sort the preview icons
                    movieArray.sort(function(a,b){
                        //=== convert to proper JavaScript Date object first
                        var dateStr;
                        var dateA, dateB;
                        if(a && a.previewDate.indexOf(",") > -1) {
                            dateStr = a.previewDate.split(",");
                            dateA = new Date ( dateStr[0], dateStr[1], dateStr[2], dateStr[3], dateStr[4], dateStr[5]).getTime();
                        }
                        if(b && b.previewDate.indexOf(",") > -1) {
                            dateStr = b.previewDate.split(",");
                            dateB = new Date ( dateStr[0], dateStr[1], dateStr[2], dateStr[3], dateStr[4], dateStr[5]).getTime();
                        }
                        //=== source: http://stackoverflow.com/questions/10123953/sort-javascript-object-array-by-date
                        return dateA > dateB ? 1 : -1;
                    });
                } catch(e) {
                    //window.console && console.log('channel.js loadMovie 2: An error has occurred: '+ e.message + ' - The application will not function correctly. Please contact the developer!');
                }
                //=== now insert as the preview icons
                for (var j = 0; j < movieArray.length; j++) {
                    $("#i" + j).val(movieArray[j].url);
                    $("#i" + j).attr("data-description", movieArray[j].description);
                    //window.console && console.log('saved array.url as ' + $("#i" + j).val());
                    if(movieArray[j].url.toLowerCase().indexOf("youtube.com") > -1) {
                        $("#v" + j).attr("href", movieArray[j].url);
                        //alert("v" + j + " " + liftoffTime + " = " + array[j].url);
                        //alert('3 movieArray[' + j + '].shuffled [' + movieArray[j].shuffled + ']');
                        if(movieArray[j].shuffled === true) {
                            //=== change its thumbnail to shuffling icon
                            $("#v" + j).find("span").replaceWith('<img src="/images/shuffle.png">');
                            //=== replace the original url now to make it appeared as random
                            $("#v" + j).attr("href", getNextShuffledUrl(movieArray[j].datetime));
                        }
                    } else
                    if(galleria_type === 1) {
                        $("#img" + j).attr("src", gDefaultPhoto);
                    } else
                    if(galleria_type === 2) {
                        $("#t" + j).attr("src", movieArray[j].url);
                        $("#t" + j).attr("data-description", movieArray[j].description);
                    }
                }


                //window.console && console.log("loading galleria ...");
                //=== http://support.galleria.io/kb/getting-started/quick-start
                //=== https://drupal.org/node/1927420
                Galleria.loadTheme('/galleria-' + GALLERIA_VERSION + '/themes/classic/galleria.classic.min.js?ts=' + (new Date()).getTime());

                Galleria.configure({
                    dummy: '/images/noimage.jpg',
                    imageCrop: true,
                    transition: 'fade'
                    , thumbnails: 'numbers'
                });
//                alert("running galleria type " + galleria_type);
                Galleria.run('#galleria' + galleria_type,{
                    dummy: '/images/noimage.jpg',
                    youtube: { enablejsapi: 1},
                    //,autoplay: 1000
                    showInfo: false,    /* turn off text 1 of 2 */
                    _toggleInfo: false  /* turn off text 2 of 2 */
                });
                Galleria.ready(function() {
                    this.attachKeyboard({
                        right: this.next,
                        left: this.prev
                    });
                    this.bind('thumbnail', function(e) {
                        e.thumbTarget.alt = 'My SEO optimized alt tag';
                    });
                });
                stat = true;
                $("#processStatus").hide();
                //window.console && console.log("1galleria loaded");
            }
        },
        error: function(jqXHR, error, errorThrown) {
            var msg = error;
            //alert && alert(msg);
            console && console.error(msg);
        }
    });

    return stat;
}

/** load all movies (mine, shared, sheduled or otherwise */
function loadMovieAll(username, log) {
    var stat = false;

    $.ajax({
        type: "POST",
        url: "/ws/crud?type=modelMovie&uid=" + username + "&filter=next5",
        async: false,
        success: function(data) {

            ////window.console && console.log("calendar event created, response = [" + data + "]");
            if(data !== undefined) {
                var obj = jQuery.parseJSON(data);
                var temp;
                var YOUTUBE_INDEX = 1;	//=== assumption: youtube is the second results!!!
                var tempMovieUrl;
                $("#playingOrsoon").attr("data-datetime", "1970,1,1,0,0,0");
                $("#playingOrsoon").val("none");
                var j;
                for (var i = 0; i < obj.length; i++) {
                    if (i === App.header_index) {
                        //=== parsing the metadata first
//                            $scope.page.server_number = data[i].pageNumber;
//                            $scope.page.server_max = data[i].maxPerPage;
//                            $scope.page.totalItem = data[i].totalItem;
//                            $scope.page.totalPage = data[i].totalPage;
//                            $scope.serviceCheck($scope.page);
                    } else {
                        if (obj[i].search_results && obj[i].search_results.value !== undefined) {
                            temp = jQuery.parseJSON(obj[i].search_results.value);
                        }
                        var desc;
                        if(typeof obj[i].description !== 'undefined')
                            desc = obj[i].description.value;
                        else
                            desc ="";
                        j = {id: obj[i].id, movie_url: temp[YOUTUBE_INDEX].movie_url, datetime: obj[i].event_pattern, title: obj[i].title, description: desc, url: obj[i].u_r_l, createDate: obj[i].modified};
                        //window.console && console.log('1loadMovieAll: data[' + i + '] = id=' + j.id + " title=" + j.title + " desc=" + j.description + " url=" + j.url + " createDate=" + j.createDate);
                        try {
                            if (j.url !== undefined) {
                                $("#playingOrsoon").attr("data-datetime", j.datetime);
                                //=== convert to youtube embed to avoid x-origin iframe issue (http://stackoverflow.com/questions/7168987/how-to-convert-a-youtube-video-url-to-the-iframe-embed-code-using-jquery-on-pag)
                                tempMovieUrl = j.url;
                                tempMovieUrl.replace(/(?:http:\/\/)?(?:www\.)?(?:youtube\.com|youtu\.be)\/(?:watch\?v=)?(.+)/g, 'https://www.youtube.com/embed/$1');
                                $("#playingOrsoon").val(tempMovieUrl);

                                $("#i" + moviePreviewCount).val(j.url);
                                $("#i" + moviePreviewCount).attr("data-description", j.description);
                                //window.console && console.log('saved j.url as ' + $("#i" + i).val());
                                if (j.url.toLowerCase().indexOf("youtube.com") > -1) {
                                    $("#v" + moviePreviewCount).attr("href", j.url);
    //                                $("#v" + moviePreviewCount).attr("span", j.description);
                                } else if (galleria_type === 1) {
                                    $("#img" + i).attr("src", gDefaultPhoto);
                                } else if (galleria_type === 2) {
                                    $("#t" + i).attr("src", j.url);
                                    $("#t" + i).attr("data-description", j.description);
                                }
                                moviePreviewCount++;
                            }
                        }
                        catch (e) {
                            //window.console && console.log('channel.js loadMovieAll: An error has occurred: ' + e.message + ' - The application will not function correctly. Please contact the developer!');
                        }
                    }
                }
                //window.console && console.log("1 loading galleria ...");
                //=== http://support.galleria.io/kb/getting-started/quick-start
                Galleria.loadTheme('/galleria-' + GALLERIA_VERSION + '/themes/classic/galleria.classic.min.js?ts=' + (new Date()).getTime());
                Galleria.configure({
                    //dummy: '/images/noimage.jpg',
                    //imageCrop: true,
                    transition: 'fade',
                    carousel: true
                    , thumbnails: 'numbers'
                    //, thumbnails: true
                });

                Galleria.run('#galleria' + galleria_type,{
                    dummy: '/images/noimage.jpg',
                    youtube: { enablejsapi: 1},
                    //,autoplay: 1000
                    showInfo: false,    /* turn off text 1 of 2 */
                    _toggleInfo: false  /* turn off text 2 of 2 */
                });
                Galleria.ready(function() {
                    galleria = this;
                    //var player;
                    this.attachKeyboard({
                        right: this.next,
                        left: this.prev
                    });
                    this.bind('thumbnail', function(e) {
                        e.thumbTarget.alt = 'My SEO optimized alt tag';
                    });
                    //=== http://support.galleria.io/discussions/questions/1502-stop-autoplay-on-any-kind-of-interaction
                    var stateChange = function(e) {
                        if ( e.data == 1 ) {
                            $('#galleria').data('galleria').pause();
                        }
                    };
                    this.bind('image', function(e) {
                        if(e.galleriaData.iframe) {
                            var temp = 'if'+new Date().getTime();
                            e.imageTarget.id = temp;
                            galleria.player = new YT.Player(temp, {
                                playerVars: { 'autoplay': 1, 'controls': 1 },
                                events: {
                                    'onReady': onPlayerReady,
                                    "onStateChange": onPlayerStateChange
                                }
                            });
                        } else {
                            iframe = false;
                        }

                        //=== http://support.galleria.io/discussions/questions/865-extending-galleria-external-play-buttoncaptions-etc
                        /* get galleria info "out of" galleria stage! */
                        data = e.galleriaData;
                        if(data) {
                            $('#title').html('<h2>' + data.title + '</h2><p>' + data.description + '</p>');
                        }

                    });

                    $("#processStatus").hide();

                });
                stat = true;
                //window.console && console.log("1galleria loaded");
            }
        },
        error: function(jqXHR, error, errorThrown) {
            var msg = error;
            //alert && alert(msg);
            console && console.error(msg);
        }
    });

    return stat;
}

//+ Jonas Raoni Soares Silva
//@ http://jsfromhell.com/array/shuffle [v1.0]
/** use only by Play Now as well as Play Later functionalities */
function shuffle(o){ //v1.0
    for(var j, x, i1 = o.length; i1; j = Math.floor(Math.random() * i1), x = o[--i1], o[i1] = o[j], o[j] = x);
    return o;
}

/** use by Play Later (scheduled play) in the Channel UI functionality */
function getNextShuffledUrl(startDatetime) {
    var randomUrl = "";

    $.ajax({
        type: "GET",
        url: "/ws/crud?type=modelMovie&uid=" + userid + "&filter=next5",
        //async: false,
        success: function(data) {
            if(data !== undefined) {
                var obj = jQuery.parseJSON(data);
                var temp; var YOUTUBE_INDEX = 1;	//=== assumption: youtube is the second results!!!
                obj = shuffle(obj);
                //var j;
                for (var mcount1 = 0; mcount1 < obj.length; mcount1++) {
                    temp = jQuery.parseJSON(obj[mcount1].search_results.value);
                    //j = {id: obj[mcount1].id, movie_url:temp[YOUTUBE_INDEX].movie_url, datetime:obj[mcount1].event_pattern, title: obj[mcount1].title, description: obj[mcount1].description, url: obj[mcount1].u_r_l, createDate: obj[mcount1].modified};
                    if(startDatetime === obj[mcount1].datetime && obj[mcount1].shuffled === true) {
                        randomUrl = temp[YOUTUBE_INDEX].movie_url;
                        break;
                    }
                }
            }
        },
        error: function(jqXHR, error, errorThrown) {
            var msg = error;
            //alert && alert(msg);
            console && console.error(msg);
        }
    });

    return randomUrl;   //TODO async needs to true in the near future!
}

function getSubTitle(text) {
    if(typeof text === 'undefined') return;
    var ret = '';

    var t = text.split(" ");
    var len = t.length;
    for(var i=0;i<len;i++) {
        if(t[i].lastIndexOf(".srt") > -1) {
            ret = t[i].substr(1, t[i].lenght);
            break;  //for now, only the first sub
        }
    }

    return ret;
}

/** load movies in a random order for playback */
function loadMovieShuffle(username, log) {
    //playNow();  //just a test

    var stat = false;
    galleriaData = [];
    //username = "pub";

    $.ajax({
        type: "POST",
        url: "/ws/crud?type=modelMovie&uid=" + username + "&filter=next5",
        async: false,
        success: function(data) {

            ////window.console && console.log("calendar event created, response = [" + data + "]");
            if(typeof data !== 'undefined') {
                var obj = jQuery.parseJSON(data);
                var YOUTUBE_INDEX = 1;	//=== assumption: youtube is the second results!!!
                var filterStr;
                $("#playingOrsoon").attr("data-datetime", "1970,1,1,0,0,0");
                $("#playingOrsoon").val("none");
                obj = shuffle(obj);     //thanks to http://stackoverflow.com/questions/6274339/how-can-i-shuffle-an-array-in-javascript
                filterStr = $.url().param('filter');     //support filter/hashtag
                var j;
                var temp = {};
                for (var mcount1 = 0; mcount1 < obj.length; mcount1++) {
                    if (mcount1 === App.header_index) {
                        //=== parsing the metadata first
//                            $scope.page.server_number = obj[i].pageNumber;
//                            $scope.page.server_max = obj[i].maxPerPage;
//                            $scope.page.totalItem = obj[i].totalItem;
//                            $scope.page.totalPage = obj[i].totalPage;
//                            $scope.serviceCheck($scope.page);
                    } else {
                        //TODO need to check for empty movie here!!!
                        try {
                            //if(obj[mcount1].search_results && typeof obj[mcount1].search_results.value !== 'undefined') {
                            //    temp = jQuery.parseJSON(obj[mcount1].search_results.value);
                            //}
                            var desc;
                            if(typeof obj[mcount1].description !== 'undefined')
                                desc = obj[mcount1].description.value;
                            else
                                desc = obj[mcount1].description;
                            j = {id: obj[mcount1].id,
                                //movie_url: temp[YOUTUBE_INDEX].movie_url,
                                datetime: obj[mcount1].event_pattern, title: obj[mcount1].title, description: desc, url: obj[mcount1].u_r_l, createDate: obj[mcount1].modified, subtitle: getSubTitle(desc)};
                            //alert('j.description = [' + j.description + "] filterStr = [" + filterStr + "]");
                            //window.console && console.log('2aloadMoviePub: data[' + mcount1 + '] = id=' + j.id + " title=" + j.title + " desc=" + j.description + " url=" + j.url + " createDate=" + j.createDate);
                            if (typeof j.url !== 'undefined') {
                                if (typeof filterStr !== 'undefined' && filterStr.trim() !== '') {
                                    if (j.description && j.description.indexOf(filterStr.trim()) > -1) {
                                        galleriaData = handleMovie(j, galleriaData);
                                        moviePreviewCount++;
                                    }
                                } else {
                                    galleriaData = handleMovie(j, galleriaData);
                                    moviePreviewCount++;
                                }
                            }
                        }
                        catch (e) {
                            //alert('Oops, I am dead :( Please refresh me? :)');
                            console && console.log('channel.js loadMoviePub 1: An error has occurred: ' + e.message + ' - The application will not function correctly. Please contact the developer!');
                        }
                    }
                }
                //alert('total movie = ' + moviePreviewCount);
                if(moviePreviewCount === 0) {
                    var msg = 'There is no movie in the collection.';
                    //alert && alert(msg);
                    console && console.error(msg);
                }

                //window.console && console.log("2 loading galleria ...");
                //=== http://support.galleria.io/kb/getting-started/quick-start
                Galleria.loadTheme('/galleria-' + GALLERIA_VERSION + '/themes/classic/galleria.classic.min.js?ts=' + (new Date()).getTime());
                Galleria.configure({
                    //dummy: '/images/noimage.jpg',
                    //imageCrop: true,
                    transition: 'fade'
                    , carousel: true
                    //http://galleria.io/docs/options/wait/
                    , wait: true
                    , thumbnails: 'numbers'
                    //, thumbnails: true
                    , lightbox: true
                    , trueFullscreen:true
                    , thumbQuality: 'auto'
                    //http://galleria.io/docs/options/youtube/
                    , youtube: { enablejsapi: 1, autoplay: 1 }
                });

//                Galleria.run('#galleria' + galleria_type, {
//                    dummy: '/images/noimage.jpg'
//                    //,autoplay: 1000
//                });
                Galleria.run('#galleria' + galleria_type, {
                    dataSource: galleriaData,
                    showInfo: false,    /* turn off text 1 of 2 */
                    _toggleInfo: false  /* turn off text 2 of 2 */
                });

                Galleria.ready(function() {
                    galleria = this;
                    //var player;
                    this.attachKeyboard({
                        right: this.next,
                        left: this.prev
                    });
                    this.bind('thumbnail', function(e) {
                        e.thumbTarget.alt = 'My SEO optimized alt tag';
                    });
                    //=== http://support.galleria.io/discussions/questions/1502-stop-autoplay-on-any-kind-of-interaction
                    var stateChange = function(e) {
                        if ( e.data == 1 ) {
                            $('#galleria').data('galleria').pause();
                        }
                    };
                    this.bind('image', function(e) {
                        if(e.galleriaData.iframe) {
                            var temp = 'if'+new Date().getTime();
                            e.imageTarget.id = temp;
                            galleria.player = new YT.Player(temp, {
                                playerVars: { 'autoplay': 1, 'controls': 1 },
                                events: {
                                    'onReady': onPlayerReady,
                                    "onStateChange": onPlayerStateChange
                                }
                            });
                        } else {
                            iframe = false;
                        }

                        //=== http://support.galleria.io/discussions/questions/865-extending-galleria-external-play-buttoncaptions-etc
                        /* get galleria info "out of" galleria stage! */
                        data = e.galleriaData;
                        if(data) {
                            $('#title').html('<h2>' + data.title + '</h2><p>' + data.description + '</p>');
                        }
                    });

//                this.bind("thumbnail", function(e) {
//                    Galleria.log(this); // the gallery scope
//                    Galleria.log(e) // the event object
//                });

                    $("#processStatus").hide();
                });
                stat = true;
                //window.console && console.log("before galleria event ... 3");
                Galleria.on('loadstart', function(e) {
                    var fSubtitle, trans;
                    //trans = .5;
                    var trans = 1.0;
                    var sText = e.galleriaData.subtitle;
                    if(isValidSubtitle(sText)) {
                        fSubtitle = '/go/' + sText;
                        //fSubtitle = '/static/sample.srt';  //just for test
                        var sub1 = require('subsTitle');
                        sub1.startSub();
                    } else {
                        $("#srt_url_data").val('');
                        fSubtitle = "";
                    }
                    //alert(fSubtitle);
                    //TODO http://css-tricks.com/forums/topic/is-it-possible-to-adapt-font-size-to-div-width/
                    $("#subtitleDiv").replaceWith("<div id=\"subtitleDiv\" style=\"top:50px;height:72px;font-size:24px;position: fixed;bottom: 10px;background: rgba(50,50,50,trans);width: 97%;z-index: 2000;color: rgb(220,220,220);text-align: center;\"><input type=\"text\" value=\"" + fSubtitle + "\" style=\"width:50%;height:70%;\" id=\"srt_url_data\" onfocus=\"if(this.value.length==0){this.value='Enter subtitle here (would not work in fullscreen mode for now).';}\"> <a class=\"btn-reverse\" href=\"#\" onclick=\"hideSubtitle();\" id=\"str_url_added\" style=\"margin-bottom:1px;\" name=\"str_url_added\"><i style=\"vertical-align: baseline;\" class=\"icon-remove-sign\"></i></a><label style=\"padding-left:10px;\" id=\"str_load_info\"></label><div class=\"srt\"></div><input alt=\"Delay of subtitles in seconds, can be postive or negative\" type=\"text\" style=\"width:2.5em;position:absolute;left:89%;bottom:9px;\" placeholder=\"<![CDATA[Delay/s]]>\" id=\"srt_delay\"/></div>");
                    $("#srt_url_data").val(fSubtitle);
                });
                //window.console && console.log("2.1.0 galleria loaded");
            }
        },
        error: function(jqXHR, error, errorThrown) {
            var msg = error.mesage;
            //alert && alert(msg);
            console && console.error(msg);
        }
    });

    return stat;
}

function isValidSubtitle(sub) {
    var ret = false;

    if(typeof sub !== 'undefined' && sub.lastIndexOf(".srt") > -1) {
        ret = true;
    }

    return ret;
}

function hideSubtitle() {
    $("#subtitleDiv").hide();
    $('#srt_delay').hide();
}

function handleMovie(j, galleriaData) {
    $("#playingOrsoon").attr("data-datetime", j.datetime);
    //=== convert to youtube embed to avoid x-origin iframe issue (http://stackoverflow.com/questions/7168987/how-to-convert-a-youtube-video-url-to-the-iframe-embed-code-using-jquery-on-pag)
    var tempMovieUrl = j.url;
    tempMovieUrl.replace(/(?:http:\/\/)?(?:www\.)?(?:youtube\.com|youtu\.be)\/(?:watch\?v=)?(.+)/g, 'https://www.youtube.com/embed/$1');
    $("#playingOrsoon").val(tempMovieUrl);
//    $("#i" + moviePreviewCount).val(tempMovieUrl);
//    $("#i" + moviePreviewCount).attr("data-description", j.description);
    //window.console && console.log('saved j.url as [' + tempMovieUrl + ']');

    //=== c.f. http://galleria.io/docs/references/data/#adding-a-layer-above-the-content
    if(galleria_type === 0) {
        /* VIDEO */
        if(j && j.url && tempMovieUrl.toLowerCase().indexOf("youtube.com") > -1) {  //IE does not have toLowerCase???
            //===handle the subtitle
            galleriaData.push({
                dummy: '/images/noimage.jpg',
                video: tempMovieUrl,
                title: j.title,
                description: j.description,
                subtitle: j.subtitle
                //link: 'http://my.destination.com'
            });
        }
    } else
    if(galleria_type === 1) {
        /* IMAGE */
        $("#img" + moviePreviewCount).attr("src", gDefaultPhoto);
//        galleriaData.push({
//            thumb: 'thumb.jpg',
//            image: 'image.jpg',
//            big: 'big.jpg',
//            title: 'My title',
//            description: 'My description',
//            link: 'http://my.destination.com',
//            layer: '<div><h2>This image is gr8</h2><p>And this text will be on top of the image</p>'
//        });
    } else
    if(galleria_type === 2) {
        /* TEXT */
        $("#t" + moviePreviewCount).attr("src", j.url);
        $("#t" + moviePreviewCount).attr("data-description", j.description);
    }

    return galleriaData;
}

function onPlayerReady(event) {
    //alert('1.3');
    if(!isMobileBrowser()) {
        setPlayerForAll(event);
        event.target.playVideo();
    } else {
        $("#srt_delay").hide();
//        setTimeout(playNow, 3000);  //c.f. "Mobile Considerations Autoplay and Scripted Playback" https://developers.google.com/youtube/iframe_api_reference
//        alert("Please click play to continue.");
    }

    //window.console && console.log("channel:onPlayerReady .");
}

function isMobileBrowser() {
    var retVal;
    var isAndroid = navigator.userAgent.toLowerCase().indexOf("android");
    if(isAndroid > -1) {
        retVal = true;
    } else {
        var isiPhone = navigator.userAgent.toLowerCase().indexOf("iphone");
        var isiPad = navigator.userAgent.toLowerCase().indexOf("ipad");
        var isiPod = navigator.userAgent.toLowerCase().indexOf("ipod");
        if(isiPhone > -1 || isiPad > -1 || isiPod > -1) {
            retVal = true;
        }
    }
    return retVal;
}

function playNow() {
    var mobilePlaybackMsg = 'YouTube requires user initiated playback on a mobile device, please click the Play icon to continue.';
    //alert(mobilePlaybackMsg);
    $("#mobilePlaybackMsg").css('color', 'red');
    $("#mobilePlaybackMsg").css('white-space', 'nowrap');
    $("#mobilePlaybackMsg").html(mobilePlaybackMsg);
    //galleria.player.playVideo();
}

function setPlayerForAll(event) {
    if(typeof event.target !== 'undefined' && typeof ytplayer === 'undefined' || typeof ytplayer !== 'undefined' && typeof ytplayer.data === 'undefined') {
        ytplayer = event.target;    //=== initialize youtube player for subtitle component
        //alert("channel.js YT Player initialized [" + ytplayer + "]!");
    }
}

/** https://developers.google.com/youtube/js_api_reference#playVideo */
function onPlayerStateChange(event) {
    ////window.console && console.log("channel:onPlayerStateChange .");
    //alert('channel.js onPlayerStateChange() entered!');

    setPlayerForAll(event); //takes care of mobile devices too, by checking it here itself

    assert && assert(typeof ytplayer !== 'undefined' && typeof ytplayer.data === 'undefined', 'channel.js: YouTube player instance is undefined for some reason!');

//    if (event.data == YT.PlayerState.PLAYING) {
//        //handle full window - restore it if it was full before
//        $('ytplayer').attr('width', $(window).width());
//        $('ytplayer').attr('height', $(window).height());
//        //TODO - the following can be used to create a "Preview" mode
//        //setTimeout(stopVideo, 6000);
//        //done = true;
//    } else
    if (event.data == YT.PlayerState.CUED) {
        //window.console && console.log("channel:onPlayerStateChange:YT.PlayerState.CUED playing ...");
        event.target.playVideo();
        //window.console && console.log("channel:onPlayerStateChange currentMoviePreviewCount [" + currentMoviePreviewCount + "] of moviePreviewCount [" + moviePreviewCount + "]");
    } else
    if (event.data == YT.PlayerState.ENDED) {
        currentMoviePreviewCount++;
        //window.console && console.log("channel:onPlayerStateChange currentMoviePreviewCount = " + currentMoviePreviewCount);

        //window.console && console.log("channel:onPlayerStateChange:YT.PlayerState.ENDED checking if there is a need to play the next video [" + currentMoviePreviewCount + "] of moviePreviewCount [" + moviePreviewCount + " ...");
        //===end the Play Now if it is the last movie
        if(currentMoviePreviewCount > moviePreviewCount) {
            history.go(-1);
        }
        //window.console && console.log("channel:onPlayerStateChange:YT.PlayerState.ENDED laoding next video ...");
        //http://galleria.io/docs/api/methods/
        galleria.next();
        //galleria.player.playVideo();
        galleria.toggleFullscreen();

    }
}

//if (Galleria) { $("body").text('Galleria works') }
/**
 *
 * *** THIS IS DEPRECATED and currently used only by non-webkit browsers like IE! ***
 *
 * */
function handleChannelType(type, username) {
    //window.console && console.log('handleChannelType entered');
    var data = [];
    galleria_type = type;

    for (var i = 0; i < GALLERIA_LIMIT; i++) {
        //window.console && console.log('retrieved j.url as ' + $("#i" + i).val());

        try{
            //videos (currently youtube only)
            if(type === 0) {
                var finalUrl = $("#i" + i).val();
                $("#v" + i).attr("href", finalUrl);
                //$("#v" + i).attr("alt", "Description " + i);
            } else
            //images
            if(type === 1) {
                if($("#i" + i).val().toLowerCase().indexOf(".png") > -1 ||
                    $("#i" + i).val().toLowerCase().indexOf(".jpg") > -1 ||
                    $("#i" + i).val().toLowerCase().indexOf(".gif") > -1
                    ) {
                    $("#img" + i).attr("src", $("#i" + i).val());
                    //$("#v" + i).attr("alt", "Description " + i);
                } else {
                    $("#img" + i).attr("src", gDefaultPhoto);
                }
            } else
            //rss feeds
            if(type === 2) {
                //window.console && console.log("2handleChannelType: $('#t' + i).val()=" + $('#t' + i).val() + " fetching rss feed ...");
                if($("#t" + i).val().toLowerCase().indexOf("youtube") == -1) {
                    try {
                        $.ajax({
                            type: "POST",
                            url: "/ws/crud?type=modelMovie&uid=" + username,
                            //async: false,
                            success: function(data) {

                                ////window.console && console.log("rss event created, response = [" + data + "]");
                                if(data !== undefined) {
                                    var obj = jQuery.parseJSON(data);
                                    var j = {};
                                    for (var i = 0; i < obj.length; i++) {
                                        //for (var i = 0; i < GALLERIA_LIMIT; i++) {
                                        j = {id: obj[i].id, title: obj[i].title, description: obj[i].description.value, url: obj[i].u_r_l, createDate: obj[i].modified, rss: '/html/feed.html?url=' + j.url, enclosure: obj[i].item.enclosure};
                                        //window.console && console.log('3handleChannelType: data[' + i + '] = id=' + j.id + " title=" + j.title + " desc=" + j.description + " url=" + j.url + " createDate=" + j.createDate);
                                        //window.console && console.log('3.1handleChannelType: data[' + i + '] = rss=' + j.rss);
                                        //window.console && console.log('3.1handleChannelType: enclosure[' + j.enclosure + ']');
                                        $("#t" + i).attr("href", j.rss);
//                                        $("#ti" + i).attr("src", 'http://www.fvicon.com/'+$("#t" + i).attr("href"));
                                    }
                                }
                            },
                            error: function(jqXHR, error, errorThrown) {
                                var msg = error;
                                //alert && alert(msg);
                                console && console.error(msg);
                            }
                        });

                    } catch(e) {
                        //window.console && console.log("rss parsing error: " + e + " url was [" + $("#i" + i).val() + "]");
                    }
                }
//                else {
//                    $("#t" + i).attr("href", j.rss);
//                }
            }
        }
        catch(e){
            //window.console && console.log('channel.js handleChannelType: An error has occurred: '+ e.message + ' - The application will not function correctly. Please contact the developer!');
        }
    }
    //=== http://support.galleria.io/kb/getting-started/quick-start
    Galleria.loadTheme('/galleria-' + GALLERIA_VERSION + '/themes/classic/galleria.classic.min.js?ts=' + (new Date()).getTime());
    console.log("type is [" + type + "]");
    if(type === 0) {
        /* VIDEO */
    	//alert("type 0");
        Galleria.run('#galleria0', {
            dummy: '/images/noimage.jpg',
            youtube: { enablejsapi: 0, autoplay: 0 },
            autoplay: 1000
        });
        Galleria.ready(function() {
            var player;
            var stateChange = function(e) {
                if ( e.data == 1 ) {
                    $('#galleria0').data('galleria').pause();
                }
            };
            this.bind('image', function(e) {
                if(e.galleriaData.iframe) {
                    var temp = 'if'+new Date().getTime();
                    e.imageTarget.id = temp;
                    player = new YT.Player(temp, {
                        events: {
                            "onStateChange": stateChange
                        }
                    });
                } else {
                    iframe = false;
                }
            });
            this.bind('thumbnail', function(e) {
                e.thumbTarget.alt = 'My SEO optimized alt tag';
            });
        });

        (function(){
            var s = document.createElement("script");
            s.src = "https://www.youtube.com/player_api"; /* Load Player API*/
            var before = document.getElementsByTagName("script")[0];
            before.parentNode.insertBefore(s, before);
        })();
    } else
    if(type === 1) {
        /* IMAGE */
    	//alert("type 1");
        Galleria.run('#galleria' + type, {
            dummy: '/images/noimage.jpg',
            transition: 'fade',
            imageCrop: true,
            autoplay: 7000
        });
    } else {
        /* TEXT */
    	//alert("type 2");
        Galleria.run('#galleria' + type, {
            dummy: '/images/noimage.jpg',
            transition: 'fade',
            imageCrop: true
            //,
            //autoplay: 7000
        });
    }
    //window.console && console.log("3galleria loaded");
}

/** Currently not used */
function loadComingMovie() {
    var stat = false;
    //window.console && console.log("**************>>>> cBuild = " + cBuild + "<<<<**************");

    //var array = movieArray;        //previously populated by loadMovie()

    for (var j = 0; j < array.length; j++) {
        $("#i" + j).val(array[j].url);
        $("#i" + j).attr("data-description", array[j].description);
        //window.console && console.log('saved array.url as ' + $("#i" + j).val());
        if(array[j].url.toLowerCase().indexOf("youtube.com") > -1) {
            $("#v" + j).attr("href", array[j].url);
            //alert("v" + j + " " + liftoffTime + " = " + array[j].url);
        } else
        if(galleria_type === 1) {
            $("#img" + j).attr("src", gDefaultPhoto);
        } else
        if(galleria_type === 2) {
            $("#t" + j).attr("src", array[j].url);
            $("#t" + j).attr("data-description", array[j].description);
        }
    }

    return stat;
}

typeof module !== 'undefined'?module.exports.getSubTitle = getSubTitle:"";
typeof module !== 'undefined'?module.exports.loadMovieShuffle = loadMovieShuffle:"";
typeof module !== 'undefined'?module.exports.loadMovieAll = loadMovieAll:"";
typeof module !== 'undefined'?module.exports.loadMovie = loadMovie:"";
