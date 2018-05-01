"use strict"

function makeOp(op) {
    return function () {
        var args = arguments;
        return function () {
            var res = [];
            for (var i = 0; i < args.length; ++i) {
                res.push(args[i].apply(null, arguments));
            }
            return op.apply(null, res);
        }
    }
}

var cnst = function (a) {
    return function() {
        return a;
    }
};

var variable = function (a) {
    return function() {
        switch (a) {
            case 'x':
                return arguments[0];
            case 'y':
                return arguments[1];
            case 'z':
                return arguments[2];
            default:
                return 0;
        }
    }
};

var add = makeOp(function (a, b) {
    return a + b;
});

var subtract = makeOp(function (a, b) {
    return a - b;
});


var multiply = makeOp(function (a, b) {
    return a * b;
});

var divide = makeOp(function (a, b) {
    return a / b;
});

var negate = makeOp(function (a) {
    return -a;
});

var cube = makeOp(function (a) {
    return Math.pow(a, 3);
});

var cuberoot = makeOp(function (a) {
    return Math.pow(a, 1/3);
});

