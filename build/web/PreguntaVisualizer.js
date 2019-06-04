'use strict';
class PreguntaVisualizer extends React.Component{
    constructor(){
        super();
        this.revisar = this.revisar.bind(this);
        this.n = 0;
        this.sum = 0;
        this.grade = 0;
        this.selectedCheckboxes = new Set();
        this.respuestaMultiple = false;
        this.retroCorrecta = document.visualizar.RetroCorrecta.value;
        this.retroIncorrecta = document.visualizar.RetroIncorrecta.value;
        this.maxElements = parseInt(document.visualizar.MaxElements.value);
        this.respuestaTag = "";
        this.maxRespuestas = (<div></div>);
        this.tagRes = (<div></div>);
        this.retroComponent = (<div></div>);
        this.state = {
            showRespuestas: false
        };
        
    }
   
    revisar(){
        //Validacion de que no se supere maximo de opciones para Partial credit
        if(!isNaN(this.maxElements) && document.visualizar.Tipo.value == "PartialCredit"){
            if(this.selectedCheckboxes.size > this.maxElements){
                alert("Seleccione solamente " + this.maxElements + " opciones");
                location.reload();
                return;
            }
        }
        //Validacion de que se haya seleccionado al menos un checkbox
        if(this.selectedCheckboxes.size == 0 && document.visualizar.Tipo.value == "PartialCredit"){
            alert("Seleccione al menos una opcion");
            location.reload();
            return;
        }
        //
        if(document.visualizar.Tipo.value == "PartialCredit"){
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
            this.grade = (this.n * 10)/this.sum;
            this.setState({showRespuestas: true});
        }
        else {
           if(document.options.radio.value == ""){
               alert("Seleccione una opcion");
               location.reload();
               return;
           }
            if(this.respuestaMultiple == true){
                this.respuestaTag = "Correcto!, " + this.retroCorrecta;
                this.respuestaMultiple = false;
                this.grade = 10;
                this.tagRES = (
                    <div><h6 className="text-center font-weight-bold"> {this.respuestaTag}</h6></div>
                );
            }
            else{
                this.grade = 0;
                this.respuestaTag = "Incorrecto, " +  this.retroIncorrecta;
            }
            
            this.setState({showRespuestas: true});
        }
            
            
    }
    render(){  
        let name = document.visualizar.NombrePregunta.value;
        let text = document.visualizar.TextoPregunta.value;
        let number = parseInt(document.visualizar.n.value, 10);
        let opciones = new Array();
        let puntos = new Array();  
        this.sum =0;
        if(!isNaN(this.maxElements)){
            this.maxRespuestas = (
                   <div><h6 className="text-center font-weight-bold text-info"> Seleccione maximo {this.maxElements} opciones</h6>
                    </div> 
            );
        }
        //let tagRES = 0;
         for(let i = 0; i < number; i++){
            opciones[i] = eval("document.visualizar.Respuesta" + (i + 1) + ".value");
            puntos[i] = eval("document.visualizar.Puntos" + (i + 1) + ".value");
            if(document.visualizar.Tipo.value == "PartialCredit")
             this.sum = this.sum + parseInt(puntos[i], 10);
            
        }
        
        if(document.visualizar.Tipo.value == "PartialCredit") this.tagRES = (
                <h6 className='text-secondary'><br/>valor({this.sum} puntos) </h6>   
        );
else this.tagRES = (<div>Da clic para evaluar pregunta</div>);
        if(this.state.showRespuestas){
            if(document.visualizar.Tipo.value == "PartialCredit") this.tagRES = (
                    <div>
                    <h6 className="text-center font-weight-bold"> {this.respuestaTag}</h6>
                    <h6 className="text-center text-info"> Obtuvo {this.n} puntos de {this.sum} posibles </h6><br/> 
                    <h6 className='text-success'>Valor({this.sum} puntos)</h6> 
                    </div>
            );
            else this.tagRES = (
                    
                    <div><h6 className="text-center font-weight-bold"> {this.respuestaTag}</h6>
                    </div>
                    
            )
    
            if(this.grade > 0) {this.retroComponent = (<div><h6 className="text-center text-success"> Su calificacion de esta pregunta es {this.grade} </h6></div>);}
                            else this.retroComponent = (<div><h6 className="text-center text-danger"> Su calificacion de esta pregunta es {this.grade} </h6></div>); 
        }
        
        const options = puntos.map(
            (opcio, i) => {
            if(document.visualizar.Tipo.value === "PartialCredit") return(
                <label className="container"><input type="checkbox" id={"opcion" + (i +  1)} value={puntos[i]} 
                onClick = {() => {//Se agregan las opciones a un set y se 
                    let opcion = opcio.trim() + "&" + i; //Se genera un id con la opcion y su numero de opcion
                    if(this.selectedCheckboxes.has(opcion)) this.selectedCheckboxes.delete(opcion);
                    else this.selectedCheckboxes.add(opcion);
                    }}/><span className="checkmark"/> {opciones[i]}</label>
            );
            else return(
                    <div align="left"><h5 className="text-secondary"><label><input type="radio" name="radio" value={puntos[i]}
                        onClick = { () => {     
                    if(document.options.radio.value == "verdadero" || document.options.radio.value == "verdadero ")this.respuestaMultiple = true;
                    }}/>{"  " + opciones[i]}</label></h5></div>
            );
        });
        
    return (
      <div className="App">
                    <br/><br/>
                    <div className='card-header bg-secondary text-light'>
                    <h1>{name}</h1>
                    </div>
                    <div className="card-body bg-light mb-3">
                    <h3 className="card-title"> {text} </h3> <br/> 
                    
                    <h5 className='text-left text-primary '>Seleccione la o las opciones correctas: </h5>
                    {this.maxRespuestas}
                    <br/>
                        <div className='checkbox lg'>
                        <form name='options'>
                        {options}
                        </form>
                        </div>
                        <div className="card-footer text-dark text-right border-0">
                        <button className="btn btn-outline-primary btn-lg" onClick= {this.revisar}> Revisar </button> <br/>        
                        {this.tagRES}
                        {this.retroComponent}
                     </div>
                    </div>
      </div>
    )
    };
}

let domContainer = document.querySelector('#PreguntaVisualizer');
ReactDOM.render(<PreguntaVisualizer />, domContainer);