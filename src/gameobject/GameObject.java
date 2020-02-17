/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import gameobject.actor.Actor;
import gameobject.food.NonCuttable;
import gameobject.food.NonCuttable;
import java.awt.Graphics;
import java.util.ArrayList;
import utils.Global;

/**
 *
 * @author dingding
 */
public abstract class GameObject {

    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected boolean isHeld = false;

    public GameObject(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
  
    public int getX() {
        return x;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
    //coordinate end

    //Bound start 四個邊
    public int getBottom() {
        return y + height;
    }

    public int getTop() {
        return y;
    }

    public int getLeft() {
        return x;
    }

    public int getRight() {
        return x + width;
    }
    //Bound end
    
        public int getImageNum(){
        return -1;
    }   
        public void isHeld (boolean isHeld , int position){
            this.isHeld = isHeld;
        }
    
    

    public boolean isTopCollision(GameObject obj) {
        if (getTop() <= obj.getBottom() && getY() == obj.getY()) {    
            return true;
        }
        return false;
    } 
    

    public boolean isCollision(GameObject obj) {
        if (getLeft() > obj.getRight()) {//沒碰到
            return false;
        }
        if (getRight() < obj.getLeft()) {//沒碰到
            return false;
        }
        if (getTop() > obj.getBottom()) {//沒碰到
            return false;
        }
        if (getBottom() < obj.getTop()) {//沒碰到
            return false;
        }
        return true;
    }
    
        
    public void followMove(MovableGameObject movableObj){
        this.x = movableObj.getX();
        this.y = movableObj.getY();
    }
    
    
     public void changeLocation(GameObject gameObj) { //移動到此obj位置
        if (gameObj instanceof GameObject) {
            GameObject actor = (GameObject) gameObj;
            this.x = gameObj.getX();
            this.y = gameObj.getY() + 10;
            return;
        }
        this.x = gameObj.getX();
        this.y = gameObj.getY();
        return;
    }

    public abstract void paint(Graphics g);
}
