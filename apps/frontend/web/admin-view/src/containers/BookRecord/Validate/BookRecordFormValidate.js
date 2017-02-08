/**
 * Created by ksb on 16. 11. 11.
 */
import {required} from '../../../helpers/validation'

function checkBlank(value) {
    if (value && value.trim() === '') {
        return '공백만 입력은 안됩니다.';
    }
}

const bookRecordFormValidate = values => {
    const errors = {}

    if (!errors.subject) {
        errors.subject = required(values.subject);
    }

    if (!errors.subject) {
        errors.subject = checkBlank(values.subject);
    }

    return errors
}


export default bookRecordFormValidate