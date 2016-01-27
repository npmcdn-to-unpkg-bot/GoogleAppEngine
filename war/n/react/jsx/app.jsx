var Router = window.ReactRouter.Router;
var Route = window.ReactRouter.Route;
var Link = window.ReactRouter.Link;
var browserHistory = window.ReactRouter.browserHistory;
var History = window.ReactRouter.History;
var NoMatch = window.ReactRouter.NoMatch;

var SRStartWrapper = React.createClass({
    render: function () {
        return (
            <SRStart router={router} />
        );
    }
});

var router = (
    <Router history={browserHistory}>
        <Route path="/" component={SRStartWrapper}>
            <Route path="fusrcreate" component={SRCreate}/>
            <Route path="*" component={NoMatch}/>
        </Route>
    </Router>
);

var App = React.createClass({
    render: function() {
        return (
            router
        );
    }
});

ReactDOM.render(<App />, document.getElementById('sr-start'));
//export router;