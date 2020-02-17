/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gameobject;

import gameobject.food.*;
import gameobject.kitchenware.Plate;
import java.util.ArrayList;
import utils.Global;
/**
 *
 * @author dingding
 */
public class CombineHelper {
    public static NonCuttable combineFood (Food f1, Food f2){     //f1是桌上的, f2是手拿的
        NonCuttable combinedFood = new NonCuttable (f1.getX(), f1.getY(),50, 50, 36,0);
        if(f1.getIsCut() == false || f2.getIsCut() ==false)
            return null;
        for (Food ingredient : f2.getIngredients() ){            
            combinedFood.addIngredient(ingredient);
        }
        for (Food ingredient : f1.getIngredients()){            
            combinedFood.addIngredient(ingredient);
        }
        
        return combinedFood;
    }
}

   
        

    
