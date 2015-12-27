(function e(t,n,r){function s(o,u){if(!n[o]){if(!t[o]){var a=typeof require=="function"&&require;if(!u&&a)return a(o,!0);if(i)return i(o,!0);var f=new Error("Cannot find module '"+o+"'");throw f.code="MODULE_NOT_FOUND",f}var l=n[o]={exports:{}};t[o][0].call(l.exports,function(e){var n=t[o][1][e];return s(n?n:e)},l,l.exports,e,t,n,r)}return n[o].exports}var i=typeof require=="function"&&require;for(var o=0;o<r.length;o++)s(r[o]);return s})({1:[function(require,module,exports){
var APP = React.createClass({
    displayName: 'APP',

    render: function () {
        return React.createElement(
            'h1',
            null,
            'Hello World'
        );
    }
});

//React.render(<APP />, document.body);
React.render(React.createElement(APP, null), document.getElementById('hello'));

},{}],2:[function(require,module,exports){
var SRStart = React.createClass({
  displayName: "SRStart",

  render: function () {
    return React.createElement(
      "table",
      { className: "table table-striped table-bordered" },
      React.createElement(
        "thead",
        null,
        React.createElement(
          "tr",
          null,
          React.createElement(
            "th",
            null,
            "th is 0"
          ),
          React.createElement(
            "th",
            null,
            "th is 1"
          ),
          React.createElement(
            "th",
            null,
            "th is 2"
          ),
          React.createElement(
            "th",
            null,
            "th is 3"
          ),
          React.createElement(
            "th",
            null,
            "th is 4"
          ),
          React.createElement(
            "th",
            null,
            "th is 5"
          )
        )
      ),
      React.createElement(
        "tbody",
        null,
        React.createElement(
          "tr",
          null,
          React.createElement(
            "td",
            null,
            "cell is row 0, column 0"
          ),
          React.createElement(
            "td",
            null,
            "cell is row 0, column 1"
          ),
          React.createElement(
            "td",
            null,
            "cell is row 0, column 2"
          ),
          React.createElement(
            "td",
            null,
            "cell is row 0, column 3"
          ),
          React.createElement(
            "td",
            null,
            "cell is row 0, column 4"
          ),
          React.createElement(
            "td",
            null,
            "cell is row 0, column 5"
          )
        ),
        React.createElement(
          "tr",
          null,
          React.createElement(
            "td",
            null,
            "cell is row 1, column 0"
          ),
          React.createElement(
            "td",
            null,
            "cell is row 1, column 1"
          ),
          React.createElement(
            "td",
            null,
            "cell is row 1, column 2"
          ),
          React.createElement(
            "td",
            null,
            "cell is row 1, column 3"
          ),
          React.createElement(
            "td",
            null,
            "cell is row 1, column 4"
          ),
          React.createElement(
            "td",
            null,
            "cell is row 1, column 5"
          )
        ),
        React.createElement(
          "tr",
          null,
          React.createElement(
            "td",
            null,
            "cell is row 2, column 0"
          ),
          React.createElement(
            "td",
            null,
            "cell is row 2, column 1"
          ),
          React.createElement(
            "td",
            null,
            "cell is row 2, column 2"
          ),
          React.createElement(
            "td",
            null,
            "cell is row 2, column 3"
          ),
          React.createElement(
            "td",
            null,
            "cell is row 2, column 4"
          ),
          React.createElement(
            "td",
            null,
            "cell is row 2, column 5"
          )
        )
      )
    );
  }
});

React.render(React.createElement(SRStart, null), document.getElementById('sr-start'));

},{}]},{},[1,2]);
