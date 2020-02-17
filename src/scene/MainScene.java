/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene;


import gameobject.Button;
import gameobject.Button.ButtonListener;
import gameobject.actor.Actor;
import io.CommandSolver.*;
import java.awt.event.MouseEvent;
import utils.Global;
import controllers.SceneController;
import java.awt.Graphics;

/**
 *
 * @author dingding
 */
public class MainScene extends Scene {

    private KeyCommandListener keyCommandListener;
    private MouseCommandListener mouseCommandListener;
    private Actor actor;
    private Button button1;

    public MainScene(SceneController sceneController) {
        super(sceneController);

        keyCommandListener = new KeyCommandListener() {
            @Override
            public void keyPressed(int commandCode, long time) {
                System.out.println("pressed at: " + time  + " -> " + commandCode);
                switch (commandCode) {
                    case Global.UP:
                    case Global.LEFT:
                    case Global.DOWN:
                    case Global.RIGHT:
                        actor.changeDirection(commandCode);
                        break;
                }
            }

            @Override
            public void keyReleased(int CommandCode, long time) {
            }
        };
        mouseCommandListener = new MouseCommandListener() {
            @Override
            public void mouseTrig(MouseEvent e, MouseState state, long trigTime) {
                if (state == MouseState.CLICKED) {
                    if (button1.isCollision(e.getX(), e.getY())) {
                        button1.click(e.getX(), e.getY());
                    }
                } else if (state == MouseState.MOVED) {
                    if (button1.isCollision(e.getX(), e.getY())) {
                        button1.hover(e.getX(), e.getY());
                    }
                }
            }
        };
    }

    @Override
    public void sceneBegin() {
        actor = new Actor(50, 50, 96, 96, 11);
        button1 = new Button(100, 100, 200, 200,1);
        button1.setButtonListener(new ButtonListener() {
            @Override
            public void onClick(int x, int y) {
                System.out.println("Button Clicked: (" + x + ", " + y + ")");
            }

            @Override
            public void hover(int x, int y) {
                System.out.println("Button Hovered: (" + x + ", " + y + ")");
            }
        }
        );
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
        button1.paint(g);
    }

    public KeyCommandListener getKeyCommandListener() {
        return keyCommandListener;
    }

    public MouseCommandListener getMouseCommandListener() {
        return mouseCommandListener;
    }

}
