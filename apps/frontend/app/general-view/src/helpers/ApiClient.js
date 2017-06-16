import superagent from 'superagent/lib/client';
// import config from '../config';
const config = {apiHost:"http://localhost:8080",apiPort:"8080",secHost:"http://localhost:8081",secPort:"8081"};

const methods = ['get', 'post', 'put', 'patch', 'del'];

function formatUrl(path) {
	const adjustedPath = path[0] !== '/' ? '/' + path : path;

	if(path.startsWith('/auth')){
		return config.secHost + adjustedPath.replace('/auth', '');
	}

	return config.apiHost + adjustedPath;
}

export default class ApiClient {
	constructor(req) {
		methods.forEach((method) =>
			this[method] = (path, {params, data} = {}) => new Promise((resolve, reject) => {
				const startedAt = new Date().getTime();
				const request = superagent[method](formatUrl(path));

				if (params) {
					request.query(params);
				}

				if (this.constructor.xAuthToken) {
					request.set('X-AUTH-TOKEN', this.constructor.xAuthToken);
				}

				if (data) {
					request.send(data);
				}

				request.end((err, {body} = {}) =>
					{
						if(__DEV__){
							const end = new Date().getTime();
							const duration = end - startedAt;
						}

						if(err){
							return reject(body || err);
						}
						return resolve(body);
					}
				);
			}));
	}
	setXAuthToken(xAuthToken) {
		this.constructor.xAuthToken = xAuthToken;
	}
}
