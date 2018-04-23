"use strict"
function cnst(a) {
    return function (x, y, z) {
        return a;
    };
}
function variable(a)  {
    return function (x, y, z) {
        switch (a) {
            case 'x':
                return x;
            case 'y':
                return y;
            case 'z':
                return z;
            default:
                return 0;
        }
    };
};

function add(a, b) {
    return function (x, y, z) {
        return a(x, y, z) + b(x, y, z);
    };
};

function subtract(a, b) {
    return function (x, y, z) {
        return a(x, y, z) - b(x, y, z);
    };
};

function multiply(a, b) {
    return function (x, y, z) {
        return a(x, y, z) * b(x, y, z);
    };
};

function divide(a, b) {
    return function (x, y, z) {
        return a(x, y, z) / b(x, y, z);
    };
};

function negate(a) {
    return function (x, y, z) {
        return -a(x, y, z);
    };
};

function cube(a) {
    return function (x, y, z) {
        return Math.pow(a(x, y, z), 3);
    };
};

function cuberoot(a) {
    return function (x, y, z) {
        return Math.pow(a(x, y, z), 1/3);
    };
};

