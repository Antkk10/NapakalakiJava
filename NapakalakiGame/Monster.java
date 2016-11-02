/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

/**
 *
 * @author antonio
 * @author marina
 */
public class Monster {
    private String name;
    private int combatLevel;
    private BadConsequence bc;
    private Prize prize;
    private int levelChangeAgainstCultisPlayer;
    
    public Monster(String name, int level, BadConsequence bc, Prize prize, int ic){
        this.name=name;
        this.combatLevel=level;
        this.bc=bc;
        this.prize=prize;
        this.levelChangeAgainstCultisPlayer=ic;
        
    }
    
    public String getTextCombatLevel(){
        String aux = " " + combatLevel;
        
        return aux;
    }
    
    public String getTextCultistLevel(){
        String aux = " " + getCombatLevelAgainstCultisPlayer();
        
        return aux;
    }
    
    // Método que devuelve el nivel de combate a cambiar del monstruo
    // si se enfrenta contra un jugador de tipo cultisplayer
    public int getCombatLevelAgainstCultisPlayer(){
        return (this.levelChangeAgainstCultisPlayer + this.combatLevel);
    }
    
    // Método usado para PrizeView
    public Prize getPrize(){
        return prize;
    }
    
    public String toString(){
        return "name = " + name + ". combatLevel = " + Integer.toString(combatLevel);
    }
    
    public String toStringPremio(){
        return this.prize.toString();
    }
    
    public String getName(){
        
        return name;
    }
    
    public int getCombatLevel(){
        
        return combatLevel;
    }
    
    public BadConsequence getBadConsequence(){
        return this.bc;
    }
    
    public int getLevelsGained(){
        return this.prize.getLevel();
    }
    
    public int getTreasuresGained(){
        return this.prize.getTreasures();
    }
    
    
    
}
