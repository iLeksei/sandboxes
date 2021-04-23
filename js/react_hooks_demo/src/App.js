import React from 'react';
import {createStore} from "redux";
import { Provider } from 'react-redux'
import {BrowserRouter, Switch, Route} from "react-router-dom";

import Main from "./components/Main/Main.js";
import mainReducer from "./reduxStore";

import "./App.scss";
import {Timer} from "./components/Timer/Timer";

const store = createStore(
    mainReducer,
    window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__()
    );

export default class App extends React.Component {

  render() {
    return (
      <Provider store={store}>
          <BrowserRouter>
              <Switch>
                  <Route exac path={"/main"} component={Main}/>
                  <Route exac path={"/timer"} component={Timer}/>
              </Switch>
          </BrowserRouter>
      </Provider>
    );
  }
};