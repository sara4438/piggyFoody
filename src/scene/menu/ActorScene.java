/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene.menu;

import controllers.*;
import gameobject.ActorBox;
import gameobject.Buttons;
import gameobject.actor.*;
import io.CommandSolver;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import popwindow.EnsureWindow;
import scene.Scene;
import utils.Global;
import utils.Global.*;
import static utils.Global.*;
import values.ImagePath;

/**
 *
 * @author dingding
 */
public class ActorScene extends Scene {

    private Actor actor;
    private Actor actor1;
    private Actor actor2;
    private BufferedImage actorImg;
    private int choseActor;
    private BufferedImage arrow;
    private BufferedImage actorBox;
    private ArrayList<ActorBox> actorbox;
    private BufferedImage home;
    private BufferedImage btnReturn;
    private BufferedImage actorInfo;
    private BufferedImage exit;
    private CommandSolver.MouseCommandListener mouseCommandListener;
    private CommandSolver.KeyCommandListener keyCommandListener;
    private EnsureWindow ensureWindow;

    public ActorScene(SceneController sceneController) {
        super(sceneController);
        ImageResourceController irc = ImageResourceController.getInstance();
        actorImg = irc.tryGetImage(PathBuilder.getImg(ImagePath.Background.Scene.Background_Actor1));
        actorBox = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.ACTORBOX1));
        arrow = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.HATRED));
        home = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.HOME));
        btnReturn = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.RETURN));
        actorInfo = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.ACTORINFO));
        exit = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.EXIT));

        keyCommandListener = new CommandSolver.KeyCommandListener() {
            @Override
            public void keyPressed(int commandCode, long time) {
                if (commandCode == Global.RIGHT) {
                    if (choseActor != 2) {
                        choseActor++;
                        System.out.println(choseActor);
                    }
                }
                if (commandCode == Global.LEFT) {
                    if (choseActor != 0) {
                        choseActor--;
                        System.out.println("choseActor");
                    }
                }
                if (commandCode == Global.MENU_ENTER) {
                    switch (choseActor) {
                        case 0:
                            // 人物一 
                            sceneController.changeScene(new LevelScene(sceneController, 0));
                            break;
                        case 1:
                            //人物二
                            sceneController.changeScene(new LevelScene(sceneController, 1));
                            break;
                        case 2:
                            //人物三   
                            sceneController.changeScene(new LevelScene(sceneController, 2));
                            break;
                    }
                }

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
            }

            @Override

            public void keyReleased(int CommandCode, long time) {
            }
        };

    }

    @Override
    public void sceneBegin() {
//        actorbox = new ArrayList<ActorBox>();
//        actorbox.add(new ActorBox(78, 300, 248, 288));
//        actorbox.add(new ActorBox(376, 300, 248, 288));
//        actorbox.add(new ActorBox(674, 300, 248, 288));
//        mouseCommandListener = new CommandSolver.MouseCommandListener() {
//            @Override
//            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
//                for (int i = 0; i < 3; i++) {
//                    if (state == CommandSolver.MouseState.CLICKED) {
//                        //System.out.println("123456");
//                        if (levelButton[i].isCollision(e.getX(), e.getY())) {
//                            currentSelect = i;
//                            //System.out.println("123456");   
//                            levelButton[i].click(e.getX(), e.getY());
//                        }
//                    } else if (state == CommandSolver.MouseState.MOVED) {
//                        if (levelButton[i].isCollision(e.getX(), e.getY())) {
//                            levelButton[i].hover(e.getX(), e.getY());
//                        }
//                    }
//                }
//
//            }
//        };
//        levelButton[i].setButtonListener(new Buttons.ButtonListener() {
//            @Override
//            public void onClick(int x, int y) {
//                //sceneController.changeScene(new MenuScene(sceneController));
//            }
//
//            @Override
//            public void hover(int x, int y) {
//            }
//        });

//        actor = new Actor(110, 300, 150, 200, 3, 0);//width: 140 height:200
//        actor1 = new Actor(425, 300, 150, 200, 3, 1);
//        actor2 = new Actor(735, 300, 150, 200, 3, 2);
        actor = new Actor(120, 300, 155, 180, 0);//width: 140 height:200
        actor1 = new Actor(420, 300, 155, 180, 1);
        actor2 = new Actor(720, 300, 155, 180, 2);
    }

    @Override
    public void sceneUpdate() {
        switch (choseActor) {
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

    @Override
    public void sceneEnd() {
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(actorImg, 0, 0, Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT, null);

//        g.drawImage(actorBox, 78, 300, 326, 588, 0, 0, 310, 360, null);
//        g.drawImage(actorBox, 376, 300, 624, 588, 310, 0, 620, 360, null);
//        g.drawImage(actorBox, 674, 300, 922, 588, 620, 0, 930, 360, null);
        g.drawImage(actorBox, 78, 270, 326, 558, 0, 0, 310, 360, null);
        g.drawImage(actorBox, 376, 270, 624, 558, 310, 0, 620, 360, null);
        g.drawImage(actorBox, 674, 270, 922, 558, 620, 0, 930, 360, null);

        actor.paint(g);
        actor1.paint(g);
        actor2.paint(g);
        g.drawImage(arrow, 127 + choseActor * 298, 180, 150, 75, null);
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
