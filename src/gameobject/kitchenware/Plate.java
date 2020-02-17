/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.kitchenware;
import controllers.ImageResourceController;
import controllers.PathBuilder;
import gameobject.*;
import gameobject.food.*;
import values.ImagePath;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
/**
 *
 * @author dingding
 */
public class Plate extends GameObject{
    private BufferedImage img;
    private NonCuttable food;
    private CombineHelper combineHelper;
    protected boolean isheld;
            
    public Plate(int x, int y , int width, int height){
    super(x,y,width,height);
    ImageResourceController irc = ImageResourceController.getInstance();
    img = irc.tryGetImage(PathBuilder.getImg(ImagePath.Kitchenwares.K1));
    }
    
    public void putFood(NonCuttable food){
        if( this.food == null){
            this.food = food;
        }
//        if (this.food != null){
//            if(CombineHelper.foodCombine(this.food, food) == null){
//                return;
//            }
//            this.food = CombineHelper.foodCombine(this.food, food);
//        }
    }
        
    public NonCuttable getFood(){
        return food;
    }
    public void move(){
        this.x = x+8;
        this.y = y +8;
    }
    public void setX(){
        this.x = x;
    }
    public void setY (){
        this.y = y;
    }
    public int getX(){
        return x;
    }
    public int geyY(){
        return y;
    }
         public boolean checkEmptyPlate() {
            Plate plate = (Plate) this;
            if (plate.getFood() == null) {
                return true;
            }
            return false;
        }
     
       private void changeImage(){
           ImageResourceController irc = ImageResourceController.getInstance();
           if(isheld == true){               
               img = irc.tryGetImage(PathBuilder.getImg(ImagePath.Kitchenwares.K2));
           }else{
               img = irc.tryGetImage(PathBuilder.getImg(ImagePath.Kitchenwares.K1));
           }           
    }
       
       public void changeStatus(boolean isheld){
           this.isheld = isheld;
           changeImage();
       }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(img, x, y,width,height, null); //畫盤子   
        if(this.food != null){
            this.food.getFoodHelper().paint(g, x, y, width, height);//畫食物
        } 
        
    }    
  
    
}
