/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacreacion.remates.utils;

import com.lacreacion.remates.domain.TblRematesCategorias;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.swing.JOptionPane;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author user
 */
public class IntegerLongConverter extends Converter {

    @Override
    public Object convertForward(Object value) {
        return ((Number) value).longValue();
    }

    @Override
    public Integer convertReverse(Object value) {
        return ((Number) value).intValue();
    }

}
