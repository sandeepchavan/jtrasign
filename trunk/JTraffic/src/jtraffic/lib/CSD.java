/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtraffic.lib;

import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author JuanmaSP
 * @author DavidSAC
 */
public class CSD {
    /**
     *
     * @param piramide
     * @return
     */
    public static List<BufferedImage> aplicar(List<BufferedImage> piramide){
        List<BufferedImage> res = new LinkedList<BufferedImage>();

        for(int k=2; k<=3; k++){
            for(int j=k+2; j <= k + 3; j++){
                res.add(OperacionesImagenes.restaImagenesConDifNiveles(piramide.get(k), k, piramide.get(j), j));
            }
        }

        return res;
    }
}
