/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtraffic.lib;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author JuanmaSP
 * @author DavidSAC
 */
public class Normalizacion {
    
    public static BufferedImage normalizar(BufferedImage imagen, int rangoMin, int rangoMax){
        if(imagen == null || imagen.getType() != BufferedImage.TYPE_BYTE_GRAY)
            return null;

        int min = minimo(imagen);
        int max = maximo(imagen);

        Raster rOrig = imagen.getData();
        int maxX = rOrig.getWidth();
        int maxY = rOrig.getHeight();

        int dif = max - min;
        int resta = min - rangoMin;
        int mult = rangoMax / dif;

        BufferedImage res = new BufferedImage(imagen.getWidth(), imagen.getHeight(), imagen.getType());
        WritableRaster wrRes = res.getRaster();
        for(int x=imagen.getMinX(); x < maxX; x++){
            for(int y = imagen.getMinY(); y < maxY; y++){
                int pixel[] = null;
                pixel = rOrig.getPixel(x, y, pixel);

                int nuevo = (pixel[0] - resta) * mult;

                wrRes.setPixel(x, y, new int[]{nuevo});
            }
        }


        return res;
    }

    /**
     * Devuelve el menor valor de un pixel contenido en la imagen
     * @param imagen
     * @return
     */
    private static int minimo(BufferedImage imagen){
        int min = Integer.MAX_VALUE;

        Raster rOrig = imagen.getData();
        int maxX = rOrig.getWidth();
        int maxY = rOrig.getHeight();

        //Buscamos el nivel de gris mínimo y máximo en la imagen
        for(int x=imagen.getMinX(); x < maxX; x++){
            for(int y = imagen.getMinY(); y < maxY; y++){
                int pixel[] = null;
                pixel = rOrig.getPixel(x, y, pixel);
                if(pixel[0] < min)
                    min = pixel[0];
            }
        }

        return min;
    }

    /**
     * Devuelve el mayor valor de un pixel contenido en la imagen.
     * @param imagen
     * @return
     */
    private static int maximo(BufferedImage imagen){
        int max = Integer.MIN_VALUE;

        Raster rOrig = imagen.getData();
        int maxX = rOrig.getWidth();
        int maxY = rOrig.getHeight();

        //Buscamos el nivel de gris mínimo y máximo en la imagen
        for(int x=imagen.getMinX(); x < maxX; x++){
            for(int y = imagen.getMinY(); y < maxY; y++){
                int pixel[] = null;
                pixel = rOrig.getPixel(x, y, pixel);
                if(pixel[0] > max)
                    max = pixel[0];
            }
        }

        return max;
    }

    /**
     * Busca los máximos locales en una imagen, devuelve sus valores
     * @param imagen
     * @return
     */
    public static List<Integer> maximosLocales(BufferedImage imagen){
        List<Integer> maximosLocales = new LinkedList<Integer>();

        Raster rOrig = imagen.getData();
        int maxX = rOrig.getWidth();
        int maxY = rOrig.getHeight();

        //Buscamos el nivel de gris mínimo y máximo en la imagen
        for(int x=imagen.getMinX(); x < maxX; x++){
            for(int y = imagen.getMinY(); y < maxY; y++){
                int pixel[] = null;
                int pixelAux[] = null;
                pixel = rOrig.getPixel(x, y, pixel);

                boolean mayor = true;

                //Condiciones para detectar si estamos en el borde de la imagen o no.
                if(x > imagen.getMinX()){
                    if(y > imagen.getMinY()){
                        pixelAux = rOrig.getPixel(x - 1, y - 1, pixelAux);
                        if(pixelAux[0] >= pixel[0])
                           mayor = false;
                    }

                    pixelAux = rOrig.getPixel(x - 1, y, pixelAux);
                    if(pixelAux[0] >= pixel[0])
                           mayor = false;

                    if(y < maxY - 1){
                        pixelAux = rOrig.getPixel(x - 1, y + 1, pixelAux);
                        if(pixelAux[0] >= pixel[0])
                           mayor = false;
                    }
                }

                if(y > imagen.getMinY()){
                    pixelAux = rOrig.getPixel(x, y - 1, pixelAux);
                        if(pixelAux[0] >= pixel[0])
                           mayor = false;
                }

                if(y < maxY - 1){
                    pixelAux = rOrig.getPixel(x, y + 1, pixelAux);
                    if(pixelAux[0] >= pixel[0])
                           mayor = false;
                }

                if(x < maxX - 1){
                    if(y > imagen.getMinY()){
                        pixelAux = rOrig.getPixel(x + 1, y - 1, pixelAux);
                        if(pixelAux[0] >= pixel[0])
                           mayor = false;
                    }

                    pixelAux = rOrig.getPixel(x + 1, y, pixelAux);
                    if(pixelAux[0] >= pixel[0])
                           mayor = false;

                    if(y < maxY - 1){
                        pixelAux = rOrig.getPixel(x + 1, y + 1, pixelAux);
                        if(pixelAux[0] >= pixel[0])
                           mayor = false;
                    }
                }

                //Si es el máximo de los de su alrededor, lo guardamos.
                if(mayor){
                    maximosLocales.add(pixel[0]);
                    //System.out.println("Maximo local encontrado: (" + x + "," + y + "): " + pixel[0]);
                }
            }
        }

        return maximosLocales;
    }
}
