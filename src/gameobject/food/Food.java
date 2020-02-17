/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject.food;

import controllers.ImageResourceController;
import controllers.PathBuilder;
import gameobject.GameObject;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import utils.Global;
import values.ImagePath;

/**
 *
 * @author sara
 */
public class Food extends GameObject {
        protected FoodHelper foodHelper ;
        protected int foodImg;
        protected ArrayList<Food> ingredients;
        protected boolean isCut ;
        private BufferedImage knifeImg;
        
        
     public Food (int x, int y, int width, int height, int foodImg){
        super ( x,y,width, height);      
        ingredients = new ArrayList<>();
        ingredients.add(this);
        this.foodImg = foodImg;
        this.foodHelper = new FoodHelper(foodImg);
    }
     public Food(int x, int y, int width, int height, int foodImg, int e){
        super ( x,y,width, height);      
        ingredients = new ArrayList<>();
        this.foodImg = foodImg;
        this.foodHelper = new FoodHelper(foodImg);
        if (e == 3){ //加菜刀
             ImageResourceController irc = ImageResourceController.getInstance();
             knifeImg = irc.tryGetImage(PathBuilder.getImg(ImagePath.Kitchenwares.KNIFE));     
        }
    } 
        
     public void isCut(){
         return;
     }
     public boolean getIsCut(){
         return isCut;
     }
        
     public ArrayList<Food> getIngredients(){
        return this.ingredients;
    }
    public void addIngredient(Food ingredient){
        ingredients.add(ingredient);
    }
    public int getX(){
        return x;
    }
    public int getWidth(){
        return width;
    }
    public int getHeight(){
        return height;
    }
    public void setHeight(int height){
        this.height = height;
    }
    public void setWidth(int width){
        this.width = width;
    }
    public int getImageNum(){
        return foodImg;
    }
    public void isHeld(boolean isHeld, int position){
        this.isHeld =  isHeld;
        foodHelper.setFoodPosition(position);
    } 
    public void changeStatus(int foodPosition){
        foodHelper.setFoodPosition(foodPosition);
    }
    public int getY(){
        return y;
    }
    public FoodHelper getFoodHelper(){
        return this.foodHelper;
    }
    public String toString(){
        return "食物"+foodImg;
    }
     
    @Override
    public void paint(Graphics g) {
        if(ingredients.size() < 2 ){
            foodHelper.paint(g, x, y, width, height);
        }else{ 
            foodHelper.paint(g, x, y, width, height);
            for(int i = 0; i< ingredients.size();i++){
                ingredients.get(i).foodHelper.paint(g, this.getX()+Global.INGREDIENTSIZE*i, this.getY()+Global.INGREDIENTSIZE*2, 
                Global.INGREDIENTSIZE, Global.INGREDIENTSIZE);
                }
            } 
        if(knifeImg != null){
            g.drawImage(knifeImg, x+5, y+2*(Global.INGREDIENTSIZE+7), x+Global.INGREDIENTSIZE+10, y+4*(4+Global.INGREDIENTSIZE), 0,0,44,74, null);
        }
    }
  }