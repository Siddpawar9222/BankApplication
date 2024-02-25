import axios from "axios";

const axiosInstance = axios.create({
  baseURL: "http://localhost:8080",
});

// Add a request interceptor
axiosInstance.interceptors.request.use(
  (config) => {
    const token = sessionStorage.getItem("jwtToken");
    if (token) {
      config.headers.Authorization = `Bearer ${token}`;
    }
    const xsrf = sessionStorage.getItem("XSRF-TOKEN");
    if (xsrf) {
      config.headers['X-XSRF-TOKEN'] = xsrf; 
    }
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

export default axiosInstance;
