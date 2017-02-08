/**
 * Created by ksb on 17. 2. 4.
 */
const SHOW_BOOK_RECORD_MODAL = "modal/SHOW_BOOK_RECORD_MODAL";
const HIDE_BOOK_RECORD_MODAL = "modal/HIDE_BOOK_RECORD_MODAL";

const initialState = {
    isShowBookRecordModal: false,
};

export default function reducer(state = initialState, action = {}) {
    switch (action.type) {
        case SHOW_BOOK_RECORD_MODAL:
            return {
                ...state,
                isShowBookRecordModal: true,
            };
        case HIDE_BOOK_RECORD_MODAL:
            return {
                ...state,
                isShowBookRecordModal: false,
            };
        default:
            return state;
    }
}

export function showBookRecordModal() {
    return {
        type: SHOW_BOOK_RECORD_MODAL
    }
}

export function hideBookRecordModal(){
    return {
        type: HIDE_BOOK_RECORD_MODAL
    }
}
