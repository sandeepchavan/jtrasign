/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtraffic.lib;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author JuanmaSP
 */
public class MaximosLocales {
    public static List<Posicion> aplicarPorSuma(BufferedImage imagen, int dim){
        if(imagen.getType() != BufferedImage.TYPE_BYTE_GRAY)
            throw new IllegalArgumentException("Solo se permite imagenes en escala de grises.");

        List<Posicion> res = new LinkedList<Posicion>();

        Raster raster = imagen.getRaster();

        int mitad = (int)Math.floor(((float)dim)/2.0);
        int minX = raster.getMinX() + mitad;
        int minY = raster.getMinY() + mitad;
        int maxX = raster.getWidth() - mitad;
        int maxY = raster.getHeight() - mitad;

        //Creamos una matríz con los valores recogidos
        int matriz[][] = new int[raster.getWidth() - dim + 1][raster.getHeight() - dim + 1];
        for(int x = minX; x < maxX; x++){
            for(int y = minY; y < maxY; y++){
                matriz[x - mitad][y - mitad] = getSuma(imagen, x - mitad, y - mitad, x + mitad, y + mitad);
            }
        }
        //Y luego buscamos en ella los máximos locales
        for(int i = 0; i < matriz.length; i++){
            for(int j = 0; j < matriz[i].length; j++){
                int aux = matriz[i][j];
                boolean esMax = true;

                //Fila superior
                if(i > 0){
                    if(j > 0)
                        if(matriz[i - 1][j - 1] >= aux)
                            esMax = false;
                    if(matriz[i - 1][j] >= aux)
                        esMax = false;
                    if(j < matriz[i - 1].length - 1)
                        if(matriz[i - 1][j + 1] >= aux)
                            esMax = false;
                }
                //Misma fila
                if(j > 0)
                    if(matriz[i][j - 1] >= aux)
                        esMax = false;
                if(j < matriz[i].length - 1)
                    if(matriz[i][j + 1] >= aux)
                        esMax = false;

                //Fila inferior
                if(i < matriz.length - 1){
                    if(j > 0)
                        if(matriz[i + 1][j - 1] >= aux)
                            esMax = false;
                    if(matriz[i+1][j] >= aux)
                        esMax = false;
                    if(j < matriz[i + 1].length - 1)
                        if(matriz[i + 1][j + 1] >= aux)
                            esMax = false;
                }


                //Si es máximo local lo añadimos como tal en los resultados
                if(esMax)
                    res.add(new Posicion(i + mitad, j + mitad));
            }
        }

        return res;
    }

    public static List<Posicion> aplicarPorEntropia(BufferedImage imagen, int dim){
        if(imagen.getType() != BufferedImage.TYPE_BYTE_GRAY)
            throw new IllegalArgumentException("Solo se permite imagenes en escala de grises.");

        List<Posicion> res = new LinkedList<Posicion>();

        Raster raster = imagen.getRaster();

        int mitad = (int)Math.floor(((float)dim)/2.0);
        int minX = raster.getMinX() + mitad;
        int minY = raster.getMinY() + mitad;
        int maxX = raster.getWidth() - mitad;
        int maxY = raster.getHeight() - mitad;

        //Creamos una matríz con los valores recogidos
        double matriz[][] = new double[raster.getWidth() - dim + 1][raster.getHeight() - dim + 1];
        for(int x = minX; x < maxX; x++){
            //System.out.print("\n");
            for(int y = minY; y < maxY; y++){
                matriz[x - mitad][y - mitad] = Entropia.aplicar(imagen, x - mitad, y - mitad, x + mitad, y + mitad);
                //System.out.print(matriz[x - mitad][y - mitad] + ",");
            }
        }
        //Y luego buscamos en ella los máximos locales
        for(int i = 0; i < matriz.length; i++){
            //System.out.print("\n");
            for(int j = 0; j < matriz[i].length; j++){
                double aux = matriz[i][j];
                //System.out.print(aux + ",");
                if(aux > 6){
                    boolean esMax = true;
                    //Fila superior
                    if(i > 0){
                        if(j > 0)
                            if(matriz[i - 1][j - 1] >= aux)
                                esMax = false;
                        if(matriz[i - 1][j] >= aux)
                            esMax = false;
                        if(j < matriz[i - 1].length - 1)
                            if(matriz[i - 1][j + 1] >= aux)
                                esMax = false;
                    }
                    //Misma fila
                    if(j > 0)
                        if(matriz[i][j - 1] >= aux)
                            esMax = false;
                    if(j < matriz[i].length - 1)
                        if(matriz[i][j + 1] >= aux)
                            esMax = false;

                    //Fila inferior
                    if(i < matriz.length - 1){
                        if(j > 0)
                            if(matriz[i + 1][j - 1] >= aux)
                                esMax = false;
                        if(matriz[i+1][j] >= aux)
                            esMax = false;
                        if(j < matriz[i + 1].length - 1)
                            if(matriz[i + 1][j + 1] >= aux)
                                esMax = false;
                    }


                    //Si es máximo local lo añadimos como tal en los resultados
                    if(esMax)
                        res.add(new Posicion(i + mitad, j + mitad));
                }
            }
        }

        return res;
    }

    private static int getSuma(BufferedImage imagen, int x1, int y1, int x2, int y2){
        int res = 0;

        Raster r = imagen.getData();
        for(int x = x1; x <= x2; x++){
            for(int y = y1; y <= y2; y++){
                int pixel[] = null;
                pixel = r.getPixel(x, y, pixel);

                res += pixel[0];
            }
        }

        return res;
    }

}
