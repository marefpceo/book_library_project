import React from 'react'
import { Routes, Route } from 'react-router-dom'
import RequireAuth from './Components/RequireAuth'
import Layout from './Components/layouts/Layout'
import { HomePage, Login, Registration, Dashboard, NotFoundPage } from './Pages/index'

function App() {
  return (
    <Routes>
      <Route path="/" element={<Layout />}>
        {/*public routes */}
        <Route path="login" element={<Login />} />
        <Route path="register" element={<Registration />} />
        <Route path="/" element={<HomePage />} />

        {/* 404 error handlers */}
        <Route path="*" element={<NotFoundPage />} />
      </Route>
      {/* protected routes */}
      <Route element={<RequireAuth />}>
        <Route path="/dashboard" element={<Dashboard />} />
      </Route>
    </Routes>
  );
}

export default App;
