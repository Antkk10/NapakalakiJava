/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

import GUI.Dice;
import GUI.NapakalakiView;
import GUI.PlayerNamesCapture;
import java.util.ArrayList;

/**
 *
 * @author antonio
 */
public class MainPrincipal {
    
    public static void main(String[] args){
        // Obtenemos la única instancia de Napakalaki
        Napakalaki game = Napakalaki.getInstance();
        
        NapakalakiView napakalakiView = new NapakalakiView();
        // Crea la instancia del dado
        Dice.createInstance(napakalakiView);
        
        
        
        ArrayList<String> names;
        
        PlayerNamesCapture namesCapture = new PlayerNamesCapture(napakalakiView,true);
        // Obtiene los nombres de los jugadores
        names = namesCapture.getNames();
        // Inicia el juego
        game.initGame(names);
        // Se enlaza el modelo a la vista
        napakalakiView.setNapakalaki(game);
        // Se muestra la ventana principal de la aplicación
        napakalakiView.setVisible(true);
        
        napakalakiView.setNapakalaki(game);
        
        
        
        
        
        
        
        
    }
}
