/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.io.IOException;
import java.text.NumberFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.FloatControl;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;

/**
 *
 * @author asdfg
 */
public class MusicController {
    AudioInputStream audioInputStream;
    private String filename;
    private Thread thread;

    public MusicController(String wavfile) {
            filename = wavfile;
    }

    public void play() {
        if (thread == null) {
            thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    audioInputStream = AudioSystem.getAudioInputStream(getClass().getResource(filename));
                    AudioFormat format = audioInputStream.getFormat();
                    DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);
                    SourceDataLine line = (SourceDataLine) AudioSystem.getLine(info);
                    line.open();
                    line.start();
                    
                    int length = 0;
                    byte[] buffer = new byte[512];
                    while ((length = audioInputStream.read(buffer)) != -1) {
                            NumberFormat numberFormat = NumberFormat.getInstance();
                            numberFormat.setMaximumFractionDigits(2);
                            line.write(buffer, 0, length);
                }
                line.drain();
                line.close();
                audioInputStream.close();
                } catch (UnsupportedAudioFileException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (LineUnavailableException e) {
                    e.printStackTrace();
                }
            }
        });
            thread.start();
        }else {
            try {
                thread.join();
                thread.start();
            } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
