/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene.ranking;

import static Main.Main.*;
import controllers.*;
import gameobject.Buttons;
import io.*;
import io.CommandSolver.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.*;
import popwindow.EnsureWindow;
import scene.*;
import scene.menu.*;
import utils.Global;
import values.ImagePath;

/**
 *
 * @author dingding
 */
public class RankingScene extends Scene {

    // private static int LEVEL_NUM = 3;
    private BufferedImage img;
    private BufferedImage stage;
    private BufferedImage stageSelect;
    private BufferedImage home;
    private BufferedImage btnReturn;
    private BufferedImage stage01;
    private BufferedImage stage02;
    private BufferedImage stage03;
    private Buttons[] levelButton;
    private MouseCommandListener mouseCommandListener;
    private CommandSolver.KeyCommandListener keyCommandListener;
    private EnsureWindow ensureWindow;
    private int currentSelect;

//    private PopUpWindow popWindow;
    private ArrayList<ScoreSystem> scoreRecords;
    private int level;

    public RankingScene(SceneController sceneController) {
        super(sceneController);
        currentSelect = 0;
    }

    public RankingScene(SceneController sceneController, int level, ArrayList<ScoreSystem> ScoreRecord) {
        super(sceneController);
        this.scoreRecords = ScoreRecord;
        currentSelect = level;
    }

    @Override
    public void sceneBegin() {
        ImageResourceController irc = ImageResourceController.getInstance();
        home = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.HOME));
        btnReturn = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.RETURN));
        stage = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.STAGE));
        stageSelect = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.STAGESELECT));
        img = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.RANKING));
        stage01 = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.STAGE01));
        stage02 = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.STAGE02));
        stage03 = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.STAGE03));
        keyCommandListener = new CommandSolver.KeyCommandListener() {
            @Override
            public void keyPressed(int commandCode, long trigTime) {
                switch (commandCode) {
                    case Global.MENU_ENTER:
                    //sceneController.changeScene(new SecondStartScene(sceneController));
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
            public void keyReleased(int commandCode, long trigTime) {
            }
        };

        mouseCommandListener = new CommandSolver.MouseCommandListener() {
            @Override
            public void mouseTrig(MouseEvent e, CommandSolver.MouseState state, long trigTime) {
                for (int i = 0; i < 3; i++) {
                    if (state == CommandSolver.MouseState.CLICKED) {
                        //System.out.println("123456");
                        if (levelButton[i].isCollision(e.getX(), e.getY())) {
                            currentSelect = i;
                            //System.out.println("123456");   
                            levelButton[i].click(e.getX(), e.getY());
                        }
                    } else if (state == CommandSolver.MouseState.MOVED) {
                        if (levelButton[i].isCollision(e.getX(), e.getY())) {
                            levelButton[i].hover(e.getX(), e.getY());
                        }
                    }
                }

            }
        };

        levelButton = new Buttons[3];
        for (int i = 0; i < 3; i++) {
            levelButton[i] = new Buttons(265 + (188 * i), 90, 148, 130, 1, i);
            levelButton[i].setButtonListener(new Buttons.ButtonListener() {
                @Override
                public void onClick(int x, int y) {
                    //sceneController.changeScene(new MenuScene(sceneController));
                }

                @Override
                public void hover(int x, int y) {
                }
            });
        }
//        sortScoreSystemsByScore();
    }

    @Override
    public void sceneUpdate() {
//        popWindow.update();
    }

    @Override
    public void sceneEnd() {

    }

    public ArrayList<ScoreSystem> sortScoreSystemsByScore() {
        for (int i = 1; i < scoreRecords.size(); i++) {
            for (int j = 0; j < scoreRecords.size() - i; j++) {
                if (scoreRecords.get(j).getTotalScore() < scoreRecords.get(j + 1).getTotalScore()) {
                    ScoreSystem tmp = scoreRecords.get(j);
                    scoreRecords.set(j, scoreRecords.get(j + 1));
                    scoreRecords.set(j + 1, tmp);
                }
            }
        }
        return scoreRecords;
    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT, null);
        ArrayList<ScoreSystem> scoreSystems = sortScoreSystemsByScore();
        g.setFont(f2);
        int count = 0;
        for (int i = 0; i < scoreSystems.size(); i++) {
            ScoreSystem sys = scoreSystems.get(i);
            if (sys.getLevel() == currentSelect) {
                g.setColor(new Color(254, 128, 136));
                g.drawString(sys.getName(), 360, 295 + count * 82);
                g.drawString(sys.getTotalScore() + "", 570, 295 + count++ * 82);
                //tmpNum++;
            }
            if (count == 5) {
                break;
            }

        }

        g.drawImage(home, 760, 670, Global.GUI_BUTTON_WIDTH, Global.GUI_BUTTON_HEIGHT, null);
        g.drawImage(btnReturn, 870, 670, Global.GUI_BUTTON_WIDTH, Global.GUI_BUTTON_HEIGHT, null);

        if (ensureWindow != null) {
            ensureWindow.paint(g);
        }
        for (int i = 0; i < 3; i++) {
            if (i == currentSelect) {
                levelButton[i].paintChooseRank(g);
            } else {
                levelButton[i].paintRank(g);
            }
        }
        g.drawImage(stage01, 370, 75, 423, 135, 0, 0, 53, 60, null);
        g.drawImage(stage02, 558, 75, 611, 135, 0, 0, 53, 60, null);
        g.drawImage(stage03, 746, 75, 799, 135, 0, 0, 53, 60, null);
    }

    public MouseCommandListener getMouseCommandListener() {
        return mouseCommandListener;
    }

    public CommandSolver.KeyCommandListener getKeyCommandListener() {
        if (ensureWindow == null) {
            return keyCommandListener;
        } else if (ensureWindow != null) {
            return ensureWindow.getKeyCommandListener();
        }
        return null;
    }

    public CommandSolver.TypedListener getTypedListener() {
        return null;
    }

}
