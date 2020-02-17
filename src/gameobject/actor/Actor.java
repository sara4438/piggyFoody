/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.actor;

import gameobject.table.*;
import controllers.*;
import gameobject.*;
import gameobject.food.*;
import java.awt.Graphics;
import java.util.ArrayList;
import utils.DelayCounter;
import utils.Global;
import static utils.Global.*;
import audio.AudioA;
//新的

/**
 *
 * @author dingding
 */
public class Actor extends MovableGameObject {

    private static final int[] ACT = {0, 1, 2, 1};
    private int act;
    private int delay;//動慢一點c
    protected int direction;
    private ActorHelper actorHelper;
    private Table tables;
    private AudioA.PressedMusic audio;
    //private BufferedImage img;//先把圖抓進來//Helper

    private DelayCounter delayCounter;
//    private WorkControl workControl;
    private CombineHelper combineHelper;
    private int playerNum;
    protected Food holdingObj;
    private int actorImg;
    private double speed;

    public Actor(int x, int y, int width, int height, int actor) {
        super(x, y, width, height);
        delay = act = 0;
        direction = Global.DOWN;
        delayCounter = new DelayCounter(4);
        actorHelper = new ActorHelper(actor);
//        workControl = new WorkControl(Global.WHICH_LEVEL);
        this.actorImg = actor;
        switch (actor) {
            case 0:
                this.speed = 3.2;
                break;
            case 1:
                this.speed = 2.5;
                break;
            case 2:
                this.speed = 1.8;
                break;
        }
        audio = new AudioA.PressedMusic();

    }

    public void setHoldingObj(Food food) {
        this.holdingObj = food;
    }

    public double getSpeed() {
        return this.speed;
    }

    public void setPlayerNum(int number) {
        this.playerNum = number;
    }

    public int getPlayerNum() {
        return playerNum;
    }

    public int getActorImg() {
        return actorImg;
    }

    //給他一個方法去改變它的方向
    public Actor changeDirection(int direction) {
        this.direction = direction;
        return this;
    }

    public boolean checkFloorTake(GameObject obj) {
        int left = x;
        int oLeft = obj.getX();
        int right = x + Global.IMG_SQUARE;
        int oRight = obj.getX() + Global.IMG_SQUARE;
        int top = y;
        int oTop = obj.getY();
        int bottom = y + Global.IMG_SQUARE;
        int oBottom = obj.getY() + Global.IMG_SQUARE;
        int oMidX = (oLeft + oRight) / 2;
        int oMidY = (oTop + oBottom) / 2;
        int midX = (left + right) / 2;
        int midY = (top + bottom) / 2;
        float overLapOffset = (Global.IMG_SQUARE * 2) / 3;
        int distance = (int) Math.sqrt(Math.pow(oMidX - midX, 2) + Math.pow(oMidY - midY, 2));

        if (distance < overLapOffset) {
            return true;
        }
        return false;
    }

    
    
    public void setActor(int actor) {
        actorHelper = new ActorHelper(actor);
    }

    public int getX() {
        return this.x;
    }

    @Override
    public int getY() {
        return this.y;
    }

    @Override
    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Food getHoldingObj() {
        return holdingObj;
    }

    public Actor move() {
        if (delayCounter.update()) {
            act = ++act % 4;
        }
        return this;
    }

    public void move(ArrayList<Table> objs) {
        if (this.getSpeedX() != 0 || this.getSpeedY() != 0) {
            this.move();
        } else {
            act = 1;
        }
        if (!hasHorizontalCollision(objs)) {
            if (this.x < 5) {
                if (dx < 0) {
                    x = 5;
                    if (holdingObj != null) {
                        holdingObj.setX(this.x);
                        holdingObj.setY(this.y);
                    } 
                    return;
                }
            } else if (this.x > 940) {
                if (dx > 0) {
                    x = 940;
                    if (holdingObj != null) {
                        holdingObj.setX(this.x);
                        holdingObj.setY(this.y);
                    }
                    return;
                }
            }
            x += dx;
        }
        if (!hasVerticalCollision(objs)) {
            if (this.y <= 150) {
                if (dy < 0) {
                    y = 150;
                    if (holdingObj != null) {
                        holdingObj.setX(this.x);
                        holdingObj.setY(this.y);
                    }
                    return;
                }
            } else if (this.y >= 690) {
                if (dy > 0) {
                    y = 690;
                    if (holdingObj != null) {
                        holdingObj.setX(this.x);
                        holdingObj.setY(this.y);
                    }
                    return;
                }
            }
            y += dy;
        }
        if (holdingObj != null) {
            holdingObj.setX(this.x);
            holdingObj.setY(this.y);
        }
    }

    public boolean processFood(ArrayList<Table> allTables) {
        //System.out.print("有來切上");
        Table table = findNearTable(allTables);
        if (table != null) {
            if (table.getGameObject() != null) {//如果桌上有東西
                table.cutFood(table.getGameObject());
            }
            return true;
        }
        return false;
    }

    public void drawObj(Table table) {
        table.operate(this);
    }

    public void takeObj(ArrayList<Table> allTables, ArrayList<Food> floorObjs) {

        Table table = findNearTable(allTables);
        if (table != null) { //在桌子前面
            if (table.getGameObject() != null) {  //如果桌上有東西
                holdingObj = table.getGameObject();
                table.setGameObject(null);
                audio.getPressedTrash().play();
            } else { //桌上沒東西
                drawObj(table);
            }
        } else { //沒有面向桌子
            takeFloorObj(floorObjs);
        }
    }

    public void releaseObj(OrderContainer orderContainer, ArrayList<Table> allTables, ArrayList<Food> floorObjs) {
        Table table = findNearTable(allTables);
        if (table == null) {  //沒靠桌子
            dropFloor(floorObjs);
        } else { // 有靠桌子,臉朝桌子的話     
            if (table.getGameObject() == null) { //如果桌上是空的
                dropTable(orderContainer, table);
            } else { //桌上有food
                if (table.getGameObject().getIngredients().size() + holdingObj.getIngredients().size() > 3) {
                    return;
                } else {
                    NonCuttable combine = CombineHelper.combineFood(table.getGameObject(), holdingObj);
                    if (combine == null) {
                        return;
                    } else {
                        table.setGameObject(combine);

                        holdingObj = null;
                    }
                }
            }
        }
    }

    public void dropTable(OrderContainer orderContainer, Table table) {
        table.receiveFood(orderContainer, this, holdingObj);
        this.holdingObj = null;
    }

    public void dropFloor(ArrayList<Food> floorObjs) {
        floorObjs.add(holdingObj);
        holdingObj = null;

    }

    public void takeFloorObj(ArrayList<Food> floorObjs) {
        for (int i = 0; i < floorObjs.size(); i++) {
            if (checkFloorTake(floorObjs.get(i))) {
                audio.getPressedTrash().play();
                holdingObj = floorObjs.get(i);
                holdingObj.changeLocation(this);
                floorObjs.remove(i);

            }
        }
    }

    public Table findNearTable(ArrayList<Table> allTables) {
        Table table1 = isLeftToTable(allTables);
        Table table2 = isRightToTable(allTables);
        Table table3 = isUpToTable(allTables);
        Table table4 = isDownToTable(allTables);
        if (table1 != null && direction == RIGHT) {
            return table1;
        }
        if (table2 != null && direction == LEFT) {
            return table2;
        }
        if (table3 != null && direction == DOWN) {
            return table3;
        }
        if (table4 != null && direction == UP) {
            return table4;
        }
        return null;
    }

    public Table isLeftToTable(ArrayList<Table> allTables) {
        int midY = (getTop() + getBottom()) / 2;
        for (int i = 0; i < allTables.size(); i++) {
            if (allTables.get(i).getTop() < midY && allTables.get(i).getBottom() > midY && getRight() == allTables.get(i).getLeft()) {
                return allTables.get(i);
            }
        }
        return null;
    }

    public Table isRightToTable(ArrayList<Table> allTables) {
        int midY = (getTop() + getBottom()) / 2;
        for (int i = 0; i < allTables.size(); i++) {
            if (allTables.get(i).getTop() < midY && allTables.get(i).getBottom() > midY && getLeft() == allTables.get(i).getRight()) {
                return allTables.get(i);
            }
        }
        return null;
    }

    public Table isUpToTable(ArrayList<Table> allTables) {
        int midX = (getLeft() + getRight()) / 2;
        for (int i = 0; i < allTables.size(); i++) {
            if (getBottom() == allTables.get(i).getTop()
                    && midX > allTables.get(i).getLeft() && midX < allTables.get(i).getRight()) {
                return allTables.get(i);
            }
        }
        return null;
    }

    public Table isDownToTable(ArrayList<Table> allTables) {
        int midX = (getLeft() + getRight()) / 2;
        for (int i = 0; i < allTables.size(); i++) {
            if (getTop() == allTables.get(i).getBottom()
                    && midX > allTables.get(i).getLeft() && midX < allTables.get(i).getRight()) {
                return allTables.get(i);
            }
        }
        return null;
    }

    //不同角色特殊功能start
    public boolean grabFood(Actor actor) {
        return false;
    }

    public boolean checkGrab(Actor actor) {
        return false;
    }

    public void addExtraScore(int playerNum) {

    }

    public void randomEat() {

    }
    //不同角色特殊功能end

    @Override
    public void paint(Graphics g) {
        //g.drawRect(x, y, width, height);
        actorHelper.paint(g, x, y, width, height, ACT[act], direction);
        if (holdingObj != null) {
            holdingObj.paint(g);
        }
    }
}
