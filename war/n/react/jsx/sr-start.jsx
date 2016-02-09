var key = localStorage.getItem('userJWTToken');

const ACTIVE = { color: 'red' };

var SRStart = React.createClass({
  //mixins: [RouterMixin],
  //routes: {
  //  '/': 'start',
  //  '/fusrcreate': 'fusrcreate',
  //  '/message/:text': 'message'
  //},
  //childContextTypes: {
  //  router: React.PropTypes.func
  //},
  //getChildContext () {
  //  return {
  //    router: router
  //  }
  //},
  //propTypes: {
  //  $state: React.PropTypes.func.required
  //},
  //start: function() {
  getInitialState: function() {
    var component = this;
    var items = {processingMessage: 'Rendering ...'};
    //console.log('sr-start.jsx getInitialState(): handleSSL(location.origin) = [' + handleSSL(location.origin) + ']');
    window.swagger = new SwaggerClient({
      url: location.origin + "/swagger/swagger.json",
      success: function() {
        swagger.sr.all({},{responseContentType: 'application/json'}, function(data) {
          //document.getElementById("mydata").innerHTML = JSON.stringify(data.obj);
          component.state.items = data.obj.content;
          component.state.processingMessage = "";   //done!
          ReactDOM.render(<SRStart $state={window.$state} items={ data.obj.content }/>, document.getElementById('sr-start'));
          $('#sr-start-table').stacktable();
          ReactDOM.render(<AppAutocomplete/>, document.getElementById('app-autocomplete'));
        });
      },
      authorizations : {
        someHeaderAuth: new SwaggerClient.ApiKeyAuthorization('Authorization', "Bearer " + key, 'header')
      }
    });

    return items;
  },
  goUpdate: function(id, e) {
    var obj = {
      id: id
    }
    //console.log('sr-start.jsx goUpdate: ', id, this.state);
    if(typeof this.state.$state === 'undefined') this.state.$state = window.$state;  //TODO workaround until this bug is fixed! :(
    this.state.$state.go('update', {obj: obj});
  },
  render: function() {
    var indents = [];
    var objects = this.state.items;
    if(typeof this.props.$state !== 'undefined') {
      this.state.$state = this.props.$state;
      //var newState = React.addons.update(this.state.$state, {
      //    $set : this.props.$state
      //});
      //this.setState(newState);
      //console.log('sr-start.jsx render(): this.state.$state [' + this.state.$state + ']');
    }
    if(objects) {
      var boundItemClick;
      for (var i = 0; i < objects.length; i++) {
        boundItemClick = this.goUpdate.bind(this, objects[i].id);

        indents.push(<tr key={i}>

          <td><a href={'#/update/' + objects[i].id}>{objects[i].id}</a></td>
          {/* <td><a onClick={boundItemClick}>{objects[i].id}</a></td> Uncaught Invariant Violation: ReactMount: Two valid but unequal nodes with the same `data-reactid`: .0.1.0.1.$0.0.0invariant */}
          <td><a target="_new" href={location.origin+'/go/'+objects[i].service+'?incog=true'}>{objects[i].service}</a>
          </td>
          <td dangerouslySetInnerHTML={{__html: objects[i].description}}/>
          <td>{objects[i].url}</td>
          <td>{objects[i].lastAccessed}</td>
          <td>{objects[i].lastUpdated}</td>
          <td><a target="_new" href={objects[i].endpoint}>{objects[i].endpoint}</a></td>
          <td>{objects[i].hit}</td>
        </tr>);
      }
    }
    return (
        <div>
          <div id="popup"></div>
          <h4 className="clearfix">
            <a className="pull-left button" href="#/create">Create New</a>
            <div className="pull-right input-group">
              <span className="pull-right input-group-btn" id="icon-clear">
                  <div className="btn btn-clear glyphicon glyphicon-remove" type="button" onClick={this.clearSearch}></div>
              </span>
              <div className="pull-right" id="app-autocomplete" />
            </div>
          </h4>
          {/* <h4><Link {...this.props} to="/fusrcreate" activeStyle={ACTIVE}>Create New</Link></h4> */}
          <div className="table-responsive">
            <table id="sr-start-table" className="table table-striped table-bordered">
              <thead>
              <tr>
                <td>ID</td>
                <td>SERV</td>
                <td>DESCRIPTION</td>
                <td>S URL</td>
                <td>VIS</td>
                <td>UPT</td>
                <td>ENDPOINT</td>
                <td>HIT</td>
              </tr>
              </thead>
              <tbody>
              {indents}
              </tbody>
            </table>
            <div>{this.state.processingMessage}</div>
          </div>
        </div>
    );
  }
  ,
  clearSearch: function() {
        ReactDOM.render(<AppAutocomplete clear />, document.getElementById('app-autocomplete'));

        console.log('sr-start.jsx clearSearch()');
  }
  //,
  //render: function() {
    //return this.renderCurrentRoute();
  //}
  //,
  //fusrcreate: function() {
  //  return <SRCreate />;
  //},
  //message: function(text) {
  //  return <div>{text}</div>;
  //},
  //notFound: function(path) {
  //  return <div class="not-found">Page Not Found: {path}</div>;
  //}
});

ReactDOM.render(<SRStart $state />, document.getElementById('sr-start'));