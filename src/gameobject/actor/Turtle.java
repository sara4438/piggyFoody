/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.actor;

import gameobject.GameObject;
import utils.Global;
import audio.AudioA;

/**
 *
 * @author sara
 */
public class Turtle extends Actor {
    
    private AudioA.PressedMusic audio;
    
    public Turtle(int x, int y, int width, int height, int actor){
        super (x,y,width, height, actor);
        audio = new AudioA.PressedMusic();
    }
    
    public boolean grabFood(Actor actor){
        if(checkGrab(actor)){
             audio.getPressedTrash().play();
            this.holdingObj = actor.holdingObj;
            actor.setHoldingObj(null);
            return true;
        }
        return false; 
    }
    
     public boolean checkGrab(Actor actor) {
        int left = x;
        int oLeft = actor.getX();
        int right = x + Global.IMG_SQUARE;
        int oRight = actor.getX() + Global.IMG_SQUARE;
        int top = y;
        int oTop = actor.getY();
        int bottom = y + Global.IMG_SQUARE;
        int oBottom = actor.getY() + Global.IMG_SQUARE;
        int oMidX = (oLeft + oRight) / 2;
        int oMidY = (oTop + oBottom) / 2;
        int midX = (left + right) / 2;
        int midY = (top + bottom) / 2;
        float overLapOffset = (Global.IMG_SQUARE * 2) / 3;
        int distance = (int) Math.sqrt(Math.pow(oMidX - midX, 2) + Math.pow(oMidY - midY, 2));

        if (distance < overLapOffset && actor.getHoldingObj() != null) {
            return true;
        }
        return false;
    }

    
}
