/*
 * JTrafficView.java
 */

package jtraffic;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.awt.Image;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.Action;
import org.jdesktop.application.ResourceMap;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;
import org.jdesktop.application.TaskMonitor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.Timer;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.filechooser.FileNameExtensionFilter;
import jtraffic.algoritmo.Algoritmo;
import jtraffic.algoritmo.ConfigAlgoritmo;
import jtraffic.algoritmo.Posicion;
import jtraffic.gui.events.JLabelActionListenerAmpliar;
import jtraffic.gui.events.JLabelActionListenerGuardar;

/**
 * The application's main frame.
 */
public class JTrafficView extends FrameView {
    private BufferedImage original;
    private BufferedImage resultado;
    private Algoritmo algoritmo;
    private ConfigAlgoritmo configAlg = new ConfigAlgoritmo();
    BufferedImage punto;

    private boolean algTerminado = false;

    public JTrafficView(SingleFrameApplication app) {
        super(app);

        initComponents();

        // status bar initialization - message timeout, idle icon and busy animation, etc
        ResourceMap resourceMap = getResourceMap();
        int messageTimeout = resourceMap.getInteger("StatusBar.messageTimeout");
        messageTimer = new Timer(messageTimeout, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                statusMessageLabel.setText("");
            }
        });
        messageTimer.setRepeats(false);
        int busyAnimationRate = resourceMap.getInteger("StatusBar.busyAnimationRate");
        for (int i = 0; i < busyIcons.length; i++) {
            busyIcons[i] = resourceMap.getIcon("StatusBar.busyIcons[" + i + "]");
        }
        busyIconTimer = new Timer(busyAnimationRate, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                busyIconIndex = (busyIconIndex + 1) % busyIcons.length;
                statusAnimationLabel.setIcon(busyIcons[busyIconIndex]);
            }
        });
        idleIcon = resourceMap.getIcon("StatusBar.idleIcon");
        statusAnimationLabel.setIcon(idleIcon);
        progressBar.setVisible(false);

        // connecting action tasks to status bar via TaskMonitor
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        taskMonitor.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                String propertyName = evt.getPropertyName();
                if ("started".equals(propertyName)) {
                    if (!busyIconTimer.isRunning()) {
                        statusAnimationLabel.setIcon(busyIcons[0]);
                        busyIconIndex = 0;
                        busyIconTimer.start();
                    }
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(true);
                } else if ("done".equals(propertyName)) {
                    busyIconTimer.stop();
                    statusAnimationLabel.setIcon(idleIcon);
                    progressBar.setVisible(false);
                    progressBar.setValue(0);
                } else if ("message".equals(propertyName)) {
                    String text = (String)(evt.getNewValue());
                    statusMessageLabel.setText((text == null) ? "" : text);
                    messageTimer.restart();
                } else if ("progress".equals(propertyName)) {
                    int value = (Integer)(evt.getNewValue());
                    progressBar.setVisible(true);
                    progressBar.setIndeterminate(false);
                    progressBar.setValue(value);
                }
            }
        });


        try {
            String direccion = System.getProperty("user.dir") + "\\src\\" + resourceMap.getResourcesDir() + "punto.png";
            direccion = direccion.replaceAll("/", "\\\\");
            //System.out.println(direccion);
            punto = ImageIO.read(new File(direccion));
        } catch (Exception e) {
            System.out.println("Fallo al traerse la imagen punto");
        }
        this.getFrame().setMinimumSize(new Dimension(700, 500));
    }

    @Action
    public void showAboutBox() {
        if (aboutBox == null) {
            JFrame mainFrame = JTrafficApp.getApplication().getMainFrame();
            aboutBox = new JTrafficAboutBox(mainFrame);
            aboutBox.setLocationRelativeTo(mainFrame);
        }
        JTrafficApp.getApplication().show(aboutBox);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        tbBarraHerr = new javax.swing.JToolBar();
        bAbrir = new javax.swing.JButton();
        bBack = new javax.swing.JButton();
        bNext = new javax.swing.JButton();
        bLanzar = new javax.swing.JButton();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        spImagenOriginal = new javax.swing.JScrollPane();
        panelImagenOriginal = new javax.swing.JPanel();
        lbImagenOriginal = new javax.swing.JLabel();
        spPaso1 = new javax.swing.JScrollPane();
        jPanel1 = new javax.swing.JPanel();
        lbR = new javax.swing.JLabel();
        lbG = new javax.swing.JLabel();
        lbB = new javax.swing.JLabel();
        lbY = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        spPaso2 = new javax.swing.JScrollPane();
        jPanel2 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        lbE = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        lbRG_BY = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jScrollPane8 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();
        spPaso3 = new javax.swing.JScrollPane();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        jPanel10 = new javax.swing.JPanel();
        lbPirE0 = new javax.swing.JLabel();
        lbPirE1 = new javax.swing.JLabel();
        lbPirE2 = new javax.swing.JLabel();
        lbPirE3 = new javax.swing.JLabel();
        lbPirE4 = new javax.swing.JLabel();
        lbPirE5 = new javax.swing.JLabel();
        lbPirE6 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane4 = new javax.swing.JScrollPane();
        jPanel11 = new javax.swing.JPanel();
        lbPirRG0 = new javax.swing.JLabel();
        lbPirRG1 = new javax.swing.JLabel();
        lbPirRG2 = new javax.swing.JLabel();
        lbPirRG3 = new javax.swing.JLabel();
        lbPirRG4 = new javax.swing.JLabel();
        lbPirRG6 = new javax.swing.JLabel();
        lbPirRG5 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        jPanel12 = new javax.swing.JPanel();
        lbPirBY0 = new javax.swing.JLabel();
        lbPirBY1 = new javax.swing.JLabel();
        lbPirBY2 = new javax.swing.JLabel();
        lbPirBY3 = new javax.swing.JLabel();
        lbPirBY4 = new javax.swing.JLabel();
        lbPirBY5 = new javax.swing.JLabel();
        lbPirBY6 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jScrollPane9 = new javax.swing.JScrollPane();
        jTextArea3 = new javax.swing.JTextArea();
        spPaso4 = new javax.swing.JScrollPane();
        jPanel7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jPanel8 = new javax.swing.JPanel();
        lbcsdE0 = new javax.swing.JLabel();
        lbcsdE1 = new javax.swing.JLabel();
        lbcsdE2 = new javax.swing.JLabel();
        lbcsdE3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jPanel9 = new javax.swing.JPanel();
        lbcsdRG0 = new javax.swing.JLabel();
        lbcsdRG1 = new javax.swing.JLabel();
        lbcsdRG2 = new javax.swing.JLabel();
        lbcsdRG3 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane6 = new javax.swing.JScrollPane();
        jPanel13 = new javax.swing.JPanel();
        lbcsdBY0 = new javax.swing.JLabel();
        lbcsdBY1 = new javax.swing.JLabel();
        lbcsdBY2 = new javax.swing.JLabel();
        lbcsdBY3 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jScrollPane13 = new javax.swing.JScrollPane();
        jTextArea7 = new javax.swing.JTextArea();
        spPaso5 = new javax.swing.JScrollPane();
        jPanel4 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        lbfmColor = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        lbfmEdge = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jScrollPane10 = new javax.swing.JScrollPane();
        jTextArea4 = new javax.swing.JTextArea();
        spPaso6 = new javax.swing.JScrollPane();
        jPanel5 = new javax.swing.JPanel();
        lbSaliency = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane11 = new javax.swing.JScrollPane();
        jTextArea5 = new javax.swing.JTextArea();
        spResultados = new javax.swing.JScrollPane();
        jPanel6 = new javax.swing.JPanel();
        lbResultado = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTextArea6 = new javax.swing.JTextArea();
        menuBar = new javax.swing.JMenuBar();
        javax.swing.JMenu fileMenu = new javax.swing.JMenu();
        miAbrir = new javax.swing.JMenuItem();
        javax.swing.JMenuItem exitMenuItem = new javax.swing.JMenuItem();
        exMenu = new javax.swing.JMenu();
        miConfigurar = new javax.swing.JMenuItem();
        miPasoAnterior = new javax.swing.JMenuItem();
        miPasoSiguiente = new javax.swing.JMenuItem();
        miLanzar = new javax.swing.JMenuItem();
        javax.swing.JMenu helpMenu = new javax.swing.JMenu();
        javax.swing.JMenuItem aboutMenuItem = new javax.swing.JMenuItem();
        statusPanel = new javax.swing.JPanel();
        javax.swing.JSeparator statusPanelSeparator = new javax.swing.JSeparator();
        statusMessageLabel = new javax.swing.JLabel();
        statusAnimationLabel = new javax.swing.JLabel();
        progressBar = new javax.swing.JProgressBar();

        mainPanel.setName("mainPanel"); // NOI18N

        tbBarraHerr.setFloatable(false);
        tbBarraHerr.setRollover(true);
        tbBarraHerr.setName("tbBarraHerr"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(jtraffic.JTrafficApp.class).getContext().getResourceMap(JTrafficView.class);
        bAbrir.setIcon(resourceMap.getIcon("bAbrir.icon")); // NOI18N
        bAbrir.setText(resourceMap.getString("bAbrir.text")); // NOI18N
        bAbrir.setFocusable(false);
        bAbrir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bAbrir.setName("bAbrir"); // NOI18N
        bAbrir.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bAbrirActionPerformed(evt);
            }
        });
        tbBarraHerr.add(bAbrir);

        bBack.setIcon(resourceMap.getIcon("bBack.icon")); // NOI18N
        bBack.setText(resourceMap.getString("bBack.text")); // NOI18N
        bBack.setEnabled(false);
        bBack.setFocusable(false);
        bBack.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bBack.setName("bBack"); // NOI18N
        bBack.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bBackActionPerformed(evt);
            }
        });
        tbBarraHerr.add(bBack);

        bNext.setIcon(resourceMap.getIcon("bNext.icon")); // NOI18N
        bNext.setText(resourceMap.getString("bNext.text")); // NOI18N
        bNext.setEnabled(false);
        bNext.setFocusable(false);
        bNext.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bNext.setName("bNext"); // NOI18N
        bNext.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bNextActionPerformed(evt);
            }
        });
        tbBarraHerr.add(bNext);

        bLanzar.setIcon(resourceMap.getIcon("bLanzar.icon")); // NOI18N
        bLanzar.setText(resourceMap.getString("bLanzar.text")); // NOI18N
        bLanzar.setEnabled(false);
        bLanzar.setFocusable(false);
        bLanzar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        bLanzar.setName("bLanzar"); // NOI18N
        bLanzar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        bLanzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bLanzarActionPerformed(evt);
            }
        });
        tbBarraHerr.add(bLanzar);

        jTabbedPane1.setName("jTabbedPane1"); // NOI18N
        jTabbedPane1.addChangeListener(new javax.swing.event.ChangeListener() {
            public void stateChanged(javax.swing.event.ChangeEvent evt) {
                jTabbedPane1StateChanged(evt);
            }
        });

        spImagenOriginal.setName("spImagenOriginal"); // NOI18N

        panelImagenOriginal.setName("panelImagenOriginal"); // NOI18N

        lbImagenOriginal.setText(resourceMap.getString("lbImagenOriginal.text")); // NOI18N
        lbImagenOriginal.setName("lbImagenOriginal"); // NOI18N

        javax.swing.GroupLayout panelImagenOriginalLayout = new javax.swing.GroupLayout(panelImagenOriginal);
        panelImagenOriginal.setLayout(panelImagenOriginalLayout);
        panelImagenOriginalLayout.setHorizontalGroup(
            panelImagenOriginalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImagenOriginalLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbImagenOriginal, javax.swing.GroupLayout.DEFAULT_SIZE, 815, Short.MAX_VALUE)
                .addContainerGap())
        );
        panelImagenOriginalLayout.setVerticalGroup(
            panelImagenOriginalLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelImagenOriginalLayout.createSequentialGroup()
                .addComponent(lbImagenOriginal, javax.swing.GroupLayout.DEFAULT_SIZE, 584, Short.MAX_VALUE)
                .addContainerGap())
        );

        spImagenOriginal.setViewportView(panelImagenOriginal);

        jTabbedPane1.addTab(resourceMap.getString("spImagenOriginal.TabConstraints.tabTitle"), spImagenOriginal); // NOI18N

        spPaso1.setName("spPaso1"); // NOI18N

        jPanel1.setName("jPanel1"); // NOI18N

        lbR.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbR.setText(resourceMap.getString("lbR.text")); // NOI18N
        lbR.setMaximumSize(new java.awt.Dimension(250, 250));
        lbR.setName("lbR"); // NOI18N

        lbG.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbG.setText(resourceMap.getString("lbG.text")); // NOI18N
        lbG.setMaximumSize(new java.awt.Dimension(250, 250));
        lbG.setName("lbG"); // NOI18N

        lbB.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbB.setText(resourceMap.getString("lbB.text")); // NOI18N
        lbB.setMaximumSize(new java.awt.Dimension(250, 250));
        lbB.setName("lbB"); // NOI18N

        lbY.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbY.setText(resourceMap.getString("lbY.text")); // NOI18N
        lbY.setMaximumSize(new java.awt.Dimension(250, 250));
        lbY.setName("lbY"); // NOI18N

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(resourceMap.getString("jLabel1.text")); // NOI18N
        jLabel1.setName("jLabel1"); // NOI18N

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText(resourceMap.getString("jLabel2.text")); // NOI18N
        jLabel2.setName("jLabel2"); // NOI18N

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText(resourceMap.getString("jLabel3.text")); // NOI18N
        jLabel3.setName("jLabel3"); // NOI18N

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText(resourceMap.getString("jLabel4.text")); // NOI18N
        jLabel4.setName("jLabel4"); // NOI18N

        jScrollPane7.setName("jScrollPane7"); // NOI18N

        jTextArea1.setColumns(20);
        jTextArea1.setEditable(false);
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(5);
        jTextArea1.setText(resourceMap.getString("jTextArea1.text")); // NOI18N
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setAutoscrolls(false);
        jTextArea1.setMaximumSize(new java.awt.Dimension(772, 22));
        jTextArea1.setName("jTextArea1"); // NOI18N
        jScrollPane7.setViewportView(jTextArea1);

        jLabel16.setFont(resourceMap.getFont("jLabel16.font")); // NOI18N
        jLabel16.setText(resourceMap.getString("jLabel16.text")); // NOI18N
        jLabel16.setName("jLabel16"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbR, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbG, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbB, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbY, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 250, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel16)
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel16)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbG, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbR, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbY, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lbB, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel3))
                .addContainerGap(62, Short.MAX_VALUE))
        );

        spPaso1.setViewportView(jPanel1);

        jTabbedPane1.addTab(resourceMap.getString("spPaso1.TabConstraints.tabTitle"), spPaso1); // NOI18N

        spPaso2.setEnabled(false);
        spPaso2.setName("spPaso2"); // NOI18N

        jPanel2.setName("jPanel2"); // NOI18N

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText(resourceMap.getString("jLabel6.text")); // NOI18N
        jLabel6.setName("jLabel6"); // NOI18N

        lbE.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbE.setText(resourceMap.getString("lbE.text")); // NOI18N
        lbE.setMaximumSize(new java.awt.Dimension(250, 250));
        lbE.setName("lbE"); // NOI18N

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText(resourceMap.getString("jLabel5.text")); // NOI18N
        jLabel5.setName("jLabel5"); // NOI18N

        lbRG_BY.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbRG_BY.setText(resourceMap.getString("lbRG_BY.text")); // NOI18N
        lbRG_BY.setMaximumSize(new java.awt.Dimension(250, 250));
        lbRG_BY.setName("lbRG_BY"); // NOI18N

        jLabel18.setFont(resourceMap.getFont("jLabel18.font")); // NOI18N
        jLabel18.setText(resourceMap.getString("jLabel18.text")); // NOI18N
        jLabel18.setName("jLabel18"); // NOI18N

        jScrollPane8.setName("jScrollPane8"); // NOI18N

        jTextArea2.setColumns(20);
        jTextArea2.setEditable(false);
        jTextArea2.setLineWrap(true);
        jTextArea2.setRows(5);
        jTextArea2.setText(resourceMap.getString("jTextArea2.text")); // NOI18N
        jTextArea2.setWrapStyleWord(true);
        jTextArea2.setAutoscrolls(false);
        jTextArea2.setName("jTextArea2"); // NOI18N
        jScrollPane8.setViewportView(jTextArea2);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbRG_BY, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbE, javax.swing.GroupLayout.DEFAULT_SIZE, 252, Short.MAX_VALUE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel18)
                    .addComponent(jScrollPane8, javax.swing.GroupLayout.PREFERRED_SIZE, 337, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbRG_BY, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel2Layout.createSequentialGroup()
                            .addComponent(jLabel18)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jScrollPane8))
                        .addComponent(lbE, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(316, Short.MAX_VALUE))
        );

        spPaso2.setViewportView(jPanel2);

        jTabbedPane1.addTab(resourceMap.getString("spPaso2.TabConstraints.tabTitle"), spPaso2); // NOI18N

        spPaso3.setEnabled(false);
        spPaso3.setName("spPaso3"); // NOI18N

        jPanel3.setName("jPanel3"); // NOI18N

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText(resourceMap.getString("jLabel9.text")); // NOI18N
        jLabel9.setName("jLabel9"); // NOI18N

        jScrollPane3.setName("jScrollPane3"); // NOI18N

        jPanel10.setName("jPanel10"); // NOI18N

        lbPirE0.setText(resourceMap.getString("lbPirE0.text")); // NOI18N
        lbPirE0.setName("lbPirE0"); // NOI18N

        lbPirE1.setText(resourceMap.getString("lbPirE1.text")); // NOI18N
        lbPirE1.setName("lbPirE1"); // NOI18N

        lbPirE2.setText(resourceMap.getString("lbPirE2.text")); // NOI18N
        lbPirE2.setName("lbPirE2"); // NOI18N

        lbPirE3.setText(resourceMap.getString("lbPirE3.text")); // NOI18N
        lbPirE3.setName("lbPirE3"); // NOI18N

        lbPirE4.setText(resourceMap.getString("lbPirE4.text")); // NOI18N
        lbPirE4.setName("lbPirE4"); // NOI18N

        lbPirE5.setText(resourceMap.getString("lbPirE5.text")); // NOI18N
        lbPirE5.setName("lbPirE5"); // NOI18N

        lbPirE6.setText(resourceMap.getString("lbPirE6.text")); // NOI18N
        lbPirE6.setName("lbPirE6"); // NOI18N

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbPirE0, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPirE2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPirE1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPirE3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPirE4, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPirE5, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPirE6, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbPirE0, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbPirE1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbPirE2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbPirE3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbPirE4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbPirE5, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbPirE6, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jScrollPane3.setViewportView(jPanel10);

        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText(resourceMap.getString("jLabel10.text")); // NOI18N
        jLabel10.setName("jLabel10"); // NOI18N

        jScrollPane4.setName("jScrollPane4"); // NOI18N

        jPanel11.setName("jPanel11"); // NOI18N

        lbPirRG0.setText(resourceMap.getString("lbPirRG0.text")); // NOI18N
        lbPirRG0.setName("lbPirRG0"); // NOI18N

        lbPirRG1.setText(resourceMap.getString("lbPirRG1.text")); // NOI18N
        lbPirRG1.setName("lbPirRG1"); // NOI18N

        lbPirRG2.setText(resourceMap.getString("lbPirRG2.text")); // NOI18N
        lbPirRG2.setName("lbPirRG2"); // NOI18N

        lbPirRG3.setText(resourceMap.getString("lbPirRG3.text")); // NOI18N
        lbPirRG3.setName("lbPirRG3"); // NOI18N

        lbPirRG4.setText(resourceMap.getString("lbPirRG4.text")); // NOI18N
        lbPirRG4.setName("lbPirRG4"); // NOI18N

        lbPirRG6.setName("lbPirRG6"); // NOI18N

        lbPirRG5.setText(resourceMap.getString("lbPirRG5.text")); // NOI18N
        lbPirRG5.setName("lbPirRG5"); // NOI18N

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbPirRG0, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPirRG1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPirRG2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPirRG3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPirRG4, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPirRG5, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPirRG6, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(135, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbPirRG0, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbPirRG1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbPirRG2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbPirRG3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbPirRG4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbPirRG5, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbPirRG6, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jScrollPane4.setViewportView(jPanel11);

        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText(resourceMap.getString("jLabel11.text")); // NOI18N
        jLabel11.setName("jLabel11"); // NOI18N

        jScrollPane5.setName("jScrollPane5"); // NOI18N

        jPanel12.setName("jPanel12"); // NOI18N

        lbPirBY0.setText(resourceMap.getString("lbPirBY0.text")); // NOI18N
        lbPirBY0.setName("lbPirBY0"); // NOI18N

        lbPirBY1.setText(resourceMap.getString("lbPirBY1.text")); // NOI18N
        lbPirBY1.setName("lbPirBY1"); // NOI18N

        lbPirBY2.setText(resourceMap.getString("lbPirBY2.text")); // NOI18N
        lbPirBY2.setName("lbPirBY2"); // NOI18N

        lbPirBY3.setText(resourceMap.getString("lbPirBY3.text")); // NOI18N
        lbPirBY3.setName("lbPirBY3"); // NOI18N

        lbPirBY4.setText(resourceMap.getString("lbPirBY4.text")); // NOI18N
        lbPirBY4.setName("lbPirBY4"); // NOI18N

        lbPirBY5.setText(resourceMap.getString("lbPirBY5.text")); // NOI18N
        lbPirBY5.setName("lbPirBY5"); // NOI18N

        lbPirBY6.setText(resourceMap.getString("lbPirBY6.text")); // NOI18N
        lbPirBY6.setName("lbPirBY6"); // NOI18N

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbPirBY0, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPirBY1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPirBY2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPirBY3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPirBY4, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPirBY5, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbPirBY6, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(197, Short.MAX_VALUE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbPirBY0, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbPirBY1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbPirBY2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbPirBY3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbPirBY4, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbPirBY5, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbPirBY6, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        jScrollPane5.setViewportView(jPanel12);

        jLabel19.setFont(resourceMap.getFont("jLabel19.font")); // NOI18N
        jLabel19.setText(resourceMap.getString("jLabel19.text")); // NOI18N
        jLabel19.setName("jLabel19"); // NOI18N

        jScrollPane9.setName("jScrollPane9"); // NOI18N

        jTextArea3.setColumns(20);
        jTextArea3.setEditable(false);
        jTextArea3.setLineWrap(true);
        jTextArea3.setRows(5);
        jTextArea3.setText(resourceMap.getString("jTextArea3.text")); // NOI18N
        jTextArea3.setWrapStyleWord(true);
        jTextArea3.setAutoscrolls(false);
        jTextArea3.setName("jTextArea3"); // NOI18N
        jScrollPane9.setViewportView(jTextArea3);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 233, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel19))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane5, javax.swing.GroupLayout.Alignment.TRAILING, 0, 0, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE)
                    .addComponent(jScrollPane9, javax.swing.GroupLayout.DEFAULT_SIZE, 618, Short.MAX_VALUE))
                .addContainerGap())
        );

        spPaso3.setViewportView(jPanel3);

        jTabbedPane1.addTab(resourceMap.getString("spPaso3.TabConstraints.tabTitle"), spPaso3); // NOI18N

        spPaso4.setEnabled(false);
        spPaso4.setName("spPaso4"); // NOI18N

        jPanel7.setName("jPanel7"); // NOI18N

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText(resourceMap.getString("jLabel7.text")); // NOI18N
        jLabel7.setName("jLabel7"); // NOI18N

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        jPanel8.setName("jPanel8"); // NOI18N

        lbcsdE0.setText(resourceMap.getString("lbcsdE0.text")); // NOI18N
        lbcsdE0.setName("lbcsdE0"); // NOI18N

        lbcsdE1.setText(resourceMap.getString("lbcsdE1.text")); // NOI18N
        lbcsdE1.setName("lbcsdE1"); // NOI18N

        lbcsdE2.setText(resourceMap.getString("lbcsdE2.text")); // NOI18N
        lbcsdE2.setName("lbcsdE2"); // NOI18N

        lbcsdE3.setText(resourceMap.getString("lbcsdE3.text")); // NOI18N
        lbcsdE3.setName("lbcsdE3"); // NOI18N

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbcsdE0, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbcsdE1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbcsdE2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbcsdE3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbcsdE0, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbcsdE1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbcsdE2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbcsdE3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane1.setViewportView(jPanel8);

        jScrollPane2.setName("jScrollPane2"); // NOI18N

        jPanel9.setName("jPanel9"); // NOI18N

        lbcsdRG0.setText(resourceMap.getString("lbcsdRG0.text")); // NOI18N
        lbcsdRG0.setName("lbcsdRG0"); // NOI18N

        lbcsdRG1.setText(resourceMap.getString("lbcsdRG1.text")); // NOI18N
        lbcsdRG1.setName("lbcsdRG1"); // NOI18N

        lbcsdRG2.setText(resourceMap.getString("lbcsdRG2.text")); // NOI18N
        lbcsdRG2.setName("lbcsdRG2"); // NOI18N

        lbcsdRG3.setText(resourceMap.getString("lbcsdRG3.text")); // NOI18N
        lbcsdRG3.setName("lbcsdRG3"); // NOI18N

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbcsdRG0, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbcsdRG1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbcsdRG2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbcsdRG3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(94, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbcsdRG0, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbcsdRG1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbcsdRG2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbcsdRG3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane2.setViewportView(jPanel9);

        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText(resourceMap.getString("jLabel8.text")); // NOI18N
        jLabel8.setName("jLabel8"); // NOI18N

        jScrollPane6.setName("jScrollPane6"); // NOI18N

        jPanel13.setName("jPanel13"); // NOI18N

        lbcsdBY0.setText(resourceMap.getString("lbcsdBY0.text")); // NOI18N
        lbcsdBY0.setName("lbcsdBY0"); // NOI18N

        lbcsdBY1.setText(resourceMap.getString("lbcsdBY1.text")); // NOI18N
        lbcsdBY1.setName("lbcsdBY1"); // NOI18N

        lbcsdBY2.setText(resourceMap.getString("lbcsdBY2.text")); // NOI18N
        lbcsdBY2.setName("lbcsdBY2"); // NOI18N

        lbcsdBY3.setText(resourceMap.getString("lbcsdBY3.text")); // NOI18N
        lbcsdBY3.setName("lbcsdBY3"); // NOI18N

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbcsdBY0, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbcsdBY1, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbcsdBY2, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbcsdBY3, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(89, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbcsdBY0, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbcsdBY1, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbcsdBY2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbcsdBY3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jScrollPane6.setViewportView(jPanel13);

        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText(resourceMap.getString("jLabel15.text")); // NOI18N
        jLabel15.setName("jLabel15"); // NOI18N

        jLabel23.setFont(resourceMap.getFont("jLabel23.font")); // NOI18N
        jLabel23.setText(resourceMap.getString("jLabel23.text")); // NOI18N
        jLabel23.setName("jLabel23"); // NOI18N

        jScrollPane13.setName("jScrollPane13"); // NOI18N

        jTextArea7.setColumns(20);
        jTextArea7.setEditable(false);
        jTextArea7.setLineWrap(true);
        jTextArea7.setRows(5);
        jTextArea7.setText(resourceMap.getString("jTextArea7.text")); // NOI18N
        jTextArea7.setWrapStyleWord(true);
        jTextArea7.setName("jTextArea7"); // NOI18N
        jScrollPane13.setViewportView(jTextArea7);

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 226, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane13)
                    .addComponent(jLabel23, javax.swing.GroupLayout.DEFAULT_SIZE, 246, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8)
                    .addComponent(jLabel15)
                    .addComponent(jLabel23))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane13, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                    .addComponent(jScrollPane6, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 570, Short.MAX_VALUE))
                .addContainerGap())
        );

        spPaso4.setViewportView(jPanel7);

        jTabbedPane1.addTab(resourceMap.getString("spPaso4.TabConstraints.tabTitle"), spPaso4); // NOI18N

        spPaso5.setEnabled(false);
        spPaso5.setName("spPaso5"); // NOI18N

        jPanel4.setName("jPanel4"); // NOI18N

        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText(resourceMap.getString("jLabel12.text")); // NOI18N
        jLabel12.setName("jLabel12"); // NOI18N

        lbfmColor.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbfmColor.setMaximumSize(new java.awt.Dimension(250, 250));
        lbfmColor.setName("lbfmColor"); // NOI18N

        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText(resourceMap.getString("jLabel13.text")); // NOI18N
        jLabel13.setName("jLabel13"); // NOI18N

        lbfmEdge.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbfmEdge.setMaximumSize(new java.awt.Dimension(250, 250));
        lbfmEdge.setName("lbfmEdge"); // NOI18N

        jLabel20.setFont(resourceMap.getFont("jLabel20.font")); // NOI18N
        jLabel20.setText(resourceMap.getString("jLabel20.text")); // NOI18N
        jLabel20.setName("jLabel20"); // NOI18N

        jScrollPane10.setName("jScrollPane10"); // NOI18N

        jTextArea4.setColumns(20);
        jTextArea4.setEditable(false);
        jTextArea4.setLineWrap(true);
        jTextArea4.setRows(5);
        jTextArea4.setText(resourceMap.getString("jTextArea4.text")); // NOI18N
        jTextArea4.setWrapStyleWord(true);
        jTextArea4.setName("jTextArea4"); // NOI18N
        jScrollPane10.setViewportView(jTextArea4);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(lbfmEdge, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbfmColor, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.DEFAULT_SIZE, 257, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 17, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel20, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addComponent(jLabel20)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane10, javax.swing.GroupLayout.DEFAULT_SIZE, 230, Short.MAX_VALUE))
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(lbfmEdge, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(lbfmColor, javax.swing.GroupLayout.PREFERRED_SIZE, 250, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(321, Short.MAX_VALUE))
        );

        spPaso5.setViewportView(jPanel4);

        jTabbedPane1.addTab(resourceMap.getString("spPaso5.TabConstraints.tabTitle"), spPaso5); // NOI18N

        spPaso6.setEnabled(false);
        spPaso6.setName("spPaso6"); // NOI18N

        jPanel5.setName("jPanel5"); // NOI18N

        lbSaliency.setText(resourceMap.getString("lbSaliency.text")); // NOI18N
        lbSaliency.setName("lbSaliency"); // NOI18N

        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText(resourceMap.getString("jLabel14.text")); // NOI18N
        jLabel14.setName("jLabel14"); // NOI18N

        jLabel21.setFont(resourceMap.getFont("jLabel21.font")); // NOI18N
        jLabel21.setText(resourceMap.getString("jLabel21.text")); // NOI18N
        jLabel21.setName("jLabel21"); // NOI18N

        jScrollPane11.setName("jScrollPane11"); // NOI18N

        jTextArea5.setColumns(20);
        jTextArea5.setEditable(false);
        jTextArea5.setLineWrap(true);
        jTextArea5.setRows(5);
        jTextArea5.setText(resourceMap.getString("jTextArea5.text")); // NOI18N
        jTextArea5.setWrapStyleWord(true);
        jTextArea5.setName("jTextArea5"); // NOI18N
        jScrollPane11.setViewportView(jTextArea5);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbSaliency, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 475, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel21)
                    .addComponent(jScrollPane11, javax.swing.GroupLayout.DEFAULT_SIZE, 322, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane11))
                    .addComponent(lbSaliency, javax.swing.GroupLayout.PREFERRED_SIZE, 367, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel14)
                .addContainerGap(216, Short.MAX_VALUE))
        );

        spPaso6.setViewportView(jPanel5);

        jTabbedPane1.addTab(resourceMap.getString("spPaso6.TabConstraints.tabTitle"), spPaso6); // NOI18N

        spResultados.setEnabled(false);
        spResultados.setName("spResultados"); // NOI18N

        jPanel6.setName("jPanel6"); // NOI18N

        lbResultado.setText(resourceMap.getString("lbResultado.text")); // NOI18N
        lbResultado.setName("lbResultado"); // NOI18N

        jLabel17.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel17.setText(resourceMap.getString("jLabel17.text")); // NOI18N
        jLabel17.setName("jLabel17"); // NOI18N

        jLabel22.setFont(resourceMap.getFont("jLabel22.font")); // NOI18N
        jLabel22.setText(resourceMap.getString("jLabel22.text")); // NOI18N
        jLabel22.setName("jLabel22"); // NOI18N

        jScrollPane12.setName("jScrollPane12"); // NOI18N

        jTextArea6.setColumns(20);
        jTextArea6.setEditable(false);
        jTextArea6.setLineWrap(true);
        jTextArea6.setRows(5);
        jTextArea6.setText(resourceMap.getString("jTextArea6.text")); // NOI18N
        jTextArea6.setWrapStyleWord(true);
        jTextArea6.setAutoscrolls(false);
        jTextArea6.setName("jTextArea6"); // NOI18N
        jScrollPane12.setViewportView(jTextArea6);

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(jLabel17, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbResultado, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 581, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jScrollPane12, javax.swing.GroupLayout.DEFAULT_SIZE, 228, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane12))
                    .addComponent(lbResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 404, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel17)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        spResultados.setViewportView(jPanel6);

        jTabbedPane1.addTab(resourceMap.getString("spResultados.TabConstraints.tabTitle"), spResultados); // NOI18N

        javax.swing.GroupLayout mainPanelLayout = new javax.swing.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(mainPanelLayout.createSequentialGroup()
                .addComponent(tbBarraHerr, javax.swing.GroupLayout.PREFERRED_SIZE, 832, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, mainPanelLayout.createSequentialGroup()
                .addComponent(tbBarraHerr, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 625, Short.MAX_VALUE))
        );

        menuBar.setName("menuBar"); // NOI18N

        fileMenu.setText(resourceMap.getString("fileMenu.text")); // NOI18N
        fileMenu.setName("fileMenu"); // NOI18N

        miAbrir.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_A, java.awt.event.InputEvent.CTRL_MASK));
        miAbrir.setText(resourceMap.getString("miAbrir.text")); // NOI18N
        miAbrir.setName("miAbrir"); // NOI18N
        miAbrir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miAbrirActionPerformed(evt);
            }
        });
        fileMenu.add(miAbrir);

        javax.swing.ActionMap actionMap = org.jdesktop.application.Application.getInstance(jtraffic.JTrafficApp.class).getContext().getActionMap(JTrafficView.class, this);
        exitMenuItem.setAction(actionMap.get("quit")); // NOI18N
        exitMenuItem.setText(resourceMap.getString("exitMenuItem.text")); // NOI18N
        exitMenuItem.setName("exitMenuItem"); // NOI18N
        fileMenu.add(exitMenuItem);

        menuBar.add(fileMenu);

        exMenu.setText(resourceMap.getString("exMenu.text")); // NOI18N
        exMenu.setName("exMenu"); // NOI18N
        exMenu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                exMenuActionPerformed(evt);
            }
        });

        miConfigurar.setText(resourceMap.getString("miConfigurar.text")); // NOI18N
        miConfigurar.setName("miConfigurar"); // NOI18N
        miConfigurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miConfigurarActionPerformed(evt);
            }
        });
        exMenu.add(miConfigurar);

        miPasoAnterior.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        miPasoAnterior.setText(resourceMap.getString("miPasoAnterior.text")); // NOI18N
        miPasoAnterior.setEnabled(false);
        miPasoAnterior.setName("miPasoAnterior"); // NOI18N
        miPasoAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miPasoAnteriorActionPerformed(evt);
            }
        });
        exMenu.add(miPasoAnterior);

        miPasoSiguiente.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F6, 0));
        miPasoSiguiente.setText(resourceMap.getString("miPasoSiguiente.text")); // NOI18N
        miPasoSiguiente.setEnabled(false);
        miPasoSiguiente.setName("miPasoSiguiente"); // NOI18N
        miPasoSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miPasoSiguienteActionPerformed(evt);
            }
        });
        exMenu.add(miPasoSiguiente);

        miLanzar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F7, 0));
        miLanzar.setText(resourceMap.getString("miLanzar.text")); // NOI18N
        miLanzar.setEnabled(false);
        miLanzar.setName("miLanzar"); // NOI18N
        miLanzar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                miLanzarActionPerformed(evt);
            }
        });
        exMenu.add(miLanzar);

        menuBar.add(exMenu);

        helpMenu.setText(resourceMap.getString("helpMenu.text")); // NOI18N
        helpMenu.setName("helpMenu"); // NOI18N

        aboutMenuItem.setAction(actionMap.get("showAboutBox")); // NOI18N
        aboutMenuItem.setName("aboutMenuItem"); // NOI18N
        helpMenu.add(aboutMenuItem);

        menuBar.add(helpMenu);

        statusPanel.setName("statusPanel"); // NOI18N

        statusPanelSeparator.setName("statusPanelSeparator"); // NOI18N

        statusMessageLabel.setName("statusMessageLabel"); // NOI18N

        statusAnimationLabel.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        statusAnimationLabel.setName("statusAnimationLabel"); // NOI18N

        progressBar.setName("progressBar"); // NOI18N

        javax.swing.GroupLayout statusPanelLayout = new javax.swing.GroupLayout(statusPanel);
        statusPanel.setLayout(statusPanelLayout);
        statusPanelLayout.setHorizontalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(statusPanelSeparator, javax.swing.GroupLayout.DEFAULT_SIZE, 842, Short.MAX_VALUE)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(statusMessageLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 672, Short.MAX_VALUE)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(statusAnimationLabel)
                .addContainerGap())
        );
        statusPanelLayout.setVerticalGroup(
            statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(statusPanelLayout.createSequentialGroup()
                .addComponent(statusPanelSeparator, javax.swing.GroupLayout.PREFERRED_SIZE, 2, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(statusPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(statusMessageLabel)
                    .addComponent(statusAnimationLabel)
                    .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(3, 3, 3))
        );

        setComponent(mainPanel);
        setMenuBar(menuBar);
        setStatusBar(statusPanel);
    }// </editor-fold>//GEN-END:initComponents

    private void miAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miAbrirActionPerformed
        abrir();
    }//GEN-LAST:event_miAbrirActionPerformed

    private void abrir(){
        JFileChooser abrirFichero = new JFileChooser();
        abrirFichero.setDialogType(JFileChooser.OPEN_DIALOG);
        
        //Carpeta de las imagenes
        File carpeta = new File(System.getProperty("user.dir") + File.separatorChar + "samples");
        if(carpeta != null)
            abrirFichero.setCurrentDirectory(carpeta);

        String[] extensiones = ImageIO.getReaderFileSuffixes();
        for(int i = 0; i < extensiones.length; i++)
            abrirFichero.setFileFilter(new FileNameExtensionFilter(extensiones[i], extensiones[i]));

        if(abrirFichero.showOpenDialog(this.getComponent()) == JFileChooser.APPROVE_OPTION){
            File fichero = abrirFichero.getSelectedFile();

            if(fichero != null){
                try{
                    original = ImageIO.read(fichero);

                    if(algoritmo!= null)
                        System.gc();
                    algTerminado = false;
                    algoritmo = new Algoritmo(original, configAlg);

                    //Limpiamos los labels:
                    limpiarLabels();
                    
                    asignaImagenALabel(lbImagenOriginal, algoritmo.getImagen(Algoritmo.ORIGINAL));

                    jTabbedPane1.setSelectedIndex(0);
                    miPasoAnterior.setEnabled(false);
                    miPasoSiguiente.setEnabled(true);
                    miLanzar.setEnabled(true);
                    bBack.setEnabled(false);
                    bNext.setEnabled(true);
                    bLanzar.setEnabled(true);
                } catch (IOException ex) {
                    Logger.getLogger(JTrafficView.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void exMenuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_exMenuActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_exMenuActionPerformed

    private void miLanzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miLanzarActionPerformed
        lanzar();
    }//GEN-LAST:event_miLanzarActionPerformed

    private void lanzar(){
        if(algTerminado){
            System.gc();
            limpiarLabels();
            asignaImagenALabel(lbImagenOriginal, original);
        }
        /*
        TaskMonitor taskMonitor = new TaskMonitor(getApplication().getContext());
        final Task t = new Task(this.getApplication()) {

            @Override
            protected Object doInBackground() throws Exception {
                mi_lanzar();
                return null;
            }
        };

        t.execute();
         */
        mi_lanzar();

        algTerminado = true;
    }

    private void paso0(){
        algoritmo.paso0();
    }

    private void paso1(){
        algoritmo.paso1();
        asignaImagenALabel(lbR, algoritmo.getImagen(Algoritmo.NORM_R));
        asignaImagenALabel(lbG, algoritmo.getImagen(Algoritmo.NORM_G));
        asignaImagenALabel(lbB, algoritmo.getImagen(Algoritmo.NORM_B));
        asignaImagenALabel(lbY, algoritmo.getImagen(Algoritmo.NORM_Y));
    }

    private void paso2(){
        algoritmo.paso2();
        asignaImagenALabel(lbRG_BY, algoritmo.getImagen(Algoritmo.NORM_BY));
        asignaImagenALabel(lbE, algoritmo.getImagen(Algoritmo.NORM_EDGE));
    }

    private void paso3(){
        algoritmo.paso3();

        List<BufferedImage> piramideEdge = algoritmo.getPiramideEdge();
        System.out.println("Pirámide Edge construida.");
        List<BufferedImage> piramideRG = algoritmo.getPiramideRG();
        System.out.println("Pirámide RG construida.");
        List<BufferedImage> piramideBY = algoritmo.getPiramideBY();
        System.out.println("Pirámide BY construida.");

        asignaImagenALabel(lbPirE0, piramideEdge.get(0));
        asignaImagenALabel(lbPirE1, piramideEdge.get(1));
        asignaImagenALabel(lbPirE2, piramideEdge.get(2));
        asignaImagenALabel(lbPirE3, piramideEdge.get(3));
        asignaImagenALabel(lbPirE4, piramideEdge.get(4));
        asignaImagenALabel(lbPirE5, piramideEdge.get(5));
        asignaImagenALabel(lbPirE6, piramideEdge.get(6));

        asignaImagenALabel(lbPirBY0, piramideBY.get(0));
        asignaImagenALabel(lbPirBY1, piramideBY.get(1));
        asignaImagenALabel(lbPirBY2, piramideBY.get(2));
        asignaImagenALabel(lbPirBY3, piramideBY.get(3));
        asignaImagenALabel(lbPirBY4, piramideBY.get(4));
        asignaImagenALabel(lbPirBY5, piramideBY.get(5));
        asignaImagenALabel(lbPirBY6, piramideBY.get(6));

        asignaImagenALabel(lbPirRG0, piramideRG.get(0));
        asignaImagenALabel(lbPirRG1, piramideRG.get(1));
        asignaImagenALabel(lbPirRG2, piramideRG.get(2));
        asignaImagenALabel(lbPirRG3, piramideRG.get(3));
        asignaImagenALabel(lbPirRG4, piramideRG.get(4));
        asignaImagenALabel(lbPirRG5, piramideRG.get(5));
        asignaImagenALabel(lbPirRG6, piramideRG.get(6));
    }

    private void paso4(){
        algoritmo.paso4();
        //Mapa CSD de Borde
        List<BufferedImage> csdBorde = algoritmo.getCsdBorde();
        System.out.println("CSD borde construido");
        asignaImagenALabel(lbcsdE0, csdBorde.get(0));
        asignaImagenALabel(lbcsdE1, csdBorde.get(1));
        asignaImagenALabel(lbcsdE2, csdBorde.get(2));
        asignaImagenALabel(lbcsdE3, csdBorde.get(3));

        //Mapa CSD de RG
        List<BufferedImage> csdRG = algoritmo.getCsdRG();
        System.out.println("CSD RG construido");
        asignaImagenALabel(lbcsdRG0, csdRG.get(0));
        asignaImagenALabel(lbcsdRG1, csdRG.get(1));
        asignaImagenALabel(lbcsdRG2, csdRG.get(2));
        asignaImagenALabel(lbcsdRG3, csdRG.get(3));

        //Mapa CSD de BY
        List<BufferedImage> csdBY = algoritmo.getCsdBY();
        System.out.println("CSD BY construido");
        asignaImagenALabel(lbcsdBY0, csdBY.get(0));
        asignaImagenALabel(lbcsdBY1, csdBY.get(1));
        asignaImagenALabel(lbcsdBY2, csdBY.get(2));
        asignaImagenALabel(lbcsdBY3, csdBY.get(3));
    }

    private void paso5(){
        algoritmo.paso5();
        asignaImagenALabel(lbfmEdge, algoritmo.getImagen(Algoritmo.EDGE_MAP));
        asignaImagenALabel(lbfmColor, algoritmo.getImagen(Algoritmo.COLOR_MAP));
    }

    private void paso6(){
        System.out.println("RTS_SM construido.");
        asignaImagenALabel(lbSaliency, algoritmo.getImagen(Algoritmo.SALIENCY_MAP));
    }

    private void paso7(){
        algoritmo.paso7();
        System.out.println("Máximos locales calculados.");

        List<Posicion> candidatos = algoritmo.getCandidatos();

        Iterator<Posicion> it = candidatos.iterator();
        
        resultado = new BufferedImage(640, 480, BufferedImage.TYPE_INT_RGB);

        Graphics2D g = resultado.createGraphics();
        g.drawImage(algoritmo.getImagen(Algoritmo.ORIGINAL_REDIMENSIONADA), 0, 0, resultado.getWidth(), resultado.getHeight(), null);
        it = candidatos.iterator();

        while(it.hasNext()){
            Posicion pos = it.next();
            System.out.println("Posicion: " + pos);

            if(punto != null)
                g.drawImage((Image)punto, pos.x, pos.y, 10, 10, null);
            else
                g.drawOval(pos.x, pos.y, 10, 10);

        }

        asignaImagenALabel(lbResultado, resultado);

        algTerminado = true;
    }

    private void mi_lanzar(){
        paso0();
        paso1();
        paso2();
        paso3();
        paso4();
        paso5();
        paso6();
        paso7();
    }

    private void bAbrirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bAbrirActionPerformed
        abrir();
    }//GEN-LAST:event_bAbrirActionPerformed

    private void bLanzarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bLanzarActionPerformed
        lanzar();
    }//GEN-LAST:event_bLanzarActionPerformed

    private void miPasoAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miPasoAnteriorActionPerformed
        pasoAnterior();
    }//GEN-LAST:event_miPasoAnteriorActionPerformed

    private void miPasoSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miPasoSiguienteActionPerformed
        pasoSiguiente();
    }//GEN-LAST:event_miPasoSiguienteActionPerformed

    private void bBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bBackActionPerformed
        pasoAnterior();
    }//GEN-LAST:event_bBackActionPerformed

    private void bNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bNextActionPerformed
        pasoSiguiente();
    }//GEN-LAST:event_bNextActionPerformed

    private void jTabbedPane1StateChanged(javax.swing.event.ChangeEvent evt) {//GEN-FIRST:event_jTabbedPane1StateChanged
        actualizarBotonesEjecucion();
    }//GEN-LAST:event_jTabbedPane1StateChanged

    private void miConfigurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_miConfigurarActionPerformed
        AlgConfigDialog dialog = new AlgConfigDialog(this.getFrame(), true, configAlg);
        dialog.show();
    }//GEN-LAST:event_miConfigurarActionPerformed

    private void pasoSiguiente(){
        if(!algTerminado)
            lanzar();

        int pasoActual = jTabbedPane1.getSelectedIndex();
        if(pasoActual < 7){
            pasoActual ++;
            jTabbedPane1.setSelectedIndex(pasoActual);
        }
    }

    private void pasoAnterior(){
        int pasoActual = jTabbedPane1.getSelectedIndex();
        if(pasoActual > 0){
            pasoActual --;
            jTabbedPane1.setSelectedIndex(pasoActual);
        }
    }

    private void actualizarBotonesEjecucion(){
        if(algTerminado){
            int pasoActual = jTabbedPane1.getSelectedIndex();
            if(pasoActual <= 0){
                bBack.setEnabled(false);
                miPasoAnterior.setEnabled(false);
                bNext.setEnabled(true);
                miPasoSiguiente.setEnabled(true);
            }else if(pasoActual < 7){
                bBack.setEnabled(true);
                miPasoAnterior.setEnabled(true);
                bNext.setEnabled(true);
                miPasoSiguiente.setEnabled(true);
            }else{
                bBack.setEnabled(true);
                miPasoAnterior.setEnabled(true);
                bNext.setEnabled(false);
                miPasoSiguiente.setEnabled(false);
            }
        }
    }

    private void asignaImagenALabel(JLabel label, BufferedImage imagen){
        Image aux = imagen.getScaledInstance(label.getWidth(), label.getHeight(), Image.SCALE_DEFAULT);
        label.setIcon(new ImageIcon(aux));
        JPopupMenu menu = new JPopupMenu();
        JMenuItem ampliar = new JMenuItem("Ampliar");
        ampliar.addActionListener(new JLabelActionListenerAmpliar(imagen, "", this.getFrame(), true));
        JMenuItem guardar = new JMenuItem("Guardar");
        guardar.addActionListener(new JLabelActionListenerGuardar(imagen, this.getFrame()));
        menu.add(ampliar);
        menu.add(guardar);

        label.setComponentPopupMenu(menu);
    }

    private void limpiarLabels(){
        liberarLabelDeImagen(lbB);
        liberarLabelDeImagen(lbE);
        liberarLabelDeImagen(lbG);
        liberarLabelDeImagen(lbImagenOriginal);
        liberarLabelDeImagen(lbPirBY0);
        liberarLabelDeImagen(lbPirBY1);
        liberarLabelDeImagen(lbPirBY2);
        liberarLabelDeImagen(lbPirBY3);
        liberarLabelDeImagen(lbPirBY4);
        liberarLabelDeImagen(lbPirBY5);
        liberarLabelDeImagen(lbPirBY6);
        liberarLabelDeImagen(lbPirE0);
        liberarLabelDeImagen(lbPirE1);
        liberarLabelDeImagen(lbPirE2);
        liberarLabelDeImagen(lbPirE3);
        liberarLabelDeImagen(lbPirE4);
        liberarLabelDeImagen(lbPirE5);
        liberarLabelDeImagen(lbPirE6);
        liberarLabelDeImagen(lbPirRG0);
        liberarLabelDeImagen(lbPirRG1);
        liberarLabelDeImagen(lbPirRG2);
        liberarLabelDeImagen(lbPirRG3);
        liberarLabelDeImagen(lbPirRG4);
        liberarLabelDeImagen(lbPirRG5);
        liberarLabelDeImagen(lbPirRG6);
        liberarLabelDeImagen(lbR);
        liberarLabelDeImagen(lbRG_BY);
        liberarLabelDeImagen(lbResultado);
        liberarLabelDeImagen(lbSaliency);
        liberarLabelDeImagen(lbY);
        liberarLabelDeImagen(lbcsdBY0);
        liberarLabelDeImagen(lbcsdBY1);
        liberarLabelDeImagen(lbcsdBY2);
        liberarLabelDeImagen(lbcsdBY3);
        liberarLabelDeImagen(lbcsdE0);
        liberarLabelDeImagen(lbcsdE1);
        liberarLabelDeImagen(lbcsdE2);
        liberarLabelDeImagen(lbcsdE3);
        liberarLabelDeImagen(lbcsdRG0);
        liberarLabelDeImagen(lbcsdRG1);
        liberarLabelDeImagen(lbcsdRG2);
        liberarLabelDeImagen(lbcsdRG3);
        liberarLabelDeImagen(lbfmColor);
        liberarLabelDeImagen(lbfmEdge);
    }

    private void liberarLabelDeImagen(JLabel label){
        if(label.getIcon() != null)
            label.setIcon(null);
        if(label.getComponentPopupMenu() != null){
            label.remove(label.getComponentPopupMenu());
            label.setComponentPopupMenu(null);
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bAbrir;
    private javax.swing.JButton bBack;
    private javax.swing.JButton bLanzar;
    private javax.swing.JButton bNext;
    private javax.swing.JMenu exMenu;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane10;
    private javax.swing.JScrollPane jScrollPane11;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane8;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JTextArea jTextArea3;
    private javax.swing.JTextArea jTextArea4;
    private javax.swing.JTextArea jTextArea5;
    private javax.swing.JTextArea jTextArea6;
    private javax.swing.JTextArea jTextArea7;
    private javax.swing.JLabel lbB;
    private javax.swing.JLabel lbE;
    private javax.swing.JLabel lbG;
    private javax.swing.JLabel lbImagenOriginal;
    private javax.swing.JLabel lbPirBY0;
    private javax.swing.JLabel lbPirBY1;
    private javax.swing.JLabel lbPirBY2;
    private javax.swing.JLabel lbPirBY3;
    private javax.swing.JLabel lbPirBY4;
    private javax.swing.JLabel lbPirBY5;
    private javax.swing.JLabel lbPirBY6;
    private javax.swing.JLabel lbPirE0;
    private javax.swing.JLabel lbPirE1;
    private javax.swing.JLabel lbPirE2;
    private javax.swing.JLabel lbPirE3;
    private javax.swing.JLabel lbPirE4;
    private javax.swing.JLabel lbPirE5;
    private javax.swing.JLabel lbPirE6;
    private javax.swing.JLabel lbPirRG0;
    private javax.swing.JLabel lbPirRG1;
    private javax.swing.JLabel lbPirRG2;
    private javax.swing.JLabel lbPirRG3;
    private javax.swing.JLabel lbPirRG4;
    private javax.swing.JLabel lbPirRG5;
    private javax.swing.JLabel lbPirRG6;
    private javax.swing.JLabel lbR;
    private javax.swing.JLabel lbRG_BY;
    private javax.swing.JLabel lbResultado;
    private javax.swing.JLabel lbSaliency;
    private javax.swing.JLabel lbY;
    private javax.swing.JLabel lbcsdBY0;
    private javax.swing.JLabel lbcsdBY1;
    private javax.swing.JLabel lbcsdBY2;
    private javax.swing.JLabel lbcsdBY3;
    private javax.swing.JLabel lbcsdE0;
    private javax.swing.JLabel lbcsdE1;
    private javax.swing.JLabel lbcsdE2;
    private javax.swing.JLabel lbcsdE3;
    private javax.swing.JLabel lbcsdRG0;
    private javax.swing.JLabel lbcsdRG1;
    private javax.swing.JLabel lbcsdRG2;
    private javax.swing.JLabel lbcsdRG3;
    private javax.swing.JLabel lbfmColor;
    private javax.swing.JLabel lbfmEdge;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JMenuBar menuBar;
    private javax.swing.JMenuItem miAbrir;
    private javax.swing.JMenuItem miConfigurar;
    private javax.swing.JMenuItem miLanzar;
    private javax.swing.JMenuItem miPasoAnterior;
    private javax.swing.JMenuItem miPasoSiguiente;
    private javax.swing.JPanel panelImagenOriginal;
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JScrollPane spImagenOriginal;
    private javax.swing.JScrollPane spPaso1;
    private javax.swing.JScrollPane spPaso2;
    private javax.swing.JScrollPane spPaso3;
    private javax.swing.JScrollPane spPaso4;
    private javax.swing.JScrollPane spPaso5;
    private javax.swing.JScrollPane spPaso6;
    private javax.swing.JScrollPane spResultados;
    private javax.swing.JLabel statusAnimationLabel;
    private javax.swing.JLabel statusMessageLabel;
    private javax.swing.JPanel statusPanel;
    private javax.swing.JToolBar tbBarraHerr;
    // End of variables declaration//GEN-END:variables

    private final Timer messageTimer;
    private final Timer busyIconTimer;
    private final Icon idleIcon;
    private final Icon[] busyIcons = new Icon[15];
    private int busyIconIndex = 0;

    private JDialog aboutBox;
}
