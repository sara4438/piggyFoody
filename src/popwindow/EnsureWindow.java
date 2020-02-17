/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popwindow;

import controllers.ImageResourceController;
import controllers.PathBuilder;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import utils.Global;
import values.ImagePath;

/**
 *
 * @author dingding
 */
public class EnsureWindow extends PopWindow {

    //private ArrayList<Tutorial> tutorial;

    private BufferedImage img;

    public EnsureWindow() {
        super(350, 225, 300, 200);
        ImageResourceController irc = ImageResourceController.getInstance();
        //bg = irc.tryGetImage(PathBuilder.getImg(ImagePath.Background.Scene.Background_Menu));
        img = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.POPINFO));
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    @Override
    public void paint(Graphics g) {

        g.setColor(new Color(0, 0, 0, 128));
        g.fillRect(0, 0, Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT);
        //g.drawRect(x, y, width, height);
        g.drawImage(img, x, y, width, height, null);

    }

}
