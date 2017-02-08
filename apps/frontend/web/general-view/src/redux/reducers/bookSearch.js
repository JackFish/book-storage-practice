/**
 * Created by ksb on 17. 2. 5.
 */
const FIND_LIST = "bookSearch/FIND_LIST";
const FIND_LIST_SUCCESS = "bookSearch/FIND_LIST_SUCCESS";
const FIND_LIST_FAIL = "bookSearch/FIND_LIST_FAIL";

const FIND_ONE = "bookSearch/FIND_ONE";
const FIND_ONE_SUCCESS = "bookSearch/FIND_ONE_SUCCESS";
const FIND_ONE_FAIL = "bookSearch/FIND_ONE_FAIL";

const initialState = {
    loading: false,
    list: {},
    one: {},
};

export default function reducer(state = initialState, action = {}) {
    switch (action.type) {
        case FIND_LIST:
            return {
                ...state,
                loading: false,
            };
        case FIND_LIST_SUCCESS:
            return {
                ...state,
                loading: true,
            };
        case FIND_LIST_FAIL:
            return {
                ...state,
                loading: true,
            };
        case FIND_ONE:
            return {
                ...state,
                loading: false,
            };
        case FIND_ONE_SUCCESS:
            return {
                ...state,
                loading: true,
            };
        case FIND_ONE_FAIL:
            return {
                ...state,
                loading: true,
            };
        default:
            return state;
    }
}

export function findList(page = 0) {
    return {
        types: [FIND_LIST, FIND_LIST_SUCCESS, FIND_LIST_FAIL],
        promise: (client) => client.get('')
    }
}

export function findOne(idx){
    return {
        types: [FIND_ONE, FIND_ONE_SUCCESS, FIND_ONE_FAIL],
        promise: (client) => client.get('')
    }
}