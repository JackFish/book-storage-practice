import { take, call, put, fork, race, takeEvery } from 'redux-saga/effects'
import{ delay } from 'redux-saga'
import {increment, incrementIfOdd, decrement} from '../reducers/count';
import ApiClient from '../helpers/ApiClient';

const client = new ApiClient();

export function * flow() {
    let request = yield take("FLOW")
    let {username, password} = request.data;
    alert("ASDASD");
}

function* runIncrementAsync() {
    yield call(delay, 1000)
    yield put(increment())
}

function* incrementAsync() {
    yield takeEvery('INCREMENT_ASYNC', runIncrementAsync)
}

function* test() {
    let request = yield take("TEST");
    // let {username, password} = request.data;
    /*try {
        const payload = yield call(client.get, '/question/test?username=username');
        // const payload = yield call(client.get, '/question/test');
        console.log(payload);
        //     yield put(success(payload));
    } catch (error) {
        console.log(error);
        //     yield put(failure(error));
    }*/
    try {
        const payload = yield call(client.post, '/question/test2', {data:{subject:"username"}});
        // const payload = yield call(client.post, '/question/test');
        console.log(payload);
        //     yield put(success(payload));
    } catch (error) {
        console.log(error);
        //     yield put(failure(error));
    }
}

export default function* rootSaga() {
    yield fork(flow);
    yield fork(incrementAsync);
    yield fork(test);
}
