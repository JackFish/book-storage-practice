/**
 * Created by gwonseongbong on 8/19/16.
 */
import ApiClient from '../../helpers/ApiClient';
const client = new ApiClient();

const lawyerFormAsyncValidate = (values, dispatch) => {
    return new Promise((resolve, reject) => {
        if(values.idx){
            resolve();
        } else {
            client.get("/lawyer/exist/email/" + encodeURIComponent(values.user.email + ".last")).then(
                (result) => {
                    if (result === 1) {
                        reject({user: {email: '이미 등록된 이메일입니다.'}});
                    } else {
                        resolve();
                    }
                },
                (error) => console.log(error)
            ).catch((error)=> {
                console.error(error);
            });
        }
    })
};

export default lawyerFormAsyncValidate;