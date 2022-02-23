import LoginNavbar from '../../Components/LoginNavbar'
import SearchForm from '../../Components/SearchForm'
import BookList from '../../Components/BookList'

const Dashboard = () => {
    return (
        <section>
            <LoginNavbar />
            <SearchForm className="mt-16"/>
            <BookList />
        </section>
    )
}

export default Dashboard