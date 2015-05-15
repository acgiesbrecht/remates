/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacreacion.remates.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author AdrianGiesbrecht
 */
@Entity
@Table(name = "tbl_pagos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblPagos.findAll", query = "SELECT t FROM TblPagos t"),
    @NamedQuery(name = "TblPagos.findById", query = "SELECT t FROM TblPagos t WHERE t.id = :id"),
    @NamedQuery(name = "TblPagos.findByFechahora", query = "SELECT t FROM TblPagos t WHERE t.fechahora = :fechahora"),
    @NamedQuery(name = "TblPagos.findByConcepto", query = "SELECT t FROM TblPagos t WHERE t.concepto = :concepto"),
    @NamedQuery(name = "TblPagos.findByMonto", query = "SELECT t FROM TblPagos t WHERE t.monto = :monto"),
    @NamedQuery(name = "TblPagos.findByTipo", query = "SELECT t FROM TblPagos t WHERE t.tipo = :tipo")})
public class TblPagos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fechahora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahora;
    @Column(name = "concepto")
    private String concepto;
    @Column(name = "monto")
    private Integer monto;
    @Column(name = "tipo")
    private Integer tipo;
    @JoinColumn(name = "id_miembro", referencedColumnName = "id")
    @ManyToOne
    private TblMiembros idMiembro;
    @JoinColumn(name = "id_remate", referencedColumnName = "id")
    @ManyToOne
    private TblRemates idRemate;

    public TblPagos() {
    }

    public TblPagos(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechahora() {
        return fechahora;
    }

    public void setFechahora(Date fechahora) {
        this.fechahora = fechahora;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public Integer getTipo() {
        return tipo;
    }

    public void setTipo(Integer tipo) {
        this.tipo = tipo;
    }

    public TblMiembros getIdMiembro() {
        return idMiembro;
    }

    public void setIdMiembro(TblMiembros idMiembro) {
        this.idMiembro = idMiembro;
    }

    public TblRemates getIdRemate() {
        return idRemate;
    }

    public void setIdRemate(TblRemates idRemate) {
        this.idRemate = idRemate;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblPagos)) {
            return false;
        }
        TblPagos other = (TblPagos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lacreacion.remates.domain.TblPagos[ id=" + id + " ]";
    }

}
