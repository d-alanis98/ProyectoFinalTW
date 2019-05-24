/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
'use strict';
class ComponentExamn extends React.Component{
    render(){        
    return (
      <div className="Delimitador">
            <div className="card border-0">
                    <ExamenVisualizer />
            </div>
      </div>
    )
    };
}

let domContainer = document.querySelector('#ComponentExamn');
ReactDOM.render(<ComponentExamn />, domContainer);

