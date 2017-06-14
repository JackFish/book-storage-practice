/**
 * Created by ohjic on 2017-06-14.
 */
export const INCREMENT = 'INCREMENT'
export const INCREMENT_IF_ODD = 'INCREMENT_IF_ODD'
export const DECREMENT = 'DECREMENT'

const initialState = {
    count: 0
}
export default function counter(state = initialState, action) {
    switch (action.type) {
        case 'INCREMENT':
            return {
                count: state.count + 1
            }
        case 'INCREMENT_IF_ODD':
            return {
                count: (state.count % 2 !== 0) ? state.count + 1 : state.count
            }
        case 'DECREMENT':
            return {
                count: state.count - 1
            }
        default:
            return state
    }
}

export function increment() {
    return {
        type: INCREMENT
    }
}

export function incrementIfOdd() {
    return {
        type: INCREMENT_IF_ODD
    }
}

export function decrement() {
    return {
        type: DECREMENT
    }
}
