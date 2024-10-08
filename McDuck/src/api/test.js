import axios from 'axios'

const api = axios.create({
  baseURL: 'http://localhost:8080',
  withCredentials: true,
  headers: {
    Accept: 'application/json',
    'Content-Type': 'application/json'
  }
})

export default {
  getCategoryProducts(category) {
    return api.post(`/api/getCategory`, category).then((response) => response.data)
  }
}
