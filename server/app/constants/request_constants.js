/**
 * Created by mfeldsztejn on 10/9/16.
 */
var endpoint = "http://swapi.co/api";
var images_endpoint = "https://api.gettyimages.com/v3/search/images?embed_content_only=true&exclude_nudity=true&file_types=jpg%2Cpng&orientations=Horizontal";
var images_api_key = "d5ksc5ag8av32mq3mtsyhr2g";
var headers = {
    'Content-Type': 'application/json'
};

module.exports = {
    ENDPOINT: endpoint,
    IMAGES_ENDPOINT: images_endpoint,
    HEADERS: headers,
    IMAGES_API_KEY: images_api_key
};