/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package evogame;


/**
 *
 * @author christophersisa
 */
public class Launcher {

    public static void main(String[] args) {
        Game game = new Game("evolution game", 1000, 1000);
        game.start();
    }
    
}
