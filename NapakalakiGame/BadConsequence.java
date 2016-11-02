/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

import java.util.ArrayList;
import java.util.Collections;

/**
 *
 * @author antonio
 */
public abstract class BadConsequence {
    private String text;
    private int levels;
    private boolean death;
    static final int MAXTREASURES=10;
    
    
    public BadConsequence(String text, int levels, boolean death){
        this.text=text;
        this.levels=levels;
        this.death=death;
    }
    
    

    
    /* Devuelve true cuando el jugador no tiene que cumplir ningún tipo de mal rollo
    */
    public abstract boolean isEmpty();
    
    
    
    public abstract String toString();
    
    public String getText(){
        return text;
    }
    
    public int getLevels(){
        return levels;
    }
    
    
    public boolean getDeath(){
        return this.death;
    }
    
    
    protected abstract void substractVisibleTreasure(Treasure treasure);
    
    
    protected abstract void substractHiddenTreasure(Treasure treasure);
    
    public abstract BadConsequence adjustToFitTreasureLists(ArrayList<Treasure> visible, ArrayList<Treasure> hidden);
        
        
    
    

    
    
}