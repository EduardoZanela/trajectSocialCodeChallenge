import { useState, useEffect } from 'react';
import Api from '../api/Api.js'
import Header from './Header.js'
import Results from './Results.js'
import SearchBar from './SearchBar.js';
import './Container.css';

function Container() {
  const [users, setUsers] = useState([]);

  useEffect(() => {
    getAllUsers();
  }, [])

  const getAllUsers = async () => {
    let res = await Api.getAllUsers();
    if(res){
      setUsers(res);
    }
  }

  return (
    <div className="container">
      <Header />
      <SearchBar />
      <Results users={users} />
    </div>
  )
}

export default Container