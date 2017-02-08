/**
 * Created by ksb on 16. 11. 11.
 */
import ApiClient from '../../../helpers/ApiClient';
const client = new ApiClient();

const bookRecordFormAsyncValidate = (values, dispatch) => {
    return new Promise((resolve, reject) => {
        if(values.idx){
            resolve();
        } else {
            client.get("/book_record/exist/subject/" + values.subject).then(
                () => {
                    resolve();
                },
                (error) => {
                    reject({subject: error.message});
                }
            ).catch((error)=> {
                console.log(error);
            });
        }
    })
};

export default bookRecordFormAsyncValidate;