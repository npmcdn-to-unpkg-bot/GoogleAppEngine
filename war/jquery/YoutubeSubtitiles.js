"use strict";

// ==UserScript==
// @name        YoutubeSubtitiles
// @namespace   com.manuskc.YoutubeSubtitiles
// @description Add subtitles to movies on youtube
// @include     *www.youtube.com/*
// @include     *youtube.com/*
// @version     1
// @grant       GM_info
// @grant       GM_xmlhttpRequest
// @require     http://code.jquery.com/jquery-latest.min.js
// ==/UserScript==
//var ytplayer;
//$(function () {   //jQuery document.ready()
typeof define !== 'undefined'?define(['require', 'exports', 'module'], function (require, exports, module) { //begin of AMD style

    function addJS_Node(text, s_URL, funcToRun, runOnLoad) {
        var D = document;
        var scriptNode = D.createElement('script');
        if (runOnLoad) {
            scriptNode.addEventListener("load", runOnLoad, false);
        }
        scriptNode.type = "text/javascript";
        if (text) scriptNode.textContent = text;
        if (s_URL) scriptNode.src = s_URL;
        if (funcToRun) scriptNode.textContent = '(' + funcToRun.toString() + ')()';

        var targ = D.getElementsByTagName('head')[0] || D.body || D.documentElement;
        targ.appendChild(scriptNode);
    }

    function hasYoutubePlayer() {
//            ytplayer = window.document.getElementById("embed#player1");

        if (typeof ytplayer !== 'undefined' && ytplayer !== null) {
            return true;
        }
        return false;
    }

    function getPlayTime() {
//            ytplayer = window.document.getElementById("embed#player1");

        var time = -1;
        var state;

        if (typeof ytplayer === 'undefined' || typeof ytplayer.getPlayerState() === 'undefined') return;

        try {
            state = ytplayer.getPlayerState();
            if (state == -1) {
                ytplayer.playVideo();
            }
            time = ytplayer.getCurrentTime();
        } catch(e) {
            console.log("YT player error: " + e + " Hint: Is Youtube player ready?");
        }

        return time;
    }

    //Playing str is a modified version of lib - jquery.srt.js : http://v2v.cc/~j/jquery.srt/jquery.srt.js

    function toSeconds(t) {
        if(typeof t === 'undefined') return 0;

        var s = 0.0
        if (t) {
            var p = t.split(':');
            try {
                for (var i = 0; i < p.length; i++)
                    s = s * 60 + parseFloat(p[i].replace(',', '.'))
            } catch (e) {
                alert("YoutubeSubtitiles.js error 3: " + e + " for text [" + t + "] length is " + t.length);
            }
        }
        return s;
    }

    function strip(s) {
        if(typeof s === 'undefined') return s;

        var temp = s;

        try {
            temp = s.replace(/^\s+|\s+$/g, "");
            if(typeof temp !== 'undefined') temp.replace(/\n\s+\n/g, "\n\n");
        } catch (e) {
            alert("YoutubeSubtitiles.js error 4: " + e + " for text [" + s + "] length is " + s.length);
        }

        return temp;
    }

    function playSubtitles(subtitleElement, subTitleText) {
        if(typeof subTitleText === 'undefined') {
            console.log("YoutubeSubtitiles.js subTitleText is undefined!?");
            return;
        }
        var srt = subTitleText;
        try {
            srt = srt.replace(/\r\n|\r|\n/g, '\n');
        } catch (e) {
            alert("YoutubeSubtitiles.js error 2: " + e + " for text [" + srt + "] length is " + srt.length);
        }

        var subtitles = {};
        srt = strip(srt);
        var srt_ = srt.split('\n\n');
        var objCount = 0;
        var s;
        var st, i, o, is, os, n, t;
        for (s in srt_) {
            st = srt_[s].split('\n');
            if (st.length >= 2) {
                n = st[0];
                i = strip(st[1].split(' --> ')[0]);
                o = strip(st[1].split(' --> ')[1]);
                t = st[2];
                if (st.length > 2) {
                    for (var j = 3; j < st.length; j++)
                        t += '\n' + st[j];
                }
                is = toSeconds(i);
                os = toSeconds(o);
                subtitles[objCount] = {
                    i: is,
                    o: os,
                    t: t
                };
                objCount++;
            }
        }

        var subTitleTextTimer = setInterval(function () {
            var showSub = false;

            var currentSub;
            var currentTime = getPlayTime();
            var delay = parseInt($("#srt_delay").val(), 10);
            if(isNaN(delay)) {
                delay = 0;
                $("#srt_delay").text("0");
            }
            var oLen = Object.keys(subtitles).length;
            for (var i=0; i<oLen; i++) {
                currentSub = subtitles[i];
                if(typeof currentSub !== 'undefined') {
                    if ((currentTime >= currentSub.i + delay) && (currentTime <= currentSub.o + delay)) {
                        showSub = true;
                        break;
                    }
                }
            }
            if (showSub) {
                subtitleElement.html(currentSub.t);
                console.log("YoutubeSubtitiles.js subTitleTextTimer() playing");
            }
            else {
                subtitleElement.html('');
                console.log("YoutubeSubtitiles.js subTitleTextTimer() cleared");
            }
            if(typeof currentSub !== 'undefined') {
                //=== Hint: uncomment the following for debugging!!!
                //console.log("currentSub.t = [" + currentSub.t + "]");
            }

//                if(previousTime > currentTime) {
//                    //it's a rewind
//                }
//                previousTime = currentTime;
        }, 300);
    }

    function startShowingSubtitles(subtitle_url) {
        $('.srt').each(function () {
                var subtitleElement = $(this);

//                if (!hasYoutubePlayer()) return;
                var srtUrl = subtitle_url;
                if (srtUrl) {
                    $("#str_load_info").hide();
                    $("#str_url_added").hide();
                    $("#srt_url_data").hide();
                    $("#srt_url_data").val("");     //IMPORTANT: do not do a server request again until I am done!!!
                    console.log("YoutubeSubtitiles.js calling service request ....................................................");
                    $.ajax({
                        type: "GET",
                        url: srtUrl
                        ,
//                        + '&Access-Control-Allow-Headers=1',
                        async: false,
                        crossDomain: true,
                        success: function (data) {
                            try {
                                $("#str_load_info").hide();
                                subtitleElement.text("");
                                playSubtitles(subtitleElement, data);
                                console.log("YoutubeSubtitiles.js service returned successfully.");
                            } catch(e) {
                                alert("YoutubeSubtitiles.js error 1 [" + e + "]");
                                window.console && console.log("YoutubeSubtitiles.js error 1 [" + e + "]");
                            }
                        },
                        error: function(error) {
                            alert("Not able to load subtitle! Error: " + error);
                        }
                    });
                } else {
                    return;
                }
            }
        );
    }

    exports.startSub = function() {    //AMD style export

        var checkStrUrlTimer = setInterval(function () {
            //console.log('-------------------------------> checkStrUrlTimer entered!');

            var checkbox = $("#str_url_added");

            if (typeof ytplayer === 'undefined' || typeof ytplayer.getPlayerState() === 'undefined') {
                //console.log('<------------------------------- checkStrUrlTimer to return: ytplayer is undefined (not ready?) ...');
                return;
            }

//            if (checkbox.is(':checked')) {
            var subtitle_url = $("#srt_url_data").val();
            $("#str_load_info").show();
            if (isValidSubtitle(subtitle_url)
                    //subtitle_url != null && subtitle_url.length > 10
                && ytplayer.getPlayerState() !== YT.PlayerState.PLAYING
            ) {
                $("#srt_url_data").hide();
                checkbox.hide();
                //$("#str_load_info").text("Loading subtitle ...");
                console.log("p");
            }
//            else {
//                $("#srt_url_data").show();
//                checkbox.show();
//                $("#str_load_info").hide();
//                $("#str_load_info").text("No Subtitle");
//            }
            ytplayer.playVideo();

            clearInterval(checkStrUrlTimer);
            startShowingSubtitles(subtitle_url);
//                }
//                else {
//                    alert("Please provide proper url to load subtitle for the video.");
//                    checkbox.prop('checked', false);
//                }
//            }
        }, 1000);

    };  //end of AMD style exports

}):"";    //end of jQuery document.ready() or AMD style define
