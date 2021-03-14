// src/count-context.js
import * as React from 'react'

export const UserContext = React.createContext()

const initialState = {
    user: {},
    logged: false
};

function userReducer(state, action) {
  switch (action.type) {
    case 'setUser': 
      return {
        ...state,
        user: action.payload.user
      }
      break;
    case 'setLogged': 
      return {
        ...state,
        logged: action.payload.logged
      }
      break;
    default:
        return state;
  }
}

function UserContextProvider({children}) {
  const [state, dispatch] = React.useReducer(userReducer, initialState)
  return <UserContext.Provider value={{state, dispatch}}>{children}</UserContext.Provider>
}

export default UserContextProvider;