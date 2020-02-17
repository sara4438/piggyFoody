/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.food;

import gameobject.GameObject;
import gameobject.MovableGameObject;
import java.awt.Graphics;
import java.util.ArrayList;
import utils.Global;

/**
 *
 * @author dingding
 */
public class NonCuttable extends Food {
    public NonCuttable(int x, int y, int width, int height, int foodImg){
        super ( x,y,width, height, foodImg);      
        isCut = true;

    }
        public NonCuttable(int x, int y, int width, int height, int foodImg, int e){
        super ( x,y,width, height, foodImg, e);     
        isCut = true;
    } 
    
  }
    
    


