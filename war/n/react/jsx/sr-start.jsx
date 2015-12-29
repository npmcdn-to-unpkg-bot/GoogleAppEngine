var SRStart = React.createClass({
  render: function() {
    var indents = [];
    for (var i=0; i < this.props.list.length; i++) {
      indents.push(<tr><td><a href="fusrupdate.html?id={objects[i].id}">{objects[i].id}</a></td><td>{objects[i].service}</td><td>{objects[i].description}</td><td>{objects[i].url}</td><td>{objects[i].lastAccessed}</td><td>{objects[i].lastUpdated}</td><td>{objects[i].endpoint}</td><td>{objects[i].hit}</td></tr>);
    }
    return (
        <div className="table-responsive">
          <table className="table table-striped table-bordered">
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

React.render(<SRStart />, document.getElementById('sr-start'))
