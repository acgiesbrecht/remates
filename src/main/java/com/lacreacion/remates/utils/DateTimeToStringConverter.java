/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacreacion.remates.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import org.jdesktop.beansbinding.Converter;

/**
 *
 * @author user
 */
public class DateTimeToStringConverter extends Converter {

    @Override
    public Object convertForward(Object value) {
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(value);
    }

    @Override
    public Date convertReverse(Object value) {
        Date res = new Date();
        try {
            res = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(value.toString());

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return res;
    }
}
