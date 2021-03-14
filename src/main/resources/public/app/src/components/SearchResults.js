import { useState, useEffect } from 'react';
import {
  useParams,
  useHistory
} from "react-router-dom";
import Api from '../api/Api.js';
import Header from './Header.js';
import SearchBar from './SearchBar.js';
import { v4 as uuidv4 } from 'uuid';

import './Container.css';

function SearchResults() {

  const history = useHistory();
  const [filteredUsers, setFilteredUsers] = useState([]);
  let { username, search } = useParams();

  useEffect(() => {
    findUsers(username, search);
  }, [username, search]);

  const findUsers = async (username, search) => {
    let res = await Api.findUsers(username, search);
    if (!res.error) {
      setFilteredUsers(res);
      return;
    }
    setFilteredUsers([]);
  }

  const onClickProfile = (username) => {
    handleNavigate(username);
  }

  const handleNavigate = (username) => {
    history.push("/details/" + username);
  }

  return (
    <div className="container">
      <Header />
      <SearchBar />

      {filteredUsers.map((user) => (
        <div className="item" onClick={() => onClickProfile(user.user.username)} >
          <Chain id={uuidv4()} findPath={user} key={uuidv4()} />
        </div>
      ))}

    </div>
  )
}

function Chain({ findPath }) {
  return (
    <span>
      {!findPath.next ? null :
        <Chain findPath={findPath.next} />
      }
      {!findPath.next ? null :
        <span> &#10140; </span>
      }
      {findPath.user.name}

    </span>
  );
}

export default SearchResults