import React from 'react'
import { Formik, Form } from 'formik'
import { Link } from "react-router-dom"
import TextField from './TextField'
import * as Yup from 'yup'

// const USER_REGEX = /^[a-zA-Z][a-zA-Z0-9-_]{3,23}$/
// const PWD_REGEX = /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#$%]).{8,24}&/

function RegistrationForm() {
  const validate = Yup.object({
    firstName: Yup.string()
      .max(15, 'Must be 15 characters or less')
      .required('Required'),
    lastName: Yup.string()
      .max(20, 'Must be 20 characters or less')
      .required('Required'),
    email: Yup.string()
      .email('Email is invalid!')
      .required('Email is required'),
    password: Yup.string()
      .min(6, 'Password must be at least 6 characters')
      .required('Password is required'),
    confirmPassword: Yup.string()
      .oneOf([Yup.ref('password'), null], 'Password must match')
      .required('Confirm Password is required'),
  });
  return <Formik
    initialValues={{
      firstName: '',
      lastName: '',
      email: '',
      password: '',
      confirmPassword: ''
    }}
    validationSchema={validate}
    onSubmit={values => {
      console.log(values)
    }}
  >
    {formik => (
      <div>
        <h1 className="text-center text-4xl text-gray-500 mb-10">Register</h1>
        <Form action="" className="flex justify-center flex-col self-center shadow-md bg-white rounded px-8 pt-6 pb-8 mb-4 font-['Roboto]">
          <div className="flex gap-4 mb-3">
            <div className="flex-1">
              <TextField type="text"
                name="firstName"
                label="First Name"
              />
            </div>
            <div className="flex-1">
              <TextField type="text"
                name="lastName"
                label="Last Name"
              />
            </div>
          </div>
          <div className="mb-3">
            <TextField type="email"
              name="email"
              label="Email Address"
            />
          </div>
          <div className="mb-3">
            <TextField type="password"
              name="password"
              label="Password"
            />
          </div>
          <div className="mb-3">
            <TextField type="password"
              name="confirmPassword"
              label="Confirm Password"
            />
          </div>
          <div className="flex items-center">
            <button className="text-white w-full rounded-full bg-blue-500 hover:bg-blue-700:text-white font-bold py-2 px-4 round">Register</button>
          </div>
          <div className="mt-14 text-center">
            Already have an account? <Link to="/login" className="text-bold text-blue-500">Login Here</Link>
          </div>
        </Form>
      </div>
    )}
  </Formik>
}

export default RegistrationForm;
