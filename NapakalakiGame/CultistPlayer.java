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
public class CultistPlayer extends Player {
    
    private static int totalCultistPlayers = 0;
    private Cultist myCultistCard;
    
    public CultistPlayer(Player p, Cultist c){
        super(p);
        totalCultistPlayers++;
        this.myCultistCard=c;
    }
    @Override
    public String getSectario(){
        return "Si";
    }
    @Override
    protected int getCombatLevel(){
        double resultado;
        
        // Obtenemos el nivel de combate de la clase Player más el 20% de lo que obtenemos
        resultado = super.getCombatLevel() * 1.2;
        // Le sumamos el nivel ganando de su carta de sectario 
        // multiplicados por el número de sectarios de la partida
        resultado += this.myCultistCard.getGainedLevels() * CultistPlayer.totalCultistPlayers;
        
        // Devuelve el entero de resultado
        return (int) resultado;
        
    }
    // Devuelve el nivel de combate del monstruo contra un jugador sectario
    @Override
    protected int getOponentLevel(Monster m){
        return m.getCombatLevelAgainstCultisPlayer();
    }
    
    @Override
    protected boolean shouldConvert(){
        return false;
    }
    
    // Se devuelve un tesoro visible elegido al azar.
    @Override
    protected Treasure giveMeATreasure(){
        // Variblae estática de tipo ArrayList<Treasure>
        ArrayList<Treasure> visibleTreasures;
        // Obtenemos los objetos visibles que tenemos usando el método de la superclase
        visibleTreasures = super.getVisibleTreasures();
        // variable para calcular un número aleatorio
        Random r = new Random();
        // Calculo de un número aleatorio entre 0 y el total de objetos visibles - 1
        int numero = r.nextInt(visibleTreasures.size());
        // Guardamos el tesoro que vamos a dar al enemigo y lo borramos de nuestro mazo de visibles
        Treasure treasure = visibleTreasures.remove(numero);
        return treasure;
    }
    
    // Método que consulta si el enemigo tiene tesoros visibles para ser robados
    // Cambiado de privado a protected
    @Override
    public boolean canYouGiveMeATreasure(){
        return super.enemy.getVisibleTreasures().isEmpty();
    }
    
    // Devuelve el número de jugadores totales de tipo CultisPlayers
    public static int getTotalCultisPlayers(){
        return totalCultistPlayers;
    }
    
}
