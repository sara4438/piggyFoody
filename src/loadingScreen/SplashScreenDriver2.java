/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package loadingScreen;

/**
 *
 * @author dingding
 */
public class SplashScreenDriver2 {
    private SplashScreen screen;
    
    public SplashScreenDriver2() {
        screen = new SplashScreen();
        screen.setLocationRelativeTo(null);
        screen.setMaxProgress(1000);
        screen.setVisible(true);
        
        for(int i = 0; i <= 1000; i++){
            for(int j = 0; j <= 50000; j++){
                String t = "ewf" + (i + j);
            }
            screen.setProgress(i);
        }
        
        screen.setVisible(false);
    }
}
