// CAN USE AXIOS

const BASE_API = 'http://localhost:8080/v1';

const Api = {
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
  }
}

export default Api;