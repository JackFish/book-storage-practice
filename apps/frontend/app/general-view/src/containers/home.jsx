import React, { Component } from 'react';
import { bindActionCreators } from 'redux'
import {connect} from 'react-redux';
import {increment, incrementIfOdd, decrement} from '../reducers/count';

class Home extends Component {
    static propTypes = {
        dispatch: React.PropTypes.func.isRequired
    };

    componentWillMount() {
        console.log(this.props.dispatch);
    }

    render() {
        const {count, increment, incrementIfOdd, decrement} = this.props;

        return (
            <section>
                test ({count})
                <button onClick={increment}>increment</button>
                <button onClick={incrementIfOdd}>incrementIfOdd</button>
                <button onClick={decrement}>decrement</button>
                <button onClick={() => this.props.dispatch({type:"INCREMENT_ASYNC"})}>incrementAsync</button>
            </section>
        );
    }
}

export default connect(
    state => ({
        count: state.count.count,
    }),function (dispatch, props) {
        return {
            dispatch,
            ...bindActionCreators({
                increment, incrementIfOdd, decrement
            }, dispatch)
        }
    }
)(Home)