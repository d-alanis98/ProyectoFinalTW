'use strict';

class ExamenPartialCredit extends React.Component{
    render(){
        return(
                <div className="Delimitador">
                    <div className="card mb-3 bg-transparent">
                        <div className="card-header">
                            <h2 className="text-light"><strong>Examen Partial Credit</strong></h2>
                        </div>
                    </div>
                </div>
        )
    }
}
let domContainer = document.querySelector('#ExamenPartialCredit');
ReactDOM.render(<ExamenPartialCredit />, domContainer);