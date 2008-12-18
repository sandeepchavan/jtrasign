/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtraffic.lib;

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
    public static final int WIDTH = 640;
    public static final int HEIGHT = 480;

    public static BufferedImage sumaImagenes(BufferedImage a, BufferedImage b){
        BufferedImage res = new BufferedImage(a.getWidth(), a.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
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

        BufferedImage res = new BufferedImage(640, 480, BufferedImage.TYPE_BYTE_GRAY);
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
        return null;
    }
    /*
    public static BufferedImage restaImagenesConDifNiveles(BufferedImage a, int nivelA, BufferedImage b, int nivelB){
        BufferedImage res = new BufferedImage(WIDTH, HEIGHT, a.getType());
        WritableRaster wr = res.getRaster();
        System.out.println("nivel a:"+ nivelA + ", nivel b:" + nivelB);
        Raster rA = a.getData();
        Raster rB = b.getData();

        int maxY = wr.getHeight();
        int maxX = wr.getWidth();

        for(int x = res.getMinX(); x < maxX; x++){
            for(int y = res.getMinY(); y < maxY; y++){
                int xA = (int) Math.floor(x / Math.pow(2.0, nivelA));
                int yA = (int) Math.floor(y / Math.pow(2.0, nivelA));

                int xB = (int) Math.floor(x / Math.pow(2.0, nivelB));
                int yB = (int) Math.floor(y / Math.pow(2.0, nivelB));

                int pixelA[] = null;
                pixelA = rA.getPixel(xA , yA, pixelA);

                int pixelB[] = null;
                pixelB = rB.getPixel(xB , yB, pixelB);

                int dif = Math.abs(pixelA[0] - pixelB[0]);

                wr.setPixel(x, y, new int[]{dif});
            }
        }

        return res;
    }*/
    public static BufferedImage restaImagenesConDifNiveles(BufferedImage c, int nC, BufferedImage s, int nS){
        BufferedImage res = new BufferedImage(640, 480, c.getType());
        WritableRaster wr = res.getRaster();

        Raster rc = c.getData();
        Raster rs = s.getData();

        int maxX = wr.getHeight();
        int maxY = wr.getWidth();

         for(int x = res.getMinX(); x < maxX; x++){
            for(int y = res.getMinY(); y < maxY; y++){
                int xS = (int) Math.floor(x / Math.pow(2.0, nS));
                int yS = (int) Math.floor(y / Math.pow(2.0, nS));

                int xC = (int) Math.floor(x / Math.pow(2.0, nC));
                int yC = (int) Math.floor(y / Math.pow(2.0, nC));

                int pixelC[] = null;
                pixelC = rc.getPixel(yC , xC, pixelC);

                int pixelS[] = null;
                pixelS = rs.getPixel(yS , xS, pixelS);

                int dif = Math.abs(pixelC[0] - pixelS[0]);

                wr.setPixel(y, x, new int[]{dif});
            }
         }

        return res;
    }
}
