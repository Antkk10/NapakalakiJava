/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package NapakalakiGame;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author antonio
 */
public class CardDealer {
    private static final CardDealer instance = new CardDealer();
    
    private ArrayList<Monster> unusedMonster=new ArrayList();
    private ArrayList<Monster> usedMonster = new ArrayList();
    private ArrayList<Treasure> usedTreasures = new ArrayList();
    private ArrayList<Treasure> unusedTreasures = new ArrayList();
    private ArrayList<Cultist> unusedCultists = new ArrayList();
    private ArrayList<Cultist> usedCultists = new ArrayList();
    
    private CardDealer(){
        
    }
    
    public static CardDealer getInstante(){
        return instance;
    }
    
    // Baraja el ArrayList de tipo Cultist
    private void shuffleCultists(){
        Collections.shuffle(this.unusedCultists);
    }
    
    // Método que crea las cartas de tipo Cultist
    private void initCultistCardDeck(){
        unusedCultists.add(new Cultist("Sectario",1));
        unusedCultists.add(new Cultist("Sectario",2));
        unusedCultists.add(new Cultist("Sectario",1));
        unusedCultists.add(new Cultist("Sectario",2));
        unusedCultists.add(new Cultist("Sectario",1));
        unusedCultists.add(new Cultist("Sectario",1));
    }
   
    
    private void initTreasureCardDeck(){
        this.unusedTreasures.add(new Treasure("¡Sí, mi amo!",4,TreasureKind.HELMET));
        this.unusedTreasures.add(new Treasure("Botas de investigación",3,TreasureKind.SHOES));
        this.unusedTreasures.add(new Treasure("Capucha de Cthulhu",3,TreasureKind.HELMET));
        this.unusedTreasures.add(new Treasure("A prueba de babas",2,TreasureKind.ARMOR));
        this.unusedTreasures.add(new Treasure("Botas de lluvia ácida",1,TreasureKind.BOTHHANDS));
        this.unusedTreasures.add(new Treasure("Casco minero",2,TreasureKind.HELMET));
        this.unusedTreasures.add(new Treasure("Ametralladora Thompson",4,TreasureKind.BOTHHANDS));
        this.unusedTreasures.add(new Treasure("Camiseta de la UGR",1,TreasureKind.ARMOR));
        this.unusedTreasures.add(new Treasure("Clavo de rail ferroviario",3,TreasureKind.ONEHAND));
        this.unusedTreasures.add(new Treasure("Cuchillo de sushi arcano",2,TreasureKind.ONEHAND));
        this.unusedTreasures.add(new Treasure("Fez alópodo",3,TreasureKind.HELMET));
        this.unusedTreasures.add(new Treasure("Hacha prehistórica",2,TreasureKind.ONEHAND));
        this.unusedTreasures.add(new Treasure("El apartado del Pr. Tesla",4,TreasureKind.HELMET));
        this.unusedTreasures.add(new Treasure("Gaita",4,TreasureKind.BOTHHANDS));
        this.unusedTreasures.add(new Treasure("Insecticida",2,TreasureKind.ONEHAND));
        this.unusedTreasures.add(new Treasure("Escopeta de 3 cañones",4,TreasureKind.BOTHHANDS));
        this.unusedTreasures.add(new Treasure("Garabato místico",2,TreasureKind.ONEHAND));
        this.unusedTreasures.add(new Treasure("La rebeca metálica",2,TreasureKind.ARMOR));
        this.unusedTreasures.add(new Treasure("Lanzallamas",4,TreasureKind.BOTHHANDS));
        this.unusedTreasures.add(new Treasure("Necrocomicón",1,TreasureKind.ONEHAND));
        this.unusedTreasures.add(new Treasure("Necronomicón",5,TreasureKind.BOTHHANDS));
        this.unusedTreasures.add(new Treasure("Linterna a 2 manos",3,TreasureKind.BOTHHANDS));
        this.unusedTreasures.add(new Treasure("Necrognomicón",2,TreasureKind.ONEHAND));
        this.unusedTreasures.add(new Treasure("Necrotelecom",2,TreasureKind.ARMOR));
        this.unusedTreasures.add(new Treasure("Mazo de los antiguos",3,TreasureKind.ONEHAND));
        this.unusedTreasures.add(new Treasure("Necroplayboycón",3,TreasureKind.ONEHAND));
        this.unusedTreasures.add(new Treasure("Porra preternatural",2,TreasureKind.ONEHAND));
        this.unusedTreasures.add(new Treasure("Shogulador",1,TreasureKind.BOTHHANDS));
        this.unusedTreasures.add(new Treasure("Varita de atizamiento",3,TreasureKind.ONEHAND));
        this.unusedTreasures.add(new Treasure("Tentáculo de pega",2,TreasureKind.HELMET));
        this.unusedTreasures.add(new Treasure("Zapato deja-amigos",1,TreasureKind.SHOES));     
    }
    
    private void initMonsterCardDeck(){
        SpecificBadConsequence specificBad;
        NumericBadConsequence numericBad;
        DeathBadConsequence deathBad;
        numericBad=new NumericBadConsequence("Pierdes 5 niveles y 3 tesoros visibles",5,3,0,false);
        Prize prize = new Prize(4,2);
        this.unusedMonster.add(new Monster("El rey de la rosa",13,numericBad,prize,0));
        
        specificBad = new SpecificBadConsequence("Te atrapan para llevarte"
                + " de fiesta y te dejan caer en mitad del vuelo. Descarta"
                + " 1 mano visible y 1 mano oculta",0,
                new ArrayList(Arrays.asList(TreasureKind.ONEHAND)),
                new ArrayList(Arrays.asList(TreasureKind.ONEHAND)));
        prize = new Prize(4,1);
        this.unusedMonster.add(new Monster("Angeles de la noche ibicenca",14,
                      specificBad,prize,0));
        
        specificBad = new SpecificBadConsequence("Pierdes tu armadura visible y otra oculta",0,
                         new ArrayList(Arrays.asList(TreasureKind.ARMOR)),
                         new ArrayList(Arrays.asList(TreasureKind.ARMOR)));
        prize= new Prize(2,1);
        this.unusedMonster.add(new Monster("3 Byakhees de bonanza",8,specificBad, prize,0));
        
        specificBad = new SpecificBadConsequence("Embobados con el lindo primigenio te descartas"
                + "de tu casco visible",0,new ArrayList(Arrays.asList(TreasureKind.HELMET)),new ArrayList());
        
        prize = new Prize(1,1);
        
        this.unusedMonster.add(new Monster("Chibithulbu",2,specificBad,prize,0));
        
        specificBad = new SpecificBadConsequence("El primordia bostezo contagioso. Pierdes"
                + " el calzado visible",0,new ArrayList(Arrays.asList(TreasureKind.SHOES)),
                new ArrayList());
        
        prize = new Prize(1,1);
        this.unusedMonster.add(new Monster("El sopor de Dunwich",2,specificBad,prize,0));
        
        numericBad = new NumericBadConsequence("Pierdes todos tus tesoros visibles",
        0,BadConsequence.MAXTREASURES,0,false);
        
        prize = new Prize(3,1);
        
        this.unusedMonster.add(new Monster("El gorrón en el umbral",10,numericBad,prize,0));
        
        specificBad = new SpecificBadConsequence("Pierdes la armadura visible",0,
                         new ArrayList(Arrays.asList(TreasureKind.ARMOR)),new ArrayList());
        
        prize = new Prize(2,1);
        
        this.unusedMonster.add(new Monster("H.P. Munchcraft",6,specificBad,prize,0));
        
        specificBad = new SpecificBadConsequence("Sientes bichos bajo la ropa. Descarta la"
                + " armadura visible",0,new ArrayList(Arrays.asList(TreasureKind.ARMOR)),
                new ArrayList());
        prize= new Prize(1,1);
        
        this.unusedMonster.add(new Monster("Bichgooth",2,specificBad,prize,0));
        
        numericBad = new NumericBadConsequence("Toses los pulmones y pierdes dos niveles",2,0,0,false);
        prize = new Prize(1,1);
        this.unusedMonster.add(new Monster("La que redacta en las tinieblas",2,numericBad,prize,0));
        
        deathBad = new DeathBadConsequence("Estos monstruos resultan bastante superficiales"
                + " y te aburren mortalmente. Estas muerto");
        prize= new Prize(2,1);
        this.unusedMonster.add(new Monster("Los hondos",8,deathBad,prize,0));
        
        numericBad = new NumericBadConsequence("Pierdes 2 niveles y 2 tesoros ocultos",2,0,2,false);
        prize= new Prize(2,1);
        this.unusedMonster.add(new Monster("Semillas Cthulhu",4,numericBad,prize,0));
        
        specificBad = new SpecificBadConsequence("Te intentas escaquear. Pierdes una mano visible",0,
                         new ArrayList(Arrays.asList(TreasureKind.ONEHAND)),
                         new ArrayList());
        prize = new Prize(2,1);
        this.unusedMonster.add(new Monster("Dameargo",1,specificBad,prize,0));
        
        numericBad = new NumericBadConsequence("Da mucho asquito. Pierdes 3 niveles",3,0,0,false);
        prize = new Prize(1,1);
        this.unusedMonster.add(new Monster("Pollipólipo volante",3,numericBad,prize,0));
        
        deathBad = new DeathBadConsequence("No le hace gracia que pronuncien mal su nombre."
                + " Estas muerto.");
        prize = new Prize(3,1);
        this.unusedMonster.add(new Monster("YskhtihyssgGoth",12,deathBad,prize,0));
        
        deathBad = new DeathBadConsequence("La familia te atrapa. Estas muerto");
        prize = new Prize(4,1);
        this.unusedMonster.add(new Monster("Familia feliz",1,deathBad,prize,0));
        
        specificBad = new SpecificBadConsequence("La quinta directiva primaria te obliga a perder"
                + "2 niveles y un tesoro 2 manos visible",2,new ArrayList(Arrays.asList(TreasureKind.BOTHHANDS)),
                new ArrayList());
        prize = new Prize(2,1);
        this.unusedMonster.add(new Monster("Roboggoth",8,specificBad,prize,0));
        
        specificBad = new SpecificBadConsequence("Te asusta en la noche",0,new ArrayList(Arrays.asList(TreasureKind.HELMET)),
        new ArrayList());
        prize = new Prize(1,1);
        this.unusedMonster.add(new Monster("El espia",5,specificBad,prize,0));
        
        numericBad = new NumericBadConsequence("Menudo susto te llevas. Pierdes 2 niveles y 5 tesoros"
                + " visibles",2,5,0,false);
        prize = new Prize(1,1);
        this.unusedMonster.add(new Monster("El Lenguas",20,numericBad, prize,0));
        
        specificBad = new SpecificBadConsequence("Te faltan manos para tanta cabeza. Pierdes"
                + " 3 niveles y tus tesoros visibles de las manos",3,
                new ArrayList(Arrays.asList(TreasureKind.BOTHHANDS, TreasureKind.ONEHAND, TreasureKind.ONEHAND)),
                new ArrayList());
        prize = new Prize(1,1);
        this.unusedMonster.add(new Monster("Bicéfalo",20,specificBad,prize,0));
        
        specificBad = new SpecificBadConsequence("Pierdes una mano visible",0,new ArrayList(Arrays.asList(TreasureKind.ONEHAND)),new ArrayList());
        prize = new Prize(3,1);
        this.unusedMonster.add(new Monster("El mal indecible impronunciable",10,specificBad,prize,-2));
        
        numericBad = new NumericBadConsequence("Pierdes tus tesoros visibles. Jajaja",0,BadConsequence.MAXTREASURES,0,false);
        prize = new Prize(2,1);
        this.unusedMonster.add(new Monster("Testigos oculares",6,numericBad,prize, +2));
        
        deathBad = new DeathBadConsequence("Hoy no es tu día de suerte, mueres.");
        prize = new Prize(2,5);
        this.unusedMonster.add(new Monster("El gran cthulhu.",20,deathBad,prize,+4));
        
        numericBad = new NumericBadConsequence("Tu gobierno te recorta 2 niveles",2,0,0,false);
        prize = new Prize(2,1);
        this.unusedMonster.add(new Monster("Serpiente Político.",8,numericBad,prize,-2));
        
        specificBad = new SpecificBadConsequence("Pierdes tu casco y tu armadura visible. Pierdes tus manos ocultas",
        0,new ArrayList(Arrays.asList(TreasureKind.HELMET,TreasureKind.ARMOR)), new ArrayList(Arrays.asList(TreasureKind.BOTHHANDS)));
        prize = new Prize(1,1);
        this.unusedMonster.add(new Monster("Felpuggoth",2,specificBad,prize,+5));
        
        numericBad = new NumericBadConsequence("Pierdes 2 niveles",2,0,0,false);
        prize = new Prize(4,2);
        this.unusedMonster.add(new Monster("Shoggoth",16,numericBad,prize,-4));
        
        numericBad = new NumericBadConsequence("Pintalabios negro. Pierdes 2 niveles",2,0,0,false);
        prize = new Prize(1,1);
        this.unusedMonster.add(new Monster("Lolitagooth",2,numericBad,prize,3));
        
        
    }
    
    private void shuffleTreasures(){
        Collections.shuffle(this.unusedTreasures);
    }
    
    private void shuffleMonsters(){
        Collections.shuffle(this.unusedMonster);
    }
    // Devuelve la siguiente carta de tipo Cultist
    public Cultist nextCultist(){
        // Comprobamos que todavía hay cartas sin usar
        if(!this.unusedCultists.isEmpty()){
            // Guardamos la carta en la variable de retorno
            Cultist cultist = this.unusedCultists.get(0);
            // La añadimos a las cartas usadas quitandolas de las no usadas
            this.usedCultists.add(this.unusedCultists.remove(0));
            
            return cultist;
        }
        else{
            
            // El array sin usar está vacio, lo cambiamos por todas las cartas usadas.
            this.unusedCultists=this.usedCultists;
            // Nuevo espacio para las cartas usadas, array vacio
            this.usedCultists = new ArrayList();
            // Barajamos el array unusedCultists
            this.shuffleCultists();
            // Guardamos una carta en la variable de retorno
            Cultist cultist = this.unusedCultists.get(0);
            // Guardamos la carta que usamos en el array de cartas usadas. Al mismo tiempo
            // lo quitamos de las cartas no usadas
            this.usedCultists.add(this.unusedCultists.remove(0));
            
            return cultist;
        }
    }
    public Treasure nextTreasure(){
        if(!this.unusedTreasures.isEmpty()){
            Treasure treasure = this.unusedTreasures.get(0);
            //this.unusedTreasures.remove(0);
            // Linea nueva
            this.usedTreasures.add(this.unusedTreasures.remove(0));
            return treasure;
        }
        else{
            this.unusedTreasures=this.usedTreasures;
            this.usedTreasures = new ArrayList();
            this.shuffleTreasures();
            Treasure treasure = this.unusedTreasures.get(0);
            this.usedTreasures.add(this.unusedTreasures.remove(0));
            return treasure;
        }
    }
    
    public Monster nextMonster(){
        if(!this.unusedMonster.isEmpty()){
            Monster monstruo = this.unusedMonster.get(0);
            this.usedMonster.add(this.unusedMonster.remove(0));
            return monstruo;
        }
        else{
            this.unusedMonster=this.usedMonster;
            this.usedMonster=new ArrayList();
            this.shuffleMonsters();
            Monster monstruo = this.unusedMonster.get(0);
            this.usedMonster.add(this.unusedMonster.remove(0));
            return monstruo;
        }
           
    }
    
    public void giveTreasureBack(Treasure t){
        
        this.usedTreasures.add(t);
    }
    
    public void giveMonsterBack(Monster m){
        this.usedMonster.add(m);
    }
    
    // Llamada a los métodos para iniciar las cartas monster, tesoros y cultis.
    // Posteriormente barajamos los tres arraylist con la correspondiente llamada
    // a los métodos.
    public void initCards(){
       
        this.initMonsterCardDeck();       
        this.initTreasureCardDeck();
        this.initCultistCardDeck();
        
        this.shuffleCultists();
        this.shuffleMonsters();
        this.shuffleTreasures();
    }
}
