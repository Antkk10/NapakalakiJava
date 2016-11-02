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
public class NumericBadConsequence extends BadConsequence {
    
    private int nVisibleTreasures;
    private int nHiddenTreasures;
    
    public NumericBadConsequence(String text, int level, int nVisibleTreasures, int nHiddenTreasures, boolean death){
        super(text,level, death);
        this.nHiddenTreasures=nHiddenTreasures;
        this.nVisibleTreasures=nVisibleTreasures;
    }
    
    @Override
    public String toString(){
        return (super.getText() + super.getLevels() + " número de tesoros visibles " + this.nVisibleTreasures
                + " número de tesoros ocultos " + this.nHiddenTreasures);
    }
    
    public int getVisibleTreasures(){
        return nVisibleTreasures;
    }
    
    public int getHiddenTreasures(){
        return nHiddenTreasures;
    }
    
    // Devuelve true si tiene visibles y ocultos == 0
    @Override
    public boolean isEmpty(){
        return (this.nHiddenTreasures == 0 && this.nVisibleTreasures==0);
    }
    
    // Método que quita un tesoro de la cantidad de tesoros visibles
    @Override
    public void substractVisibleTreasure(Treasure treasure){
        if(this.nVisibleTreasures != 0)
            nVisibleTreasures--;
        
    }
    // Método que quita un tesoro de la cantidad de tesoros ocultos
    @Override
    public void substractHiddenTreasure(Treasure treasure){
        if(this.nHiddenTreasures!=0)
            nHiddenTreasures--;
        
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
        
        int nOcultos=0, nVisibles=0;
        
        if(this.nHiddenTreasures!=0){
            if(this.nHiddenTreasures > hidden.size())
                nOcultos=hidden.size();
            else
                nOcultos=this.nHiddenTreasures;
        }

        if(this.nVisibleTreasures!=0){
            if(this.nVisibleTreasures > visible.size())
                nVisibles=visible.size();
            else
                nVisibles=this.nVisibleTreasures;
        }
            
        NumericBadConsequence badConsequence = new NumericBadConsequence(super.getText(),super.getLevels(),nVisibles,nOcultos, false);
            
        return badConsequence;
    }
}
