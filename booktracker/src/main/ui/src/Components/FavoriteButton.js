import  { faHeart } from "@fortawesome/free-solid-svg-icons"
import { FontAwesomeIcon } from "@fortawesome/react-fontawesome"
import { Component } from "react";

// const showMessage = () => {
//     alert("Added to your favorites!");
// };

class FavoriteButton extends Component{

state = { liked: false};

toggle = () => {
    let localLiked = this.state.liked;
    localLiked = !localLiked;
    this.setState({liked: localLiked});
};

render() {
    return(
        <div className="flex justify-between pt-1" onClick={() => this.toggle()}>

            {this.state.liked === false ? (<FontAwesomeIcon icon = {faHeart} />)
            : (<FontAwesomeIcon icon = {faHeart} transform="grow-4" color="red"/>)}
        </div>
    );
}
}

export default FavoriteButton;