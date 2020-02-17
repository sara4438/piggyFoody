/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene.menu;

import controllers.*;
import io.CommandSolver;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import popwindow.EnsureWindow;
import scene.Scene;
import utils.Global;
import static utils.Global.*;
import values.ImagePath;

/**
 *
 * @author dingding
 */
public class ModeScene extends Scene {

    private CommandSolver.KeyCommandListener keyCommandListener;
    private CommandSolver.MouseCommandListener mouseCommandListener;
    private BufferedImage actorImg;
    private BufferedImage cheifHat;
    private BufferedImage playerBtn;
    private BufferedImage home;
    private BufferedImage btnReturn;
    private BufferedImage exit;
    private int playlerMode;
    private EnsureWindow ensureWindow;

    public ModeScene(SceneController sceneController) {
        super(sceneController);
        ImageResourceController irc = ImageResourceController.getInstance();
        actorImg = irc.tryGetImage(PathBuilder.getImg(ImagePath.Background.Scene.Background_Actor));
        cheifHat = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.HATRED));
        playerBtn = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.PLAYERBTN));
        home = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.HOME));
        btnReturn = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.RETURN));
        exit = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.EXIT));
        keyCommandListener = new CommandSolver.KeyCommandListener() {
            @Override
            public void keyPressed(int commandCode, long time) {
                if (commandCode == Global.RIGHT) {
                    if (playlerMode != 1) {
                        playlerMode++;
                        // System.out.println(playlerMode);
                    }
                }
                if (commandCode == Global.LEFT) {
                    if (playlerMode != 0) {
                        playlerMode--;
                        // System.out.println("playlerMode");
                    }
                }
                if (commandCode == Global.MENU_ENTER) {
                    switch (playlerMode) {
                        case 0:
                            //單人遊戲
                            sceneController.changeScene(new ActorScene(sceneController));
                            break;
                        case 1:
                            //雙人遊戲
                            sceneController.changeScene(new ActorSceneTwo(sceneController));
                            break;
                    }
                }
                if (commandCode == Global.RETURN) {
                    sceneController.changeScene(new MenuScene(sceneController));

                }
                if (commandCode == Global.CONTORL) {
                    
                    ensureWindow = new EnsureWindow();
                    
                    ensureWindow.setKeyCommandListener(new CommandSolver.KeyCommandListener() {

                        @Override
                        public void keyPressed(int commandCode, long trigTime) {
                            switch (commandCode) {
                                case Global.Y:
                                    sceneController.changeScene(new MenuScene(sceneController));

                                    break;
                                case Global.N:
                                    ensureWindow = null;
                                    break;
                            }
                        }

                        @Override
                        public void keyReleased(int commandCode, long trigTime) {
                        }
                    });
                }
            }

            @Override

            public void keyReleased(int CommandCode, long time) {
            }
        };

    }

    @Override
    public void sceneBegin() {
        //home = new Button(800, 640, 71, 71,0);
    }

    @Override
    public void sceneUpdate() {
    }

    @Override
    public void sceneEnd() {
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(actorImg, 0, 0, Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT, null);

        g.drawImage(playerBtn, 141, 350, 450, 457, 0, 0, 309, 107, null);
        g.drawImage(playerBtn, 550, 350, 859, 457, 0, 119, 309, 226, null);
        g.drawImage(cheifHat, 220 + 409 * playlerMode, 255, 370 + 409 * playlerMode, 340, 0, 0, 150, 75, null);

        g.drawImage(home, HOME_X, HOME_Y, GUI_BUTTON_WIDTH, GUI_BUTTON_HEIGHT, null);
        g.drawImage(btnReturn, RETURN_X, RETURN_Y, GUI_BUTTON_WIDTH, GUI_BUTTON_HEIGHT, null);
        if (ensureWindow != null) {
            ensureWindow.paint(g);
        }

    }

  public CommandSolver.KeyCommandListener getKeyCommandListener() {
        if (ensureWindow == null) {
            return keyCommandListener;
        } else if (ensureWindow != null) {
            return ensureWindow.getKeyCommandListener();
        }
        return null;
    }

    public CommandSolver.MouseCommandListener getMouseCommandListener() {
        return mouseCommandListener;
    }

}
