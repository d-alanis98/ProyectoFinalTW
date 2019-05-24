'use strict';
class NavigationExam extends React.Component{
    render(){   
    let number;
    let banner;
    if(document.form != null){
        number = document.form.n.value;
        banner = " examenes";
    }
    else number = null;
    
    return (
            <div>
                <nav className="navbar navbar-dark bg-dark justify-content-start fixed-top">
                  <a href="ServletOpciones" className="text-white">
                      Inicio
                  </a> &nbsp;&nbsp;&nbsp;                
                  <a href="PaginaProfesor" className="text-white">
                      Mis preguntas
                  </a> &nbsp;&nbsp;&nbsp;
                  <a href="PaginaProfesorExamenes" className="text-white">
                      Mis examenes
                      <span className="badge badge-pill badge-light ml-2">
                            {number}{banner}
                      </span>
                  </a>
                </nav>    
            </div>
    )
    };
}

let domContainer = document.querySelector('#NavigationExam');
ReactDOM.render(<NavigationExam />, domContainer);
/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


