/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.table;

import Main.GameState;
import controllers.WorkControl;
import gameobject.OrderContainer;
import gameobject.actor.Actor;
import gameobject.food.Food;
import gameobject.food.NonCuttable;
import java.util.ArrayList;
import audio.AudioA;

/**
 *
 * @author sara
 */
public class ScoreTable extends Table {
    private AudioA.PressedMusic audio;

    public ScoreTable(int x, int y, int width, int height, int table) {
        super(x, y, width, height, table);
        audio = new AudioA.PressedMusic();
    }

    
    @Override
    public void operate(Actor actor){        
    }
    
    public boolean receiveFood(OrderContainer orderContainer, Actor actor, Food holdingObj) {
        if(orderContainer.fulfillOrder(holdingObj.getIngredients(), actor) !=null){
            audio.getPoint().play();
            System.out.print(GameState.getScore1());
            return true;
        }
            System.out.print("沒得分");
            return false;
     }    
}
