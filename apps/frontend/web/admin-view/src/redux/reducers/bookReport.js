/**
 * Created by ksb on 16. 11. 16.
 */
import {BLOCK_TYPE_DRAFT, BLOCK_TYPE_IMAGE, BLOCK_TYPE_YOUBUTE} from '../../helpers/constants';

const FIND_SUMMARY_LIST = "bookReport/FIND_SUMMARY_LIST";
const FIND_SUMMARY_LIST_SUCCESS = "bookReport/FIND_SUMMARY_LIST_SUCCESS";
const FIND_SUMMARY_LIST_FAIL = "bookReport/FIND_SUMMARY_LIST_FAIL";

const FIND_DETAIL_ONE = "bookReport/FIND_DETAIL_ONE";
const FIND_DETAIL_ONE_SUCCESS = "bookReport/FIND_DETAIL_ONE_SUCCESS";
const FIND_DETAIL_ONE_FAIL = "bookReport/FIND_DETAIL_ONE_FAIL";

const CREATE = "bookReport/CREATE";
const CREATE_SUCCESS = "bookReport/CREATE_SUCCESS";
const CREATE_FAIL = "bookReport/CREATE_FAIL";

const UPDATE = "bookReport/UPDATE";
const UPDATE_SUCCESS = "bookReport/UPDATE_SUCCESS";
const UPDATE_FAIL = "bookReport/UPDATE_FAIL";

const VISIBLE = "bookReport/VISIBLE";
const VISIBLE_SUCCESS = "bookReport/VISIBLE_SUCCESS";
const VISIBLE_FAIL = "bookReport/VISIBLE_FAIL";

const REMOVE = "bookReport/REMOVE";
const REMOVE_SUCCESS = "bookReport/REMOVE_SUCCESS";
const REMOVE_FAIL = "bookReport/REMOVE_FAIL";

const RESET = "bookReport/RESET";

const SET_BLOCK_STATE_LIST = "bookReport/SET_BLOCK_STATE_LIST";

const SEARCH = "bookRecord/SEARCH";

const LOAD_SEARCH_STATUS = "bookReport/LOAD_SEARCH_STATUS";
const LOAD_SEARCH_STATUS_SUCCESS = "bookReport/LOAD_SEARCH_STATUS_SUCCESS";
const LOAD_SEARCH_STATUS_FAIL = "bookReport/LOAD_SEARCH_STATUS_FAIL";

const initialState = {
    loading: false,
    detailOne: {},
    blockStateList: [],
    summaryList: {},
    searchStatus: [],
    search: {}
}

export default function reducer(state = initialState, action = {}) {
    switch (action.type) {
        case FIND_SUMMARY_LIST:
            return {
                ...state,
                loading: true
            };
        case FIND_SUMMARY_LIST_SUCCESS:
            return {
                ...state,
                loading: false,
                summaryList: action.result
            };
        case FIND_SUMMARY_LIST_FAIL:
            return {
                ...state,
                loading: false
            };
        case FIND_DETAIL_ONE:
            return {
                ...state,
                loading: true
            };
        case FIND_DETAIL_ONE_SUCCESS:
            return {
                ...state,
                loading: false,
                detailOne: action.result
            };
        case FIND_DETAIL_ONE_FAIL:
            return {
                ...state,
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
                loading: false
            };
        case UPDATE_FAIL:
            return {
                ...state,
                loading: false
            };
        case VISIBLE:
            return {
                ...state,
                loading: true
            };
        case VISIBLE_SUCCESS:
            return {
                ...state,
                loading: false,
                summaryList: Object.assign({}, state.summaryList, {
                    content: state.summaryList.content.map(summary =>
                        summary.idx === action.idx ? Object.assign({}, summary, {visible: action.visible}) : summary)
                }),
            };
        case VISIBLE_FAIL:
            return {
                ...state,
                loading: false
            };
        case REMOVE:
            return {
                ...state,
                loading: true
            };
        case REMOVE_SUCCESS:
            return {
                ...state,
                loading: false
            };
        case REMOVE_FAIL:
            return {
                ...state,
                loading: false
            };
        case RESET:
            return {
                ...state,
                detailOne: initialState.detailOne,
                blockStateList: initialState.blockStateList
            };
        case SET_BLOCK_STATE_LIST:
            return {
                ...state,
                blockStateList: action.blockStateList
            };
        case SEARCH:
            return {
                ...state,
                search: action.search
            };
        case LOAD_SEARCH_STATUS:
            return {
                ...state,
                loading: true
            };
        case LOAD_SEARCH_STATUS_SUCCESS:
            return {
                ...state,
                loading: false,
                searchStatus: action.result
            };
        case LOAD_SEARCH_STATUS_FAIL:
            return {
                ...state,
                loading: false
            };
        default:
            return state;
    }
}

export function findSummaryList(page = 0, term = "", type = ""){
    return {
        types: [FIND_SUMMARY_LIST, FIND_SUMMARY_LIST_SUCCESS, FIND_SUMMARY_LIST_FAIL],
        promise: (client) => client.get('/book_report?page=' + page + '&term=' + term + '&type=' + type)
    }
}

export function findDetailOne(idx){
    return {
        types: [FIND_DETAIL_ONE, FIND_DETAIL_ONE_SUCCESS, FIND_DETAIL_ONE_FAIL],
        promise: (client) => client.get('/book_report/' + idx)
    }
}

export function create(bookReport, imageFileList){
    const attach = imageFileList;

    return {
        types: [CREATE, CREATE_SUCCESS, CREATE_FAIL],
        promise: (client) => client.post('/book_report', {
            data: bookReport,
            attach: attach
        })
    }
}

export function update(bookReport, imageFileList){
    const attach = imageFileList;

    return {
        types: [UPDATE, UPDATE_SUCCESS, UPDATE_FAIL],
        promise: (client) => client.patch('/book_report', {
            data: bookReport,
            attach: attach
        }),
        bookReport: bookReport
    }
}

export function visible(idx){
    return commonVisible(idx, true);
}

export function invisible(idx){
    return commonVisible(idx, false);
}

function commonVisible(idx, visible){
    return {
        types: [VISIBLE, VISIBLE_SUCCESS, VISIBLE_FAIL],
        promise: (client) => client.patch('/book_report/visible',{
            data: {
                idx: idx,
                visible: visible
            }
        }),
        visible: visible,
        idx: idx
    }
}

export function remove(idx) {
    return {
        types: [REMOVE, REMOVE_SUCCESS, REMOVE_FAIL],
        promise: (client) => client.del('/book_report/' + idx),
        idx: idx
    }
}

export function reset(){
    return {
        type: RESET
    }
}

export function setBlockStateList(blockStateList){
    return {
        type: SET_BLOCK_STATE_LIST,
        blockStateList
    }
}

export function search(term = "", type = ""){
    return {
        type: SEARCH,
        search: {term: term, type: type}
    }
}

export function loadSearchStatus(){
    return {
        types: [LOAD_SEARCH_STATUS, LOAD_SEARCH_STATUS_SUCCESS, LOAD_SEARCH_STATUS_FAIL],
        promise: (client) => client.get('/book_report/search_status')
    }
}