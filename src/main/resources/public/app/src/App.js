import Container from './components/Container.js';
import Details from './components/Details.js';
import SearchResults from './components/SearchResults';
import SignUp from './components/SignUp.js';
import UserContextProvider from './contexts/UserContextProvider.js';

import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";

function App() {
  return (
    <UserContextProvider>
      <Router>
        <Switch>
          <Route exact path="/">
            <Container />
          </Route>
          <Route path="/details/:id" exact children={<Details />} />
          <Route path="/search/:username/:search" children={<SearchResults />} />
          <Route path="/signup" exact children={<SignUp />} />
        </Switch>
      </Router>
    </UserContextProvider>
  );
}

export default App;
