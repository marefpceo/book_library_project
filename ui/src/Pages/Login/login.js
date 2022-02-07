import React from "react"
import LoginForm from "../../Components/LoginForm"
import Navbar from "../../Components/Navbar";

const login = () => {
  return <>
    <Navbar />
    <div className="grid place-items-center h-screen">
      <LoginForm />
    </div>
  </>;
};

export default login;
