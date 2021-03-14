// CAN USE AXIOS

const BASE_API = 'http://localhost:8080/v1';

const Api = {
  getUser: async (username) => {
    const req = await fetch(`${BASE_API}/users/${username}`, {
      method: 'GET',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json'
      }
    });
    const json = await req.json();
    return json;
  },
  getAllUsers: async () => {
    const req = await fetch(`${BASE_API}/users`, {
      method: 'GET',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json'
      }
    });
    const json = await req.json();
    return json;
  },
  getUser: async (user) => {
    const req = await fetch(`${BASE_API}/users/${user}`, {
      method: 'GET',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json'
      }
    });
    const json = await req.json();
    return json;
  },
  findUsers: async (user, heading) => {
    const req = await fetch(`${BASE_API}/users/${user}/find/${heading}`, {
      method: 'GET',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json'
      }
    });
    const json = await req.json();
    return json;
  },
  createUser: async (username, name, url) => {
    const req = await fetch(`${BASE_API}/users`, {
      method: 'POST',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json'
      },
      body: JSON.stringify({username, name, url})
    });
    const json = await req.json();
    return json;
  },
  addFriend: async (username, friendusername) => {
    const req = await fetch(`${BASE_API}/users/${username}/${friendusername}`, {
      method: 'POST',
      headers: {
        Accept: 'application/json',
        'Content-Type': 'application/json'
      }
    });
    const json = await req.json();
    return json;
  }
}

export default Api;