import axios from 'axios'
import { serverIp } from "../../public/config";

const request = axios.create({
	baseURL: `http://${serverIp}:8888`,
	timeout: 30000
})

request.interceptors.request.use(config => {
	config.headers['Content-Type'] = 'application/json;charset=utf-8';

	return config
}, error => {
	return Promise.reject(error)
});

export default request
