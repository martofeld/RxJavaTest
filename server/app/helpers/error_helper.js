/**
 * Created by mfeldsztejn on 10/9/16.
 */
var k404Error = {
    "code": 404,
    "message": "404 not found"
};
var k400Error = {
    "code": 400,
    "message": "400 bad request"
};

function handlePossibleRequestError(error, response) {
    var definitiveError;
    switch(response.statusCode){
        case 400:
            definitiveError = k400Error;
            break;
        case 404:
            definitiveError = k404Error;
            break;
        default:
            definitiveError = error;
            break;
    }
    
    return definitiveError;
}

module.exports = {
    handleError: handlePossibleRequestError
};