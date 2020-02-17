/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import Main.GameState;
import controllers.ImageResourceController;
import controllers.PathBuilder;
import gameobject.actor.Actor;
import gameobject.food.Food;
import gameobject.food.NonCuttable;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import utils.DelayCounter;
import utils.Global;
import values.ImagePath;

/**
 *
 * @author sara
 */
public class OrderContainer extends GameObject{
    private ArrayList <Order> orders;
    private int level;
    
    public OrderContainer(int x , int y, int width, int height, int level ){
        super(x,y,width,height);
        this.level=level;
        orders = new ArrayList<>();
        if(level !=-1){
            for(int i = 0; i < Global.INITIAL_NUM_OF_ORDERS; i++){
            orders.add(genOrders(i));
        }
        }else{ //遊戲示範
            orders.add(genOrders(0));
        }
    }       
    
    public Order genOrders( int i) {
       Order order = new Order( this.x + i * (Global.ORDER_WIDTH+ Global.IMG_SQUARE * 1/2 ), 
               this.y, Global.ORDER_WIDTH, Global.ORDER_HEIGHT, level);
       return order;
   }
    
       public Order fulfillOrder( ArrayList <Food> ingredients , Actor actor) {
           int playerNum = actor.getPlayerNum();
            for(int i = 0; i< orders.size(); i++) {             
                Order order = orders.get(i);         
                if (order.isFulfilledBy(ingredients)) {//檢查有沒有被完成 order
                    orders.set(i ,genOrders(i)); //直接替換掉完成訂單          
                    if(actor.getPlayerNum() == 1){
                        GameState.getGameState().addScore1(order.getScore());
                    }     
                    if(actor.getPlayerNum() == 2){
                        GameState.getGameState().addScore2(order.getScore());
                    }
                    return order;
                 }
            }
            return null;
        }
       public class RedSquare extends GameObject {
           private BufferedImage img ; 
           private DelayCounter delayCounter;
           private int count = 0;
           private int []paintOrder ;
           private int order;
           public RedSquare(int x, int y, int width, int height){
               super (x,y,width, height);
               paintOrder = new int [] {0,1};
               delayCounter = new DelayCounter (10000000);
                 ImageResourceController irc = ImageResourceController.getInstance();
                 img = irc.tryGetImage(PathBuilder.getImg(ImagePath.Indicator.REDSQUARE));  
           }
           public void move(){
               System.out.print("紅框閃");
               do{
                   if (delayCounter.update()) {
                       System.out.println("!");
                       order++ ;
                       if(order >=1){
                           order = 0;
                           count ++;
                       }
                } 
              }while(count<=6);   
               count = 0;
           }
           
            @Override
            public void paint(Graphics g) {
                int cx = paintOrder [order] *width;
                int cy = 0;
                g.drawImage(img, x, y, x+width, y+height, cx, cy, cx+width, cy+height, null);
           }
       }
           
    
    @Override
    public void paint(Graphics g) {
        for ( int i = 0; i < orders.size(); i++){
              orders.get(i).paint(g);
        }
    }
}
    
    
    