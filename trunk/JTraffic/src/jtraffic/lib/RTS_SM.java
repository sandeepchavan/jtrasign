/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtraffic.lib;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.awt.image.WritableRaster;
import java.util.List;

/**
 *
 * @author JuanmaSP
 * @author DavidSAC
 */
public class RTS_SM {

    public static BufferedImage construirRTS_SM(List<BufferedImage> edges, List<BufferedImage> rg, List<BufferedImage> by, float pesoEdge, float pesoColor){
        BufferedImage res = null;

        BufferedImage edge = OperacionesImagenes.sumaImagenes(edges);
        List<BufferedImage> colors = OperacionesImagenes.sumaImagenesPorParejas(by, rg);
        BufferedImage color = OperacionesImagenes.sumaImagenes(colors);

        res = construir(edge, color, pesoEdge, pesoColor);

        return res;
    }

    private static BufferedImage construir(BufferedImage edge, BufferedImage color, float pesoEdge, float pesoColor){
        BufferedImage res = new BufferedImage(edge.getWidth(), edge.getHeight(), BufferedImage.TYPE_BYTE_GRAY);
        WritableRaster wres = res.getRaster();

        Raster rA = edge.getData();
        Raster rB = color.getData();

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

                    int nuevo = (int) (((int) pixelA[0] * pesoEdge) + ((int) pixelB[0] * pesoColor));
                    wres.setPixel(x, y, new int[]{nuevo});
                }
        }

        return res;
    }
}
