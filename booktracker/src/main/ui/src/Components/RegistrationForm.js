import { React, useEffect, useState } from 'react'
import { Formik, Form } from 'formik'
import { Link } from "react-router-dom"
import TextField from './TextField'
import * as Yup from 'yup'
import axios from 'axios'

function RegistrationForm() {

  const validate = Yup.object({
    firstName: Yup.string()
      .max(15, 'Must be 15 characters or less')
      .required('Required'),
    lastName: Yup.string()
      .max(20, 'Must be 20 characters or less')
      .required('Required'),
    userName: Yup.string()
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
  return ( <Formik
    initialValues={{
      firstName: '',
      lastName: '',
      userName: '',
      email: '',
      password: '',
      confirmPassword: ''
    }}
    validationSchema={validate}
    onSubmit={async (values) => {
      console.log(values)

      const data = {
        user: [
          {
            register: {
              firstName: values.firstName,
              lastName: values.lastName,
              userName: values.userName,
              email: values.email,
              password: values.password,
              confirmPassword: values.confirmPassword,
            },
          },
        ],
      }
      await axios.post('http://localhost:8000/api', data)
        .then(response => {
          console.log("Registered Successfully!")
        })
        .catch(error => {
          console.log(error)
        })
    }}
  >
    {formik => (
      <div>
        <h1 className="text-center text-4xl text-gray-500 mb-10">Register</h1>
        <Form action="" method='POST' onSubmit={formik.handleSubmit} className="flex justify-center flex-col self-center shadow-md bg-white rounded px-8 pt-6 pb-8 mb-4 font-['Roboto]">
          <div className="flex gap-4 mb-3">
            <div className="flex-1">
              <TextField type="text"
                name="firstName"
                label="First Name"
                value={formik.values.firstName}
              />
            </div>
            <div className="flex-1">
              <TextField type="text"
                name="lastName"
                label="Last Name"
                value={formik.values.lastName}
              />
            </div>
          </div>
          <div className="flex gap-4 mb-3">
          <div className="flex-1">
            <TextField type="text"
              name="userName"
              label="User Name"
              value={formik.values.userName}
            />
          </div>
          <div className="flex-1">
            <TextField type="email"
              name="email"
              label="Email Address"
              value={formik.values.email}
            />
          </div>
          </div>
          <div className="mb-3">
            <TextField type="password"
              name="password"
              label="Password"
              value={formik.values.password}
            />
          </div>
          <div className="mb-3">
            <TextField type="password"
              name="confirmPassword"
              label="Confirm Password"
              value={formik.values.confirmPassword}
            />
          </div>
          <div className="flex items-center">
            <button type="submit" className="text-white w-full rounded-full bg-blue-500 hover:bg-blue-700:text-white font-bold py-2 px-4 round">Register</button>
          </div>
          <div className="mt-14 text-center">
            Already have an account? <Link to="/login" className="text-bold text-blue-500">Login Here</Link>
          </div>
        </Form>
      </div>
    )}
  </Formik> )
}

export default RegistrationForm;
