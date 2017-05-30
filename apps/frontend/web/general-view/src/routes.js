import React from 'react';
import {IndexRoute, Route} from 'react-router';
import { isLoaded as isAuthLoaded, load as loadAuth } from 'redux/reducers/auth';
import {
	Root,
    Login,
    Join,
	Profile,
    Home,
	BookRecord,
	BookRecordList,
	BookRecordDetail,
	BookReport,
	BookReportList,
	BookReportDetail,
	BookChat,
	BookQuestion
} from './containers';

export default (store) => {
    const requireLogin = (nextState, replace, cb) => {
        function checkAuth() {
            const {auth: {user}} = store.getState();
            if (!user) {
                // oops, not logged in, so can't be here!
                replace({
                    pathname: '/login',
                    state: {nextPathname: nextState.location.pathname}
                });
            }
            cb();
        }

        if (!isAuthLoaded(store.getState())) {
            store.dispatch(loadAuth()).then(checkAuth).catch(e => {
                replace({
                    pathname: '/login',
                    state: {nextPathname: nextState.location.pathname}
                });

                cb();
            });
        } else {
            checkAuth();
        }
    };
    store.dispatch(loadAuth());

	return (
		<Route path="/" component={Root}>
			{ /* Home (main) route */ }
			<IndexRoute component={Home}/>

			<Route path="login" component={Login}>
			</Route>

			<Route path="join" component={Join}>
			</Route>

			<Route path="profile" component={Profile}>
			</Route>

			<Route path="bookRecord" component={BookRecord}>
				<IndexRoute component={BookRecordList} />
				<Route path="detail/:idx" component = {BookRecordDetail} />
			</Route>
			<Route path="bookReport" component={BookReport}>
				<IndexRoute component={BookReportList} />
				<Route path="detail/:idx" component = {BookReportDetail} />
			</Route>
			<Route path="bookChat" component={BookChat}>
			</Route>
			<Route path="bookQuestion" component={BookQuestion}>
			</Route>
		</Route>
	);
};