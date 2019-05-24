'use strict';

class ExamenVisualizer extends React.Component{
    
    render(){        
        let number = parseInt(document.visualizarExamen.n.value, 10);
        let name = document.visualizarExamen.title.value;
        let opciones = new Array();
        for(let i = 0; i < number; i++){
            opciones[i] = eval("document.visualizarExamen.NombreExamen" + (i + 1) + ".value");
            
        }
        const table =opciones.map((val,i)=>{
            return(
                    
                      <tbody>
                            <td>
                            
                            {opciones[i]}
                            
                            </td>
                            <td>
                            <form name='formulario' method='get'>
                            <input type='hidden' name='item' value={opciones[i]}/>
                            <input type ='submit' value='Visualizar'  formaction='ServletVisualizar' className='btn btn-success'/>
                            </form>
                            </td>
                      </tbody>
                    
                    )
        })
        
    return (
            
      <div className="App border-0">
          
                                      
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

let domContainer = document.querySelector('#ExamenVisualizer');
ReactDOM.render(<ExamenVisualizer />, domContainer);