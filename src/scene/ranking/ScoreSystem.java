/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene.ranking;

import java.awt.Graphics;

/**
 *
 * @author dingding
 */
public class ScoreSystem {

    private int totalScore;
    private String name;
    private int level;

    public ScoreSystem() {
        totalScore = 0;
    }

    public ScoreSystem(String name, int totalScore, int level) {
        this.name = name;
        this.totalScore = totalScore;
        this.level = level;
    }

    public void setName(String name) {
        this.name = name;
    }

   public String getName(){
       return name;
   }
   
   public int getLevel(){
       return level;
   }
   
   public int getTotalScore(){
       return totalScore;
   }
   public int setTotalScore(){
       this.totalScore = totalScore;
       return totalScore;
   }
   public void paint(Graphics g){
//        g.drawString(totalScore+"/", 100, 80);
    }
    
    @Override
    public String toString(){
        return name +","+totalScore+"," +level  ;
    }
   
}