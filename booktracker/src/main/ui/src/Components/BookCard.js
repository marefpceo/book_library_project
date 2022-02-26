import {
    faStar, faComments, faHeart
  } from "@fortawesome/free-solid-svg-icons"
  import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
  import BookBanner from '../assets/harrypotterprisonerazkabanbanner.jpg'
  import FavoriteButton from "./FavoriteButton"

const BookCard = () => {

    return (
        <section className="grid gap-1">
            <img src={BookBanner} alt="" className="" style={{width: '100%'}}/>
            <h3 className="text-2xl capitalize pt-3 font-bold text-gray-600">harry potter and the prisoner of azkaban</h3>
            <h6 className="text-lg italic">First edition</h6>
            <p>by J. K. Rowling</p>
            <div className="flex gap-2 pt-3 ">
                <FontAwesomeIcon icon={faStar} className="text-yellow-500"/>
                <FontAwesomeIcon icon={faStar} className="text-yellow-500"/>
                <FontAwesomeIcon icon={faStar} className="text-yellow-500"/>
                <FontAwesomeIcon icon={faStar} className="text-yellow-500"/>
                <FontAwesomeIcon icon={faStar} className="text-yellow-500"/>
            </div>
            <div className="flex justify-between pt-3">
                <FontAwesomeIcon icon={faComments} />
                <FavoriteButton />
            </div>
            <img src="" alt="" />
        </section>
    )
}

export default BookCard