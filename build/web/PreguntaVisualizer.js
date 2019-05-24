'use strict';
class PreguntaVisualizer extends React.Component{
    constructor(){
        super();
        this.revisar = this.revisar.bind(this);
        this.n = 0;
        this.sum = 0;
        this.selectedCheckboxes = new Set();
        this.retroCorrecta = document.visualizar.RetroCorrecta.value;
        this.retroIncorrecta = document.visualizar.RetroIncorrecta.value;
        this.maxElements = parseInt(document.visualizar.MaxElements.value);
        this.respuestaTag = "";
        this.state = {
            showRespuestas: false
        };
        
    }
   
    revisar(){
            this.n = 0;
            for(const checkbox of this.selectedCheckboxes){
                let index =checkbox.indexOf('&');
                let number = parseInt(checkbox.substring(0, index));
                this.n = this.n + number;
            }
            if(this.n == 0) this.respuestaTag = "Incorrecto, " +  this.retroIncorrecta;
            else{
                if(this.sum == this.n) this.respuestaTag = "Correcto!, " + this.retroCorrecta;
                else this.respuestaTag = "Parcialmente correcta, " + this.retroIncorrecta;
            }
            this.setState({showRespuestas: true});
            
            
            
    }
    render(){  
        let name = document.visualizar.NombrePregunta.value;
        let text = document.visualizar.TextoPregunta.value;
        let number = parseInt(document.visualizar.n.value, 10);
        let opciones = new Array();
        let puntos = new Array();  
        this.sum =0;
        for(let i = 0; i < number; i++){
            opciones[i] = eval("document.visualizar.Respuesta" + (i + 1) + ".value");
            puntos[i] = eval("document.visualizar.Puntos" + (i + 1) + ".value");
            this.sum = this.sum + parseInt(puntos[i], 10);
        }
        let tagRES = (
                <h6 className='text-secondary'><br/>valor({this.sum} puntos) </h6>   
        );
        if(this.state.showRespuestas){
            tagRES = (
                    <div>
                    <h6 className="text-center font-weight-bold"> {this.respuestaTag}</h6>
                    <h6 className="text-center text-info"> Obtuvo {this.n} puntos de {this.sum} posibles </h6><br/> 
                    <h6 className='text-success'>Valor({this.sum} puntos)</h6>    
                    </div>
            );
        }
        const options = puntos.map(
            (opcio, i) => {
            return(
                <label className="container"><input type="checkbox" id={"opcion" + (i +  1)} value={puntos[i]} 
                onClick = {() => {//Se agregan las opciones a un set y se 
                    let opcion = opcio.trim() + "&" + i; //Se genera un id con la opcion y su numero de opcion
                    if(this.selectedCheckboxes.has(opcion)) this.selectedCheckboxes.delete(opcion);
                    else this.selectedCheckboxes.add(opcion);
                    }}/><span className="checkmark"/> {opciones[i]}</label>
            );
        });
        
    return (
      <div className="App">

                    <div className='card-header bg-secondary text-light'>
                    <h1>{name}</h1>
                    </div>
                    <div className="card-body bg-light mb-3">
                    <h3 className="card-title"> {text} </h3> <br/> 
                    <h5 className='text-left'>Seleccione las opciones correctas: </h5>
                    <br/>
                        <div className='checkbox lg'>
                        {options}
                        </div>
                        <div className="card-footer text-dark text-right border-0">
                        <button className="btn btn-outline-primary btn-lg" onClick= {this.revisar}> Revisar </button> <br/>        
                        {tagRES}
                     </div>
                    </div>
      </div>
    )
    };
}

let domContainer = document.querySelector('#PreguntaVisualizer');
ReactDOM.render(<PreguntaVisualizer />, domContainer);