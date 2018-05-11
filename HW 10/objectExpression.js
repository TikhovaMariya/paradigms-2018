"use strict";

function Const(value) {
    this.value = function () {
        return value;
    };

    this.evaluate = function () {
        return this.value();
    };

    this.toString = function () {
        return this.value().toString();
    };
}

function Variable(name) {
    this.name = function () {
        return name;
    };

    this.evaluate = function () {
        switch (this.name()) {
            case 'x':
                return arguments[0];
            case 'y':
                return arguments[1];
            case 'z':
                return arguments[2];
            default:
                return 0;
        }
    };

    this.toString = function () {
        return this.name();
    };
}

function UnaryOperation(expression) {
    this.expression = function () {
        return expression;
    };

    this.evaluate = function () {
        var ex = this.expression();
        return this.apply(ex.evaluate.apply(ex, arguments));
    };

    this.toString = function () {
        return this.expression() + " " + this.operation();
    }
}

function Negate(expression) {
    Negate.prototype = UnaryOperation;
    UnaryOperation.call(this, expression);

    this.operation = function () {
        return "negate";
    };

    this.apply = function (expression) {
        return -expression;
    };
}

function Square(expression) {
    Square.prototype = UnaryOperation;
    UnaryOperation.call(this, expression);

    this.operation = function () {
        return "square";
    };

    this.apply = function (expression) {
        return expression * expression;
    };
}

function Sqrt(expression) {
    Sqrt.prototype = UnaryOperation;
    UnaryOperation.call(this, expression);

    this.operation = function () {
        return "sqrt";
    };

    this.apply = function (expression) {
        return Math.sqrt(Math.abs(expression));
    };
}

function BinaryOperation(first, second) {
    this.first = function () {
        return first;
    };

    this.second = function () {
        return second;
    };

    this.evaluate = function () {
        var x = this.first();
        var y = this.second();
        return this.apply(x.evaluate.apply(x, arguments), y.evaluate.apply(y, arguments));
    };

    this.toString = function () {
        return this.first().toString() + " " + this.second().toString() + " " + this.symbol();
    }
}

function Add(first, second) {
    Add.prototype = BinaryOperation;
    BinaryOperation.call(this, first, second);

    this.symbol = function () {
        return "+";
    };

    this.apply = function (first, second) {
        return first + second;
    };
}


function Subtract(first, second) {
    Subtract.prototype = BinaryOperation;
    BinaryOperation.call(this, first, second);

    this.symbol = function () {
        return "-";
    };

    this.apply = function (first, second) {
        return first - second;
    };
}

function Multiply(first, second) {
    Multiply.prototype = BinaryOperation;
    BinaryOperation.call(this, first, second);

    this.symbol = function () {
        return "*";
    };

    this.apply = function (first, second) {
        return first * second;
    };
}

function Divide(first, second) {
    Divide.prototype = BinaryOperation;
    BinaryOperation.call(this, first, second);

    this.symbol = function () {
        return "/";
    };

    this.apply = function (first, second) {
        return first / second;
    };
}

function Power(first, second) {
    Power.prototype = BinaryOperation;
    BinaryOperation.call(this, first, second);

    this.symbol = function () {
        return "pow";
    };

    this.apply = function (a, b) {
        return Math.pow(a, b);
    };
}

function Log(first, second) {
    Log.prototype = BinaryOperation;
    BinaryOperation.call(this, first, second);

    this.symbol = function () {
        return "log";
    };

    this.apply = function (a, b) {
        return Math.log(Math.abs(b)) / Math.log(Math.abs(a));
    };
}
