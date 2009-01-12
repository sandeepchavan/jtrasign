/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtraffic.algoritmo;

import jtraffic.lib.*;
import java.awt.image.BufferedImage;
import java.util.Hashtable;
import java.util.Iterator;
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
    private List<BufferedImage> csdRG;
    private List<BufferedImage> csdBY;
    private List<Posicion> candidatos;

    /** Índice de las imágenes almacenadas */
    public static final int ORIGINAL = 0;
    public static final int ORIGINAL_REDIMENSIONADA = 12;
    public static final int NORM_R = 1;
    public static final int NORM_G = 2;
    public static final int NORM_B = 3;
    public static final int NORM_Y = 4;
    public static final int NORM_BY = 7;
    public static final int NORM_RG = 8;
    public static final int NORM_RG_BY = 5;
    public static final int NORM_EDGE = 6;
    public static final int COLOR_MAP = 10;
    public static final int EDGE_MAP = 11;
    public static final int SALIENCY_MAP = 9;

    /************** Parámetros configurables ****************/
    ConfigAlgoritmo configuracion;

    public Algoritmo(BufferedImage imagen, ConfigAlgoritmo configuracion){
        imagenes = new Hashtable<Integer, BufferedImage>();
        imagenes.put(ORIGINAL, imagen);
        this.configuracion = configuracion;
    }

    public List<BufferedImage> getPiramideEdge(){
        return this.piramideEdge;
    }
    public List<BufferedImage> getPiramideRG(){
        return this.piramideRG;
    }
    public List<BufferedImage> getPiramideBY(){
        return this.piramideBY;
    }
    public List<BufferedImage> getCsdBorde(){
        return this.csdBorde;
    }
    public List<BufferedImage> getCsdRG(){
        return this.csdRG;
    }
    public List<BufferedImage> getCsdBY(){
        return this.csdBY;
        
    }

    public List<Posicion> getCandidatos(){
        return this.candidatos;
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

    public void paso0(){
        //Redimensionamos la imagen original.        
        imagenes.put(ORIGINAL_REDIMENSIONADA,
                OperacionesImagenes.redimensionar(imagenes.get(ORIGINAL)
                        , configuracion.dimensionesAlg.width
                        , configuracion.dimensionesAlg.height));
    }

    public void paso1(){
        BufferedImage[] imagenesNormalizadas = ImagenesNormalizadas.construirRGBYE(imagenes.get(ORIGINAL_REDIMENSIONADA));
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
        piramideEdge = PiramidesGaussianas.aplicar(imagenes.get(NORM_EDGE),configuracion.niveles_piramide);
        piramideBY = PiramidesGaussianas.aplicar(imagenes.get(NORM_BY),configuracion.niveles_piramide);
        piramideRG = PiramidesGaussianas.aplicar(imagenes.get(NORM_RG),configuracion.niveles_piramide);
    }
    public void paso4(){
        csdBorde = CSD.aplicar(piramideEdge, configuracion.dimensionesAlg.width, configuracion.dimensionesAlg.height);
        csdRG = CSD.aplicar(piramideRG, configuracion.dimensionesAlg.width, configuracion.dimensionesAlg.height);
        csdBY = CSD.aplicar(piramideBY, configuracion.dimensionesAlg.width, configuracion.dimensionesAlg.height);
    }
    public void paso5(){
         List<BufferedImage> imagenesSaliencyMap =
                RTS_SM.construirRTS_SM(csdBorde, csdRG, csdBY, configuracion.pesoBorde, configuracion.pesoColor);
        imagenes.put(COLOR_MAP, imagenesSaliencyMap.get(1));
        imagenes.put(EDGE_MAP, imagenesSaliencyMap.get(0));
        imagenes.put(SALIENCY_MAP, imagenesSaliencyMap.get(2));
    }
    public void paso6(){
       //La función de ese paso ya está implementado en el paso anterior.
    }
    public void paso7(){
        BufferedImage saliencyMap = imagenes.get(SALIENCY_MAP);
        int reduccion = configuracion.factorReduccionBusq;

        //Si el factor de reducción es mayor que 1, reducimos previamente la imagen
        if(reduccion > 0){
            int w = (int)Math.ceil(saliencyMap.getWidth() / Math.pow(2, reduccion));
            int h = (int)Math.ceil(saliencyMap.getHeight() / Math.pow(2, reduccion));
            System.out.println("Algoritmo.paso7(): imagen redimensionada.");
            saliencyMap = OperacionesImagenes.redimensionar(saliencyMap, w, h);
        }
        
        candidatos = MaximosLocales.aplicarPorEntropia(saliencyMap, 
                configuracion.dimFixedWindow, configuracion.umbralBusq);

        //Si se ha reducido, tenemos que retocar los datos de las posiciones
        if(reduccion > 0){
            Iterator<Posicion> it = candidatos.iterator();
            while(it.hasNext()){
                Posicion pos = it.next();
                pos.x = pos.x * (int)Math.pow(2, reduccion);
                pos.y = pos.y * (int)Math.pow(2, reduccion);
            }
        }
        System.out.println("Algoritmo.paso7(): " + candidatos.size() + " candidatos encontrados.");
    }

    public BufferedImage getImagen(int ident){
        return imagenes.get(ident);
    }
}
