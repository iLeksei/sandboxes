import React, {PureComponent} from 'react';
import PropTypes from 'prop-types';
import {bindActionCreators} from "redux";
import {decrement, increment, reset} from "../../reduxActions/counter";
import {connect} from "react-redux";

import "./Main.scss";


export class Main extends React.Component {
    render() {
        return (
            <div className="main__container">
                <div className="main__counter-wrapper">
                    <nav>
                        <button id="increment-counter" onClick={this.props.increment}>increment</button>
                        <button id="decrement-counter" onClick={this.props.decrement}>decrement</button>
                        <button id="reset-counter" onClick={this.props.reset}>reset</button>
                    </nav>
                    <div className="main__counter-element">{this.props.counter}</div>
                </div>
            </div>
        );
    }
}

const mapStateToProps = (state) => ({
    counter: state.counterReducer.counter
});

const mapDispatchToProps = (dispatch) => bindActionCreators({
    increment,
    decrement,
    reset,
}, dispatch);

export default connect(
    mapStateToProps,
    mapDispatchToProps
)(Main)

Main.defaultProps = {
    increment: () => {},
    decrement: () => {},
    reset: () => {},
    counter: 0,
};

Main.propTypes = {
    increment: PropTypes.func,
    decrement:  PropTypes.func,
    reset:  PropTypes.func,
    counter:  PropTypes.number,
};