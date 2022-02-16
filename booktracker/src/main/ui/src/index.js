<<<<<<< HEAD
import React from 'react'
import ReactDOM from 'react-dom'
import { BrowserRouter as Router, Routes, Route } from "react-router-dom"
import './index.css'
import App from './App'
import { Login, Registration, Search } from './Pages/index'

ReactDOM.render(
  <Router>
=======
import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import './index.css';
import App from './App';
import HomeP<TextField type="email"
name="email"
label="Email Address"
/>age from './Pages/HomePage/homepage'
import Login from './Pages/Login/login'
import Registration from './Pages/Registration/registration'

ReactDOM.render(
  <BrowserRouter>
>>>>>>> main
    <Routes>
      <Route path="/" element={<App />} />
      <Route path="login" element={<Login />} />
      <Route path="register" element={<Registration />} />
<<<<<<< HEAD
      <Route path="search" element={<Search />} />
    </Routes>
  </Router>,
=======
    </Routes>
  </BrowserRouter>,
>>>>>>> main
  document.getElementById('root')
);
