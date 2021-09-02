import React from "react";
import { Button, Table } from "react-bootstrap";

export default class ListReunion extends React.Component {
    constructor(props) {
        super(props)
        this.state = {
            list : []
        }
    }
    componentDidMount() {
        this.getAllReunion().then((value) => {
            this.setState({list : value})
        }).catch((err) => {
            console.error(err);
        })
    }
    render() {
        const { list } = this.state;
        return (
            <Table striped bordered hover>
                <thead>
                    <tr>
                    <th>typeReunion</th>
                    <th>debutReservation</th>
                    <th>finReservation</th>
                    <th>nbrPresonne</th>
                    </tr>
                </thead>
                <tbody>
                    {
                        list.map((l) => 
                        <tr key={l.id}>
                            <td>{l.typeReunion}</td>
                            <td>{l.debutReservation}</td>
                            <td>{l.finReservation}</td>
                            <td>{l.nbrPresonne}</td>
                        </tr>
                        )
                    }
                </tbody>
            </Table>
        )
    }
    getAllReunion() {
        return new Promise((resolve,reject) => {
            fetch("http://localhost:8080/spring-service/getReunions").then((res) => res.json()).then((r) => {
                if(r.length > 0) {
                    resolve(r)
                } else {
                    reject("LISTE VIDE");
                }
            })
        })
    }
}