import { combineReducers } from 'redux';
import todos from './todos.js';
import credentials from './credentials.js';
import count from './count.js';

export default combineReducers({
  todos,
  credentials,
  count
});
