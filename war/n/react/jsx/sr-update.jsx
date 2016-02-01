var SRUpdate = React.createClass({
    propTypes: {
        id: React.PropTypes.number,
        service: React.PropTypes.string,
        description: React.PropTypes.string,
        endpoint: function (props, propName, component) {
            if (typeof props[propName] !== 'undefined' && !component.isURL(props[propName])) {
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
        var qs = URI(location.href).query(true); // == e.g. { id : 4529987906437120 }
        if(typeof qs.id === 'undefined') {
            if(typeof this.props.id !== 'undefined') {
                qs.id = this.props.id;  //TODO anti-pattern to refer to props in here???
            } else {
                qs.id = 0;
            }
        }
        if(qs.id > 0) {
            var component = this;
            console.log('SRUpdate booted');
            var key = localStorage.getItem('userJWTToken');
            //console.log( "Set bearer token: " + key );
            window.swagger = new SwaggerClient({
                url: location.origin + "/swagger/swagger.json",
                success: function () {
                    swagger.sr.id({id: qs.id}, {responseContentType: 'application/json'}, function (data) {
                        //document.getElementById("mydata").innerHTML = JSON.stringify(data.obj);
                        //console.log(data.obj);
                        component.setState({
                            id: data.obj.id,
                            service: data.obj.service,
                            summary: data.obj.summary,
                            description: data.obj.description,
                            endpoint: data.obj.endpoint,
                            //enable save if url is valid
                            isSubmitting: component.isURL(data.obj.endpoint)
                        });
                        document.querySelector("trix-editor").editor.insertHTML(component.state.description);
                    });
                },
                authorizations : {
                    someHeaderAuth: new SwaggerClient.ApiKeyAuthorization('Authorization', "Bearer " + key, 'header')
                }
            });
        }
        return {
            service: '', description: '', endpoint: '',
            isSubmitting: false
        };
    },
    render: function() {
        var divStyle = {
            minHeight: '300px'
        };
        var delStyle = {background: '#98969E'};
        if(this.props.title === 'Create') delStyle = {display: 'none'};
        this.state.$state=this.props.$state;

        //console.log('sr-update.jsx render(): ', this.props);
        return (
                    <div className="container">
                        <h1>Service Manager - Update</h1><br />
                        <form className="form-horizontal" role="form">
                            <div id="sr-update-table">
                                <div className="control-group">
                                    <label htmlFor="id" className="col-sm-2 control-label">ID:</label>
                                    <input type="text" className="col-sm-10 form-control" ref="id" value={this.state.id} required readOnly />
                                </div>
                                <div className="control-group">
                                    <label htmlFor="title" className="col-sm-2 control-label">Service:</label>
                                    <input type="text" className="col-sm-10 form-control" ref="title" onKeyUp={this.save} required value={this.state.service} ref="service" onChange={function(e){this.setState({service: e.target.value})}.bind(this)} placeholder="Enter any easy to remember service name." />
                                </div>
                                <div className="control-group">
                                    <label htmlFor="endpoint" className="col-sm-2 control-label">URL:</label>
                                    <div className="col-sm-10">
                                        <input type="url" className="form-control" ref="endpoint" onKeyUp={this.save} required value={this.state.endpoint} onChange={function(e){this.setState({endpoint: e.target.value, isSubmitting: this.isURL(e.target.value)})}.bind(this)} onClick={this.save} placeholder="Enter any valid URL e.g. http://www.google.com." />
                                    </div>
                                </div>
                                <div className="control-group">
                                    <label htmlFor="summary" className="col-sm-2 control-label">Summary:</label>
                                    <div className="col-sm-10">
                                        <input type="text" className="form-control" ref="summary" onKeyUp={this.save} value={this.state.summary} onChange={function(e){this.setState({summary: e.target.value})}.bind(this)} />
                                    </div>
                                </div>
                                <div className="control-group">
                                    <label htmlFor="desc" className="col-sm-2 control-label">Description:</label>
                                    <div className="col-sm-10">
                                        <input type="text" className="form-control" ref="descriptionRaw" onKeyUp={this.save} value={this.state.descriptionRaw} onChange={function(e){this.setState({descriptionRaw: e.target.value})}.bind(this)} />
                                        <input id="x" ref="desc" type="hidden" name="content" />
                                        <trix-editor style={divStyle} input="x"></trix-editor>
                                    </div>
                                </div>
                                <div className="control-group">
                                    <label htmlFor="createItem" className="col-sm-2 control-label" />
                                    <div className="col-sm-10">
                                        <input type="hidden" name="_method" defaultValue="POST" />
                                        <input className="form-control btn btn-primary" ref="deleteItem" type="button" style={delStyle} defaultValue="Delete" onClick={() => {if(confirm('Delete the item?')) {this.deleteItem()};}} />
                                        <input className="form-control btn btn-primary" ref="createItem" type="button" style={{background: '#98969E'}} defaultValue="Save" disabled={!this.state.isSubmitting} onClick={this.updateItem} />
                                        <input className="form-control btn" ref="cancelItem" defaultValue="Cancel" type="button" onClick={this.goHome} />
                                    </div>
                                </div>
                            </div>
                        </form>
                    </div>
        )
    },
    goHome: function() {
        //location.href='fusrstart.html';
        this.state.$state.go('home');
    },
    saveNow: function(component) {
        //console.log('createItem() unknown error!');
        var srJson = {
            sr: {
                id: component.state.id,
                service: component.state.service,
                summary: component.state.summary,
                description: component.refs.desc.value,
                endpoint: component.state.endpoint
            }
        };
        //console.log('sr-update.jsx saveNow(): ' + component.state);
        //console.log(srJson.sr);
        swagger.sr.saveSR(srJson, {responseContentType: 'application/json'}, function (data) {
            //document.getElementById("mydata").innerHTML = JSON.stringify(data.obj);
            //console.log(data.obj);
            component.goHome();
        });
    },
    updateItem: function() {
        if(typeof this.props.createItemCallback !== 'undefined') {
            //console.log('SRCreate-SRUpdate ...');
            this.props.createItemCallback(this);
        } else {
            var component = this;
            var key = localStorage.getItem('userJWTToken');
            //console.log('sr-update.jsx updateItem: ' + component.refs.service.value + ' ' + component.state.service);
            window.swagger = new SwaggerClient({
                url: location.origin + "/swagger/swagger.json",
                success: function () {
                    swagger.sr.existsSR_service({service: component.state.service}, {responseContentType: 'application/json'}, function(data) {
                        console.log(data.obj);
                        if(data.obj && typeof data !== 'undefined' && typeof data.obj !== 'undefined') {
                            if(data.obj && data.obj.service != component.state.service) {
                                //hmm...
                            } else {
                                if(data.obj && data.obj.id == component.state.id) {
                                    component.saveNow(component); //if it is the current item we are editing, it is fine to save! :)
                                } else {
                                    status = 'service [' + component.state.service + '] exists!';
                                    alert(status);
                                }
                            }
                        } else {
                            component.saveNow(component);
                        }
                    });
                },
                authorizations: {
                    someHeaderAuth: new SwaggerClient.ApiKeyAuthorization('Authorization', "Bearer " + key, 'header')
                }
            });
            console.log('SRUpdate input saved');
        }
    },
    save: function(e) {
        this.setState({
        //    id: this.state.id,
        //    service: this.refs.service.value,
            description: this.refs.desc.value
        //    endpoint: this.refs.endpoint.value,
        //    isSubmitting: false
        });
        //console.log(this.state);
        var evt = e || window.event
        // "e" is the standard behavior (FF, Chrome, Safari, Opera),
        // while "window.event" (or "event") is IE's behavior
        if ( evt.keyCode === 13 ) {
            this.updateItem();
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
