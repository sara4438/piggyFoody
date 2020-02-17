/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.food;

import gameobject.GameObject;
import java.awt.Graphics;
import java.util.ArrayList;
import audio.AudioA;

/**
 *
 * @author sara
 */
public class Cuttable extends  Food {
      private AudioA.PressedMusic audio;

    public Cuttable(int x, int y, int width, int height, int foodImg){
        super ( x,y,width, height, foodImg);      
        isCut = false;
        audio = new AudioA.PressedMusic();
    }
        public Cuttable(int x, int y, int width, int height, int foodImg, int e){
        super ( x,y,width, height, foodImg, e);      
    } 
        public void isCut(){
            if( !isCut){
                audio.getCut().play();
                this.foodImg = foodImg +1;
                this.foodHelper = new FoodHelper(this.foodImg);
            }            
            isCut = true;
        }
}
