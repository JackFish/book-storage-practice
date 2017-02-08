import React, {Component} from 'react';
import {Navbar, Nav, NavItem, NavDropdown, MenuItem} from 'react-bootstrap';
import { Link } from 'react-router';
import { LinkContainer } from 'react-router-bootstrap';

export default class Top extends Component {

	render() {
		return (
			<Navbar>
				<Navbar.Header>

				</Navbar.Header>
				<Navbar.Collapse>
					<Nav>
						<LinkContainer to="/">
							<NavItem eventKey={1}>
								북스토리지
							</NavItem>
						</LinkContainer>
						<NavDropdown eventKey={2} title="책감상" id="bookReport">
							<LinkContainer to="/bookReport">
								<MenuItem eventKey={2.1}>책감상</MenuItem>
							</LinkContainer>
						</NavDropdown>
						<NavDropdown eventKey={3} title="책기록" id="bookRecord">
							<LinkContainer to="/bookRecord">
								<MenuItem eventKey={3.1}>책기록</MenuItem>
							</LinkContainer>
						</NavDropdown>
					</Nav>
				</Navbar.Collapse>
			</Navbar>
		)
	}
}