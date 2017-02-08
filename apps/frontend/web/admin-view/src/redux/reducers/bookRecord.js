/**
 * Created by ksb on 16. 11. 11.
 */
const FIND_SUMMARY_LIST = "bookRecord/FIND_SUMMARY_LIST";
const FIND_SUMMARY_LIST_SUCCESS = "bookRecord/FIND_SUMMARY_LIST_SUCCESS";
const FIND_SUMMARY_LIST_FAIL = "bookRecord/FIND_SUMMARY_LIST_FAIL";

const FIND_INFINITE_SUMMARY_LIST = "bookRecord/FIND_INFINITE_SUMMARY_LIST";
const FIND_INFINITE_SUMMARY_LIST_SUCCESS = "bookRecord/FIND_INFINITE_SUMMARY_LIST_SUCCESS";
const FIND_INFINITE_SUMMARY_LIST_FAIL = "bookRecord/FIND_INFINITE_SUMMARY_LIST_FAIL";

const FIND_DETAIL_ONE = "bookRecord/FIND_DETAIL_ONE";
const FIND_DETAIL_ONE_SUCCESS = "bookRecord/FIND_DETAIL_ONE_SUCCESS";
const FIND_DETAIL_ONE_FAIL = "bookRecord/FIND_DETAIL_ONE_FAIL";

const CREATE = "bookRecord/CREATE";
const CREATE_SUCCESS = "bookRecord/CREATE_SUCCESS";
const CREATE_FAIL = "bookRecord/CREATE_FAIL";

const UPDATE = "bookRecord/UPDATE";
const UPDATE_SUCCESS = "bookRecord/UPDATE_SUCCESS";
const UPDATE_FAIL = "bookRecord/UPDATE_FAIL";

const VISIBLE = "bookRecord/VISIBLE";
const VISIBLE_SUCCESS = "bookRecord/VISIBLE_SUCCESS";
const VISIBLE_FAIL = "bookRecord/VISIBLE_FAIL";

const REMOVE = "bookRecord/REMOVE";
const REMOVE_SUCCESS = "bookRecord/REMOVE_SUCCESS";
const REMOVE_FAIL = "bookRecord/REMOVE_FAIL";

const RESET = "bookRecord/RESET";

const SEARCH = "bookRecord/SEARCH";

const LOAD_SEARCH_STATUS = "bookRecord/LOAD_SEARCH_STATUS";
const LOAD_SEARCH_STATUS_SUCCESS = "bookRecord/LOAD_SEARCH_STATUS_SUCCESS";
const LOAD_SEARCH_STATUS_FAIL = "bookRecord/LOAD_SEARCH_STATUS_FAIL";

const initialState = {
    loading: false,
    summaryList: {},
    infiniteSummaryList: {},
    detailOne: {imageGroup: {imageList: []}},
    searchStatus: [],
    search: {}
};

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
        case FIND_INFINITE_SUMMARY_LIST:
            return {
                ...state,
                loading: true
            };
        case FIND_INFINITE_SUMMARY_LIST_SUCCESS:
            if(action.page == 0){
                return {
                    ...state,
                    infiniteSummaryList: action.result,
                    loading: false
                };
            } else {
                return {
                    ...state,
                    infiniteSummaryList: {
                        ...action.result,
                        content: [...state.infiniteSummaryList.content, ...action.result.content]
                    },
                    loading: false
                };
            }
        case FIND_INFINITE_SUMMARY_LIST_FAIL:
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
                detailOne: initialState.detailOne
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
        promise: (client) => client.get('/book_record?page=' + page + '&term=' + term + '&type=' + type)
    }
}

export function findInfiniteSummaryList(page = 0, term = "", type = ""){
    return {
        types: [FIND_INFINITE_SUMMARY_LIST, FIND_INFINITE_SUMMARY_LIST_SUCCESS, FIND_INFINITE_SUMMARY_LIST_FAIL],
        promise: (client) => client.get('/book_record?page=' + page + '&term=' + term + '&type=' + type),
        page: page
    }
}

export function findDetailOne(idx){
    return {
        types: [FIND_DETAIL_ONE, FIND_DETAIL_ONE_SUCCESS, FIND_DETAIL_ONE_FAIL],
        promise: (client) => client.get('/book_record/' + idx)
    }
}

export function create(bookRecord){
    const createImageList = bookRecord.imageGroup.imageList.filter(image => image.file != null);
    const attach = createImageList.map(image => image.file);
    const imageList = createImageList.map(image => {
        return {sortOrder: image.sortOrder};
    });

    return {
        types: [CREATE, CREATE_SUCCESS, CREATE_FAIL],
        promise: (client) => client.post('/book_record', {
            data: {...bookRecord, imageGroup: {imageList: imageList}},
            attach: attach
        })
    }
}

export function update(bookRecord){
    const createImageList = bookRecord.imageGroup.imageList.filter(image => image.file != null);
    const updateImageList = bookRecord.imageGroup.imageList.filter(image => image.file == null);
    const attach = createImageList.map(image => image.file);
    const imageList = createImageList.map(image => {
        return {sortOrder: image.sortOrder};
    }).concat(updateImageList.map(image => {
        return {imageIdx: image.imageIdx, sortOrder: image.sortOrder};
    }));

    return {
        types: [UPDATE, UPDATE_SUCCESS, UPDATE_FAIL],
        promise: (client) => client.patch('/book_record', {
            data: {...bookRecord, imageGroup: {imageList: imageList}},
            attach: attach
        }),
        bookRecord: bookRecord
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
        promise: (client) => client.patch('/book_record/visible',{
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
        promise: (client) => client.del('/book_record/' + idx),
        idx: idx
    }
}

export function reset(){
    return {
        type: RESET
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
        promise: (client) => client.get('/book_record/search_status')
    }
}