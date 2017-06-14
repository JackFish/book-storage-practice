import { put, call, takeEvery } from 'redux-saga/effects'
import{ delay } from 'redux-saga'
import {increment, incrementIfOdd, decrement} from '../reducers/count';

export function* incrementAsync() {
    yield call(delay, 1000)
    yield put(increment())
}

export default function* rootSaga() {
    yield takeEvery('INCREMENT_ASYNC', incrementAsync)
}
