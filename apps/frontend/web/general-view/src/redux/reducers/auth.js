/**
 * Created by ksb on 2017. 5. 29..
 */
const AUTH_LOAD =  'auth/AUTH_LOAD';
const AUTH_LOAD_SUCCESS = 'auth/AUTH_LOAD_SUCCESS';
const AUTH_LOAD_FAIL = 'auth/AUTH_LOAD_FAIL';

const AUTH_TOKEN = 'auth/AUTH_TOKEN';
const AUTH_TOKEN_SUCCESS = 'auth/AUTH_TOKEN_SUCCESS';
const AUTH_TOKEN_FAIL = 'auth/AUTH_TOKEN_FAIL';

const LOGIN = 'auth/LOGIN';
const LOGIN_SUCCESS = 'auth/LOGIN_SUCCESS';
const LOGIN_FAIL = 'auth/LOGIN_FAIL';

const LOGOUT = 'auth/LOGOUT';
const LOGOUT_SUCCESS = 'auth/LOGOUT_SUCCESS';
const LOGOUT_FAIL = 'auth/LOGOUT_FAIL';

const CREATE = "user/CREATE";
const CREATE_SUCCESS = "user/CREATE_SUCCESS";
const CREATE_FAIL = "user/CREATE_FAIL";

const UPDATE = "user/UPDATE";
const UPDATE_SUCCESS = "user/UPDATE_SUCCESS";
const UPDATE_FAIL = "user/UPDATE_FAIL";

const initialState = {
    loading: false,
}

export default function reducer(state = initialState, action = {}) {
    switch(action.type){
        case AUTH_LOAD:
            return {
                ...state,
                loading: true
            };
        case AUTH_LOAD_SUCCESS:
            if(action.result.email) {
                return {
                    ...state,
                    user: action.result,
                    loading: false
                }
            }
            return {
                ...state,
                loading: false,
            }
        case AUTH_LOAD_FAIL:
            return {
                ...state,
                loading: false,
            };
        case AUTH_TOKEN:
            return {
                ...state
            };
        case AUTH_TOKEN_SUCCESS:
            return {
                ...state,
                token: action.result.token
            };
        case LOGIN:
            return {
                ...state,
                loading: true
            };
        case LOGIN_SUCCESS:
            return {
                ...state,
                user: action.result,
                loginError: null,
                loading: false
            };
        case LOGIN_FAIL:
            return {
                ...state,
                loginError: action.error.response.text,
                loading: false
            };
        case LOGOUT:
            return {
                ...state,
                loading: true
            };
        case LOGOUT_SUCCESS:
        case LOGOUT_FAIL:
            return {
                ...state,
                user: null,
                token: null,
                loading: false
            };
        case CREATE:
            return {
                ...state,
                loading: true
            };
        case CREATE_SUCCESS:
            return {
                ...state,
                loading: false
            };
        case CREATE_FAIL:
            return {
                ...state,
                loading: false
            };
        case UPDATE:
            return {
                ...state,
                loading: true
            };
        case UPDATE_SUCCESS:
            return {
                ...state,
                user: {
                    ...state.user,
                    userName: action.user.userName
                },
                loading: false
            };
        case UPDATE_FAIL:
            return {
                ...state,
                loading: false
            };
        default:
            return state;
    }
}

export function isLoaded(globalState) {
    return globalState.auth && globalState.auth.loaded;
}

export function load(){
    return {
        types: [AUTH_LOAD, AUTH_LOAD_SUCCESS, AUTH_LOAD_FAIL],
        promise: (client) => client.get('/auth/me')
    }
}

export function authToken(){
    return {
        types: [AUTH_TOKEN, AUTH_TOKEN_SUCCESS, AUTH_TOKEN_FAIL],
        promise: (client) => client.get('/auth')
    }
}

export function login(values, token){
    return {
        types: [LOGIN, LOGIN_SUCCESS,  LOGIN_FAIL],
        promise: (client) => client.post('/auth/login', {
            data: values,
            token: token,
            authorization: 'Basic ' + btoa(values.email + ':' + values.password)
        })
    }
}

export function logout(token){
    return {
        types: [LOGOUT, LOGOUT_SUCCESS, LOGOUT_FAIL],
        promise: (client) => client.post('/auth/logout', {
            token: token
        })
    }
}

export function create(user){
    return {
        types: [CREATE, CREATE_SUCCESS, CREATE_FAIL],
        promise: (client) => client.post('/user', {
            data: user
        })
    }
}

export function update(user){
    return {
        types: [UPDATE, UPDATE_SUCCESS, UPDATE_FAIL],
        promise: (client) => client.patch('/user', {
            data: user
        }),
        user: user
    }
}