import React from 'react';
import {IndexRoute, Route} from 'react-router';
// import { isLoaded as isAuthLoaded, load as loadAuth } from 'redux/modules/auth';
import {
	Root,
	Home,
	BookRecord,
	BookRecordList,
	BookRecordDetail,
	BookReport,
	BookReportList,
	BookReportDetail,
	BookCalendar,
	BookChat,
	BookQuestion
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
				<Route path="detail/:idx" component = {BookRecordDetail} />
			</Route>
			<Route path="bookReport" component={BookReport}>
				<IndexRoute component={BookReportList} />
				<Route path="detail/:idx" component = {BookReportDetail} />
			</Route>
			<Route path="bookCalendar" component={BookCalendar}>
			</Route>
			<Route path="bookChat" component={BookChat}>
			</Route>
			<Route path="bookQuestion" component={BookQuestion}>
			</Route>
		</Route>
	);
};