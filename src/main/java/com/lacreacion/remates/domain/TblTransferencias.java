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
@Table(name = "tbl_transferencias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblTransferencias.findAll", query = "SELECT t FROM TblTransferencias t"),
    @NamedQuery(name = "TblTransferencias.findByFechahora", query = "SELECT t FROM TblTransferencias t WHERE t.fechahora = :fechahora"),
    @NamedQuery(name = "TblTransferencias.findByConcepto", query = "SELECT t FROM TblTransferencias t WHERE t.concepto = :concepto"),
    @NamedQuery(name = "TblTransferencias.findByMonto", query = "SELECT t FROM TblTransferencias t WHERE t.monto = :monto"),
    @NamedQuery(name = "TblTransferencias.findById", query = "SELECT t FROM TblTransferencias t WHERE t.id = :id")})
public class TblTransferencias implements Serializable {
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "fechahora")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fechahora;
    @Column(name = "concepto")
    private String concepto;
    @Basic(optional = false)
    @Column(name = "monto")
    private int monto;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_miembro", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblMiembros idMiembro;
    @JoinColumn(name = "id_remate", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private TblRemates idRemate;

    public TblTransferencias() {
    }

    public TblTransferencias(Integer id) {
        this.id = id;
    }

    public TblTransferencias(Integer id, Date fechahora, int monto) {
        this.id = id;
        this.fechahora = fechahora;
        this.monto = monto;
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

    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        if (!(object instanceof TblTransferencias)) {
            return false;
        }
        TblTransferencias other = (TblTransferencias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.lacreacion.remates.domain.TblTransferencias[ id=" + id + " ]";
    }

}
