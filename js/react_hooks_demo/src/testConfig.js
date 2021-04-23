const {configure} = require('enzyme');
const Adapter = require('enzyme-adapter-react-16');
const fetchMock =  require('jest-fetch-mock');

configure({ adapter: new Adapter() });

global.fetch = fetchMock;
fetchMock.enableMocks();
