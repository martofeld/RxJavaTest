/**
 * Created by mfeldsztejn on 10/11/16.
 */

var request = require("request");
var constants = require("../constants/request_constants");

function getImages(query, callback) {
    
    var headers = constants.HEADERS;
    headers["Api-Key"] = constants.IMAGES_API_KEY;
    
    var options = {
        url: constants.IMAGES_ENDPOINT + "&phrase=" + query,
        method: "GET",
        headers: headers
    };
    
    request(options, function (error, response, body) {
        var json_body = JSON.parse(body);
        var images = [];
        for (var i = 0; i < 5; i++){
            var random = _getRandom(json_body.images.length);
            var image = json_body.images[random].display_sizes[0].uri;
            if(images.indexOf(image) == -1) {
                images.push(image);
            } else {
                i--;
            }
        }
        return callback(images);
    });
}

function _getRandom(top) {
    return Math.floor((Math.random() * top) + 1);
}

module.exports = {
    "getImages": getImages
};