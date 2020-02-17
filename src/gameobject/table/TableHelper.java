/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.table;

import controllers.ImageResourceController;
import controllers.PathBuilder;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utils.Global;
import values.ImagePath;

/**
 *
 * @author sara
 */
public class TableHelper {

    private BufferedImage img;
    private int tablePosition;

    public TableHelper(int foodType) {
        this.img = getTable(foodType);
        tablePosition = foodType;
    }

    public BufferedImage getImg() {
        return img;
    }

    private BufferedImage getTable(int foodType) { 
        if (foodType >= 0 && foodType < 50) {
            ImageResourceController irc = ImageResourceController.getInstance();
            return irc.tryGetImage(PathBuilder.getImg(ImagePath.Table.Tables.Table_Six));
        }
        return null;

    }

    public void paint(Graphics g, int x, int y, int width, int height) {
        if (img == null) {
            return;
        }
        int cx = Global.IMG_SQUARE * (tablePosition % 12);
        int cy = Global.IMG_SQUARE * (tablePosition / 12);
        g.drawImage(img, x, y, x + width, y + height, cx, cy, cx + Global.IMG_SQUARE, cy + Global.IMG_SQUARE, null);
    }
}