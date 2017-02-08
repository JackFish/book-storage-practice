import React, {Component} from 'react';
import {Glyphicon} from 'react-bootstrap';
import { Link } from 'react-router';
import { LinkContainer } from 'react-router-bootstrap';

export default class Header extends Component {

	render() {
		return (
			<div className="l-header">
				<div className="l-inner l-float-clear">
					<div className="l-brand">
						<Link className="l-logo" to="/">
							북스토리지
						</Link>
					</div>
					<div className="l-nav">
						<div className="l-nav-inner">
							<ul>
								<li>
									<Link to="/bookRecord">
										도서
									</Link>
								</li>
								<li>
									<Link to="/bookReport">
										감상
									</Link>
								</li>
								<li>
									<Link to="/bookCalendar">
										대화
									</Link>
								</li>
								<li>
									<Link to="/bookChat">
										달력
									</Link>
								</li>
								<li>
									<Link to="/bookQuestion">
										질문
									</Link>
								</li>
							</ul>
						</div>
					</div>
					<div className="l-right l-float-clear">
						<div className="l-search">
							<div className="l-input-wrapper">
								<input className="l-input" type="text" placeholder="Search on Storage..." />
							</div>
							<span className="l-button">
								<Glyphicon glyph="search" />
							</span>
						</div>
					</div>
				</div>
			</div>
		)
	}
}