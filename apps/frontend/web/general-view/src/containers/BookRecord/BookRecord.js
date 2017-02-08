/**
 * Created by ksb on 16. 11. 10.
 */
import React, {PropTypes, Component} from 'react';
import {Grid} from 'react-bootstrap';

export default class BookRecord extends Component {
    static propTypes = {
        children: PropTypes.object.isRequired
    }

    render() {
        return (
            <div>
                <div>
                    <h1>
                        BookRecord
                    </h1>
                </div>
                {this.props.children}
            </div>
        )
    }
}