/**
 * Created by ksb on 17. 2. 5.
 */
const POST_FIND_LIST = "bookQuestion/POST_FIND_LIST";
const POST_FIND_LIST_SUCCESS = "bookQuestion/POST_FIND_LIST_SUCCESS";
const POST_FIND_LIST_FAIL = "bookQuestion/POST_FIND_LIST_FAIL";

const POST_FIND_ONE = "bookQuestion/POST_FIND_ONE";
const POST_FIND_ONE_SUCCESS = "bookQuestion/POST_FIND_ONE_SUCCESS";
const POST_FIND_ONE_FAIL = "bookQuestion/POST_FIND_ONE_FAIL";

const POST_CREATE = "bookQuestion/POST_CREATE";
const POST_CREATE_SUCCESS = "bookQuestion/POST_CREATE_SUCCESS";
const POST_CREATE_FAIL = "bookQuestion/POST_CREATE_FAIL";

const POST_UPDATE = "bookQuestion/POST_UPDATE";
const POST_UPDATE_SUCCESS = "bookQuestion/POST_UPDATE_SUCCESS";
const POST_UPDATE_FAIL = "bookQuestion/POST_UPDATE_FAIL";

const POST_REMOVE = "bookQuestion/POST_REMOVE";
const POST_REMOVE_SUCCESS = "bookQuestion/POST_REMOVE_SUCCESS";
const POST_REMOVE_FAIL = "bookQuestion/POST_REMOVE_FAIL";

const REPLY_FIND_LIST = "bookQuestion/REPLY_FIND_LIST";
const REPLY_FIND_LIST_SUCCESS = "bookQuestion/REPLY_FIND_LIST_SUCCESS";
const REPLY_FIND_LIST_FAIL = "bookQuestion/REPLY_FIND_LIST_FAIL";

const REPLY_FIND_ONE = "bookQuestion/REPLY_FIND_ONE";
const REPLY_FIND_ONE_SUCCESS = "bookQuestion/REPLY_FIND_ONE_SUCCESS";
const REPLY_FIND_ONE_FAIL = "bookQuestion/REPLY_FIND_ONE_FAIL";

const REPLY_CREATE = "bookQuestion/REPLY_CREATE";
const REPLY_CREATE_SUCCESS = "bookQuestion/REPLY_CREATE_SUCCESS";
const REPLY_CREATE_FAIL = "bookQuestion/REPLY_CREATE_FAIL";

const REPLY_UPDATE = "bookQuestion/REPLY_UPDATE";
const REPLY_UPDATE_SUCCESS = "bookQuestion/REPLY_UPDATE_SUCCESS";
const REPLY_UPDATE_FAIL = "bookQuestion/REPLY_UPDATE_FAIL";

const REPLY_REMOVE = "bookQuestion/REPLY_REMOVE";
const REPLY_REMOVE_SUCCESS = "bookQuestion/REPLY_REMOVE_SUCCESS";
const REPLY_REMOVE_FAIL = "bookQuestion/REPLY_REMOVE_FAIL";

const initialState = {
    loading: false,
    postList: {},
    postOne: {},
    replyList: {},
    replyOne: {},
};

export default function reducer(state = initialState, action = {}) {
    switch (action.type) {
        case POST_FIND_LIST:
            return {
                ...state,
                loading: false,
            };
        case POST_FIND_LIST_SUCCESS:
            return {
                ...state,
                loading: true,
            };
        case POST_FIND_LIST_FAIL:
            return {
                ...state,
                loading: true,
            };
        case POST_FIND_ONE:
            return {
                ...state,
                loading: false,
            };
        case POST_FIND_ONE_SUCCESS:
            return {
                ...state,
                loading: true,
            };
        case POST_FIND_ONE_FAIL:
            return {
                ...state,
                loading: true,
            };
        case POST_CREATE:
            return {
                ...state,
                loading: false,
            };
        case POST_CREATE_SUCCESS:
            return {
                ...state,
                loading: true,
            };
        case POST_CREATE_FAIL:
            return {
                ...state,
                loading: true,
            };
        case POST_UPDATE:
            return {
                ...state,
                loading: false,
            };
        case POST_UPDATE_SUCCESS:
            return {
                ...state,
                loading: true,
            };
        case POST_UPDATE_FAIL:
            return {
                ...state,
                loading: true,
            };
        case POST_REMOVE:
            return {
                ...state,
                loading: false,
            };
        case POST_REMOVE_SUCCESS:
            return {
                ...state,
                loading: true,
            };
        case POST_REMOVE_FAIL:
            return {
                ...state,
                loading: true,
            };
        case REPLY_FIND_LIST:
            return {
                ...state,
                loading: false,
            };
        case REPLY_FIND_LIST_SUCCESS:
            return {
                ...state,
                loading: true,
            };
        case REPLY_FIND_LIST_FAIL:
            return {
                ...state,
                loading: true,
            };
        case REPLY_FIND_ONE:
            return {
                ...state,
                loading: false,
            };
        case REPLY_FIND_ONE_SUCCESS:
            return {
                ...state,
                loading: true,
            };
        case REPLY_FIND_ONE_FAIL:
            return {
                ...state,
                loading: true,
            };
        case REPLY_CREATE:
            return {
                ...state,
                loading: false,
            };
        case REPLY_CREATE_SUCCESS:
            return {
                ...state,
                loading: true,
            };
        case REPLY_CREATE_FAIL:
            return {
                ...state,
                loading: true,
            };
        case REPLY_UPDATE:
            return {
                ...state,
                loading: false,
            };
        case REPLY_UPDATE_SUCCESS:
            return {
                ...state,
                loading: true,
            };
        case REPLY_UPDATE_FAIL:
            return {
                ...state,
                loading: true,
            };
        case REPLY_REMOVE:
            return {
                ...state,
                loading: false,
            };
        case REPLY_REMOVE_SUCCESS:
            return {
                ...state,
                loading: true,
            };
        case REPLY_REMOVE_FAIL:
            return {
                ...state,
                loading: true,
            };
        default:
            return state;
    }
}

export function findPostList(page = 0) {
    return {
        types: [POST_FIND_LIST, POST_FIND_LIST_SUCCESS, POST_FIND_LIST_FAIL],
        promise: (client) => client.get('/question/post')
    }
}

export function findPostOne(idx){
    return {
        types: [POST_FIND_ONE, POST_FIND_ONE_SUCCESS, POST_FIND_ONE_FAIL],
        promise: (client) => client.get('/question/post')
    }
}

export function createPost(post){
    return {
        types: [POST_CREATE, POST_CREATE_SUCCESS, POST_CREATE_FAIL],
        promise: (client) => client.post('/question/post', {
            data: post
        })
    }
}

export function updatePost(post){
    return {
        types: [POST_UPDATE, POST_UPDATE_SUCCESS, POST_UPDATE_FAIL],
        promise: (client) => client.patch('/question/post', {
            data: post
        }),
        post: post
    }
}

export function removePost(idx) {
    return {
        types: [POST_REMOVE, POST_REMOVE_SUCCESS, POST_REMOVE_FAIL],
        promise: (client) => client.del('/question/post/' + idx),
        idx: idx
    }
}

export function findReplyList(page = 0) {
    return {
        types: [REPLY_FIND_LIST, REPLY_FIND_LIST_SUCCESS, REPLY_FIND_LIST_FAIL],
        promise: (client) => client.get('/question/reply')
    }
}

export function findReplyOne(idx){
    return {
        types: [REPLY_FIND_ONE, REPLY_FIND_ONE_SUCCESS, REPLY_FIND_ONE_FAIL],
        promise: (client) => client.get('/question/reply')
    }
}

export function createReply(reply){
    return {
        types: [REPLY_CREATE, REPLY_CREATE_SUCCESS, REPLY_CREATE_FAIL],
        promise: (client) => client.post('/question/reply', {
            data: reply
        })
    }
}

export function updateReply(reply){
    return {
        types: [REPLY_UPDATE, REPLY_UPDATE_SUCCESS, REPLY_UPDATE_FAIL],
        promise: (client) => client.patch('/question/reply', {
            data: reply
        }),
        reply: reply
    }
}

export function removeReply(idx) {
    return {
        types: [REPLY_REMOVE, REPLY_REMOVE_SUCCESS, REPLY_REMOVE_FAIL],
        promise: (client) => client.del('/question/reply/' + idx),
        idx: idx
    }
}
