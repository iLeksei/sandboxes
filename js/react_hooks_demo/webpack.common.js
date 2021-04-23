const path = require("path");
const HtmlWebpackPlugin = require("html-webpack-plugin");

module.exports = {
    entry: "./src/index.js",
    output: {
        filename: 'bundle.js',
        path: path.join(__dirname, '/dist'),
    },
    module: {
        rules: [
            {
                test: /\.(js|jsx)$/,
                exclude: "/(node_modules|bower_components)/",
                include: path.resolve(__dirname, 'src'),
                use: {
                    loader: "babel-loader",
                    options: {
                        presets: ["@babel/preset-env", "@babel/preset-react"]
                    }
                }
            },
            {
                test: /\.css$/,
                use: ["css-loader", "style-loader"]
            },
            {
                test: /\.s[ac]ss$/i,
                use: [
                    'style-loader',
                    'css-loader',
                    'sass-loader',
                ],
            },
        ],
    },
    resolve: {
        extensions: ["*",".js", ".jsx"],
        modules: [
            path.join(__dirname, "node_modules"),
            path.join(__dirname, "src"),
        ]
    },
    plugins: [

        // new HtmlWebpackPlugin({
        //     template:  path.join(__dirname, "index.html"),
        // })
    ]
};