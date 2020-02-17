/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene.menu;

import controllers.*;
import io.CommandSolver;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import popwindow.EnsureWindow;
import scene.Scene;
import scene.game.GameScene;
import utils.Global;
import values.ImagePath;
import static utils.Global.*;

/**
 *
 * @author dingding
 */
public class LevelScene extends Scene {

    private CommandSolver.KeyCommandListener keyCommandListener;
    private BufferedImage actorImg;
    private int choseLevel;
    private BufferedImage arrow;
    // private BufferedImage actorBox;
    private BufferedImage gameLevel;
    private int actorOne;
    private int actorTwo;
    private int pickOne = -1;
    private BufferedImage home;
    private BufferedImage btnReturn;
    private BufferedImage stage1;
    private BufferedImage stage2;
    private BufferedImage stage3;
    private EnsureWindow ensureWindow;

    public LevelScene(SceneController sceneController, int pickOne) {
        super(sceneController);
        this.pickOne = pickOne;
    }

    public LevelScene(SceneController sceneController, int actorOne, int actorTwo) {
        super(sceneController);
        this.actorOne = actorOne;
        this.actorTwo = actorTwo;
    }

    @Override
    public void sceneBegin() {
        ImageResourceController irc = ImageResourceController.getInstance();
        actorImg = irc.tryGetImage(PathBuilder.getImg(ImagePath.Background.Scene.Background_Actor));
        //actorBox = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.ACTORBOX));
        stage1 = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.STAGE1));
        stage2 = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.STAGE2));
        stage3 = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.STAGE3));
        arrow = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.HATRED));
        gameLevel = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.GAMELEVEL));
        home = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.HOME));
        btnReturn = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.RETURN));
        keyCommandListener = new CommandSolver.KeyCommandListener() {
            @Override
            public void keyPressed(int commandCode, long time) {
                if (commandCode == Global.RIGHT) {
                    if (choseLevel != 2) {
                        choseLevel++;
                        //System.out.println(choseLevel);
                    }
                }
                if (commandCode == Global.LEFT) {
                    if (choseLevel != 0) {
                        choseLevel--;
                        // System.out.println("choseLevel");
                    }
                }
                if (commandCode == Global.MENU_ENTER) {
                    switch (choseLevel) {
                        case 0:
                            // 關卡一

                            if (pickOne != -1) {
                                sceneController.changeScene(new GameScene(sceneController, pickOne, 0));

                            } else {
                                sceneController.changeScene(new GameScene(sceneController, actorOne, actorTwo, 0));

                            }

                            break;
                        case 1:
                            //關卡二
                            if (pickOne != -1) {
                                sceneController.changeScene(new GameScene(sceneController, pickOne, 1));

                            } else {
                                sceneController.changeScene(new GameScene(sceneController, actorOne, actorTwo, 1));

                            }
                            break;
                        case 2:
                            //關卡三   
                            if (pickOne != -1) {
                                sceneController.changeScene(new GameScene(sceneController, pickOne, 2));
                            } else {
                                sceneController.changeScene(new GameScene(sceneController, actorOne, actorTwo, 2));
                            }
                            break;
                    }
                }

                if (commandCode == Global.RETURN) {
                    if (pickOne != -1) {
                        sceneController.changeScene(new ActorScene(sceneController));
                    } else {
                        sceneController.changeScene(new ActorSceneTwo(sceneController));
                    }

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
    public void sceneUpdate() {

    }

    @Override
    public void sceneEnd() {
    }

    @Override
    public void paint(Graphics g) {

        g.drawImage(actorImg, 0, 0, Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT, null);
        g.drawImage(stage1, 78, 300, 248, 288, null);
        g.drawImage(stage2, 376, 300, 248, 288, null);
        g.drawImage(stage3, 674, 300, 248, 288, null);
        //g.drawImage(gameLevel,98 , 320, 268,268, null);
        g.drawImage(arrow, 127 + choseLevel * 298, 200, 150, 75, null);
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

}
