/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.table;

import controllers.ImageResourceController;
import controllers.PathBuilder;
import gameobject.GameObject;
import gameobject.OrderContainer;
import gameobject.actor.Actor;
import gameobject.food.Food;
import gameobject.food.NonCuttable;
import gameobject.food.NonCuttable;
import java.awt.Graphics;
import java.awt.image.*;
import java.util.ArrayList;
import values.ImagePath;

/**
 *
 * @author dingding
 */
public class Table extends GameObject {

    private BufferedImage img;
    private BufferedImage food1;
    protected Food gameObj;
    protected TableHelper tableHelper;
    protected int table;

    public Table(int x, int y, int width, int height, int table) {
        super(x, y, width, height);
        this.table = table;
        tableHelper = new TableHelper(table);
    }

    public boolean receiveFood(OrderContainer orderContainer, Actor actor, Food holdingObj) {
        gameObj = holdingObj;
        changeObjLocation();
        return true;
    }

    public boolean cutFood(Food tableFood) {
        return false;
    }

    public void setGameObject(Food gameObject) {
        this.gameObj = gameObject;
        if (gameObject != null) {
            this.gameObj.setX(this.x);
            this.gameObj.setY(this.y);
        }
    }

    public void operate(Actor actor) {
        return;
    }

    public Food getGameObject() {
        return this.gameObj;
    }

    public void changeObjLocation() {
        gameObj.setX(this.x);
        gameObj.setY(this.y);
    }

    @Override
    public void paint(Graphics g) {
        tableHelper.paint(g, x, y, width, height);
        if (gameObj != null) {
            gameObj.paint(g);
        }
    }

}
