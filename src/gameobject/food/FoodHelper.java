/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.food;
import controllers.ImageResourceController;
import controllers.PathBuilder;
import utils.Global;
import values.ImagePath;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
/**
 *
 * @author dingding
 */
public class FoodHelper {
    private BufferedImage img;
    private int foodPosition;

    public FoodHelper(int food) {
        this.img = getFood(food);
        foodPosition = food ;
    }
    public BufferedImage getImg(){ 
        return img;
    }
    public void setFoodPosition(int foodPosition){
        this.foodPosition = foodPosition;
    }

    private BufferedImage getFood(int food) {
        ImageResourceController irc = ImageResourceController.getInstance();
        if (food >= 0 && food < 48) {
//            System.out.print(irc.tryGetImage(PathBuilder.getImg(ImagePath.Food.S1)));
            return irc.tryGetImage(PathBuilder.getImg(ImagePath.Food.S1));            
        } 
        return null;
    }    
    
    public void paint(Graphics g, int x, int y, int width, int height){
        if(img == null){
            return;
        }
        
        int cx = Global.IMG_SQUARE* (foodPosition % 12);
        int cy = Global.IMG_SQUARE * (foodPosition / 12);
//        g.drawRect(x, y, width, height);
        g.drawImage(img, x, y, x + width ,y+ height, cx , cy, cx + Global.IMG_SQUARE , cy+ Global.IMG_SQUARE ,null);  
//        g.drawImage(img, x, y, width, height, null);
    }
}