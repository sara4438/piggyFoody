/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package level;

import gameobject.table.ChoppingTable;
import gameobject.table.FoodTable;
import gameobject.table.ScoreTable;
import gameobject.table.Table;
import gameobject.table.TrashTable;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException; 
import java.util.ArrayList;

/**
 *
 * @author dingding
 */
public class Level {

    //protected LevelController controller;
    protected ArrayList<Table> tables;

    public Level(String name) {
        tables = new ArrayList<Table>();
        getGameObject(name);
    }

    //private Point playerPosition;
    public void getGameObject(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName + ".txt"));
            int y = 0;
            while (br.ready()) {
                String[] strs = br.readLine().split("");
                for (int i = 0; i < strs.length; i++) {
                    switch (strs[i]) {
                        case "1"://咖啡普通桌
                            tables.add(new Table(i * 50,  y * 50, 50, 50, 33));
                             break;
                        case "2"://垃圾桶
                            tables.add(new Table(i * 50,  y * 50, 50, 50, 35));
                            break;
                        case "3"://出餐口
                            tables.add(new Table(i * 50,  y * 50, 50, 50, 34));
                            break;
                        case "4":
                            tables.add(new FoodTable(i * 50,  y * 50, 50, 50, 0));
                            
                            //stars.add(new Star(i * IMG_X_OFFSET, 400 + y * IMG_Y_OFFSET, IMG_X_OFFSET, IMG_Y_OFFSET));
                            break;
                         case "5":
                            tables.add(new FoodTable(i * 50,  y * 50, 50, 50, 1));
                             break;
                        case "6":
                            tables.add(new FoodTable(i * 50, y * 50, 50, 50, 2));
                            break;
                        case "7":
                            tables.add(new FoodTable(i * 50,  y * 50, 50, 50, 3));
                            break;
                        case "8":
                            tables.add(new FoodTable(i * 50,  y * 50, 50, 50, 4));
                            break;
                    }
                }
                y++;
            }
            br.close();
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        }
    }
    
    
    public ArrayList<Table> getTable(){
        return tables;
    }

    public void paint(Graphics g, int x, int y) {
        // for會非同步, foreach是同步

        for (Table tables : tables) {
            tables.paint(g);
        }

//        for (Knife knife : knives) {
//            knife.paint(g, x, y);
//        }
//        for (Star star : stars) {
//            star.paint(g, x, y);
//        }
//        exitSigns.get(0).paint(g, x, y);
//        System.out.println("Star: " + stars.size());
    }
}
