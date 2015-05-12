/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacreacion.remates;

import com.lacreacion.remates.domain.TblMiembros;
import java.awt.Color;
import java.awt.EventQueue;
import java.beans.Beans;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.HashMap;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.prefs.Preferences;
import javax.persistence.Persistence;
import javax.persistence.RollbackException;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;

/**
 *
 * @author Industria
 */
public class FrameTransferencias extends JInternalFrame {

    String databaseIP;
    Map<String, String> persistenceMap = new HashMap<>();

    public FrameTransferencias() {
        super("Transferencias",
                true, //resizable
                true, //closable
                true, //maximizable
                true);//iconifiable
        getDatabaseIP();
        initComponents();
        if (!Beans.isDesignTime()) {
            entityManager.getTransaction().begin();
        }
        AutoCompleteDecorator.decorate(cboMiembro);
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

        query = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT t FROM TblTransferencias t");
        list = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(query.getResultList());
        queryMiembros = java.beans.Beans.isDesignTime() ? null : entityManager.createQuery("SELECT t FROM TblMiembros t ORDER BY t.nombre");
        listMiembros = java.beans.Beans.isDesignTime() ? java.util.Collections.emptyList() : org.jdesktop.observablecollections.ObservableCollections.observableList(queryMiembros.getResultList());
        dateToStringConverter1 = new com.lacreacion.remates.utils.DateToStringConverter();
        dateTableCellRenderer1 = new com.lacreacion.remates.utils.DateTimeTableCellRenderer();
        entityManager = java.beans.Beans.isDesignTime() ? null : Persistence.createEntityManagerFactory("remates_PU", persistenceMap).createEntityManager();
        numberCellRenderer1 = new com.lacreacion.remates.utils.NumberCellRenderer();
        masterScrollPane = new javax.swing.JScrollPane();
        masterTable = new javax.swing.JTable();
        fechahoraLabel = new javax.swing.JLabel();
        conceptoLabel = new javax.swing.JLabel();
        montoLabel = new javax.swing.JLabel();
        idLabel = new javax.swing.JLabel();
        idMiembroLabel = new javax.swing.JLabel();
        fechahoraField = new javax.swing.JTextField();
        conceptoField = new javax.swing.JTextField();
        montoField = new javax.swing.JTextField();
        idField = new javax.swing.JTextField();
        saveButton = new javax.swing.JButton();
        refreshButton = new javax.swing.JButton();
        newButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        newButton1 = new javax.swing.JButton();
        idMiembroLabel1 = new javax.swing.JLabel();
        txtCtaCte = new javax.swing.JTextField();
        idMiembroLabel2 = new javax.swing.JLabel();
        cboMiembro = new javax.swing.JComboBox();

        FormListener formListener = new FormListener();

        dateTableCellRenderer1.setText("dateTableCellRenderer1");

        numberCellRenderer1.setText("numberCellRenderer1");

        addInternalFrameListener(formListener);

        org.jdesktop.swingbinding.JTableBinding jTableBinding = org.jdesktop.swingbinding.SwingBindings.createJTableBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, list, masterTable);
        org.jdesktop.swingbinding.JTableBinding.ColumnBinding columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${id}"));
        columnBinding.setColumnName("Id");
        columnBinding.setColumnClass(Integer.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${fechahora}"));
        columnBinding.setColumnName("Fecha/Hora");
        columnBinding.setColumnClass(java.util.Date.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${idMiembro}"));
        columnBinding.setColumnName("Miembro");
        columnBinding.setColumnClass(com.lacreacion.remates.domain.TblMiembros.class);
        columnBinding.setEditable(false);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${concepto}"));
        columnBinding.setColumnName("Concepto");
        columnBinding.setColumnClass(String.class);
        columnBinding = jTableBinding.addColumnBinding(org.jdesktop.beansbinding.ELProperty.create("${monto}"));
        columnBinding.setColumnName("Monto");
        columnBinding.setColumnClass(Integer.class);
        bindingGroup.addBinding(jTableBinding);
        jTableBinding.bind();
        masterScrollPane.setViewportView(masterTable);
        if (masterTable.getColumnModel().getColumnCount() > 0) {
            masterTable.getColumnModel().getColumn(1).setCellRenderer(dateTableCellRenderer1);
            masterTable.getColumnModel().getColumn(4).setCellRenderer(numberCellRenderer1);
        }

        fechahoraLabel.setText("Fecha/Hora:");

        conceptoLabel.setText("Concepto:");

        montoLabel.setText("Monto:");

        idLabel.setText("Id:");

        idMiembroLabel.setText("Miembro:");

        org.jdesktop.beansbinding.Binding binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.fechahora}"), fechahoraField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceUnreadableValue("null");
        binding.setConverter(dateToStringConverter1);
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), fechahoraField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.concepto}"), conceptoField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceUnreadableValue("null");
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), conceptoField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.monto}"), montoField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceUnreadableValue("null");
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), montoField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.id}"), idField, org.jdesktop.beansbinding.BeanProperty.create("text"));
        binding.setSourceUnreadableValue("null");
        bindingGroup.addBinding(binding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), idField, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        saveButton.setText("Guardar");
        saveButton.addActionListener(formListener);

        refreshButton.setText("Cancelar");
        refreshButton.addActionListener(formListener);

        newButton.setText("Nuevo");
        newButton.addActionListener(formListener);

        deleteButton.setText("Eliminar");

        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement != null}"), deleteButton, org.jdesktop.beansbinding.BeanProperty.create("enabled"));
        bindingGroup.addBinding(binding);

        deleteButton.addActionListener(formListener);

        newButton1.setText("Imprimir");
        newButton1.addActionListener(formListener);

        idMiembroLabel1.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        idMiembroLabel1.setText("Cta. Cte.:");

        txtCtaCte.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        txtCtaCte.addFocusListener(formListener);
        txtCtaCte.addActionListener(formListener);
        txtCtaCte.addKeyListener(formListener);

        idMiembroLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        idMiembroLabel2.setText("Nombre:");

        cboMiembro.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N

        org.jdesktop.swingbinding.JComboBoxBinding jComboBoxBinding = org.jdesktop.swingbinding.SwingBindings.createJComboBoxBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, listMiembros, cboMiembro);
        bindingGroup.addBinding(jComboBoxBinding);
        binding = org.jdesktop.beansbinding.Bindings.createAutoBinding(org.jdesktop.beansbinding.AutoBinding.UpdateStrategy.READ_WRITE, masterTable, org.jdesktop.beansbinding.ELProperty.create("${selectedElement.idMiembro}"), cboMiembro, org.jdesktop.beansbinding.BeanProperty.create("selectedItem"));
        bindingGroup.addBinding(binding);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(masterScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 550, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(conceptoLabel)
                                    .addComponent(montoLabel))
                                .addGap(14, 14, 14)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(conceptoField)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(montoField, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(fechahoraLabel)
                                            .addComponent(idLabel))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(fechahoraField, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(idMiembroLabel)
                                        .addGap(18, 18, 18)
                                        .addComponent(idMiembroLabel1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtCtaCte, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(12, 12, 12)
                                        .addComponent(idMiembroLabel2)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cboMiembro, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(newButton1)
                        .addGap(18, 18, 18)
                        .addComponent(newButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(deleteButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(refreshButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(saveButton)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {deleteButton, newButton, refreshButton, saveButton});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(masterScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 154, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(fechahoraLabel)
                    .addComponent(fechahoraField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idMiembroLabel)
                    .addComponent(idMiembroLabel1)
                    .addComponent(txtCtaCte, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idMiembroLabel2)
                    .addComponent(cboMiembro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(conceptoLabel)
                    .addComponent(conceptoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(montoLabel)
                    .addComponent(montoField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(saveButton)
                    .addComponent(refreshButton)
                    .addComponent(deleteButton)
                    .addComponent(newButton)
                    .addComponent(newButton1))
                .addGap(22, 22, 22))
        );

        bindingGroup.bind();
    }

    // Code for dispatching events from components to event handlers.

    private class FormListener implements java.awt.event.ActionListener, java.awt.event.FocusListener, java.awt.event.KeyListener, javax.swing.event.InternalFrameListener {
        FormListener() {}
        public void actionPerformed(java.awt.event.ActionEvent evt) {
            if (evt.getSource() == saveButton) {
                FrameTransferencias.this.saveButtonActionPerformed(evt);
            }
            else if (evt.getSource() == refreshButton) {
                FrameTransferencias.this.refreshButtonActionPerformed(evt);
            }
            else if (evt.getSource() == newButton) {
                FrameTransferencias.this.newButtonActionPerformed(evt);
            }
            else if (evt.getSource() == deleteButton) {
                FrameTransferencias.this.deleteButtonActionPerformed(evt);
            }
            else if (evt.getSource() == newButton1) {
                FrameTransferencias.this.newButton1ActionPerformed(evt);
            }
            else if (evt.getSource() == txtCtaCte) {
                FrameTransferencias.this.txtCtaCteActionPerformed(evt);
            }
        }

        public void focusGained(java.awt.event.FocusEvent evt) {
            if (evt.getSource() == txtCtaCte) {
                FrameTransferencias.this.txtCtaCteFocusGained(evt);
            }
        }

        public void focusLost(java.awt.event.FocusEvent evt) {
        }

        public void keyPressed(java.awt.event.KeyEvent evt) {
            if (evt.getSource() == txtCtaCte) {
                FrameTransferencias.this.txtCtaCteKeyPressed(evt);
            }
        }

        public void keyReleased(java.awt.event.KeyEvent evt) {
            if (evt.getSource() == txtCtaCte) {
                FrameTransferencias.this.txtCtaCteKeyReleased(evt);
            }
        }

        public void keyTyped(java.awt.event.KeyEvent evt) {
            if (evt.getSource() == txtCtaCte) {
                FrameTransferencias.this.txtCtaCteKeyTyped(evt);
            }
        }

        public void internalFrameActivated(javax.swing.event.InternalFrameEvent evt) {
            if (evt.getSource() == FrameTransferencias.this) {
                FrameTransferencias.this.formInternalFrameActivated(evt);
            }
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
    }// </editor-fold>//GEN-END:initComponents

    void refresh() {
        try {
            entityManager.getTransaction().rollback();
            entityManager.getTransaction().begin();
            java.util.Collection data = query.getResultList();
            data.stream().forEach((entity) -> {
                entityManager.refresh(entity);
            });
            list.clear();
            list.addAll(data);

            data = queryMiembros.getResultList();
            data.stream().forEach((entity) -> {
                entityManager.refresh(entity);
            });
            listMiembros.clear();
            listMiembros.addAll(data);

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void refreshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_refreshButtonActionPerformed
        refresh();
    }//GEN-LAST:event_refreshButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        try {
            int[] selected = masterTable.getSelectedRows();
            List<com.lacreacion.remates.domain.TblTransferencias> toRemove = new ArrayList<com.lacreacion.remates.domain.TblTransferencias>(selected.length);
            for (int idx = 0; idx < selected.length; idx++) {
                com.lacreacion.remates.domain.TblTransferencias t = list.get(masterTable.convertRowIndexToModel(selected[idx]));
                toRemove.add(t);
                entityManager.remove(t);
            }
            list.removeAll(toRemove);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void newButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButtonActionPerformed
        try {
            com.lacreacion.remates.domain.TblTransferencias t = new com.lacreacion.remates.domain.TblTransferencias();
            entityManager.persist(t);
            list.add(t);
            int row = list.size() - 1;
            masterTable.setRowSelectionInterval(row, row);
            masterTable.scrollRectToVisible(masterTable.getCellRect(row, 0, true));
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
        }
    }//GEN-LAST:event_newButtonActionPerformed

    private void saveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveButtonActionPerformed
        try {
            entityManager.getTransaction().commit();
            entityManager.getTransaction().begin();
            java.util.Collection data = query.getResultList();
            for (Object entity : data) {
                entityManager.refresh(entity);
            }
            list.clear();
            list.addAll(data);
        } catch (RollbackException rex) {
            JOptionPane.showMessageDialog(null, rex.getMessage());
            entityManager.getTransaction().begin();
            List<com.lacreacion.remates.domain.TblTransferencias> merged = new ArrayList<com.lacreacion.remates.domain.TblTransferencias>(list.size());
            for (com.lacreacion.remates.domain.TblTransferencias t : list) {
                merged.add(entityManager.merge(t));
            }
            list.clear();
            list.addAll(merged);
        }
    }//GEN-LAST:event_saveButtonActionPerformed

    private void newButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newButton1ActionPerformed
        if (masterTable.getSelectedRow() > 0) {
            try {
                Connection conn = DriverManager.getConnection("jdbc:postgresql://" + databaseIP + ":5432/remate", "postgres", "123456");
                Map parameters = new HashMap();
                parameters.put("transferencia_id", Integer.valueOf(idField.getText()));
                parameters.put("logo", getClass().getResource("/reports/cclogo200.png").getPath());

                JasperReport report = JasperCompileManager.compileReport(getClass().getResourceAsStream("/reports/transferencia.jrxml"));

                JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameters, conn);
                //JasperViewer jReportsViewer = new JasperViewer(jasperPrint, false);
                //jReportsViewer.setVisible(true);
                JasperPrintManager.printReport(jasperPrint, false);

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
                ex.printStackTrace();
            }
        }
    }//GEN-LAST:event_newButton1ActionPerformed

    private void formInternalFrameActivated(javax.swing.event.InternalFrameEvent evt) {//GEN-FIRST:event_formInternalFrameActivated

    }//GEN-LAST:event_formInternalFrameActivated

    private void txtCtaCteFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtCtaCteFocusGained
        try {
            txtCtaCte.setSelectionStart(0);
            txtCtaCte.setSelectionEnd(txtCtaCte.getText().length());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
        }
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCtaCteFocusGained

    private void txtCtaCteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCtaCteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCtaCteActionPerformed

    private void txtCtaCteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCtaCteKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCtaCteKeyPressed

    private void txtCtaCteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCtaCteKeyReleased
        try {
            txtCtaCte.setBackground(Color.white);
            if (txtCtaCte.getText().length() > 4) {
                List<TblMiembros> list = listMiembros;
                Optional<TblMiembros> value = list.stream().filter(a -> a.getCtacte().equals(Integer.valueOf(txtCtaCte.getText())))
                        .findFirst();
                System.out.println(Integer.valueOf(txtCtaCte.getText()));
                System.out.println(value.isPresent());
                if (value.isPresent()) {
                    cboMiembro.setSelectedItem(value.get());
                    txtCtaCte.setBackground(Color.green);
                    saveButton.requestFocus();
                }

            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, Thread.currentThread().getStackTrace()[1].getMethodName() + " - " + ex.getMessage());
        }

        // TODO add your handling code here:
    }//GEN-LAST:event_txtCtaCteKeyReleased

    private void txtCtaCteKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCtaCteKeyTyped

        // TODO add your handling code here:
    }//GEN-LAST:event_txtCtaCteKeyTyped

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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox cboMiembro;
    private javax.swing.JTextField conceptoField;
    private javax.swing.JLabel conceptoLabel;
    private com.lacreacion.remates.utils.DateTimeTableCellRenderer dateTableCellRenderer1;
    private com.lacreacion.remates.utils.DateToStringConverter dateToStringConverter1;
    private javax.swing.JButton deleteButton;
    private javax.persistence.EntityManager entityManager;
    private javax.swing.JTextField fechahoraField;
    private javax.swing.JLabel fechahoraLabel;
    private javax.swing.JTextField idField;
    private javax.swing.JLabel idLabel;
    private javax.swing.JLabel idMiembroLabel;
    private javax.swing.JLabel idMiembroLabel1;
    private javax.swing.JLabel idMiembroLabel2;
    private java.util.List<com.lacreacion.remates.domain.TblTransferencias> list;
    private java.util.List listMiembros;
    private javax.swing.JScrollPane masterScrollPane;
    private javax.swing.JTable masterTable;
    private javax.swing.JTextField montoField;
    private javax.swing.JLabel montoLabel;
    private javax.swing.JButton newButton;
    private javax.swing.JButton newButton1;
    private com.lacreacion.remates.utils.NumberCellRenderer numberCellRenderer1;
    private javax.persistence.Query query;
    private javax.persistence.Query queryMiembros;
    private javax.swing.JButton refreshButton;
    private javax.swing.JButton saveButton;
    private javax.swing.JTextField txtCtaCte;
    private org.jdesktop.beansbinding.BindingGroup bindingGroup;
    // End of variables declaration//GEN-END:variables

    public static void main(String[] args) {
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
            java.util.logging.Logger.getLogger(FrameTransferencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrameTransferencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrameTransferencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrameTransferencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                JFrame frame = new JFrame();
                frame.setContentPane(new FrameTransferencias());
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.pack();
                frame.setVisible(true);
            }
        });
    }

}
