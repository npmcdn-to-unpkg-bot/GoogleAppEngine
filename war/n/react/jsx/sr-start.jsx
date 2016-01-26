var key = localStorage.getItem('userJWTToken');
var RouterMixin = ReactMiniRouter.RouterMixin;
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
  mixins: [RouterMixin],
  routes: {
    '/': 'start',
    '/fusrcreate': 'fusrcreate',
    '/message/:text': 'message'
  },
  start: function() {
    var indents = [];
    var objects = this.props.items;
    //console.log(this.props);
    for (var i=0; i < objects.length; i++) {
      indents.push(<tr key={i}><td><a href={'fusrupdate.html?id='+objects[i].id}>{objects[i].id}</a></td><td><a target="_new" href={location.origin+'/go/'+objects[i].service+'?incog=true'}>{objects[i].service}</a></td><td dangerouslySetInnerHTML={{__html: objects[i].description}} /><td>{objects[i].url}</td><td>{objects[i].lastAccessed}</td><td>{objects[i].lastUpdated}</td><td><a target="_new" href={objects[i].endpoint}>{objects[i].endpoint}</a></td><td>{objects[i].hit}</td></tr>);
    }
    return (
        <div>
          //fusrcreate.html?id=0
          <h4><a href="/fusrcreate">Create New</a></h4>
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
        </div>
    );
  },
  render: function() {
    return this.renderCurrentRoute();
  },
  fusrcreate: function() {
    return <div>Hello World</div>;
  },
  message: function(text) {
    return <div>{text}</div>;
  },
  notFound: function(path) {
    return <div class="not-found">Page Not Found: {path}</div>;
  }
});

//ReactDOM.render(<SRUpdate items={ data.content }/>, document.getElementById('sr-start'))