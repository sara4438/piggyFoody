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
import values.ImagePath;

/**
 *
 * @author dingding
 */
public class Button extends GameObject {

    private BufferedImage img;
    //private BufferedImage home;
    private int picNumber;
    private int choosePosition;

    public Button(int x, int y, int width, int height, int picNumber) {
        super(x, y, width, height);
        ImageResourceController irc = ImageResourceController.getInstance();
        img = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.BUTTON1));
        //home = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.HOME));
        //img = irc.tryGetImage("/resources/imgs/background/button1.png");
        this.picNumber = picNumber;
        //img = getPic(picNumber);
        //picNumber = 0;
    }

    private BufferedImage getPic(int picNumber) {
        ImageResourceController irc = ImageResourceController.getInstance();
        if (picNumber == 0) {
            return irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.HOME));
        }
        if (picNumber == 1) {
            return irc.tryGetImage(PathBuilder.getImg(ImagePath.TestMenu.ENEMY1));
        }
        if (picNumber == 2) {
            return irc.tryGetImage(PathBuilder.getImg(ImagePath.TestMenu.ENEMY1));
        }
        if (picNumber == 3) {
            return irc.tryGetImage(PathBuilder.getImg(ImagePath.TestMenu.ENEMY1));
        }
        return null;
    }

    public interface ButtonListener {

        public void onClick(int x, int y);

        public void hover(int x, int y);
    }

    private ButtonListener buttonListener;

    public void setButtonListener(ButtonListener buttonListener) {
        this.buttonListener = buttonListener;
    }

    public boolean isCollision(int x, int y) {
        if (x < this.x || x > this.x + this.width) {
            return false;
        }
        if (y < this.y || y > this.y + this.height) {
            return false; 
        }
        return true;
    }

    public void click(int x, int y) {
        if (buttonListener == null) {
            return;
        }
        buttonListener.onClick(x, y);
    }

    public void hover(int x, int y) {
        if (buttonListener == null) {
            return;
        }
        buttonListener.hover(x, y);
    }

    @Override
    public void paint(Graphics g) {
        //g.drawRect(x, y, width, height);
        int countX = 313 * picNumber;
      
        g.drawImage(img, x, y, x + width, y + height,
                countX, 0,
                countX + 313, 107,
                null);
        
//          if (picNumber == 0) {
//            g.drawImage(home, x, y, x + width, y + height, 0,0 ,71 ,71 , null);
//        }
    }
     

}
