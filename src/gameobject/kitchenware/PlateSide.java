/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.kitchenware;
import controllers.*;
import values.ImagePath;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
/**
 *
 * @author dingding
 */
public class PlateSide extends Plate{
    BufferedImage img;
            
        public PlateSide ( int x, int y , int width, int height){
        super( x,y,width,height);
        ImageResourceController irc = ImageResourceController.getInstance();
        img = irc.tryGetImage(PathBuilder.getImg(ImagePath.Kitchenwares.K1));
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(img, x, y,width,height, null);     
    }
    
}