/**
 * Created by ksb on 2017. 5. 29..
 */
const CREATE = "user/CREATE";
const CREATE_SUCCESS = "user/CREATE_SUCCESS";
const CREATE_FAIL = "user/CREATE_FAIL";

const UPDATE = "user/UPDATE";
const UPDATE_SUCCESS = "user/UPDATE_SUCCESS";
const UPDATE_FAIL = "user/UPDATE_FAIL";

const initialState = {
    loading: false,
    // user: {email:null, password:null, name:null}
};

export default function reducer(state = initialState, action = {}) {
    switch (action.type) {
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
        })
    }
}