var SRUpdate = React.createClass({
    getInitialState: function() {
        //console.log('SRUpdate booted');
        var id = this.props.id;
        var qs = URI(location.href).query(true); // == e.g. { id : 4529987906437120 }
        window.swagger = new SwaggerClient({
            url: location.origin + "/swagger/swagger.json",
            success: function() {
                swagger.sr.id({id: qs.id},{responseContentType: 'application/json'}, function(data) {
                    document.getElementById("mydata").innerHTML = JSON.stringify(data.obj);
                    console.log(data.obj);
                    id = data.obj.id;
                    this.service = data.obj.service;
                    this.description = data.obj.description;
                    this.endpoint = data.obj.endpoint;
                });
            }
        });

        return null;
    },
    render: function() {
        return (
                    <div className="container" onupdateevent="{this.updateItem}">
                        <h1>Service Manager - Update</h1><br />
                        <form className="form-horizontal" role="form">
                            <div className="form-group row">
                                <div className="control-group">
                                    <label htmlFor="id" className="col-sm-2 control-label">ID:</label>
                                    <input type="text" className="col-sm-10 form-control" id="id" defaultValue value={this.props.id} required readOnly />
                                </div>
                                <div className="control-group">
                                    <label htmlFor="title" className="col-sm-2 control-label">Service:</label>
                                    <input type="text" className="col-sm-10 form-control" id="title" onkeydown="{this.updateItem}" required value={this.service} defaultvalue placeholder="Enter any easy to remember service name." />
                                </div>
                                <div className="control-group">
                                    <label htmlFor="desc" className="col-sm-2 control-label">Description:</label>
                                    <div className="col-sm-10">
                                        <input type="text" className="form-control" id="desc" onkeydown="{this.updateItem}" value={this.description} defaultvalue />
                                    </div>
                                </div>
                                <div className="control-group">
                                    <label htmlFor="endpoint" className="col-sm-2 control-label">URL:</label>
                                    <div className="col-sm-10">
                                        <input type="url" className="form-control" id="endpoint" onkeydown="{this.updateItem}" required value={this.endpoint} defaultvalue placeholder="Enter any valid URL e.g. http://www.google.com." />
                                    </div>
                                </div>
                                <div className="control-group">
                                    <label htmlFor="createItem" className="col-sm-2 control-label" />
                                    <div className="col-sm-10">
                                        <input type="hidden" name="_method" defaultValue="POST" />
                                        <input className="form-control btn btn-primary" id="deleteItem" type="button" style={{background: '#98969E'}} defaultValue="Delete" ng-click="page.deleteItem()" />
                                        <input className="form-control btn btn-primary" id="createItem" type="submit" style={{background: '#98969E'}} defaultValue="Save" ng-disabled="!(page.endpoint)" onclick="{this.props.onUpdateEvent}" />
                                        <input className="form-control btn" id="cancelItem" defaultValue="Cancel" type="button" onclick="location.href='fusrstart.html'" />
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
        )
    },
    updateItem: function() {

    }
});

React.render(<SRUpdate />, document.getElementById('sr-update'));
