import Container from './components/Container.js'
import Details from './components/Details.js'
import {
  BrowserRouter as Router,
  Switch,
  Route
} from "react-router-dom";

function App() {
  return (
    <Router>
      <Switch>
        <Route exact path="/">
          <Container />
        </Route>
        <Route path="/:id" children={<Details />} />
      </Switch>
    </Router>
  );
}

export default App;
