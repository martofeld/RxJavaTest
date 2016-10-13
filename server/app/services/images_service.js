/**
 * Created by mfeldsztejn on 10/11/16.
 */

var request_service = require("./request_service");
var constants = require("../constants/request_constants");

function getImages(query, callback) {
    
    var headers = constants.HEADERS;
    headers["Api-Key"] = constants.IMAGES_API_KEY;
    
    var options = {
        url: constants.IMAGES_ENDPOINT + "&phrase=" + query + " movies"
    };
    
    request_service.get(options, function (error, response) {
        if (error){
            return callback(error, response);
        }
        
        var images = [];
        var retries = 5;
        for (var i = 0; i < 5 && i < response.images.length && retries > 0; i++){
            var random = _getRandom(response.images.length);
            var image = _getUrlForImage(response.images[random]);
            if(images.indexOf(image) == -1) {
                images.push(image);
            } else {
                i--;
                retries--;
            }
        }
        return callback(false, images);
    });
}

function _getRandom(top) {
    return Math.floor((Math.random() * top));
}

function _getUrlForImage(image) {
    var url;
    if (image && image.display_sizes && image.display_sizes.length > 0){
        url = image.display_sizes[0].uri;
    }
    return url;
}

module.exports = {
    "getImages": getImages
};