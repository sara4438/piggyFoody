/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package popwindow;

import static Main.Main.*;
import io.CommandSolver;
import java.awt.Color;
import java.awt.Graphics;
import scene.menu.MenuScene;
import utils.Global;

/**
 *
 * @author dingding
 */
public class ResetWindow extends PopWindow {

    public boolean reset; // Press Enter to try again

    public ResetWindow() {
        super(330, 540, 250, 600);
        setKeyCommandListener(new CommandSolver.KeyCommandListener() {
            @Override
            public void keyPressed(int commandCode, long trigTime) {
                switch (commandCode) {
                    case Global.MENU_ENTER:
                        reset = true;
                        break;
                }
            }

            @Override
            public void keyReleased(int commandCode, long trigTime) {
            }
        });
    }

    @Override
    public boolean isEnd() {
        return false;
    }

    public boolean reset() {
        return reset;
    }

    @Override
    public void paint(Graphics g) {
//        g.setColor(new Color(0, 0, 0, 128));
//        g.fillRect(0, 0, Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT);
        g.setFont(f1);
       g.setColor(new Color(254, 128, 136));
        g.drawString("Press Enter to try again.", x, y);
        //g.drawRect(x, y, width, height);

    }

}
