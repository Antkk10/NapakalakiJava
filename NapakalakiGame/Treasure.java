/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;


/**
 *
 * @author antonio
 */
public class Treasure {
    private String name;
    private int bonus;
    private TreasureKind type;
    
    public Treasure(String n, int bonus, TreasureKind t){
        this.name=n;
        this.bonus=bonus;
        this.type=t;
    }
    
    public Treasure(Treasure t){
        this.name=t.name;
        this.bonus=t.bonus;
        this.type=t.type;
    }
    
    public String toString(){
        return "name: " + this.name + ", bonus " + this.bonus + "Tesoro " + this.type; 
    }
    
    public String getName(){
        return this.name;
    }
    
    public String getTextType(){
        String aux = this.type + " ";
        
        return aux;
    }
    
    public String getTextBonus(){
        String aux = this.bonus + " ";
        
        return aux;
    }
    
    public int getBonus(){
        return this.bonus;
    }
    
    public TreasureKind getType(){
        return this.type;
    }
    
    
}
