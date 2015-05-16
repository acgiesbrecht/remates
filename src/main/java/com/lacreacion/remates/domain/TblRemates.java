/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lacreacion.remates.domain;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author AdrianGiesbrecht
 */
@Entity
@Table(name = "tbl_remates")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblRemates.findAll", query = "SELECT t FROM TblRemates t"),
    @NamedQuery(name = "TblRemates.findById", query = "SELECT t FROM TblRemates t WHERE t.id = :id"),
    @NamedQuery(name = "TblRemates.findByFecha", query = "SELECT t FROM TblRemates t WHERE t.fecha = :fecha"),
    @NamedQuery(name = "TblRemates.findByDescripcion", query = "SELECT t FROM TblRemates t WHERE t.descripcion = :descripcion")})
public class TblRemates implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Column(name = "descripcion")
    private String descripcion;
    @OneToMany(mappedBy = "idRemate")
    private Collection<TblPagos> tblPagosCollection;
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "tblRemates")
    private TblRematesCuotas tblRematesCuotas;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idRemate")
    private Collection<TblRematesDetalle> tblRematesDetalleCollection;

    public TblRemates() {
    }

    public TblRemates(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @XmlTransient
    public Collection<TblPagos> getTblPagosCollection() {
        return tblPagosCollection;
    }

    public void setTblPagosCollection(Collection<TblPagos> tblPagosCollection) {
        this.tblPagosCollection = tblPagosCollection;
    }

    public TblRematesCuotas getTblRematesCuotas() {
        return tblRematesCuotas;
    }

    public void setTblRematesCuotas(TblRematesCuotas tblRematesCuotas) {
        this.tblRematesCuotas = tblRematesCuotas;
    }

    @XmlTransient
    public Collection<TblRematesDetalle> getTblRematesDetalleCollection() {
        return tblRematesDetalleCollection;
    }

    public void setTblRematesDetalleCollection(Collection<TblRematesDetalle> tblRematesDetalleCollection) {
        this.tblRematesDetalleCollection = tblRematesDetalleCollection;
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
        if (!(object instanceof TblRemates)) {
            return false;
        }
        TblRemates other = (TblRemates) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return descripcion;
    }

}
