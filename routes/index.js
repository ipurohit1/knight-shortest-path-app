const fetch = require("node-fetch");
let express = require('express');
let router = express.Router();

/* GET home page. */
router.get('/', function (req, res, next) {
    res.render('index', {title: 'Express', highlighted: []});
});

// Fetches coordinates from server and sends it back to client-side
router.post('/', async function (req, res) {
    console.log("Everytime you post");
    const response = await fetch('http://ec2-54-166-247-203.compute-1.amazonaws.com:8080/api/calculateMoves/' + req.body.A.split(',')[0] + '&' + req.body.A.split(',')[1] + '&' + req.body.B.split(',')[0] + '&' + req.body.B.split(',')[1]);
    req.body.C = await response.json();
    coordinate_list = req.body.C;
    res.send(JSON.stringify(coordinate_list));
});


module.exports = router;
