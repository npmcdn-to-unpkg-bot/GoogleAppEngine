riot.tag2('hello', '<h1>Hello World</h1>', '', '', function(opts) {
});
riot.tag2('sr-start', '<div class="table-responsive"> <table class="table table-striped table-bordered"> <thead> <tr><td>ID</td><td>SERV</td><td>DESCRIPTION</td><td>S URL</td><td>VIS</td><td>UPT</td><td>ENDPOINT</td><td>HIT</td></tr> </thead> <tbody each="{item, index in list}"> <tr each="{item}"><td><a href="fusrupdate.html?id={id}">{id}</a></td><td>{service}</td><td>{description}</td><td>{url}</td><td>{lastAccessed}</td><td>{lastUpdated}</td><td>{endpoint}</td><td>{hit}</td></tr> </tbody> </table> </div>', '', '', function(opts) {
        console.log("list size = " + opts.list.length);
}, '{ }');
