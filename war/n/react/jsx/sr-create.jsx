var SRCreate = React.createClass({
    render: function() {
        return (
            <SRUpdate />
        )
    },
    goHome: function() {
        location.href='fusrstart.html';
    },
    createItem: function() {
        var component = this;
        var key = localStorage.getItem('userJWTToken');
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
                    component.goHome();
                });
            },
            authorizations : {
                someHeaderAuth: new SwaggerClient.ApiKeyAuthorization('Authorization', "Bearer " + key, 'header')
            }
        });
        console.log('SRCreate invoked');
    }
});

ReactDOM.render(<SRCreate />, document.getElementById('sr-create'));
