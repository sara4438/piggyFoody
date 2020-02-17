/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import utils.Global;
import static utils.Global.VICOTRY_REQUIREMENT;

/**
 *
 * @author sara
 */
public class GameState {

    private static GameState instance;
    private int victoryRequirement = Global.VICOTRY_REQUIREMENT;
    private static int score1 = 0;
    private static int score2 = 0;
//     private static boolean demoMode = false;

    private GameState() {
    }

//        public boolean getDemoMode(){
//            return demoMode;
//        }
    public void addScore1(int increment) {
        score1 += increment;
        if (isVictory()) {
            //贏了要幹嘛
        }
    }

    public void addScore2(int increment) {
        score2 += increment;
        if (isVictory()) {
            //贏了要幹嘛
        }
    }

    public static int getHigestScore() {
        if (score1 > score2) {
            return score1;
        }
        return score2;
    }

    public static int getScore1() {
        return score1;
    }

    public static int getScore2() {
        return score2;
    }

    public void reset1() {
        this.score1 = 0;
    }

    public void reset2() {
        this.score2 = 0;
    }

    private boolean isVictory() {
        return score1 >= VICOTRY_REQUIREMENT;
    }

    public static GameState getGameState() {
        if (instance == null) {
            instance = new GameState();
        }
        return instance;
    }
}
