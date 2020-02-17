/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.actor;

import audio.AudioA;

/**
 *
 * @author dingding
 */
public class Pig extends Actor{
    
    private AudioA.PressedMusic audio;

    public Pig(int x, int y, int width, int height, int actor) {
        super(x, y, width, height, actor);
        audio = new AudioA.PressedMusic();
    }
    
    public void randomEat(){
        int n = (int)(Math.random()* 60);
        
        if(n == 0){
            this.holdingObj = null;
            audio.getEat().play();
            
            
        }
    }
    
}