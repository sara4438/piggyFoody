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
import java.util.ArrayList;
import utils.Global;
import values.ImagePath;

/**
 *
 * @author user1
 */
public class Buttons extends GameObject {

    public interface ButtonListener {

        public void onClick(int x, int y);

        public void hover(int x, int y);
    }

    private ButtonListener buttonListener;
    private ArrayList<BufferedImage> img;
    private int currentImg;
    private int set;

    public Buttons(int x, int y, int width, int height, int number, int set) {
        super(x, y, width, height);
        img = new ArrayList<>();
        setButton(number, img);
        this.set = set;
    }

    public Buttons(int x, int y, int width, int height, int number) {
        super(x, y, width, height);
        img = new ArrayList<>();
        setButton(number, img);
    }

    public Buttons(int x, int y, int number) {
        super(x, y, Global.IMG_X_OFFSET, Global.IMG_Y_OFFSET);
        img = new ArrayList<>();
        setButton(number, img);
        currentImg = 0;
    }

    public void setButton(int number, ArrayList<BufferedImage> img) {
        ImageResourceController irc = ImageResourceController.getInstance();
        if (number == 0) { // main button
            //img.add(irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.STAGE)));
        } else if (number == 1) {
            //img.add(irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.STAGESELECT))); 
            img.add(irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.STAGE)));

            img.add(irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.STAGESELECT)));

        } else if (number == 2) {
            img.add(irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.HOME)));
        }

    }

    public void setButtonListener(ButtonListener buttonListener) {
        this.buttonListener = buttonListener;
    }

    public boolean isCollision(int x, int y) {
        if (x < this.x || x > this.x + this.width) {
            currentImg = 0;
            return false;
        }
        if (y < this.y || y > this.y + this.height) {
            currentImg = 0;
            return false;
        }
        currentImg = 1;
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

//    public void mainPaint(Graphics g) {
//        g.drawImage(img.get(0), (int) x, (int) y, (int) (x + width), (int) (y + height),
//                set * 320, currentImg * 130, (set + 1) * 320, (1 + currentImg) * 130, null);
//    }
//
//    public void paintSheet(Graphics g) {
//        g.drawImage(img.get(0), (int) x, (int) y, (int) (x + width), (int) (y + height),
//                currentImg * 40, 0, (1 + currentImg) * 40, 40, null);
//    }
    public void paintHome(Graphics g) {
        //g.drawlmage(img.get(2),)
    }

    public void paintRank(Graphics g) {
        g.drawImage(img.get(currentImg), (int) x, (int) y, (int) (x + width), (int) (y + height),
                set * 148, 0, (set + 1) * 148, 130, null);
    }

    public void paintChooseRank(Graphics g) {
        g.drawImage(img.get(1), (int) x, (int) y, (int) (x + width), (int) (y + height),
                set * 148, 0, (set + 1) * 148, 130, null);
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img.get(currentImg), (int) x, (int) y, (int) width, (int) height, null);
    }

}
