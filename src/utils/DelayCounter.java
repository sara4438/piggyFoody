/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

/**
 *
 * @author dingding
 */
public class DelayCounter {

    //在裡面做一個actionperform
    //就是timer
//    public interface Action {
//
//        public void action();//配合我們的delay去幫他執行某件事情！
//    } 
    private int delay;//要延遲的時間
    private int count;//計算延遲
  

    public DelayCounter(int delay) {
        //把這邊的delay變成算完之後的
        this.delay = delay * Global.ANIME_DELAY;//讓他可以設定要延遲多久為一個週期
        this.count = 0;//用count來幫你算這個延遲的時間
        // this.action = action;
    }
    public boolean update() {
        if (count++ < delay) {
            return false;
        }
        count = 0;
        return true;
    }

}
