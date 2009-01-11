/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtraffic.algoritmo;

/**
 *
 * @author JuanmaSP
 * @author DavidSAC
 * @date 2008-12-30
 */
public class Posicion {
    public int x;
    public int y;
    public double valor;

    public Posicion(int x, int y, double valor){
        this.x = x;
        this.y = y;
        this.valor = valor;
    }

    @Override
    public String toString(){
        return "(" + x + "," + y + ", valor: " + valor + ")";
    }
}
