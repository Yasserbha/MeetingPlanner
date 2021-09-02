import React from "react";
import { Button, Table } from "react-bootstrap";
import { Link } from "react-router-dom";
export default class Salle extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
        salles: []
    };
  }

  render() {
      const { salles } = this.state;
    return (
      <Table striped bordered hover>
      <thead>
        <tr>
          <th>NAME</th>
          <th>Nombre de place</th>
          <th>Equipement</th>
          <th>Button</th>
        </tr>
      </thead>
      <tbody>
        {
          salles.map((s) => 
          <tr key={s.id}>
            <td>{s.name}</td>
            <td>{s.nbrPlace}</td>
            <td className="row">
              {
                s.equipements.map((e) => <p key={e} className="col-md-2">{e}</p>)
              }
            </td>
            <td>
              <Button variant="dark">
                <Link to={`/reservation/${s.id}`}>Reserver</Link>
              </Button>
            </td>
          </tr>
          )
        }
      </tbody>
    </Table>
    );
  }
  componentDidMount() {
    this.getAllSalle();
  }
  getAllSalle() {
    fetch("http://localhost:8080/spring-service/getSalle")
      .then((value) => value.json())
      .then((v) => {
        this.setState({salles : v});
      });
  }
}
