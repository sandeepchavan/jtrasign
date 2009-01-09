/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtraffic.lib;

import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.util.List;

/**
 *
 * @author JuanmaSP
 */
public class Algoritmo {

    private Hashtable<Integer, BufferedImage> imagenes;
    private List<BufferedImage> piramideEdge;
    private List<BufferedImage> piramideRG;
    private List<BufferedImage> piramideBY;
    private List<BufferedImage> csdBorde;

    /** Índice de las imágenes almacenadas */
    public static final int ORIGINAL = 0;
    public static final int NORM_R = 1;
    public static final int NORM_G = 2;
    public static final int NORM_B = 3;
    public static final int NORM_Y = 4;
    public static final int NORM_BY = 7;
    public static final int NORM_RG = 8;
    public static final int NORM_RG_BY = 5;
    public static final int NORM_EDGE = 6;

    /************** Parámetros configurables ****************/
    /** Niveles, como máximo, a construir de las pirámides */
    private int niveles_piramide = 7;
    private int width = 320;
    private int heigth = 240;
    private int dimFixedWindow = 15;
    private float pesoBorde = 0.5f;
    private float pesoColor = 0.5f;

    public Algoritmo(BufferedImage imagen){
        imagenes = new Hashtable<Integer, BufferedImage>();
        imagenes.put(ORIGINAL, imagen);
    }

    public void setDimensiones(int width, int heigth){
        this.width = width;
        this.heigth = heigth;
    }

    public void setDimFixedWindow(int dim){
        this.dimFixedWindow = dim;
    }

    public void lanzar(){
        paso1();
        paso2();
        paso3();
        paso4();
        paso5();
        paso6();
        paso7();
    }

    public void paso1(){
        BufferedImage[] imagenesNormalizadas = ImagenesNormalizadas.construirRGBYE(imagenes.get(ORIGINAL));
        imagenes.put(NORM_R, imagenesNormalizadas[ImagenesNormalizadas.R]);
        imagenes.put(NORM_G, imagenesNormalizadas[ImagenesNormalizadas.G]);
        imagenes.put(NORM_B, imagenesNormalizadas[ImagenesNormalizadas.B]);
        imagenes.put(NORM_Y, imagenesNormalizadas[ImagenesNormalizadas.Y]);
        imagenes.put(NORM_RG_BY, imagenesNormalizadas[ImagenesNormalizadas.RG_BY]);
        imagenes.put(NORM_EDGE, imagenesNormalizadas[ImagenesNormalizadas.E]);
        imagenes.put(NORM_BY, imagenesNormalizadas[ImagenesNormalizadas.BY]);
        imagenes.put(NORM_RG, imagenesNormalizadas[ImagenesNormalizadas.RG]);
    }
    public void paso2(){
        //La función de este paso ya está implementada en el anterior.
    }
    public void paso3(){
        piramideEdge = PiramidesGaussianas.aplicar(imagenes.get(NORM_EDGE),niveles_piramide);
        piramideBY = PiramidesGaussianas.aplicar(imagenes.get(NORM_BY),niveles_piramide);
        piramideRG = PiramidesGaussianas.aplicar(imagenes.get(NORM_RG),niveles_piramide);
    }
    public void paso4(){
        /*
        csdBorde = CSD.aplicar(piramideEdge);
        csdRG = CSD.aplicar(piramideRG);
        csdBY = CSD.aplicar(piramideBY);
         */
    }
    public void paso5(){

    }
    public void paso6(){

    }
    public void paso7(){

    }

    public BufferedImage getImagen(int ident){
        return imagenes.get(ident);
    }
}
