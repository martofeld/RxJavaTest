/**
 * Created by mfeldsztejn on 10/9/16.
 */
//MODULES
var request_service = require("./request_service");
var constants = require("../constants/request_constants");
var imagesService = require("./images_service");

function getPeople(req, res, next) {
    var page = req.query.page;
    if (!page) {
        page = 1;
    }

    var options = {
        url: constants.ENDPOINT + "/people?page=" + page
    };
    
    request_service.get(options, function (error, response) {
        if (error) {
            return res.send(json_body);
        }
        var people = {};

        people.count = response.count;
        people.next = response.next;
        people.previous = response.previous;
        people.results = [];
        for (var i = 0; i < response.results.length; i++) {
            var result = response.results[i];
            people.results.push({
                name: result.name,
                height: result.height,
                mass: result.mass,
                hair_color: result.hair_color
            });
        }

        return res.send(people);
    });
}

function getPerson(req, res, next) {
    var id = req.params.id;

    var options = {
        url: constants.ENDPOINT + "/people/" + id
    };

    request_service.get(options, function (error, person) {
        if (error) {
            return res.send(person);
        }

        imagesService.getImages(person.name, function (error, images) {
            if (error){
                return res.send(images);
            } else {
                person.images = images;
                return res.send(person);
            }
        });
    });

}

module.exports = {
    "getPeople": getPeople,
    "getPerson": getPerson
};