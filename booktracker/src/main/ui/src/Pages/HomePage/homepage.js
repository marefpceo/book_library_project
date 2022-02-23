import SearchForm from '../../Components/SearchForm'
import BookList from '../../Components/BookList';

function homepage() {
    return <section className="flex flex-col justify-center h-screen">
            {/* <h1 className="text-center text-5xl text-bold">Book Tracker</h1> */}
            <SearchForm />
            <BookList />
    </section>;
}

export default homepage;
