var express = require('express');
var cluster = require('cluster');
var peopleService = require('./app/services/people_service');
var apicache = require('apicache');
var cache = apicache.middleware("1 day");
apicache.options({
    debug: true
});

if (cluster.isMaster) {
    // Count the machine's CPUs
    var cpuCount = require('os').cpus().length;
    console.log("Machine has %d cpus", cpuCount);

    // Create a worker for each CPU
    for (var i = 0; i < cpuCount / 2; i += 1) {
        cluster.fork();
    }
} else {
    var app = express();
    var router = express.Router();

    router.use(cache);
    router.use(function (req, res, next) {
        var requestedUrl = req.protocol + '://' + req.hostname + ':3000' + req.url;
        console.log("Requested url %s is being processed by worker nº %d", requestedUrl, cluster.worker.id);
        next();
    });

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
        console.log('Example app listening on port %s with worker id %d!', port, cluster.worker.id);
    });
}

// Listen for dying workers
cluster.on('exit', function (worker) {

    // Replace the dead worker,
    // we're not sentimental
    console.log("Worker %d died :'(", worker.id);
    cluster.fork();

});