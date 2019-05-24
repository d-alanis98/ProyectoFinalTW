'use strict';
class Navigation extends React.Component{
    render(){   
    let number;
    let banner;
    if(document.form != null){
        number = document.form.n.value;
        banner = " preguntas";
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
                  <a href=" PaginaProfesorExamenes" className="text-white" align="left">
                      Mis examenes
                  </a> 
                  <span className="badge badge-pill badge-light ml-2">
                            {number}{banner}
                  </span>
                </nav>    
            </div>
    );
    };
};

let domContainer = document.querySelector('#Navigation');
ReactDOM.render(<Navigation />, domContainer);
