/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Main.GameState;
import gameobject.*;
import gameobject.actor.*;
import gameobject.food.Food;
import gameobject.table.*;
import java.awt.Graphics;
import java.io.*;
import java.util.ArrayList;
import audio.AudioA;

/**
 *
 * @author sara
 */
public class WorkControl {

    private ArrayList<Food> floorFood;
    private ArrayList<Table> allTables;
    private ArrayList<TableLeg> tablelegs;
    private Actor actor;
    private Actor actor2;
    private OrderContainer orderContainer;
    private GameState gameState;
    private AudioA.PressedMusic audio;

    public WorkControl(int level, int pickActor) {
        allTables = new ArrayList<Table>();
        floorFood = new ArrayList<Food>();
        tablelegs = new ArrayList<TableLeg>();
        audio = new AudioA.PressedMusic();

        switch (level) {
            case 0:
                getGameObject("Level1");
                audio.getThemeOne().loop();
                orderContainer = new OrderContainer(20, 5, 460, 160, level); //最後一個參數是level幾
                gameState = GameState.getGameState();
                switch (pickActor) {
                    case 0:
                        actor = new Pig(500, 400, 50, 50, pickActor);
                        break;
                    case 1:
                        actor = new Rabbit(500, 400, 50, 50, pickActor);
                        break;
                    case 2:
                        actor = new Turtle(500, 400, 50, 50, pickActor);
                }
                actor.setPlayerNum(1);
                break;
            case 1:
                getGameObject("Level2");
                audio.getThemeTwo().loop();
                orderContainer = new OrderContainer(20, 5, 460, 160, level); //最後一個參數是level幾
                gameState = GameState.getGameState();

                switch (pickActor) {
                    case 0:
                        actor = new Pig(700, 500, 50, 50, pickActor);
                        break;
                    case 1:
                        actor = new Rabbit(700, 500, 50, 50, pickActor);
                        break;
                    case 2:
                        actor = new Turtle(700, 500, 50, 50, pickActor);
                }
                actor.setPlayerNum(1);
                break;
            case 2:
                getGameObject("Level3");
                audio.getThemeThree().loop();
                orderContainer = new OrderContainer(20, 5, 460, 160, level); //最後一個參數是level幾
                gameState = GameState.getGameState();
                switch (pickActor) {
                    case 0:
                        actor = new Pig(450, 500, 50, 50, pickActor);
                        break;
                    case 1:
                        actor = new Rabbit(450, 500, 50, 50, pickActor);
                        break;
                    case 2:
                        actor = new Turtle(450, 500, 50, 50, pickActor);
                }
                actor.setPlayerNum(1);
                break;
        }
    }

    public WorkControl(int level, int actorOne, int actorTwo) {
        allTables = new ArrayList<Table>();
        floorFood = new ArrayList<Food>();
        tablelegs = new ArrayList<TableLeg>();
        audio = new AudioA.PressedMusic();

        switch (actorOne) {
            case 0:
                actor = new Pig(650, 400, 50, 50, actorOne);
                break;
            case 1:
                actor = new Rabbit(600, 300, 50, 50, actorOne);
                break;
            case 2:
                actor = new Turtle(650, 400, 50, 50, actorOne);
                break;
        }
        actor.setPlayerNum(1);
        switch (actorTwo) {
            case 0:
                actor2 = new Pig(300, 400, 50, 50, actorTwo);
                break;
            case 1:
                actor2 = new Rabbit(300, 400, 50, 50, actorTwo);
                break;
            case 2:
                actor2 = new Turtle(300, 400, 50, 50, actorTwo);
                break;
        }
        actor2.setPlayerNum(2);

        switch (level) {
            case 0:
                getGameObject("Level1");
                audio.getThemeOne().loop();
                orderContainer = new OrderContainer(20, 5, 460, 160, level);
                gameState = GameState.getGameState();

                break;
            case 1:
                getGameObject("Level2");
                audio.getThemeTwo().loop();
                orderContainer = new OrderContainer(20, 5, 460, 160, level);
                gameState = GameState.getGameState();
                break;
            case 2:
                getGameObject("Level3");
                audio.getThemeThree().loop();
                orderContainer = new OrderContainer(20, 5, 460, 160, level);
                gameState = GameState.getGameState();
                break;
        }
    }

    public Actor getActor() {
        return actor;
    }

    public Actor getActor2() {
        return actor2;
    }

    public OrderContainer getOrderContainer() {
        return this.orderContainer;
    }

    public void getGameObject(String fileName) {
        try {
            BufferedReader br = new BufferedReader(new FileReader(fileName + ".txt"));
            int y = 0;
            while (br.ready()) {
                String[] strs = br.readLine().split("");
                for (int i = 0; i < strs.length; i++) {
                    switch (strs[i]) {
                        case "a": //桌腳
                            tablelegs.add(new TableLeg(i * 50, y * 50, 50, 50, 40));
                            break;
                        case "1"://咖啡普通桌
                            allTables.add(new Table(i * 50, y * 50, 50, 50, 36));
                            break;
                        case "2"://垃圾桶
                            allTables.add(new TrashTable(i * 50, y * 50, 50, 50, 38));
                            break;
                        case "3"://出餐口
                            allTables.add(new ScoreTable(i * 50, y * 50, 50, 50, 37));
                            break;
                        case "0": //切菜桌
                            allTables.add(new ChoppingTable(i * 50, y * 50, 50, 50, 39));
                            break;
                        case "4":
                            allTables.add(new FoodTable(i * 50, y * 50, 50, 50, 0));
                            break;
                        case "5":
                            allTables.add(new FoodTable(i * 50, y * 50, 50, 50, 1));
                            break;
                        case "6":
                            allTables.add(new FoodTable(i * 50, y * 50, 50, 50, 2));
                            break;
                        case "7":
                            allTables.add(new FoodTable(i * 50, y * 50, 50, 50, 3));
                            break;
                        case "8":
                            allTables.add(new FoodTable(i * 50, y * 50, 50, 50, 4));
                            break;
                        case "9":
                            allTables.add(new FoodTable(i * 50, y * 50, 50, 50, 12));
                            break;
                        case "b":
                            allTables.add(new FoodTable(i * 50, y * 50, 50, 50, 13));
                            break;
                        case "c":
                            allTables.add(new FoodTable(i * 50, y * 50, 50, 50, 14));
                            break;
                        case "d":
                            allTables.add(new FoodTable(i * 50, y * 50, 50, 50, 15));
                            break;
                        case "e":
                            allTables.add(new FoodTable(i * 50, y * 50, 50, 50, 16));
                            break;
                        case "f":
                            allTables.add(new FoodTable(i * 50, y * 50, 50, 50, 18));
                            break;
                        case "g":
                            allTables.add(new FoodTable(i * 50, y * 50, 50, 50, 24));
                            break;
                        case "h":
                            allTables.add(new FoodTable(i * 50, y * 50, 50, 50, 25));
                            break;
                        case "i":
                            allTables.add(new FoodTable(i * 50, y * 50, 50, 50, 26));
                            break;
                        case "j":
                            allTables.add(new FoodTable(i * 50, y * 50, 50, 50, 28));
                            break;
                        case "k":
                            allTables.add(new FoodTable(i * 50, y * 50, 50, 50, 30));
                            break;
                        case "l":
                            allTables.add(new FoodTable(i * 50, y * 50, 50, 50, 32));
                            break;
                        case "m":
                            allTables.add(new FoodTable(i * 50, y * 50, 50, 50, 34));
                            break;
                        case "o": //送餐桌腳
                            tablelegs.add(new TableLeg(i * 50, y * 50, 50, 50, 43));
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

    public ArrayList<Table> getAllTables() {
        return allTables;
    }

    public ArrayList<Food> getFloorObjs() {
        return floorFood;
    }

    public void paint(Graphics g) {
        for (int i = 0; i < allTables.size(); i++) {
            allTables.get(i).paint(g);
        }
        for (int i = 0; i < floorFood.size(); i++) {
            floorFood.get(i).paint(g);
        }
        for (int i = 0; i < tablelegs.size(); i++) {
            tablelegs.get(i).paint(g);
        }
        orderContainer.paint(g);
        actor.paint(g);
        if (actor2 != null) {
            actor2.paint(g);
        }
    }
}
