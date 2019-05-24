'use strict';

class Retroalimentacion extends React.Component
{
  constructor(){
    super();
    this.state={
      showRetro: false,
      RetroalimentacionCorrecta: '',
      RetroalimentacionIncorrecta: ''
    };
   this.HandleOnChange = this.HandleOnChange.bind(this)
  }
  
  HandleOnChange(e){
      const {value, name} = e.target;
        this.setState({
            [name]: value
      });
      document.form.RetroalimentacionCorrecta.value=this.state.RetroalimentacionCorrecta;
      document.form.RetroalimentacionIncorrecta.value=this.state.RetroalimentacionIncorrecta;
      
  }

  render(){
    let SRetro;
    if(this.state.showRetro)
    {
      SRetro = (
        <div>
          <br></br>
          <strong>Retroalimentacion del nivel de la pregunta</strong>
          <br></br>
          <br></br>

          <div className="text-left">
          <div>
            Retroalimentacion a la respuesta Correcta:
            <br></br>
            <input className="form-control" type="text" name="RetroalimentacionCorrecta" onChange={this.HandleOnChange}/>
          </div>

          <br></br>
          <br></br>

          <div className="text-left">
            Retroalimentacion a la respuesta Incorrecta:
            <br></br>
            <input className="form-control" type="text" name="RetroalimentacionIncorrecta" onChange={this.HandleOnChange}/>
            </div>
          </div>
        </div>

      );
    };
    return(
      <div className="card-footer bg-light">
      <input type="checkbox" onClick={()=>this.setState({showRetro: !this.state.showRetro})} name="Retro"/> Mostrar Retroalimentacion a la respuesta
      
            {SRetro}

      </div>
    )
  }
}

let domContainer = document.querySelector('#Retroalimentacion');
ReactDOM.render(<Retroalimentacion />, domContainer);
