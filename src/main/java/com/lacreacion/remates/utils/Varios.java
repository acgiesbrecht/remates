/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacreacion.remates.utils;

import com.lacreacion.remates.domain.TblRematesCuotas;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author user
 */
public class Varios {

    public static Integer getCuotasCantidad(TblRematesCuotas cuotas) {
        int count = 0;
        if (cuotas.getFecha1() != null) {
            count++;
        }
        if (cuotas.getFecha2() != null) {
            count++;
        }
        if (cuotas.getFecha3() != null) {
            count++;
        }
        if (cuotas.getFecha4() != null) {
            count++;
        }
        return count;

    }

    public static List<Date> getCuotasFechas(TblRematesCuotas cuotas) {
        List<Date> cuotasList = new ArrayList<>();
        if (cuotas.getFecha1() != null) {
            cuotasList.add(cuotas.getFecha1());
        }
        if (cuotas.getFecha2() != null) {
            cuotasList.add(cuotas.getFecha2());
        }
        if (cuotas.getFecha3() != null) {
            cuotasList.add(cuotas.getFecha3());
        }
        if (cuotas.getFecha4() != null) {
            cuotasList.add(cuotas.getFecha4());
        }
        return cuotasList;
    }
}
