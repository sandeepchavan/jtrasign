/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtraffic.lib.filtros;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;

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

    public static final int Sx = 4;
    public static final int Sy = 5;

    /**
     * Las matrices de convolución de los distintos tipos de
     * filtros de Sobel.
     */
    private final static float filtros[][][] = {
        //Sx_Aba
        {{-1, -2, -1},
         {0, 0, 0},
         {1, 2, 1}},
        //Sx_Arr
        {{1, 2, 1},
         {0, 0, 0},
         {-1, -2, -1}},
        //Sy_Der
        {{-1, 0, 1},
         {-2, 0, 2},
         {-1, 0, 1}},
        //Sy_Izq
        {{1, 0, -1},
         {2, 0, -2},
         {1, 0, -1}}
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

        if(tipo < 0)
            return null;
        else if(tipo == Sx || tipo == Sy)
                res = aplicarAbs(imagen, tipo, tratBordes);
        else
            res = Convolucion.aplicar(filtros[tipo], imagen, tratBordes);

        return res;
    }

    private static BufferedImage aplicarAbs(BufferedImage imagen, int tipo, int tratBordes){
        BufferedImage res = null;

        int tipo1, tipo2;
        if(tipo == Sx){
            tipo1 = Sx_Aba;
            tipo2 = Sx_Arr;
        }else if(tipo == Sy){
            tipo1 = Sy_Der;
            tipo2 = Sy_Izq;
        }
        else
            return null;

        BufferedImage aux1 = Convolucion.aplicar(filtros[tipo1], imagen, tratBordes);
        Raster rAux1 = aux1.getData();
        BufferedImage aux2 = Convolucion.aplicar(filtros[tipo2], imagen, tratBordes);
        Raster rAux2 = aux2.getData();

        res = new BufferedImage(imagen.getWidth(), imagen.getHeight(), imagen.getType());
        WritableRaster rRes = res.getRaster();

        int maxY = rAux1.getHeight();
        int maxX = rAux1.getWidth();

        for(int y = rAux1.getMinY(); y < maxY; y++){
            for(int x = rAux1.getMinX(); x < maxX; x++){
                int pixel1[] = null;
                pixel1 = rAux1.getPixel(x , y, pixel1);

                int pixel2[] = null;
                pixel2 = rAux2.getPixel(x , y, pixel2);

                rRes.setPixel(x, y, new int[]{(pixel1[0] + pixel2[0])});
            }
        }


        return res;
    }
}
