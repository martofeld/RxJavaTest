/**
 * Created by mfeldsztejn on 10/13/16.
 */
var request = require("request");
var error_handler = require("../helpers/error_helper");
var constants = require("../constants/request_constants");

function get(options, callback) {
    options.headers = constants.HEADERS;
    options.method = "GET";
    
    request(options, function (error, response, body) {
        response.setEncoding('utf-8');
        var possibleError = error_handler.handleError(error, response);
        if (possibleError){
            return callback(true, possibleError);
        }
        
        var json_body = JSON.parse(body);
        callback(false, json_body);
    });
}

module.exports = {get: get};