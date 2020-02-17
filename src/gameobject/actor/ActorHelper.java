/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.actor;

import controllers.*;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utils.Global;
import values.ImagePath;

/**
 *
 * @author dingding
 */
public class ActorHelper {

    //換照片放在這個類別
    //每個方向有三種狀態
    //有四個方向
    private BufferedImage img;
    private int actorPosition;
    private int actor;

    public ActorHelper(int actor) {
        img = getActor(actor);
        actorPosition = actor % 8;
    }
 
    private BufferedImage getActor(int actor) {
        this.actor = actor;
        ImageResourceController irc = ImageResourceController.getInstance();
        if(actor == 0){
            return irc.tryGetImage(PathBuilder.getImg(ImagePath.Character.Actor.PIG));
        }
        if(actor == 1){
            return irc.tryGetImage(PathBuilder.getImg(ImagePath.Character.Actor.RABBIT));
        }
        if(actor == 2){
            return irc.tryGetImage(PathBuilder.getImg(ImagePath.Character.Actor.TURTLE));
        }
        return null;
    }

    public void paint(Graphics g, int x, int y, int width, int height, int act, int direction) {        
        if (img == null) {
            return;
        }
       //121,214
        if (actor == 0) {
            g.drawImage(img, x, y, x + width, y + height,
                    act * 121, (int)(direction * 214.25),
                    121 + act * 121, (int)(214.25 + direction * 214.25), null);
        }//7=372,412
        if (actor == 1) {
            g.drawImage(img, x, y, x + width, y + height,
                    act * 372, direction * 412,
                    372 + act * 372, 412 + direction * 412, null);
        }//121,220
        if (actor == 2) {
            g.drawImage(img, x, y, x + width, y + height,
                    act * 121, direction * 220,
                    121 + act * 121, 220 + direction * 220, null);
        }
    }

}
