/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import controllers.ImageResourceController;
import controllers.PathBuilder;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import values.ImagePath;

/**
 *
 * @author dingding
 */
public class ScoreTimer extends GameObject {

    BufferedImage img;

    public ScoreTimer(int x, int y, int width, int height) {
        super(x, y, width, height); 
        ImageResourceController irc = ImageResourceController.getInstance();
        img = irc.tryGetImage(PathBuilder.getImg(ImagePath.GameScene.Gamescene.TIME));
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img, x, y, x+width,y+height,0,0,467,137, null);
    }

}
