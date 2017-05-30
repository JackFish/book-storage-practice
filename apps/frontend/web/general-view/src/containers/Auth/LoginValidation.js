/**
 * Created by gwonseongbong on 2016. 10. 24..
 */
import {createValidator, required, email} from '../../helpers/validation'

const loginValidation = createValidator({
    email: [required, email],
    password: [required]
});
export default loginValidation;