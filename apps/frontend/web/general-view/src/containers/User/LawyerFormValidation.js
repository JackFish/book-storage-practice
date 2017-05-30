/**
 * Created by gwonseongbong on 8/18/16.
 */
import {required, email} from '../../helpers/validation'

function checkBlank(value) {
    if (value && value.trim() === '') {
        return '공백만 입력은 안됩니다.';
    }
}

const lawyerFormValidation = values => {
    const errors = {}
    if (values.user) {
        errors.user = {};
        if (!errors.user.email) {
            errors.user.email = required(values.user.email);
        }
        if (!errors.user.email) {
            errors.user.email = email(values.user.email);
        }

        if (!values.idx && !errors.user.password) {
            errors.user.password = required(values.user.password);
        }
        if (!errors.user.password) {
            errors.user.password = checkBlank(values.user.password);
        }

        if (!errors.user.userName) {
            errors.user.userName = required(values.user.userName);
        }
        if (!errors.user.userName) {
            errors.user.userName = checkBlank(values.user.userName);
        }

        if(values.lawyerLawFirmList){
            errors.lawyerLawFirmList = [];
            values.lawyerLawFirmList.forEach((value, i) => {
                if (!errors.lawyerLawFirmList[i]) {
                    errors.lawyerLawFirmList[i] = required(value.lawFirm.name);
                }
                if (!errors.lawyerLawFirmList[i]) {
                    errors.lawyerLawFirmList[i] = checkBlank(value.lawFirm.name);
                }
            });

            if(errors.lawyerLawFirmList.filter(value => value!==undefined).length === 0 &&
                values.lawyerLawFirmList.filter(value => value && value.workState === "WORK").length >= 2){
                values.lawyerLawFirmList.forEach((value, i) => {
                    if (value.workState === "WORK") {
                        errors.lawyerLawFirmList[i] = "재직중인 로펌은 하나만 가능합니다.";
                    }
                });
            }
        }
    }
    return errors
}

export default lawyerFormValidation;