/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacreacion.remates;

import ca.odell.glazedlists.BasicEventList;
import ca.odell.glazedlists.EventList;
import ca.odell.glazedlists.GlazedLists;
import ca.odell.glazedlists.matchers.TextMatcherEditor;
import ca.odell.glazedlists.swing.AutoCompleteSupport;
import com.lacreacion.remates.domain.CuotaModel;
import com.lacreacion.remates.domain.TblMiembros;
import com.lacreacion.remates.domain.TblPagos;
import com.lacreacion.remates.domain.TblRemates;
import com.lacreacion.remates.domain.TblRematesCategorias;
import com.lacreacion.remates.domain.TblRematesCuotas;
import com.lacreacion.remates.utils.Varios;
import java.awt.Color;
import java.awt.KeyboardFocusManager;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Timer;
import java.util.prefs.Preferences;
import javax.persistence.Persistence;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author user
 */
public class FramePagos extends javax.swing.JInternalFrame {

    Map<String, String> persistenceMap = new HashMap<>();
    List<TblMiembros> listMiembrosFiltered;
    TblMiembros selectedMiembro;
    String databaseIP;
    List<Date> listFechasCuotas;
    TblRematesCuotas remateCuotas;
    Integer saldoActual;
    Timer timer;
    EventList<TblMiembros> eventListMiembros = new BasicEventList<>();

    /**
     * Creates new form FramePagos
     */
    private void getDatabaseIP() {
        try {
            databaseIP = Preferences.userRoot().node("Remates").get("DatabaseIP", "127.0.0.1");

            persistenceMap.put("javax.persistence.jdbc.url", "jdbc:postgresql://" + databaseIP + ":5432/remate");
            persistenceMap.put("javax.persistence.jdbc.user", "postgres");
            persistenceMap.put("javax.persistence.jdbc.password", "123456");
            persistenceMap.put("javax.persistence.jdbc.driver", "org.postgresql.Driver");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
        }
    }

    public FramePagos() {

        super("Pagos",
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable
        try {

            getDatabaseIP();
            initComponents();
            txtCtaCte.setEnabled(false);
            cboMiembro.setEnabled(false);
            masterTable.setVisible(false);
            lblDeuda.setVisible(false);
            lblPagos.setVisible(false);
            lblSaldo.setVisible(false);
            txtTransferencia.setVisible(false);
            txtRecibo.setVisible(false);
            cmdProcesar.setVisible(false);

            /*timer = new Timer();
             timer.schedule(new TimerTask() {
             @Override
             public void run() {
             if (cboFechaRemate.getSelectedIndex() > -1) {
             loadPendientes();
             }
             }
             }, 0, 5000);*/
            //AutoCompleteDecorator.decorate(cboFechaRemate);
            //AutoCompleteDecorator.decorate(cboMiembro);
            // AutoCompleteSupport.install(cboMiembro, GlazedLists.eventListOf(listMiembros));
            //final EventList<TblMiembros> urls = GlazedLists.eventList(Arrays.asList(URLS));
            AutoCompleteSupport support = AutoCompleteSupport.install(cboFechaRemate, GlazedLists.eventListOf(listRemates.toArray()));
            support.setFilterMode(TextMatcherEditor.CONTAINS);

            eventListMiembros.clear();
            eventListMiembros.addAll(listMiembros);
            AutoCompleteSupport support1 = AutoCompleteSupport.install(cboMiembro, eventListMiembros);
            support1.setFilterMode(TextMatcherEditor.CONTAINS);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String s = sdf.format(new Date());
            Date today = sdf.parse(s);
            Optional<TblRemates> value = listRemates.stream().filter(a -> a.getFecha().equals(today))
                    .findFirst();
            if (value.isPresent()) {
                cboFechaRemate.setSelectedItem(value.get());
            } else {
                cboFechaRemate.setSelectedIndex(-1);
            }
            cboMiembro.setSelectedIndex(-1);
            KeyboardFocusManager.getCurrentKeyboardFocusManager()
                    .addPropertyChangeListener("permanentFocusOwner", new PropertyChangeListener() {
                        @Override
                        public void propertyChange(final PropertyChangeEvent e) {
                            if (e.getNewValue() instanceof JFormattedTextField) {
                                SwingUtilities.invokeLater(new Runnable() {
                                    public void run() {
                                        JFormattedTextField textField = (JFormattedTextField) e.getNewValue();
                                        textField.selectAll();
                                    }
                                });
                            }
                        }
                    });
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {
        bindingGroup = new org.jdesktop.beansbinding.BindingGroup();

        entityManager = java.beans.Beans.isDesignTime() ? null : Persistence.createEntityManagerFactory("remates_PU", persistenceMap).createEntityManager();
        dateToStringConverter1 = new com.lacreacion.remates.utils.DateToStringConverter();
        queryRemates = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT t FROM TblRemates t ORDER BY t.fecha");
        listRemates = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(queryRemates.getResultList());
        queryMiembros = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT t FROM TblMiembros t ORDER BY t.nombre");
        queryRematesDetalle = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT t FROM TblRematesDetalle t WHERE t.idRemate = :remate AND t.idMiembro = :miembro ORDER BY t.fechahora");
        queryRematesDetalle.setParameter("remate", null);
        queryRematesDetalle.setParameter("miembro", null);
        listMiembros = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(queryMiembros.getResultList());
        listRematesDetalle = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(queryRematesDetalle.getResultList());
        dateTimeTableCellRenderer1 = new com.lacreacion.remates.utils.DateTimeTableCellRenderer();
        numberCellRenderer1 = new com.lacreacion.remates.utils.NumberCellRenderer();
        cboFechaRemate = new javax.swing.JComboBox();
        jLabel1 = new javax.swing.JLabel();
        idMiembroLabel = new javax.swing.JLabel();
        idMiembroLabel1 = new javax.swing.JLabel();
        txtCtaCte = new javax.swing.JTextField();
        idMiembroLabel2 = new javax.swing.JLabel();
        cboMiembro = new javax.swing.JComboBox();
        idMiembroLabel3 = new javax.swing.JLabel();
        idMiembroLabel4 = new javax.swing.JLabel();
        masterScrollPane = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();
        lblDeuda = new javax.swing.JLabel();
        idMiembroLabel7 = new javax.swing.JLabel();
        lblPagos = new javax.swing.JLabel();
        idMiembroLabel8 = new javax.swing.JLabel();
        lblSaldo = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        idMiembroLabel5 = new javax.swing.JLabel();
        idMiembroLabel6 = new javax.swing.JLabel();
        cmdCancel = new javax.swing.JButton();
        cmdProcesar = new javax.swing.JButton();
        lblCuotasFechas = new javax.swing.JLabel();
        txtTransferencia = new javax.swing.JFormattedTextField();
        txtRecibo = new javax.swing.JFormattedTextField();
        jButton1 = new javax.swing.JButton();

        dateTimeTableCellRenderer1.setText("dateTimeTableCellRenderer1");

        numberCellRenderer1.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        numberCellRenderer1.setText("numberCellRenderer1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addInternalFrameListener(new javax.swing.event.InternalFrameListener() {
            public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
                formInternalFrameActivated(evt);
            }
            public void internalFrameClosed(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameClosing(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeactivated(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameDeiconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameIconified(javax.swing.event.InternalFrameEvent evt) {
            }
            public void internalFrameOpened(javax.swing.event.InternalFrameEvent evt) {
            }
        });

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listRemates, cboFechaRemate);
        bindingGroup.addBinding(jComboBoxBinding);

        cboFechaRemate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboFechaRemateActionPerformed(evt);
            }
        });

        jLabel1.setText("Fecha de Remate");

        idMiembroLabel.setText("Miembro:");

        idMiembroLabel1.setText("Cta. Cte.:");

        txtCtaCte.setEnabled(false);
        txtCtaCte.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtCtaCteFocusGained(evt);
            }
        });
        txtCtaCte.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCtaCteActionPerformed(evt);
            }
        });
        txtCtaCte.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCtaCteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCtaCteKeyReleased(evt);
            }
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtCtaCteKeyTyped(evt);
            }
        });

        idMiembroLabel2.setText("Nombre:");

        cboMiembro.setEnabled(false);

        jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listMiembros, cboMiembro);
        bindingGroup.addBinding(jComboBoxBinding);

        cboMiembro.addPopupMenuListener(new javax.swing.event.PopupMenuListener() {
            public void popupMenuCanceled(javax.swing.event.PopupMenuEvent evt) {
            }
            public void popupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {
                cboMiembroPopupMenuWillBecomeInvisible(evt);
            }
            public void popupMenuWillBecomeVisible(javax.swing.event.PopupMenuEvent evt) {
            }
        });
        cboMiembro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboMiembroActionPerformed(evt);
            }
        });

        idMiembroLabel3.setText("Deuda Total:");

        idMiembroLabel4.setText("Realizar Pago:");

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listRematesDetalle, masterTable);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${fechahora}"));
        columnBinding.setColumnName("Fecha Hora");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${idCategoria}"));
        columnBinding.setColumnName("Categoria");
        columnBinding.setColumnClass(TblRematesCategorias.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${observacion}"));
        columnBinding.setColumnName("Observaciones");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${monto}"));
        columnBinding.setColumnName("Monto");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, cboMiembro, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), masterTable, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        masterScrollPane.setViewportView(masterTable);
        if (masterTable.getColumnModel().getColumnCount() > 0) {
            masterTable.getColumnModel().getColumn(0).setCellRenderer(dateTimeTableCellRenderer1);
            masterTable.getColumnModel().getColumn(3).setCellRenderer(numberCellRenderer1);
        }

        lblDeuda.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblDeuda.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblDeuda.setText("1000000");

        idMiembroLabel7.setText("Pagos anteriores:");

        lblPagos.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        lblPagos.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblPagos.setText("1000000");

        idMiembroLabel8.setText("Saldo a pagar:");

        lblSaldo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        lblSaldo.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lblSaldo.setText("1000000");

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        idMiembroLabel5.setText("Transferencia:");

        idMiembroLabel6.setText("Efectivo:");

        cmdCancel.setText("Cancelar");
        cmdCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdCancelActionPerformed(evt);
            }
        });

        cmdProcesar.setText("Procesar & Imprimir");
        cmdProcesar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmdProcesarActionPerformed(evt);
            }
        });

        txtTransferencia.setColumns(9);
        txtTransferencia.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        txtTransferencia.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtTransferenciaMouseClicked(evt);
            }
        });

        txtRecibo.setColumns(9);
        txtRecibo.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.NumberFormatter(java.text.NumberFormat.getIntegerInstance())));
        txtRecibo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                txtReciboMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(cmdProcesar, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cmdCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(idMiembroLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTransferencia, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(134, 134, 134)
                        .addComponent(lblCuotasFechas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(idMiembroLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(idMiembroLabel5)
                        .addComponent(txtTransferencia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lblCuotasFechas, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idMiembroLabel6)
                    .addComponent(txtRecibo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cmdCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cmdProcesar, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButton1.setText("Actualizar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(masterScrollPane, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(idMiembroLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idMiembroLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idMiembroLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(42, 42, 42)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lblSaldo, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblPagos, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(lblDeuda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1)
                                    .addComponent(idMiembroLabel))
                                .addGap(37, 37, 37)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(cboFechaRemate, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(idMiembroLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtCtaCte, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(idMiembroLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cboMiembro, javax.swing.GroupLayout.PREFERRED_SIZE, 303, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(jButton1))))
                            .addComponent(idMiembroLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 181, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 12, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cboFechaRemate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idMiembroLabel)
                    .addComponent(idMiembroLabel1)
                    .addComponent(txtCtaCte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idMiembroLabel2)
                    .addComponent(cboMiembro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton1))
                .addGap(12, 12, 12)
                .addComponent(masterScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lblDeuda)
                        .addGap(10, 10, 10)
                        .addComponent(lblPagos)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lblSaldo))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(idMiembroLabel3)
                        .addGap(10, 10, 10)
                        .addComponent(idMiembroLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(idMiembroLabel8)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(idMiembroLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        bindingGroup.bind();

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCtaCteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCtaCteFocusGained
        txtCtaCte.setSelectionStart(0);
        txtCtaCte.setSelectionEnd(txtCtaCte.getText().length());
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCtaCteFocusGained

    private void txtCtaCteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCtaCteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCtaCteKeyPressed

    private void txtCtaCteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCtaCteKeyReleased
        try {
            txtCtaCte.setBackground(Color.white);
            if (txtCtaCte.getText().length() > 4) {
                listMiembrosFiltered = listMiembros;
                Optional<TblMiembros> value = listMiembrosFiltered.stream().filter(a -> a.getCtacte().equals(Integer.valueOf(txtCtaCte.getText())))
                        .findFirst();
                if (value.isPresent()) {
                    cboMiembro.setSelectedItem(value.get());
                    txtCtaCte.setBackground(Color.green);
                }


                /*for (TblMiembros value : listMiembrosFiltered) {
                 if (value.getCtacte().equals(Integer.valueOf(txtCtaCte.getText()))) {
                 cboMiembro.setSelectedItem(value);
                 txtCtaCte.setBackground(Color.green);
                 }
                 }*/
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
        }        // TODO add your handling code here:
    }//GEN-LAST:event_txtCtaCteKeyReleased

    private void txtCtaCteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCtaCteKeyTyped

        // TODO add your handling code here:
    }//GEN-LAST:event_txtCtaCteKeyTyped

    void loadPendientes() {
        try {
            if (cboFechaRemate.getSelectedIndex() > -1) {
                Integer remateId = ((TblRemates) cboFechaRemate.getSelectedItem()).getId();
                //queryMiembros = entityManager.createNativeQuery("SELECT m.* FROM tbl_miembros m, tbl_remates_detalle r WHERE r.id_remate = " + remateId.toString() + " AND r.id_miembro = m.id AND m.ctacte <> 11111 GROUP BY m.id ORDER BY m.nombre", TblMiembros.class);
                queryMiembros = entityManager.createNativeQuery("WITH remates AS "
                        + "	(SELECT m.*, SUM(rd.monto) AS monto FROM tbl_miembros m "
                        + "	LEFT JOIN tbl_remates_detalle rd ON m.id = rd.id_miembro "
                        + "	group by m.id, m.nombre, m.ctacte, m.domicilio, m.box),"
                        + "     pagos AS"
                        + "       (SELECT m.*, COALESCE(SUM(p.monto),0) AS monto FROM tbl_miembros m "
                        + "	LEFT JOIN tbl_pagos p ON m.id = p.id_miembro "
                        + "	group by m.id, m.nombre, m.ctacte, m.domicilio, m.box)     "
                        + "SELECT remates.id, remates.nombre, remates.ctacte, remates.domicilio, remates.box FROM remates, pagos "
                        + "where remates.id = pagos.id AND (remates.monto - pagos.monto) > 0 "
                        + "order by remates.nombre", TblMiembros.class);

                listMiembros.clear();
                listMiembros.addAll(queryMiembros.getResultList());
                eventListMiembros.clear();
                eventListMiembros.addAll(listMiembros);

                if (listMiembros.size() > 0) {
                    txtCtaCte.setEnabled(true);
                    txtCtaCte.requestFocus();
                    cboMiembro.setEnabled(true);
                } else {
                    txtCtaCte.setEnabled(false);
                    cboMiembro.setEnabled(false);
                }

                remateCuotas = entityManager.find(TblRematesCuotas.class, remateId);
                String fechas = "Las transferencias seran imprimidas con fechas de";
                listFechasCuotas = Varios.getCuotasFechas(remateCuotas);
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                for (Date fecha : listFechasCuotas) {
                    fechas += ", " + sdf.format(fecha);
                }
                fechas = fechas + ".";
                fechas = fechas.replaceFirst(",", " ");
                lblCuotasFechas.setText(fechas);

            }
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
        }
    }
    private void cboFechaRemateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboFechaRemateActionPerformed
        loadPendientes();
    }//GEN-LAST:event_cboFechaRemateActionPerformed

    void loadMiembro() {
        try {
            selectedMiembro = (TblMiembros) cboMiembro.getSelectedItem();
            /*queryRematesDetalle
             = entityManager.createNativeQuery("SELECT * FROM tbl_remates_detalle WHERE id_remate = " + ((TblRemates) cboFechaRemate.getSelectedItem()).getId().toString() + " AND id_miembro = "
             + selectedMiembro.getId().toString() + " ORDER BY fechahora", TblRematesDetalle.class
             );*/

            queryRematesDetalle.setParameter("remate", (TblRemates) cboFechaRemate.getSelectedItem());
            queryRematesDetalle.setParameter("miembro", selectedMiembro);
            listRematesDetalle.clear();

            listRematesDetalle.addAll(queryRematesDetalle.getResultList());
            if (listRematesDetalle.size() > 0) {
                masterTable.setVisible(true);
                lblDeuda.setVisible(true);
                lblPagos.setVisible(true);
                lblSaldo.setVisible(true);
                txtTransferencia.setVisible(true);
                txtRecibo.setVisible(true);
                cmdProcesar.setVisible(true);
            } else {
                masterTable.setVisible(false);
                lblDeuda.setVisible(false);
                lblPagos.setVisible(false);
                lblSaldo.setVisible(false);
                txtTransferencia.setVisible(false);
                txtRecibo.setVisible(false);
                cmdProcesar.setVisible(false);
            }

            Integer deuda = listRematesDetalle.stream()
                    .mapToInt(s -> s.getMonto())
                    .sum();

            lblDeuda.setText(String.format("%(,d", deuda));
            /*
             Integer pagosT;
             try {
             System.out.println("SELECT sum(monto) AS total   FROM tbl_transferencias  WHERE id_miembro = " + selectedMiembro.getId().toString() + " AND id_remate = " + ((TblRemates) cboFechaRemate.getSelectedItem()).getId().toString());

             pagosT = Integer.parseInt(entityManager.createNativeQuery("SELECT sum(monto) AS total"
             + "   FROM tbl_transferencias"
             + "  WHERE id_miembro = " + selectedMiembro.getId().toString()
             + " AND id_remate = " + ((TblRemates) cboFechaRemate.getSelectedItem()).getId().toString()).getSingleResult().toString());
             } catch (Exception ex) {
             pagosT = 0;
             }
             Integer pagosR;
             try {
             pagosR = Integer.parseInt(entityManager.createNativeQuery("SELECT sum(monto) AS total"
             + " FROM tbl_recibos"
             + " WHERE id_miembro = " + selectedMiembro.getId().toString()
             + " AND id_remate = " + ((TblRemates) cboFechaRemate.getSelectedItem()).getId().toString()).getSingleResult().toString());
             } catch (Exception ex) {
             pagosR = 0;
             }

             Integer pagos = pagosT + pagosR;
             */

            Integer pagos;

            try {
                pagos = Integer.parseInt(entityManager.createNativeQuery("SELECT sum(monto) AS total"
                        + " FROM tbl_pagos"
                        + " WHERE id_miembro = " + selectedMiembro.getId().toString()
                        + " AND id_remate = " + ((TblRemates) cboFechaRemate.getSelectedItem()).getId().toString()).getSingleResult().toString());
            } catch (Exception ex) {
                pagos = 0;
            }

            lblPagos.setText(String.format("%(,d", pagos));

            saldoActual = deuda - pagos;

            lblSaldo.setText(String.format("%(,d", saldoActual));

            txtTransferencia.setValue(deuda - pagos);
            txtRecibo.setValue(0);

            txtTransferencia.selectAll();
            //txtTransferencia.requestFocus();
            txtCtaCte.setText(((TblMiembros) cboMiembro.getSelectedItem()).getCtacte().toString());

        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
        }
    }
    private void cboMiembroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboMiembroActionPerformed

    }//GEN-LAST:event_cboMiembroActionPerformed

    private void cmdCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdCancelActionPerformed

    }//GEN-LAST:event_cmdCancelActionPerformed

    private void txtCtaCteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCtaCteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCtaCteActionPerformed

    private void cmdProcesarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cmdProcesarActionPerformed
        try {
            cmdProcesar.setEnabled(false);
            Connection conn = DriverManager.getConnection("jdbc:postgresql://" + databaseIP + ":5432/remate", "postgres", "123456");
            Date fecha = new Date();
            Integer t_id = 0;
            Integer r_id = 0;
            /*if (!StringUtils.isNumeric(txtTransferencia.getText())) {
             txtTransferencia1.setText("0");
             }
             if (!StringUtils.isNumeric(txtEfectivo.getText())) {
             txtEfectivo.setText("0");
             }*/
            if (((Number) txtRecibo.getValue()).intValue() + ((Number) txtTransferencia.getValue()).intValue() > saldoActual) {
                JOptionPane.showMessageDialog(null, "El Monto a abonar no puede superar la deuda existente.");
                return;
            }
            //if (Integer.valueOf(txtTransferencia.getValue()) > 0) {
            Integer transferenciaMonto = ((Number) txtTransferencia.getValue()).intValue();
            if (transferenciaMonto > 0) {
                //List<CuotaModel> cuotasList = Varios.getCuotas(remateCuotas, Integer.valueOf(txtTransferencia1.getText()));
                List<CuotaModel> cuotasList = Varios.getCuotas(remateCuotas, transferenciaMonto);
                for (CuotaModel cuota : cuotasList) {
                    TblPagos transferencia = new TblPagos();
                    transferencia.setFechahora(cuota.getFecha());
                    transferencia.setIdMiembro(selectedMiembro);
                    transferencia.setConcepto(((TblRemates) cboFechaRemate.getSelectedItem()).getDescripcion());
                    transferencia.setMonto(cuota.getMonto());
                    transferencia.setTipo(0);
                    transferencia.setIdRemate((TblRemates) cboFechaRemate.getSelectedItem());
                    entityManager.getTransaction().begin();
                    entityManager.persist(transferencia);
                    entityManager.flush();
                    entityManager.getTransaction().commit();
                    t_id = transferencia.getId();
                    if (t_id > 0) {
                        Map parameters = new HashMap();
                        parameters.put("transferencia_id", t_id);
                        parameters.put("logo", getClass().getResourceAsStream("/reports/cclogo200.png"));
                        parameters.put("logo2", getClass().getResourceAsStream("/reports/cclogo200.png"));
                        parameters.put("logo3", getClass().getResourceAsStream("/reports/cclogo200.png"));

                        JasperReport report = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/transferencia.jrxml"));

                        JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, conn);
                        //JasperViewer jReportsViewer = new JasperViewer(jasperPrint, false);
                        //jReportsViewer.setVisible(true);
                        JasperPrintManager.printReport(jasperPrint, false);
                    }
                }

            }

            Integer reciboMonto = ((Number) txtRecibo.getValue()).intValue();
            if (reciboMonto > 0) {
                TblPagos recibo = new TblPagos();
                recibo.setFechahora(fecha);
                recibo.setIdMiembro(selectedMiembro);
                recibo.setConcepto(((TblRemates) cboFechaRemate.getSelectedItem()).getDescripcion());
                recibo.setMonto(reciboMonto);
                recibo.setIdRemate((TblRemates) cboFechaRemate.getSelectedItem());
                recibo.setTipo(1);
                entityManager.getTransaction().begin();
                entityManager.persist(recibo);
                entityManager.flush();
                entityManager.getTransaction().commit();

                r_id = recibo.getId();
                if (r_id > 0) {
                    Map parameters = new HashMap();
                    parameters.put("recibo_id", r_id);
                    JasperReport report = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/recibo.jrxml"));
                    JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, conn);
                    //JasperViewer jReportsViewer = new JasperViewer(jasperPrint, false);
                    //jReportsViewer.setVisible(true);
                    JasperPrintManager.printReport(jasperPrint, false);
                }
            }

            loadPendientes();
            cboMiembro.setSelectedItem(null);
            listRematesDetalle.clear();
            lblDeuda.setText("");
            lblSaldo.setText("");
            lblPagos.setText("");
            txtCtaCte.setText("");
            txtCtaCte.setBackground(Color.white);
            txtCtaCte.requestFocus();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
            ex.printStackTrace();
        } finally {
            cmdProcesar.setEnabled(true);
        }
    }//GEN-LAST:event_cmdProcesarActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated
        databaseIP = Preferences.userRoot().node("Remates").get("DatabaseIP", "127.0.0.1");
    }//GEN-LAST:event_formInternalFrameActivated

    private void txtTransferenciaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtTransferenciaMouseClicked

    }//GEN-LAST:event_txtTransferenciaMouseClicked

    private void txtReciboMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_txtReciboMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_txtReciboMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        loadPendientes();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cboMiembroPopupMenuWillBecomeInvisible(javax.swing.event.PopupMenuEvent evt) {//GEN-FIRST:event_cboMiembroPopupMenuWillBecomeInvisible
        if (cboMiembro.getSelectedItem() != null) {
            loadMiembro();
        }

    }//GEN-LAST:event_cboMiembroPopupMenuWillBecomeInvisible

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;

                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FramePagos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FramePagos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FramePagos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FramePagos.class
                    .getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FramePagos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboFechaRemate;
    private javax.swing.JComboBox cboMiembro;
    private javax.swing.JButton cmdCancel;
    private javax.swing.JButton cmdProcesar;
    private com.lacreacion.remates.utils.DateTimeTableCellRenderer dateTimeTableCellRenderer1;
    private com.lacreacion.remates.utils.DateToStringConverter dateToStringConverter1;
    private javax.persistence.EntityManager entityManager;
    private javax.swing.JLabel idMiembroLabel;
    private javax.swing.JLabel idMiembroLabel1;
    private javax.swing.JLabel idMiembroLabel2;
    private javax.swing.JLabel idMiembroLabel3;
    private javax.swing.JLabel idMiembroLabel4;
    private javax.swing.JLabel idMiembroLabel5;
    private javax.swing.JLabel idMiembroLabel6;
    private javax.swing.JLabel idMiembroLabel7;
    private javax.swing.JLabel idMiembroLabel8;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lblCuotasFechas;
    private javax.swing.JLabel lblDeuda;
    private javax.swing.JLabel lblPagos;
    private javax.swing.JLabel lblSaldo;
    private java.util.List<com.lacreacion.remates.domain.TblMiembros> listMiembros;
    private java.util.List<com.lacreacion.remates.domain.TblRemates> listRemates;
    private java.util.List<com.lacreacion.remates.domain.TblRematesDetalle> listRematesDetalle;
    private javax.swing.JScrollPane masterScrollPane;
    private javax.swing.JTable masterTable;
    private com.lacreacion.remates.utils.NumberCellRenderer numberCellRenderer1;
    private javax.persistence.Query queryMiembros;
    private javax.persistence.Query queryRemates;
    private javax.persistence.Query queryRematesDetalle;
    private javax.swing.JTextField txtCtaCte;
    private javax.swing.JFormattedTextField txtRecibo;
    private javax.swing.JFormattedTextField txtTransferencia;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables
}
