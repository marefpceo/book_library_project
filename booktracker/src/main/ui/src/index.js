import React from 'react';
import ReactDOM from 'react-dom';
import { BrowserRouter, Routes, Route } from "react-router-dom";
import './index.css';
import App from './App';
import HomePage from './Pages/HomePage/homepage'
import Login from './Pages/Login/login'
import Registration from './Pages/Registration/registration'

ReactDOM.render(
  <BrowserRouter>
    <Routes>
      <Route path="/" element={<App />} />
      <Route path="login" element={<Login />} />
      <Route path="register" element={<Registration />} />
    </Routes>
  </BrowserRouter>,
  document.getElementById('root')
);
