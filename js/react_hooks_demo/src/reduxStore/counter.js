import {DECREMENT_COUNTER, INCREMENT_COUNTER, RESET_COUNTER} from "../constants/reduxActions";
import {cloneDeep} from "../utils/common";

const initialStore = {
    counter: 0,
};

const actionIncrementCounter = (store, action) => {
    const newStore = cloneDeep(store);
    newStore.counter = ++newStore.counter;
    return newStore;
};
const actionDecrementCounter = (store, action) => {
    const newStore = cloneDeep(store);
    newStore.counter = --store.counter;
    return newStore;
};

const actionResetCounter = (store, action) => {
    const newStore = cloneDeep(store);
    newStore.counter = 0;
    return newStore;
};

const actions = {
    [INCREMENT_COUNTER]: actionIncrementCounter,
    [DECREMENT_COUNTER]: actionDecrementCounter,
    [RESET_COUNTER]: actionResetCounter,
};

export default function counterReducer(store = initialStore, action) {
    if (actions[action.type]) {
        return actions[action.type](store, action);
    }
    return store;
}