/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtraffic.lib.filtros;

import java.awt.image.BufferedImage;

/**
 *
 * @author juanmasp
 */
public class FiltroSobel {
    /** Filtro de Sobel aplicado en el eje x hacia abajo. */
    public static final int Sx_Aba = 0;
    /** Filtro de Sobel aplicado en el eje x hacia arriba. */
    public static final int Sx_Arr = 1;
    /** Filtro de Sobel aplicado en el eje y hacia la izquierda. */
    public static final int Sy_Izq = 2;
    /** Filtro de Sobel aplicado en el eje y hacia la derecha. */
    public static final int Sy_Der = 3;

    /**
     * Las matrices de convolución de los distintos tipos de
     * filtros de Sobel.
     */
    private final static float filtros[][][] = {
        //Sx_Aba
        {{-1, -2, -1}, {0, 0, 0}, {1, 2, 1}},
        //Sx_Arr
        {{1, 2, 1}, {0, 0, 0}, {-1, -2, -1}},
        //Sy_Der
        {{-1, 0, 1}, {-2, 0, 2}, {-1, 0, 1}},
        //Sy_Izq
        {{1, 0, -1}, {2, 0, -2}, {1, 0, -1}}
    };

    /**
     * Aplica el filtro de sobel  a una imagen.
     *
     * @param imagen sobre la que se desa aplicar el filtro
     * @param tipo de filtro de Sobel que se puede aplicar.<br/> Puede
     * valer Sx_Der, Sx_Izq, Sy_Aba o Sy_Arr
     * @param tratBordes
     * @return la imagen resultante de aplicar el filtro de sobel o null en caso de fallo.
     */
    public static BufferedImage aplicar(BufferedImage imagen, int tipo, int tratBordes){
        BufferedImage res = null;

        if(tipo < 0 || tipo > 3)
            return null;

        res = Convolucion.aplicar(filtros[tipo], imagen, tratBordes);

        return res;
    }
}
