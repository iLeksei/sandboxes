import React  from"react";
import {shallow, mount}  from "enzyme";
import configureMockStore from 'redux-mock-store'
import thunk   from 'redux-thunk'
import fetchMock  from 'jest-fetch-mock';
import {Main}  from  "../../src/components/Main/Main";

const middlewares = [thunk];
const mockStore = configureMockStore(middlewares);

describe("Main component", () => {

    let props = {
        increment: jest.fn(),
        decrement: jest.fn(),
        reset: jest.fn(),
        counter: 10,
    };

    afterEach(() => {
        fetchMock.resetMocks();
    });

    it('should render', () => {
        const instance = shallow(<Main />);
        console.log(instance.debug());

    });

});