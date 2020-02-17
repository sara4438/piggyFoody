/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.table;

import gameobject.table.Table;
import controllers.ImageResourceController;
import controllers.PathBuilder;
import gameobject.food.Cuttable;
import gameobject.food.Food;
import gameobject.food.NonCuttable;
import values.ImagePath;
import audio.AudioA;
import gameobject.actor.Actor;

/**
 *
 * @author sara
 */
public class FoodTable extends Table {

    private int foodType; //取什麼食物的桶子
     private AudioA.PressedMusic audio;

    public FoodTable(int x, int y, int width, int height, int foodType) { //食材是照片第幾張圖
        super(x, y, width, height, foodType);
        this.foodType = foodType;
        audio = new AudioA.PressedMusic();
    }

    @Override
    public void operate(Actor actor) {
        if (foodType == 16 || foodType == 18 || foodType == 26 || foodType == 28 || foodType == 30 || foodType == 32 || foodType == 34) {
            actor.setHoldingObj(new Cuttable(x, y, width, height, foodType));
        } else {
            actor.setHoldingObj( new NonCuttable(x, y, width, height, foodType));
        }
        audio.getPressedTrash().play();

    }
}