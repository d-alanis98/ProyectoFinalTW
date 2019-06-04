'use strict';

class OpcionesMultiple extends React.Component
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
    this.index=0;
    this.HandleOnChange = this.HandleOnChange.bind(this);
    this.HandleOnChangeSelect = this.HandleOnChangeSelect.bind(this);
  }
  
  HandleOnChange(e){
    const {value, name} = e.target;
    console.log([name]);
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
  
  HandleOnChangeSelect(event){
      //this.index=0;
      let stringPuntos = "Puntos" + this.index;
      this.setState({
          [stringPuntos]: event.target.value
      })
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
      //this.index=i;
      return(
        <tr>
          <td>{i+4}</td>
          <select form={puntos} onChange={(event) => {this.index=i+4; this.HandleOnChangeSelect(event)}}>
                  <option value="" selected></option>
                  <option value="verdadero">Verdadero</option>
                  <option value="falso">Falso</option>
          </select>
          <form className="form-control" name={puntos} onChange={this.HandleOnChange}/>
          <td><input onChange={this.HandleOnChange} className="form-control" type="text" name={opciones} size="40"/></td>
        </tr> 
      )
    })

    let celdas = (
        <tbody align='center'>
        <tr>
          <td>1</td>
          <select form="Puntos1" onChange={(event) => {this.index=1; this.HandleOnChangeSelect(event)}}>
                  <option value="" selected></option>
                  <option value="verdadero">Verdadero</option>
                  <option value="falso">Falso</option>
          </select>
          <form className="form-control" name="Puntos1" type='hidden'/>
          <td><input className="form-control" type="text" name="Respuesta1" size="40" onChange={this.HandleOnChange}/></td>
          
        </tr>
        <tr>
          <td>2</td>
          <select form="Puntos2" onChange={(event) => {this.index=2; this.HandleOnChangeSelect(event)}} >
                  <option value=""></option>
                  <option value="verdadero">Verdadero</option>
                  <option value="falso">Falso</option>
          </select>
          <form className="form-control" name="Puntos2"/>
          <td><input onChange={this.HandleOnChange} className="form-control" type="text" name="Respuesta2" size="40"/></td>
        </tr>
        <tr>
          <td>3</td>
          <select form="Puntos3" onChange={(event) => {this.index=3; this.HandleOnChangeSelect(event)}}>
                  <option value=""></option>
                  <option value="verdadero">Verdadero</option>
                  <option value="falso">Falso</option>
          </select>
          <form className="form-control" name="Puntos3"/>
          <td><input onChange={this.HandleOnChange} className="form-control" type="text" name="Respuesta3" size="40"/></td>
        </tr>
        </tbody>
        

      );
      
    return(
            <div className="card-body bg-light"> <h4><strong>Opciones de respuestas</strong></h4>

      <table className="table">
      <tr><td></td><td><h5>Falso/Verdadero</h5></td><td><h5>Respuesta</h5></td></tr>
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
      <br></br>
      </div>

    </div>

    )
  }
}

let domContainer = document.querySelector('#OpcionesMultiple');
ReactDOM.render(<OpcionesMultiple />, domContainer);