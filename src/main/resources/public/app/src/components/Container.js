import { useState, useEffect } from 'react';
import Api from '../api/Api.js'
import Header from './Header.js'
import Search from './Search.js'
import Results from './Results.js'
import './Container.css'

function Container() {
  const [users, setUsers] = useState([]);
  const [searchQuery, setSearchQuery] = useState([]);
  const [filteredUsers, setFilteredUsers] = useState([]);
  
  useEffect(() => {
    getAllUsers();
  }, [])

  const getAllUsers = async () => {
    let res = await Api.getAllUsers();
    if(res){
      setUsers(res);
    }
  }

  const onChange = val => {
    setSearchQuery(val.toLowerCase()); 

  }
  return (
    <div className="container">
      <Header />
      <Search onChange={onChange} />
      <Results users={users} />
    </div>
  )
}

export default Container