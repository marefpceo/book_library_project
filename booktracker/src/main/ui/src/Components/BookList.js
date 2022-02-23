import BookCard from "./BookCard"

const BookList = () => {
  return (
    <section className="md:container md:mx-auto mx-auto">
        <div className="grid grid-cols-5 gap-6 mt-10">
            <BookCard />
            <BookCard />
            <BookCard />
            <BookCard />
            <BookCard />
        </div>
    </section>
  )
}

export default BookList