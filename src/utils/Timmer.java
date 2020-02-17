/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.awt.Graphics;

/**
 *
 * @author dingding
 */
public class Timmer {

    private long startTime;
    private long totalMilliSecond;
    private int targetSecond;
    private long currentTime;
    private int totalSecond;

    private int secondDigits;
    private int secondTenDigits;
    private int minuteDigits;
    private int minuteTenDigits;

    private int remindTime;

    private int x;
    private int y;
    private int width;
    private int height;

    public Timmer(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public Timmer(int x, int y, int width, int height, int targetSecond) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.targetSecond = targetSecond;
    }

    public void timeStart() {
        startTime = System.currentTimeMillis();
    }

    public void timeUpDate() {
        currentTime = System.currentTimeMillis() - this.startTime;
        remindTime = (int) (targetSecond * 1000 - currentTime);//
        convertTime();
    }

    public void timeStop() {
        totalMilliSecond = currentTime - startTime;
        totalSecond = (int) (totalMilliSecond / 1000);
        System.out.println(totalSecond);
    }

//    private void convertTime() {
//        secondDigits = (int) (currentTime / 1000) % 60 % 10;
//        secondTenDigits = (int) (currentTime / 1000) % 60 / 10;
//
//        minuteDigits = (int) (currentTime / 1000) / 60 % 10;
//        minuteTenDigits = (int) (currentTime / 1000) / 60 / 10;
//    }
    
    private void convertTime() { 
        secondDigits = (int) (remindTime / 1000) % 60 % 10;
        secondTenDigits = (int) (remindTime / 1000) % 60 / 10;

        minuteDigits = (int) (remindTime / 1000) / 60 % 10;
        minuteTenDigits = (int) (remindTime / 1000) / 60 / 10;
    }

    public int getTotalSecond() {
        return totalSecond;
    }

    public boolean isTimeUp() {
        return currentTime / 1000 >= targetSecond;
    }

    public void paint(Graphics g) {
        g.drawString("" + minuteTenDigits, x - 50, y);
        //g.drawString("" + (targetSecond/60 -minuteDigits), x - 50, y);
        g.drawString("" + (minuteDigits), x - 25, y);

        g.drawString(":", x, y);

        g.drawString("" + (secondTenDigits), x + 25, y);

        g.drawString("" + (secondDigits), x + 50, y);
    }
//    targetSecond

}
