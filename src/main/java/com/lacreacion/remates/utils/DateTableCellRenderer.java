/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacreacion.remates.utils;

import java.text.SimpleDateFormat;

/**
 *
 * @author user
 */
public class DateTableCellRenderer extends NormalTableCellRenderer {

    @Override
    public void setValue(Object value) {
        setText((value == null) ? "" : new SimpleDateFormat("yyyy-MM-dd").format(value));
    }
    /*
     @Override
     public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
     super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
     //System.out.println(String.valueOf(column) + " - " + table.getValueAt(row, column).toString());

     if (table.getValueAt(row, 2).toString().equals("En Proceso...")) {
     setBackground(Color.pink);
     setForeground(Color.black);
     } else {
     setBackground(table.getBackground());
     setForeground(table.getForeground());
     }
     if (isSelected) {
     setBackground(table.getSelectionBackground());
     setForeground(table.getSelectionForeground());
     }
     return this;
     }*/
}
