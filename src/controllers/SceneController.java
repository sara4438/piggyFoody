/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import io.CommandSolver.CommandWrapper;
import java.awt.Graphics;
import scene.Scene;

/**
 *
 * @author dingding
 */
public class SceneController {

    private Scene currentScene;
    public void changeScene(Scene scene) {
        if (currentScene != null) {
            currentScene.sceneEnd();
        }
        currentScene = scene;
        currentScene.sceneBegin(); 
    }
 
    public void sceneUpdate(CommandWrapper commands) {
        if (commands != null) {
            commands.actionCommand(currentScene.getKeyCommandListener());
            commands.actionCommand(currentScene.getMouseCommandListener());
            commands.actionCommand(currentScene.getTypedListener()); 
        }
        currentScene.sceneUpdate();
    }

    public void paint(Graphics g) {
        currentScene.paint(g);
    }
}
