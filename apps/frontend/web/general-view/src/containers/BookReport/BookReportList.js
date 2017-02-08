/**
 * Created by ksb on 16. 11. 10.
 */
import React, {PropTypes, Component} from 'react';
import {Grid} from 'react-bootstrap';

export default class BookReportList extends Component {
    static propTypes = {
        children: PropTypes.object.isRequired
    }

    render() {
        return (
            <div>
                BookReportList
            </div>
        )
    }
}