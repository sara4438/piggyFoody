/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import controllers.ImageResourceController;
import controllers.PathBuilder;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utils.DelayCounter;
import utils.Global;
import values.ImagePath;

/**
 *
 * @author sara
 */
public class Indicator extends GameObject{
    private BufferedImage img;
    private int position ;
    private int[] CYCLE ;
    private DelayCounter delayCounter;

    public Indicator(int x , int y, int width, int height){
        super(x,y,width, height);
        CYCLE = new int[]{0, 1, 2, 3, 4};
        delayCounter = new DelayCounter(300000000);
        position = 0;
         ImageResourceController irc = ImageResourceController.getInstance();
        img = irc.tryGetImage(PathBuilder.getImg(ImagePath.Indicator.INDICATORBAR));  
    }
    
    public void process(){
            do{
                 if (delayCounter.update()) {
                    position++;
            } 
        }while(position <4);
      }
    
    @Override
    public void paint(Graphics g) {
        int cx = Global.IMG_SQUARE* CYCLE[position];
        int cy = 0;
        g.drawImage(img, x, y, x+width, y+height , cx, cy, cx+width, cy+height, null);
      
    }
    
}
