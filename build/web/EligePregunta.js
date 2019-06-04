'use strict';

class EligePregunta extends React.Component{
	constructor(){
		super();
		this.state={
			showPC: false,
			showMO: false,
			showElige: true
		};
	}

	handleClick1(){
		this.setState({
			showElige: !this.state.showElige,
			showPC:!this.state.showPC
		});
	}

	handleClick2(){
		this.setState({
			showElige: !this.state.showElige,
			showMO:!this.state.showMO
		});
	}

	render(){

	let PC;
	if(this.state.showPC)
    {
      PC = (
        <PreguntaPartialCredit />
      );
    };

	let MO ;
    if(this.state.showMO)
    {
      MO = (
      <PreguntaMultipleOption />
      );
    };

    let Elige ;
    if(this.state.showElige)
    {
      Elige = (
      	<div className="panel panel-primary">
        	<strong>Elige el tipo de pregunta</strong>
			<div>
			<br></br>
				<button onClick={ () => this.handleClick1() } className="btn btn-success">Pregunta tipo PartialCredit</button>
				<br></br><br></br>
				<button onClick={ () => this.handleClick2() } className="btn btn-success">Pregunta tipo MultipleOption</button>
			</div>
		</div>
      );
    };

	return(

	<div className="panel panel-success">
	<br></br>
		{Elige}
		{PC}
                {MO}
	</div>

	)
    }
}

let domContainer = document.querySelector('#EligePregunta');
ReactDOM.render(<EligePregunta />, domContainer);
