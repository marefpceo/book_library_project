import SearchForm from '../../Components/SearchForm';

function homepage() {
    return <section className="flex flex-col justify-center items-center h-screen">
            <h1 className="text-center text-5xl text-bold">Book Tracker</h1>
            <SearchForm />
    </section>;
}

export default homepage;
