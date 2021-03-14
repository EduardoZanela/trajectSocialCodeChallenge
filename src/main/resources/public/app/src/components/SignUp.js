import { useState, useContext } from 'react';
import { UserContext } from '../contexts/UserContextProvider.js';
import {
  useHistory
} from "react-router-dom";
import Api from '../api/Api.js';
import Header from './Header.js';
import InputCustom from './InputCustom.js'

import './Container.css';

function SignUp() {

  const { state: userstate, dispatch: userDispatch } = useContext(UserContext);

  const [username, setUsername] = useState(null);
  const [name, setName] = useState(null);
  const [link, setLink] = useState(null);

  const history = useHistory();

  const handleNavigate = () => {
    history.push("/");
  }

  const onChangeName = (val) => {
    setName(val);
  }
  const onChangeUsernamename = (val) => {
    setUsername(val);
  }
  const onChangeLink = (val) => {
    setLink(val);
  }
  const onClickCreateUser = () => {
    if (name && username && link) {
      createUser();
    } else {
      alert("Fill all the fields!");
    }
  }
  const createUser = async () => {
    let res = await Api.createUser(username, name, link);
    if (res.error) {
      alert(res.error);
      return;
    }
    userDispatch({ type: 'setUser', payload: { user: res } });
    userDispatch({ type: 'setLogged', payload: { logged: true } });
    handleNavigate();
  }

  return (
    <div className="container">
      <Header />
      <InputCustom onChange={onChangeUsernamename} placeholder="Type your Username" />
      <InputCustom onChange={onChangeName} placeholder="Type your Name" />
      <InputCustom onChange={onChangeLink} placeholder="Type your Link" />
      <span className="item-link" onClick={onClickCreateUser}>Create</span>
    </div>
  )
}

export default SignUp