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
@Table(name = "tbl_recibos")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblRecibos.findAll", query = "SELECT t FROM TblRecibos t"),
    @NamedQuery(name = "TblRecibos.findById", query = "SELECT t FROM TblRecibos t WHERE t.id = :id"),
    @NamedQuery(name = "TblRecibos.findByFechahora", query = "SELECT t FROM TblRecibos t WHERE t.fechahora = :fechahora"),
    @NamedQuery(name = "TblRecibos.findByMonto", query = "SELECT t FROM TblRecibos t WHERE t.monto = :monto"),
    @NamedQuery(name = "TblRecibos.findByConcepto", query = "SELECT t FROM TblRecibos t WHERE t.concepto = :concepto")})
public class TblRecibos implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fechahora")
    @Temporal(TemporalType.TIME)
    private Date fechahora;
    @Column(name = "monto")
    private Integer monto;
    @Column(name = "concepto")
    private String concepto;
    @JoinColumn(name = "id_miembro", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblMiembros idMiembro;
    @JoinColumn(name = "id_remate", referencedColumnName = "id")
    @ManyToOne
    private TblRemates idRemate;

    public TblRecibos() {
    }

    public TblRecibos(Integer id) {
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

    public Integer getMonto() {
        return monto;
    }

    public void setMonto(Integer monto) {
        this.monto = monto;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
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
        if (!(object instanceof TblRecibos)) {
            return false;
        }
        TblRecibos other = (TblRecibos) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lacreacion.remates.domain.TblRecibos[ id=" + id + " ]";
    }

}
