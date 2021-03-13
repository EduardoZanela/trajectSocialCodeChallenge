// CAN USE AXIOS

const BASE_API = 'http://localhost:8080/v1';

export default {
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
    }
}