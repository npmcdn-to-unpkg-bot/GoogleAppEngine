<sr-start>
    <div class="table-responsive">
    <table class="table table-striped table-bordered">
    <thead>
    <tr><td>ID</td><td>SERV</td><td>DESCRIPTION</td><td>S URL</td><td>VIS</td><td>UPT</td><td>ENDPOINT</td><td>HIT</td></tr>
    </thead>
    <tbody each={list}>
        <tr><td><a href="fusrupdate.html?id={id}">{id}</a></td><td>{service}</td><td>{description}</td><td>{url}</td><td>{lastAccessed}</td><td>{lastUpdated}</td><td>{endpoint}</td><td>{hit}</td></tr>
    </tbody>
    </table>
    </div>
    <script>
        this.list = opts.data;
        this.on('mount', function () {
            //console.log("data string size = " + opts.data.length);
            console.log("sr-start tag mounted");
            //console.log(this.list);
        });
    </script>
</sr-start>