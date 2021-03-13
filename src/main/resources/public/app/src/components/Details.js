import { useState, useEffect } from 'react';
import { v4 as uuidv4 } from 'uuid';
import Item from './Item.js';
import './Item.css';
import {
  useParams
} from "react-router-dom";
import Api from '../api/Api.js';
import Header from './Header.js';
import './Details.css';

function Details() {
  const [user, setUser] = useState({ friends: [], headings: [] });

  let { id } = useParams();

  useEffect(() => {
    getUser(id);
    console.log('1');
  }, [id])


  const getUser = async (id) => {
    let res = await Api.getUser(id);
    if (res) {
      setUser(res);
    }
  }

  return (
    <div className="container">
      <Header />
      <div className="item item-first">
        <span className="item-name">{user.name}</span>
        <a href={user.url} className="item-shortened-url" target="_blank" rel="noreferrer" >Website URL</a>
        <a href={user.shortenedUrl} className="item-shortened-url" target="_blank" rel="noreferrer" >{user.shortenedUrl}</a>
        {user.headings.map((heading) => (
          <span className="item-heading" key={uuidv4()}>{heading.heading}</span>
        ))}
      </div>
      <h2 className="subtitle">Friends:</h2>
      <div className="details-friends">
        {user.friends.map((user) => (
          <Item id={uuidv4()} data={user} key={uuidv4()} />
        ))}
      </div>
    </div>
  )
}

export default Details