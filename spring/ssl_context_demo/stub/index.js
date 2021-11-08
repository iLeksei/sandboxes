const express = require("express");
const bodyParser = require("body-parser");
const os = require("os");
const ip = require("ip");
const api = express();
const fs = require('fs');
const https = require('https');

const PORT = process.env.STUB_PORT || 1414;

api.use(bodyParser.json());
api.use(bodyParser.urlencoded({ extended: true }));
api.use(express.static(__dirname + "/public"))

api.get("/health-check", (req, res) => {
    const instanceHost = ip.address() + ":" +PORT;
    res.status(200).send("stub with host: " + instanceHost + " is alive");
})

// api.listen(PORT, () => {
//    console.info(`stub-server has started on: ${PORT} port`);
// });

https.createServer({
    key: fs.readFileSync(__dirname + '/ssl2/private_stub2.pem'),
    cert: fs.readFileSync(__dirname + '/ssl2/public_stub2.pem'),
}, api).listen(PORT, () => {
       console.info(`stub-server has started on: ${PORT} port`);
});