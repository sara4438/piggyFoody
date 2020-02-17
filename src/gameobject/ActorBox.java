/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import controllers.*;
import java.awt.Graphics;
import java.awt.image.*;
import values.ImagePath;

/**
 *
 * @author dingding
 */
public class ActorBox extends GameObject {

    BufferedImage img;
    //private int position;
    private int select;
    private int set;

    public ActorBox(int x, int y, int width, int height) {
        super(x, y, width, height);
        ImageResourceController irc = ImageResourceController.getInstance();
        img = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.ACTORBOX1));
        //this.position = position * 100;
        select = 0;
         this.set = set;
    }

    public void setSeclect() {
        select = 1;
    }

    public void cleanSelect() {
        select = 0;
    }

    @Override
    public void paint(Graphics g) {
        for(int set = 0; set<3;set++){
         g.drawImage(img, (int) x, (int) y, (int) (x + width), (int) (y + height),
                set * 310, 0, (310) +(310*set), 360, null);
        }
//        for (int i = 0; i < 3; i++) {
//            g.drawImage(img, x, y, x + width, y + height, 310 * i, 0, 310 + 310 * i, 360, null);
//        }
    }

}
