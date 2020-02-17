/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package scene.ranking;

import static Main.Main.f;
import controllers.ImageResourceController;
import controllers.PathBuilder;
import io.CommandSolver;
import io.CommandSolver.TypedListener;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Stack;
import values.ImagePath;

/**
 *
 * @author dingding
 */
public class PopUpWindow {

    private TypedListener typedListener;
    private Stack<String> nameArray;
    //private ArrayList<String> nameArray;
    private ArrayList<ScoreSystem> scoreSystems;
    // private ScoreSystem scoreSystem;
    //private DrawCenterText centerText;
    private ScoreSystem scoreRecord;
    private int currentLevel;
    private int currentScore;
    private String name;
    private boolean isFinished;
    private BufferedImage img;
    private int playerNum;

    public PopUpWindow() {
        scoreSystems = new ArrayList<>();
        scoreSystems = readScoreSystems();
        //writeScoreSystem(scoreSystems);
    }

    public PopUpWindow(int level, int score, int playerNum) {
        nameArray = new Stack<>();
//        nameArray = new ArrayList<>();
        scoreSystems = new ArrayList<ScoreSystem>();
        currentLevel = level;
        currentScore = score;
        name = "";
        isFinished = false;
        this.playerNum = playerNum;
//        ImageResourceController irc = ImageResourceController.getInstance();
//        img = irc.tryGetImage(PathBuilder.getImg(ImagePath.BG_POPUP));
        typedListener = new TypedListener() {
            @Override
            public void keyTyped(char c, long trigTime) {
                if (c == KeyEvent.VK_BACK_SPACE) {
                    if (!nameArray.empty()) {
                        nameArray.pop();
                    }
//                    if (nameArray == null) {
//                        nameArray = new Stack<>();
//                    }
                    //nameArray.remove(nameArray.size() - 1); 
                } else if (c == KeyEvent.VK_ENTER) {
                    if (name.equals("")) {
                        return;
                    }
                    saveFiles();

                    isFinished = true;

                } else {

                    if ((c >= 48 && c <= 57) || (c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {

                        nameArray.add(String.valueOf(c));
                    }
                }
            }
        };
    }

    public boolean getIsFinished() {

        return isFinished;

    }

    public void update() {
        if (nameArray != null) {
            name = "";
            for (int i = 0; i < nameArray.size(); i++) {
                if (i > 10) {
                    return;
                }
                name += nameArray.get(i);

            }
        }
    }

    public void saveFiles() {
        ScoreSystem scoreSystem = new ScoreSystem(name, currentScore, currentLevel);
        scoreSystems = readScoreSystems();
        scoreSystems.add(scoreSystem);
        writeScoreSystem(scoreSystems);
    }

    //寫檔
    public void writeScoreSystem(ArrayList<ScoreSystem> scoreSystems) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter("scoreSystem.txt"));
            for (int i = 0; i < scoreSystems.size(); i++) {//寫入檔案
                bw.write(scoreSystems.get(i).toString());
                bw.newLine();
            }
            bw.flush();
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<ScoreSystem> getScoreSystems() {
        return scoreSystems;
    }

    //讀檔
    public ArrayList<ScoreSystem> readScoreSystems() {
        try {
            BufferedReader br = new BufferedReader(new FileReader("scoreSystem.txt"));
            ArrayList<ScoreSystem> scoreSystemArr = new ArrayList<>();
            while (br.ready()) {
                String data[] = br.readLine().split(",");
                if (data.length < 3) {
                    continue;
                }
                ScoreSystem tmp = new ScoreSystem(data[0], Integer.valueOf(data[1]), Integer.valueOf(data[2]));
                scoreSystemArr.add(tmp);
            }
            br.close();
            return scoreSystemArr;
        } catch (Exception e) {
        }
        return null;
    }

//    public void drawName(Graphics g){
//        
//    }
    public void paint(Graphics g) {

        g.setFont(f);

        g.setColor(new Color(254, 128, 136));
        if (playerNum == 1) {
            g.drawString(name, 440, 610);
        } else {
            g.drawString(name, 440, 630);
        }
        //g.drawString("Please Press Enter",400,650);
        //g.drawString("Please Press Enter", 400, 550);    
    }

    public CommandSolver.TypedListener getTypedListener() {
        return typedListener;
    }
}
