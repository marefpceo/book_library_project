import React from 'react'
import { Link } from "react-router-dom"
import { Formik } from 'formik'
import TextField from './TextField'
import * as Yup from 'yup'
import axios from 'axios'

function LoginForm() {
  const validate = Yup.object({
    email: Yup.string()
      .email('Email is invalid!')
      .required('Email is required'),
    password: Yup.string()
      .min(6, 'Password must be at least 6 characters')
      .required('Password is required'),
  });
  return <Formik
    initialValues={{
      email: '',
      password: '',
    }}
    validationSchema={validate}
    onSubmit={async (values) => {
      console.log(values)

      const checkUser = {
        userName: values.userName,
        password: values.password,
      }

      await axios.get('http://localhost:8000/api/user/register', checkUser)
        .then(response => {
          console.log(response)
        })
    }}
  >
    {formik => (
      <div>
        <h1 className="text-center text-4xl text-gray-500 mb-10">Login</h1>
        <form action="" method='GET' onSubmit={formik.handleSubmit} className="w-96 flex justify-center flex-col self-center shadow-md bg-white rounded px-8 pt-6 pb-8 mb-4 font-['Roboto]">
          <div className="mb-3">
            <TextField type="text"
              name="userName"
              label="User Name"
              value={formik.values.userName}
            />
          </div>
          <div className="mb-3">
            <TextField type="password"
              name="password"
              label="Password"
              value={formik.values.password}
            />
          </div>
          <div className="flex items-center">
            <button type="submit" className="text-white w-full rounded-full bg-blue-500 hover:bg-blue-700:text-white font-bold py-2 px-4 round">Login</button>
          </div>
          <div className="mt-14 text-center">
            Are you new? <Link to="/register" className="text-bold text-blue-500">Register</Link>
          </div>
        </form>
      </div>
    )}
  </Formik>
}

export default LoginForm;
