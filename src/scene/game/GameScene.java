/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene.game;

import controllers.*;
import gameobject.actor.*;
import io.CommandSolver;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import io.CommandSolver.TypedListener;
import scene.Scene;
import utils.Global;
import values.ImagePath;
import Main.GameState;
import static Main.Main.f;
import static Main.Main.f3;
import audio.AudioA;
import io.CommandSolver.*;
import java.awt.Font;
import popwindow.EnsureWindow;
import popwindow.ResetWindow;
import scene.menu.LevelScene;
import scene.ranking.PopUpWindow;
import scene.menu.*;
import scene.ranking.*;
import static utils.Global.*;
import utils.Timmer;

/**
 *
 * @author dingding
 */
public class GameScene extends Scene {

    private BufferedImage img;
    private BufferedImage time;
    private BufferedImage point;
    private BufferedImage lose;
    private BufferedImage win;
    private BufferedImage winP1;
    private BufferedImage winP2;
    private BufferedImage winTie;
    private BufferedImage home;
    private BufferedImage btnReturn;
    private BufferedImage nametag;
    private int pickActor = -1;
    private int actorOne;
    private int actorTwo;
    private int picklevel;
    private int count;
    private boolean paintLose;
    private boolean paintWin;
    private boolean paintWinP1;
    private boolean paintWinP2;
    private boolean paintTie;
    private WorkControl workControl;
    private Timmer countdown;
    private AudioA.PressedMusic audio;
    private PopUpWindow popWindow;
    private TypedListener typedListener;
    private boolean popWindowMode;
    private EnsureWindow ensureWindow;//跳出確認視窗
    private int playerNum;
    private ResetWindow resetWindow;

    public GameScene(SceneController sceneController, int pickActor, int picklevel) {
        super(sceneController);
        GameState.getGameState().reset1();
        this.pickActor = pickActor;
        this.picklevel = picklevel;
        workControl = new WorkControl(picklevel, pickActor); // 一名玩家
        countdown = new Timmer(870, 145, 100, 100, 180);
        if (picklevel == 0) {
            countdown = new Timmer(870, 145, 100, 100, 120);
        }
        playerNum = 1;

    }

    public GameScene(SceneController sceneController, int actorOne, int actorTwo, int picklevel) {
        super(sceneController);
        GameState.getGameState().reset1();
        GameState.getGameState().reset2();
        this.actorOne = actorOne;
        this.actorTwo = actorTwo;
        this.picklevel = picklevel;
        workControl = new WorkControl(picklevel, actorOne, actorTwo); //兩名玩家
        countdown = new Timmer(870, 145, 100, 100, 180);
        if (picklevel == 0) {
            countdown = new Timmer(870, 145, 100, 100, 120);
        }
        playerNum = 2;

    }

    @Override
    public void sceneBegin() {
        ImageResourceController irc = ImageResourceController.getInstance();
        if (picklevel == 0) {
            img = irc.tryGetImage(PathBuilder.getImg(ImagePath.Background.Scene.Background_level1));
        }
        if (picklevel == 1) {
            img = irc.tryGetImage(PathBuilder.getImg(ImagePath.Background.Scene.Background_level2));
        }
        if (picklevel == 2) {
            img = irc.tryGetImage(PathBuilder.getImg(ImagePath.Background.Scene.Background_level3));
        }
        time = irc.tryGetImage(PathBuilder.getImg(ImagePath.GameScene.Gamescene.TIME));
        point = irc.tryGetImage(PathBuilder.getImg(ImagePath.GameScene.Gamescene.DOUBLEPOINT));
        lose = irc.tryGetImage(PathBuilder.getImg(ImagePath.GameScene.Gamescene.LOSE));
        win = irc.tryGetImage(PathBuilder.getImg(ImagePath.GameScene.Gamescene.WIN));
        winP1 = irc.tryGetImage(PathBuilder.getImg(ImagePath.GameScene.Gamescene.WINP1));
        winP2 = irc.tryGetImage(PathBuilder.getImg(ImagePath.GameScene.Gamescene.WINP2));
        winTie = irc.tryGetImage(PathBuilder.getImg(ImagePath.GameScene.Gamescene.WINTIE));
        nametag = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.NAME));
        home = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.HOME));
        audio = new AudioA.PressedMusic();

        countdown.timeStart();
        paintWinP1 = false;
        paintWinP2 = false;
        paintLose = false;
        paintWin = false;
        paintTie = false;
        count = 0;
        popWindowMode = false;

    }

    @Override
    public void sceneUpdate() {

        if (popWindowMode) {
            popWindow.update();
            if (popWindow.getIsFinished()) {
                sceneController.changeScene(new RankingScene(sceneController, picklevel, popWindow.getScoreSystems()));
            }
            return;
        }
        if (pickActor != -1) {
            if (resetWindow != null) {
                if (resetWindow.reset) {
                    sceneController.changeScene(new GameScene(sceneController, pickActor, picklevel));
                    if (picklevel == 0) {
                        audio.getThemeOne().loop();
                    }
                    if (picklevel == 1) {
                        audio.getThemeTwo().loop();
                    }
                    if (picklevel == 2) {
                        audio.getThemeThree().loop();
                    }
                }
            }
            workControl.getActor().move(workControl.getAllTables());
            // 贏了//時間不會停！         
            if (GameState.getScore1() >= Global.VICOTRY_REQUIREMENT && countdown.isTimeUp()) {
                //System.out.println("WINWINWINW");
                paintWin = true;
                popWindow = new PopUpWindow(picklevel, GameState.getHigestScore(), playerNum);
                popWindowMode = true;

                if (popWindow.getIsFinished()) {
                    sceneController.changeScene(new RankingScene(sceneController, picklevel, popWindow.getScoreSystems()));
                }

            } else if (!countdown.isTimeUp() && GameState.getScore1() != Global.VICOTRY_REQUIREMENT) {
                countdown.timeUpDate();
                paintLose = true;

            } else {
                paintLose = false;
            }

        } else {
            if (resetWindow != null) {
                if (resetWindow.reset) {
                    sceneController.changeScene(new GameScene(sceneController, actorOne, actorTwo, picklevel));
                    if (picklevel == 0) {
                        audio.getThemeOne().loop();
                    }
                    if (picklevel == 1) {
                        audio.getThemeTwo().loop();
                    }
                    if (picklevel == 2) {
                        audio.getThemeThree().loop();
                    }
                }
            }
            workControl.getActor().move(workControl.getAllTables());
            workControl.getActor2().move(workControl.getAllTables());
            paintLose = true;
            if (countdown.isTimeUp()) {
                //第一人贏
                if (GameState.getScore1() > GameState.getScore2()) {
                    paintWinP1 = true;
                    popWindow = new PopUpWindow(picklevel, GameState.getHigestScore(), playerNum);
                    popWindowMode = true;

                }
                //第二人贏
                if (GameState.getScore2() > GameState.getScore1()) {
                    paintWinP2 = true;
                    popWindow = new PopUpWindow(picklevel, GameState.getHigestScore(), playerNum);
                    popWindowMode = true;
                    if (popWindow.getIsFinished()) {
                        sceneController.changeScene(new RankingScene(sceneController, picklevel, popWindow.getScoreSystems()));
                    }

                }
                //兩人不為零分 平手
                if (GameState.getScore2() != 0 && GameState.getScore1() != 0 && GameState.getScore1() == GameState.getScore2()) {
                    paintTie = true;

                }
                //兩人都零分
                if (GameState.getScore2() == 0 && GameState.getScore1() == 0) {
                    paintLose = false;
                }
            } else {
                countdown.timeUpDate();
            }
        }
        count++;
        if (paintLose == false) {
            resetWindow = new ResetWindow();
        }
    }

    @Override
    public void sceneEnd() {
        audio.getThemeThree().stop();
        audio.getThemeOne().stop();
        audio.getThemeTwo().stop();
    }

    private void drawLose(Graphics g) {
        drawRect(g);
        g.drawImage(lose, 0, 0, Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT, 0, 0, Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT, null);
    }

    private void drawWin(Graphics g) {
        drawRect(g);
        g.drawImage(win, 0, 0, Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT, 0, 0, Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT, null);
    }

    private void drawWinP1(Graphics g) {
        drawRect(g);
        g.drawImage(winP1, 0, 0, Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT, 0, 0, Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT, null);
    }

    private void drawWinP2(Graphics g) {
        drawRect(g);
        g.drawImage(winP2, 0, 0, Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT, 0, 0, Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT, null);
    }

    private void drawWinTie(Graphics g) {
        drawRect(g);
        g.drawImage(winTie, 0, 0, Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT, 0, 0, Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT, null);
    }

    private void drawRect(Graphics g) {
        g.setColor(new Color(0, 0, 0, 128));
        g.fillRect(0, 0, Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT);
    }

    private void drawScore(Graphics g) {
        g.setFont(f3);
        g.setColor(new Color(254, 128, 136));
        g.drawString("" + GameState.getScore1(), 860, 55);
        if (workControl.getActor2() != null) {
            g.drawString("" + GameState.getScore2(), 640, 55);
        }
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT, 0, 0, Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT, null);
        workControl.paint(g);

        if (workControl.getActor2() != null) {
            g.drawImage(point, 760, 10, 970, 75, 0, 0, 470, 140, null);
            g.drawImage(point, 530, 10, 740, 75, 0, 140, 470, 280, null);
        } else {
            g.drawImage(time, 760, 10, 970, 75, 0, 0, 470, 140, null);
        }

        g.drawImage(time, 760, 100, 970, 165, 0, 140, 470, 280, null);

        drawScore(g);//分數
        countdown.paint(g); //倒數
        if (!paintLose) {//輸了
            drawLose(g);
        }
        if (paintWin) {//贏了
            drawWin(g);
            g.drawImage(nametag, 300, 550, 400, 107, null);
            g.setFont(f);
            g.setColor(new Color(254, 128, 136));
            g.drawString("Please Press Enter", 400, 530);
        }
        if (paintWinP1) {
            drawWinP1(g);
            g.drawImage(nametag, 300, 570, 400, 107, null);
            g.setFont(f);
            g.setColor(new Color(254, 128, 136));
            g.drawString("Please Press Enter", 400, 550);

        }
        if (paintWinP2) {
            drawWinP2(g);
            g.drawImage(nametag, 300, 570, 400, 107, null);
            g.setFont(f);
            g.setColor(new Color(254, 128, 136));
            g.drawString("Please Press Enter", 400, 550);
            //popWindow.paint(g);
        }
        if (paintTie) {
            drawWinTie(g);

            //g.drawImage(nametag, 300, 550, 400, 107, null);
        }
        g.drawImage(home, HOME_X, HOME_Y, GUI_BUTTON_WIDTH, GUI_BUTTON_HEIGHT, null);
        // g.drawImage(btnReturn, RETURN_X, RETURN_Y, GUI_BUTTON_WIDTH, GUI_BUTTON_HEIGHT, null);

        if (popWindowMode) {
            popWindow.paint(g);
            //g.drawImage(name, 300, 520, 400, 107, null);
        }
        if (ensureWindow != null) {
            ensureWindow.paint(g);
        }

        if (resetWindow != null) {
            resetWindow.paint(g);
        }
    }

    @Override
    public CommandSolver.KeyCommandListener getKeyCommandListener() {
        if (resetWindow != null) {
            return resetWindow.getKeyCommandListener();
        }
        if (ensureWindow == null) {

            return new CommandSolver.KeyCommandListener() {

                @Override
                public void keyPressed(int commandCode, long trigTime) {
                    if (pickActor != -1) {
                        switch (commandCode) {
                            case Global.UP:

                                workControl.getActor().changeDirection(Global.UP).setSpeedY(-workControl.getActor().getSpeed());
                                if (workControl.getActor().getHoldingObj() != null) {
                                    workControl.getActor().randomEat();
                                }
                                break;
                            case Global.LEFT:
                                workControl.getActor().changeDirection(Global.LEFT).setSpeedX(-workControl.getActor().getSpeed());
                                break;
                            case Global.DOWN:
                                workControl.getActor().changeDirection(Global.DOWN).setSpeedY(workControl.getActor().getSpeed());
                                if (workControl.getActor().getHoldingObj() != null) {
                                    workControl.getActor().randomEat();
                                }
                                break;
                            case Global.RIGHT:
                                workControl.getActor().changeDirection(Global.RIGHT).setSpeedX(workControl.getActor().getSpeed());
                                break;
                            case Global.TAKE:

                                if (workControl.getActor().getHoldingObj() == null) {  //手上沒東西 
                                    workControl.getActor().takeObj(workControl.getAllTables(), workControl.getFloorObjs());
                                } else {  //手上有東西
                                    workControl.getActor().releaseObj(workControl.getOrderContainer(), workControl.getAllTables(), workControl.getFloorObjs());
                                }
                                break;
                            case Global.PROCESS:
                                workControl.getActor().processFood(workControl.getAllTables());
                                break;
                        }
                    } else {
                        switch (commandCode) {
                            case Global.UP:
                                if (workControl.getActor().getHoldingObj() != null) {
                                    workControl.getActor().randomEat();
                                }
                                workControl.getActor().changeDirection(Global.UP).setSpeedY(-workControl.getActor().getSpeed());
                                break;
                            case Global.LEFT:
                                workControl.getActor().changeDirection(Global.LEFT).setSpeedX(-workControl.getActor().getSpeed());
                                break;
                            case Global.DOWN:
                                if (workControl.getActor().getHoldingObj() != null) {
                                    workControl.getActor().randomEat();
                                }
                                workControl.getActor().changeDirection(Global.DOWN).setSpeedY(workControl.getActor().getSpeed());
                                break;
                            case Global.RIGHT:
                                workControl.getActor().changeDirection(Global.RIGHT).setSpeedX(workControl.getActor().getSpeed());
                                break;
                            case Global.TAKE:
                                if (workControl.getActor().getHoldingObj() == null) {  //手上沒東西 
                                    workControl.getActor().takeObj(workControl.getAllTables(), workControl.getFloorObjs());
                                    if (workControl.getActor().checkGrab(workControl.getActor2())) {  //烏龜可搶別人手上食物                              
                                        workControl.getActor().grabFood(workControl.getActor2());
                                    }
                                } else {  //手上有東西
                                    workControl.getActor().releaseObj(workControl.getOrderContainer(), workControl.getAllTables(), workControl.getFloorObjs());
                                }
                                break;
                            case Global.PROCESS:
                                workControl.getActor().processFood(workControl.getAllTables());
                                break;
                            case Global.UP2:
                                workControl.getActor2().changeDirection(Global.UP).setSpeedY(-workControl.getActor2().getSpeed());
                                if (workControl.getActor2().getHoldingObj() != null) {
                                    workControl.getActor2().randomEat();
                                }
                                break;

                            case Global.LEFT2:
                                workControl.getActor2().changeDirection(Global.LEFT).setSpeedX(-workControl.getActor2().getSpeed());
                                break;
                            case Global.DOWN2:
                                workControl.getActor2().changeDirection(Global.DOWN).setSpeedY(workControl.getActor2().getSpeed());
                                if (workControl.getActor2().getHoldingObj() != null) {
                                    workControl.getActor2().randomEat();
                                }
                                break;
                            case Global.RIGHT2:
                                workControl.getActor2().changeDirection(Global.RIGHT).setSpeedX(workControl.getActor2().getSpeed());
                                break;
                            case Global.TAKE2:
                                if (workControl.getActor2().getHoldingObj() == null) {  //手上沒東西 
                                    workControl.getActor2().takeObj(workControl.getAllTables(), workControl.getFloorObjs());
                                    if (workControl.getActor2().checkGrab(workControl.getActor())) {
                                        workControl.getActor2().grabFood(workControl.getActor());
                                    }else 
                                        return ;
                                 
                                } else {  //手上有東西
                                    workControl.getActor2().releaseObj(workControl.getOrderContainer(), workControl.getAllTables(), workControl.getFloorObjs());
                                }
                                break;
                            case Global.PROCESS2:
                                workControl.getActor2().processFood(workControl.getAllTables());
                                break;

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
                public void keyReleased(int commandCode, long trigTime) {
                    if (pickActor != -1) {
                        switch (commandCode) {
                            case Global.UP:
                                workControl.getActor().setSpeedY(0);
                                break;
                            case Global.LEFT:
                                workControl.getActor().setSpeedX(0);
                                break;
                            case Global.RIGHT:
                                workControl.getActor().setSpeedX(0);
                                break;
                            case Global.DOWN:
                                workControl.getActor().setSpeedY(0);
                                break;
                        }
                    } else {
                        switch (commandCode) {

                            case Global.UP:
                                workControl.getActor().setSpeedY(0);
                                break;
                            case Global.LEFT:
                                workControl.getActor().setSpeedX(0);
                                break;
                            case Global.RIGHT:
                                workControl.getActor().setSpeedX(0);
                                break;
                            case Global.DOWN:
                                workControl.getActor().setSpeedY(0);
                                break;
                            case Global.TAKE:
                                break;
                            case Global.UP2:
                                workControl.getActor2().setSpeedY(0);
                                break;
                            case Global.LEFT2:
                                workControl.getActor2().setSpeedX(0);
                                break;
                            case Global.RIGHT2:
                                workControl.getActor2().setSpeedX(0);
                                break;
                            case Global.DOWN2:
                                workControl.getActor2().setSpeedY(0);
                                break;
                        }
                    }

                }
            };
        } else if (ensureWindow != null) {
            return ensureWindow.getKeyCommandListener();
        }
        return null;
    }

    @Override
    public CommandSolver.MouseCommandListener getMouseCommandListener() {
        return new CommandSolver.MouseCommandListener() {

            @Override
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {

            }
        };
    }

    public CommandSolver.TypedListener getTypedListener() {
        if (popWindowMode) {
            return popWindow.getTypedListener();
        }
        return typedListener;
    }

}
