var Editor = Draft.Editor;
var SRUpdate = React.createClass({
    propTypes: {
        id: React.PropTypes.number,
        service: React.PropTypes.string,
        description: React.PropTypes.string,
        endpoint: function (props, propName, component) {
            if (typeof props[propName] !== 'undefined' && !component.isURL(props[propName])) {
                return new Error('Invalid URL!');
            }
        },
        category: React.PropTypes.oneOf(this.getCategories),
        useDescription: React.PropTypes.bool,
        useHtml: React.PropTypes.bool,
        hit: React.PropTypes.number,
        disabled: React.PropTypes.bool
    },
    getCategories: function() {
        return ['TECHNOLOGY','RELATIONSHIP','INCOMING','OUTGOING','SCIENCE','BUSINESS','WORLD','SPORTS','ENTERTAINMENT','HEALTH','POLITICS','SOCIETY','GOVERNMENT','CODE','AUDIO','VIDEO',
            'IMAGE','GAME','DALEKJS','PROTRACTOR'];
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
        var component = this;
        var qs = URI(location.href).query(true); // == e.g. { id : 4529987906437120 }
        if(typeof qs.id === 'undefined') {
            if(typeof this.props.id !== 'undefined') {
                qs.id = this.props.id;  //TODO anti-pattern to refer to props in here???
            } else {
                qs.id = 0;
            }
        }
        if(qs.id > 0) {
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
                            category: data.obj.category,
                            useDescription: data.obj.useDescription,
                            useHtml: data.obj.useHtml,
                            hit: data.obj.hit,
                            disabled: data.obj.disabled,
                            //enable save if url is valid
                            isSubmitting: component.isURL(data.obj.endpoint)
                        });
                        document.querySelector("trix-editor").editor.insertHTML(component.state.description);
                    });
                    ReactDOM.render(<AppAutocomplete/>, document.getElementById('app-autocomplete'));
                },
                authorizations : {
                    someHeaderAuth: new SwaggerClient.ApiKeyAuthorization('Authorization', "Bearer " + key, 'header')
                }
            });
        }

//        this.state = { editorState: Draft.EditorState.createEmpty() };
        this.onChange = function (editorState) {
          return component.setState({ editorState: editorState });
        };

        return {
            editorState: Draft.EditorState.createEmpty(),
            service: '', description: '', endpoint: '',
            isSubmitting: false
        };
    },
    onChangeDescription() {
        this.setState({useDescription: !this.state.useDescription});
    },
    onChangeHtml() {
        this.setState({useHtml: !this.state.useHtml});
    },
    onChangeDisabled() {
        this.setState({disabled: !this.state.disabled});
    },
    render: function() {
        var editorState = this.state.editorState;

        var editorStyle = {
            minHeight: '300px',
            marginBottom: '15px'
        };
        var categoryStyle = {
            marginBottom: '25px'
        };
        var delStyle = {background: '#98969E'};
        if(this.props.title === 'Create') delStyle = {display: 'none'};
        this.state.$state=this.props.$state;    //TODO this is actually an anti-pattern!
        var options = [];
        var cat = this.getCategories();
        for (var i = 0; i < cat.length; i++) {
            options.push(<option key={i} value={cat[i]}>{cat[i]}</option>);
        }
        //console.log('sr-update.jsx render(): ', this.props);
        return (
                    <div className="container">
                        <h3 className="clearfix">
                            <div className="pull-left" >Service Manager - Update</div>
                        </h3>

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
                                        <Editor ref="desc" editorState={editorState} onChange={this.onChange} />
                                    </div>
                                </div>
                                <div className="control-group">
                                    <label htmlFor="category" className="col-sm-2 control-label">Category:</label>
                                    <div className="col-sm-10" style={categoryStyle}>
                                        <select className="form-control" onChange={function(e){this.setState({category: e.target.value})}.bind(this)} ref="category" value={this.state.category}>
                                            {options}
                                        </select>
                                    </div>
                                    <div className="col-sm-10">
                                    <label htmlFor="useDescription" >Use the Content:</label>
                                    <input className="form-control" type="checkbox"
                                           name="useDescription"
                                           checked={this.state.useDescription}
                                           onChange={this.onChangeDescription}
                                    />
                                    <label htmlFor="useHtml" >Save as HTML:</label>
                                        <input className="form-control" type="checkbox"
                                               name="useHtml"
                                               checked={this.state.useHtml}
                                               onChange={this.onChangeHtml}
                                               />
                                    <label htmlFor="hit" >Hit:</label>
                                        <input type="text" className="form-control" ref="hit" onKeyUp={this.save} value={this.state.hit} onChange={function(e){this.setState({hit: e.target.value})}.bind(this)} />
                                    <label htmlFor="disabled" >Disabled:</label>
                                        <input className="form-control" type="checkbox"
                                               name="disabled"
                                               checked={this.state.disabled}
                                               onChange={this.onChangeDisabled}
                                               />
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
        //console.log('saveNow() component.state.disabled [' + component.state.disabled + ']');
        //console.log('saveNow() component.state.useDescription [' + component.state.useDescription + ']');
        var srJson = {
            sr: {
                id: component.state.id,
                service: component.state.service,
                summary: component.state.summary,
                description: component.refs.desc.value,
                endpoint: component.state.endpoint,
                category: component.state.category,
                useDescription: component.state.useDescription,
                useHtml: component.state.useHtml,
                hit: component.state.hit,
                disabled: component.state.disabled
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
                                    //status = 'service [' + component.state.service + '] exists!';
                                    //alert(status);
                                    component.replaceItem(component, data.obj);
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
        var evt = e || window.event;
        // "e" is the standard behavior (FF, Chrome, Safari, Opera),
        // while "window.event" (or "event") is IE's behavior
        if ( evt.keyCode === 13 ) {
            this.updateItem();
            // You can disable the form submission this way:
            return false
        }
    },
    replaceItem: function (component, json) {
        swagger.sr.existsSR_service({service: component.state.service}, {responseContentType: 'application/json'}, function(data) {
            console.log(data.obj);
            if(data.obj && typeof data !== 'undefined' && typeof data.obj !== 'undefined') {
                if(data.obj && data.obj.service != component.state.service) {
                    //hmm...
                } else {
                    //yes or no?
                    if(confirm('Replace the current item? If you choose Ok, you will loose the changes if any!')) {
                        //replace it with the one found if yes
                        component.state.id = json.id;
                        component.state.service = json.service;
                        component.state.summary = json.summary;
                        component.state.desc = json.description;
                        component.state.endpoint = json.endpoint;
                    }
                }
            } else {
                component.saveNow(component);
            }
        });
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
