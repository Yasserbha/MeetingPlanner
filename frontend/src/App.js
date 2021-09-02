import './App.css';
import { BrowserRouter as Router, Switch, Route, Link} from "react-router-dom";
import {Navbar, Container,Nav} from "react-bootstrap"
import Salle from "./components/salle.js";
import Reservation from "./components/reservation.js";
import 'bootstrap/dist/css/bootstrap.min.css';
import ListReunion from './components/listReunion';
import AjoutSalle from './components/ajoutSalle';
function App() {
  return (
    <Router>
      <div>
        <Navbar bg="dark" variant="dark">
          <Container>
            <Nav className="me-auto">
              <Link className="nav-link" to="/list">List Reunion</Link>
              <Link className="nav-link" to="/salle">List Salle</Link>
              <Link className="nav-link" to="/addSalle">Ajouter Salle</Link>
            </Nav>
          </Container>
        </Navbar>

        {/* A <Switch> looks through its children <Route>s and
          renders the first one that matches the current URL. */}
        <Switch>
          <Route path="/list">
            <ListReunion />
          </Route>
          <Route path="/salle">
            <Salle />
          </Route>
          <Route path="/addSalle">
           <AjoutSalle />
          </Route>
          <Route exact path="/reservation/:id" component={Reservation}>
          </Route>
        </Switch>
      </div>
    </Router>
  );
}

export default App;
