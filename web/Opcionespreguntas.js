'use strict';

class Opcionespreguntas extends React.Component
{
  constructor(){
    super();
    this.state={
      TablaInicial: [],
      Numero: 4,
      CantMaxima: '',
      Puntos1: '',
      Puntos2: '',
      Puntos3: '',
      Puntos4: '',
      Puntos5: '',
      Puntos6: '',
      Respuesta1: '',
      Respuesta2: '',
      Respuesta3: '',
      Respuesta4: '',
      Respuesta5: '',
      Respuesta6: '',
    };
    this.HandleOnChange = this.HandleOnChange.bind(this);
  }
  
  HandleOnChange(e){
    const {value, name} = e.target;
    this.setState({
        [name] : value
    });
    console.log(this.state);
    document.form.Puntos1.value= this.state.Puntos1;
    document.form.Puntos2.value= this.state.Puntos2;
    document.form.Puntos3.value= this.state.Puntos3;
    document.form.Puntos4.value= this.state.Puntos4;
    document.form.Puntos5.value= this.state.Puntos5;
    document.form.Puntos6.value= this.state.Puntos6;
    document.form.Respuesta1.value= this.state.Respuesta1;
    document.form.Respuesta2.value= this.state.Respuesta2;
    document.form.Respuesta3.value= this.state.Respuesta3;
    document.form.Respuesta4.value= this.state.Respuesta4;
    document.form.Respuesta5.value= this.state.Respuesta5;
    document.form.Respuesta6.value= this.state.Respuesta6;
    document.form.CantMaxima.value= this.state.CantMaxima;
    document.form.opciones.value= this.state.opcionesNum;
  }

  handleAddRow(e){
      e.preventDefault();
      this.setState(
        {
          TablaInicial: [...this.state.TablaInicial,e.target.Numero.value]

        }
      );
  }

  render(){
    const rows = this.state.TablaInicial.map((TablaInicial,i) => {
      var y=i+4;
      var opciones="Respuesta"+y;
      var puntos="Puntos"+y;
      return(
        <tr>
          <td>{i+4}</td>
          <td><input className="form-control" onChange={this.HandleOnChange} type="text" name={puntos} size="7"/></td>
          <td><input onChange={this.HandleOnChange} className="form-control" type="text" name={opciones} size="40"/></td>
          
        </tr>
      )
    })

    let celdas = (
        <tbody>
        <tr>
          <td>1</td>
          <td><input className="form-control" onChange={this.HandleOnChange} type="text" name="Puntos1" size="7"/></td>
          <td><input className="form-control" type="text" name="Respuesta1" size="40" onChange={this.HandleOnChange}/></td>
        </tr>
        <tr>
          <td>2</td>
          <td><input className="form-control" onChange={this.HandleOnChange} type="text" name="Puntos2" size="7"/></td>
          <td><input onChange={this.HandleOnChange} className="form-control" type="text" name="Respuesta2" size="40"/></td>
        </tr>
        <tr>
          <td>3</td>
          <td><input className="form-control" onChange={this.HandleOnChange} type="text" name="Puntos3" size="7"/></td>
          <td><input onChange={this.HandleOnChange} className="form-control" type="text" name="Respuesta3" size="40"/></td>
        </tr>
        </tbody>
        

      );
      
    return(
            <div className="card-body bg-light"> <h4><strong>Opciones de respuestas</strong></h4>

      <table className="table">
      <tr><td></td><td><h5>Puntos</h5></td><td><h5>Respuesta</h5></td></tr>
          {celdas}
          {rows}
      </table>

      <form onSubmit={this.handleAddRow.bind(this)}>

      <div className="col align-self-end">
      <button type="submit" className="btn btn-info btn-block" name="Numero" value={(this.state.TablaInicial.length)+1}>Agregar opcion</button>
      </div>
      </form>

      <div className="text-center">
      <br></br>
      Cantidad maxima de opciones que podran ser seleccionadas:
      <br></br><br/>
      <input type="text" className='form-control-sm' name="CantMaxima" size="7" onChange={this.HandleOnChange}/>
      <br></br>
      <br></br>
      </div>

    </div>

    )
  }
}

let domContainer = document.querySelector('#OpcionesPreguntas');
ReactDOM.render(<Opcionespreguntas />, domContainer);