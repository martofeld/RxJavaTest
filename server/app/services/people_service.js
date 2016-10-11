/**
 * Created by mfeldsztejn on 10/9/16.
 */
//MODULES
var request = require("request");
var constants = require("../constants/request_constants");
var errorHelper = require("../helpers/error_helper");

function getPeople(req, res) {
    var page = req.query.page;
    if (!page) {
        page = 1;
    }

    var options = {
        url: constants.ENDPOINT + "/people?page=" + page,
        method: "GET",
        headers: constants.HEADERS
    };

    request(options, function (error, response, body) {
        errorHelper.handleError(error, response, res);
        
        response.setEncoding('utf-8');
        var people = {};

        var json_body = JSON.parse(body);

        people.count = json_body.count;
        people.next = json_body.next;
        people.previous = json_body.previous;
        people.results = [];
        for (var i = 0; i < json_body.results.length; i++) {
            var result = json_body.results[i];
            people.results.push({
                name: result.name,
                height: result.height,
                mass: result.mass,
                hair_color: result.hair_color
            });
        }

        res.send(people);
    });
}

module.exports = {
    getPeople: getPeople
};