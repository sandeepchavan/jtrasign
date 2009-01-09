/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtraffic.lib;

/**
 *
 * @author JuanmaSP
 * @author DavidSAC
 * @date 2008-12-30
 */
public class Posicion {
    public int x;
    public int y;

    public Posicion(int x, int y){
        this.x = x;
        this.y = y;
    }

    public String toString(){
        return "(" + x + "," + y + ")";
    }
}
