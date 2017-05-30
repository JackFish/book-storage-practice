/**
 * Created by ksb on 2017. 5. 30..
 */
import ApiClient from '../../../helpers/ApiClient';
const client = new ApiClient();

const userAsyncValidate = (values, dispatch) => {
    let promise1 = new Promise((resolve, reject) => {
        client.get("/user/exist/email/" + encodeURIComponent(values.email + ".last")).then(
            (result) => {
                resolve();
            },
            (error) => {
                reject({email: '이미 등록된 이메일입니다.'})
            }
        ).catch((error)=> {
            console.error(error);
        });
    });

    let promise2 = new Promise((resolve, reject) => {
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

    return Promise.all([promise1, promise2]).then(function (values) {
        return values;
    });
};

export default userAsyncValidate;