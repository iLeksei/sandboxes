import React from 'react';
import './App.css';
import {Main} from "./components/Main/Main";
import {UsersContext} from "./context/UsersContext";
import {Footer} from "./components/Footer/Footer";
import {WithUsers} from "./hocs/WithUsers";

function App() {
    return (
    <div className="App">
        <UsersContext>
            {
                WithUsers(<Main/>)
            }
            <Footer />
        </UsersContext>
    </div>
  );
}

export default App;
