var express = require('express');
var peopleService = require('./services/people_service');

var app = express();
var router = express.Router();

router.get('/', function (req, res) {
    res.send('Hello World!');
});

router.route('/people')
    .get(peopleService.getPeople);

app.use('/', router);

app.listen(3000, function () {
    console.log('Example app listening on port 3000!');
});