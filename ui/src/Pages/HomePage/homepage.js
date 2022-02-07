import React from 'react'
import Navbar from '../../Components/Navbar'
import SearchForm from '../../Components/SearchForm';

function homepage() {
    return <>
        <Navbar />
        <div className="flex flex-col justify-center items-center h-screen">
            <h1 className="text-center text-5xl text-bold">Book Tracker</h1>
            <SearchForm />
        </div>
    </>;
}

export default homepage;
