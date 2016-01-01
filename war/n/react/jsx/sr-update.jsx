var SRUpdate = React.createClass({
    getInitialState: function() {
        var component = this;
        console.log('SRUpdate booted');
        var item = { id: -1, service: '', description: '',  endpoint: '' };
        var qs = URI(location.href).query(true); // == e.g. { id : 4529987906437120 }
        window.swagger = new SwaggerClient({
            url: location.origin + "/swagger/swagger.json",
            success: function() {
                swagger.sr.id({id: qs.id},{responseContentType: 'application/json'}, function(data) {
                    //document.getElementById("mydata").innerHTML = JSON.stringify(data.obj);
                    //console.log(data.obj);
                    component.setState({
                        id: data.obj.id,
                        service: data.obj.service,
                        description: data.obj.description,
                        endpoint: data.obj.endpoint
                    });
                });
            }
        });

        return {
            item,
            isSubmitting: true
        };
    },
    render: function() {
        return (
                    <div className="container">
                        <h1>Service Manager - Update</h1><br />
                        <form className="form-horizontal" role="form">
                            <div className="form-group row">
                                <div className="control-group">
                                    <label htmlFor="id" className="col-sm-2 control-label">ID:</label>
                                    <input type="text" className="col-sm-10 form-control" id="id" defaultValue value={this.state.id} required readOnly />
                                </div>
                                <div className="control-group">
                                    <label htmlFor="title" className="col-sm-2 control-label">Service:</label>
                                    <input type="text" className="col-sm-10 form-control" id="title" onKeyPress={this.updateItem} required value={this.state.service} onChange={function(e){this.setState({service: e.target.value})}.bind(this)} defaultvalue placeholder="Enter any easy to remember service name." />
                                </div>
                                <div className="control-group">
                                    <label htmlFor="desc" className="col-sm-2 control-label">Description:</label>
                                    <div className="col-sm-10">
                                        <input type="text" className="form-control" id="desc" onKeyPress={this.updateItem} value={this.state.description} onChange={function(e){this.setState({description: e.target.value})}.bind(this)} defaultvalue />
                                    </div>
                                </div>
                                <div className="control-group">
                                    <label htmlFor="endpoint" className="col-sm-2 control-label">URL:</label>
                                    <div className="col-sm-10">
                                        <input type="url" className="form-control" id="endpoint" onKeyPress={this.updateItem} required value={this.state.endpoint} onChange={function(e){this.setState({endpoint: e.target.value})}.bind(this)} defaultvalue placeholder="Enter any valid URL e.g. http://www.google.com." />
                                    </div>
                                </div>
                                <div className="control-group">
                                    <label htmlFor="createItem" className="col-sm-2 control-label" />
                                    <div className="col-sm-10">
                                        <input type="hidden" name="_method" defaultValue value="POST" />
                                        <input className="form-control btn btn-primary" id="deleteItem" type="button" style={{background: '#98969E'}} defaultValue value="Delete" onClick={this.deleteItem} />
                                        <input className="form-control btn btn-primary" id="createItem" type="button" style={{background: '#98969E'}} defaultValue value="Save" disabled={this.state.isSubmitting} onClick={this.updateItem} />
                                        <input className="form-control btn" id="cancelItem" defaultValue value="Cancel" type="button" onClick={this.goHome} />
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
        )
    },
    goHome: function() {
        location.href='fusrstart.html';
    },
    updateItem: function() {
        var component = this;
        window.swagger = new SwaggerClient({
            url: location.origin + "/swagger/swagger.json",
            success: function() {
                var srJson = {
                    sr: {
                        id: component.state.id,
                        service: component.state.service,
                        description: component.state.description,
                        endpoint: component.state.endpoint
                    }
                };
                swagger.sr.save(srJson,{responseContentType: 'application/json'}, function(data) {
                    //document.getElementById("mydata").innerHTML = JSON.stringify(data.obj);
                    //console.log(data.obj);
                });
            }
        });
        console.log('SRUpdate input saved');
    },
    deleteItem: function() {
        var component = this;
        window.swagger = new SwaggerClient({
            url: location.origin + "/swagger/swagger.json",
            success: function() {
                swagger.sr.delete({id: component.state.id},{responseContentType: 'application/json'}, function(data) {
                    //document.getElementById("mydata").innerHTML = JSON.stringify(data.obj);
                    //console.log(data.obj);
                });
            }
        });
        console.log('SRUpdate item deleted');
    }
});
