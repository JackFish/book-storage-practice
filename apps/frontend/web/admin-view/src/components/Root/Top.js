import React, {Component} from 'react';
import {Navbar, Nav, NavItem, NavDropdown, MenuItem} from 'react-bootstrap';
import { Link } from 'react-router';
import { LinkContainer } from 'react-router-bootstrap';
import { connect } from 'react-redux';
import {push as pushState} from 'react-router-redux';
import {authToken, logout} from 'redux/reducers/auth';

@connect(
    state => ({
        user : state.auth.user,
        token: state.auth.token
    }),
    {pushState, authToken, logout}
)
export default class Top extends Component {

    handleLogout = (e) => {
        e.preventDefault();
        this.props.authToken().then(result =>
            this.props.logout(result.token)
        ).then(() => this.props.pushState('/login'))
    }

    render() {
        const {user} = this.props;

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
					<Nav componentClass={'div'} pullRight>
                        {
                            user ?
								<Navbar.Text>
                                    {user.userName}&nbsp;
									<Navbar.Link href="/logout" onClick={this.handleLogout}>
										로그아웃
									</Navbar.Link>
								</Navbar.Text>
                                : null
                        }
					</Nav>
				</Navbar.Collapse>
			</Navbar>
		)
	}
}