/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;
import controllers.SceneController;
import gameobject.actor.Actor;
import io.CommandSolver;
import java.awt.Graphics;
/**
 *
 * @author dingding
 */
public class TestScene extends Scene{
    private Actor actor;

    public TestScene(SceneController sceneController) {
        super(sceneController);
    }
    
    @Override
    public void sceneBegin() {
        actor = new Actor(30, 30, 96, 96, 5);
    }

    @Override
    public void sceneUpdate() {
        actor.move();
    }

    @Override
    public void sceneEnd() {
        
    }

    @Override
    public void paint(Graphics g) {
        actor.paint(g);
    }
    
}

