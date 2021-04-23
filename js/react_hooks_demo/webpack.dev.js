const {merge} = require('webpack-merge');
const common = require('./webpack.common.js');
const webpack = require('webpack');
const path = require("path");

module.exports = merge(common, {
    plugins: [
        new webpack.HotModuleReplacementPlugin(),
        new webpack.ProgressPlugin()
    ],
    mode: 'development',
    devtool: 'source-map',
    watch: true,
    devServer: {
        historyApiFallback: true,
        hot: true,
        // contentBase: "./dist",
        compress: true,
        port: 8080,
        allowedHosts: [
            'localhost:8882'
        ]
    },
});