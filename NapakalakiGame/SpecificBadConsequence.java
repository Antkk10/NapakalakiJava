/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

import java.util.ArrayList;

/**
 *
 * @author antonio
 */
public class SpecificBadConsequence extends BadConsequence {
    
    private ArrayList<TreasureKind> specificHiddenTreasures = new ArrayList();
    private ArrayList<TreasureKind> specificVisibleTreasures = new ArrayList();
    
    public SpecificBadConsequence(String text, int level,  ArrayList<TreasureKind> tVisible,
            ArrayList<TreasureKind> tHidden){
        
        super(text,level, false);
        this.specificHiddenTreasures=tHidden;
        this.specificVisibleTreasures=tVisible;
    }
    
    // Método que devuelve el mal rollo
    @Override
    public String toString(){
        return (super.getText() + super.getLevels() + this.specificHiddenTreasures + this.specificVisibleTreasures);
    }
    
    // Método que devuelve true cuando el jugador no tiene que devolver ninguna mala consecuencia
    @Override
    public boolean isEmpty(){
        return (this.specificHiddenTreasures.isEmpty() && this.specificVisibleTreasures.isEmpty());
    }
    
    public ArrayList<TreasureKind> getSpecificHiddenTreasure(){
        return this.specificHiddenTreasures;
    }
    
    public ArrayList<TreasureKind> getSpecificvisibleTreasure(){
        return this.specificVisibleTreasures;
    }
    
    // Método que quita un objeto específico de la lista de tesoros visibles
    // si es que la lista contiene dicho tesoro
    @Override
    public void substractVisibleTreasure(Treasure treasure){
        if(this.specificVisibleTreasures.contains(treasure.getType()))
            this.specificVisibleTreasures.remove(this.specificVisibleTreasures.indexOf(treasure.getType()));

        
    }
    
    // Método que quita un objeto específico de la lista de tesoros ocultos
    // si es que la lista contiene dicho tesoro
    @Override
    public void substractHiddenTreasure(Treasure treasure){
        if(this.specificHiddenTreasures.contains(treasure.getType()))
         
         this.specificHiddenTreasures.remove(this.specificHiddenTreasures.indexOf(treasure.getType()));

        
    }

    
    private int howManyTreasures(TreasureKind tkind, ArrayList<Treasure> tesoros){
        int contador_tesoros=0;
        
        for(int i=0;i<tesoros.size();i++){
            if(tesoros.get(i).getType().equals(tkind))
                contador_tesoros=contador_tesoros+1;
        }
        
  
        return contador_tesoros;
    }
    
    private int howManyTypeTreasures(TreasureKind tkind, ArrayList<TreasureKind> tesoros){
        int contador_tesoros=0;
        
        for(int i=0;i<tesoros.size();i++){
            if(tesoros.get(i).equals(tkind))
                contador_tesoros=contador_tesoros+1;
        }
        
  
        return contador_tesoros;
    }

    // Método que ajusta la mala consecuencia.
    /* Con respecto a la versión anterior, he quitado todas las comprobaciones
    * ya que si se llama a este método es seguro que contenga alguna mala consecuencia
    * de tipo númerica que cumplir.
    */
    // Devuelve la mala consecuencia ajustada, en función de la cantidad de elementos guardados
    // en los parámetros.
    // Los parámetros visible y hidden son la lista de tesoros visibles y ocultos del jugador.
    @Override
    public BadConsequence adjustToFitTreasureLists(ArrayList<Treasure> visible, ArrayList<Treasure> hidden){

            ArrayList<TreasureKind> specificV = new ArrayList();
            ArrayList<TreasureKind> specificH = new ArrayList();  
            
            // Array auxiliar para utilizarlo en los métodos howMany...
            ArrayList<TreasureKind> comparador = new ArrayList();
            comparador.add(TreasureKind.ARMOR);
            comparador.add(TreasureKind.BOTHHANDS);
            comparador.add(TreasureKind.ONEHAND);
            comparador.add(TreasureKind.HELMET);
            comparador.add(TreasureKind.SHOES);
            
            // Se comprueba si hay algún tesoro visible que quitar.
            if(!this.specificVisibleTreasures.isEmpty()){
                // Bucle para ver que tipo de tesoros hay en la lista visible del jugador como en la mala consecuencia
                for(int i=0;i<comparador.size();i++){
                    // Variable que contiene la cantidad de veces que tiene un tipo de tesoro el jugador como tesoro visible.
                    int contador_tesoro = this.howManyTreasures(comparador.get(i), visible);
                    // Comprueba que el tipo de tesoro está en la mala consecuencia y lo tiene el jugador como objeto visible
                    if(this.specificVisibleTreasures.contains(comparador.get(i)) && contador_tesoro > 0){
                        // Si en la mala consecuencia aparece más o los mismos tipos de tesoros que tiene el jugador
                        // puesto entonces el tope es la cantidad de tesoros que tiene el jugador como visibles
                        if(this.howManyTypeTreasures(comparador.get(i),this.specificVisibleTreasures) >= contador_tesoro){
                            // Guardamos en el array nuevo la cantidad de tesoros visibles del ajuste de la mala consecuencia
                            for(int j=0;j<contador_tesoro;j++)
                                specificV.add(comparador.get(i));
                        }
                        else{
                            // Guardamos en el array nuevo la cantidad de tesoros visibles del ajuste de la mala consecuencia
                            for(int j=0;j<this.howManyTypeTreasures(comparador.get(i),this.specificVisibleTreasures);j++)
                                specificV.add(comparador.get(i));
                        }
                    }
                }
            }
            // Ahora con los tesoros ocultos
            if(!this.specificHiddenTreasures.isEmpty()){
                // Bucle para ver que tipo de tesoros hay en la lista visible del jugador como en la mala consecuencia
                for(int i=0;i<comparador.size();i++){
                    // Variable que contiene la cantidad de veces que tiene un tipo de tesoro el jugador como tesoro oculto.
                    int contador_tesoro = this.howManyTreasures(comparador.get(i), hidden);
                    // Comprueba que el tipo de tesoro está en la mala consecuencia y lo tiene el jugador como objeto oculto.
                    if(this.specificHiddenTreasures.contains(comparador.get(i)) && contador_tesoro > 0){
                        // Si en la mala consecuencia aparece más o los mismos tipos de tesoros ocultos que tiene el jugador
                        // puesto entonces el tope es la cantidad de tesoros que tiene el jugador como ocultos.
                        if(this.howManyTypeTreasures(comparador.get(i),this.specificHiddenTreasures) >= contador_tesoro){
                            // Guardamos en el array nuevo la cantidad de tesoros ocultos del ajuste de la mala consecuencia
                            for(int j=0;j<contador_tesoro;j++)
                                specificH.add(comparador.get(i));
                        }
                        else{
                            // Guardamos en el array nuevo la cantidad de tesoros ocultos del ajuste de la mala consecuencia
                            for(int j=0;j<this.howManyTypeTreasures(comparador.get(i),this.specificHiddenTreasures);j++)
                                specificH.add(comparador.get(i));
                        }
                    }
                }
            }
            
            
            SpecificBadConsequence badConsequence = new SpecificBadConsequence(super.getText(),super.getLevels(),specificV,specificH);
            
            return badConsequence;
        
    }
}
