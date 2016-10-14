/**
 * Created by mfeldsztejn on 10/9/16.
 */
//MODULES
var async = require("async");
var request_service = require("./request_service");
var constants = require("../constants/request_constants");
var imagesService = require("./images_service");
var obtained = {};

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
            return res.send(response);
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
            if (error) {
                return res.send(images);
            } else {
                person.images = images;
            }
        });

        var filmsReady, starShipsReady, vehiclesReady;
        async.map(person.films, requestUrl, function (error, films) {
            console.log(person.name + " films: " + films);
            person.films = films;
            filmsReady = true;
            _sendResponseIfPossible(res, person, filmsReady, starShipsReady, vehiclesReady)
        });

        async.map(person.vehicles, requestUrl, function (error, vehicles) {
            console.log(person.name + " vehicles: " + vehicles);
            person.vehicles = vehicles;
            vehiclesReady = true;
            _sendResponseIfPossible(res, person, filmsReady, starShipsReady, vehiclesReady)
        });

        async.map(person.starships, requestUrl, function (error, starShips) {
            console.log(person.name + " star ships: " + starShips);
            person.starships = starShips;
            starShipsReady = true;
            _sendResponseIfPossible(res, person, filmsReady, starShipsReady, vehiclesReady)
        });

    });

}

function _sendResponseIfPossible() {
    var res = arguments[0];
    var person = arguments[1];
    var canSend = true;
    for (var i = 2; i < arguments.length; i++) {
        canSend = canSend && arguments[i];
    }
    if (canSend) {
        console.log("sending response now");
        res.send(person);
    }
}

function requestUrl(url, callback) {
    if (url in obtained) {
        console.log(url + "was already obtained, returning saved value..." + obtained[url]);
        return callback(null, {name: obtained[url], url: url})
    } else {
        request_service.get({url: url}, function (error, response) {
            if (error) {
                return callback(error, error);
            } else {
                var name = response.title || response.name;
                console.log("Saving " + url + " with name " + name);
                obtained[url] = name;
                return callback(null, {name: name, url: response.url});
            }
        });
    }
}

module.exports = {
    "getPeople": getPeople,
    "getPerson": getPerson
};