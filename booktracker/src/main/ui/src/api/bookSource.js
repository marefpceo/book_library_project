import axios from 'axios'

export default axios.create({
  // baseURL: 'http://localhost:8080/'
  baseURL: 'http://openlibrary.org/search.json?author=tolkien'
})