"use strict";

var username;

//TODO this "getUsername" routine needs to be in somewhere commonly accessible place!
//if(App.isValidSession()) {
//    username = Parse.User.current().getUsername();
//} else {
//    alert(App.session_expired_msg + "We are sorry that you have to be redirected to the login page.");
//    //alert(App.session_expired_msg + " uid [" + Parse.User.current().getUsername() + "]");
//    location.href = App.login_url;
//}
//alert("calendar.js username [" + username + "]");

gHref = "/api/jwt/ws/crud?type=modelCalendar&uid=" + username;

$.ajaxSetup({headers: { 'Authorization': 'Bearer ' + localStorage.getItem('2shareJWTToken') }}); //JWT support

function initCalendar() {
    var date = new Date();
    var d = date.getDate();
    var m = date.getMonth();
    var y = date.getFullYear();

    /* initialize the external events
     -----------------------------------------------------------------*/
    $('.external-event').each(function () {

        // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
        // it doesn't need to have a start or end
        var eventObject = {
            title: $.trim($(this).text()) // use the element's text as the event title
        };

// store the Event Object in the DOM element so we can get to it later
        $(this).data('eventObject', eventObject);

// make the event draggable using jQuery UI
        $(this).draggable({
            zIndex: 999,
            revert: true,      // will cause the event to go back to its
            revertDuration: 0  //  original position after the drag
        });

    });

    //source: https://code.google.com/p/fullcalendar/issues/detail?id=293
    function init(slotMinutes) { // slotMinutes is an optional parameter
        return $('#calendar').fullCalendar({
            //c.f. http://arshaw.com/fullcalendar/docs/event_rendering/eventRender/
            //TODO somehow this piece of codes (eventRender to add img tag) is causing the event row to be "shortened" by 10% from the right! Not sure why ...
            eventRender: function (event, element) {
                try {
                    var inputMovieid = $('input[name=movieid]').val();
                    //window.console && console.log("event.thumbUrl [" + event.thumbUrl + "] event.movieid [" + event.movieid + "] input.movieid [" + inputMovieid + "]");
                    ////window.console && console.log(event);
                    ////window.console && console.log(element);
                    if (event.thumbUrl
                        && event.movieid === inputMovieid
                        ) {
//                        $(".fc-event-bg").after("<img style='padding-top:-10px;width:80px;height:auto;' src='" + event.thumbUrl + "'>");
                        element.find(".fc-event-title").after($("<span class=\"fc-event-icons\"></span>").html("<img style='display:inline-block;padding-top:-10px;width:80px;height:auto;' src=\"" + event.thumbUrl + "\" />"));
                    }
                    //put a small gap in between shuffled events http://jsfiddle.net/domi27/bQXYp/1/)
//                    var nextEventLeft = element.offset().left + element.width() + 5;
//                    element.parent().children().eq(element.index()+1).css('left',nextEventLeft);
                } catch (e) {
                    alert('calendar.js: init() not able to render event, error: ' + e);
                }
            },
            header: {
                left: 'prev,next today',
                center: 'title',
                right: 'month,agendaWeek,agendaDay'
            },
            titleFormat: {
                month: 'MMMM yyyy',                             // September 2009
                week: "MMM d[ yyyy]{ '&#8212;'[ MMM] d yyyy}", // Sep 7 - 13 2009
                day: 'MMM d, yyyy'                  // Tuesday, Sep 8, 2009
            },
            columnFormat: {
                month: 'ddd',    // Mon
                week: 'ddd M/d', // Mon 9/7
                day: 'dddd'  // Monday 9/7
            },
            selectable: true,
            selectHelper: true,
            editable: true,
            defaultView: 'agendaDay',
//        maxTime: '23:59',
            allDaySlot: false,
            slotMinutes: slotMinutes,
            firstHour: new Date().getHours(),
            eventDurationEditable: false,
            slotEventOverlap: false,
            events: gHref, //"/api/jwt/ws/crud?type=modelCalendar&uid=" + username,
            droppable: true, // this allows things to be dropped onto the calendar !!!
            drop: function (date, allDay) { // this function is called when something is dropped
                if (isOverlapped(date) === true) {
                    alert('Oops, but there is already one item scheduled at ' + date + '. Please try other date and time or remove the existing item.');
                    return;
                }

                //window.console && console.log("new event dropped !");
                //window.console && console.log(date);
                //window.console && console.log('allDay [' + allDay + ']');
                createNewEvent(this, date, allDay);
            },
            eventResize: function (event, jsEvent, ui, view) {
                //window.console && console.log("resized !");
                saveExistingEvent(event);
            },
            eventDrop: function (event, dayDelta, minuteDelta, allDay, revertFunc, jsEvent, ui, view) {
                ////window.console && console.log("eventDropped: dayDelta [" + dayDelta + "]");
                if (isOverlapped(event.start) === true) {
                    alert('Oops, but there is already one item scheduled at ' + date + '. Please try other date and time or remove the existing item.');
                    return true;    //TODO this might be the reason why update fails to prevent overlapped event!!??
                }
                saveExistingEvent(event);
            },
            eventMouseover: function (event, jsEvent, view) {
                //window.console && console.log("calendar.js: hovered! 1");
            },
            eventClick: function (event, jsEvent, view) {
                //TODO - to handle doubleclick c.f. http://stackoverflow.com/questions/8124460/handle-dblclick-in-fullcalendar-jquery-plugin

                //TODO to disable the event click properly instead of by checking the title!!!
                if (event.title.indexOf('(just a place holder)') > -1) {
                    return false;
                }
                //window.console && console.log("eventClick: movieid [" + event.movieid + "]");
                if (confirm("Really delete event " + event.title + " [id=" + event.id + "] ?")) {
                    //if (confirm("Really delete event " + event.title + " [id=" + event.id + " movieid=" + event.movieid + "] ?")) {
                    showInProgress();

                    //delete event in backend first
                    $('input[name=id]').val(event.id);
                    $('input[name=title]').val('');
                    var s = new Date(event.start);
                    var startDateString = s.format("mm/dd/yyyy HH:MM");
                    $('input[name=start]').val(startDateString);
                    var e = new Date(event.end);
                    var endDateString = e.format("mm/dd/yyyy HH:MM");
                    $('input[name=end]').val(endDateString);
//            $('input[name=url]').val('');
                    $('input[name=movieid]').val(event.movieid);
                    $('input[name=allDay]').val('');
                    //=== final fix for not found issue
                    $('input[name=type]').val('modelCalendar');
                    $('input[name=action]').val('delete');
                    $('input[name=repeat]').val(frequency);
                    $('input[name=uid]').val(username);

                    var frequency = $('input[name=repeated]:checked').val();
                    //window.console && console.log("deleting calendar event id '" + event.id + "' repeated '" + frequency + "'");
                    $.ajax({
                        type: "POST",
                        url: "/api/jwt/ws/crud",  // read the action attribute of the form
                        data: $('#calendarForm').serialize(),
                        async: false,
                        success: function (data) {
                            //window.console && console.log("calendar event id deleted returned by server = '" + data + "'");
                            //alert("calendar event id returned by server = '" + obj.key.id + "' event.id '" + event.id + "'");
                            if (data > 0) {
                                try {
                                    //finally delete in frontend
                                    gCalendar.fullCalendar('removeEvents', event.id);
                                    //window.console && console.log("calendar id : " + event.id + " removed");
                                } catch(e) {
                                    //window.console && console.log("calendar.js eventClick() error [" + e + "]");
                                }
                            }
                            //refresh the left panels
                            refreshEventsUI();
                        }

                    });
                    refreshFullCalendar(true);
                }
            }


        });
    }

    /** This is to refresh AngularJS based UI */
    function refreshEventsUI() {
        //refresh the left panels
        var el = $("[ng-controller='MovieController']");
        el.scope().loadItemsForCalendar();
        el.scope().$apply();
//        el.scope().loadItemsForCalendar(1);
//        el.scope().$apply();
//        el.scope().loadItemsForCalendar(2);
//        el.scope().$apply();
    }

    function changeSlotMinutes(v) {
        $('#calendar').fullCalendar('destroy');
        gCalendar = init(v);
    }

    /* initialize the calendar
     -----------------------------------------------------------------*/
    gCalendar = init(30);    //default to 30 minutes slot

    //https://github.com/arshaw/fullcalendar/pull/17
    $("#movieMode").click(function () {
        //alert('movie mode!');
        changeSlotMinutes(30);
    });
    $("#musicMode").click(function () {
        //alert('music mode!');
        changeSlotMinutes(1);
    });

}

function showInProgress() {
    $("#calendar-loader").css("display", "table-cell");
    $("body").css("cursor", "progress");
}

function showDone() {
    $("#calendar-loader").hide();
    $("body").css("cursor", "default");
}

//c.f. http://jsfiddle.net/briguy37/2MVFd/
function generateUUID() {
    var d = new Date().getTime();
    var uuid = 'xxxxxxxx-xxxx-4xxx-yxxx-xxxxxxxxxxxx'.replace(/[xy]/g, function(c) {
        var r = (d + Math.random()*16)%16 | 0;
        d = Math.floor(d/16);
        return (c ==='x' ? r : (r&0x7|0x8)).toString(16);
    });
    return uuid;
}

function saveNewEvent(event) {
    var stat = false;

    var s = new Date(event.start);
    var startDateString = s.format("mm/dd/yyyy HH:MM");

    var e = new Date(event.end);
    var endDateString = e.format("mm/dd/yyyy HH:MM");
    //alert('endDateString = ' + endDateString);
    //alert('calendar.js event.title [' + event.title + ']');
    $('input[name=title]').val(event.title);
    $('input[name=start]').val(startDateString);
    $('input[name=end]').val(endDateString);
    $('input[name=movieUrl]').val(event.movieUrl);
    $('input[name=movieid]').val(event.movieid);
    //alert('event.movieid = ' + event.movieid);
    $('input[name=allDay]').val(event.allDay);
    $('input[name=color]').val(event.color);
    //=== final fix for not found issue
    $('input[name=type]').val('modelCalendar');
    $('input[name=action]').val('create');
    $('input[name=repeat]').val(frequency);
    $('input[name=uid]').val(username);
    $('input[name=shuffled]').val(event.shuffled);

    //window.console && console.log("in saveNewEvent calendar.js $('input[name=title]'): " + $('input[name=title]').val());
    //window.console && console.log("in saveNewEvent calendar.js $('input[name=start]'): " + $('input[name=start]').val());
    //window.console && console.log("in saveNewEvent calendar.js $('input[name=end]'): " + $('input[name=end]').val());
    var frequency = $('input[name=repeated]:checked').val();
    //window.console && console.log("creating new calendar event id '" + event.id + "' repeated '" + frequency + "'");
    //create "remember" closure
    (function() {
        var lastMovieDroppedTitle = $('input[name=title]').val();
        var intendedDateTime = $('input[name=start]').val();
        //alert("dropped!");
        //submit to backend first
        $.ajax({
            type: "POST",
            url: "/api/jwt/ws/crud",  // read the action attribute of the form
            data: $('#calendarForm').serialize(),  // what data should go there?
            //http://stackoverflow.com/questions/5316697/jquery-return-data-after-ajax-call-success
            async: false,
//            dataType: "jsonp", // necessary for IE9
            crossDomain: true,
            success: function (data) {
                // wohoo, this works!
                ////window.console && console.log("calendar event created, response = [" + data + "]");
                var obj = jQuery.parseJSON(data);
//                if(obj === undefined || obj.key === undefined) {
//                    alert("calendar.js Server replied does not contain a valid obj.key. Event creation failed!");
//                    return;
//                }
                try {
                    //=== always used id provided by the server
                    if (obj && obj.key && obj.key.id) {
                        //window.console && console.log("1 calendar event id returned by server = '" + obj.key.id + "'");
                        event.id = obj.key.id;
                        //window.console && console.log("in saveNewEvent calendar.js User ID: " + username + " event.id: " + event.id);
                        stat = true;
                    } else {
                        alert("calendar.js saveNewEvent() for User ID [" + username + "] failed [" + data + "]");
                    }
                } catch(e) {
                    //window.console && console.log("in saveNewEvent calendar.js error [" + e + "]");
                }
            },
            error: function(jqXHR, error, errorThrown) {
                alert("Not able to schedule video [" + lastMovieDroppedTitle + "] for playback starting " + intendedDateTime + "! Error: " + error);
            }
        });
    })();
    refreshFullCalendar();

    return stat;
}

function handleNewlyCreatedEvent(copiedEventObject, allDay, that, shuffled) {
    //=== combines all custom properties
    copiedEventObject.allDay = allDay;
    copiedEventObject.shuffled = shuffled;

//    if ($("input[name='repeated']:checked").val() === 'Daily Event') {
//        copiedEventObject.color = 'green';
//    } else if ($("input[name='repeated']:checked").val() === 'Weekly Event') {
//        copiedEventObject.color = 'orange';
//    } else if ($("input[name='repeated']:checked").val() === 'Monthly Event') {
//        copiedEventObject.color = 'brown';
//    }

    //alert('calendar.js handleNewlyCreatedEvent() 1');

    if (saveNewEvent(copiedEventObject)) {
        //alert('calendar.js handleNewlyCreatedEvent() 2');
        // render the event on the calendar
        // the last `true` argument determines if the event "sticks" (http://arshaw.com/fullcalendar/docs/event_rendering/renderEvent/)
        if(gOriginalEventObject !== undefined) {  //=== only if it is a dropped event (vs. the scheduled event like from mobile UI)
            //alert('handleNewlyCreatedEvent: movieid = ' + $(that).data("movieid"));
            copiedEventObject.movieid = $.trim($(that).data("movieid"));

            try {
                $('#calendar').fullCalendar('renderEvent', copiedEventObject, true);
                // is the "remove after drop" checkbox checked?
                //if ($('#drop-remove').is(':checked')) {
                // if so, remove the element from the "Draggable Events" list
                $(that).remove();
            } catch(e) {
                //window.console && console.log("calendar.js handleNewlyCreatedEvent error = [" + e + "]");
            }

            //=== cleanup after itself
            $('input[name=shuffled]').val("");
            //}
        }

        showDone();
    }
}

function refreshFullCalendar(force) {
    //window.console && console.log("checking $('input[name=color]') = [" + $('input[name=color]').val() + "]");
    //reload only if it is recurring events to save server CPU cycles/overhead
//    if($('#frequency').val() !== 'One Time Event') {
    if (force || $('input[name=color]').val() !== '') {    //TBD there should be a better way than depending on the color!!!
        try {
            calendar.fullCalendar( 'removeEventSource', gHref );
        } catch(e) {
            //window.console && console.log("calendar.js refreshFullCalendar error 1 [" + e + "]");
        }

//        alert("calendar.js before ajax call of gHref [" + gHref + "]");

        $.ajax({
            url: gHref,
            async: false,
            success: function(data) {
//                alert("calendar.js ajax call returned data [" + data + "]");
                try {
                    $('#calendar').fullCalendar('refetchEvents');   //blind reload!
                    //window.console && console.log("****** fullCalendar refreshed by force! ******");
                    //alert('fullCalendar reloaded!');    //TODO does not seems to work! :(
                } catch(e) {
                    //window.console && console.log("calendar.js refreshFullCalendar error 2 [" + e + "]");
                }
            }
        });
    }


}

//TODO needs to be in a module!
/** start time can not coincide with an already scheduled movie (overlapped), if any
 *  - assumed only one channel for now
 */
function isOverlapped(startDate) {
    var retVal = false;

    try {
        if (startDate !== undefined && gEventArray !== undefined) {
            for (var j = 0; j < gEventArray.length; j++) {
                if (startDate === gEventArray[j].start || startDate.format("mm/dd/yyyy HH:MM") === gEventArray[j].start) {
                    retVal = true;
                    break;
                }
            }
        }
    } catch(e) {
        //window.console && console.log('calendar.js isOverlapped() error [' + e + ']');
    }

    return retVal;
}

/**
 * Create a temporary volatile event.
 */
function createTemporaryEvent(that, title, startDate, endDate, allDay, shuffled) {
    var tempDate1 = startDate;  //clone date
    var tempDate2 = endDate;  //clone date
    gTempEventId = createEventId();
    var color;
    if(shuffled === true) {
        color = '#FFB872';
    } else {
        color = 'gray';
    }
    try {
        return $('#calendar').fullCalendar('renderEvent', {
//        return that.fullCalendar('renderEvent', {
            id: gTempEventId, title: title, start: tempDate1.format("mm/dd/yyyy HH:MM"), end: tempDate2.format("mm/dd/yyyy HH:MM")    //not accurate as it is not the real length of the movie!
            , movieid: $('input[name=movieid]').val(), movieUrl: 'dummy', thumbUrl: '', color: color, allDay: allDay, editable: false
            //TODO to disable the event click properly instead of by checking the title!!!
//        , eventClick: function(event) {
//            if (event.url) {
//                return false;
//            }
//        }
        }, true);
    } catch(e) {
        //window.console && console.log('main-config.js createTemporaryEvent() error [' + e + ']');
    }
}

function createEventId() {
    //return generateUUID();
    var d = new Date().getTime();

    return d;
}

/**
 * Create a new calendar event.
 *
 * @param that the FullCalendar object instance
 * @param date the JavaScript date of the event
 * @param allDay true if all day, false otherwise
 * @param shuffle true if it is a shuffle play (randomized), false otherwise
 */
function createNewEvent(that, date, allDay, shuffle) {
    gOriginalEventObject = undefined;

    showInProgress();

    if(allDay === undefined) {
        allDay = false;
    }

    // retrieve the dropped element's stored Event Object
    gOriginalEventObject = $(that).data('eventObject');

    // we need to copy it, so that multiple events don't have a reference to the same object
    var copiedEventObject;
    var tempDate1;
    var tempDate2;
    if(gOriginalEventObject !== true) {
        gTempEventId = eventId; //TODO eventId could be undefined here!!!
        if(that !== undefined) {
            tempDate1 = new Date(date);  //clone date
        } else {
            //=== must be from non-calendar UI like movie UI
            tempDate1 = date;  //clone date
        }
        tempDate2 = new Date(tempDate1.getFullYear(), tempDate1.getMonth(), tempDate1.getDate(), tempDate1.getHours(), tempDate1.getMinutes(), tempDate1.getSeconds() + duration, 0);    //TODO duration could be undefined here!!!
        var eventId = createEventId();
//        alert('1 calendar.js createNewEvent() movieid [' + $('input[name=movieid]').val() + ']');
        if(that === undefined) {
            //=== supports create event from Movie UI use case
            copiedEventObject = {
                id: eventId, allDay: allDay
            };
        } else {
            //=== supports non-drag and drop use case
            copiedEventObject = that.fullCalendar('renderEvent', {
                id: eventId, allDay: allDay
            }, true);
        }
        copiedEventObject.title = $('input[name=title]').val();
        copiedEventObject.start = tempDate1.format("mm/dd/yyyy HH:MM");  //date;
        copiedEventObject.movieid = $('input[name=movieid]').val();
        copiedEventObject.movieUrl = $('input[name=movieUrl]').val();
        copiedEventObject.thumbUrl = $('input[name=thumbUrl]').val();

        //showDone(); return;     //TODO when the date bug is fixed, to remove this line
    } else {
//    if(gOriginalEventObject) {
        copiedEventObject = $.extend({}, gOriginalEventObject);
        // assign it the date that was reported
        //copiedEventObject.start = date;
        //copiedEventObject.allDay = allDay;
        //http://stackoverflow.com/questions/4169570/fullcalendar-set-event-end-in-drop-function
        tempDate1 = new Date(date);  //clone date
        //alert('date [' + date + "] tempDate1 [" + tempDate1 + "] allDay [" + allDay + "]");
        copiedEventObject.start = tempDate1.format("mm/dd/yyyy HH:MM");  //date;
        //alert('tempDate1 = ' + tempDate1);
    }

    //alert('calendar.js createNewEvent() date [' + date + '] tempDate1.format("mm/dd/yyyy HH:MM") [' + copiedEventObject.start  + ']');

    var youtube_id;
    var event = copiedEventObject;
    var movieUrl = $('input[name=movieUrl]').val();
    //http://stackoverflow.com/questions/3452546/javascript-regex-how-to-get-youtube-video-id-from-url
    if(movieUrl.match('/?.*(?:youtu.be\\/|v\\/|u/\\w/|embed\\/|watch\\?.*&?v=)')){
        youtube_id = movieUrl.split(/v\/|v=|youtu\.be\//)[1].split(/[?&]/)[0];
    }
    //=== get movie duration (source: http://stackoverflow.com/questions/2086260/youtube-player-api-how-to-get-duration-of-a-loaded-cued-video-without-playing-i)
    var youTubeURL = 'https://gdata.youtube.com/feeds/api/videos/' + youtube_id +'?v=2&alt=json';
    var duration;
    var ajaxReturned = false;
//    var that = this;
//    alert('1 calendar.js createNewEvent() before ajax submit movieUrl [' + movieUrl + '] youTubeURL [' + youTubeURL + '] ...');
    try {
        var json = (function (event) {
            $.ajax({
                async: false,
                global: false,
                url: youTubeURL,
                dataType: "jsonp", // necessary for IE9
                crossDomain: true,
                success: function (data, event) {
                    duration = data.entry.media$group.yt$duration.seconds;
                    tempDate2 = new Date(tempDate1.getFullYear(), tempDate1.getMonth(), tempDate1.getDate(), tempDate1.getHours(), tempDate1.getMinutes(), tempDate1.getSeconds() + duration, 0);    // <-- make sure we assigned a date object
                    copiedEventObject.end = tempDate2.format("mm/dd/yyyy HH:MM");
                    //alert('copiedEventObject.start = ' + copiedEventObject.start);
                    //TODO - to check to make sure this is not past date!!!
//                    alert("1 calendar.js createNewEvent() ajax call returned: movieUrl = id [" + youtube_id + "] duration [" + duration + "] url [" + event.movieUrl + "]");
                    handleNewlyCreatedEvent(copiedEventObject, allDay, that, shuffle);
                    ajaxReturned = true;
                },
                error: function(error) {
                    alert("1 Not able to get the duration of YouTube video. Your movie length will not be accurate! Error: " + error);
                }
            });
        })();
        if(!ajaxReturned) {
            duration = 3600;    //just set to 1 hour if duration is not known/error occured
            tempDate2 = new Date(tempDate1.getFullYear(), tempDate1.getMonth(), tempDate1.getDate(), tempDate1.getHours(), tempDate1.getMinutes(), tempDate1.getSeconds() + duration, 0);    // <-- make sure we assigned a date object
            copiedEventObject.end = tempDate2.format("mm/dd/yyyy HH:MM");
            //TODO - to check to make sure this is not past date!!!
            //alert("2 calendar.js createNewEvent() ajax error: movieUrl = id [" + youtube_id + "] duration [" + duration + "] url [" + event.movieUrl + "]");
            handleNewlyCreatedEvent(copiedEventObject, allDay, that, shuffle);
        }
    } catch(e) {
        alert("2 Not able to get the duration of YouTube video. Your movie length will not be accurate! Error: " + e);
    }
}

function saveExistingEvent(event) {
    showInProgress();

    var stat = false;

    //window.console && console.log("in saveExistingEvent calendar.js User ID: " + username + " event.id: " + event.id);
    $('input[name=id]').val(event.id);
    $('input[name=title]').val(event.title);
    var s = new Date(event.start);
    var startDateString = s.format("mm/dd/yyyy HH:MM");
    var e = new Date(event.end);
    var endDateString = e.format("mm/dd/yyyy HH:MM");
    $('input[name=start]').val(startDateString);
    $('input[name=end]').val(endDateString);
    $('input[name=movieUrl]').val(event.movieUrl);
    $('input[name=movieid]').val(event.movieid);
    $('input[name=allDay]').val(event.allDay);
    $('input[name=color]').val(event.color);
    //=== final fix for not found issue
    $('input[name=type]').val('modelCalendar');
    $('input[name=action]').val('update');
    $('input[name=repeat]').val(frequency);
    $('input[name=uid]').val(username);

    //window.console && console.log("in saveExistingEvent calendar.js $('input[name=id]'): " + $('input[name=id]').val());
    //window.console && console.log("in saveExistingEvent calendar.js $('input[name=start]'): " + $('input[name=start]').val());
    //window.console && console.log("in saveExistingEvent calendar.js $('input[name=end]'): " + $('input[name=end]').val());
    var frequency = $('input[name=repeated]:checked').val();
    //window.console && console.log("updating calendar event id '" + event.id + "' repeated '" + frequency + "'");
    //submit to backend first
    $.ajax({
        type: "POST",
        url: "/api/jwt/ws/crud",  // read the action attribute of the form
        data: $('#calendarForm').serialize(),  // what data should go there?
//        dataType: "jsonp", // necessary for IE9
        crossDomain: true,
        success: function (data) {
            // wohoo, this works!
            ////window.console && console.log("calendar event created, response = [" + data + "]");
            var obj = jQuery.parseJSON(data);
            //=== always used id provided by the server
            if(obj && obj.key && obj.key.id) {
                //window.console && console.log("2 calendar event id returned by server = '" + obj.key.id + "'");
                event.id = obj.key.id;
                //window.console && console.log("in saveExistingEvent calendar.js User ID: " + username + " event.id: " + event.id + " updated");
                stat = true;
                showDone();
            } else {
                alert("calendar.js: User ID: " + username + " failed [" + data + "]");
            }
        },
        error: function(jqXHR, error, errorThrown) {
            alert("Not able to update a scheduled video. Error: " + error);
        }

    });
    refreshFullCalendar();

    return stat;
}

/**
 * jQuery drag and drop with AngularJS (Desktop client)
 *
 * @param itemRow
 * @param movieUrl
 */
function handleDraggable(itemRow, movieUrl) {
    //window.console && console.log("handleDraggable entered movieUrl '" + movieUrl + "'");
    // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
    // it doesn't need to have a start or end
    var eventObject = {
        movieUrl: movieUrl
    };
    eventObject.title = $.trim($(itemRow).data("movietitle"));   //$.trim($(itemRow).text()) // use the element's text as the event title
    eventObject.movieid = $.trim($(itemRow).data("movieid"));
    eventObject.movieUrl = movieUrl;
    eventObject.thumbUrl = $(itemRow).find("img").attr("src");
    //window.console && console.log("1 handleDraggable entered");
    console && console.log("handleDraggable: thumb [" + eventObject.thumb + "]");
    console && console.log("handleDraggable: movieid [" + eventObject.movieid + "]");
    //window.console && console.log(eventObject.thumbUrl);


// store the Event Object in the DOM element so we can get to it later
    $(itemRow).data('eventObject', eventObject);
    //TODO begin this should not depends on desktop hovering!
    $('input[name=title]').val(eventObject.title);
    $('input[name=movieid]').val(eventObject.movieid);
    $('input[name=movieUrl]').val(eventObject.movieUrl);
    $('input[name=thumbUrl]').val(eventObject.thumbUrl);
    $('input[name=allDay]').val(eventObject.allDay);
    //TODO end this should not depends on desktop hovering!
    //window.console && console.log("2 handleDraggable entered");

    try {
        // make the event draggable using jQuery UI
        $(itemRow).draggable({
            zIndex: 999,
            revert: true,      // will cause the event to go back to its
            revertDuration: 0  //  original position after the drag
        });
    } catch(e) {
        //window.console && console.log("handleDraggable error [" + e + "]");
    }
    //window.console && console.log("3 handleDraggable entered");

}

/**
 * Click and update UI with AngularJS (mainly Mobile client)
 */
function handleNonDraggable(item) {
    //alert('calendar.js handleNonDraggable title [' + item.title + '] id [' + item.id + ']');
    //window.console && console.log("handleNonDraggable entered");
    // create an Event Object (http://arshaw.com/fullcalendar/docs/event_data/Event_Object/)
    // it doesn't need to have a start or end
    var eventObject = {
        movieUrl: item.url
        , allDay: false
    };
    eventObject.title = $.trim(item.title);
    eventObject.movieid = $.trim(item.id);
    eventObject.thumbUrl = $('input[name=thumbUrl]').val();

    //window.console && console.log("1 handleNonDraggable entered");
    //window.console && console.log("handleNonDraggable: thumb [" + eventObject.thumb + "]");
    //window.console && console.log("handleNonDraggable: movieid [" + eventObject.movieid + "]");
    //window.console && console.log(eventObject.thumbUrl);


// store the Event Object in the DOM element so we can get to it later
    //TODO begin this should not depends on desktop hovering!
//    alert('calendar.js eventObject.title [' + eventObject.title + ']');
    $('input[name=title]').val(eventObject.title);
    $('input[name=movieid]').val(item.id);
    $('input[name=movieUrl]').val(eventObject.movieUrl);
    $('input[name=thumbUrl]').val(eventObject.thumbUrl);
    $('input[name=allDay]').val(eventObject.allDay);
    //TODO end this should not depends on desktop hovering!
    //window.console && console.log("2 handleNonDraggable entered");
}

function viewAllShowtime() {
    window.open("/api/jwt/ws/crud?type=modelCalendar&uid=" + username, "_new");
}

function removeAllShowtime() {
    var r=confirm("Please confirm to purge all your calendar events. The action can not be undone.");
    if (r==true)
    {
        window.open("/api/jwt/ws/crud?type=modelMovie&uid=" + username + "&action=purgecalendar", "_new");
    }
    else
    {
        //alert("You pressed Cancel!")
    }
}

//    var evs = $('#calendar').fullCalendar('getView').getShownEvents();
//TODO this call probably can be avoid if somehow we can just make use of the one which populate the fullcalendar events
$.ajax({
    type: "GET",
    url: "/api/jwt/ws/crud?type=modelCalendar&uid=" + username,
    async: true,
    success: function(data) {
        ////window.console && console.log("calendar event created, response = [" + data + "]");
        if(data !== undefined) {
            var obj = jQuery.parseJSON(data);
            var j;
            //=== need to sort the obj first based on the datetime!!!
            for (var i = 0; i < obj.length; i++) {
                try {
                    j = {id: obj[i].id, start:obj[i].start, movie_id: obj[i].movie_id};
                    gEventArray.push(j);
                }
                catch(e){
                    //window.console && console.log('channel.js loadMovie 1: An error has occurred: '+ e.message + ' - The application will not function correctly. Please contact the developer!');
                }
            }
        }
        //alert("events size [" + gEventArray.length + "]");
    },
    error: function(jqXHR, error, errorThrown) {
        if(jqXHR.status && jqXHR.status==500){
            //alert(jqXHR.responseText);
            //TODO ignoring error from the server - should have been handled by movie UI already (assuming that it is loaded by movie UI first everytime)
            //window.console && console.log("calendar.js error ignored: " + jqXHR.responseText + " [gBuild " + gBuild + "]");
        }else{
            //most slightly due to empty JSON response thus causing Uncaught SyntaxError: Unexpected end of input	movie.html:1
            if(jqXHR.responseText !== '') {
                alert("calendar.js error: [" + jqXHR.responseText + "]");
            }
        }
    }

});
