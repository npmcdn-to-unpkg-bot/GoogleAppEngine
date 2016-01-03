riot.tag2('hello', '<h1>Hello World</h1>', '', '', function(opts) {
});
riot.tag2('sr-create', '<sr-update></sr-update>', '', '', function(opts) {
    this.item = opts.data;
    this.on('mount', function () {
        console.log("sr-create tag mounted");

    });
});

riot.tag2('sr-start', '<div class="table-responsive"> <table class="table table-striped table-bordered"> <thead> <tr><td>ID</td><td>SERV</td><td>DESCRIPTION</td><td>S URL</td><td>VIS</td><td>UPT</td><td>ENDPOINT</td><td>HIT</td></tr> </thead> <tbody each="{list}"> <tr><td><a href="fusrupdate.html?id={id}">{id}</a></td><td>{service}</td><td>{description}</td><td>{url}</td><td>{lastAccessed}</td><td>{lastUpdated}</td><td>{endpoint}</td><td>{hit}</td></tr> </tbody> </table> </div>', '', '', function(opts) {
        this.list = opts.data;
        this.on('mount', function () {

            console.log("sr-start tag mounted");

        });
}, '{ }');
riot.tag2('sr-update', '<div class="container"> <h1>Service Manager - Update</h1><br> <form class="form-horizontal" role="form"> <div class="form-group row"> <div classname="control-group"> <label htmlfor="id" classname="col-sm-2 control-label">ID:</label> <input type="text" classname="col-sm-10 form-control" id="id" defaultvalue value="{item.id}" required readonly> </div> <div class="control-group"> <label for="title" class="col-sm-2 control-label">Service:</label> <input type="text" class="col-sm-10 form-control" id="title" ng-enter="page.createItem()" required value="{item.service}" placeholder="Enter any easy to remember service name."> </div> <div class="control-group"> <label for="desc" class="col-sm-2 control-label">Description:</label> <div class="col-sm-10"> <input type="text" class="form-control" id="desc" ng-enter="page.createItem()" value="{item.description}"> </div> </div> <div class="control-group"> <label for="endpoint" class="col-sm-2 control-label">URL:</label> <div class="col-sm-10"> <input type="url" class="form-control" id="endpoint" ng-enter="page.createItem()" required value="{item.endpoint}" placeholder="Enter any valid URL e.g. http://www.google.com."> </div> </div> <div class="control-group"> <label for="createItem" class="col-sm-2 control-label"></label> <div class="col-sm-10"> <input type="hidden" name="_method" value="POST"> <input class="form-control btn btn-primary" id="createItem" type="submit" style="background:#98969E;" value="Save" ng-click="page.createItem()"> <input class="form-control btn" id="cancelItem" value="Cancel" type="button" onclick="location.href=\'fusrstart.html\'"> </div> </div> </div> </form> </div>', '', '', function(opts) {
    this.item = opts.data;
    this.on('mount', function () {
        console.log("sr-update tag mounted");

    });
}, '{ }');