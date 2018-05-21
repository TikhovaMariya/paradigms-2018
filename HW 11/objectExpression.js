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

    this.prefix = this.toString;
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

    this.prefix = this.toString;
}

// Unary Operations

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

    this.prefix = function () {
        return "(" + this.operation() + " " + this.expression().prefix() + ")";
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

function ArcTan(expression) {
    ArcTan.prototype = UnaryOperation;
    UnaryOperation.call(this, expression);

    this.operation = function () {
        return "atan";
    };

    this.apply = function (expression) {
        return Math.atan(expression);
    };
}

function Exp(expression) {
    Exp.prototype = UnaryOperation;
    UnaryOperation.call(this, expression);

    this.operation = function () {
        return "exp";
    };

    this.apply = function (expression) {
        return Math.exp(expression);
    };
}

// Binary Operations

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
    };

    this.prefix = function () {
        return "(" + this.symbol() + " " + this.first().prefix() + " " + this.second().prefix() + ")";
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

// Exceptions

function IndexedException(message, index) {
    this.message = message + " at index " + index.toString();
}
IndexedException.prototype = Object.create(Error.prototype);

function UnknownVariable(name, index) {
    var m = "Unknown variable " + name;
    return new IndexedException(m, index);
}

function UnknownOperation(name, index) {
    var m = "Unknown operation " + name;
    return new IndexedException(m, index);
}

function ValueInsteadOfOperation(found, value) {
    var m = "Expected operation, found " + found + " \"" + value + "\"";
    return new IndexedException(m, index);
}

function IncorrectArgumentsAmmount(expected, found) {
    this.message = "Expected " + expected + " arguments, found " + found;
}
IncorrectArgumentsAmmount.prototype = Object.create(Error.prototype);

function ValueExpected(found, index) {
    var m = "Expected value, found " + found;
    return new IndexedException(m, index);
}

// Parse Prefix

var index;
var expression;
var brace_balance = 0;

function getFunction(x) {
    switch (x) {
        case "+":
            return Add;
        case "-":
            return Subtract;
        case "*":
            return Multiply;
        case "/":
            return Divide;
        case "power":
            return Power;
        case "log":
            return Log;
        case "atan":
            return ArcTan;
        case "exp":
            return Exp;
        case "square":
            return Square;
        case "sqrt":
            return Sqrt;
        case "negate":
            return Negate;
        default:
            throw new UnknownOperation(x, index - x.length);
    }
}

function getArgCount(x) {
    switch (x) {
        case "+":
        case "-":
        case "*":
        case "/":
        case "power":
        case "log":
            return 2;
        case "negate":
        case "atan":
        case "exp":
        case "square":
        case "sqrt":
            return 1;
    }
    throw new UnknownOperation(x, index - x.length);
}

function isLetter(symbol) {
    return symbol >= "a" && symbol <= "z" || symbol >= "A" && symbol <= "Z";
}

function isName(name) {
    return name === "x" || name === "y" || name === "z";
}

function isUnaryOperation(x) {
    return x === "negate" || x === "log" || x === "sqrt" || x === "square" || x === "atan" || x === "exp";
}

function skipWhitespaces() {
    while (index < expression.length() && /\s/.test(expression.charAt(index))) {
        index++;
    }
}

function parseValue() {
    var start = index;
    while (index < expression.length() && !isNaN(expression.charAt(index)) && !/\s/.test(expression.charAt(index))) {
        index++;
    }
    return expression.substring(start, index);
}

function parseText() {
    var start = index;
    while (index < expression.length() && isLetter(expression.charAt(index))) {
        index++;
    }
    return expression.substring(start, index);
}

function cutToken() {
    var start = index;
    while (index < expression.length() && !/\s/.test(expression.charAt(index))) {
        index++;
    }
    return expression.substring(start, index);
}

function getToken() {
    skipWhitespaces();
    if (index < expression.length) {
        var ch = expression.charAt(index);
        switch (ch) {
            case "-":
                index++;
                if (!/\s/.test(expression.charAt(index)) && !isNaN(expression.charAt(index))) {
                    return parseInt("-" + parseValue());
                }
                if (isLetter(expression.charAt(index))) {
                    var x = cutToken();
                    throw new IndexedException("Invalid number " + x, index - x.length);
                }
                return ch;
            case "+":
            case "/":
            case "*":
            case "(":
            case ")":
                index++;
                return ch;
            default:
                if (!isNaN(ch)) { //next token is a number
                    return parseInt(parseValue());
                } else if (isLetter(ch)) { //next token is a variable
                    if (isName(ch)) {
                        index++;
                        return ch;
                    }
                    else {
                        var operation = parseText();
                        if (isUnaryOperation(operation)) {
                            return operation;
                        }
                        else {
                            if (operation.length == 1) {
                                throw new UnknownVariable(operation, index);
                            }
                            throw new UnknownOperation(operation, index);
                        }
                    }
                } else {
                    throw new UnknownOperation(cutToken(), index);
                }
        }
    } else {
        if (brace_balance > 0) {
            throw new Error("Missing )");
        }
        throw new Error("Empty input");
    }
}

function getArguments(n) {
    var args = [];
    var nextToken;
    var count = n;
    n++;
    while (n > 0 && index < expression.length) {
        n--;
        nextToken = getToken();
        if (nextToken === "(") {
            index--;
            args.push(parseBraced());
            continue;
        }
        if (!isNaN(nextToken)) {
            args.push(new Const(nextToken));
            continue;
        }
        if (isName(nextToken)) {
            args.push(new Variable(nextToken));
            continue;
        }
        if (nextToken === ")") {
            if (n > 0) {
                throw new IncorrectArgumentsAmmount(count.toString(), args.length.toString());
            }
            return args;
        }
        throw new ValueExpected(nextToken, index - nextToken.length);
    }
    if (index < expression.length) {
        throw new Error("Excessive info");
    }
    throw new Error("Missing )");
}

function parseBraced() {
    var nextToken = getToken();
    if (nextToken !== "(") {
        throw new Error("Missed (");
    }
    nextToken = getToken();
    if (nextToken === ")") {
        throw new Error("Empty op");
    }
    if (isName(nextToken)) {
        throw new ValueInsteadOfOperation("variable", nextToken, index - nextToken.length - 1);
    }
    if (!isNaN(nextToken)) {
        throw new ValueInsteadOfOperation("const", nextToken.toString(), index - nextToken.length - 1);
    }
    var func = getFunction(nextToken);
    var argumentsCount = getArgCount(nextToken);
    var args = getArguments(argumentsCount);
    return new func(args[0], args[1]);
}

function parsePrefix(str) {
    expression = str.trim();
    index = 0;
    var nextToken = getToken();
    if (nextToken !== "(") {
        if (index < expression.length) {
            throw new Error("Missed (");
        }
        if (!isNaN(nextToken)) {
            return new Const(nextToken);
        }
        if (isName(nextToken)) {
            return new Variable(nextToken);
        }
    }
    index = 0;
    var res = parseBraced();
    if (index < expression.length) {
        throw new Error("Excessive info");
    }
    return res;
}
