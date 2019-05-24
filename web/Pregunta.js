'use strict';

class Pregunta extends React.Component{
  constructor(){
    super();
    this.state={
      showSubida: false,
      NombrePregunta: '',
      TextoPregunta: '',
    };
    this.HandleOnChange = this.HandleOnChange.bind(this)
  }

  HandleOnChange(e){
    const {value, name} =e.target;
    this.setState({
        [name] : value
    });
    console.log(this.state);
    document.form.NombrePregunta.value= this.state.NombrePregunta;
    document.form.TextoPregunta.value= this.state.TextoPregunta;
    document.form.usr.value = document.getElementById("usuario").textContent;
  }
  
  render(){
    let SSubida ;
    if(this.state.showSubida)
    {
      SSubida = (
        <div className="card-body">
        Subida de archivos
        <br></br>
        <br></br>
          Selecciona el archivo a subir:
          <br></br>
          <form action="UploadServlet.java" method = "post" enctype = "multipart/form-data">
             <input type = "file" name = "file" size = "45" />
            </form>
        </div>
      );
    };


    return(
      <div>
          <div className="card-header bg-secondary text-white">
          <h4><strong>Nombre de la pregunta</strong></h4>
      <br></br>
      <input type="text" className="form-control bg-light border-0 rounded " name="NombrePregunta" onChange={this.HandleOnChange}/>
      </div>
      <div className="card-body bg-light"> <h4><strong>Cuerpo de la pregunta</strong></h4>

        <br></br>

        <textarea className="form-control" name="TextoPregunta" widht="50px" onChange={this.HandleOnChange}/>

        <br></br>
        <br></br>

        <input type="checkbox"  onClick={()=>this.setState({showSubida: !this.state.showSubida})} name="Incluir" /> Incluir Archivo Multimedia


        {SSubida}

        <br></br>
        <br></br>


      </div>
      </div>
    )
  }
}

let domContainer = document.querySelector('#Pregunta');
ReactDOM.render(<Pregunta />, domContainer);
