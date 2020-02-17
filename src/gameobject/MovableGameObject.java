/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import gameobject.table.Table;
import gameobject.food.NonCuttable;
import gameobject.food.NonCuttable;
import java.util.ArrayList;
import utils.Global;

/**
 *
 * @author dingding
 */
public abstract class MovableGameObject extends GameObject {

//    private int speed;
    protected double dx;
    protected double dy;
    
    public MovableGameObject(int x, int y, int width, int height) {
        super(x, y, width, height);
//        this.speed = speed;
    }
    
    public  void setSpeedX(double speed){         
         this.dx = speed * Global.ACT_SPEED;
     }

    
    public double getSpeedX(){
        return dx;
    }
     public  void setSpeedY(double speed){
             this.dy = speed * Global.ACT_SPEED;
    }
    
    
    public double getSpeedY(){
        return dy;
    }
    

 
    
    public boolean hasVerticalCollision(ArrayList<Table> objs){
        for(int i =0;i<objs.size();i++){
            Table obj = objs.get(i);
            //如果左右沒碰到 上下碰到
            if(this.getLeft() < obj.getRight() && this.getRight() > obj.getLeft()){//左右沒碰到
                if(this.getBottom() >= obj.getTop() && this.getBottom() <= obj.getTop() + obj.getHeight()/2
                        && this.getTop() < obj.getBottom() 
                        && this.getSpeedY() > 0){//判斷下面
                      y = obj.getTop() - height;
                      this.setSpeedY(0);
                    return true;                    
                }             
                   
                if(this.getTop() <= obj.getBottom() && this.getTop() >= obj.getTop() + obj.getHeight() / 2
                        && this.getBottom() > obj.getTop()
                        && this.getSpeedY() < 0){
                    y = obj.getBottom();//判斷上面
                    this.setSpeedY(0);
                    return true;
                
                }
            }
        } 
        return false;
    }
    
    public boolean hasHorizontalCollision(ArrayList<Table> objs){
        for(int i =0;i<objs.size();i++){
            Table obj = objs.get(i);
            //如果上下碰到 左右沒碰到
            if(this.getTop() < obj.getBottom() && this.getBottom() > obj.getTop()){
                if(this.getRight() >= obj.getLeft()  && this.getRight() <= obj.getLeft() + obj.getWidth()/2
                        && this.getLeft() < obj.getRight() 
                        && this.getSpeedX() > 0){
                    x = obj.getLeft() - this.width;//判斷右邊 邊界
                    this.setSpeedX(0);
                    return true;            
                } 
                if(this.getLeft() <= obj.getRight()
                        && this.getRight() > obj.getLeft()&& this.getLeft() >= obj.getLeft() + obj.getWidth()/2
                        && this.getSpeedX() < 0){
                    x = obj.getRight();//判斷左邊
                    this.setSpeedX(0);
                    return true;
                }
            }
        } 
        return false;
    }
     
    public void move(ArrayList<Table> objs){        
        if(!hasHorizontalCollision(objs)){
            if(this.x <= 100){
                if(dx < 0){
                    x = 100;
                    return;
                }
            }else if (this.x >950){
                if(dx > 0){
                    x = 950;
                    return;
                }
            }
             x += dx;
        }                    
        if(!hasVerticalCollision(objs)){
            if(this.y < 50){
                if(dy < 0)
                    return;
            }else if (this.y > 700){
                    if(dy > 0)
                        return;
            }else{
                y += dy;
            } 
        }
    }
    
    public void moveUp(){
        
    }
}
    
    
