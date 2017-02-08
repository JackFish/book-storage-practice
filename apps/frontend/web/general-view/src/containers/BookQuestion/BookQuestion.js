/**
 * Created by ksb on 17. 2. 5.
 */
import React, {PropTypes, Component} from 'react';
import {Grid} from 'react-bootstrap';

export default class BookQuestion extends Component {
    static propTypes = {
        children: PropTypes.object.isRequired
    }

    render() {
        return (
            <div className="l-main-container">
                <h1>질문</h1>
                <div className="l-description">
                    <p>
                        질문
                    </p>
                </div>
                <div className="l-main-content">

                </div>
            </div>
        )
    }
}