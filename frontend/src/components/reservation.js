import React from "react";

export default class Reservation extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      type: "VC",
      nbrPresonne: 0,
      debutReservation: "",
    };
    this.handleType = this.handleType.bind(this);
    this.handleNbPersonne = this.handleNbPersonne.bind(this);
    this.handleDateDebut = this.handleDateDebut.bind(this);
    this.handleSubmit = this.handleSubmit.bind(this);
  }
  // componentDidMount() {

  // }
  render() {
    return (
      <div>
        <h1>Reservation</h1>
        <form onSubmit={this.handleSubmit}>
          <label>Type :</label>
          <select value={this.state.type} onChange={this.handleType}>
            <option value="VC">VC</option>
            <option value="SPEC">SPEC</option>
            <option value="RS">RS</option>
            <option value="RC">RC</option>
          </select>
          <label> nombre de personne : </label>
          <input
            type="number"
            value={this.state.nbrPresonne}
            onChange={this.handleNbPersonne}
          />
          <label>Date debut de reservation :</label>
          <input
            type="datetime-local"
            value={this.state.debutReservation}
            onChange={this.handleDateDebut}
          />
          <input type="submit" value="submit" />
        </form>
      </div>
    );
  }
  handleType(event) {
    this.setState({ type: event.target.value });
  }
  handleNbPersonne(event) {
    this.setState({ nbrPresonne: event.target.value });
  }
  handleDateDebut(event) {
    this.setState({ debutReservation: event.target.value });
  }
  handleSubmit(event) {
    const date = new Date(this.state.debutReservation);
    const requestOption = {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({
        typeReunion: this.state.type,
        nbrPresonne: this.state.nbrPresonne,
        debutReservation: date.toISOString(),
        salle: {
          id: this.props.match.params.id,
        },
      }),
    };
    fetch("http://localhost:8080/spring-service/ajoutReunion", requestOption)
      .then((res) => {
        if(res.status === 400 || res.status === 500) {
          res.text().then((r) => alert(r))
          return;
        }
        alert("YESS");
      })
    event.preventDefault();
  }
}
