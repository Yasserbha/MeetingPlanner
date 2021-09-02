import React from "react"
import Chip from '@material-ui/core/Chip';
export default class AjoutSalle extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            name: "",
            nbrPlace: "",
            equipements: [
                {
                    name: "Néant",
                    status: false
                },
                {
                    name: "Ecran",
                    status: false  
                },
                {
                    name: "Pieuvre",
                    status: false  
                },
                {
                    name: "Tableau",
                    status: false  
                },
                {
                    name: "Webcam",
                    status: false  
                }
            ]
        }
        this.handleName = this.handleName.bind(this);
        this.handleNbrPlace = this.handleNbrPlace.bind(this);
        this.handleSubmit = this.handleSubmit.bind(this);
    }
    handleClick(index,status) {
        this.setState(prevState => ({
            equipements : prevState.equipements.map((e,i) => {
                if(i === index) {
                   return Object.assign(e,{status : status})
                } else {
                    return e
                }
            })
        }))
    }
    
    render() {
        const root = {
            display: 'flex',
            justifyContent: 'center',
            flexWrap: 'wrap',
        }
        return (
            <div className="container">
                <h1>Ajouter Salle</h1>
                <form onSubmit={this.handleSubmit}>
                    <div className="form-group">
                    <label>Name</label>
                    <input className="form-control" type="text" value={this.state.name} onChange={this.handleName}/>
                    </div>
                    <div className="form-group">
                    <label>Nombre de place</label>
                    <input className="form-control" type="number" value={this.state.nbrPlace} onChange={this.handleNbrPlace}/>
                    </div>
                    <label>Equipements</label>
                    <div className={root}>
                        {
                            this.state.equipements.map((e,i) => {
                                if (e.status) {
                                    return <Chip key={i} label={e.name} color="primary" onClick={() => this.handleClick(i,false)} />
                                } else {
                                    return <Chip key={i} label={e.name} onClick={() => this.handleClick(i,true)} />
                                }
                            })
                        }
                    </div>
                    <input className="btn btn-dark mt-3" type="submit" value="submit"/>
                </form>
            </div>

        )
    }
    handleName(event) {
        this.setState({name : event.target.value})
    }
    handleNbrPlace(event) {
        this.setState({nbrPlace : event.target.value})
    }
    handleSubmit(event) {
        let allEquipement = []
        this.state.equipements.map((e) => {
            if(e.status) {
                allEquipement.push(e.name);
            }
        })
        const requestOption = {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify({
                name : this.state.name,
                nbrPlace : this.state.nbrPlace,
                equipements : allEquipement
            }),
          };
          fetch("http://localhost:8080/spring-service/ajoutSalle",requestOption).then((e) => {
              if(e.status === 201) {
                  alert("ajouter avec Succés")
              } else {
                  console.log(e);
              }
          })
        event.preventDefault();
    }
}