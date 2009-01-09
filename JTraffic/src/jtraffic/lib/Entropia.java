/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtraffic.lib;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;

/**
 *
 * @author JuanmaSP
 */
public class Entropia {
    public static double aplicar(BufferedImage imagen){
        return aplicar(imagen, imagen.getMinX(), imagen.getWidth(), imagen.getMinY(), imagen.getHeight());
    }

    public static double aplicar(BufferedImage imagen, int x1, int y1, int x2, int y2){
        if(imagen == null)
            throw new IllegalArgumentException("El parámetro imagen no puede ser nulo");
        if(imagen.getType() != BufferedImage.TYPE_BYTE_GRAY)
            throw new IllegalArgumentException("Entropia solo puede aplicarse a imágenes en escala de grises(1 byte)");

        Raster r = imagen.getData();

        int maxX = r.getWidth();
        int maxY = r.getHeight();

        //Comprobamos que los datos pedidos son lógicos:
        if(x1 < r.getMinX() ||x1 > maxX)
            throw new IllegalArgumentException("El valor de x1 debe estar comprendido entre minX y maxX");
        if(x2 < r.getMinX() ||x2 > maxX)
            throw new IllegalArgumentException("El valor de x2 debe estar comprendido entre minX y maxX");
        if(x1 >= x2)
            throw new IllegalArgumentException("x2 debe ser mayor que x1");
        if(y1 < r.getMinY() ||y1 > maxY)
            throw new IllegalArgumentException("El valor de y1 debe estar comprendido entre minY y maxY");
        if(y2 < r.getMinX() ||y2 > maxY)
            throw new IllegalArgumentException("El valor de y2 debe estar comprendido entre minY y maxY");
        if(y1 >= y2)
            throw new IllegalArgumentException("x2 debe ser mayor que x1");

        //Primero creamos un histograma a partir de la imagen
        Histograma<Integer> histograma = new Histograma<Integer>();

        for(int x = x1; x <= x2; x ++){
            for(int y = y1; y <= y2; y++){
                int pixel[] = null;
                pixel = r.getPixel(x, y, pixel);

                histograma.addDato(new Integer(pixel[0]));
            }
        }

        //Obtenemos la entropia con ayuda del histograma
        double result = 0.0, p;
        for(int i = 0; i < 255; i++){
            p = histograma.probabilidad(new Integer(i));
            //System.out.println(p);
            if(p > 0)
                result += p * (Math.log10(p) / Math.log10(2));

        }
        //System.out.println("Result = " + result);
        return -result;
    }
}
