/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtraffic.algoritmo;

import java.util.LinkedList;
import java.util.List;

/**
 * Objeto de configuración del algoritmo.
 *
 * @author JuanmaSP
 * @author DavidSAC
 * @date 2009-01-11
 */
public class ConfigAlgoritmo {
    public static List<Dimensiones> dimensionesPermitidas(){
        List<Dimensiones> dims = new LinkedList<Dimensiones>();
        dims.add(new Dimensiones(640, 480));
        dims.add(new Dimensiones(320, 240));
        dims.add(new Dimensiones(160, 120));
        return dims;
    }

    public int niveles_piramide = 7;
    public Dimensiones dimensionesAlg = new Dimensiones(640, 480);
    public int dimFixedWindow = 15;
    public int factorReduccionBusq = 2;
    public double umbralBusq = 10;
    public float pesoBorde = 0.5f;
    public float pesoColor = 0.5f;

    @Override
    public String toString(){
        String res = "ConfigAlgoritmo[niveles_piramide: " + niveles_piramide
                + ", dimensionesAlg: " + dimensionesAlg
                + ", dimFixedWindow: " + dimFixedWindow
                + ", factorReduccionBusq: " + factorReduccionBusq
                + ", umbralBusq: " + umbralBusq
                + ", pesoBorde: " + pesoBorde
                + ", pesoColor: " + pesoColor + "]";
        return res;
    }
}

