/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audio;

import controllers.AudioResourceController;
import controllers.PathBuilder;

import java.applet.AudioClip;
import java.util.ArrayList;
import values.AudioPath;
import utils.Global;

/**
 *
 * @author dingding
 */
public class AudioA {

    private static final AudioResourceController arc = AudioResourceController.getInstance();

    public static class PressedMusic {

        private final AudioClip pressedTrash;
        private final AudioClip getPoint;
        private final AudioClip eat;
        private final AudioClip cut;
         private final AudioClip theme1;
        private final AudioClip theme2;
         private final AudioClip theme3;

        public PressedMusic() {

            pressedTrash = arc.tryGetAudio(PathBuilder.getAudio(AudioPath.AUDIO.Audio.TRASH));
            getPoint = arc.tryGetAudio(PathBuilder.getAudio(AudioPath.AUDIO.Audio.POINT));
            eat = arc.tryGetAudio(PathBuilder.getAudio(AudioPath.AUDIO.Audio.EAT));
            cut = arc.tryGetAudio(PathBuilder.getAudio(AudioPath.AUDIO.Audio.CUT));
            theme2 = arc.tryGetAudio(PathBuilder.getAudio(AudioPath.AUDIO.Audio.THEME2));
            theme1 = arc.tryGetAudio(PathBuilder.getAudio(AudioPath.AUDIO.Audio.THEME1));
            theme3 = arc.tryGetAudio(PathBuilder.getAudio(AudioPath.AUDIO.Audio.THEME3));

            //pressedF = arc.tryGetAudio(PathBuilder.getAudio(AudioPath.PressedMusic.PIANO_4));
            //meow = arc.tryGetAudio(PathBuilder.getAudio(AudioPath.PressedMusic.MEOW));
        }

        public AudioClip getPressedTrash() {
            return pressedTrash;
        }

        public AudioClip getPoint() {
            return getPoint;
        }

        public AudioClip getEat() {
            return eat;
        }

        public AudioClip getCut() {
            return cut;
        }

        public AudioClip getThemeTwo() {
            return theme2;
        }
        public AudioClip getThemeOne() {
            return theme1;
        }
        public AudioClip getThemeThree() {
            return theme3;
        }
//        public AudioClip getPressedS() {
//            return pressedS;
//        }
//        
//        public AudioClip getPressedD() {
//            return pressedD;
//        }
//        
//        public AudioClip getPressedF() {
//            return pressedF;
//        }
//        
//        public AudioClip getMeow() {
//            return meow;
//        }
//        public void play(Actor actor){
//            actor.takeObj(null, null);
//        }
//        public void play(int commandCode) {
//            switch (commandCode) {
//                case Global.PROCESS:   
//                    
//                    pressedTrash.play(); 
//                    
//                    break; 
//                //  case Global.S:
//               //  pressedS.play();
//               //  break;
//               //  case Global.D:
//               // pressedD.play();
//               //  break;
//               //  case Global.F:
//               //                    pressedF.play();
//               //                    break;
//            }
//        }
    }
//    public static class BackGroundMusic{
//        private final AudioClip piano;
//        private final AudioClip test;
//        
//        
//        public BackGroundMusic(){
//            /* 無法讀太大的檔案 */
//            piano = arc.tryGetAudio(PathBuilder.getAudio(AudioPath..PIANO));
//            test = arc.tryGetAudio(PathBuilder.getAudio(AudioPath..TEST));
//        }
//        public void play(int sceneNumber){
//            switch(sceneNumber){
//                case 1:
//                    piano.loop();
//                    break;
//            }
//        }
//        
//        public void stop(){
//            piano.stop();
//        }
//    }
}
