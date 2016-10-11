var express = require('express');
var peopleService = require('./app/services/people_service');

var app = express();
var router = express.Router();

router.get('/', function (req, res) {
    res.send('Hello World!');
});

router.route('/people')
    .get(peopleService.getPeople);

router.route('/people/:id')
    .get(peopleService.getPerson);

app.use('/', router);

app.listen(3000, function () {
    console.log('Example app listening on port 3000!');
});