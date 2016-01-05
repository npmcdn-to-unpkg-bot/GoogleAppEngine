<sr-create>
    <sr-update data={item} />
    <script>
    this.item = opts.data;
    this.on('mount', function () {
        console.log("sr-create tag mounted");
        //console.log(this.item);
    });
    </script>
</sr-create>
