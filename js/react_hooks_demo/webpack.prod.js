const path = require('path');
const {merge} = require('webpack-merge');
const common = require('./webpack.common.js');
const { CleanWebpackPlugin } = require('clean-webpack-plugin');
const UglifyJsPlugin = require('uglifyjs-webpack-plugin');

module.exports = merge(common, {
    mode: 'production',
    plugins: [
        new CleanWebpackPlugin({exclude: "index.html"}), // для очистки dict при новой сборки
    ],
    optimization: {
        minimizer: [
            new UglifyJsPlugin({
                test: /\.js(\?.*)?$/i,
                include: /\/src/,
                extractComments: 'all',
                uglifyOptions: {
                    compress: {
                        arguments: true,
                        sequences: true,
                        booleans: true,
                        loops: true,
                        unused: true,
                        warnings: false,
                        drop_console: true,
                        unsafe: true,
                        dead_code: true,
                        drop_debugger: true,
                    },
                },
            }),
        ]
    }
});