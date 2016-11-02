/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

import java.util.ArrayList;
import java.util.Random;


/**
 *
 * @author antonio
 */
public class Napakalaki {
    private static final Napakalaki instance=new Napakalaki();
    private Player currentPlayer=null;
    private ArrayList<Player> players = new ArrayList();
    private CardDealer dealer;
    private Monster currentMonster;    
    
    private Napakalaki(){
        
    }
    
    public static Napakalaki getInstance(){
        return instance;
    }
    
    // Creación de una lista de jugadores.
    private void initPlayers(ArrayList<String> names){
        Player jugador;
        
        for(int i=0;i<names.size();i++){
            jugador = new Player(names.get(i));
            players.add(jugador);
            
        }
    }
    
    // Indica el jugador que le toca
    private Player nextPlayer(){
        
        // compruebo si es la primera jugada. Jugador actual = null
        if(this.currentPlayer==null){
            Random r = new Random();
            int jugador_empieza = r.nextInt(players.size());
            return this.players.get(jugador_empieza);
        }
        // Jugador siguiente.
        // indexof devuelve la posición del jugador pasado como parámetro.
        else{
            int jugador_siguiente = this.players.indexOf(this.currentPlayer);
            int total_jugadores = this.players.size();
            jugador_siguiente = (jugador_siguiente + 1) % total_jugadores;
            return this.players.get(jugador_siguiente);
        }
    }
    
    private boolean nextTurnAllowed(){
        if(this.currentPlayer==null)
            return true;        
        return this.currentPlayer.validState();
    }
    
    private void setEnemies(){
        Random r;
        int total_jugadores = this.players.size();
        int i=0;
        while(i<total_jugadores){
            r = new Random();
            int aleatorio = r.nextInt(total_jugadores);
            if(i!=aleatorio){
                this.players.get(i).setEnemy(this.players.get(aleatorio));
                i++;
            }
        }
    }
    
    public CombatResult developCombat(){
        CombatResult combatResult;
        combatResult = this.currentPlayer.combat(currentMonster);
        // Si combatResult == LOSEANDCONVERT
        if ( combatResult.equals(CombatResult.LOSEANDCONVERT)){
            // Se obtiene una carta de tipo cultist
            Cultist carta_cultist = this.dealer.nextCultist();
            // Creamos un jugador de tipo cultist pasandole el jugador actual y la carta anterior.
            CultistPlayer cultist_player = new CultistPlayer(this.currentPlayer,carta_cultist);
            for(int i=0;i<players.size();i++){
                if(players.get(i).getEnemy().equals(this.currentPlayer))
                    players.get(i).setEnemy(cultist_player);
            }
            // Sustituimos en el array de jugadores al actual jugador por cultis_player
            this.players.set(this.players.indexOf(this.currentPlayer), cultist_player);
            // También lo sustituimos en el jugador actual
            this.currentPlayer=cultist_player;
        }
        this.dealer.giveMonsterBack(currentMonster);
        return combatResult;
    }
    
    public void discardVisibleTreasures(ArrayList<Treasure> treasures){
        Treasure treasure;
        int cnt=treasures.size();
        for(int i=0;i<cnt;i++){
            treasure=treasures.get(0);
            this.currentPlayer.discardVisibleTreasure(treasure);
            this.dealer.giveTreasureBack(treasure);
        }
    }
    
    public void discardHiddenTreasures(ArrayList<Treasure> treasures){
        Treasure treasure;
        int cnt=treasures.size();
        for(int i=0;i<cnt;i++){
            treasure=treasures.get(0);
            this.currentPlayer.discardHiddenTreasure(treasure);
            this.dealer.giveTreasureBack(treasure);
        }
    }
    
    public void makeTreasuresVisible(ArrayList<Treasure> treasures){
        int cnt=treasures.size();
        for(int i=0;i<cnt;i++){
            this.currentPlayer.makeTreasureVisible(treasures.get(i));
        }
    }
    
    public void initGame(ArrayList<String> players){
        
        this.initPlayers(players);
        
        this.setEnemies();
        
        this.dealer = CardDealer.getInstante();
        dealer.initCards();
        
        this.nextTurn();
        
    }
    
    public Player getCurrentPlayer(){
        return this.currentPlayer;
    }
    
    public Monster getCurrentMonster(){
        return this.currentMonster;
    }
    
    public boolean nextTurn(){
        boolean stateOK = this.nextTurnAllowed();
        if(stateOK){
            this.currentMonster=dealer.nextMonster();
            this.currentPlayer=this.nextPlayer();
            boolean dead= this.currentPlayer.isDead();
            if(dead || (currentPlayer.getHiddenTreasures().size() == 0 && currentPlayer.getVisibleTreasures().size() == 0)){
                this.currentPlayer.initTreasures();
            }
        }
        return stateOK;
    }
    // Comprueba si es el final del juego.
    public boolean endOfGame(CombatResult result){
        return result.equals(CombatResult.WINGAME);
        
    }
}
