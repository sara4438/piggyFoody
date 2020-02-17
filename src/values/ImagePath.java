/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package values;

/**
 *
 * @author dingding
 */
public class ImagePath {

    public static class TestMenu {

        public static final String Menu = "/Animations";

        public static final String ENEMY = Menu + "/Attack2.png";
        public static final String ENEMY1 = Menu + "/Attack3.png";
        //enemy可以另外開一個類別放
    }

    public static class AirPlane {

        public static final String FOLDER_AIRPLANE = "/airplane";
        public static final String AIRCRAFT = FOLDER_AIRPLANE + "/airplane1.png";
        public static final String BULLET_STATE0 = FOLDER_AIRPLANE + "/boom.png";
        public static final String BULLET_STATE1 = FOLDER_AIRPLANE + "/boom2.png";
        public static final String ENEMY = FOLDER_AIRPLANE + "/enemy1.png";
        //enemy可以另外開一個類別放
    }

    // Character
    public static class Character {

        public static final String FOLDER_CHARACTER = "/Characters";

        public static class Actor {

            public static final String A1 = FOLDER_CHARACTER + "/Actor1.png";
            public static final String A2 = FOLDER_CHARACTER + "/Actor2.png";
            public static final String PIG = FOLDER_CHARACTER + "/pigNew.png";
            public static final String RABBIT = FOLDER_CHARACTER + "/rabbbitNew.png";
            public static final String TURTLE = FOLDER_CHARACTER + "/turtleNew.png";
        }
    }

    //Tilesets
    public static class Tilesets {

        public static final String FOLDER_TOLESETS = "/Tilesets";

        public static class Inside {

            public static final String INSIDE_A2 = FOLDER_TOLESETS + "/Inside_A2.png";
            public static final String INSIDE_A1 = FOLDER_TOLESETS + "/Inside_A1.png";

        }
    }

//    public static class Tilesets{
//        public static final String FOLDER_TILESETS = "/Tilesets";
//        public static final String T2 = FOLDER_TILESETS + "/Inside_A2.png";
//        public static final String T4 = FOLDER_TILESETS + "/Inside_A4.png";
//        }
    public static class Kitchenwares {

        public static final String FOLDER_KITCHENWARES = "/Kitchenwares";
        public static final String K1 = FOLDER_KITCHENWARES + "/plate.png";
        public static final String K2 = FOLDER_KITCHENWARES + "/plate2.png";
        public static final String KNIFE = FOLDER_KITCHENWARES + "/knife.png";
    }

    public static class Food {

        public static final String FOLDER_FOOD = "/Food";
        public static final String S1 = FOLDER_FOOD + "/combine.png";
        public static final String S2 = FOLDER_FOOD + "/kiwi.png";
        public static final String S3 = FOLDER_FOOD + "/flour.png";
        public static final String S4 = FOLDER_FOOD + "/sugar.png";
        public static final String S5 = FOLDER_FOOD + "/test.png";
        public static final String A1 = FOLDER_FOOD + "/A1.png";
        public static final String ORDERS = FOLDER_FOOD + "/ordersNew.png";
        public static final String POT = FOLDER_FOOD + "/pot.png";
    }

    public static class Background {

        public static final String FOLDER_BACKGROUND = "/Background";

        public static class Scene {

            public static final String Background_Menu = FOLDER_BACKGROUND + "/background.png";
            public static final String Background_Actor = FOLDER_BACKGROUND + "/backgroundActor.png";
            public static final String Background_Level = FOLDER_BACKGROUND + "/levelOne.png";

            public static final String Background_level1 = FOLDER_BACKGROUND + "/level1.png";
            public static final String Background_level2 = FOLDER_BACKGROUND + "/level2.png";
            public static final String Background_level3 = FOLDER_BACKGROUND + "/level3.png";

            public static final String Background_Pink = FOLDER_BACKGROUND + "/backgrounddot.png";
            public static final String Background_About = FOLDER_BACKGROUND + "/aboutBackground.png";
            public static final String Background_About2 = FOLDER_BACKGROUND + "/aboutBackground2.png";
            public static final String Background_Actor1 = FOLDER_BACKGROUND + "/backgroundActor1.png";
//            public static final String Background_Five = FOLDER_BACKGROUND + "/sushi.jpg";
//            public static final String Background_Six = FOLDER_BACKGROUND + "/Pink_Star.gif"; 
        }
    }

    public static class Table {

        public static final String FOLDER_TABLE = "/Table";

        public static class Tables {

            public static final String Table_One = FOLDER_TABLE + "/BrownTable.png";
            public static final String Table_Two = FOLDER_TABLE + "/test.jpg";
            public static final String Table_Three = FOLDER_TABLE + "/food.jpg";
            public static final String Table_Four = FOLDER_TABLE + "/pink.jpg";
            public static final String Table_Five = FOLDER_TABLE + "/sushi.jpg";
            public static final String Table_Six = FOLDER_TABLE + "/tables.png";
            public static final String Tables = FOLDER_TABLE + "/tablesNEW.png";
            //public static final String Background_Pink = FOLDER_TABLE + "/Pink_Sar.gif";

        }
    }

    public static class Indicator {

        public static final String FOLDER_INDICATOR = "/Indicator";
        public static final String INDICATORBAR = FOLDER_INDICATOR + "/indicatorBar.png";
        public static final String REDSQUARE = FOLDER_INDICATOR + "/redSquare.png";
    }

    public static class GameScene {

        public static final String FOLDER_GAMESCENE = "/GameScene";

        public static class Gamescene {

            public static final String DOUBLEPOINT = FOLDER_GAMESCENE + "/doublepoint.png";
            public static final String LOSE = FOLDER_GAMESCENE + "/lose.png";
            public static final String WIN = FOLDER_GAMESCENE + "/win.png";
            public static final String WINP1 = FOLDER_GAMESCENE + "/player1.png";
            public static final String WINP2 = FOLDER_GAMESCENE + "/player2.png";
            public static final String WINTIE = FOLDER_GAMESCENE + "/draw.png";
            public static final String SCORE = FOLDER_GAMESCENE + "/score.png";
            public static final String TIME = FOLDER_GAMESCENE + "/time.png";
        }

    }

    public static class GUI {
 
        public static final String FOLDER_GUI = "/GUI";

        public static class Gui {

            public static final String ARROW = FOLDER_GUI + "/forkwhite.png";
            public static final String BUTTON1 = FOLDER_GUI + "/btnNonetrans.png";
            public static final String ACTORBOX = FOLDER_GUI + "/actorBoxOne.png";
            public static final String ACTORBOX1 = FOLDER_GUI + "/ActorRec1.png";
            public static final String ACTORICON = FOLDER_GUI + "/hatIcon.png";
            public static final String CHOSEACTORONE = FOLDER_GUI + "/hatP1.png";
            public static final String CHOSEACTORTWO = FOLDER_GUI + "/hatP2.png";
            public static final String HATRED = FOLDER_GUI + "/hatred.png";
            public static final String PLAYERBTN = FOLDER_GUI + "/playerBtn.png";
            public static final String HOME = FOLDER_GUI + "/home.png";
            public static final String RETURN = FOLDER_GUI + "/return.png";
            public static final String EXIT = FOLDER_GUI + "/exit.png";
            public static final String GAMELEVEL = FOLDER_GUI + "/gameLevel.png";
            public static final String RANKING = FOLDER_GUI + "/ranking.png";
            public static final String POPINFO = FOLDER_GUI + "/window.png";
            public static final String STAGE1 = FOLDER_GUI + "/stage1.png";
            public static final String STAGE2 = FOLDER_GUI + "/stage2.png";
            public static final String STAGE3 = FOLDER_GUI + "/stage3.png";
            
            public static final String STAGE = FOLDER_GUI + "/stage.png"; 
            public static final String STAGESELECT = FOLDER_GUI + "/stageSelected.png"; 
            
            public static final String NAME = FOLDER_GUI +"/name.png";
            
            public static final String STAGE01 = FOLDER_GUI +"/stage01.png";
            public static final String STAGE02 = FOLDER_GUI +"/stage02.png";
            public static final String STAGE03 = FOLDER_GUI +"/stage03.png";
            
            
            public static final String ACTORINFO = FOLDER_GUI+"/ActorInfo.png";
            
            public static final String LOGO = FOLDER_GUI+"/logo2.png";
            
            
            

        }
    }

    public static class Map {

        public static final String FOLDER_MAP = "/Map";

        public static class map {

            public static final String MAP_ONE = FOLDER_MAP + "/mapWhite.jpg";

        }
    }

}
