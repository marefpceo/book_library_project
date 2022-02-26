import { FaRegHeart } from "react-icons/fa"
import { FaHeart } from "react-icons/fa"
import { Component } from "react";

const addMessage = () => {
    alert("Added to favorites!");
};

const removeMessage = () => {
    alert("Removed from favorites!");
};

class FavoriteButton extends Component{

state = { liked: false};

toggle = () => {
    let localLiked = this.state.liked;
    localLiked = !localLiked;
    this.setState({liked: localLiked});

    if (localLiked == true){
        addMessage();
    };

    if (localLiked == false){
        removeMessage();
    };   
};

render() {
    return(
        <div className="flex justify-between pt-1" onClick={() => this.toggle()}>

            {this.state.liked === false ? (<FaRegHeart />)
            : (<FaHeart color="red"/>)}
        </div>
    );
}
}

export default FavoriteButton;