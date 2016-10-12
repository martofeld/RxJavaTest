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

var port = process.env.PORT || 3000;
app.listen(port, function () {
    console.log('Example app listening on port ' + port + '!');
});