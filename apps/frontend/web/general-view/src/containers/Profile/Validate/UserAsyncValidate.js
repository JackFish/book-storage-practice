/**
 * Created by ksb on 2017. 5. 30..
 */
import ApiClient from '../../../helpers/ApiClient';
const client = new ApiClient();

const userAsyncValidate = (values, dispatch) => {
    let promise = new Promise((resolve, reject) => {
        client.get("/user/exist/name/" + values.name).then(
            (result) => {
                resolve();
            },
            (error) => {
                reject({name: '이미 등록된 이름입니다.'})
            }
        ).catch((error)=> {
            console.error(error);
        });
    });

    return Promise.all([promise]).then(function (values) {
        return values;
    });
};

export default userAsyncValidate;