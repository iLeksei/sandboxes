const express = require("express");
const bodyParser = require("body-parser");

const mainRouter = require("./routes/mainRouter");

const app = express();

app.use("/main", mainRouter);

app.use(bodyParser())
    .listen(1414, () => {
        console.log("server has started on 1414 port!");
    });