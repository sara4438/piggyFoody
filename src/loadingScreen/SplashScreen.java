/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loadingScreen;

import controllers.ImageResourceController;
import controllers.PathBuilder;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.image.BufferedImage;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JWindow;
import javax.swing.SwingUtilities;
import values.ImagePath;

/**
 *
 * @author ann
 */
public class SplashScreen extends JWindow{
    private BorderLayout borderLayout;
    private JLabel imgLabel;
    private JPanel southPanel;
    private FlowLayout southFolw; 
    private JProgressBar progressBar;
    private ImageIcon imgIcon;
     
    public SplashScreen(){ 
        ImageResourceController irc = ImageResourceController.getInstance();
        BufferedImage img = irc.tryGetImage(PathBuilder.getImg(ImagePath.GUI.Gui.LOGO));        
        this.imgIcon = new ImageIcon(img);
        borderLayout = new BorderLayout();
        imgLabel = new JLabel();
        southPanel = new JPanel();
        southFolw = new FlowLayout();
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        try{
            init();
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
    private void init() throws Exception{
        imgLabel.setIcon(imgIcon);
        getContentPane().setLayout(borderLayout);
        southPanel.setLayout(southFolw);
        southPanel.setBackground(Color.BLACK);
        getContentPane().add(imgLabel, BorderLayout.CENTER);
        getContentPane().add(southPanel, BorderLayout.SOUTH);
        southPanel.add(progressBar, null);
        pack();
    }
    
    public void setMaxProgress(int maxProgress){
        progressBar.setMaximum(maxProgress);
    }
    
    public void setProgress(int progress){
        int percentage = (int)(((float)progress / (float)progressBar.getMaximum()) * 100);
        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                progressBar.setValue(progress);
                progressBar.setString("Loading: " + percentage + "%");
                
            }
        });
        
    }   
}

