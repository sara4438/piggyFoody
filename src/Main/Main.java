/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import controllers.MusicController;
import controllers.PathBuilder;  
import io.CommandSolver;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.*;
import loadingScreen.SplashScreenDriver2;
import utils.Global;
import values.AudioPath;

/**
 *
 * @author dingding
 */
public class Main {

    public static Font f = new Font("Arial", Font.BOLD, 25);//Calabri
    public static Font f1 = new Font("Arial", Font.BOLD, 30);//Calabri
    public static Font f2 = new Font("Arial", Font.BOLD, 20);
    public static Font f3 = new Font("Arial", Font.BOLD, 36);

    //public static Font f4 = new Font("Arial", Font.BOLD, 25);

    /**
     * Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        

//        MusicController bgm;
//        bgm = new MusicController(PathBuilder.getAudio(AudioPath.AUDIO.Audio.THEME));
//        bgm.play(); 
        JFrame j = new JFrame();
        j.setTitle("Test Title");
        j.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        j.setSize(Global.WINDOW_WIDTH, Global.WINDOW_HEIGHT);
        GameJPanel jp = new GameJPanel();
        j.add(jp);
        SplashScreenDriver2 splash = new SplashScreenDriver2();

        CommandSolver cs = new CommandSolver.Builder(Global.MILLISEC_PER_UPDATE * 2, new int[][]{
            {KeyEvent.VK_UP, Global.UP},
            {KeyEvent.VK_LEFT, Global.LEFT},
            {KeyEvent.VK_DOWN, Global.DOWN},
            {KeyEvent.VK_RIGHT, Global.RIGHT},
            {KeyEvent.VK_SPACE, Global.TAKE},
            {KeyEvent.VK_M, Global.PROCESS},
            {KeyEvent.VK_ENTER, Global.MENU_ENTER},
            {KeyEvent.VK_Z, Global.SELECT},
            {KeyEvent.VK_W, Global.UP2},
            {KeyEvent.VK_A, Global.LEFT2},
            {KeyEvent.VK_S, Global.DOWN2},
            {KeyEvent.VK_D, Global.RIGHT2},
            {KeyEvent.VK_1, Global.TAKE2},
            {KeyEvent.VK_2, Global.PROCESS2},
            {KeyEvent.VK_R, Global.RETURN},
            {KeyEvent.VK_H, Global.HOME},
            {KeyEvent.VK_Y, Global.Y},
            {KeyEvent.VK_N, Global.N},
            {KeyEvent.VK_CONTROL, Global.CONTORL}
        }).enableMouseTrack(jp).keyCleanMode().keyTypedMode().trackChar().gen();
        j.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                cs.trig(e.getKeyCode(), true);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                cs.trig(e.getKeyCode(), false);
            }
        });
        j.setFocusable(true);
        cs.start();
        j.setVisible(true);

        long startTime = System.currentTimeMillis();
        long lastRepaintTime = System.currentTimeMillis();
        long passedFrames = 0;
        while (true) {
            long currentTime = System.currentTimeMillis();
            long totalTime = currentTime - startTime;
            long targetTotalFrames = totalTime / Global.MILLISEC_PER_UPDATE;
            // input
            // input end
            while (passedFrames < targetTotalFrames) {
                jp.update(cs.update());
                passedFrames++;
            }

            if (Global.LIMIT_DELTA_TIME <= currentTime - lastRepaintTime) {
                lastRepaintTime = currentTime;
                j.repaint();
            }
        }
    }

}
