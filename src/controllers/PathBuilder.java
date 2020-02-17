/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

/**
 *
 * @author dingding
 */
public class PathBuilder {
    public static final String ROOT = "/resources";//應該拆到另外一個value類中
    public static final String IMG_ROOT = "/imgs";

    //方便起見先建立在這
    //PathController.getImg(ImageResourcePath.AIRPLANE);
    public static String getImg(String path) { //再把後面那段傳進來 串接起來
        return ROOT + IMG_ROOT + path;//根目錄長這樣

    }

    public static final String AUDIO_ROOT = "/audio";

    public static String getAudio(String path) {
        return ROOT + AUDIO_ROOT + path;
    }
    
}
