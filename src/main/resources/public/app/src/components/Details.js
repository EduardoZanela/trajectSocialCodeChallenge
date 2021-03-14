import { useState, useEffect, useContext } from 'react';
import {UserContext} from '../contexts/UserContextProvider.js';
import { v4 as uuidv4 } from 'uuid';
import Item from './Item.js';
import './Item.css';
import {
  useParams
} from "react-router-dom";
import Api from '../api/Api.js';
import Header from './Header.js';
import SearchBar from './SearchBar.js';
import './Details.css';

function Details() {

  const { state:userstate, dispatch:userDispatch} = useContext(UserContext);
  const [user, setUser] = useState({ friends: [], headings: [] });

  let { id } = useParams();

  useEffect(() => {
    getUser(id);
  }, [id])

  const isFriend = () => {
    if(!userstate.user.name){
      return false;
    }
    let haveFriend = userstate.user.friends.filter(friend => friend.username === id).length > 0
    if(userstate.user.username === id || haveFriend ){
      return true;
    }
    return false;
  };

  const getUser = async (id) => {
    let res = await Api.getUser(id);
    if (res) {
      setUser(res);
    }
  }

  const addFriend = async () => {
    let res = await Api.addFriend(userstate.user.username, id);
    if (res.error) {
      alert(res.error);
      return;
    }
    userDispatch({type: 'setUser', payload: {user: res}});
    userDispatch({type: 'setLogged', payload: {logged: true}});
    alert("Successfully added friend");
  }

  const onClickAddFriend = () => {
    if(!userstate.user.username){
      alert("Set user first");
      return;
    }
    addFriend();
  }

  return (
    <div className="container">
      <Header />
      <SearchBar />
      <div className="item item-first">
        <span className="item-name">{user.name}</span>
        <a href={user.url} className="item-shortened-url" target="_blank" rel="noreferrer" >Website URL</a>
        <a href={user.shortenedUrl} className="item-shortened-url" target="_blank" rel="noreferrer" >{user.shortenedUrl}</a>
        {user.headings.map((heading) => (
          <span className="item-heading" key={uuidv4()}>{heading.heading}</span>
        ))}
      </div>
      {!isFriend() ? <h3 className="item-link" onClick={onClickAddFriend}>Add Friend</h3> : null}
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