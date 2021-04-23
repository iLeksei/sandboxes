import { combineReducers } from 'redux'
import counterReducer from './counter';


const mainReducer = combineReducers({
    counterReducer,
});

export default mainReducer;