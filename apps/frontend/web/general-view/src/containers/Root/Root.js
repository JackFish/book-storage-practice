import React, {PropTypes, Component} from 'react';
import Helmet from 'react-helmet';
import Header from '../../components/Root/Header';
import Sidebar from '../../components/Root/Sidebar';
import Footer from '../../components/Root/Footer';

import config from '../../config';

export default class Root extends Component {
	static propTypes = {
		children: PropTypes.object.isRequired
	};

	componentDidMount() {
		var WebFontLoader = require('webfontloader');

		WebFontLoader.load({
			custom: {
				families: ['Noto Sans KR']
			}
		});
	}

	render() {
		return (
			<div className="l-container">
				<Header />
				<div className="l-content">
					<div className="l-inner">
						<div className="l-main">
							{this.props.children}
						</div>
						<Sidebar />
					</div>
				</div>
				<Footer />
			</div>
		)
	}
}