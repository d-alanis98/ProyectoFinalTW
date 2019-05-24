'use strict';

class ExamenEliminar extends React.Component{
    
    render(){        
        let number = parseInt(document.eliminarExamen.n.value, 10);
        let name = document.eliminarExamen.title.value;
        let opciones = new Array();
        for(let i = 0; i < number; i++){
            opciones[i] = eval("document.eliminarExamen.NombreExamen" + (i + 1) + ".value");
            
        }
        const table =opciones.map((val,i)=>{
            return(
                    
                      <tbody>
                            <td>
                            
                            {opciones[i]}
                            
                            </td>
                            <td>
                            <form name='formulario' method='get'>
                            <input type='hidden' name='exam' value={name}/>
                            <input type='hidden' name='item' value={opciones[i]}/>
                            <input type ='submit' value='Eliminar pregunta' formaction='ServletPreguntaExamen' className='btn btn-danger'/>
                            </form>
                            </td>
                      </tbody>
                    
                    )
        })
        
    return (
            
      <div className="Delimitador">
          
                                      
          <div className='card-header bg-secondary text-light'>
        <h1>{"Nombre del examen "+name}</h1>
        </div>
        <div className="card bg-transparent border-0">
        <div className='card-body bg-light ml-5 mr-5 rounded-bottom'>
            <table className= "table table-striped table-bordered  table-hover">
                    <thead >
                        <tr>
                          <th scope="col">Nombre</th>
                          <th scope="col">Accion</th>
                          
                        </tr>
                      </thead>
                     {table}
                     </table>
            </div>
        </div>
        
      </div>
    )
    };
}

let domContainer = document.querySelector('#ExamenEliminar');
ReactDOM.render(<ExamenEliminar />, domContainer);