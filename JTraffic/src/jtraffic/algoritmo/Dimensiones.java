/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtraffic.algoritmo;

/**
 *
 * @author JuanmaSP
 */
public class Dimensiones {
    public int width;
    public int height;

    public Dimensiones(int w, int h){
        width = w;
        height = h;
    }

    public String toString(){
        return width + "x" + height;
    }

    public boolean equals(Object o){
        if(!(o instanceof Dimensiones))
            return false;
        Dimensiones dim = (Dimensiones) o;
        return dim.width == this.width && dim.height == this.height;
    }
}
