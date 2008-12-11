package jtraffic.lib;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.LinkedList;
import java.util.List;
import jtraffic.lib.filtros.FiltroGaussiano;

/**
 *
 * @author JuanmaSP
 * @author DavidSAC
 * @date 2008-12-11
 */
public class PiramidesGaussianas {
    /**
     *  Obtiene una pirámide Gaussiana a partir de una imagen.
     * La pirámide tendrá un máximo de num_max niveles o hasta que
     * se llegue a una imagen con 1 solo pixel de ancho o alto.
     *
     * @param imagen de la que se va a obtener la pirámide.
     * @param num_max de niveles de la pirámide.
     * @return
     */
    public static List<BufferedImage> aplicar(BufferedImage imagen, int num_max){
        List<BufferedImage> res = new LinkedList<BufferedImage>();
        
        int num = num_max;

        BufferedImage aux = imagen;
        res.add(aux);
        num --;
        while(aux.getWidth() > 1 && aux.getHeight() > 1 && num > 0){
            BufferedImage aux2 = new BufferedImage(aux.getWidth() / 2, aux.getHeight() /2, aux.getType());
            Graphics2D g = aux2.createGraphics();
            
            g.drawImage(aux, 0, 0, aux2.getWidth() + 2, aux2.getHeight()+ 2, null);
            g.dispose();

            aux2 = FiltroGaussiano.aplicar(aux2, 1, 3);

            res.add(aux2);
            aux = aux2;
            num --;
        }

        return res;
    }
}
