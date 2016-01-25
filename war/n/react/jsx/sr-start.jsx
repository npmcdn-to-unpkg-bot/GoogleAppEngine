var key = localStorage.getItem('userJWTToken');
//console.log('sr-create.jsx: [' + key + '] location.origin [' + location.origin + '] mockup url [' + handleSSL('http://mockup.com') + ']');
window.swagger = new SwaggerClient({
  url: location.origin + "/swagger/swagger.json",
  success: function() {
    swagger.sr.all({},{responseContentType: 'application/json'}, function(data) {
      //document.getElementById("mydata").innerHTML = JSON.stringify(data.obj);
      ReactDOM.render(<SRStart items={ data.obj.content }/>, document.getElementById('sr-start'));
      $('#sr-start-table').stacktable();
    });
  },
  authorizations : {
    someHeaderAuth: new SwaggerClient.ApiKeyAuthorization('Authorization', "Bearer " + key, 'header')
  }
});
var SRStart = React.createClass({
  render: function() {
    var indents = [];
    var objects = this.props.items;
    //console.log(this.props);
    for (var i=0; i < objects.length; i++) {
      indents.push(<tr key={i}><td><a href={'fusrupdate.html?id='+objects[i].id}>{objects[i].id}</a></td><td><a target="_new" href={location.origin+'/go/'+objects[i].service+'?incog=true'}>{objects[i].service}</a></td><td dangerouslySetInnerHTML={{__html: objects[i].description}} /><td>{objects[i].url}</td><td>{objects[i].lastAccessed}</td><td>{objects[i].lastUpdated}</td><td><a target="_new" href={objects[i].endpoint}>{objects[i].endpoint}</a></td><td>{objects[i].hit}</td></tr>);
    }
    return (
        <div className="table-responsive">
          <table id="sr-start-table" className="table table-striped table-bordered">
            <thead>
            <tr><td>ID</td><td>SERV</td><td>DESCRIPTION</td><td>S URL</td><td>VIS</td><td>UPT</td><td>ENDPOINT</td><td>HIT</td></tr>
            </thead>
            <tbody>
              {indents}
            </tbody>
          </table>
        </div>
    );
  }
});

//ReactDOM.render(<SRUpdate items={ data.content }/>, document.getElementById('sr-start'))