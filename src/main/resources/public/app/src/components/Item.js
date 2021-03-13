import { useState } from 'react';
import './Item.css'

function Item(props) {

  const handleClick = e => {
  }

  return (
    <div
      key={props.id}
      className="item"
      title={props.data.name}
      onClick={handleClick}
    >
      <span className="item-name">{props.data.name}</span>
      <span className="item-friends">Number of friends: {props.data.friends.length}</span>
      <a href={props.data.shortenedUrl} className="item-shortened-url" target="_blank">{props.data.shortenedUrl}</a>
    </div>
  )
}

export default Item