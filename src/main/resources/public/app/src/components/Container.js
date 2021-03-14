import { useState, useEffect } from 'react';
import Api from '../api/Api.js'
import Header from './Header.js'
import Search from './Search.js'
import Results from './Results.js'
import './Container.css'

function Container() {
  const [users, setUsers] = useState([]);
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

  const findUsers = async (val) => {
    if(val) {
      let res = await Api.findUsers('eduardozanela', val);
      if(res){
        setFilteredUsers(res);
        return;
      }
    }
    setFilteredUsers([]);
  }

  const onChange = (val, key) => {
    if (key === 'Enter') {
      findUsers(val);
    }
  }
  return (
    <div className="container">
      <Header />
      <Search onChange={onChange} />
      <Results users={filteredUsers.length > 0 ? filteredUsers : users} />
    </div>
  )
}

export default Container