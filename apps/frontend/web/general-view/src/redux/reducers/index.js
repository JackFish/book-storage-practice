import { combineReducers } from 'redux';
import { routerReducer } from 'react-router-redux';
import {reducer as form} from 'redux-form';
import {reducer as reduxAsyncConnect} from 'redux-connect';

import auth from './auth';
import user from './user';
import bookQuestion from './bookQuestion';
import bookRecord from './bookRecord';
import bookReport from './bookReport';
import bookSearch from './bookSearch';

export default combineReducers({
	routing: routerReducer,
	reduxAsyncConnect,
	form,
    auth,
    user,
	bookQuestion,
	bookRecord,
	bookReport,
	bookSearch
});