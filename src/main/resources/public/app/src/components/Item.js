import './Item.css'
import {
  useHistory 
} from "react-router-dom";
function Item(props) {

  const history = useHistory();

  const handleClick = e => {
    history.push("/"+props.data.username);
  }

  return (
    <div
      key={props.id}
      className="item"
      title={props.data.name}
      onClick={handleClick} >
      <span className="item-name">{props.data.name}</span>
      <span className="item-friends">{props.data.friends ? 'Number of friends:' + props.data.friends.length
        : ''}
      </span>
      <a href={props.data.shortenedUrl} className="item-shortened-url" target="_blank" rel="noreferrer" >{props.data.shortenedUrl}</a>
    </div>
  )
}

export default Item