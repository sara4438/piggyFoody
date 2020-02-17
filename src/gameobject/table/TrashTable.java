/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.table;

import Main.GameState;
import audio.AudioA;
import controllers.ImageResourceController;
import controllers.PathBuilder;
import gameobject.actor.Actor;
import gameobject.food.Food;
import gameobject.food.NonCuttable;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import values.ImagePath;
import audio.AudioA;
import gameobject.OrderContainer;

/**
 *
 * @author sara
 */
public class TrashTable extends Table {

    private BufferedImage trash;
    private BufferedImage trashcover;
    private AudioA.PressedMusic audio;

    public TrashTable(int x, int y, int width, int height, int table) {
        super(x, y, width, height, table);
        audio = new AudioA.PressedMusic();

    }

    @Override
    public void operate(Actor actor) {
    }

    @Override
    public boolean receiveFood(OrderContainer orderContainer,Actor actor, Food holdingObj) {
        audio.getPressedTrash().play();

        return true;
    }

    @Override
    public void paint(Graphics g) {
        tableHelper.paint(g, x, y, width, height);
    }
}
