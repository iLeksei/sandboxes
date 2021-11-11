import React from 'react';
import './App.css';
import {Main} from "./components/Main/Main";
import {WithUsers} from "./context/WithPosts";
import {Footer} from "./components/Footer/Footer";

function App() {
    return (
    <div className="App">
        <WithUsers>
            <Main />
            <Footer />
        </WithUsers>
    </div>
  );
}

export default App;
