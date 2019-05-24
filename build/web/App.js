'use strict';

class App extends React.Component{  
    render() {
        let EligePregunta=(
                
                <EligePregunta />
                );
        
    return (
      <div className="App">
          <nav>Perra</nav>
              <header className="App-header">

                <div className="Delimitador">
                
                   {EligePregunta}
                   
                </div>

              </header>
      </div>
    )
  }
}

let domContainer = document.querySelector('#APP');
ReactDOM.render(<App />, domContainer);
