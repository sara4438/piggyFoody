/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.actor;

import Main.GameState;
import utils.Global;

/**
 *
 * @author dingding
 */
public class Rabbit extends Actor {

    public Rabbit(int x, int y, int width, int height, int actor) {
        super(x, y, width, height, actor);
    }

    public void addExtraScore(int playerNum) { 
        if (playerNum == 1) {
            GameState.getGameState().addScore1(Global.RABBIT_EXTRA_SCORE);
        } else {
            GameState.getGameState().addScore2(Global.RABBIT_EXTRA_SCORE);
        }

    }

}
