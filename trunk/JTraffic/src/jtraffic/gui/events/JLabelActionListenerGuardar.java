/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtraffic.gui.events;

import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import jtraffic.ErrorDialog;

/**
 *
 * @author JuanmaSP
 */
public class JLabelActionListenerGuardar implements ActionListener{
    private BufferedImage imagen;
    private Frame contenedor;

    public JLabelActionListenerGuardar(BufferedImage imagen, Frame contenedor){
        this.imagen = imagen;
        this.contenedor = contenedor;
    }

    public void actionPerformed(ActionEvent e) {
        guardar();
    }

    private void guardar(){
        JFileChooser fChooser = new JFileChooser();
        fChooser.setDialogType(JFileChooser.SAVE_DIALOG);
        fChooser.setMultiSelectionEnabled(false);   //Solo se puede seleccionar un fichero
        fChooser.setFileFilter(new FileNameExtensionFilter("PNG", "png"));
        fChooser.setAcceptAllFileFilterUsed(false);

        if(fChooser.showDialog(contenedor, "Guardar imagen") == JFileChooser.APPROVE_OPTION){
            //Abrimos el fichero
            File file = fChooser.getSelectedFile();
            if(file != null){
                try {
                    ImageIO.write(imagen, "png", file);
                } catch (IOException ex) {
                    ErrorDialog dialog = new ErrorDialog(contenedor, "Fallo al crear la imagen: " + ex.getMessage());
                    dialog.show();
                }
                
            }
        }
    }

}
