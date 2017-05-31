/**
 * Created by ksb on 2017. 5. 30..
 */
import {required, email} from '../../../helpers/validation'

function checkBlank(value) {
    if (value && value.trim() === '') {
        return '공백만 입력은 안됩니다.';
    }
}

const userValidation = values => {
    const errors = {}
    if (!values.uniqueId && !errors.password) {
        errors.password = required(values.password);
    }
    if (!errors.password) {
        errors.password = checkBlank(values.password);
    }

    if (!errors.userName) {
        errors.userName = required(values.userName);
    }
    if (!errors.userName) {
        errors.userName = checkBlank(values.userName);
    }
    return errors
}

export default userValidation;