/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene.about;

import controllers.ImageResourceController;
import controllers.PathBuilder;
import controllers.SceneController;
import gameobject.Order;
import gameobject.OrderContainer;
import gameobject.actor.Actor;
import gameobject.food.Food;
import gameobject.table.ChoppingTable;
import gameobject.table.FoodTable;
import gameobject.table.ScoreTable;
import gameobject.table.Table;
import gameobject.table.TableLeg;
import io.CommandSolver;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import popwindow.EnsureWindow;
import scene.Scene;
import scene.Scene;
import scene.menu.MenuScene;
import scene.menu.ModeScene;
import utils.Global;
import values.ImagePath;

/**
 *
 * @author sara
 */
public class AboutScene2 extends Scene {

    private CommandSolver.KeyCommandListener keyCommandListener;
    //private CommandSolver.KeyCommandListener keyCommandListener;
    private CommandSolver.MouseCommandListener mouseCommandListener;
    private BufferedImage img;
    private Actor actor1;
    private Actor actor2;
    private ArrayList<Table> tables;
    private ArrayList<Food> floorFood;
    private ArrayList<TableLeg> tableLegs;
    private OrderContainer orderContainer;
    private BufferedImage home;
    private BufferedImage btnReturn;
    private EnsureWindow ensureWindow;

    public AboutScene2(SceneController sceneController) {
        super(sceneController);
        ImageResourceController irc = ImageResourceController.getInstance();
        img = irc.tryGetImage(PathBuilder.getImg(ImagePath.Background.Scene.Background_About2));
        home = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.HOME));
        btnReturn = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.RETURN));
    }

    @Override
    public void sceneBegin() {
        actor1 = new Actor(650, 200, 50, 50, 0);
        actor2 = new Actor(650, 500, 50, 50, 1);
        tables = new ArrayList<>();
        floorFood = new ArrayList<>();
        tableLegs = new ArrayList<>();
        tables.add(new Table(750, 200, 50, 50, 36));
        tables.add(new Table(750, 500, 50, 50, 36));
        tables.add(new FoodTable(700, 200, 50, 50, 14));
        tables.add(new FoodTable(700, 500, 50, 50, 13));
        tables.add(new FoodTable(800, 200, 50, 50, 16));
        tables.add(new FoodTable(800, 500, 50, 50, 18));
        tables.add(new ChoppingTable(750, 350, 50, 50, 39));
        tables.add(new ScoreTable(700, 350, 50, 50, 37));
        tableLegs.add(new TableLeg(750, 250, 50, 50, 40));
        tableLegs.add(new TableLeg(750, 550, 50, 50, 40));
        tableLegs.add(new TableLeg(800, 250, 50, 50, 40));
        tableLegs.add(new TableLeg(800, 550, 50, 50, 40));
        tableLegs.add(new TableLeg(750, 400, 50, 50, 40));
        tableLegs.add(new TableLeg(700, 250, 50, 50, 40));
        tableLegs.add(new TableLeg(700, 550, 50, 50, 40));
        tableLegs.add(new TableLeg(700, 400, 50, 50, 40));
        orderContainer = new OrderContainer(200, 250, Global.ORDER_WIDTH, Global.ORDER_HEIGHT, -1); //最後一個參數是level幾
    }

    @Override
    public void sceneUpdate() {
        actor1.move(tables);
        actor2.move(tables);
    }

    @Override
    public void sceneEnd() {

    }

    @Override
    public void paint(Graphics g) {
        g.drawImage(img, 0, 0, Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT, null);
        for (int i = 0; i < floorFood.size(); i++) {
            floorFood.get(i).paint(g);
        }
        for (int i = 0; i < tables.size(); i++) {
            tables.get(i).paint(g);
        }
        for (int i = 0; i < tableLegs.size(); i++) {
            tableLegs.get(i).paint(g);
        }
        orderContainer.paint(g);
        actor1.paint(g);
        actor2.paint(g);
//        g.drawImage(home, 760, 670, Global.GUI_BUTTON_WIDTH, Global.GUI_BUTTON_HEIGHT, null);
//        g.drawImage(btnReturn, 870, 670, Global.GUI_BUTTON_WIDTH, Global.GUI_BUTTON_HEIGHT, null);
        if (ensureWindow != null) {
            ensureWindow.paint(g);
        }
    }

//    public CommandSolver.KeyCommandListener getKeyCommandListener() {
//       
//    }

    public CommandSolver.KeyCommandListener getKeyCommandListener() {
if (ensureWindow == null) {
        return new CommandSolver.KeyCommandListener() {

            @Override
            public void keyPressed(int commandCode, long trigTime) {
                switch (commandCode) {
                    case Global.UP:
                        actor1.changeDirection(Global.UP).setSpeedY(-actor1.getSpeed());
                        break;
                    case Global.LEFT:
                        actor1.changeDirection(Global.LEFT).setSpeedX(-actor1.getSpeed());
                        break;
                    case Global.DOWN:
                        actor1.changeDirection(Global.DOWN).setSpeedY(actor1.getSpeed());
                        break;
                    case Global.RIGHT:
                        actor1.changeDirection(Global.RIGHT).setSpeedX(actor1.getSpeed());
                        break;
                    case Global.TAKE:
                        if (actor1.getHoldingObj() == null) {  //手上沒東西 
                            actor1.takeObj(tables, floorFood);
                            if (actor1.checkGrab(actor2)) {  //烏龜可搶別人手上食物                              
                                actor1.grabFood(actor2);
                            }
                        } else {  //手上有東西
                            actor1.releaseObj(orderContainer, tables, floorFood);
                        }
                        break;
                    case Global.PROCESS:
                        actor1.processFood(tables);
                        break;
                    case Global.UP2:
                        actor2.changeDirection(Global.UP).setSpeedY(-actor2.getSpeed());
                        break;
                    case Global.LEFT2:
                        actor2.changeDirection(Global.LEFT).setSpeedX(-actor2.getSpeed());
                        break;
                    case Global.DOWN2:
                        actor2.changeDirection(Global.DOWN).setSpeedY(actor2.getSpeed());
                        break;
                    case Global.RIGHT2:
                        actor2.changeDirection(Global.RIGHT).setSpeedX(actor2.getSpeed());
                        break;
                    case Global.TAKE2:
                        if (actor2.getHoldingObj() == null) {  //手上沒東西 
                            actor2.takeObj(tables, floorFood);
                            if (actor2.checkGrab(actor1)) {
                                actor2.grabFood(actor1);
                            }
                        } else {  //手上有東西
                            actor2.releaseObj(orderContainer, tables, floorFood);
                        }
                        break;
                    case Global.PROCESS2:
                        actor2.processFood(tables);
                        break;

                }
                if (commandCode == Global.RETURN) {
                    sceneController.changeScene(new AboutScene(sceneController));
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
                switch (commandCode) {
                    case Global.UP:
                        actor1.setSpeedY(0);
                        break;
                    case Global.LEFT:
                        actor1.setSpeedX(0);
                        break;
                    case Global.RIGHT:
                        actor1.setSpeedX(0);
                        break;
                    case Global.DOWN:
                        actor1.setSpeedY(0);
                        break;
                    case Global.TAKE:
                        break;
                    case Global.UP2:
                        actor2.setSpeedY(0);
                        break;
                    case Global.LEFT2:
                        actor2.setSpeedX(0);
                        break;
                    case Global.RIGHT2:
                        actor2.setSpeedX(0);
                        break;
                    case Global.DOWN2:
                        actor2.setSpeedY(0);
                        break;
                }
            }
        };
}else if (ensureWindow != null) {
            return ensureWindow.getKeyCommandListener();
        }
        return null;
    }

    public CommandSolver.MouseCommandListener getMouseCommandListener() {
        return mouseCommandListener;
    }
//      public CommandSolver.KeyCommandListener getKeyCommandListener() {
//        if (ensureWindow == null) {
//            return keyCommandListener;
//        } else if (ensureWindow != null) {
//            return ensureWindow.getKeyCommandListener();
//        }
//        return null;
//    }
}
