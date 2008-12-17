/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtraffic.lib;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import jtraffic.lib.filtros.Convolucion;
import jtraffic.lib.filtros.FiltroSobel;

/**
 *
 * @author JuanmaSP
 */
public class ImagenesNormalizadas {
    public static final int R = 0;
    public static final int G = 1;
    public static final int RG = 6;
    public static final int B = 2;
    public static final int Y = 3;
    public static final int BY = 7;
    public static final int RG_BY = 4;
    public static final int E = 5;


    public static BufferedImage[] construirRGBYE(BufferedImage imagen){
        BufferedImage result[] = new BufferedImage[8];

        int maxX = imagen.getWidth();
        int maxY = imagen.getHeight();

        BufferedImage r = new BufferedImage(maxX, maxY, BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster rasterR = r.getRaster();
        BufferedImage g = new BufferedImage(maxX, maxY, BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster rasterG = g.getRaster();
        BufferedImage b = new BufferedImage(maxX, maxY, BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster rasterB = b.getRaster();
        BufferedImage ye = new BufferedImage(maxX, maxY, BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster rasterY = ye.getRaster();
        BufferedImage rg_by = new BufferedImage(maxX, maxY, BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster rasterRg_by = rg_by.getRaster();
        BufferedImage rg = new BufferedImage(maxX, maxY, BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster rasterRg = rg.getRaster();
        BufferedImage by = new BufferedImage(maxX, maxY, BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster rasterBy = by.getRaster();

        Raster rasterO = imagen.getData();

        maxX = rasterO.getHeight();
        maxY = rasterO.getWidth();

         for(int x = rasterO.getMinX(); x < maxX; x++){
            for(int y = rasterO.getMinY(); y < maxY; y++){
                int pixel[] = null;
                pixel = rasterO.getPixel(y , x, pixel);

                int red = pixel[0];
                int green = pixel[1];
                int blue = pixel[2];

                int nRed = red - ((green + blue) / 2);
                if(nRed <= 0)
                    nRed = 0;

                int nGreen = green - ((red + blue) / 2);
                if(nGreen <= 0)
                    nGreen = 0;

                int nBlue = blue - ((red + green) / 2);
                if(nBlue <= 0)
                    nBlue = 0;

                int nYellow = ((red + green) / 2) - blue - (Math.abs(red - green)/2);
                if(nYellow <= 0)
                    nYellow = 0;

                int pRg_by = Math.abs(Math.abs(nRed - nGreen) - Math.abs(nBlue - nYellow));

                rasterR.setPixel(y, x, new int[]{nRed});
                rasterG.setPixel(y, x, new int[]{nGreen});
                rasterB.setPixel(y, x, new int[]{nBlue});
                rasterY.setPixel(y, x, new int[]{nYellow});
                rasterRg_by.setPixel(y, x, new int[]{pRg_by});
                rasterRg.setPixel(y, x, new int[]{(Math.abs(nRed - nGreen))});
                rasterBy.setPixel(y, x, new int[]{(Math.abs(nBlue - nYellow))});
            }
         }

        //A partir de la imagen original aplicamos sobel
        BufferedImage imagenes[] = filtroSobel(rg_by);
        BufferedImage sX = imagenes[0];
        BufferedImage sY = imagenes[1];

        //Formamos la imagen e = raiz((rg_by * Sx)^2 + (rg_by * Sy)^2)
        BufferedImage sobel = new BufferedImage(sX.getWidth(), sX.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster rasterSobel = sobel.getRaster();

        BufferedImage edge = new BufferedImage(rg_by.getWidth(), rg_by.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster rasterEdge = edge.getRaster();

        Raster rasterSX = sX.getData();
        Raster rasterSY = sY.getData();

        for(int x = rasterRg_by.getMinX(); x < maxX; x++){
            for(int y = rasterRg_by.getMinY(); y < maxY; y++){
                int pixel[] = null;
                int pixelSx[] = null;
                int pixelSy[] = null;
                pixel = rasterRg_by.getPixel(y , x, pixel);

                pixelSx = rasterSX.getPixel(y , x, pixelSx);
                pixelSy = rasterSY.getPixel(y, x, pixelSy);

                int psobel = Math.abs(pixelSx[0]) + Math.abs(pixelSy[0]);
                rasterSobel.setPixel(y, x, new int[]{psobel});

                int nuevop = (int)Math.sqrt((pixel[0] * pixelSx[0])^2 + (pixel[0] * pixelSy[0])^2);

                rasterEdge.setPixel(y, x, new int[]{nuevop});
            }
        }

        //Preparamos las imagenes para ser devueltas
        result[R] = r;
        result[G] = g;
        result[B] = b;
        result[Y] = ye;
        result[RG_BY] = rg_by;
        result[RG] = rg;
        result[BY] = by;
        result[E] = edge;

        //Devolvemos las imágenes
        return result;
    }

    private static BufferedImage[] filtroSobel(BufferedImage bi){
        BufferedImage resultado[] = new BufferedImage[2];

        resultado[0] = FiltroSobel.aplicar(bi, FiltroSobel.Sx, Convolucion.SIN_BORDES);
        resultado[1] = FiltroSobel.aplicar(bi, FiltroSobel.Sy, Convolucion.SIN_BORDES);

        return resultado;
    }
}
