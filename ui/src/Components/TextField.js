import React from 'react'
import { ErrorMessage, useField } from 'formik'

function TextField({ label, type, ...props }) {
    const [field, meta] = useField(props)
    return <>
        <div className='flex flex-col mb-5'>
            <label htmlFor={field.name} className="block text-gray-700 text-sm mb-2">{label}</label>
            <input {...field} {...props} 
                type={type}
                placeholder={label}
                className={`shadow appearance-none border rounded w-full py-2 px-2 text-gray-700 leading-tight focus:outline-none focus:shadow-outline ${meta.touched && meta.error && 'bg-red-50 border border-red-500 text-red-900 placeholder-red-700 text-sm rounded-lg focus:ring-red-500 focus:border-red-500 block w-full p-2.5 dark:bg-red-100 dark:border-red-400'}`}
                autoComplete='off' />
            <ErrorMessage component='div' name={field.name} className="text-red-900" />
        </div>
    </>
}

export default TextField
