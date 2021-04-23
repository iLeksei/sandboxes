const express = require("express");

const mainRouter = express.Router();

mainRouter.get("/", (req,res) => res.json("hello from server!"));

module.exports = mainRouter;
