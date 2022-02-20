import React from 'react'
import ReactDOM from 'react-dom'
import { BrowserRouter, Routes, Route } from "react-router-dom"
import './index.css'
import App from './App'
import { AuthProvider } from './context/AuthProvider'
import Login from './Pages/Login/login'
import Registration from './Pages/Registration/registration'

ReactDOM.render(
    <BrowserRouter>
    <AuthProvider>
      <Routes>
        <Route path="/" element={<App />} />
        <Route path="login" element={<Login />} />
        <Route path="register" element={<Registration />} />
      </Routes>
      </AuthProvider>
    </BrowserRouter>,
  document.getElementById('root')
);
