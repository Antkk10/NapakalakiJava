/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

import java.util.ArrayList;
import java.util.Random;
import GUI.Dice;

/**
 *
 * @author antonio
 */
public class Player {
    private String name;
    // Examen
    private int level;
    // Fin examen
    private boolean dead;
    private boolean canISteal;
    // protected para que la pueda usar la subclase CultisPlayer
    protected Player enemy;
    
    private ArrayList<Treasure>hiddenTreasures = new ArrayList();
    private ArrayList<Treasure>visibleTreasures = new ArrayList();
    
    private BadConsequence pendingBadConsequence;
    static final int MAXLEVEL = 10;
    
    public Player(String name){
        this.name=name;
        this.level=1;
        this.dead=true;
        this.canISteal=true;
        this.enemy=null;
        this.pendingBadConsequence=null;
        
    }
    
    // Constructor de copia
    Player(Player p){
        this.name=p.name;
        this.level=p.level;
        this.dead=p.dead;
        this.canISteal=p.canISteal;
        this.enemy=p.enemy;
        //this.pendingBadConsequence=p.pendingBadConsequence;
    }
    
    public String getTextLevel(){
        String aux = " " + this.level;
        
        return aux;
    }
    
    public String getSectario(){
        return "no";
    }
    
    public String getTextCombatLevel(){
        String aux = " " + this.getCombatLevel();
        
        return aux;
    }
    public String getTextMuero(){
        if(dead)
            return "si";
        else
            return "no";
    }
    
    public String getNameEnemy(){
        return this.enemy.getName();
    }
    public BadConsequence getPendingBad(){
        return pendingBadConsequence;
    }
    // Método que devuelve el enemigo.
    public Player getEnemy(){
        return this.enemy;
    }   
    
    // Devuelve el level del monster
    protected int getOponentLevel(Monster m){
        return m.getCombatLevel();
    }
    
    protected boolean shouldConvert(){
        Dice dice = Dice.getInstance();
        int numero = dice.nextNumber();
        
        if(numero == 1)
            return true;
        else
            return false;
        
    }
    public String toString(){
        return "Nombre: " + this.name + ", nivel: " + this.level;
    }

    // Devuelve el nombre del jugador.
    public String getName(){
        return this.name;
    }
    
    private void bringToLife(){
        this.dead=false;
    }
    
   
    protected int getCombatLevel(){
        int suma=0;
       
        
        for(int i=0; i<this.visibleTreasures.size();i++){
            suma=suma+this.visibleTreasures.get(i).getBonus();
        }
        
        return this.level+suma;
        
       
    }
    
    private void incrementLevels(int i){
        if(this.level+i<=Player.MAXLEVEL)
            this.level=this.level+i;
        else
            this.level=10;
    }
    
    private void decrementarLevels(int l){
        if(this.level-l>=1)
            this.level=this.level-l;
        else
            this.level=1;
    }
    
    private void setPendingBadConsequence(BadConsequence b){
        this.pendingBadConsequence=b;
    }
    
    // Método que aplica premio al jugador.
    private void applyPrize(Monster m){
        int nlevels;
        nlevels = m.getLevelsGained();
        this.incrementLevels(nlevels);
        int ntreasures;
        ntreasures=m.getTreasuresGained();
        
        if(ntreasures > 0){
            CardDealer dealer = CardDealer.getInstante();
            
            for(int i=1;i<=ntreasures;i++){
                Treasure treasure = dealer.nextTreasure();
                this.hiddenTreasures.add(treasure);
            }
        }
    }
    
    
    private void applyBadConsequence(Monster m){
        BadConsequence badConsequence = m.getBadConsequence();
        int nlevels = badConsequence.getLevels();
        this.decrementarLevels(nlevels);
        // Solo para práctica 3
        if(badConsequence.getDeath())
            this.discardAllTreasures();
        else{
            BadConsequence pendingBad;
            pendingBad = badConsequence.adjustToFitTreasureLists(visibleTreasures, hiddenTreasures);
        
            this.setPendingBadConsequence(pendingBad);
        }
    }
    
    // Devuelve el número de tesoros visibles de tipo tKind que tiene el jugador.
    private int howManyVisibleTreasures(TreasureKind tkind){
        int contador_tesoros=0;
        
        for(int i=0;i<this.visibleTreasures.size();i++){
            if(this.visibleTreasures.get(i).getType().equals(tkind))
                contador_tesoros=contador_tesoros+1;
        }
        
  
        return contador_tesoros;
    }
    
    // Método que comprueba si un tesoro se puede hacer visible
    private boolean canMakeTreasureVisible(Treasure t){
        if(this.visibleTreasures.size()<4){
            if(t.getType().equals(TreasureKind.ONEHAND)){
                // Comprobamos que no tenga más de un objeto de una mano.
                if(this.howManyVisibleTreasures(t.getType())<2 && this.howManyVisibleTreasures(TreasureKind.BOTHHANDS) == 0)
                    return true;
                else
                    return false;
            }
            else{
                // Comprueba que el tipo de tesoro es arma de dos manos
                if(t.getType().equals(TreasureKind.BOTHHANDS)){
                    // Comprobamos que no tiene ese mismo tesoro, ningún tesoro de dos manos y además menos de 4 tesoros.
                    if((this.howManyVisibleTreasures(t.getType())==0) && this.howManyVisibleTreasures(TreasureKind.ONEHAND)==0)
                        return true;
                    else
                        return false;
                }
                else{
                    // Comprobamos que tiene ese tesoro
                    boolean contiene=false;
                    for(int i=0;i<this.visibleTreasures.size() && !contiene;i++){
                        if(t.getType().equals(this.visibleTreasures.get(i).getType()))
                            contiene=true;
                    }

                    return !contiene;
                }
            }
        }
        else
            return false;
    }
    
    private void dielfNoTreasures(){
        if((this.visibleTreasures.size() == 0) && (this.hiddenTreasures.size()==0)){
            this.dead=true;
        }
    }
    
    // Devuelve true si el jugador está muerto
    public boolean isDead(){
        if(this.dead){
            // Si el jugador está muerto se descarta de todos sus tesoros visibles y ocultos.
            this.discardAllTreasures();
        }
        return this.dead;
    }
    
    // Devuelve el array de tesoros ocultos
    public ArrayList<Treasure> getHiddenTreasures(){
        return this.hiddenTreasures;
    }
    
    // Devuelve el array de tesoros visibles
    public ArrayList<Treasure> getVisibleTreasures(){
        return this.visibleTreasures;
    }
    
    // Método que simula el combate entre monstruo y player
    public CombatResult combat(Monster m){
        int myLevel = this.getCombatLevel();
        // El nivel de combate del monstruo que nos lo da el método
        // getOponentLevel
        int monsterLevel = this.getOponentLevel(m);
        CombatResult combatResult;
        
        if(myLevel > monsterLevel){
            this.applyPrize(m);
            if(this.level>= Player.MAXLEVEL)
                combatResult= CombatResult.WINGAME;
            else
                combatResult = CombatResult.WIN;
        }
        else{
            this.applyBadConsequence(m);
            // Si devuelve true guardamos en el resultado LOSEANDCONVERT
            // para convertirse en sectario.
            if(this.shouldConvert())
                combatResult = CombatResult.LOSEANDCONVERT;
            else
                combatResult = CombatResult.LOSE;
        }
        
        return combatResult;
    }
    
    // Método que cambia el tesoro de oculto a visible
    public void makeTreasureVisible(Treasure t){
        // Comprobamos si tiene el tesoro visible.
        if( this.canMakeTreasureVisible(t)){
            
            // Ponemos el objeto visible
            this.visibleTreasures.add(t);
            
            discardHiddenTreasure(t);
        }
    }
    
    // Método que descarta un tesoro visible
    public void discardVisibleTreasure(Treasure t){
       
        
        int pos=0;
        for(int i=0;i<this.visibleTreasures.size();i++){
            if(t.getBonus() == visibleTreasures.get(i).getBonus() && t.getName().equals(visibleTreasures.get(i).getName()) &&
                    t.getType().equals(visibleTreasures.get(i).getType()))
                pos=i;
        }
        if(visibleTreasures.size() != 0)
            visibleTreasures.remove(pos);
        
        // Comprueba si queda mala consecuencia por cumplir
        if((this.pendingBadConsequence!=null) && (!this.pendingBadConsequence.isEmpty()))
            // Actualiza la mala consecuencia pendiente para que el tesoro no forme
            // parte de la mala consecuencia
            this.pendingBadConsequence.substractVisibleTreasure(t);
        
        this.dielfNoTreasures();
    }
    
    // Método que descarta un tesoro oculto
    public void discardHiddenTreasure(Treasure t){
        int pos=0;
        for(int i=0;i<this.hiddenTreasures.size();i++){
            if(t.getBonus() == hiddenTreasures.get(i).getBonus() && t.getName().equals(hiddenTreasures.get(i).getName()) &&
                    t.getType().equals(hiddenTreasures.get(i).getType()))
                pos=i;
        }
        
        if(hiddenTreasures.size() != 0)
            hiddenTreasures.remove(pos);
        
        // Comprueba si hay una mala consecuencia por cumplor
        if((this.pendingBadConsequence!=null) && (!this.pendingBadConsequence.isEmpty())){
            // Actualiza la mala consecuencia.
            this.pendingBadConsequence.substractHiddenTreasure(t);
        }
        this.dielfNoTreasures();
    }
    
    // Devuelve true cuando el jugador no tiene ningún mal rollo que cumplir
    // (llamando a la función isEmpty de BadConsequence) y no tiene más de 4
    // tesoros ocultos.
    public boolean validState(){
        if(this.pendingBadConsequence==null)
            return true;
        
        return (this.pendingBadConsequence.isEmpty() && (this.hiddenTreasures.size() <= 4));
    }
    
    public void initTreasures(){
        CardDealer dealer = CardDealer.getInstante();
        Dice dice = Dice.getInstance();
        this.bringToLife();
        Treasure treasure = dealer.nextTreasure();
        this.hiddenTreasures.add(treasure);
        int number = dice.nextNumber();
        if(number > 1){
            treasure = dealer.nextTreasure();
            this.hiddenTreasures.add(treasure);
        }
        if(number==6){
            treasure = dealer.nextTreasure();
            this.hiddenTreasures.add(treasure);
        }
            
    }
    
    public int getLevels(){
        return this.level;
        
    }
    
    public Treasure stealTreasure(){
        Treasure treasure = null;
        if(this.canISteal()){
            if(this.enemy.canYouGiveMeATreasure()){
                treasure = this.enemy.giveMeATreasure();
                this.hiddenTreasures.add(treasure);
                this.haveStolen();
            }
        }
        return treasure;
    }
    
    public void setEnemy(Player enemy){
        this.enemy=enemy;
        
    }
    
    // Devuelve un tesoro oculto.
    protected Treasure giveMeATreasure(){
        Random r = new Random();
        int numero = r.nextInt(this.hiddenTreasures.size());
        // Guardamos el tesoro que vamos a dar al enemigo y lo borramos de nuestro mazo de ocultos
        Treasure treasure = this.hiddenTreasures.remove(numero);
        return treasure;
        
    }
    
    public boolean canISteal(){
        return this.canISteal;
    }
    
    
    public boolean canYouGiveMeATreasure(){
        return !this.hiddenTreasures.isEmpty();
    }
    
    private void haveStolen(){
        this.canISteal=false;
    }
    
    public void discardAllTreasures(){
        
        /* Por qué while y no un for con contador que vaya aumentando?
        Cuando llama al método discardVisibleTreasure manda un tesoro para borrar y
        dentro de ese método elimina el tesoro del array visibleTreasure con lo cual el size decrementa en uno
        ya que se ha borrado una posición del array.
        Como queremos borrarlos todos, llamará a la función size cada vez que comprueba la condición
        y llegará un momento que valga 0, que es cuando acabamos de borrar.
        */
        int cnt = visibleTreasures.size();
        for(int i=0;i<cnt;i++){
            Treasure treasure = this.visibleTreasures.get(0);
            discardVisibleTreasure(treasure);
        }
        
        cnt = hiddenTreasures.size();
        
        for(int i=0;i<cnt;i++){
            Treasure treasure = this.hiddenTreasures.get(0);
            this.discardHiddenTreasure(treasure);
        }
    }
    
    
}
