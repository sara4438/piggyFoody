/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene.menu;

import controllers.*;
import gameobject.Button;
import io.CommandSolver;
import java.awt.*; 
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import scene.about.AboutScene;
import scene.Scene;
import scene.ranking.PopUpWindow;
import scene.ranking.RankingScene;
import utils.Global;
import values.ImagePath;

/**
 *
 * @author dingding
 */
public class MenuScene extends Scene {

    private CommandSolver.KeyCommandListener keyCommandListener;
    private ArrayList<Button> buttons; 
    private BufferedImage bg;
    private BufferedImage arrow;
    private int arrowCount;
    private PopUpWindow popWindow;

    public MenuScene(SceneController sceneController) {
        super(sceneController);
        buttons = new ArrayList<Button>();
        ImageResourceController irc = ImageResourceController.getInstance();
        bg = irc.tryGetImage(PathBuilder.getImg(ImagePath.Background.Scene.Background_Menu));
        arrow = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.ARROW));
        popWindow = new PopUpWindow(); 
        keyCommandListener = new CommandSolver.KeyCommandListener() {
            @Override 
            public void keyPressed(int commandCode, long time) {
                //選擇東西
                if (commandCode == Global.DOWN) {
                    if (arrowCount != 2) {
                        arrowCount++;
                        //System.out.println(arrowCount);
                    }
                }
                if (commandCode == Global.UP) {
                    if (arrowCount != 0) {
                        arrowCount--;
                        //System.out.println("arrowCount");
                    } 
                }
                //進入關卡
                if (commandCode == Global.MENU_ENTER) {
                    switch (arrowCount) {
                        case 0:
                            sceneController.changeScene(new ModeScene(sceneController)); 
                            break;
                        case 1:
                            sceneController.changeScene(new RankingScene(sceneController,1, popWindow.getScoreSystems()));
                            break;
                        case 2:
                            sceneController.changeScene(new AboutScene(sceneController));
                            break;
                    }
                }
            }

            @Override
            public void keyReleased(int CommandCode, long time) {
            }
        };
    }

    @Override
    public void sceneBegin() {

        int button_width = 270;
        int button_height = 70;
        int button_spacing = 50;
//        for (int i = 0; i < 3; i++) {
//            buttons.add(new Button(Global.WINDOW_WIDTH / 2 - button_width / 2,
//                    252 + i * (button_height + button_spacing), button_width, button_height, i));
//        }
         for (int i = 0; i < 3; i++) {
            buttons.add(new Button(
                   650,
                    370 + i * (button_height + button_spacing), button_width, button_height, i));
        }
    }

    @Override
    public void sceneUpdate() {
    }

    @Override
    public void sceneEnd() {
    }

    @Override
    public void paint(Graphics g) {
        //background 
        g.drawImage(bg, 0, 0, Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT, null);

        for (int i = 0; i < 3; i++) {
            buttons.get(i).paint(g);
        }    
        g.drawImage(arrow, 600, 385 + arrowCount * 120, null);


    }
    public CommandSolver.KeyCommandListener getKeyCommandListener() {
        return keyCommandListener;
    }  

}
