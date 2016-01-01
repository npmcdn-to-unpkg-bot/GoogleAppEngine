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
            }
        });
        console.log('SRCreate invoked');
    }
});

React.render(<SRCreate />, document.getElementById('sr-create'));
