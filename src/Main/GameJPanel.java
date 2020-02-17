/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import controllers.SceneController;
import javax.swing.*;
import io.CommandSolver.CommandWrapper;
import java.awt.*;
import scene.menu.*;
import scene.ranking.PopUpWindow;
import scene.ranking.RankingScene;

/**
 *
 * @author dingding
 */
public class GameJPanel extends JPanel {

    private SceneController sceneController;

    public GameJPanel() {
        sceneController = new SceneController();
     
        sceneController.changeScene(new MenuScene(sceneController));
    }

    public void update(CommandWrapper commands) {
        sceneController.sceneUpdate(commands);

    }

    @Override
    public void paintComponent(Graphics g) {
        sceneController.paint(g);
        g.drawString("", 0, 0);

    }
}
