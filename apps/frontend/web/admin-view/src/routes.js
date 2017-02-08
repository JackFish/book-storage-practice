import React from 'react';
import {IndexRoute, Route} from 'react-router';
// import { isLoaded as isAuthLoaded, load as loadAuth } from 'redux/modules/auth';
import {
	Root,
	Home,
	BookRecord,
	BookRecordList,
	BookRecordForm,
	BookReport,
	BookReportList,
	BookReportForm
} from './containers';

export default (store) => {
	// const requireLogin = (nextState, replace, cb) => {
	//   function checkAuth() {
	//     const { auth: { user }} = store.getState();
	//     if (!user) {
	//       // oops, not logged in, so can't be here!
	//       replace('/');
	//     }
	//     cb();
	//   }
	//
	//   if (!isAuthLoaded(store.getState())) {
	//     store.dispatch(loadAuth()).then(checkAuth);
	//   } else {
	//     checkAuth();
	//   }
	// };

	return (
		<Route path="/" component={Root}>
			{ /* Home (main) route */ }
			<IndexRoute component={Home}/>
			<Route path="bookRecord" component={BookRecord}>
				<IndexRoute component={BookRecordList} />
				<Route path="page/:page" component = {BookRecordList} />
				<Route path="form" component = {BookRecordForm} />
				<Route path="form/:idx" component = {BookRecordForm} />
			</Route>
			<Route path="bookReport" component={BookReport}>
				<IndexRoute component={BookReportList} />
				<Route path="page/:page" component = {BookReportList} />
				<Route path="form" component = {BookReportForm} />
				<Route path="form/:idx" component = {BookReportForm} />
			</Route>
		</Route>
	);
};
