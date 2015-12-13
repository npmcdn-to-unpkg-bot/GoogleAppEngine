var SRStart = React.createClass({
  render: function() {
    return (

      <table className="t-data-grid">
        <thead>
          <tr>
            <th className="id t-first"><a rel="nofollow" href="/sci/serviceregistrystart.landscapesrgrid.columns:sort/id">Id</a><a rel="nofollow" href="/sci/serviceregistrystart.landscapesrgrid.columns:sort/id" /></th>
            <th className="service"><a rel="nofollow" href="/sci/serviceregistrystart.landscapesrgrid.columns:sort/service">SERV</a><a rel="nofollow" href="/sci/serviceregistrystart.landscapesrgrid.columns:sort/service" /></th>
            <th className="description"><a rel="nofollow" href="/sci/serviceregistrystart.landscapesrgrid.columns:sort/description">Description</a><a rel="nofollow" href="/sci/serviceregistrystart.landscapesrgrid.columns:sort/description" />
            </th>
            <th className="shortUrl"><a rel="nofollow" href="/sci/serviceregistrystart.landscapesrgrid.columns:sort/shortUrl">S
                URL</a><a rel="nofollow" href="/sci/serviceregistrystart.landscapesrgrid.columns:sort/shortUrl" /></th>
            <th className="lastAccessed"><a rel="nofollow" href="/sci/serviceregistrystart.landscapesrgrid.columns:sort/lastAccessed">VIS</a><a rel="nofollow" href="/sci/serviceregistrystart.landscapesrgrid.columns:sort/lastAccessed" />
            </th>
            <th className="lastUpdated t-sort-column-descending"><a className="t-sort-column-descending" rel="nofollow" href="/sci/serviceregistrystart.landscapesrgrid.columns:sort/lastUpdated">UPT</a><a className="t-sort-column-descending" rel="nofollow" href="/sci/serviceregistrystart.landscapesrgrid.columns:sort/lastUpdated" /></th>
            <th className="endpoint"><a rel="nofollow" href="/sci/serviceregistrystart.landscapesrgrid.columns:sort/endpoint">Endpoint</a><a rel="nofollow" href="/sci/serviceregistrystart.landscapesrgrid.columns:sort/endpoint" /></th>
            <th className="hit"><a rel="nofollow" href="/sci/serviceregistrystart.landscapesrgrid.columns:sort/hit">Hit</a><a rel="nofollow" href="/sci/serviceregistrystart.landscapesrgrid.columns:sort/hit" /></th>
            <th className="delete t-last">ACT</th>
          </tr>
        </thead>
        <tbody>
          <tr className="myRowClass t-first t-last">
            <td className="id"><a href="/sci/serviceregistrysave/6588273673633792">6588273673633792</a></td>
            <td className="service"><a href="http://cloudiservice.appspot.com/go/h?incog=true" target="_new">h</a>
            </td>
            <td className="description"><span style={{width: 200}}>test</span>test
            </td>
            <td className="shortUrl">&nbsp;</td>
            <td className="lastAccessed">
              N/A
            </td>
            <td className="lastUpdated t-sort-column-descending">
              moments ago
            </td>
            <td className="endpoint"><a href="h" target="_new">h</a></td>
            <td className="hit">-1</td>
            <td className="delete">
              <a id="delete" onclick="return confirm('OK to delete this item?');" href="/sci/serviceregistrystart.delete/6588273673633792">Delete</a>
            </td>
          </tr>
        </tbody>
      </table>
    );
  }
});

React.render(<SRStart />, document.getElementById('sr-start'))
