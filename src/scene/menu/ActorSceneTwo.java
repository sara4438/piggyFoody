/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene.menu;

import controllers.*;
import gameobject.*;
import gameobject.actor.*;
import io.CommandSolver;
import java.awt.Graphics;
import java.awt.image.*;
import java.util.ArrayList;
import popwindow.EnsureWindow;
import scene.Scene;
import utils.Global;
import values.ImagePath;
import static utils.Global.*;

/**
 *
 * @author dingding
 */
public class ActorSceneTwo extends Scene {

    private CommandSolver.KeyCommandListener keyCommandListener;
    private Actor actor;
    private Actor actor1;
    private Actor actor2;
    private BufferedImage actorImg;
    private BufferedImage arrow;
    private BufferedImage arrow1;
    private BufferedImage actorBox;
    private int choseActor1;
    private int choseActor2;
    private boolean actor1Chosen = false;
    //private ArrayList<ActorBox> actorbox;
    private BufferedImage home;
    private BufferedImage btnReturn;
    private EnsureWindow ensureWindow;

    public ActorSceneTwo(SceneController sceneController) {
        super(sceneController);
        ImageResourceController irc = ImageResourceController.getInstance();
        actorImg = irc.tryGetImage(PathBuilder.getImg(ImagePath.Background.Scene.Background_Actor1));
        actorBox = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.ACTORBOX1));
        arrow = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.CHOSEACTORONE));
        home = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.HOME));
        btnReturn = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.RETURN));

        keyCommandListener = new CommandSolver.KeyCommandListener() {
            @Override
            public void keyPressed(int commandCode, long time) {
                if (commandCode == Global.RETURN) {
                    sceneController.changeScene(new ModeScene(sceneController));
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
                if (actor1Chosen) {
                    if (commandCode == Global.RIGHT) {
                        if (choseActor2 != 2) {
                            choseActor2++;
                        }
                    }
                    if (commandCode == Global.LEFT) {
                        if (choseActor2 != 0) {
                            choseActor2--;
                        }
                    }
                    if (commandCode == Global.MENU_ENTER) {
                        actor1Chosen = true;
                        switch (choseActor2) {

                            case 0:
                                sceneController.changeScene(new LevelScene(sceneController, choseActor1, choseActor2));
                                break;
                            case 1:
                                sceneController.changeScene(new LevelScene(sceneController, choseActor1, choseActor2));
                                choseActor2 = 1;
                                break;
                            case 2:
                                sceneController.changeScene(new LevelScene(sceneController, choseActor1, choseActor2));
                                choseActor2 = 2;
                                break;
                        }
                    }

                }

                if (!actor1Chosen) {
                    if (commandCode == Global.RIGHT) {
                        if (choseActor1 != 2) {
                            choseActor1++;
//                        System.out.println(choseActor);
                        }
                    }
                    if (commandCode == Global.LEFT) {
                        if (choseActor1 != 0) {
                            choseActor1--;
//                        System.out.println("choseActor");
                        }
                    }
                    if (commandCode == Global.MENU_ENTER) {
                        actor1Chosen = true;
                        switch (choseActor1) {

                            case 0:
                                choseActor1 = 0;
                                break;
                            case 1:
                                choseActor1 = 1;
                                break;
                            case 2:
                                choseActor1 = 2;
                                break;
                        }
                        arrow1 = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.CHOSEACTORTWO));
                    }

                }
            }

            @Override

            public void keyReleased(int CommandCode, long time
            ) {
            }
        };
    }

    @Override

    public void sceneBegin() {
        actor = new Actor(120, 300, 155, 180, 0);//width: 140 height:200
        actor1 = new Actor(420, 300, 155, 180, 1);
        actor2 = new Actor(720, 300, 155, 180, 2);
//        actorbox = new ArrayList<ActorBox>();
//        actorbox.add(new ActorBox(78, 300, 248, 288));
//        actorbox.add(new ActorBox(376, 300, 248, 288));
//        actorbox.add(new ActorBox(674, 300, 248, 288));

//
//        actor = new Actor(80, 500, 155, 180, 3, 3);//width: 140 height:200
//        actor1 = new Actor(395, 500, 155, 180, 3, 4);
//        actor2 = new Actor(710, 500, 155, 180, 3, 5);
    }

    @Override
    public void sceneUpdate() {
        if (!actor1Chosen) {
            switch (choseActor1) {
                case 0:
                    actor.move();
                    break;
                case 1:
                    actor1.move();
                    break;
                case 2:
                    actor2.move();
                    break;
            }
        } else {
            switch (choseActor2) {
                case 0:
                    actor.move();
                    break;
                case 1:
                    actor1.move();
                    break;
                case 2:
                    actor2.move();
                    break;
            }
        }
    }

    @Override
    public void sceneEnd() {
    }

    @Override
    public void paint(Graphics g) {

        g.drawImage(actorImg, 0, 0, Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT, null);



        g.drawImage(arrow, 127 + choseActor1 * 298, 180, 150, 75, null);
        g.drawImage(arrow1, 127 + choseActor2 * 298, 180, 150, 75, null);

        g.drawImage(actorBox, 78, 270, 326, 558, 0, 0, 310, 360, null);
        g.drawImage(actorBox, 376, 270, 624, 558, 310, 0, 620, 360, null);
        g.drawImage(actorBox, 674, 270, 922, 558, 620, 0, 930, 360, null);
        actor.paint(g);
        actor1.paint(g);
        actor2.paint(g);
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
