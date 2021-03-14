import { useState, useContext } from 'react';
import {
  useHistory 
} from "react-router-dom";
import InputCustom from './InputCustom.js'
import {UserContext} from '../contexts/UserContextProvider.js';
import Api from '../api/Api.js';

function SearchBar() {
  const { state:userstate, dispatch:userDispatch} = useContext(UserContext);

  const [username, setUsername] = useState("");
  const [search, setSearch] = useState("");
  
  const history = useHistory();

  const handleNavigate = () => {
    history.push("/search/"+userstate.user.username+"/"+search);
  }

  const findUsers = async (val) => {
    if(!userstate.user.username || !search){
      alert('Set the user before any operation');
      return;
    }
    handleNavigate();
  }

  const findUser = async (val) => {
    if(username){
      let res = await Api.getUser(username);
      if(res && !res.error){
        userDispatch({type: 'setUser', payload: {user: res}});
        userDispatch({type: 'setLogged', payload: {logged: true}});
        return;
      }
    }
  }

  const onKeyDownSearch = (val, key) => {
    if (key === 'Enter') {
      findUsers(val);
    }
  }

  const onKeyDownUser = (val, key) => {
    if (key === 'Enter') {
      findUser(val);
    }
  }

  const onChangeName = (val) => {
    setUsername(val);
  }

  const onChangeSearch = (val) => {
    setSearch(val);
  }

  return (
    <span style={{width: '50%'}}>
      {!userstate.logged ? <InputCustom onChange={onChangeName} onKeyDown={onKeyDownUser} placeholder="Type your Username and press enter..." /> : null}
      <InputCustom onChange={onChangeSearch} onKeyDown={onKeyDownSearch} placeholder="Search for a keyword and press enter..." />
    </span>
  )
}

export default SearchBar