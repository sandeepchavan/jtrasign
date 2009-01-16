/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtraffic.gui.events;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import jtraffic.ImageDialog;

/**
 *
 * @author JuanmaSP
 */
public class JLabelActionListenerAmpliar implements ActionListener{
    private BufferedImage imagen;
    private String texto;
    private Frame contenedor;
    private boolean modo;

    public JLabelActionListenerAmpliar(BufferedImage imagen, String textoDialog, Frame contenedor, boolean modo){
        this.imagen = imagen;
        this.texto = textoDialog;
        this.contenedor = contenedor;
        this.modo = modo;
    }

    public void actionPerformed(ActionEvent e) {
        ImageDialog dialog = new ImageDialog(imagen, contenedor, modo);
        dialog.setTitle(texto);
        dialog.show();
    }
    
}
