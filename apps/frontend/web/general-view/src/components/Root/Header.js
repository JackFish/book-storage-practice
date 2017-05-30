import React, {Component} from 'react';
import {Glyphicon} from 'react-bootstrap';
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

export default class Header extends Component {
    handleLogout = (e) => {
        e.preventDefault();
        this.props.authToken().then(result =>
            this.props.logout(result.token)
        ).then(() => this.props.pushState('/'))
    }


    render() {
        const {user} = this.props;

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
									<Link to="/bookChat">
										대화
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
						{
							user ?
								<div>
									<Link to="/profile">
										{user.name}
									</Link>
									&nbsp;
									<a href="#" onClick={this.handleLogout}>
										퇴장하다
									</a>
								</div>
								:
								<div>
									<Link to="/login">
										입장하다
									</Link>
									&nbsp;
									<Link to="/join">
										가입하다
									</Link>
								</div>
						}
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