"use strict";

var styles = {
    item: {
        padding: '2px 6px',
        cursor: 'default'
    },

    highlightedItem: {
        color: 'white',
        background: 'hsl(250, 50%, 50%)',
        padding: '2px 6px',
        cursor: 'default'
    },

    menu: {
        border: 'solid 1px #ccc'
    }
};

var _extends = Object.assign || function (target) { for (var i = 1; i < arguments.length; i++) { var source = arguments[i]; for (var key in source) { if (Object.prototype.hasOwnProperty.call(source, key)) { target[key] = source[key]; } } } return target; };

var AppAutocomplete = React.createClass({
    displayName: 'App',

    getInitialState: function getInitialState() {
        return {
            unitedStates: getStates(),
            loading: false
        };
    },

    render: function render() {
        var _this = this;

        return React.createElement(
            'div',
            null,
            React.createElement(Autocomplete, {
                items: this.state.unitedStates,
                getItemValue: function (item) {
                    return item.name;
                },
                onSelect: function () {
                    return _this.setState({ unitedStates: [] });
                },
                onChange: function (event, value) {
//                    console.log('app-autocomplete.js onChange(): value [' + value + ']');
                    _this.setState({ loading: true });
                    fakeRequest(value, function (items) {
                        _this.setState({ unitedStates: items, loading: false });
                    });
                },
                renderItem: function (item, isHighlighted) {
                    return React.createElement(
                        'div',
                        {
                            style: isHighlighted ? styles.highlightedItem : styles.item,
                            key: item.abbr,
                            id: item.abbr
                        },
                        item.name
                    );
                },
                renderMenu: function (items, value, style) {
                    return React.createElement(
                        'div',
                        { style: _extends({}, styles.menu, style) },
                        value === '' ? React.createElement(
                            'div',
                            { style: { padding: 6 } },
                            'Type of the name of a United State'
                        ) : _this.state.loading ? React.createElement(
                            'div',
                            { style: { padding: 6 } },
                            'Loading...'
                        ) : items.length === 0 ? React.createElement(
                            'div',
                            { style: { padding: 6 } },
                            'No matches for ',
                            value
                        ) : _this.renderItems(items)
                    );
                }
            })
        );
    },

    renderItems: function renderItems(items) {
        //console.log(items);
        return items.map(function (item, index) {
            var text = item.props.children;
            if (index === 0 || items[index - 1].props.children.charAt(0) !== text.charAt(0)) {
                var style = {
                    background: '#eee',
                    color: '#454545',
                    padding: '2px 6px',
                    fontWeight: 'bold'
                };
                return [React.createElement(
                    'div',
                    { style: style },
                    text.charAt(0)
                ), item];
            } else {
                return item;
            }
        });
    }
});

var searchedResults = [];
var arr = [];
var r = {};
function getES(term) {
    $.get('https://es-n3t.rhcloud.com/service_registry/_search?size=100&pretty&q='+term, function(result) {
        if(typeof result !== 'undefined') {
            //console.log('app-autocomplete.js getES(): term [' + term + '] result [' + result.hits.total + ']');
            r = result.hits.hits;
            //console.log(r);
            var h;
            for(var i = 0; i<r.length; i++) {
                h = r[i];
                arr.push(h._source);
            }
            searchedResults = arr.reduce(function(all, item, index) {
                all.push({abbr: item.service, name: item.summary});
                return all;
            }, []);
        }
    });
}

function getStates(value) {
    getES(value);

    //TODO to use JavaScript reduce to convert ES results into the following format! :p

    return searchedResults;     //[
//        {abbr: "AL", name: "Alabama"},
//        {abbr: "AK", name: "Alaska"},
//        {abbr: "AZ", name: "Arizona"},
//        {abbr: "AR", name: "Arkansas"},
//        {abbr: "CA", name: "California"},
//        {abbr: "CO", name: "Colorado"},
//        {abbr: "CT", name: "Connecticut"},
//        {abbr: "DE", name: "Delaware"},
//        {abbr: "FL", name: "Florida"},
//        {abbr: "GA", name: "Georgia"},
//        {abbr: "HI", name: "Hawaii"},
//        {abbr: "ID", name: "Idaho"},
//        {abbr: "IL", name: "Illinois"},
//        {abbr: "IN", name: "Indiana"},
//        {abbr: "IA", name: "Iowa"},
//        {abbr: "KS", name: "Kansas"},
//        {abbr: "KY", name: "Kentucky"},
//        {abbr: "LA", name: "Louisiana"},
//        {abbr: "ME", name: "Maine"},
//        {abbr: "MD", name: "Maryland"},
//        {abbr: "MA", name: "Massachusetts"},
//        {abbr: "MI", name: "Michigan"},
//        {abbr: "MN", name: "Minnesota"},
//        {abbr: "MS", name: "Mississippi"},
//        {abbr: "MO", name: "Missouri"},
//        {abbr: "MT", name: "Montana"},
//        {abbr: "NE", name: "Nebraska"},
//        {abbr: "NV", name: "Nevada"},
//        {abbr: "NH", name: "New Hampshire"},
//        {abbr: "NJ", name: "New Jersey"},
//        {abbr: "NM", name: "New Mexico"},
//        {abbr: "NY", name: "New York"},
//        {abbr: "NC", name: "North Carolina"},
//        {abbr: "ND", name: "North Dakota"},
//        {abbr: "OH", name: "Ohio"},
//        {abbr: "OK", name: "Oklahoma"},
//        {abbr: "OR", name: "Oregon"},
//        {abbr: "PA", name: "Pennsylvania"},
//        {abbr: "RI", name: "Rhode Island"},
//        {abbr: "SC", name: "South Carolina"},
//        {abbr: "SD", name: "South Dakota"},
//        {abbr: "TN", name: "Tennessee"},
//        {abbr: "TX", name: "Texas"},
//        {abbr: "UT", name: "Utah"},
//        {abbr: "VT", name: "Vermont"},
//        {abbr: "VA", name: "Virginia"},
//        {abbr: "WA", name: "Washington"},
//        {abbr: "WV", name: "West Virginia"},
//        {abbr: "WI", name: "Wisconsin"},
//        {abbr: "WY", name: "Wyoming"}
//    ];
}

function matchStateToTerm(state, value) {
    return (
        state.name.toLowerCase().indexOf(value.toLowerCase()) !== -1 ||
        state.abbr.toLowerCase().indexOf(value.toLowerCase()) !== -1
    )
}

function sortStates(a, b, value) {
    return (
        a.name.toLowerCase().indexOf(value.toLowerCase()) >
        b.name.toLowerCase().indexOf(value.toLowerCase()) ? 1 : -1
    )
}

function fakeRequest(value, cb) {
    if (value === '') return getStates(value);
    var items = getStates(value).filter(function (state) {
        return matchStateToTerm(state, value);
    });
    setTimeout(function () {
        cb(items);
    }, 500);
}

//ReactDOM.render(React.createElement(AppAutocomplete, null), document.getElementById('app-autocomplete'));