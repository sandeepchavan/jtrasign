/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtraffic.lib;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author JuanmaSP
 * @author DavidSAC
 */
public class OperacionesImagenes {
    public static BufferedImage sumaImagenes(BufferedImage a, BufferedImage b){
        BufferedImage res = new BufferedImage(a.getWidth(), a.getHeight(), a.getType());
        WritableRaster wres = res.getRaster();

        Raster rA = a.getData();
        Raster rB = b.getData();

        int maxY = wres.getHeight();
        int maxX = wres.getWidth();

        for(int x = res.getMinX(); x < maxX; x++){
                for(int y = res.getMinY(); y < maxY; y++){
                    //Pixel acumulado
                    int pixelA[] = null;
                    pixelA = rA.getPixel(x , y, pixelA);
                    //Pixel nuevo
                    int pixelB[] = null;
                    pixelB = rB.getPixel(x , y, pixelB);

                    int nuevo = pixelA[0] + pixelB[0];
                    wres.setPixel(x, y, new int[]{nuevo});
                }
        }

        return res;
    }

    public static BufferedImage sumaImagenes(List<BufferedImage> imagenes){
        if(imagenes == null || imagenes.size() == 0)
            return null;


        BufferedImage res = new BufferedImage(imagenes.get(0).getWidth(), imagenes.get(0).getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster wres = res.getRaster();

        int maxY = wres.getHeight();
        int maxX = wres.getWidth();

        Iterator<BufferedImage> it = imagenes.iterator();
        while(it.hasNext()){
            //BufferedImage aux = Normalizar.aplicar(it.next(), 0, 255/lista.size());
            //BufferedImage aux = Normalizar.normalizacionCompleta(it.next());
            BufferedImage aux = it.next();
            Raster r = aux.getData();

            for(int x = res.getMinX(); x < maxX; x++){
                for(int y = res.getMinY(); y < maxY; y++){
                    //Pixel acumulado
                    int pixelA[] = null;
                    pixelA = wres.getPixel(x , y, pixelA);
                    //Pixel nuevo
                    int pixel[] = null;
                    pixel = r.getPixel(x , y, pixel);

                    int nuevo = pixelA[0] + pixel[0];
                    wres.setPixel(x, y, new int[]{nuevo});
                }
            }
        }

        return res;
    }

    public static List<BufferedImage> sumaImagenesPorParejas(List<BufferedImage> a, List<BufferedImage> b){
        List<BufferedImage> res = new LinkedList<BufferedImage>();

        int tam = a.size();

        for(int i=0;i<tam;i++){
            res.add(sumaImagenes(a.get(i), b.get(i)));
        }

        return res;
    }

    public static BufferedImage sumaImagenesNormalizadas(BufferedImage a, BufferedImage b){
        return null;
    }

    public static BufferedImage sumaImagenesNormalizadas(List<BufferedImage> imagenes){
        if(imagenes == null || imagenes.size() == 0)
            return null;

        int rangoMaxNorm = (int) Math.floor(255 / ((float)imagenes.size()));

        BufferedImage res = new BufferedImage(640, 480, BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster wres = res.getRaster();

        int maxY = wres.getHeight();
        int maxX = wres.getWidth();

        Iterator<BufferedImage> it = imagenes.iterator();
        while(it.hasNext()){
            //BufferedImage aux = Normalizar.aplicar(it.next(), 0, 255/lista.size());
            //BufferedImage aux = Normalizar.normalizacionCompleta(it.next());
            BufferedImage aux = Normalizacion.normalizar(it.next(), 0, rangoMaxNorm);
            Raster r = aux.getData();

            for(int x = res.getMinX(); x < maxX; x++){
                for(int y = res.getMinY(); y < maxY; y++){
                    //Pixel acumulado
                    int pixelA[] = null;
                    pixelA = wres.getPixel(x , y, pixelA);
                    //Pixel nuevo
                    int pixel[] = null;
                    pixel = r.getPixel(x , y, pixel);

                    int nuevo = pixelA[0] + pixel[0];
                    wres.setPixel(x, y, new int[]{nuevo});
                }
            }
        }

        return res;
    }

    public static BufferedImage redimensionar(BufferedImage imagen, int width, int height){
        BufferedImage redimensionada = new BufferedImage(width, height, imagen.getType());
        Graphics2D g = redimensionada.createGraphics();

        g.drawImage(imagen, 0, 0, redimensionada.getWidth(), redimensionada.getHeight(), null);
        g.dispose();

        return redimensionada;
    }

    public static BufferedImage restaImagenesConDifNiveles(BufferedImage c, int nC, BufferedImage s, int nS, int width, int heigth){
        BufferedImage res = new BufferedImage(width, heigth, c.getType());
        WritableRaster wr = res.getRaster();

        Raster rc = c.getData();
        Raster rs = s.getData();

        int maxX = wr.getWidth();
        int maxY = wr.getHeight();

         for(int x = res.getMinX(); x < maxX; x++){
            for(int y = res.getMinY(); y < maxY; y++){
                int xS = (int) Math.floor(x / Math.pow(2.0, nS));
                int yS = (int) Math.floor(y / Math.pow(2.0, nS));

                int xC = (int) Math.floor(x / Math.pow(2.0, nC));
                int yC = (int) Math.floor(y / Math.pow(2.0, nC));

                int pixelC[] = null;
                pixelC = rc.getPixel(xC , yC, pixelC);
                
                int pixelS[] = null;
                pixelS = rs.getPixel(xS , yS, pixelS);

                int dif = Math.abs(pixelC[0] - pixelS[0]);

                wr.setPixel(x, y, new int[]{dif});
            }
         }

        return res;
    }
}
