/**
 * Created by ksb on 17. 1. 29.
 */
import React, {Component} from 'react';
import {Row, Col, Image} from 'react-bootstrap';
import { Link } from 'react-router';
import { LinkContainer } from 'react-router-bootstrap';

export default class SiderBar extends Component {

    render() {
        return (
            <div className="l-sidebar">
                <div className="l-row">
                    <div className="l-col l-col-1">
                        <p>Book Storage</p>
                        <Image src={require("../../images/book1.png")} responsive />
                    </div>
                </div>
                <div className="l-row">
                    <div className="l-col l-col-4">
                        <Image src={require("../../images/book2.png")} responsive />
                    </div>
                    <div className="l-col l-col-4">
                        <Image src={require("../../images/book3.png")} responsive />
                    </div>
                    <div className="l-col l-col-4">
                        <Image src={require("../../images/book4.png")} responsive />
                    </div>
                    <div className="l-col l-col-4">
                        <Image src={require("../../images/book5.png")} responsive />
                    </div>
                </div>
                <div className="l-row">
                    <div className="l-col l-col-1">
                        <Image src={require("../../images/book6.png")} responsive />
                    </div>
                </div>
            </div>
        )
    }
}


