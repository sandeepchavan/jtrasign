/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package jtraffic.gui.events;

import java.awt.Frame;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import jtraffic.ImageDialog;

/**
 *
 * @author JuanmaSP
 */
public class MouseListenerImagenesLabel implements MouseListener{
    private Frame creador;
    private BufferedImage image;
    private boolean modo;

    public MouseListenerImagenesLabel(Frame creador, BufferedImage imagen, boolean modo){
        super();
        this.creador = creador;
        this.image = imagen;
        this.modo = modo;
    }

    public void mouseClicked(MouseEvent e) {
        ImageDialog dialog = new ImageDialog(image, creador, modo);
        dialog.setTitle("");
        dialog.show();
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

}
