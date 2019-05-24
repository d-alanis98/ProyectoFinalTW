/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
'use strict';
class Component extends React.Component{
    render(){        
    return (
      <div className="Delimitador">
            <div className="card border-0">
                    <PreguntaVisualizer />
            </div>
      </div>
    )
    };
}

let domContainer = document.querySelector('#Component');
ReactDOM.render(<Component />, domContainer);

