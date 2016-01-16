var SRUpdate = React.createClass({
    propTypes: {
        id: React.PropTypes.number,
        service: React.PropTypes.string,
        description: React.PropTypes.string,
        endpoint: function (props, propName, component) {
            if (typeof props[propName] !== 'undefined' && !this.isURL(props[propName])) {
                return new Error('Invalid URL!');
            }
        }
        //gender: React.PropTypes.oneOf(['M','F','NA']),
        //node: React.PropTypes.node,
        //cb: React.PropTypes.func.isRequired
    },
    isURL: function(value) {
        //console.log(value);
        var urlregex = new RegExp("^(http|https|ftp)\://([a-zA-Z0-9\.\-]+(\:[a-zA-Z0-9\.&amp;%\$\-]+)*@)*((25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9])\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[1-9]|0)\.(25[0-5]|2[0-4][0-9]|[0-1]{1}[0-9]{2}|[1-9]{1}[0-9]{1}|[0-9])|([a-zA-Z0-9\-]+\.)*[a-zA-Z0-9\-]+\.(com|edu|gov|int|mil|net|org|biz|arpa|info|name|pro|aero|coop|museum|[a-zA-Z]{2}))(\:[0-9]+)*(/($|[a-zA-Z0-9\.\,\?\'\\\+&amp;%\$#\=~_\-]+))*$");
        if (urlregex.test(value)) {
            return (true);
        }
        return (false);
    },
    getInitialState: function() {
        var item = {id: -1, service: '', description: '', endpoint: ''};
        var qs = URI(location.href).query(true); // == e.g. { id : 4529987906437120 }
        if(qs.id > 0) {
            var component = this;
            console.log('SRUpdate booted');
            var key = localStorage.getItem('userJWTToken');
            //var apiKeyAuth = new SwaggerClient.ApiKeyAuthorization( "Authorization", "Bearer " + key, "header" );
            //window.swaggerUi.api.clientAuthorizations.add( "bearer", apiKeyAuth );
            console.log( "Set bearer token: " + key );
            window.swagger = new SwaggerClient({
                url: location.origin + "/swagger/swagger.json",
                success: function () {
                    swagger.sr.id({id: qs.id}, {responseContentType: 'application/json'}, function (data) {
                        //document.getElementById("mydata").innerHTML = JSON.stringify(data.obj);
                        //console.log(data.obj);
                        component.setState({
                            id: data.obj.id,
                            service: data.obj.service,
                            description: data.obj.description,
                            endpoint: data.obj.endpoint,
                            //enable save if url is valid
                            isSubmitting: component.isURL(data.obj.endpoint)
                        });
                    });
                },
                authorizations : {
                    someHeaderAuth: new SwaggerClient.ApiKeyAuthorization('Authorization', "Bearer " + key, 'header')
                }
            });
        }
        return {
            item,
            isSubmitting: false
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
                                    <input type="text" className="col-sm-10 form-control" id="title" onKeyDown={this.updateItem} required value={this.state.service} ref="service" onChange={function(e){this.setState({service: e.target.value})}.bind(this)} defaultvalue placeholder="Enter any easy to remember service name." />
                                </div>
                                <div className="control-group">
                                    <label htmlFor="desc" className="col-sm-2 control-label">Description:</label>
                                    <div className="col-sm-10">
                                        <input type="text" className="form-control" id="desc" onKeyDown={this.updateItem} value={this.state.description} onChange={function(e){this.setState({description: e.target.value})}.bind(this)} defaultvalue />
                                    </div>
                                </div>
                                <div className="control-group">
                                    <label htmlFor="endpoint" className="col-sm-2 control-label">URL:</label>
                                    <div className="col-sm-10">
                                        <input type="url" className="form-control" id="endpoint" onKeyDown={this.updateItem} required value={this.state.endpoint} onChange={function(e){this.setState({endpoint: e.target.value, isSubmitting: this.isURL(e.target.value)})}.bind(this)} onClick={this.updateItem} defaultvalue placeholder="Enter any valid URL e.g. http://www.google.com." />
                                    </div>
                                </div>
                                <div className="control-group">
                                    <label htmlFor="createItem" className="col-sm-2 control-label" />
                                    <div className="col-sm-10">
                                        <input type="hidden" name="_method" defaultValue value="POST" />
                                        <input className="form-control btn btn-primary" id="deleteItem" type="button" style={{background: '#98969E'}} defaultValue value="Delete" onClick={this.deleteItem} />
                                        <input className="form-control btn btn-primary" id="createItem" type="button" style={{background: '#98969E'}} defaultValue value="Save" disabled={!this.state.isSubmitting} onClick={this.save} />
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
    save: function() {
        var component = this;
        var key = localStorage.getItem('userJWTToken');
        //console.log('sr-update.jsx save: ' + component.refs.service.value + ' ' + component.state.service);
        window.swagger = new SwaggerClient({
            url: location.origin + "/swagger/swagger.json",
            success: function() {
                var srJson = {
                    sr: {
                        id: component.state.id,
                        service: component.refs.service.value,   //component.state.service,
                        description: component.state.description,
                        endpoint: component.state.endpoint
                    }
                };
                swagger.sr.save(srJson,{responseContentType: 'application/json'}, function(data) {
                    //document.getElementById("mydata").innerHTML = JSON.stringify(data.obj);
                    //console.log(data.obj);
                    component.goHome();
                });
            },
            authorizations : {
                someHeaderAuth: new SwaggerClient.ApiKeyAuthorization('Authorization', "Bearer " + key, 'header')
            }
        });
        console.log('SRUpdate input saved');
    },
    updateItem: function(e) {
        var evt = e || window.event
        // "e" is the standard behavior (FF, Chrome, Safari, Opera),
        // while "window.event" (or "event") is IE's behavior
        if ( evt.keyCode === 13 ) {
            this.save();
            // You can disable the form submission this way:
            return false
        }
    },
    deleteItem: function() {
        var component = this;
        var key = localStorage.getItem('userJWTToken');
        window.swagger = new SwaggerClient({
            url: location.origin + "/swagger/swagger.json",
            success: function() {
                swagger.sr.delete({id: component.state.id},{responseContentType: 'application/json'}, function(data) {
                    //document.getElementById("mydata").innerHTML = JSON.stringify(data.obj);
                    //console.log(data.obj);
                    component.goHome();
                });
            },
            authorizations : {
                someHeaderAuth: new SwaggerClient.ApiKeyAuthorization('Authorization', "Bearer " + key, 'header')
            }
        });
        console.log('SRUpdate item deleted');
    }
});
