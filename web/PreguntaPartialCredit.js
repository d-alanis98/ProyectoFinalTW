'use strict';

class PreguntaPartialCredit extends React.Component{
    render(){
        
    return(
      <div className="Delimitador">

          <div className="card mb-3 bg-transparent">
            <div className="card-header">
                <h2 className="text-light"><strong>Pregunta partial credit</strong></h2>
            </div>
          </div>
          <br></br>
          <Pregunta />
          <Opcionespreguntas />
          <Retroalimentacion />
          <div className="card-footer bg-light">
         <form name="form" action="CrearXMLNuevo" method="get">
         <input type="hidden" name="NombrePregunta"/>
         <input type="hidden" name="TextoPregunta"/>
         <input type="hidden" name="Puntos1"/>
         <input type="hidden" name="Puntos2"/>
         <input type="hidden" name="Puntos3"/>
         <input type="hidden" name="Puntos4"/>
         <input type="hidden" name="Puntos5"/>
         <input type="hidden" name="Puntos6"/>
         <input type="hidden" name="Respuesta1"/>
         <input type="hidden" name="Respuesta2"/>
         <input type="hidden" name="Respuesta3"/>
         <input type="hidden" name="Respuesta4"/>
         <input type="hidden" name="Respuesta5"/>
         <input type="hidden" name="Respuesta6"/>
         <input type="hidden" name="CantMaxima"/>
         <input type="hidden" name="opciones"/>
         <input type="hidden" name="RetroalimentacionCorrecta"/>
         <input type="hidden" name="RetroalimentacionIncorrecta"/>
         <input type="hidden" name="Tipo" value="PartialCredit"/>
         <input type="hidden" name="usr"/>
         
         <br></br>
         <input type="submit" value="Crear Pregunta" className="btn btn-success mb-2 btn-block"/>
         <input type="submit" value="Cancelar" className="btn btn-danger mb-2 btn-block" formaction="PaginaProfesor"/>
         </form>
         </div>
 
      </div>
    )
  }
}

let domContainer = document.querySelector('#PreguntaPartialCredit');
ReactDOM.render(<PreguntaPartialCredit />, domContainer);