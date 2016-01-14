var express=require('express');
var app=express();
var request = require('request')
var apicache  = require('apicache');
var cache     = apicache.options({ debug: true }).middleware;
var cors = require('cors');
var bodyParser = require('body-parser');
//var qs = require('querystring');
var uuid = 'Yellow v0.0.1 build 103d alpha';

var ipaddress = process.env.OPENSHIFT_NODEJS_IP || "127.0.0.1";
//var ipaddress = "162.251.112.180";
var port = process.env.OPENSHIFT_NODEJS_PORT || 8080; //uncomment this for OpenShift!!!
//var port = 8082;    //use this when debugging with node-inspector as it is already using 8080

var uid = ''
//var targetHost = 'http://chudoonet.appspot.com'
var targetHost = 'http://127.0.0.1:8888'
var getItemsURL = targetHost + '/api/jwt/ws/crud'
var postItemURL = targetHost + '/api/jwt/ws/crud'
var count = 0;

app.use(cors());
app.use(express.bodyParser());

function setRestHost(aid) {
    if(typeof aid !== 'undefined') {
        if (aid.indexOf('localhost') > -1) {
            targetHost = 'http://127.0.0.1:8888'
        } else if (aid.indexOf('share') > -1) {
            //targetHost = 'https://2share.appspot.com'
            targetHost = 'https://cloudaware.appspot.com'
        } else if (aid.indexOf('chudoo') > -1) {
            targetHost = 'https://chudoonet.appspot.com'
        } else if (aid.indexOf('awa') > -1) {
            targetHost = 'https://cloudaware.appspot.com'
        }
    } else {
        targetHost = 'http://127.0.0.1:8888'
    }

    getItemsURL = targetHost + '/api/jwt/ws/crud'
    postItemURL = targetHost + '/api/jwt/ws/crud'
    console.log('aid [' + aid + '] targetHost set to [' + targetHost + ']');
}

app.get('/api/jwt/ws/crud', cache('1 day'), function(req,res,next) {
    var origin = req.query.origin;
    var type = req.query.type;	//e.g. modelMovie
    var maxPerPage = req.query.maxPerPage;
    var pageNumber = req.query.pageNumber;
    var aid = req.query.aid;
    var uid = req.query.uid;
    if(typeof uid === 'undefined') {
        uid = req.body.uid;
    }
    var action = req.query.action;
    var username = req.query.username;
    var logintype = req.query.logintype;
    var filter = req.query.filter;

    console.log('get called ' + count++ + ' : type ['+ type + ']')
    setRestHost(origin);

    request({
        headers: {
            'Authorization': 'Bearer xxx',
        },
        uri: getItemsURL + '?uid=' + uid
          + '&origin=' + origin
          + '&type=' + type
          + '&maxPerPage=' + maxPerPage
          + '&pageNumber=' + pageNumber
          + '&aid=' + aid
          + '&filter=' + filter,
          body: formData,
          method: 'GET'
        }
        , function (error, response, body) {
        if (!error && response.statusCode == 200) {
  	        req.apicacheGroup = origin+"-"+aid+"-"+uid+"-"+type+"-"+filter;	//the key is the appId + user

            res.send(body)
            console.log("apicacheGroup [" + req.apicacheGroup + "] added into cache")
            //res.send(body + " type[" + type + "] uid[" + uid + "]")
        }
    })
});
app.post('/api/jwt/ws/crud', function(req, res, next) {
    var origin = req.body.origin;
    var aid = req.body.aid;
    var uid = req.body.uid;
    var action = req.body.action;
    var type = req.body.type;
    var id = req.body.id;
    var oid = req.body.oid;
    var title = req.body.title;
    var description = req.body.description;
    var url = req.body.url;
    var shared = req.body.shared;
    var channelPattern = req.body.channelPattern;
    var search_results = req.body.search_results;
    var filter = req.body.filter;

    setRestHost(origin);
    console.log("post id[" + id + "]")
    var formData = {
        aid: aid,
        uid: uid,
        action: action,
        type: type,
        id: id,
        oid: oid,
        title: title,
        description: description,
        url: url,
        shared: shared,
        channelPattern: channelPattern,
        search_results: search_results,
        filter: filter
    };
    request.post({
        headers: {
            'Authorization': 'Bearer xxx',
        },
        uri: postItemURL,
          body: formData,
          method: 'POST'
        }
    	, function (error, response, body) {
        if (!error && response.statusCode == 200) {
            //=== update model (delete, update or insert)
            apicache.clear(origin+"-"+aid+"-"+uid+"-"+type+"-"+filter);

            res.send(body)
            //res.send(body + " type[" + type + "] uid[" + uid + "]")
        }
    })
});
app.get('/test',function(req,res) {
 res.send(uuid);
});
app.get('*',function(req,res) {
 res.send('oops, bad route');
});

app.listen(port, ipaddress, function() {
 console.log('Listening on port '+port+ ' on host ' + ipaddress + ' [' + uuid + ']');
});