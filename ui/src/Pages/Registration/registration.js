import React from "react"
import RegistrationForm from "../../Components/RegistrationForm"
import Navbar from "../../Components/Navbar"

const registration = () => {
  return <>
    <Navbar />
    <div className="grid place-items-center h-screen">
      <RegistrationForm />
    </div>
  </>
}

export default registration
