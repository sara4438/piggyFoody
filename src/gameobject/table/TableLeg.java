/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.table;

import Main.GameState;
import controllers.ImageResourceController;
import controllers.PathBuilder;
import gameobject.GameObject;
import gameobject.OrderContainer;
import gameobject.actor.Actor;
import gameobject.food.Food;
import gameobject.food.NonCuttable;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import values.ImagePath;

/**
 *
 * @author dingding
 */
public class TableLeg extends GameObject {
    private BufferedImage img; 
    private int imgNum;
    public TableLeg(int x, int y, int width, int height, int imgNum){ //食材是照片第幾張圖
        super(x,y,width, height);
        this.imgNum = imgNum;
        ImageResourceController irc = ImageResourceController.getInstance();
        img = irc.tryGetImage(PathBuilder.getImg(ImagePath.Table.Tables.Table_Six));
        
    }


    @Override
    public void paint(Graphics g) {     
        if (img == null) {
            return;
        }
        int cx = 50 * (imgNum % 12);
        int cy = 50 * (imgNum / 12);
        g.drawImage(img, x, y, x + width, y + height, cx, cy, cx + 50, cy + 50, null);
    }

}
