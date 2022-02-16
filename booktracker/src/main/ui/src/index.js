import React from 'react'
import ReactDOM from 'react-dom'
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"
import './index.css'
import App from './App'
import { Login, Registration, Search } from './Pages/index'

ReactDOM.render(
  <Router>
    <Routes>
      <Route path="/" element={<App />} />
      <Route path="login" element={<Login />} />
      <Route path="register" element={<Registration />} />
      <Route path="search" element={<Search />} />
    </Routes>
  </Router>,
  document.getElementById('root')
);
