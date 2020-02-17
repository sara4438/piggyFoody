/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author dingding
 */
public class Global {

    //這個類負責存我們的常數
    //方便測試用
    public static final int WHICH_LEVEL = 0;  //現在要測第幾關

    //資料刷新的時間
    public static final int UPDATE_TIMES_PER_SEC = 40;
    public static final int MILLISEC_PER_UPDATE = 1000 / UPDATE_TIMES_PER_SEC;
    //畫面更新的時間
    public static final int FRAME_LIMIT = 100;
    public static final int LIMIT_DELTA_TIME = 1000 / FRAME_LIMIT;

    //物件速度計算 
    public static final int ACT_SPEED = 100 / UPDATE_TIMES_PER_SEC;
    public static final int ANIME_DELAY = UPDATE_TIMES_PER_SEC / 40;//30基準會是2 

    //把基準變240
    //30的話每一次動的是8 
    //60的話每一次動的是4
    //畫actor的一些常數 
    //圖片大小制定
    public static final int IMG_X_OFFSET = 32;
    public static final int IMG_Y_OFFSET = 32;
    public static final int IMG_PLATEX_OFFSET = 50;
    public static final int IMG_PLATEY_OFFSET = 50;
    public static final int IMG_SQUARE = 50;
    public static final int INGREDIENTSIZE = 17;
    public static final int INDICATOR_OFFSET = 10;
    public static final int INITIAL_NUM_OF_ORDERS = 3;
    public static final int ORDER_INGREDIENTSIZE = 29;
    public static final int ORDER_ORIGINALPHOTO_WIDTH = 230;
    public static final int ORDER_ORIGINALPHOTO_HEIGHT = 240;

    //上下左右常數制定
    public static final int UP = 3;
    public static final int LEFT = 1;
    public static final int DOWN = 0;
    public static final int RIGHT = 2;
    public static final int TAKE = 4;
    public static final int PROCESS = 5;

    public static final int SELECT = 11;
    public static final int UP2 = 6;
    public static final int LEFT2 = 7;
    public static final int DOWN2 = 8;
    public static final int RIGHT2 = 9;
    public static final int TAKE2 = 10;
    public static final int PROCESS2 = 11;
//    public static final int ABOUTSELECT= 12
    public static final int Y = 66;
    public static final int N = 55;

    public static final int GUI_BUTTON_WIDTH = 100;
    public static final int GUI_BUTTON_HEIGHT = 40;

    public static final int RETURN_X = 760;
    public static final int RETURN_Y = 680;

    public static final int HOME_X = 870;
    public static final int HOME_Y = 680;

    //照片順序是下左右上 
    public static final int WINDOW_WIDTH = 1000;
    public static final int WINDOW_HEIGHT = 760;
    public static final int ORDER_WIDTH = 150;
    public static final int ORDER_HEIGHT = 160;

    //MENU 常數
    public static final int RETURN = 12;
    public static final int HOME = 13;
    public static final int CONTORL = 88;

    //button 常數
//    public static final int MENU_PRESSED_RIGHT = 1;
//    public static final int MENU_PRESSED_LEFT = 2;
//    public static final int MENU_PRESSED_UP = 3;
//    public static final int MENU_PRESSED_DOWN = 4;
    public static final int MENU_ENTER = 15;

    //判斷誤差
    public static final int COLLISIONOFFSET = 10;
    public static final int INGREDIENT_Y = 110;
    //得分相關
    public static final int VICOTRY_REQUIREMENT = 100; 
    public static int RABBIT_EXTRA_SCORE = 6;
    
    

}
