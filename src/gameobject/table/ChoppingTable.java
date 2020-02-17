/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.table;

import Main.GameState;
import gameobject.Indicator;
import gameobject.OrderContainer;
import gameobject.food.Food;
import gameobject.food.NonCuttable;
import java.awt.Graphics;
import utils.Global;


/**
 *
 * @author sara
 */
public class ChoppingTable extends Table {   
    private Indicator indicator; 
    
  public ChoppingTable(int x, int y, int width, int height, int table) {
        super(x, y, width, height, table);       
    }
  
  public boolean cutFood(Food tableFood){
//      this.indicator = new Indicator(x ,y+ Global.INDICATOR_OFFSET,width,height) ;
//      indicator.process();
//      indicator = null;      
      gameObj.isCut();
      return true;
  }
    @Override
    public void paint(Graphics g) {        
        tableHelper.paint( g, x, y, width, height);
        if(gameObj != null){
            gameObj.paint(g);
        }        
        if(indicator != null){
            indicator.paint(g);
        }
        
    }
}
