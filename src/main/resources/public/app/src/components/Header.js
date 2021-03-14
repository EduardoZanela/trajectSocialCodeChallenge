import { useContext } from 'react';
import {UserContext} from '../contexts/UserContextProvider.js';
import './Header.css'
import { Link } from 'react-router-dom';


function Header() {
  const { state:userstate, dispatch:userDispatch} = useContext(UserContext);

  const onClick = () => {
    userDispatch({type: 'setUser', payload: {user: {}}});
    userDispatch({type: 'setLogged', payload: {logged: false}});
  }

  return (
    <div className="header">
      <h1>
        Friends <span role="img" aria-label="Unicorn Emoji">🦄</span>
      </h1>
      <p>
        A simple tool for friends interesting topics.
      </p>
      <Link className="item-link" to="/" >Home</Link>
      {userstate.user.name
        ? <span className="item-link" onClick={onClick} >Sign out</span>
        : <Link className="item-link" to="/signup" >Create User</Link>
      }
    </div>
  )
}

export default Header