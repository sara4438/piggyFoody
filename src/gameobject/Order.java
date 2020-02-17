/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import static Main.Main.*;
import controllers.*;
import gameobject.food.Food;
import java.awt.Color;
import java.awt.Graphics; 
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import utils.Global;
import static utils.Global.INGREDIENT_Y;
import values.ImagePath;

/**
 *
 * @author sara
 */
public class Order extends GameObject{
    private ArrayList <Food> requirements;
    private BufferedImage img;
    private int order;
    private int score;     

    
    public Order (int x, int y, int width, int height, int level){ 
       super(x,y,width, height);
       requirements = new ArrayList<>();
       switch(level){
           case 0:
                score = (int)(Math.random()* 6+ 18); //18-23分 
                order  = genOrder(level);  
                requirements.add(new Food(this.x+ 28, this.y + INGREDIENT_Y, Global.ORDER_INGREDIENTSIZE, Global.ORDER_INGREDIENTSIZE,0));
                requirements.add(new Food(this.x+ 28+Global.ORDER_INGREDIENTSIZE+1, this.y + INGREDIENT_Y, Global.ORDER_INGREDIENTSIZE, Global.ORDER_INGREDIENTSIZE,1));
                requirements.add(new Food(this.x+ 28+Global.ORDER_INGREDIENTSIZE*2+2, this.y + INGREDIENT_Y, Global.ORDER_INGREDIENTSIZE, Global.ORDER_INGREDIENTSIZE, order+2));                
               break;
           case 1: 
               score = (int)(Math.random()* 6+ 20); //20-25分 
               order  = genOrder (level);  
               int n = (int) (Math.random() * 2+1); //1-2隨機 海苔or酪梨
               int m = (int) (Math.random()* 2 +1); //1-2隨機 南瓜or番茄
               switch (order){ //選拉麵或壽司
                   case 3:
                       requirements.add(new Food (this.x + 28, this.y + Global.INGREDIENT_Y, Global.ORDER_INGREDIENTSIZE, Global.ORDER_INGREDIENTSIZE,13 ));                       
                       requirements.add(new Food (this.x+ 28+Global.ORDER_INGREDIENTSIZE+1, this.y + Global.INGREDIENT_Y, Global.ORDER_INGREDIENTSIZE, Global.ORDER_INGREDIENTSIZE,  13 +n ));
                       requirements.add(new Food (this.x+ 28+Global.ORDER_INGREDIENTSIZE*2+2, this.y + Global.INGREDIENT_Y, Global.ORDER_INGREDIENTSIZE, Global.ORDER_INGREDIENTSIZE, 15 +2*m, 3 ));
                       break;
                   case 4:
                       requirements.add(new Food (this.x + 28, this.y + Global.INGREDIENT_Y, Global.ORDER_INGREDIENTSIZE, Global.ORDER_INGREDIENTSIZE, 12));
                       requirements.add(new Food (this.x+ 28+Global.ORDER_INGREDIENTSIZE+1, this.y + Global.INGREDIENT_Y, Global.ORDER_INGREDIENTSIZE, Global.ORDER_INGREDIENTSIZE,  13 +n ));
                       requirements.add(new Food (this.x+ 28+Global.ORDER_INGREDIENTSIZE*2+2, this.y + Global.INGREDIENT_Y, Global.ORDER_INGREDIENTSIZE, Global.ORDER_INGREDIENTSIZE, 15 +2*m,3 ));
                       break;
                }
                       break;
           case 2: 
               score = (int)(Math.random()*6 +23); //23-28分
               order = genOrder (level);
               int o = (int) (Math.random() *5 + 0); //選配料1
               int p = (int) (Math.random() *5 + 0); //選配料2
               switch (order){ // order選皮
                   case 6:
                       requirements.add(new Food (this.x + 29, this.y + Global.INGREDIENT_Y, Global.ORDER_INGREDIENTSIZE, Global.ORDER_INGREDIENTSIZE,24 ));                       
                       requirements.add(new Food (this.x + 29+Global.ORDER_INGREDIENTSIZE+1, this.y + Global.INGREDIENT_Y, Global.ORDER_INGREDIENTSIZE, Global.ORDER_INGREDIENTSIZE, 27 +  2*o ,3));
                       requirements.add(new Food (this.x + 29+Global.ORDER_INGREDIENTSIZE*2+2, this.y + Global.INGREDIENT_Y, Global.ORDER_INGREDIENTSIZE, Global.ORDER_INGREDIENTSIZE, 27 +  2*p ,3));
                       break;
                   case 7:
                        requirements.add(new Food (this.x + 29, this.y + Global.INGREDIENT_Y, Global.ORDER_INGREDIENTSIZE, Global.ORDER_INGREDIENTSIZE,25 ));                       
                        requirements.add(new Food (this.x + 29+Global.ORDER_INGREDIENTSIZE+1, this.y + Global.INGREDIENT_Y, Global.ORDER_INGREDIENTSIZE, Global.ORDER_INGREDIENTSIZE, 27 + 2*o ,3));
                        requirements.add(new Food (this.x + 29+Global.ORDER_INGREDIENTSIZE*2+2, this.y + Global.INGREDIENT_Y, Global.ORDER_INGREDIENTSIZE, Global.ORDER_INGREDIENTSIZE, 27 + 2*p ,3));
                       break;
                   case 8: 
                        requirements.add(new Food (this.x + 29, this.y + Global.INGREDIENT_Y, Global.ORDER_INGREDIENTSIZE, Global.ORDER_INGREDIENTSIZE, 35, 3 ));                       
                        requirements.add(new Food (this.x + 29+Global.ORDER_INGREDIENTSIZE+1, this.y + Global.INGREDIENT_Y, Global.ORDER_INGREDIENTSIZE, Global.ORDER_INGREDIENTSIZE, 35,3 ));
                        if(o<2){
                            requirements.add(new Food (this.x + 29+Global.ORDER_INGREDIENTSIZE*2+2, this.y + Global.INGREDIENT_Y, Global.ORDER_INGREDIENTSIZE, Global.ORDER_INGREDIENTSIZE, 35 ,3));
                        }
                        if(o >4){
                            requirements.add(new Food (this.x + 28+Global.ORDER_INGREDIENTSIZE*2+2, this.y + Global.INGREDIENT_Y, Global.ORDER_INGREDIENTSIZE, Global.ORDER_INGREDIENTSIZE, 27 ));
                        }
                       break;
               }
               
               break;
           case -1:
                 order  = 3;  
                requirements = new ArrayList<>();
                requirements.add(new Food(this.x+ 28, this.y + INGREDIENT_Y, Global.ORDER_INGREDIENTSIZE, Global.ORDER_INGREDIENTSIZE,13));
                requirements.add(new Food(this.x+ 28+Global.ORDER_INGREDIENTSIZE+1, this.y + INGREDIENT_Y, Global.ORDER_INGREDIENTSIZE, Global.ORDER_INGREDIENTSIZE,14));
                requirements.add(new Food(this.x+ 28+Global.ORDER_INGREDIENTSIZE*2+2, this.y + INGREDIENT_Y, Global.ORDER_INGREDIENTSIZE, Global.ORDER_INGREDIENTSIZE, 17,3));                                
                break;
       }
            ImageResourceController irc = ImageResourceController.getInstance();
            img = irc.tryGetImage(PathBuilder.getImg(ImagePath.Food.ORDERS));  
               
       }
    
    public int genOrder(int level){
        int n = 0;
        switch (level){
            case 0 :
                n =  (int) (Math.random()*3 + 0);
                return n;        
            case 1: 
                n = (int) (Math.random()*2 + 3);
                return n;
            case 2: 
                n = (int) (Math.random()*5 +1); //讓漢堡,pizza機率大於薯條
                if( n <3 )  //1,2是pizza
                    return 6;                
                if( n > 3 && n <6 )  //3,4是漢堡
                    return 7;
                else //5是薯條                    
                    return 8;                
         }
        return -1;                
    }
    public int getScore(){
        return score;
    }
    public ArrayList<Food> getRequirements (){
        return requirements;
    }
    
    public boolean isFulfilledBy( ArrayList<Food> ingredients){
        boolean hasAnUnfulfilledRequirement = false; 
        ArrayList<Food> ingredientsCopy = new ArrayList<>(); 
        for( Food ingredient : ingredients){
            ingredientsCopy.add(new Food (ingredient.getX(),ingredient.getY(), ingredient.getWidth(), ingredient.getHeight(),ingredient.getImageNum()));
        }        
        for ( Food requirement: requirements)  {
            hasAnUnfulfilledRequirement =   !fulfills(ingredientsCopy, requirement); 
            if (hasAnUnfulfilledRequirement) {
                return false;
            }
        }                                
        return true;
     }
    
    private boolean fulfills(ArrayList<Food> ingredientsCopy, Food requirement){
        for (int i = 0; i< ingredientsCopy.size(); i++) { 
            if (ingredientsCopy.get(i).getImageNum() == requirement.getImageNum()) {
                ingredientsCopy.remove(i); 
                return true;
            }
        }
        return false;
    }
     private void drawMenu(Graphics g) {
        g.setFont(f2);
        g.setColor(new Color(254, 128, 136));
        g.drawString("" + score, x+100, y+100);        
    }
    
    public String toString(){
        return "菜單" +order;
    }

    
    @Override
    public void paint(Graphics g) {
        if(img == null){
            return;
        }
        int cx = Global.ORDER_ORIGINALPHOTO_WIDTH * (order %3);
        int cy = Global.ORDER_ORIGINALPHOTO_HEIGHT * (order / 3);
        g.drawImage(img, x , y, x + width ,y+ height, cx , cy, cx +  Global.ORDER_ORIGINALPHOTO_WIDTH, cy+ Global.ORDER_ORIGINALPHOTO_HEIGHT ,null);  
        drawMenu(g); 
        for(int i = 0; i< requirements.size(); i++){
            requirements.get(i).paint(g);
        }        
        
    }
    
    
}