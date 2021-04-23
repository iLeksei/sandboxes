import {DECREMENT_COUNTER, INCREMENT_COUNTER, RESET_COUNTER} from "../constants/reduxActions";

export const increment = () => ({type: INCREMENT_COUNTER,});
export const decrement = () => ({type: DECREMENT_COUNTER,});
export const reset = () => ({type: RESET_COUNTER,});

