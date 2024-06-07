import axios from "axios";
import { getToken } from "../Auth/Index";

export const BASE_URL = 'http://localhost:3839/api';

export const myAxios = axios.create({
  baseURL: BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

export const privateAxios=axios.create({
  baseURL:BASE_URL
})

privateAxios.interceptors.request.use(
  async (config) => {
    try {
      const token = await getToken();

      if (token) {
        // Set the Authorization header
        config.headers.Authorization = `Bearer ${token}`;
      }
      return config;
    } catch (error) {
      return Promise.reject(error);
    }
  },
  (error) => Promise.reject(error)
);
