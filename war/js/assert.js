"use strict";

function assert(expression, message) {
    try { console.assert(expression, message); } catch (e) { alert(message); }
}